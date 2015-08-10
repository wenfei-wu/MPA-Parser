package mpa.main;

import mpa.representation.Configs;
import mpa.representation.Statistics;

public class Driver {

   public static void main(String[] args) {
      if(Configs.debug){
         Debug d = new Debug(Configs.root, Configs.filelist);
         d.Run();
         return ;
      }
      
      for (String arg : args) {
    	  if (arg.equals("-h") || arg.equals("--help")) { 
    		  printUsage();
    		  System.exit(1);
    	  }
      }
      
      if (args.length == 2) {
    	  String configFile = args[0];
    	  String vendor = args[1];
    	  Statistics stats = Mpa.parseVendorConfigurations(vendor, configFile);
    	  System.out.println(stats.toString());
      }
      else if (args.length == 3) {
          String configList = args[0];
          String configsDir = args[1];
          String outputDir = args[2];
          String statisticsFile = outputDir + "/statistics.csv";
          String failuresFile = outputDir + "/failures.csv";
          String warningsFile = outputDir + "/warnings.csv";

          Mpa mpa = new Mpa(configList, configsDir, 3, statisticsFile, 
                  failuresFile);
          System.out.println("===============================================");
          System.out.println("MPA starting...");
          mpa.Start();
          //mpa.WriteFailures("failures.csv");
          mpa.WriteWarnings(warningsFile);
          //mpa.WriteStatistics("statistics.csv");
      }
      else {
    	  printUsage();
		  System.exit(1);
      }
   }
   
   private static void printUsage()
   {
       System.out.println("Usage:");
       System.out.println("\tmpa <config file> Arista|Cisco|Citrix|F5|Juniper|Quanta");
       System.out.println("\tmpa <config list file> <src root> <output path>");
       System.out.println("config list file format:");
       System.out.println("\tstamp,device,config,vendor,role,model");
       System.exit(1);
   }
}
