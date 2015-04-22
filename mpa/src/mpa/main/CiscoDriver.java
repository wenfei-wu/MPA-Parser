package mpa.main;

import mpa.grammar.MpaCombinedParser;
import mpa.grammar.cisco.control.CiscoCombinedParser;
import mpa.grammar.cisco.control.CiscoExtractor;
import mpa.representation.Statistics;
import mpa.util.Preprocessor;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;



public class CiscoDriver {

   public void example() {
      //String file=args[0];
      String file = "cisco_test.cfg";
	   // read files
      String configContent = "";
      Preprocessor prep = new Preprocessor();
      try {
         configContent = prep.Process("Cisco", file);
      } catch (Exception e) {
    	   return ;
      }
      MpaCombinedParser<?,?> parser = new CiscoCombinedParser(configContent);
      ParserRuleContext tree = parser.parse();
      CiscoExtractor extractor = new CiscoExtractor();
      ParseTreeWalker walker = new ParseTreeWalker();
      walker.walk(extractor, tree);
    //  System.out.println("done");
      Statistics stat = extractor.getVendorConfiguration();
   }
}
