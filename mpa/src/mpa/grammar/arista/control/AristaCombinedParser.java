package mpa.grammar.arista.control;

import mpa.grammar.MpaCombinedParser;
import mpa.grammar.arista.AristaGrammar;
import mpa.grammar.arista.AristaLexer;
import mpa.grammar.arista.AristaGrammar.Arista_configurationContext;

public class AristaCombinedParser  extends
                     MpaCombinedParser<AristaGrammar, AristaLexer> {
   
   public AristaCombinedParser(String input) {
      super(AristaGrammar.class, AristaLexer.class, input);
   }
   
   @Override
   public Arista_configurationContext parse() {
      return _parser.arista_configuration();
   }
}
