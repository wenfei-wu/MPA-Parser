package mpa.grammar.quanta.control;

import org.antlr.v4.runtime.misc.NotNull;

import mpa.grammar.MpaExtractor;
import mpa.grammar.quanta.QuantaGrammar;
import mpa.grammar.quanta.QuantaGrammarBaseListener;
import mpa.representation.Statistics;

public class QuantaExtractor extends QuantaGrammarBaseListener
         implements MpaExtractor {
   Statistics stat = new Statistics();
   @Override
   public Statistics getVendorConfiguration() {
      return stat;
   }
   
   @Override 
   public void enterIf_stanza(@NotNull QuantaGrammar.If_stanzaContext ctx) { 
      
   	//  this.stat.interfaces.add(ctx.iname.getText());  
   }
   
   @Override 
   public void enterLacp(@NotNull QuantaGrammar.LacpContext ctx) {
      stat.L2ProtoInst("LACP", null);
   }
   
   @Override 
   public void enterUdld(@NotNull QuantaGrammar.UdldContext ctx) {
      stat.L2ProtoInst("UDLD", null);
   }
   
   @Override 
   public void enterAcl_stanza(@NotNull QuantaGrammar.Acl_stanzaContext ctx) {
   //   this.stat.acls.add(ctx.aclName.getText());
      
   }
}
