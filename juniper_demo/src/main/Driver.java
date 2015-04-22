package main;

import juniper.JuniperLexer;
import juniper.JuniperParser;
import juniper.control.JuniperFlattener;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;




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
      JuniperLexer lexer = new JuniperLexer(inputStream);
      CommonTokenStream tokens = new CommonTokenStream((TokenSource) lexer);
    // create parser
      JuniperParser parser = new JuniperParser(tokens);
    // configurations
      parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
      AddErrorListener(lexer, parser);      

      ParserRuleContext tree = parser.juniper_configuration();
      JuniperFlattener flattener = new JuniperFlattener();
      ParseTreeWalker walker = new ParseTreeWalker();
      walker.walk(flattener, tree);
      String result =  flattener.getFlattenedConfigurationText();
      System.out.println(result);
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
   }
}
