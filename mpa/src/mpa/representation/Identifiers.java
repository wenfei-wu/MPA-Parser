package mpa.representation;

public class Identifiers {
   
   // reference type
      // cisco
      // acl
   public final static String ACCESS_T="access-list";
   public final static String COMMUNITY_T="community-list";
   public final static String PREFIX_T="prefix-list";
   //public final static String FILTER_T = "filter-list";
      // Stanza type
   //public final String ACL_T = "acl";
   public final static String ROUTEMAP_T = "routemap";
   public final static String IFACE_T = "iface";
      // router
   public final static String OSPF_T = "ospf";
   public final static String BGP_T = "bgp";
      // juniper
   public final static String GROUP_T = "group";
   public final static String POLICY_T = "policy";
   public final static String AS_T = "as-path";
   public final static String FILTER_T = "filter";
   public final static String TERM_T = "term";
   public final static String R_INST_T = "routing-instance";
   public final static String R_OPT_T = "routing-options";
   
   // address format
   //public final String IP_F = "ip";
   //public final String PREFIX_F = "subnet";
   //public final String IPMASK_F = "ip/mask";
   
   // juniper BGP hierarchy
   public final static String BGP_GLOBAL = "bgp-global";
   public final static String BGP_GROUP = "bgp-group";
   public final static String BGP_NEIGHBOR = "bgp-neighbor";
}
