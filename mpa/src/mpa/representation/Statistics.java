package mpa.representation;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
   List<String> interfaces = new ArrayList<String>();
   
   public void GetIface(String iface_name){
      interfaces.add(iface_name);
   }
   
   public void PrintIfaces(){
      System.out.println("In statistics");
      for(String i: interfaces){
         System.out.print(i+" ");
      }
      System.out.println();
   }

}
