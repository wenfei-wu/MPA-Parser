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
/*
   @Override 
   public void enterInterface_stanza(@NotNull QuantaGrammar.Interface_stanzaContext ctx) { 
      String name = ctx.iname.getText();
      System.out.println("Extractor find a iname "+name);
      stat.GetIface(name);
   }
*/
}
