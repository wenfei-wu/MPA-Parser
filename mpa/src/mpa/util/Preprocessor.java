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
      else if(vendor.equals("F5")){
         return F5Process(file);
      }
      else if(vendor.equals("Citrix")){
         return CitrixProcess(file);
      }
      else{
         
      }
      return null;
   }
   private String CitrixProcess(String file) throws IOException {
      String patterns[] = new String[]{"add service .*", 
            "add (lb|cs) vserver .*", "bind (lb|cs|ssl) vserver",
            "(add|bind) serviceGroup .*", "(add|bind) lb monitor .*", 
            "(add|bind) ssl cipher .*", "add .* policy .*",
            "(add|bind) vlan .*", "set interface .*"
            };
      String out = "";
      BufferedReader br = new BufferedReader(new FileReader(file));  
      String line = null;  
      while ((line = br.readLine()) != null)  
      {
         for(int i = 0; i<patterns.length; i++){
            if(line.matches(patterns[i])){
               line = line.replaceAll("\\\\\\\"", "'");
               out+= line+"\n";
               break;
            }
         }
      }
      return out;
   }
   private String F5Process(String file) throws IOException {
      String POOL = "pool";
      String VIRTUAL ="virtual";
      String PROFILE = "profile";
      String LTM = "ltm";
      String MONITOR = "monitor";
      String currentStanza = null;
      
      boolean inVirtualProfile = false;
      
      String out = "";
      BufferedReader br = new BufferedReader(new FileReader(file));  
      String line = null;  
      while ((line = br.readLine()) != null)  
      {
         if(line.startsWith(POOL) && line.endsWith("{")){
            currentStanza = POOL;
            out+= line+"\n";
         }
         else if(line.startsWith(VIRTUAL)){
            currentStanza = VIRTUAL;
            out+= line+"\n";            
         }
         else if(line.startsWith(PROFILE)){
            currentStanza = PROFILE;
            out+= line+"\n";            
         }
         else if(line.startsWith(LTM)){
            currentStanza = LTM;
            out+= line+"\n";
         }
         else if(line.startsWith(MONITOR)){
            currentStanza = MONITOR;
            out+= line+"\n";            
         }
         else if(line.startsWith("}")){
            if(currentStanza != null){
               out+= line+"\n";
            }
            currentStanza = null;
         }
         else if(!line.startsWith(" ")){
            
         }
         else{ // line starts with " "
            if(currentStanza == null){
               
            }
            else if(currentStanza.equals(VIRTUAL)){
               if(line.startsWith("   pool")){
                  out+= line+"\n";
               }
               else if(line.startsWith("   profiles")){
                  if(line.endsWith("{"))
                     inVirtualProfile = true;
                  if(line.endsWith("{") || line.endsWith("}"))
                     out+= line+"\n";
               }
               else if(line.startsWith("   }")){
                  if(inVirtualProfile){
                     out+= line+"\n";
                  }
                  inVirtualProfile = false;
               }
               else if(line.startsWith("    ")){ // subsubstanza
                  if(inVirtualProfile && !line.startsWith("         ")){
                     out+=line+"\n";
                  }
               }
               else{
                  inVirtualProfile = false;                  
               }
            }
            else if(currentStanza.equals(POOL)){
               if(line.startsWith("   monitor"))
                  out+= line+"\n";
            }
         }
      }
      return out;
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
      String CLAIM = "!";
      String content="";
      BufferedReader br = new BufferedReader(new FileReader(file));  
      String line = null;  
      while ((line = br.readLine()) != null)  
      {
         if(line.startsWith(CLAIM)){
            continue;
         }
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
      // interface
      String INTERFACE = "interface";
      // L3
      String OSPF = "router ospf";
      String BGP = "router bgp";
      // routemap
      String ROUTE_MAP = "route-map";
      // ACL
      String ACCESS_LIST = "access-list";
      String PREFIX_LIST = "prefix-list";
      String COMMUNITY_LIST = "community-list";
      // VLAN
      String VLAN = "vlan \\d+";
      // L2
      String UDLD = "udld aggressive";
      String MSTP = "spanning-tree mst configuration";
      String MST_MODE = "spanning-tree mode mst";
      
      String out = "";
      BufferedReader br = new BufferedReader(new FileReader(file));  
      String line = null;  
      while ((line = br.readLine()) != null)  
      {
         if(!line.startsWith(" ")){
            if(line.startsWith(INTERFACE) || // interface
                  line.startsWith(OSPF) || line.startsWith(BGP) || // L3
                  line.startsWith(ROUTE_MAP) || // routemap
                  line.startsWith(MSTP) || line.startsWith(MST_MODE) ){  // L2
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
               if(line.matches(VLAN) // VLAN
                     || line.contains(ACCESS_LIST)  // ACL 
                     || line.contains(PREFIX_LIST)
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
