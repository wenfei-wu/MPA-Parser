package mpa.representation;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
   List<String> interfaces = new ArrayList<String>();
   List<String> acls = new ArrayList<String>();
   List<Reference> references = new ArrayList<Reference>();
   
   public boolean hasUDLD;
   public boolean hasMSTP;
   public boolean hasDHCP;
   public boolean hasDOT1Q;
   public boolean hasHSRP;
   public boolean hasLACP;
   
   public boolean hasOSPF;
   public boolean hasBGP;
   
   
   public int UDLDInst;
   public int MSTPInst;
   public int DHCPInst;
   public int DOT1QInst;
   public int HSRPInst;
   public int LACPInst;
   
   public int OSPFInst;
   public int BGPInst;
   
   public Statistics(){
	   hasUDLD = false;  UDLDInst = 0;
	   hasMSTP = false;  MSTPInst = 0;
	   hasDHCP = false;  DHCPInst = 0;
	   hasDOT1Q = false; DOT1QInst = 0;
	   hasHSRP = false;  HSRPInst = 0;
	   hasLACP = false;  LACPInst = 0;
	   hasOSPF = false;  OSPFInst = 0;
	   hasBGP = false;   BGPInst = 0;
   }
   
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
