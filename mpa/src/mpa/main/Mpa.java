package mpa.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import mpa.grammar.MpaCombinedParser;
import mpa.grammar.arista.control.AristaCombinedParser;
import mpa.grammar.arista.control.AristaExtractor;
import mpa.grammar.cisco.control.CiscoCombinedParser;
import mpa.grammar.cisco.control.CiscoExtractor;
import mpa.grammar.flatjuniper.FlatJuniperParser;
import mpa.grammar.flatjuniper.control.FlatJuniperCombinedParser;
import mpa.grammar.flatjuniper.control.FlatJuniperExtractor;
import mpa.grammar.quanta.control.QuantaCombinedParser;
import mpa.grammar.quanta.control.QuantaExtractor;
import mpa.representation.Configs;
import mpa.representation.Statistics;
import mpa.util.FileIO;
import mpa.util.Preprocessor;
import mpa.util.Util;

public class Mpa {
   private final int PER_THREAD_FILES_PER_ROUND = 10;
   private final int PER_THREAD_FILES_PER_REPORT = 100;
   
	String fileList;
	int numThread;
	String root;
	Map<String, Statistics> stats;
	String failures;
	int count;
	
	Lock inputLock, outputLock;
	MpaThread mpaThreads[];
	Queue<String> files = new LinkedList<String>();

   long startTime;
	public Mpa(String flist, String source_root, int nThread){
	   fileList = flist;
		numThread = nThread;
		root = source_root;
		stats = new HashMap<String, Statistics>();
		failures = "";
		count = 0;
		inputLock = new ReentrantLock();
		outputLock = new ReentrantLock();
		
		mpaThreads = new MpaThread[numThread];
		for(int i = 0; i<numThread; i++){
		   mpaThreads[i] = new MpaThread(i);
		}
		startTime = System.nanoTime();
	}
	
	public void Start(){
		Input();
		Process();
	}
	
	public Map<String, Statistics> GetStat(){
	   return stats;
	}

