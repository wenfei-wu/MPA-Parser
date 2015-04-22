package mpa.grammar;



import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.PredictionMode;



public abstract class MpaCombinedParser<P extends MpaParser, L extends MpaLexer> {

   private L _lexer;
   protected P _parser;
   protected CommonTokenStream _tokens;
   public MpaCombinedParser(Class<P> pClass, Class<L> lClass, String input) {

      ANTLRInputStream inputStream = new ANTLRInputStream(input);
      try {
         _lexer = lClass.getConstructor(CharStream.class).newInstance(
               inputStream);
         _tokens = new CommonTokenStream(_lexer);
         _parser = pClass.getConstructor(TokenStream.class)
               .newInstance(_tokens);

         _parser.getInterpreter().setPredictionMode(PredictionMode.SLL);

         AddErrorListener(_lexer, _parser);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         System.exit(0);
      }
   }

   public abstract ParserRuleContext parse();
   
   private void AddErrorListener(Lexer lexer, Parser parser){
      lexer.addErrorListener(new BaseErrorListener() {
         @Override
         public void syntaxError(Recognizer<?, ?> recognizer,
               Object offendingSymbol, int line, int charPositionInLine,
               String msg, RecognitionException e) {
            throw new IllegalStateException("failed to token at line " + line
                  + " due to " + msg, e);
         }
      });

      parser.addErrorListener(new BaseErrorListener() {
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
