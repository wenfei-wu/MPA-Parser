package mpa.representation;

import java.util.ArrayList;
import java.util.List;

public class References {
   public final String INTERFACE_T = "interface";
   public final String ACL_T = "acl";
   public final String ROUTER_T = "router";
   public final String ROUTEMAP_T = "routemap";
   
   // intra_ref
   // entity :  type, name
   List<String[]> entities;
   // reference: from_type, from_name, to_type, to_name;
   List<String[]> intra_ref;
   public References(){
      entities = new ArrayList<String[]>();
      intra_ref = new ArrayList<String[]>();
   }
   
   public void AddRefEntity(String type, String name){  // there may be duplicated 
      entities.add(new String[]{type, name});
   }
   public void AddIntraReferences(String from_t, String from_n, String to_t, String to_n){
      intra_ref.add(new String[]{from_t, from_n, to_t, to_n});
   }

   
   // inter_ref
      // bgp
   
   
   
   
   @Override
   public String toString(){
      return intra_ref.size()+",0";
   }

}
