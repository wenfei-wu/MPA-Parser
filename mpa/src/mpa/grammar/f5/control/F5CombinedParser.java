package mpa.grammar.f5.control;

import org.antlr.v4.runtime.ParserRuleContext;

import mpa.grammar.MpaCombinedParser;
import mpa.grammar.f5.F5Lexer;
import mpa.grammar.f5.F5Parser;

public class F5CombinedParser extends 
            MpaCombinedParser<F5Parser, F5Lexer> {

   public F5CombinedParser(String input) {
      super(F5Parser.class, F5Lexer.class, input);
   }

   @Override
   public ParserRuleContext parse() {
      return _parser.f5_configuration();
   }

}
