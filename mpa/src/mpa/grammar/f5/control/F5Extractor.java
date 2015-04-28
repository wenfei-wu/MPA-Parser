package mpa.grammar.f5.control;

import org.antlr.v4.runtime.misc.NotNull;

import mpa.grammar.MpaExtractor;
import mpa.grammar.f5.F5Parser;
import mpa.grammar.f5.F5ParserBaseListener;
import mpa.representation.Identifiers;
import mpa.representation.Statistics;

public class F5Extractor extends F5ParserBaseListener implements MpaExtractor {
   Statistics stat = new Statistics();
   @Override
   public Statistics getVendorConfiguration() {
      // TODO Auto-generated method stub
      return stat;
   }

   // References
      // entities
   String currentStanza = null;
      // virtual:   virtual_stanza
   @Override public void enterVirtual_stanza(@NotNull F5Parser.Virtual_stanzaContext ctx) {
      if(ctx.name!=null){
         currentStanza = ctx.name.getText();
         stat.AddRefEntity(Identifiers.VIRTUAL_T, ctx.name.getText());
      }
   }
   @Override public void exitVirtual_stanza(@NotNull F5Parser.Virtual_stanzaContext ctx) { 
      currentStanza = null;
   }
   
      // pool: pool_stanza
   @Override public void enterPool_stanza(@NotNull F5Parser.Pool_stanzaContext ctx) { 
      if(ctx.name!=null){
         currentStanza = ctx.name.getText();
         stat.AddRefEntity(Identifiers.POOL_T, ctx.name.getText());
      }
   }
   @Override public void exitPool_stanza(@NotNull F5Parser.Pool_stanzaContext ctx) { 
      currentStanza = null;
   }
   
      // profile: profile_stanza
   @Override public void enterProfile_stanza(@NotNull F5Parser.Profile_stanzaContext ctx) { }
   
      // ltm: ltm_stanza
   @Override public void enterLtm_stanza(@NotNull F5Parser.Ltm_stanzaContext ctx) {
      currentStanza = "default";
      stat.AddRefEntity(Identifiers.LTM_T, "default");
   }
   @Override public void exitLtm_stanza(@NotNull F5Parser.Ltm_stanzaContext ctx) { 
      currentStanza = null;
   }
   
      // monitor: monitor_stanza
   @Override public void enterMonitor_stanza(@NotNull F5Parser.Monitor_stanzaContext ctx) {
      if(ctx.name!=null){
         currentStanza = ctx.name.getText();
         stat.AddRefEntity(Identifiers.MONITOR_T, ctx.name.getText());
      }
   }
   
      // intra-ref
      // virtual to X: virtual_profile_substanza, vp_profiles_substanza, virtual_pool_substanza
   @Override public void enterVirtual_profile_substanza(@NotNull F5Parser.Virtual_profile_substanzaContext ctx) {
      if(ctx.name!=null)
         stat.AddIntraRef(Identifiers.VIRTUAL_T, currentStanza, Identifiers.PROFILE_T, ctx.name.getText());
   }
   @Override public void enterVp_profiles_substanza(@NotNull F5Parser.Vp_profiles_substanzaContext ctx) { 
      if(ctx.name!=null)
         stat.AddIntraRef(Identifiers.VIRTUAL_T, currentStanza, Identifiers.PROFILE_T, ctx.name.getText());
   }
   @Override public void enterVirtual_pool_substanza(@NotNull F5Parser.Virtual_pool_substanzaContext ctx) { 
      if(ctx.name!=null)
         stat.AddIntraRef(Identifiers.VIRTUAL_T, currentStanza, Identifiers.POOL_T, ctx.name.getText());
   }
   
      // pool to X: pm_monitor
   @Override public void enterPm_monitor(@NotNull F5Parser.Pm_monitorContext ctx) { 
      if(ctx.name!=null)
         stat.AddIntraRef(Identifiers.POOL_T, currentStanza, Identifiers.MONITOR_T, ctx.name.getText());
   }
   
      // ltm to X: ltm_monitor_tail, ltm_pool_tail, ltm_profile_tail, ltm_virtual_tail
   
   @Override public void enterLtm_monitor_tail(@NotNull F5Parser.Ltm_monitor_tailContext ctx) {
      if(ctx.name!=null)
         stat.AddIntraRef(Identifiers.LTM_T, currentStanza, Identifiers.MONITOR_T, ctx.name.getText());
   }
   @Override public void enterLtm_pool_tail(@NotNull F5Parser.Ltm_pool_tailContext ctx) { 
      if(ctx.name!=null)
         stat.AddIntraRef(Identifiers.LTM_T, currentStanza, Identifiers.POOL_T, ctx.name.getText());
   }
   @Override public void enterLtm_profile_tail(@NotNull F5Parser.Ltm_profile_tailContext ctx) {
      if(ctx.name!=null)
         stat.AddIntraRef(Identifiers.LTM_T, currentStanza, Identifiers.PROFILE_T, ctx.name.getText());
   }
   @Override public void enterLtm_virtual_tail(@NotNull F5Parser.Ltm_virtual_tailContext ctx) {
      if(ctx.name!=null)
         stat.AddIntraRef(Identifiers.LTM_T, currentStanza, Identifiers.VIRTUAL_T, ctx.name.getText());
   }
   
}
