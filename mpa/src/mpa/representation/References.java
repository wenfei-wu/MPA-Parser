package mpa.representation;

import java.util.ArrayList;
import java.util.List;

public class References {
   public final String INTERFACE_T = "interface";
   public final String ACL_T = "acl";
   public final String ROUTER_T = "router";
   public final String ROUTEMAP_T = "routemap";
   
   // internal
   // entity :  type, name
   List<String[]> entities;
   // reference: from_type, from_name, to_type, to_name;
   List<String[]> references;
   public References(){
      entities = new ArrayList<String[]>();
      references = new ArrayList<String[]>();
   }
   
   public void AddEntity(String type, String name){
      
   }
   public void AddInternalReferences(String from_t, String from_n, String to_t, String to_n){
      references.add(new String[]{from_t, from_n, to_t, to_n});
   }
   
   // external, special
      // bgp

}
