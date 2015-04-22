package main;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.atn.PredictionMode;

import cisco.CiscoGrammar;
import cisco.CiscoGrammarCommonLexer;


public class Driver {
   public static void main(String []args){
      if(args.length!=2){
         System.out.println("usage java Driver [vendor] [config_file]");
      }
        // read files
      String configContent = "";
      Preprocessor prep = new Preprocessor();
      try {
         configContent = prep.Process(args[0], args[1]);
      } catch (Exception e) {
	     return ;
      }
      ANTLRInputStream inputStream = new ANTLRInputStream(configContent);
    // create lexer
      CiscoGrammarCommonLexer lexer = new CiscoGrammarCommonLexer(inputStream);
      CommonTokenStream tokens = new CommonTokenStream((TokenSource) lexer);
    // create parser
      CiscoGrammar parser = new CiscoGrammar(tokens);
    // configurations
      parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
      AddErrorListener(lexer, parser);
      try {
    // parse it
         parser.cisco_configuration();
      } catch (Exception e) {
    	  System.out.println("Error !!");
    //	  e.printStackTrace();
         return ;
      }
   }
   private static void AddErrorListener(Lexer lexer, Parser parser){
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
