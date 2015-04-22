package mpa.representation;

import java.util.ArrayList;
import java.util.List;

import mpa.util.Util;

public class Statistics {

   L2Protocols l2protocols;
   L3Protocols l3protocols;
   Vlans vlans;
   References references;

   public List<String> interfaces = new ArrayList<String>();
   public List<String> acls = new ArrayList<String>();
   public List<Reference> references = new ArrayList<Reference>();
   public List<BGPNeighbor> bgpNeighbors = new ArrayList<BGPNeighbor>();
   
   public String asNumber;
   
   public boolean hasUDLD;
   public boolean hasMSTP;
   public boolean hasDHCP;
   public boolean hasDOT1Q;
   public boolean hasHSRP;
   public boolean hasLACP;
   
   public boolean hasOSPF;
   public boolean hasBGP;
   
   public Statistics(){
      l2protocols = new L2Protocols();
      l3protocols = new L3Protocols();
      vlans = new Vlans();
      references = new References();
   }
   
   
   // VLAN
   public void DeclareVlan(String vlan){
      vlans.DeclareVlan(vlan);
   }
   public void IfaceVlan(String iface, String vlan){
      vlans.IfaceVlan(iface, vlan);
   }
   
   // l2 protocols
   public void DeclareL2Proto(String proto){
      l2protocols.DeclareProtocol(proto);
   }
   public void L2ProtoInst(String proto, String info){
      l2protocols.ProtoInst(proto, info);
   }
   
   // l3 protocols  TODO
   
   @Override
   public String toString(){
      String fields[] = new String[] {vlans.toString(), l2protocols.toString(), 
            l3protocols.toString(), references.toString() };
      return Util.Join(",", fields);
   }
}
