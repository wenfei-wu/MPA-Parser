package mpa.grammar.cisco.control;

import org.antlr.v4.runtime.misc.NotNull;

import mpa.grammar.MpaExtractor;
import mpa.grammar.cisco.CiscoGrammar;
import mpa.grammar.cisco.CiscoGrammarBaseListener;
import mpa.representation.Statistics;

public class CiscoExtractor extends CiscoGrammarBaseListener
         implements MpaExtractor {
   Statistics stat;
   @Override
   public Statistics getVendorConfiguration() {
      return stat;
   }

   @Override 
   public void enterInterface_stanza(@NotNull CiscoGrammar.Interface_stanzaContext ctx) { 
      String name = ctx.iname.getText();
      System.out.println("Extractor find a iname "+name);
   }
}
