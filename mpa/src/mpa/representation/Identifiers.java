package mpa.representation;

public class Identifiers {
   
   // reference type
      // acl
   public final static String ACCESS_T="access-list";
   public final static String COMMUNITY_T="community-list";
   public final static String PREFIX_T="prefix-list";
 //  public final static String FILTER_T = "filter-list";
      // Stanza type
   //public final String ACL_T = "acl";
   public final static String ROUTEMAP_T = "routemap";
   public final static String IFACE_T = "iface";
      // router
   public final static String OSPF_T = "ospf";
   public final static String BGP_T = "bgp";
   
   // address format
   public final String IP_F = "ip";
   public final String PREFIX_F = "subnet";
   public final String IPMASK_F = "ip/mask";
}
