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
import mpa.grammar.quanta.control.QuantaCombinedParser;
import mpa.grammar.quanta.control.QuantaExtractor;
import mpa.representation.Statistics;
import mpa.util.Preprocessor;

public class Mpa {
   private final int PER_THREAD_FILES_PER_ROUND = 10;
   private final int PER_THREAD_FILES_PER_REPORT = 100;
   
	String fileList;
	int numThread;
	String root;
	Map<String, Statistics> output;
	int count;
	
	Lock inputLock, outputLock;
	MpaThread mpaThreads[];
	Queue<String> files = new LinkedList<String>();

   long startTime;
	public Mpa(String flist, String source_root, int nThread){
	   fileList = flist;
		numThread = nThread;
		root = source_root;
		output = new HashMap<String, Statistics>();
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
	   return output;
	}

   private void Process() {
      // TODO Auto-generated method stub

      Thread t[] = new Thread[numThread];
      for(int i = 0; i<numThread; i++){
         t[i] = new Thread(mpaThreads[i], "thread"+i);
      }
      for(int i = 0; i< numThread; i++){
         t[i].run();
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
      }

      @Override
      public void run() {
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
         localCount+=countInRound;
         outputLock.lock();
         if(last || localCount % PER_THREAD_FILES_PER_REPORT==0){
            output.putAll(localOutput);
            count+=localCount;
            long endTime = System.nanoTime();    
            long elapsed = (endTime - startTime)/1000000000; // in second
            System.out.println(count+" file processed in "+elapsed+" second, report from thread "+index+", localCount is "+localCount);
            System.out.println("localFalures: "+localFailure);
            // clean for next output
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
            if(fields.length!=6){
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
         } catch (IOException e) {
            e.printStackTrace();
            return null;
         }
         Statistics stat =null;
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
         else{
            System.out.println("unknown vendor: "+ vendor);
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
            if(countInRound>=PER_THREAD_FILES_PER_ROUND) break;
         }
         inputLock.unlock();
         if(countInRound==0) return false;
         else return true;
      }
   
   }
}
