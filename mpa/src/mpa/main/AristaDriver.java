package mpa.main;

import mpa.grammar.MpaCombinedParser;
import mpa.grammar.arista.control.AristaCombinedParser;
import mpa.grammar.arista.control.AristaExtractor;
import mpa.representation.Statistics;
import mpa.util.Preprocessor;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;



public class AristaDriver {
   public void example(){
      //String file=args[0];
      String file = "arista_DCS-7050S-64-F.cfg";
	   // read files
      String configContent = "";
      Preprocessor prep = new Preprocessor();
      try {
         configContent = prep.Process("Arista", file);
      } catch (Exception e) {
    	   return ;
      }
      MpaCombinedParser<?,?> parser = new AristaCombinedParser(configContent);
      ParserRuleContext tree = parser.parse();
      AristaExtractor extractor = new AristaExtractor();
      ParseTreeWalker walker = new ParseTreeWalker();
      walker.walk(extractor, tree);
    //  System.out.println("done");
      Statistics stat = extractor.getVendorConfiguration();
   }
}
