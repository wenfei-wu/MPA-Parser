package mpa.grammar.cisco.control;

import org.antlr.v4.runtime.misc.NotNull;

import mpa.grammar.MpaExtractor;
import mpa.grammar.cisco.CiscoGrammar;
import mpa.grammar.cisco.CiscoGrammarBaseListener;
import mpa.representation.Identifiers;
import mpa.representation.Statistics;
import mpa.util.Util;

public class CiscoExtractor extends CiscoGrammarBaseListener
         implements MpaExtractor {
   Statistics stat = new Statistics();
   @Override
   public Statistics getVendorConfiguration() {
      return stat;
   }

   // util helper:  intra-config entity, current stanza
   String currentIface;
   @Override public void enterInterface_stanza(@NotNull CiscoGrammar.Interface_stanzaContext ctx) { 
      currentIface = ctx.iname.getText();
      stat.AddRefEntity(Identifiers.IFACE_T, currentIface);
   }   
   @Override public void exitInterface_stanza(@NotNull CiscoGrammar.Interface_stanzaContext ctx) { 
      currentIface = null;
   }
   String currentRoutemap = null;
   @Override public void enterRoute_map_stanza(@NotNull CiscoGrammar.Route_map_stanzaContext ctx) {
      if(ctx.named!=null){
         stat.AddRefEntity(Identifiers.ROUTEMAP_T, ctx.named.name.getText());
         currentRoutemap = ctx.named.name.getText();
      }
   }
   @Override public void exitRoute_map_stanza(@NotNull CiscoGrammar.Route_map_stanzaContext ctx) { 
      currentRoutemap = null;
   }
   String currentBgp = null;
   @Override public void enterRouter_bgp_stanza(@NotNull CiscoGrammar.Router_bgp_stanzaContext ctx) { 
      stat.AddRefEntity(Identifiers.BGP_T, ctx.asNum.getText());
      currentBgp = ctx.asNum.getText();
   }
   @Override public void exitRouter_bgp_stanza(@NotNull CiscoGrammar.Router_bgp_stanzaContext ctx) {
      currentBgp = null;
   }
   String currentOspf = null;
   @Override public void enterRouter_ospf_stanza(@NotNull CiscoGrammar.Router_ospf_stanzaContext ctx) { 
      stat.AddRefEntity(Identifiers.OSPF_T, ctx.procnum.getText());
      currentOspf = ctx.procnum.getText();
   }
   @Override public void exitRouter_ospf_stanza(@NotNull CiscoGrammar.Router_ospf_stanzaContext ctx) {
      currentOspf = null;
   }
   
   // Intra-Config Reference
      // Entitiy
         // ACL
   @Override public void enterAppletalk_access_list_numbered_stanza(@NotNull CiscoGrammar.Appletalk_access_list_numbered_stanzaContext ctx) { 
      stat.AddRefEntity(Identifiers.ACCESS_T, ctx.name.getText());
   }
   @Override public void enterArp_access_list_stanza(@NotNull CiscoGrammar.Arp_access_list_stanzaContext ctx) { 
      stat.AddRefEntity(Identifiers.ACCESS_T, ctx.name.getText());
   }
   @Override public void enterExtended_access_list_named_stanza(@NotNull CiscoGrammar.Extended_access_list_named_stanzaContext ctx) { 
      stat.AddRefEntity(Identifiers.ACCESS_T, ctx.name.getText());
   }
   @Override public void enterExtended_access_list_numbered_stanza(@NotNull CiscoGrammar.Extended_access_list_numbered_stanzaContext ctx) { 
      stat.AddRefEntity(Identifiers.ACCESS_T, ctx.name.getText());
   }
   @Override public void enterIp_as_path_numbered_stanza(@NotNull CiscoGrammar.Ip_as_path_numbered_stanzaContext ctx) { 
      stat.AddRefEntity(Identifiers.ACCESS_T, ctx.name.getText());
   }
   @Override public void enterIp_community_list_expanded_named_stanza(@NotNull CiscoGrammar.Ip_community_list_expanded_named_stanzaContext ctx) { 
      stat.AddRefEntity(Identifiers.COMMUNITY_T, ctx.name.getText());
   }
   @Override public void enterIp_community_list_expanded_numbered_stanza(@NotNull CiscoGrammar.Ip_community_list_expanded_numbered_stanzaContext ctx) { 
      stat.AddRefEntity(Identifiers.COMMUNITY_T, ctx.name.getText());
   }
   @Override public void enterIp_extcommunity_list_stanza(@NotNull CiscoGrammar.Ip_extcommunity_list_stanzaContext ctx) { 
      stat.AddRefEntity(Identifiers.COMMUNITY_T, ctx.name.getText());
   }
   @Override public void enterIp_community_list_standard_named_stanza(@NotNull CiscoGrammar.Ip_community_list_standard_named_stanzaContext ctx) {
      stat.AddRefEntity(Identifiers.COMMUNITY_T, ctx.name.getText());
   }
   @Override public void enterIp_community_list_standard_numbered_stanza(@NotNull CiscoGrammar.Ip_community_list_standard_numbered_stanzaContext ctx) { 
      stat.AddRefEntity(Identifiers.COMMUNITY_T, ctx.name.getText());
   }
   @Override public void enterIp_prefix_list_named_stanza(@NotNull CiscoGrammar.Ip_prefix_list_named_stanzaContext ctx) { 
      stat.AddRefEntity(Identifiers.PREFIX_T, ctx.name.getText());
   }
   @Override public void enterIpx_sap_access_list_numbered_stanza(@NotNull CiscoGrammar.Ipx_sap_access_list_numbered_stanzaContext ctx) { 
      stat.AddRefEntity(Identifiers.ACCESS_T, ctx.name.getText());
   }
   @Override public void enterMac_access_list_stanza_tail(@NotNull CiscoGrammar.Mac_access_list_stanza_tailContext ctx) { 
      stat.AddRefEntity(Identifiers.ACCESS_T, ctx.num.getText());
   }
   @Override public void enterNexus_access_list_stanza(@NotNull CiscoGrammar.Nexus_access_list_stanzaContext ctx) {
      stat.AddRefEntity(Identifiers.ACCESS_T, ctx.name.getText());
   }
   @Override public void enterNexus_prefix_list_stanza(@NotNull CiscoGrammar.Nexus_prefix_list_stanzaContext ctx) {
      stat.AddRefEntity(Identifiers.PREFIX_T, ctx.name.getText());
   }
   @Override public void enterProtocol_type_code_access_list_numbered_stanza(@NotNull CiscoGrammar.Protocol_type_code_access_list_numbered_stanzaContext ctx) {
      stat.AddRefEntity(Identifiers.ACCESS_T, ctx.name.getText());
   }
   @Override public void enterStandard_access_list_named_stanza(@NotNull CiscoGrammar.Standard_access_list_named_stanzaContext ctx) {
      stat.AddRefEntity(Identifiers.ACCESS_T, ctx.name.getText());
   }
   @Override public void enterStandard_access_list_numbered_stanza(@NotNull CiscoGrammar.Standard_access_list_numbered_stanzaContext ctx) { 
      stat.AddRefEntity(Identifiers.ACCESS_T, ctx.name.getText());
   }
         // route-map
   //@Override public void enterRoute_map_named_stanza(@NotNull CiscoGrammar.Route_map_named_stanzaContext ctx) { }
      // ref
         // iface to xxx
   @Override public void enterIp_policy_if_stanza(@NotNull CiscoGrammar.Ip_policy_if_stanzaContext ctx) { 
      stat.AddIntraRef(Identifiers.IFACE_T, currentIface, Identifiers.ROUTEMAP_T, ctx.name.getText());
   }
   @Override public void enterMac_access_group_if_stanza(@NotNull CiscoGrammar.Mac_access_group_if_stanzaContext ctx) {
      stat.AddIntraRef(Identifiers.IFACE_T, currentIface, Identifiers.ACCESS_T, ctx.name.getText());
   }
   @Override public void enterIp_access_group_if_stanza(@NotNull CiscoGrammar.Ip_access_group_if_stanzaContext ctx) {
      stat.AddIntraRef(Identifiers.IFACE_T, currentIface, Identifiers.ACCESS_T, ctx.name.getText());
   }
   @Override public void enterIp_ospf_router_if_stanza(@NotNull CiscoGrammar.Ip_ospf_router_if_stanzaContext ctx) {
      stat.AddIntraRef(Identifiers.IFACE_T, currentIface, Identifiers.OSPF_T, ctx.procnum.getText());
         // this is for OSPF
      stat.IfaceOspfArea(currentIface, ctx.procnum.getText(), ctx.area.getText());
   }
         // bgp to xxx
   @Override public void enterAf_neighbor_route_map_tail(@NotNull CiscoGrammar.Af_neighbor_route_map_tailContext ctx) {
      stat.AddIntraRef(Identifiers.BGP_T, currentBgp, Identifiers.ROUTEMAP_T, ctx.map.getText());
   }
   @Override public void enterAf_rb_connected_tail(@NotNull CiscoGrammar.Af_rb_connected_tailContext ctx) {
      if(ctx.map!= null)
         stat.AddIntraRef(Identifiers.BGP_T, currentBgp, Identifiers.ROUTEMAP_T, ctx.map.getText());
   }
   @Override public void enterAf_rb_direct_tail(@NotNull CiscoGrammar.Af_rb_direct_tailContext ctx) {
      stat.AddIntraRef(Identifiers.BGP_T, currentBgp, Identifiers.ROUTEMAP_T, ctx.map.getText());
   }
   @Override public void enterAf_rb_static_tail(@NotNull CiscoGrammar.Af_rb_static_tailContext ctx) {
      if(ctx.map!= null)
         stat.AddIntraRef(Identifiers.BGP_T, currentBgp, Identifiers.ROUTEMAP_T, ctx.map.getText());      
   }
   @Override public void enterNeighbor_nexus_af_route_map_substanza(@NotNull CiscoGrammar.Neighbor_nexus_af_route_map_substanzaContext ctx) {
      stat.AddIntraRef(Identifiers.BGP_T, currentBgp, Identifiers.ROUTEMAP_T, ctx.map.getText()); 
   }
   @Override public void enterNeighbor_route_map_tail(@NotNull CiscoGrammar.Neighbor_route_map_tailContext ctx) {
      stat.AddIntraRef(Identifiers.BGP_T, currentBgp, Identifiers.ROUTEMAP_T, ctx.map.getText()); 
   }
   @Override public void enterNetwork_substanza(@NotNull CiscoGrammar.Network_substanzaContext ctx) {
      if(ctx.map!= null)
         stat.AddIntraRef(Identifiers.BGP_T, currentBgp, Identifiers.ROUTEMAP_T, ctx.map.getText());  
   }
   @Override public void enterRedistribute_substanza(@NotNull CiscoGrammar.Redistribute_substanzaContext ctx) { 
      if(ctx.map!= null)
         stat.AddIntraRef(Identifiers.BGP_T, currentBgp, Identifiers.ROUTEMAP_T, ctx.map.getText());        
   }
   @Override public void enterTemplate_af_route_map_subsubstanza(@NotNull CiscoGrammar.Template_af_route_map_subsubstanzaContext ctx) {
      stat.AddIntraRef(Identifiers.BGP_T, currentBgp, Identifiers.ROUTEMAP_T, ctx.map.getText()); 
   }
   @Override public void enterAf_neighbor_distribute_list_tail(@NotNull CiscoGrammar.Af_neighbor_distribute_list_tailContext ctx) {
      stat.AddIntraRef(Identifiers.BGP_T, currentBgp, Identifiers.ACCESS_T, ctx.num.getText()); 
   }
   @Override public void enterAf_neighbor_filter_list_tail(@NotNull CiscoGrammar.Af_neighbor_filter_list_tailContext ctx) {
      stat.AddIntraRef(Identifiers.BGP_T, currentBgp, Identifiers.ACCESS_T, ctx.list.getText()); 
      }
   @Override public void enterAf_neighbor_prefix_list_tail(@NotNull CiscoGrammar.Af_neighbor_prefix_list_tailContext ctx) {
      stat.AddIntraRef(Identifiers.BGP_T, currentBgp, Identifiers.PREFIX_T, ctx.list.getText());       
   }
   @Override public void enterNeighbor_filter_list_tail(@NotNull CiscoGrammar.Neighbor_filter_list_tailContext ctx) {
      stat.AddIntraRef(Identifiers.BGP_T, currentBgp, Identifiers.ACCESS_T, ctx.list.getText());       
   }
   @Override public void enterNeighbor_nexus_af_filter_list_substanza(@NotNull CiscoGrammar.Neighbor_nexus_af_filter_list_substanzaContext ctx) { 
      stat.AddIntraRef(Identifiers.BGP_T, currentBgp, Identifiers.ACCESS_T, ctx.list.getText()); 
   }
   @Override public void enterNeighbor_nexus_af_prefix_list_substanza(@NotNull CiscoGrammar.Neighbor_nexus_af_prefix_list_substanzaContext ctx) {
      stat.AddIntraRef(Identifiers.BGP_T, currentBgp, Identifiers.PREFIX_T, ctx.list.getText());       
   }
   @Override public void enterNeighbor_prefix_list_tail(@NotNull CiscoGrammar.Neighbor_prefix_list_tailContext ctx) {
      stat.AddIntraRef(Identifiers.BGP_T, currentBgp, Identifiers.PREFIX_T, ctx.list.getText());       
   }
   @Override public void enterTemplate_af_prefix_list_substanza(@NotNull CiscoGrammar.Template_af_prefix_list_substanzaContext ctx) {
      stat.AddIntraRef(Identifiers.BGP_T, currentBgp, Identifiers.PREFIX_T, ctx.list.getText());       
   }
         // ospf to xxx
   @Override public void enterDefault_information_ro_stanza(@NotNull CiscoGrammar.Default_information_ro_stanzaContext ctx) {
      if(ctx.map != null)
         stat.AddIntraRef(Identifiers.OSPF_T, currentOspf, Identifiers.ROUTEMAP_T, ctx.map.getText());   
   }
   @Override public void enterDistribute_list_stanza(@NotNull CiscoGrammar.Distribute_list_stanzaContext ctx) {
      stat.AddIntraRef(Identifiers.OSPF_T, currentOspf, Identifiers.ROUTEMAP_T, ctx.name.getText());   
   }
   @Override public void enterRedistribute_bgp_ro_stanza(@NotNull CiscoGrammar.Redistribute_bgp_ro_stanzaContext ctx) {
      if(ctx.map != null)
         stat.AddIntraRef(Identifiers.OSPF_T, currentOspf, Identifiers.ROUTEMAP_T, ctx.map.getText());         
   }
   @Override public void enterRedistribute_connected_ro_stanza(@NotNull CiscoGrammar.Redistribute_connected_ro_stanzaContext ctx) {
      if(ctx.map != null)
         stat.AddIntraRef(Identifiers.OSPF_T, currentOspf, Identifiers.ROUTEMAP_T, ctx.map.getText());         
   }
   @Override public void enterRedistribute_static_ro_stanza(@NotNull CiscoGrammar.Redistribute_static_ro_stanzaContext ctx) {
      if(ctx.map != null)
         stat.AddIntraRef(Identifiers.OSPF_T, currentOspf, Identifiers.ROUTEMAP_T, ctx.map.getText());  
   }
         // routemap to xxx
   @Override public void enterMatch_as_path_access_list_rm_stanza(@NotNull CiscoGrammar.Match_as_path_access_list_rm_stanzaContext ctx) {
      stat.AddIntraRef(Identifiers.ROUTEMAP_T, currentRoutemap, Identifiers.ACCESS_T, ctx.name.getText());
   }
   @Override public void enterMatch_community_list_rm_stanza(@NotNull CiscoGrammar.Match_community_list_rm_stanzaContext ctx) {
      stat.AddIntraRef(Identifiers.ROUTEMAP_T, currentRoutemap, Identifiers.COMMUNITY_T, ctx.name.getText());      
   }
   @Override public void enterMatch_extcommunity_rm_stanza(@NotNull CiscoGrammar.Match_extcommunity_rm_stanzaContext ctx) {
      stat.AddIntraRef(Identifiers.ROUTEMAP_T, currentRoutemap, Identifiers.COMMUNITY_T, ctx.name.getText());            
   }
   @Override public void enterMatch_ip_access_list_rm_stanza(@NotNull CiscoGrammar.Match_ip_access_list_rm_stanzaContext ctx) {
      stat.AddIntraRef(Identifiers.ROUTEMAP_T, currentRoutemap, Identifiers.ACCESS_T, ctx.name.getText());   
   }
   @Override public void enterMatch_ip_prefix_list_rm_stanza(@NotNull CiscoGrammar.Match_ip_prefix_list_rm_stanzaContext ctx) {
      stat.AddIntraRef(Identifiers.ROUTEMAP_T, currentRoutemap, Identifiers.PREFIX_T, ctx.name.getText());         
   }
   @Override public void enterMatch_ipv6_prefix_list_stanza(@NotNull CiscoGrammar.Match_ipv6_prefix_list_stanzaContext ctx) { 
      stat.AddIntraRef(Identifiers.ROUTEMAP_T, currentRoutemap, Identifiers.PREFIX_T, ctx.name.getText());   
   }
   @Override public void enterSet_comm_list_delete_rm_stanza(@NotNull CiscoGrammar.Set_comm_list_delete_rm_stanzaContext ctx) { 
      stat.AddIntraRef(Identifiers.ROUTEMAP_T, currentRoutemap, Identifiers.COMMUNITY_T, ctx.name.getText());
   }
   @Override public void enterSet_community_additive_rm_stanza(@NotNull CiscoGrammar.Set_community_additive_rm_stanzaContext ctx) {
      if(ctx.comm!= null){
         stat.AddIntraRef(Identifiers.ROUTEMAP_T, currentRoutemap, Identifiers.COMMUNITY_T, ctx.comm.getText());
      }
   }
   @Override public void enterSet_community_rm_stanza(@NotNull CiscoGrammar.Set_community_rm_stanzaContext ctx) {
      stat.AddIntraRef(Identifiers.ROUTEMAP_T, currentRoutemap, Identifiers.COMMUNITY_T, ctx.comm.getText());
   }
   @Override public void enterSet_extcommunity_stanza(@NotNull CiscoGrammar.Set_extcommunity_stanzaContext ctx) {
      stat.AddIntraRef(Identifiers.ROUTEMAP_T, currentRoutemap, Identifiers.COMMUNITY_T, ctx.comm.getText());
   }
   @Override public void enterSet_extcomm_list_rm_stanza(@NotNull CiscoGrammar.Set_extcomm_list_rm_stanzaContext ctx) {
      stat.AddIntraRef(Identifiers.ROUTEMAP_T, currentRoutemap, Identifiers.COMMUNITY_T, ctx.comm.getText());
   }
   @Override public void enterMatch_interface_rm_stanza(@NotNull CiscoGrammar.Match_interface_rm_stanzaContext ctx) {
      stat.AddIntraRef(Identifiers.ROUTEMAP_T, currentRoutemap, Identifiers.IFACE_T, ctx.name.getText());
   }
   
   
   
   // VLAN
   @Override public void enterVlan_stanza(@NotNull CiscoGrammar.Vlan_stanzaContext ctx) { 
      stat.DeclareVlan(ctx.v.getText());
   }
   @Override public void enterSwitchport_access_if_stanza(@NotNull CiscoGrammar.Switchport_access_if_stanzaContext ctx) {
      stat.IfaceVlan(currentIface, ctx.vlan.getText());
   }
   @Override public void enterSwitchport_trunk_allowed_if_stanza(@NotNull CiscoGrammar.Switchport_trunk_allowed_if_stanzaContext ctx) { 
      stat.IfaceVlan(currentIface, ctx.r.getText());
   }
   @Override public void enterSwitchport_trunk_native_if_stanza(@NotNull CiscoGrammar.Switchport_trunk_native_if_stanzaContext ctx) { 
      stat.IfaceVlan(currentIface, ctx.vlan.getText());
   }
   
   // L2
      // MSTP
   @Override public void enterMstp_stanza(@NotNull CiscoGrammar.Mstp_stanzaContext ctx) { 
      stat.DeclareL2Proto("MSTP");
   }
   @Override public void enterMstp_instance_substanza(@NotNull CiscoGrammar.Mstp_instance_substanzaContext ctx) { 
      stat.L2ProtoInst("MSTP", ctx.r.getText());
   }
      // UDLD
   @Override public void enterUdld_stanza(@NotNull CiscoGrammar.Udld_stanzaContext ctx) {
      stat.DeclareL2Proto("UDLD");
   }
   @Override public void enterUdld_enable_if_stanza_tail(@NotNull CiscoGrammar.Udld_enable_if_stanza_tailContext ctx) {
      stat.L2ProtoInst("UDLD", null);
   }
      // LACP
   @Override public void enterChannel_group_if_stanza(@NotNull CiscoGrammar.Channel_group_if_stanzaContext ctx) { 
      stat.L2ProtoInst("LACP", null);
   }
      // DHCP
   @Override public void enterIp_helper_address_if_stanza(@NotNull CiscoGrammar.Ip_helper_address_if_stanzaContext ctx) { 
      stat.L2ProtoInst("DHCP", null);
   }
      // HSRP
   @Override public void enterStandby_ip_tail(@NotNull CiscoGrammar.Standby_ip_tailContext ctx) { 
      stat.L2ProtoInst("HSRP", null);
   }
      // 802.1Q
   @Override public void enterSwitchport_trunk_encapsulation_if_stanza(@NotNull CiscoGrammar.Switchport_trunk_encapsulation_if_stanzaContext ctx) { 
      stat.L2ProtoInst("DOT1Q", null);
   }
   @Override public void enterEncapsulation_dot1q_if_stanza(@NotNull CiscoGrammar.Encapsulation_dot1q_if_stanzaContext ctx) { 
      stat.L2ProtoInst("DOT1Q", null);
   }
   
   // L3
      // OSPF
   @Override public void enterIp_address_if_stanza(@NotNull CiscoGrammar.Ip_address_if_stanzaContext ctx) {
      if(ctx.ip!=null){
         stat.IfaceIp(currentIface, ctx.ip.getText(), ctx.subnet.getText());
      }
      else{
         stat.IfaceIP(currentIface, ctx.prefix.getText());
      }
   }
   @Override public void enterIp_address_secondary_if_stanza(@NotNull CiscoGrammar.Ip_address_secondary_if_stanzaContext ctx) {
      if(ctx.ip!=null){
         stat.IfaceIp(currentIface, ctx.ip.getText(), ctx.subnet.getText());
      }
      else{
         stat.IfaceIP(currentIface, ctx.prefix.getText());
      }
   }
         // iface ospf is in intra-ref
   //@Override public void enterIp_ospf_router_if_stanza(@NotNull CiscoGrammar.Ip_ospf_router_if_stanzaContext ctx) { }
   @Override public void enterNetwork_ro_stanza(@NotNull CiscoGrammar.Network_ro_stanzaContext ctx) {
      String area;
      if(ctx.area_int!=null) area = ctx.area_int.getText();
      else area = ctx.area_ip.getText();
      if(ctx.ip!=null){
         stat.OspfNetworkArea(currentOspf, ctx.ip.getText(), ctx.wildcard.getText(), area);
      }
      else{
         stat.OspfNetworkArea(currentOspf, ctx.prefix.getText(), area);
      }
   }
   
      // BGP
         // Template
   String currentTemplate = null;
   @Override public void enterTemplate_peer_substanza(@NotNull CiscoGrammar.Template_peer_substanzaContext ctx) {
      currentTemplate = ctx.name.getText();
   }
   @Override public void exitTemplate_peer_substanza(@NotNull CiscoGrammar.Template_peer_substanzaContext ctx) { 
      currentTemplate = null;
   }
         // template as
   @Override public void enterTemplate_remote_as_substanza(@NotNull CiscoGrammar.Template_remote_as_substanzaContext ctx) {
      stat.BgpTemplateAs(currentTemplate, currentBgp, ctx.asNum.getText());
   }
         // group
   String currentGroup = null;
   @Override public void enterAf_neighbor_substanza(@NotNull CiscoGrammar.Af_neighbor_substanzaContext ctx) { 
      if(ctx.group!=null)
         currentGroup = ctx.group.getText();
      else if(ctx.ip!=null){
         currentNeighborAddr = ctx.ip.getText();
         currentNeighborMask = "255.255.255.255";
      }
   }

   @Override public void exitAf_neighbor_substanza(@NotNull CiscoGrammar.Af_neighbor_substanzaContext ctx) { 
      currentGroup = null;
      currentNeighborAddr = null;
      currentNeighborMask = null;
   }
   @Override public void enterNeighbor_standalone_substanza(@NotNull CiscoGrammar.Neighbor_standalone_substanzaContext ctx) { 
      if(ctx.peergroup!=null)
         currentGroup = ctx.peergroup.getText();
      else if(ctx.ip!=null){
         currentNeighborAddr = ctx.ip.getText();
         currentNeighborMask = "255.255.255.255";
      }
   }
   @Override public void exitNeighbor_standalone_substanza(@NotNull CiscoGrammar.Neighbor_standalone_substanzaContext ctx) { 
      currentGroup = null;
      currentNeighborAddr = null;
      currentNeighborMask = null;
   }
         // neighbor
   String currentNeighborAddr = null;
   String currentNeighborMask = null;
   //@Override public void enterAf_neighbor_substanza(@NotNull CiscoGrammar.Af_neighbor_substanzaContext ctx) { }
   @Override public void enterNeighbor_nexus_substanza(@NotNull CiscoGrammar.Neighbor_nexus_substanzaContext ctx) {
      if(ctx.ip!=null){
         currentNeighborAddr = ctx.ip.getText();
         currentNeighborMask = "255.255.255.255";         
      }
      else if(ctx.prefix!=null){
         String pair[] = Util.getIpMaskFromPrefix(ctx.prefix.getText());
         currentNeighborAddr = pair[0];
         currentNeighborMask = pair[1];
      }
      // as number
      if(ctx.asNum!=null){
         stat.BgpNeighborAs(currentBgp, currentNeighborAddr, currentNeighborMask, ctx.asNum.getText());
      }
   }
   @Override public void exitNeighbor_nexus_substanza(@NotNull CiscoGrammar.Neighbor_nexus_substanzaContext ctx) {
      currentNeighborAddr = null;
      currentNeighborMask = null;
   }
   //@Override public void enterNeighbor_standalone_substanza(@NotNull CiscoGrammar.Neighbor_standalone_substanzaContext ctx) { }
   @Override public void enterVrf_neighbor_nexus_stanza(@NotNull CiscoGrammar.Vrf_neighbor_nexus_stanzaContext ctx) {
      if(ctx.ip!=null){
         currentNeighborAddr = ctx.ip.getText();
         currentNeighborMask = "255.255.255.255";         
      }
   }
   @Override public void exitVrf_neighbor_nexus_stanza(@NotNull CiscoGrammar.Vrf_neighbor_nexus_stanzaContext ctx) {
      currentNeighborAddr = null;
      currentNeighborMask = null;
   }
      // neighbor as
   @Override public void enterNeighbor_nexus_remote_as_substanza(@NotNull CiscoGrammar.Neighbor_nexus_remote_as_substanzaContext ctx) { 
      stat.BgpNeighborAs(currentBgp, currentNeighborAddr, currentNeighborMask, ctx.asNum.getText());
   }
   //@Override public void enterNeighbor_nexus_substanza(@NotNull CiscoGrammar.Neighbor_nexus_substanzaContext ctx) { }
   @Override public void enterAf_neighbor_remote_as_tail(@NotNull CiscoGrammar.Af_neighbor_remote_as_tailContext ctx) {
      if(currentGroup != null){
         stat.BgpGroupAs(currentGroup, currentBgp, ctx.asNum.getText());
      }
      else if(currentNeighborAddr!=null){
         stat.BgpNeighborAs(currentBgp, currentNeighborAddr, currentNeighborMask, ctx.asNum.getText());
      }
   }
   @Override public void enterNeighbor_remote_as_tail(@NotNull CiscoGrammar.Neighbor_remote_as_tailContext ctx) {
      if(currentGroup != null){
         stat.BgpGroupAs(currentGroup, currentBgp, ctx.asNum.getText());
      }
      else if(currentNeighborAddr!=null){
         stat.BgpNeighborAs(currentBgp, currentNeighborAddr, currentNeighborMask, ctx.asNum.getText());
      }
   }
      // neighbor group
   @Override public void enterAf_neighbor_peer_group_tail(@NotNull CiscoGrammar.Af_neighbor_peer_group_tailContext ctx) {
      stat.BgpNeighborGroup(currentBgp, currentNeighborMask, currentNeighborMask, ctx.group.getText());
   }
   @Override public void enterNeighbor_peer_group_tail(@NotNull CiscoGrammar.Neighbor_peer_group_tailContext ctx) { 
      if(ctx.group!=null){
         stat.BgpNeighborGroup(currentBgp, currentNeighborMask, currentNeighborMask, ctx.group.getText());
      }
   }
      // a neighbor nexus may cancel its remote-as, not sure how to deal with this now
   @Override public void enterNeighbor_nexus_no_remote_as_substanza(@NotNull CiscoGrammar.Neighbor_nexus_no_remote_as_substanzaContext ctx) {
   }
      // neighbor template
   @Override public void enterNeighbor_inherit_tail(@NotNull CiscoGrammar.Neighbor_inherit_tailContext ctx) {
      stat.BgpNeighborTemplate(currentBgp, currentNeighborAddr, currentNeighborMask, ctx.template.getText());
   }
   @Override public void enterNeighbor_nexus_inherit_substanza(@NotNull CiscoGrammar.Neighbor_nexus_inherit_substanzaContext ctx) {
      stat.BgpNeighborTemplate(currentBgp, currentNeighborAddr, currentNeighborMask, ctx.peer.getText());
   }
   @Override public void enterVrf_neighbor_inherit_substanza(@NotNull CiscoGrammar.Vrf_neighbor_inherit_substanzaContext ctx) {
      stat.BgpNeighborTemplate(currentBgp, currentNeighborAddr, currentNeighborMask, ctx.peer.getText());
      
   }
   @Override public void enterTemplate_inherit_substanza(@NotNull CiscoGrammar.Template_inherit_substanzaContext ctx) {
      stat.BgpTemplateInheritTemplate(currentBgp, currentTemplate, ctx.inherit.getText());
   }
   
}
