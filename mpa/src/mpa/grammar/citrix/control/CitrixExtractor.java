package mpa.grammar.citrix.control;

import java.util.HashSet;
import java.util.Set;

import org.antlr.v4.runtime.misc.NotNull;

import mpa.grammar.MpaExtractor;
import mpa.grammar.citrix.CitrixParser;
import mpa.grammar.citrix.CitrixParserBaseListener;
import mpa.representation.Identifiers;
import mpa.representation.Statistics;

public class CitrixExtractor extends CitrixParserBaseListener implements MpaExtractor{
   Statistics stat = new Statistics();
   @Override
   public Statistics getVendorConfiguration() {
      return stat;
   }
   
   // reference
      // entity: service, vserver, serviceGroup, monitor, cipher, policy
         // add_service, add_vserver, add_servicegroup, add_lb_monitor, add_ssl_cipher, add_policy
   @Override public void enterAdd_service(@NotNull CitrixParser.Add_serviceContext ctx) { 
      stat.AddRefEntity(Identifiers.SERVICE_T, ctx.name.getText());
   }
   @Override public void enterAdd_vserver(@NotNull CitrixParser.Add_vserverContext ctx) { 
      stat.AddRefEntity(Identifiers.VSERVER_T, ctx.name.getText());
   }
   @Override public void enterAdd_servicegroup(@NotNull CitrixParser.Add_servicegroupContext ctx) { 
      stat.AddRefEntity(Identifiers.SERVICEGROUP_T, ctx.name.getText());
   }
   @Override public void enterAdd_lb_monitor(@NotNull CitrixParser.Add_lb_monitorContext ctx) {
      stat.AddRefEntity(Identifiers.MONITOR_T, ctx.name.getText());
   }
   @Override public void enterAdd_ssl_cipher(@NotNull CitrixParser.Add_ssl_cipherContext ctx) {
      stat.AddRefEntity(Identifiers.CIPHER_T, ctx.name.getText());
   }
   @Override public void enterAdd_policy(@NotNull CitrixParser.Add_policyContext ctx) {
      stat.AddRefEntity(Identifiers.GROUP_T, ctx.name.getText());
   }
   
      // ref: x to vserver, x to serviceGroup, x to monitor, x to <cipher, vserver> , x to policy
         // bind_vserver, bind_servicegroup, bind_lb_monitor, bind_ssl_cipher, generic_parameters
   @Override public void enterBind_vserver(@NotNull CitrixParser.Bind_vserverContext ctx) { 
      stat.AddIntraRef("default", "default", Identifiers.VSERVER_T, ctx.name.getText());
   }
   @Override public void enterBind_servicegroup(@NotNull CitrixParser.Bind_servicegroupContext ctx) {
      stat.AddIntraRef("default", "default", Identifiers.SERVICEGROUP_T, ctx.name.getText());      
   }
   @Override public void enterBind_lb_monitor(@NotNull CitrixParser.Bind_lb_monitorContext ctx) { 
      stat.AddIntraRef("default", "default", Identifiers.MONITOR_T, ctx.m.getText());
      
   }
   @Override public void enterBind_ssl_cipher(@NotNull CitrixParser.Bind_ssl_cipherContext ctx) {
      stat.AddIntraRef("default", "default", Identifiers.CIPHER_T, ctx.c.getText());
      stat.AddIntraRef("default", "default", Identifiers.VSERVER_T, ctx.vs.getText());      
   }
   @Override public void enterGeneric_parameters(@NotNull CitrixParser.Generic_parametersContext ctx) { 
      // the from_type, from_name needs refined
      if(ctx.policy!=null)
         stat.AddIntraRef("default", "default", Identifiers.POLICY_T, ctx.policy.getText());      
   }
   
   // VLAN: add_vlan, bind_vlan
   Set<String> vlans = new HashSet<String>();
   @Override public void enterAdd_vlan(@NotNull CitrixParser.Add_vlanContext ctx) {
      String v = ctx.id.getText();
      if(! vlans.contains(v)){
         vlans.add(v);
         stat.DeclareVlan(v);
      }
   }
   @Override public void enterBind_vlan(@NotNull CitrixParser.Bind_vlanContext ctx) {
      String v = ctx.id.getText();
      if(! vlans.contains(v)){
         vlans.add(v);
         stat.DeclareVlan(v);
      }
   }
   
   // LACP: interface_parameters
   @Override public void enterInterface_parameters(@NotNull CitrixParser.Interface_parametersContext ctx) {
      if(ctx.a!=null){
         stat.L2ProtoInst("LACP", null);
      }
   }
   
}
