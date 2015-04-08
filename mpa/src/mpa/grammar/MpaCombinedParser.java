package mpa.grammar;

import java.lang.reflect.InvocationTargetException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
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
      
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         System.exit(0);
      }
   }

   public abstract ParserRuleContext parse();
}
