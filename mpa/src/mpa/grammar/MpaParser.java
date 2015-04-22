package mpa.grammar;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.PredictionMode;

public abstract class MpaParser extends Parser{

   public MpaParser(TokenStream input) {
      super(input);
//    initErrorListener();
   }


   public void initErrorListener() {
      addErrorListener(new BaseErrorListener() {
          @Override
          public void syntaxError(Recognizer<?, ?> recognizer,
                Object offendingSymbol, int line, int charPositionInLine,
                String msg, RecognitionException e) {
             throw new IllegalStateException("failed to parse at line " + line
                   + " due to " + msg, e);
          }
       });
   }
}
