package mpa.grammar.flatjuniper.control;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.antlr.v4.runtime.misc.NotNull;

import mpa.grammar.MpaExtractor;
import mpa.grammar.flatjuniper.FlatJuniperParser;
import mpa.grammar.flatjuniper.FlatJuniperParserBaseListener;
import mpa.representation.Identifiers;
import mpa.representation.Statistics;
import mpa.util.Util;


public class FlatJuniperExtractor extends FlatJuniperParserBaseListener
         implements MpaExtractor {
	   Statistics stat = new Statistics();
	   @Override
	   public Statistics getVendorConfiguration() {
	      return stat;
	   }
	   
	   // type, name
	   Stack<String[]> currentStanza = new Stack<String[]>();
	   // helper
	      // group
	   boolean inGroup = false;
	   String currentGroup = null;
	   @Override public void enterS_groups_named(@NotNull FlatJuniperParser.S_groups_namedContext ctx) {
	      inGroup = true;
	      stat.AddRefEntity(Identifiers.GROUP_T, ctx.name.getText());
	      currentStanza.push(new String[]{Identifiers.GROUP_T, ctx.name.getText()});
	      currentGroup = ctx.name.getText();
	   }
	   @Override public void exitS_groups_named(@NotNull FlatJuniperParser.S_groups_namedContext ctx) { 
	      inGroup = false;
	      currentStanza.pop();
	      currentGroup = null;
	   }
	   @Override public void exitFlat_juniper_configuration(@NotNull FlatJuniperParser.Flat_juniper_configurationContext ctx) {
	      // finally process remaining things
	      // bgp now process all neighbors
         for(String neighbor: bgp_neighbors.keySet()){
            String local_as = null;
            String peer_as = null;
            Stack<String> stack = bgp_neighbors.get(neighbor);
            for(int i = stack.size(); i>=1; i--){
               String key = stack.subList(0, i).toString();
               if(local_as == null){
                  if(bgp_local_as.containsKey(key)){
                     local_as = bgp_local_as.get(key);
                  }
               }
               if(peer_as == null){
                  if(bgp_peer_as.containsKey(key)){
                     peer_as = bgp_peer_as.get(key);
                  }
               }
               if(bgp_type.containsKey(key)){
                  String type = bgp_type.get(key);
                  if(type.equals("internal")){
                     if(peer_as!=null){
                        local_as = peer_as;
                     }
                  }
               }
            }
            if(local_as==null) local_as = "NA";
            if(peer_as == null) peer_as = "NA";
            stat.BgpNeighborAs(local_as, neighbor, "255.255.255.255", peer_as);
         }
	   }
	   
	      // interface 
	   Set<String> ifaces = new HashSet<String>();
	   String currentIface = null;
	   String currentIface_wildcard = null;
      @Override public void enterS_interfaces(@NotNull FlatJuniperParser.S_interfacesContext ctx) { 
         if(!inGroup){
            if(ctx.name!= null){
               currentIface = ctx.name.getText();        
               //System.out.println(ctx.getText());
               stat.AddRefEntity(Identifiers.IFACE_T, ctx.name.getText());
               currentStanza.push(new String[]{Identifiers.IFACE_T, ctx.name.getText()});                           
            }
            else{
               currentStanza.push(new String[]{Identifiers.IFACE_T, "default"});
            }
         }
         else{
            if(ctx.wild!= null){
               currentIface_wildcard = ctx.wild.getText();
            }
         }
      }
      @Override public void exitS_interfaces(@NotNull FlatJuniperParser.S_interfacesContext ctx) { 
         currentIface = null;
         if(!inGroup){        
               currentStanza.pop(); 
               if(ctx.name!= null){           
                  // check LACP: (1) not disabled, (2) not checked
                  String iface_name = ctx.name.getText();
                  if(!iface_name.equals(currentDisabledIface) && !ifaces.contains(iface_name)) {
                     ifaces.add(ctx.name.getText()); 
                     // check LACP wildcard
                     for(String wildcard: default_wildcard_with_lacp){
                        if(Util.JuniperMatch(iface_name, wildcard)){
                           stat.L2ProtoInst("LACP", null);
                           break;
                        }
                     }  
                  }
               }
         }
         else{
            if(ctx.wild!=null){
               currentIface_wildcard = null;
            }
         }
      }
      String currentDisabledIface = null;
      @Override public void enterIt_disable(@NotNull FlatJuniperParser.It_disableContext ctx) {
         currentDisabledIface = currentIface;
      }
      
	   
	   // Intra-Ref
	      // Entity
	         // Group
      //@Override public void enterS_groups_named(@NotNull FlatJuniperParser.S_groups_namedContext ctx) { }
      //@Override public void exitS_groups_named(@NotNull FlatJuniperParser.S_groups_namedContext ctx) { }
	         // policy-option
	            // as-path, community, prefix-list, policy-statement
	   @Override public void enterPot_as_path(@NotNull FlatJuniperParser.Pot_as_pathContext ctx) {
	      if(!inGroup){
	         stat.AddRefEntity(Identifiers.AS_T, ctx.name.getText());
	         currentStanza.push(new String[]{Identifiers.AS_T, ctx.name.getText()});
	      }
	   }
	   @Override public void exitPot_as_path(@NotNull FlatJuniperParser.Pot_as_pathContext ctx) {
	      if(!inGroup)
            currentStanza.pop(); 
	   }
	   @Override public void enterPot_community(@NotNull FlatJuniperParser.Pot_communityContext ctx) {
         if(!inGroup){
            currentStanza.push(new String[]{Identifiers.COMMUNITY_T, ctx.name.getText()});
            stat.AddRefEntity(Identifiers.COMMUNITY_T, ctx.name.getText());
         }
	   }
	   @Override public void exitPot_community(@NotNull FlatJuniperParser.Pot_communityContext ctx) {
	      if(!inGroup) {
            currentStanza.pop(); 
	      }
	   }
	   @Override public void enterPot_prefix_list(@NotNull FlatJuniperParser.Pot_prefix_listContext ctx) {
         if(!inGroup){
            stat.AddRefEntity(Identifiers.PREFIX_T, ctx.name.getText());
            currentStanza.push(new String[]{Identifiers.PREFIX_T, ctx.name.getText()});
         }
	   }
	   @Override public void exitPot_prefix_list(@NotNull FlatJuniperParser.Pot_prefix_listContext ctx) {
	      if(!inGroup) {
            currentStanza.pop(); 
         }
	   }
	   @Override public void enterPot_policy_statement(@NotNull FlatJuniperParser.Pot_policy_statementContext ctx) {
	      if(!inGroup){
            stat.AddRefEntity(Identifiers.POLICY_T, ctx.name.getText());
            currentStanza.push(new String[]{Identifiers.POLICY_T, ctx.name.getText()});
	      }
	   }
	   @Override public void exitPot_policy_statement(@NotNull FlatJuniperParser.Pot_policy_statementContext ctx) {
	      if(!inGroup) {
            currentStanza.pop(); 
         }
	   }
	         // firewall
	            // filter, term
	   @Override public void enterFwt_filter(@NotNull FlatJuniperParser.Fwt_filterContext ctx) {
         if(!inGroup){
            stat.AddRefEntity(Identifiers.FILTER_T, ctx.name.getText()); 
            currentStanza.push(new String[]{Identifiers.FILTER_T, ctx.name.getText()});
         }
	   }
	   @Override public void exitFwt_filter(@NotNull FlatJuniperParser.Fwt_filterContext ctx) { 
	      if(!inGroup) {
            currentStanza.pop(); 
         }	      
	   }
	   @Override public void enterFwft_term(@NotNull FlatJuniperParser.Fwft_termContext ctx) {	
         if(!inGroup){
            stat.AddRefEntity(Identifiers.FILTER_T, ctx.name.getText());
            currentStanza.push(new String[]{Identifiers.FILTER_T, ctx.name.getText()});
         }
	   }
	   @Override public void exitFwft_term(@NotNull FlatJuniperParser.Fwft_termContext ctx) {
	      if(!inGroup) {
            currentStanza.pop(); 
         }     
	   }
	         // interface
	            // s_interfaces

      //@Override public void enterS_interfaces(@NotNull FlatJuniperParser.S_interfacesContext ctx) { }
      //@Override public void exitS_interfaces(@NotNull FlatJuniperParser.S_interfacesContext ctx) { }
	         // protocols
	            // bgp: local-as
	   @Override public void enterS_protocols_bgp(@NotNull FlatJuniperParser.S_protocols_bgpContext ctx) {
	      if(!inGroup){
	         stat.AddRefEntity(Identifiers.BGP_T, "default");
	         currentStanza.push(new String[]{Identifiers.BGP_T, "default"});
	         
	         // for bgp
	         bgp_stack.push("global");
	      }
	   }
	   @Override public void exitS_protocols_bgp(@NotNull FlatJuniperParser.S_protocols_bgpContext ctx) {
	      if(!inGroup) {
            currentStanza.pop(); 
            
            // for bgp
            bgp_stack.pop();            
         }	      
	   }
	   @Override public void enterBt_group(@NotNull FlatJuniperParser.Bt_groupContext ctx) {
         if(!inGroup){
            stat.AddRefEntity(Identifiers.BGP_T, ctx.name.getText());
            currentStanza.push(new String[]{Identifiers.BGP_T, ctx.name.getText()});
            
            // for bgp
            bgp_stack.push(ctx.name.getText());
         }	      
	   }
	   @Override public void exitBt_group(@NotNull FlatJuniperParser.Bt_groupContext ctx) {
	      if(!inGroup) {
            currentStanza.pop(); 
            
            // for bgp
            bgp_stack.pop();
         }	      
	   }
	            // ospf
	   @Override public void enterS_protocols_ospf3(@NotNull FlatJuniperParser.S_protocols_ospf3Context ctx) {
	      if(!inGroup){
            stat.AddRefEntity(Identifiers.OSPF_T, "default");
            currentStanza.push(new String[]{Identifiers.BGP_T, "default"});
         }
	   }
	   @Override public void exitS_protocols_ospf3(@NotNull FlatJuniperParser.S_protocols_ospf3Context ctx) { 
	      if(!inGroup) {
            currentStanza.pop(); 
         }
	   }
	   @Override public void enterS_protocols_ospf(@NotNull FlatJuniperParser.S_protocols_ospfContext ctx) { 
	      if(!inGroup){
            stat.AddRefEntity(Identifiers.OSPF_T, "default");
            currentStanza.push(new String[]{Identifiers.BGP_T, "default"});
         }
	   }
	   @Override public void exitS_protocols_ospf(@NotNull FlatJuniperParser.S_protocols_ospfContext ctx) { 
	      if(!inGroup) {
            currentStanza.pop(); 
         }
	   }
	   
	      // OSPF
	   String currentArea = null;
	   String currentAreaWildCard = null;
	   @Override public void enterOt_area(@NotNull FlatJuniperParser.Ot_areaContext ctx) {
	      if(!inGroup){
            stat.AddRefEntity(Identifiers.BGP_T, ctx.area.getText());	   
            currentStanza.push(new String[]{Identifiers.BGP_T, ctx.area.getText()}); 
            currentArea = ctx.area.getText();
	      } 
	      else{
	         currentAreaWildCard = ctx.wild.getText();
	      }
	   }
	   @Override public void exitOt_area(@NotNull FlatJuniperParser.Ot_areaContext ctx) {
	      if(!inGroup) {
            currentStanza.pop(); 
            currentArea = null;
         }	
         else{
            currentAreaWildCard = null;
         }      
	   }
	         // routing-instance
	   @Override public void enterS_routing_instances(@NotNull FlatJuniperParser.S_routing_instancesContext ctx) {
	      if(!inGroup){
	         stat.AddRefEntity(Identifiers.R_INST_T, "default");
	         currentStanza.push(new String[]{Identifiers.R_INST_T, "default"});
	      }
	   }
	   @Override public void exitS_routing_instances(@NotNull FlatJuniperParser.S_routing_instancesContext ctx) {
	      if(!inGroup) {
            currentStanza.pop(); 
         }   
	   }
      
	   @Override public void enterRit_named_routing_instance(@NotNull FlatJuniperParser.Rit_named_routing_instanceContext ctx) { 
	      if(!inGroup){
            stat.AddRefEntity(Identifiers.R_INST_T, ctx.name.getText());
            currentStanza.push(new String[]{Identifiers.R_INST_T, ctx.name.getText()});
         }
	   }
	   @Override public void exitRit_named_routing_instance(@NotNull FlatJuniperParser.Rit_named_routing_instanceContext ctx) {
	      if(!inGroup) {
            currentStanza.pop(); 
         }   
	   }
      
	   // routing-options
	   @Override public void enterS_routing_options(@NotNull FlatJuniperParser.S_routing_optionsContext ctx) {
         if(!inGroup){
            stat.AddRefEntity(Identifiers.R_OPT_T, "default");
            currentStanza.push(new String[]{Identifiers.R_OPT_T, "default"}); 
         }
	   }
	   @Override public void exitS_routing_options(@NotNull FlatJuniperParser.S_routing_optionsContext ctx) {
	      if(!inGroup) {
            currentStanza.pop(); 
         }  
	   }
      @Override public void enterRot_rib_groups(@NotNull FlatJuniperParser.Rot_rib_groupsContext ctx) {
         if(!inGroup){
            stat.AddRefEntity(Identifiers.GROUP_T, ctx.name.getText());
            currentStanza.push(new String[]{Identifiers.GROUP_T, ctx.name.getText()}); 
         }
      }

      @Override public void exitRot_rib_groups(@NotNull FlatJuniperParser.Rot_rib_groupsContext ctx) {
         if(!inGroup) {
            currentStanza.pop(); 
         } 
      }
      @Override public void enterRst_rib_group(@NotNull FlatJuniperParser.Rst_rib_groupContext ctx) {
         if(!inGroup){
            stat.AddRefEntity(Identifiers.GROUP_T, ctx.name.getText()); 
            currentStanza.push(new String[]{Identifiers.GROUP_T, ctx.name.getText()}); 
         }
      }

      @Override public void exitRst_rib_group(@NotNull FlatJuniperParser.Rst_rib_groupContext ctx) { 
         if(!inGroup) {
            currentStanza.pop(); 
         } 
      }
      
	      // ref
	         // xxx to group
      @Override public void enterS_apply_groups(@NotNull FlatJuniperParser.S_apply_groupsContext ctx) {
         String []from;
         if(currentStanza.empty())
            from = new String[]{"N/A", "N/A"};
         else
            from = currentStanza.peek();
         stat.AddIntraRef(from[0], from[1], Identifiers.GROUP_T, ctx.name.getText());
         
         if(from[0].equals(Identifiers.IFACE_T)){
            String iface_name = from[1];
            String group_name = ctx.name.getText();
            if(iface_name.equals("default")){
               // compute default lacp iface
               if(group_iface_wildcard_with_lacp.containsKey(group_name)){
                  Set<String> wildcards = group_iface_wildcard_with_lacp.get(group_name);
                  for(String wildcard : wildcards)
                     default_wildcard_with_lacp.add(wildcard);
               }
            }
         }
         else if(currentIface!=null){
            String iface_name = currentIface;
            String group_name = ctx.name.getText();
            if(group_iface_wildcard_with_lacp.containsKey(group_name)){
               Set<String> wildcards = group_iface_wildcard_with_lacp.get(group_name);
               for(String wildcard: wildcards){
                  if(Util.JuniperMatch(iface_name, wildcard)){
                     stat.L2ProtoInst("LACP", null);
                  }
               }
            }
         }
         
      }
	         // policy-options: apply groups, tt_apply_groups, fromt_as_path, fromt_community, fromt_interface
            // fromt_policy, fromt_prefix_list,
            //  tht_community_add, tht_community_delete,tht_community_set
      //@Override public void enterS_apply_groups(@NotNull FlatJuniperParser.S_apply_groupsContext ctx) { }
      @Override public void enterFromt_as_path(@NotNull FlatJuniperParser.Fromt_as_pathContext ctx) {
         if(!inGroup){
            String []from = currentStanza.peek();
            stat.AddIntraRef(from[0], from[1], Identifiers.AS_T, ctx.name.getText() );            
         }
      }
      @Override public void enterFromt_community(@NotNull FlatJuniperParser.Fromt_communityContext ctx) {
         if(!inGroup){
            String []from = currentStanza.peek();
            stat.AddIntraRef(from[0], from[1], Identifiers.COMMUNITY_T, ctx.name.getText());            
         }
         
      }
      @Override public void enterFromt_interface(@NotNull FlatJuniperParser.Fromt_interfaceContext ctx) {
         if(!inGroup){
            String []from = currentStanza.peek();
            stat.AddIntraRef(from[0], from[1], Identifiers.IFACE_T, ctx.id.getText()); 
         }
         
      }
      @Override public void enterFromt_policy(@NotNull FlatJuniperParser.Fromt_policyContext ctx) {
         if(!inGroup){
            String []from = currentStanza.peek();
            stat.AddIntraRef(from[0], from[1],  Identifiers.POLICY_T, ctx.name.getText());            
         }
         
      }
      @Override public void enterFromt_prefix_list(@NotNull FlatJuniperParser.Fromt_prefix_listContext ctx) {
         if(!inGroup){
            String []from = currentStanza.peek();
            stat.AddIntraRef(from[0], from[1], Identifiers.PREFIX_T, ctx.name.getText() );            
         }
         
      }
      @Override public void enterTht_community_add(@NotNull FlatJuniperParser.Tht_community_addContext ctx) {
         if(!inGroup){
            String []from = currentStanza.peek();
            stat.AddIntraRef(from[0], from[1], Identifiers.COMMUNITY_T, ctx.name.getText());            
         }
         
      }
      @Override public void enterTht_community_delete(@NotNull FlatJuniperParser.Tht_community_deleteContext ctx) {

         if(!inGroup){
            String []from = currentStanza.peek();
            stat.AddIntraRef(from[0], from[1], Identifiers.COMMUNITY_T, ctx.name.getText());            
         }
      }
      @Override public void enterTht_community_set(@NotNull FlatJuniperParser.Tht_community_setContext ctx) {

         if(!inGroup){
            String []from = currentStanza.peek();
            stat.AddIntraRef(from[0], from[1], Identifiers.COMMUNITY_T, ctx.name.getText());            
         }
      }
            // firewall: fwfromt_destination_prefix_list, fwfromt_prefix_list, fwfromt_source_prefix_list
      @Override public void enterFwfromt_destination_prefix_list(@NotNull FlatJuniperParser.Fwfromt_destination_prefix_listContext ctx) {
         if(!inGroup){
            String []from = currentStanza.peek();
            stat.AddIntraRef(from[0], from[1], Identifiers.PREFIX_T, ctx.name.getText());            
         }
      }
      @Override public void enterFwfromt_prefix_list(@NotNull FlatJuniperParser.Fwfromt_prefix_listContext ctx) {
         if(!inGroup){
            String []from = currentStanza.peek();
            stat.AddIntraRef(from[0], from[1], Identifiers.PREFIX_T, ctx.name.getText());            
         }
      }
      @Override public void enterFwfromt_source_prefix_list(@NotNull FlatJuniperParser.Fwfromt_source_prefix_listContext ctx) { 
         if(!inGroup){
            String []from = currentStanza.peek();
            stat.AddIntraRef(from[0], from[1], Identifiers.PREFIX_T, ctx.name.getText());            
         }
      }
            // interface: it_apply_groups, it_apply_groups_except, ifamt_filter
      @Override public void enterIfamt_filter(@NotNull FlatJuniperParser.Ifamt_filterContext ctx) { 
         if(!inGroup){
            String []from = currentStanza.peek();
            stat.AddIntraRef(from[0], from[1], Identifiers.FILTER_T, ctx.name.name.getText());            
         }
      }
            // protocols
               // bgp: bt_export, bt_import
      @Override public void enterPolicy_expression(@NotNull FlatJuniperParser.Policy_expressionContext ctx) {
         if(!inGroup){
            if(ctx.name!=null){
               String []from = currentStanza.peek();
               stat.AddIntraRef(from[0], from[1], Identifiers.POLICY_T, ctx.name.getText());  
            }
         }
      }
               // ospf: ot_apply_groups, ot_export, ot_export, at_apply_groups, ait_apply_groups_except
      @Override public void enterOt_export(@NotNull FlatJuniperParser.Ot_exportContext ctx) {
         if(!inGroup){
            //System.out.println(ctx.getText());
            String []from = currentStanza.peek();
            stat.AddIntraRef(from[0], from[1], Identifiers.POLICY_T, ctx.name.getText());            
         }
      }
      @Override public void enterOt_import(@NotNull FlatJuniperParser.Ot_importContext ctx) {
         if(!inGroup){
            String []from = currentStanza.peek();
            stat.AddIntraRef(from[0], from[1], Identifiers.POLICY_T, ctx.name.getText());            
         }
      }
            // routing-instance: rot_aggregate, gt_policy, 
            // rit_interface, rit_vrf_export, rit_vrf_import
      @Override public void enterRot_aggregate(@NotNull FlatJuniperParser.Rot_aggregateContext ctx) { 
         if(!inGroup){
            if(ctx.community!=null){
               String []from = currentStanza.peek();
               stat.AddIntraRef(from[0], from[1], Identifiers.COMMUNITY_T, ctx.community.getText());      
            }
         }
      }
      @Override public void enterGt_policy(@NotNull FlatJuniperParser.Gt_policyContext ctx) {
         if(!inGroup){
            String []from = currentStanza.peek();
            stat.AddIntraRef(from[0], from[1], Identifiers.POLICY_T, ctx.policy.getText());            
         }
      }
      @Override public void enterRit_interface(@NotNull FlatJuniperParser.Rit_interfaceContext ctx) {
         if(!inGroup){
            String []from = currentStanza.peek();
            stat.AddIntraRef(from[0], from[1], Identifiers.IFACE_T, ctx.id.getText());            
         }
      }
      @Override public void enterRit_vrf_export(@NotNull FlatJuniperParser.Rit_vrf_exportContext ctx) {
         if(!inGroup){
            String []from = currentStanza.peek();
            stat.AddIntraRef(from[0], from[1], Identifiers.POLICY_T, ctx.name.getText());            
         }
      }
      @Override public void enterRit_vrf_import(@NotNull FlatJuniperParser.Rit_vrf_importContext ctx) { 
         if(!inGroup){
            String []from = currentStanza.peek();
            stat.AddIntraRef(from[0], from[1], Identifiers.POLICY_T, ctx.name.getText());            
         }
      }
      
      
	   // VLAN: it_vlan_id, brt_vlan_id_list, need current interface: s_interfaces
      //@Override public void enterS_interfaces(@NotNull FlatJuniperParser.S_interfacesContext ctx) { }
      //@Override public void exitS_interfaces(@NotNull FlatJuniperParser.S_interfacesContext ctx) { }
      @Override public void enterIt_vlan_id(@NotNull FlatJuniperParser.It_vlan_idContext ctx) {
         if(!inGroup){
            stat.IfaceVlan(currentIface, ctx.id.getText());
            stat.L2ProtoInst("DOT1Q", null);
         }
         else{
            System.out.println("Juniper Warning: find vlan used in group");
            stat.SetWarning();
         }
      }
      @Override public void enterBrt_vlan_id_list(@NotNull FlatJuniperParser.Brt_vlan_id_listContext ctx) { 
         if(!inGroup){
            stat.IfaceVlan(currentIface, ctx.id.getText());
            stat.L2ProtoInst("DOT1Q", null);
         }
         else{
            System.out.println("Juniper Warning: find vlan used in group");
            stat.SetWarning();
         }
      }      
	   
	   // L2
         // mstp: none
         // lacp: it_agg_lacp
      Map<String, Set<String>> group_iface_wildcard_with_lacp = new HashMap<String, Set<String>>();
      HashSet<String> default_wildcard_with_lacp = new HashSet<String>();
      @Override public void enterIt_agg_lacp(@NotNull FlatJuniperParser.It_agg_lacpContext ctx) {
         if(!inGroup){
            if(ctx.a!=null){
               stat.L2ProtoInst("LACP", null);
               stat.L2NumIface(ifaces.size());
            }
         }
         else{
            if(group_iface_wildcard_with_lacp.containsKey(currentGroup)){
               Set<String> wildcards = group_iface_wildcard_with_lacp.get(currentGroup);
               wildcards.add(currentIface_wildcard);
               group_iface_wildcard_with_lacp.put(currentGroup, wildcards);
            }
            else{
               Set<String> wildcards = new HashSet<String>();
               wildcards.add(currentIface_wildcard);
               group_iface_wildcard_with_lacp.put(currentGroup, wildcards);
            }
         }
      }
         // lldp
      @Override public void enterLldp_interface(@NotNull FlatJuniperParser.Lldp_interfaceContext ctx) { 
         stat.L2ProtoInst("LLDP", ctx.name.getText());
         stat.L2NumIface(ifaces.size());
      }
         // 802.1Q
      //@Override public void enterIt_vlan_id(@NotNull FlatJuniperParser.It_vlan_idContext ctx) {}
      //@Override public void enterBrt_vlan_id_list(@NotNull FlatJuniperParser.Brt_vlan_id_listContext ctx) { }
      
         // DHCP-relay
      Set<String> DHCP_relay_iface = new HashSet<String>();
      @Override public void enterFo_helper_bootp_interface_tail(@NotNull FlatJuniperParser.Fo_helper_bootp_interface_tailContext ctx) { 
         String iface = ctx.name.getText();
         //System.out.println("dhcp-relay: iface: "+iface);
         if(! DHCP_relay_iface.contains(iface)){
            DHCP_relay_iface.add(iface);
            stat.L2ProtoInst("DHCP", null);
         }
      }
      @Override public void enterFo_dhcp_relay_group_tail(@NotNull FlatJuniperParser.Fo_dhcp_relay_group_tailContext ctx) {
         String iface = ctx.name.getText();
         if(! DHCP_relay_iface.contains(iface)){
            DHCP_relay_iface.add(iface);
            stat.L2ProtoInst("DHCP", null);
         }         
      }
      
         // HSRP: VRRP
      @Override public void enterIvrrpt_virtual_address(@NotNull FlatJuniperParser.Ivrrpt_virtual_addressContext ctx) {
         stat.L2ProtoInst("VRRP", null);
      }
      
	   // L3
         // OSPF
            // iface ip
      @Override public void enterIfamt_address(@NotNull FlatJuniperParser.Ifamt_addressContext ctx) {
         String prefix = ctx.prefix.getText();
         if(!inGroup){
            stat.IfaceIP(currentIface, prefix);
         }
         else{
            System.out.println("Juniper Warning: interface address configured in group, not processed");
            stat.SetWarning();
         }
      }
            // area iface:  ot_area, at_interface
      //@Override public void enterOt_area(@NotNull FlatJuniperParser.Ot_areaContext ctx) { }
      Set<String> areaIface = new HashSet<String>();
      @Override public void enterAt_interface(@NotNull FlatJuniperParser.At_interfaceContext ctx) {
         if(!inGroup){
            String iface = ctx.id.getText();
            String key = currentArea+"/"+iface;
            if(!areaIface.contains(key)){
               stat.OspfAreaIface(currentArea, ctx.id.getText());
               areaIface.add(key);
            }
         }
         else{
            if(!currentAreaWildCard.equals("<*>") ||  !ctx.wild.getText().equals("<*>")){
               System.out.println("Juniper Warning: OSPF interface in Group, not processed");
               stat.SetWarning();
            }
         }
      }
      
         // BGP: hierarchy: s_protocols_bgp, bt_group, bt_neighbor
            // bt_local_as, bt_peer_as, bt_type
      Stack<String> bgp_stack = new Stack<String>();
      Map<String, String> bgp_local_as = new HashMap<String, String>();
      Map<String, String> bgp_peer_as = new HashMap<String, String>();
      Map<String, String> bgp_type = new HashMap<String, String>();
      Map<String, Stack<String>> bgp_neighbors = new HashMap< String, Stack<String> >();
      //@Override public void enterS_protocols_bgp(@NotNull FlatJuniperParser.S_protocols_bgpContext ctx) { }
      //@Override public void enterBt_group(@NotNull FlatJuniperParser.Bt_groupContext ctx) { }
      @SuppressWarnings("unchecked")
      @Override public void enterBt_neighbor(@NotNull FlatJuniperParser.Bt_neighborContext ctx) {
         bgp_stack.push(ctx.ip.getText());
         String neighbor = ctx.ip.getText();
         bgp_neighbors.put(neighbor, (Stack<String>)bgp_stack.clone());
         if(inGroup){
            System.out.println("Juniper Warning: bpg neighbor in group, not processed.");
            stat.SetWarning();
         }
      }
      @Override public void exitBt_neighbor(@NotNull FlatJuniperParser.Bt_neighborContext ctx) {
         bgp_stack.pop();
      }
      @Override public void enterBt_local_as(@NotNull FlatJuniperParser.Bt_local_asContext ctx) {
         String key = bgp_stack.toString();
         bgp_local_as.put(key, ctx.as.getText());
         if(inGroup){
            System.out.println("Juniper Warning: bpg neighbor in group, not processed.");
            stat.SetWarning();
         }
      }
      @Override public void enterBt_peer_as(@NotNull FlatJuniperParser.Bt_peer_asContext ctx) {
         String key = bgp_stack.toString();
         bgp_peer_as.put(key, ctx.as.getText());
      }
      @Override public void enterBt_type(@NotNull FlatJuniperParser.Bt_typeContext ctx) { 
         String key = bgp_stack.toString();
         bgp_type.put(key, ctx.t.getText());
         if(inGroup){
            System.out.println("Juniper Warning: bpg neighbor in group, not processed.");
            stat.SetWarning();
         }
      }
      
      
      
}
