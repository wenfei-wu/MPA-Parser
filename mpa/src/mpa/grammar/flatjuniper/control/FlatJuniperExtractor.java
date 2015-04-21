package mpa.grammar.flatjuniper.control;

import mpa.grammar.MpaExtractor;
import mpa.grammar.flatjuniper.FlatJuniperParserBaseListener;
import mpa.representation.Statistics;


public class FlatJuniperExtractor extends FlatJuniperParserBaseListener
         implements MpaExtractor {
	   Statistics stat = new Statistics();
	   @Override
	   public Statistics getVendorConfiguration() {
	      return stat;
	   }

}
