package mpa.main;

import mpa.grammar.MpaCombinedParser;
import mpa.grammar.quanta.control.QuantaCombinedParser;
import mpa.grammar.quanta.control.QuantaExtractor;
import mpa.representation.Statistics;
import mpa.util.Preprocessor;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;



public class QuantaDriver {
   public static void main(String []args){
      //String file=args[0];
      String file = "quanta_LB4M.cfg";
	   // read files
      String configContent = "";
      Preprocessor prep = new Preprocessor();
      try {
         configContent = prep.Process("Quanta", file);
      } catch (Exception e) {
    	   return ;
      }
      MpaCombinedParser<?,?> parser = new QuantaCombinedParser(configContent);
      ParserRuleContext tree = parser.parse();
      QuantaExtractor extractor = new QuantaExtractor();
      ParseTreeWalker walker = new ParseTreeWalker();
      walker.walk(extractor, tree);
    //  System.out.println("done");
      Statistics stat = extractor.getVendorConfiguration();
      stat.PrintIfaces();
   }
}