   private void Process() {
      Thread t[] = new Thread[numThread];
      for(int i = 0; i<numThread; i++){
         t[i] = new Thread(mpaThreads[i], "thread"+i);
      }
      for(int i = 0; i< numThread; i++){
         t[i].start();
//         System.out.println("===========");
      }
      for(int i =0; i<numThread; i++){
         try {
            t[i].join();
         } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }

   private void Input() {
      // TODO Auto-generated method stub

      BufferedReader br = null;
      try {
         br = new BufferedReader(new FileReader(fileList));
      } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }  
      String line = null;  
      try {
         while ((line = br.readLine()) != null)  
            files.add(line);
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
  //    System.out.println("Input: read "+files.size()+" files.");
   }

   
   private class MpaThread implements Runnable{
      int index ;
      Queue<String> localFiles;
      HashMap<String, Statistics> localOutput;
      
      int countInRound;
      int localCount;
      String localFailure;
      public MpaThread(int i){
         index= i;
         localFiles = new LinkedList<String>();
         localOutput = new HashMap<String, Statistics>();
         localCount = 0;
         localFailure = "";
    //     System.out.println("MpaThread "+i+" created");
      }

      @Override
      public void run() {
  //       System.out.println("MpaThread "+index+" start");
         while(true){
            // input
            boolean hasData = input();
            if(!hasData) break;
            // process
            process();
            // output
            output(false);
         }
         output(true);
      }

      private void output(boolean last) {
        // localCount+=countInRound;
         outputLock.lock();
         if(last || localCount >= PER_THREAD_FILES_PER_REPORT){
            stats.putAll(localOutput);
            count+=localCount;
            failures += localFailure;
            long endTime = System.nanoTime();    
            long elapsed = (endTime - startTime)/1000000000; // in second
            System.out.println(count+" file processed in "+elapsed+" second, report from thread "+index+", localCount is "+localCount);
          //  System.out.println("localFalures: "+localFailure);
            // clean for next output
            System.out.println("localCount is "+localCount+", localOutput size is "+localOutput.size());
            localOutput.clear();
            localCount = 0;
            localFailure = "";
         }
         outputLock.unlock();
         countInRound = 0;
      }

      private void process() {
         while(!localFiles.isEmpty()){
            String line = localFiles.remove();
            localCount++;
            //System.out.println(localCount);            
            
            String[] fields = line.split(",");
            if(fields.length!=7){
               localFailure += line+"\n";
               continue;
            }
            String stamp = fields[0];
            String device = fields[1];
            String config = fields[2];
            String vendor = fields[3];
            String file = root+'/'+stamp+'/'+device+'/'+config;
            Statistics stat  = parseVendorConfigurations(vendor, file);            
            if(stat!=null){
               localOutput.put(line, stat);
            }
            else{
               System.out.println("FAILURE: "+line);
               localFailure += line+"\n";
            }
         }
      }

      private Statistics parseVendorConfigurations(String vendor, String file) {
         String content;
         Preprocessor prep = new Preprocessor();
         try {
            content = prep.Process(vendor, file);
         } catch (Exception e) {
            e.printStackTrace();
            return null;
         }
         Statistics stat =null;
         try{
            if(vendor.equals("Cisco")){
               MpaCombinedParser<?,?> parser = new CiscoCombinedParser(content);
               ParserRuleContext tree = parser.parse();
               CiscoExtractor extractor = new CiscoExtractor();
               ParseTreeWalker walker = new ParseTreeWalker();
               walker.walk(extractor, tree);
               stat = extractor.getVendorConfiguration();
            }
            else if(vendor.equals("Arista")){
               MpaCombinedParser<?,?> parser = new AristaCombinedParser(content);
               ParserRuleContext tree = parser.parse();
               AristaExtractor extractor = new AristaExtractor();
               ParseTreeWalker walker = new ParseTreeWalker();
               walker.walk(extractor, tree);
               stat = extractor.getVendorConfiguration();
            }
            else if(vendor.equals("Quanta")){
               MpaCombinedParser<?,?> parser = new QuantaCombinedParser(content);
               ParserRuleContext tree = parser.parse();
               QuantaExtractor extractor = new QuantaExtractor();
               ParseTreeWalker walker = new ParseTreeWalker();
               walker.walk(extractor, tree);
               stat = extractor.getVendorConfiguration();
            }
            else if(vendor.equals("Juniper") || vendor.equals("Juniper-Flat")){
                MpaCombinedParser<?,?> parser = new FlatJuniperCombinedParser(content);
                ParserRuleContext tree = parser.parse();
                FlatJuniperExtractor extractor = new FlatJuniperExtractor();
                ParseTreeWalker walker = new ParseTreeWalker();
                walker.walk(extractor, tree);
                stat = extractor.getVendorConfiguration();
            }
            else{
            //   System.out.println("unknown vendor: "+ vendor);
            }     
         }catch(Exception e){
         //   System.out.println("Parse Error: "+file);
         }
         return stat;
      }

      private boolean input() {
         inputLock.lock();
         countInRound = 0;
         while(!files.isEmpty()){
            String file = files.remove();
            countInRound++;
            localFiles.add(file);
            //System.out.println("*");
            if(countInRound>=PER_THREAD_FILES_PER_ROUND) break;
         }
         inputLock.unlock();
     //    System.out.println("Thread "+index+" read "+countInRound+" files.");
         if(countInRound==0) return false;
         else return true;
      }   
   }


   public void WriteFailures(String filename) {
      FileIO.WriteToFile(failures, filename, false);
   }
   
   public void WriteStatistics(String filename){
      System.out.println("Wrting Statistics:");
      System.out.println("stamp, device, config, vendor, model, role, architecture");
      System.out.println("vlanDeclared, vlanInst");
      System.out.println(Util.Join(", ", Configs.L2Protocols));
      System.out.println("OSPF, BGP");
      System.out.println("References");
      String out="";
      for(Map.Entry<String, Statistics> entry: stats.entrySet()){
         out+=entry.getKey()+","+entry.getValue()+"\n";
      }
      FileIO.WriteToFile(out, "statistics.csv", false);
   }
}
