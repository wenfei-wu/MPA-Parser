package mpa.grammar.cisco.control;

import org.antlr.v4.runtime.misc.NotNull;

import mpa.grammar.MpaExtractor;
import mpa.grammar.cisco.CiscoGrammar;
import mpa.grammar.cisco.CiscoGrammarBaseListener;
import mpa.representation.Statistics;

public class CiscoExtractor extends CiscoGrammarBaseListener
         implements MpaExtractor {
   Statistics stat = new Statistics();
   @Override
   public Statistics getVendorConfiguration() {
      return stat;
   }

   // util, helper
   String currentIface;
   @Override public void enterInterface_stanza(@NotNull CiscoGrammar.Interface_stanzaContext ctx) { 
      currentIface = ctx.iname.getText();
   }   
   @Override public void exitInterface_stanza(@NotNull CiscoGrammar.Interface_stanzaContext ctx) { 
      currentIface = null;
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
   
  
}
