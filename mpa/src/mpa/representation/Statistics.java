package mpa.representation;

import java.util.ArrayList;
import java.util.List;

import mpa.util.Util;

public class Statistics {

   L2Protocols l2protocols;
   L3Protocols l3protocols;
   Vlans vlans;
   References references;
  
   public Statistics(){
      l2protocols = new L2Protocols();
      l3protocols = new L3Protocols();
      vlans = new Vlans();
      references = new References();
   }
   
   // References
      // intra
   public void AddRefEntity(String type, String name){
      references.AddRefEntity(type, name);
   }
      // intra ref
   public void AddIntraRef(String from_t, String from_n, String to_t, String to_n){
      references.AddIntraReferences(from_t, from_n, to_t, to_n);
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
