package mpa.grammar.arista.control;

import org.antlr.v4.runtime.misc.NotNull;

import mpa.grammar.MpaExtractor;
import mpa.grammar.arista.AristaGrammar;
import mpa.grammar.arista.AristaGrammarBaseListener;
import mpa.representation.Statistics;

public class AristaExtractor extends AristaGrammarBaseListener
         implements MpaExtractor {
   Statistics stat = new Statistics();
   @Override
   public Statistics getVendorConfiguration() {
      return stat;
   }
/*
   @Override 
   public void enterInterface_stanza(@NotNull AristaGrammar.Interface_stanzaContext ctx) { 
      String name = ctx.iname.getText();
      System.out.println("Extractor find a iname "+name);
      stat.GetIface(name);
   }
*/
}
