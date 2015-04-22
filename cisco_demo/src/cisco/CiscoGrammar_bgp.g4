parser grammar CiscoGrammar_bgp;
import CiscoGrammarCommonParser;


address_family_header
:
   ADDRESS_FAMILY
   (
      ipv4 = IPV4
      | ipv6 = IPV6
      | vpnv4 = VPNV4
      | vpnv6 = VPNV6
   )
   (
      unicast = UNICAST
      | multicast = MULTICAST
      | mdt = MDT
      | ( vrf = VRF vrf_name = VARIABLE )
   )? NEWLINE
;

address_family_footer
:
   EXIT_ADDRESS_FAMILY NEWLINE
;

address_family_substanza
:
   address_family_header 
   (
      af_neighbor_substanza
      | af_network_substanza
      | af_no_neighbor_substanza
      | af_null_substanza
      | af_rb_substanza
   )* address_family_footer? 
;

af_neighbor_distribute_list_tail
:
   DISTRIBUTE_LIST num = DEC ( IN | OUT )? NEWLINE
;

af_neighbor_filter_list_tail
:
   FILTER_LIST list = DEC ( IN | OUT )? NEWLINE
;

af_neighbor_peer_group_tail
:
   PEER_GROUP group = VARIABLE NEWLINE
;

af_neighbor_prefix_list_tail
:
   PREFIX_LIST list = VARIABLE ( IN | OUT ) NEWLINE
;

af_neighbor_remote_as_tail
:
   ( LOCAL_AS | REMOTE_AS ) asNum = DEC NEWLINE
;

af_neighbor_route_map_tail
:
   ROUTE_MAP map = VARIABLE ( IN | OUT )? NEWLINE
;

af_neighbor_substanza
:
  NEIGHBOR 
  ( 
     ( ip = IP_ADDRESS  )
     | ipv6 = IPV6_ADDRESS
     | ( group = VARIABLE   )
  )
  (
     af_neighbor_distribute_list_tail
     | af_neighbor_filter_list_tail
     | af_neighbor_null_tail
     | af_neighbor_peer_group_tail
     | af_neighbor_prefix_list_tail
     | af_neighbor_remote_as_tail
     | af_neighbor_route_map_tail
  )
;

af_network_substanza
:
   NETWORK 
   (
      ( ip = IP_ADDRESS ( MASK mask =IP_ADDRESS )? ) 
      | prefix = IP_PREFIX
      | ipv6 = IPV6_PREFIX
   ) 
   ( ROUTE_MAP VARIABLE )?
   NEWLINE
;

af_no_neighbor_substanza
:
   no_neighbor_substanza
;

af_rb_substanza
:
   REDISTRIBUTE
   (
      af_rb_connected_tail
      | af_rb_direct_tail
      | af_rb_static_tail
   )
;

af_rb_connected_tail
:
   CONNECTED ( ROUTE_MAP map = VARIABLE   )? NEWLINE
;

af_rb_direct_tail
:
   DIRECT ROUTE_MAP map = VARIABLE NEWLINE
;

af_rb_static_tail
:
   STATIC ( ROUTE_MAP map = VARIABLE  )? NEWLINE
;

neighbor_nexus_address_family_substanza
:
   address_family_header
   (
      neighbor_nexus_af_filter_list_substanza
      | neighbor_nexus_af_prefix_list_substanza
      | neighbor_nexus_af_route_map_substanza
      | neighbor_nexus_af_null_substanza
   )*
;

neighbor_filter_list_tail
:
   FILTER_LIST list = DEC ( IN | OUT )? NEWLINE
;

neighbor_nexus_af_filter_list_substanza
:
   FILTER_LIST list = DEC ( IN | OUT )? NEWLINE
;

neighbor_nexus_af_prefix_list_substanza
:
   PREFIX_LIST list = VARIABLE ( IN | OUT )? NEWLINE
;

neighbor_nexus_af_route_map_substanza
:
   ROUTE_MAP map = VARIABLE ( IN | OUT )? NEWLINE
;

neighbor_nexus_inherit_substanza
:
   INHERIT PEER peer = VARIABLE NEWLINE
;

neighbor_nexus_no_remote_as_substanza
:
   NO REMOTE_AS asNum = DEC NEWLINE
;

neighbor_nexus_remote_as_substanza
:
   REMOTE_AS asNum = DEC NEWLINE
;

neighbor_nexus_substanza
:
   NEIGHBOR 
   (
      ( ip = IP_ADDRESS  )
      | ipv6 = IPV6_ADDRESS
      | ( prefix = IP_PREFIX  )
      | prefixv6 = IPV6_PREFIX
   ) 
   ( REMOTE_AS asNum = DEC  )? NEWLINE
   (  
      neighbor_nexus_address_family_substanza
      | neighbor_nexus_inherit_substanza
      | neighbor_nexus_no_remote_as_substanza
      | neighbor_nexus_null_substanza
      | neighbor_nexus_remote_as_substanza
   )*
;

af_neighbor_null_tail
:
   neighbor_null_tail
;

template_af_null_subsubstanza
:
   neighbor_null_tail
;

template_null_substanza
:
   neighbor_null_tail
   | ( NO 
         (
            DESCRIPTION
            | SHUTDOWN
         ) ~NEWLINE* NEWLINE 
     )
;

neighbor_empty_tail
:
   NEWLINE
;

neighbor_nexus_af_null_substanza
:
   neighbor_null_tail
;

neighbor_nexus_null_substanza
:
   neighbor_null_tail
   | ( 
        NO 
        (
           ROUTE_MAP
           | SHUTDOWN
        ) ~NEWLINE* NEWLINE 
     ) 
;

