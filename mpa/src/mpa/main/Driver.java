package mpa.main;

import java.util.Map;

import mpa.representation.Statistics;

public class Driver {

   public static void main(String[] args) {  
      if(args.length!=3){
         System.out.println("Error intput argument number: "+args.length);
         System.out.println("Usage: mpa <config_list file> <src root> <output path>");
         System.out.println("list_file format: stamp,device,config,vendor,role,model");
         System.exit(1);
      }
      // TODO Auto-generated method stub
      Mpa mpa = new Mpa(args[0], args[1], 5);
      mpa.Start();
      mpa.WriteFailures("failures.csv");
      Map<String, Statistics> stat = mpa.GetStat();
      System.out.println("TODO: output stat");
      mpa.WriteStatistics("statistics.csv");
   }
}
