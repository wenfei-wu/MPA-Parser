package mpa.grammar.quanta.control;

import mpa.grammar.MpaCombinedParser;
import mpa.grammar.quanta.QuantaGrammar;
import mpa.grammar.quanta.QuantaLexer;
import mpa.grammar.quanta.QuantaGrammar.Quanta_configurationContext;

public class QuantaCombinedParser  extends
                     MpaCombinedParser<QuantaGrammar, QuantaLexer> {
   
   public QuantaCombinedParser(String input) {
      super(QuantaGrammar.class, QuantaLexer.class, input);
   }
   
   @Override
   public Quanta_configurationContext parse() {
      return _parser.quanta_configuration();
   }
}
