package mpa.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import mpa.representation.Configs;
import mpa.representation.Statistics;
import mpa.util.FileIO;
import mpa.util.Preprocessor;
import mpa.util.Util;

public class Debug {
   String root;
   String filelist;
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
         br.close();
      } catch (IOException e) {
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
      Statistics stat = Mpa.parseVendorConfigurations(vendor, file);

      System.out.println("vlanDeclared, vlanInst");
      System.out.println(Util.Join(", ", Configs.L2Protocols));
      System.out.println("OSPF, BGP");
      System.out.println("References");
      
      System.out.println(stat);
   }
}

