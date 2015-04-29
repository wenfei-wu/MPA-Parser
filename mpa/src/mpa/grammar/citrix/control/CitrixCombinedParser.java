package mpa.grammar.citrix.control;

import org.antlr.v4.runtime.ParserRuleContext;

import mpa.grammar.MpaCombinedParser;
import mpa.grammar.citrix.CitrixLexer;
import mpa.grammar.citrix.CitrixParser;

public class CitrixCombinedParser extends MpaCombinedParser<CitrixParser, CitrixLexer> {

   public CitrixCombinedParser(String input) {
      super(CitrixParser.class, CitrixLexer.class, input);
   }
   @Override
   public ParserRuleContext parse() {
      return _parser.citrix_configuration();
   }

}
