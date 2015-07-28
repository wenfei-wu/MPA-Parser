package mpa.main;

import mpa.representation.Configs;

public class Driver {

   public static void main(String[] args) {
      if(Configs.debug){
         Debug d = new Debug(Configs.root, Configs.filelist);
         d.Run();
         return ;
      }
      
      if(args.length!=3){
         System.out.println("Missing arguments");
         System.out.println("Usage: mpa <config_list file> <src root> <output path>");
         System.out.println("list_file format: stamp,device,config,vendor,role,model");
         System.exit(1);
      }

      String configList = args[0];
      String configsDir = args[1];
      String outputDir = args[2];
      String statisticsFile = outputDir + "/statistics.csv";
      String failuresFile = outputDir + "/failures.csv";
      String warningsFile = outputDir + "/warnings.csv";

      Mpa mpa = new Mpa(configList, configsDir, 3, statisticsFile, 
              failuresFile);
      System.out.println("===================================================");
      System.out.println("MPA start parsing");
      System.out.println("===================================================");
      System.out.println("===================================================");
      System.out.println("MPA write statistics");
      mpa.WriteStatistics(null);
      System.out.println("===================================================");
      mpa.Start();
      //mpa.WriteFailures("failures.csv");
      mpa.WriteWarnings(warningsFile);
      //mpa.WriteStatistics("statistics.csv");
   }
}
