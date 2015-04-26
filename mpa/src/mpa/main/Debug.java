package mpa.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import mpa.grammar.MpaCombinedParser;
import mpa.grammar.arista.control.AristaCombinedParser;
import mpa.grammar.arista.control.AristaExtractor;
import mpa.grammar.cisco.control.CiscoCombinedParser;
import mpa.grammar.cisco.control.CiscoExtractor;
import mpa.grammar.flatjuniper.control.FlatJuniperCombinedParser;
import mpa.grammar.flatjuniper.control.FlatJuniperExtractor;
import mpa.grammar.quanta.control.QuantaCombinedParser;
import mpa.grammar.quanta.control.QuantaExtractor;
import mpa.representation.Configs;
import mpa.representation.Statistics;
import mpa.util.FileIO;
import mpa.util.Preprocessor;
import mpa.util.Util;

public class Debug {
   String root;
   String filelist = "flatjuniper_examples.csv";
   String file;
   String content;
   
   public Debug(String r, String fl){
      root = r;
      filelist = fl;
   }
   
   public void Run(){
      // read a line
      BufferedReader br = null;
      try {
         br = new BufferedReader(new FileReader(filelist));
      } catch (FileNotFoundException e) {
         e.printStackTrace();
         return ;
      }
      try {
         file = br.readLine();
      } catch (IOException e) {
         e.printStackTrace();
         return ;
      }

      System.out.println("Looking into Configuration File: "+file);
      String[] fields = file.split(",");
      if(fields.length!=7){
         System.out.println("Error: "+file);
         return ;
      }
      String stamp = fields[0];
      String device = fields[1];
      String config = fields[2];
      String vendor = fields[3];
      String file = root+'/'+stamp+'/'+device+'/'+config;
      // preprocess
      Preprocessor prep = new Preprocessor();
      try {
         content = prep.Process(vendor, file);
      } catch (Exception e) {
         e.printStackTrace();
         return ;
      }
      FileIO.WriteToFile(content, "debug_content.cfg", false);
      // parse
      //content = "set interfaces xe-0/1/0 unit 617 vlan-id 617\n";
      Statistics stat = parseVendorConfigurations(vendor, content);

      System.out.println("vlanDeclared, vlanInst");
      System.out.println(Util.Join(", ", Configs.L2Protocols));
      System.out.println("OSPF, BGP");
      System.out.println("References");
      
      System.out.println(stat);
   }
   

   private Statistics parseVendorConfigurations(String vendor, String content) {
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
         e.printStackTrace();
         System.out.println("Parse Error: "+file);
      }
      return stat;
   }
}
