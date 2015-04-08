package mpa.grammar;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.PredictionMode;

public abstract class MpaParser extends Parser{

   public MpaParser(TokenStream input) {
      super(input);
   }


}