neighbor_null_tail
:
   (
      ACTIVATE
      | ALLOWAS_IN
      | DEFAULT_ORIGINATE
      | DESCRIPTION
      | DONT_CAPABILITY_NEGOTIATE
      | EBGP_MULTIHOP
      | MAXIMUM_PEERS
      | MAXIMUM_PREFIX
      | NEXT_HOP_SELF
      | PASSWORD
      | REMOVE_PRIVATE_AS
      | ROUTE_REFLECTOR_CLIENT
      | SEND_COMMUNITY
      | SEND_LABEL
      | SHUTDOWN
      | SOFT_RECONFIGURATION
      | TIMERS
      | UPDATE_SOURCE
      | VERSION
   ) ~NEWLINE* NEWLINE
;

neighbor_peer_group_tail
:
   PEER_GROUP ( group = VARIABLE    )? NEWLINE  
;

neighbor_prefix_list_tail
:
   PREFIX_LIST list = VARIABLE ( IN | OUT )? NEWLINE
;

neighbor_remote_as_tail
:
   REMOTE_AS asNum = DEC NEWLINE
;

neighbor_route_map_tail
:
   ROUTE_MAP map = VARIABLE ( IN | OUT )? NEWLINE
;

neighbor_standalone_substanza
:
   NEIGHBOR
   (
      ( ip = IP_ADDRESS  )
      | ip6 = IPV6_ADDRESS
      | ( peergroup = VARIABLE  )
   )
   (
      neighbor_filter_list_tail
      | neighbor_null_tail
      | neighbor_peer_group_tail
      | neighbor_prefix_list_tail
      | neighbor_remote_as_tail
      | neighbor_route_map_tail
   )
;

network_substanza
:
   NETWORK ( ip = IP_ADDRESS ( MASK mask = IP_ADDRESS )? ) 
   ( ROUTE_MAP map = VARIABLE  )?
   NEWLINE
;

no_neighbor_substanza
:
   NO NEIGHBOR ( IP_ADDRESS | VARIABLE | IPV6_ADDRESS )
   (
      ACTIVATE
      | ACTIVE
      | TRANSPORT
   ) ~NEWLINE* NEWLINE
;

af_null_substanza
:
   null_bgp_substanza
;

null_bgp_substanza
:
   NO?
   (
      AGGREGATE_ADDRESS
      | AUTO_SUMMARY
      | BESTPATH
      | BGP
      | DEFAULT_INFORMATION
      | LOG_NEIGHBOR_CHANGES
      | MAXIMUM_PATHS
      | SHUTDOWN
      | SYNCHRONIZATION
      | ( TEMPLATE PEER_SESSION )
   ) ~NEWLINE* NEWLINE
;

redistribute_substanza
:
   REDISTRIBUTE ( STATIC | CONNECTED ) ( ROUTE_MAP map = VARIABLE )? NEWLINE
;

// !!!!!!!!!!!!!!! router bgp
router_bgp_stanza
:
   ROUTER BGP asNum = DEC NEWLINE  
   (
      address_family_substanza
      | neighbor_nexus_substanza
      | neighbor_standalone_substanza
      | network_substanza
      | no_neighbor_substanza
      | null_bgp_substanza
      | redistribute_substanza
      | template_peer_substanza
      | vrf_nexus_substanza
   )*
;
//!!!!!!!!!! template 
template_peer_substanza
:
   TEMPLATE PEER name = VARIABLE NEWLINE
   (
      template_address_family_substanza
      | template_inherit_substanza
      | template_null_substanza
      | template_remote_as_substanza
   )*
;

template_address_family_header
:
   address_family_header
;

template_address_family_substanza
:
   template_address_family_header
   (
      template_af_null_subsubstanza
      | template_af_prefix_list_substanza
      | template_af_route_map_subsubstanza
   )*
;

template_af_prefix_list_substanza
:
   PREFIX_LIST list = VARIABLE ( IN | OUT )? NEWLINE
;

template_af_route_map_subsubstanza
:
   ROUTE_MAP map = VARIABLE ( IN | OUT )? NEWLINE
;

template_inherit_substanza
:
   INHERIT PEER_SESSION inherit = VARIABLE NEWLINE
;

template_remote_as_substanza
:
   REMOTE_AS asNum = DEC NEWLINE
;

vrf_address_family_nexus_stanza
:
   address_family_header
   (
      vrf_af_network_substanza
     | vrf_af_null_substanza
   )*
;

vrf_af_network_substanza
:
   NETWORK 
   (
      prefix = IP_PREFIX
      | ipv6 = IPV6_ADDRESS
   ) NEWLINE
;

vrf_af_null_substanza
:
   (
      MAXIMUM_PATHS
   ) ~NEWLINE* NEWLINE
;

vrf_neighbor_nexus_stanza
:
   NEIGHBOR
   (
      ( ip = IP_ADDRESS )
      | ipv6 = IPV6_ADDRESS
   )
   NEWLINE
   (
      vrf_neighbor_inherit_substanza
      | vrf_neighbor_null_substanza
   )*
;

vrf_neighbor_inherit_substanza
:
   INHERIT PEER peer = VARIABLE NEWLINE
;

vrf_neighbor_null_substanza
:
   (
      DESCRIPTION
      | DEFAULT
   ) ~NEWLINE* NEWLINE
;

vrf_nexus_substanza
:
   VRF name = VARIABLE NEWLINE
   (      
      vrf_address_family_nexus_stanza
      | vrf_neighbor_nexus_stanza
      | vrf_null_stanza
   )*
;

vrf_null_stanza
:
   (
      ROUTER_ID
   ) ~NEWLINE* NEWLINE
;
