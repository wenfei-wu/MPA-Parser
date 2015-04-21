package mpa.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import mpa.grammar.MpaCombinedParser;
import mpa.grammar.flatjuniper.control.FlatJuniperCombinedParser;
import mpa.grammar.flatjuniper.control.FlatJuniperExtractor;
import mpa.grammar.juniper.control.JuniperCombinedParser;
import mpa.grammar.juniper.control.JuniperFlattener;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Preprocessor {
   public String Process(String vendor, String file) throws IOException{
      if (vendor.equals("Cisco")){
         return CiscoProcess(file);
      } 
      else if (vendor.equals("Arista")){
         return AristaProcess(file);
      }
      else if (vendor.equals("Quanta")){
         return QuantaProcess(file);
      }
      else if(vendor.equals("Juniper")){
         return JuniperProcess(file);
      }
      else if(vendor.equals("Juniper-Flat")){
         return FlatJuniperProcess(file);
      }
      else{
         
      }
      return null;
   }
   private String FlatJuniperProcess(String file) throws IOException  {
      String SET = "set";
      String UNSET = "unset";
      String DEACTIVATE = "deactivate";
      String EXIT = "exit";
      
      String out = "";
      BufferedReader br = new BufferedReader(new FileReader(file));  
      String line = null;  
      while ((line = br.readLine()) != null)  
      {
         if(line.startsWith(SET) || line.startsWith(UNSET) 
               || line.startsWith(DEACTIVATE) || line.startsWith(EXIT) ){
            out+= line+"\n";
         }
         else{
           // System.out.println("Preprocessor FlatJuniper line: "+line);
         }
      }
      return out;
   }
   private String JuniperProcess(String file) throws IOException {
      String content="";
      BufferedReader br = new BufferedReader(new FileReader(file));  
      String line = null;  
      while ((line = br.readLine()) != null)  
      {
         content+= line+"\n";
      }
      MpaCombinedParser<?,?> parser = new JuniperCombinedParser(content);
      ParserRuleContext tree = parser.parse();
      JuniperFlattener flattener = new JuniperFlattener();
      ParseTreeWalker walker = new ParseTreeWalker();
      walker.walk(flattener, tree);
      return flattener.getFlattenedConfigurationText();
   }
   public String CiscoProcess(String file) throws IOException{
      boolean inStanza=false;
      String INTERFACE = "interface";
      String OSPF = "router ospf";
      String BGP = "router bgp";
      String ROUTE_MAP = "route-map";
      String MSTP = "spanning-tree mst configuration";
      String ACCESS_LIST = "access-list";
      String PREFIX_LIST = "prefix-list";
      String COMMUNITY_LIST = "community-list";
      String out = "";
      BufferedReader br = new BufferedReader(new FileReader(file));  
      String line = null;  
      while ((line = br.readLine()) != null)  
      {
         if(!line.startsWith(" ")){
            if(line.startsWith(INTERFACE) || line.startsWith(OSPF) ||
                  line.startsWith(BGP) || line.startsWith(ROUTE_MAP) ||
                  line.startsWith(MSTP)){
               inStanza = true;
            }
            else{
               inStanza = false;
            }
         }
         if (inStanza){
            out+=line+"\n";            
         }
         else{
            if(!line.startsWith(" ")){
               if(line.contains(ACCESS_LIST) || line.contains(PREFIX_LIST)
                     || line.contains(COMMUNITY_LIST)){
                  out+=line+"\n";
               }
            }
         }
      }
      return out;
   }
   public String AristaProcess(String file) throws IOException{
      boolean inStanza=false;
      String INTERFACE = "interface";
      String ACCESS_LIST = "access-list";
      String out = "";
      BufferedReader br = new BufferedReader(new FileReader(file));
      String line = null;
      while ((line = br.readLine()) != null)
      {
       	 if(!line.startsWith(" ")){
            if(line.startsWith(INTERFACE) || line.startsWith(ACCESS_LIST)){
               inStanza = true;
            }
            else{
               inStanza = false;
            }
         }
         if (inStanza){
            out+=line+"\n";
         }
         else{
            if(!line.startsWith(" ")){
               if(line.contains(ACCESS_LIST)){
                  out+=line+"\n";
               }
            }
         }
      }
  //    System.out.println(out);
      return out;
   }
 public String QuantaProcess(String file) throws IOException{
      boolean inStanza=false;
      String INTERFACE = "interface";
      String ACCESS_LIST = "access-list";
      String out = "";
      BufferedReader br = new BufferedReader(new FileReader(file));
      String line = null;
      while ((line = br.readLine()) != null)
      {
       	 if(!line.startsWith(" ")){
            if(line.startsWith(INTERFACE) || line.startsWith(ACCESS_LIST)){
               inStanza = true;
            }
            else{
               inStanza = false;
            }
         }
         if (inStanza){
            out+=line+"\n";
         }
         else{
            if(!line.startsWith(" ")){
               if(line.contains(ACCESS_LIST)){
                  out+=line+"\n";
               }
            }
         }
      }
  //    System.out.println(out);
      return out;
   }   
}
