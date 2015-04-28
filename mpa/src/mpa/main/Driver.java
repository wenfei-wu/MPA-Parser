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
         System.out.println("Error intput argument number: "+args.length);
         System.out.println("Usage: mpa <config_list file> <src root> <output path>");
         System.out.println("list_file format: stamp,device,config,vendor,role,model");
         System.exit(1);
      }
      // TODO Auto-generated method stub
      Mpa mpa = new Mpa(args[0], args[1], 5, "statistics.csv", "failures.csv");
      System.out.println("====================================================");
      System.out.println("MPA start parsing");
      System.out.println("====================================================");
      System.out.println("====================================================");
      System.out.println("MPA write statistics");
      mpa.WriteStatistics(null);
      System.out.println("====================================================");
      mpa.Start();
      //mpa.WriteFailures("failures.csv");
      mpa.WriteWarnings("warnings.csv");
      //mpa.WriteStatistics("statistics.csv");
   }
}
