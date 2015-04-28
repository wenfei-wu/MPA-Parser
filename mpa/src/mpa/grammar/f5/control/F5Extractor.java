package mpa.grammar.f5.control;

import mpa.grammar.MpaExtractor;
import mpa.grammar.f5.F5ParserBaseListener;
import mpa.representation.Statistics;

public class F5Extractor extends F5ParserBaseListener implements MpaExtractor {
   Statistics stat;
   @Override
   public Statistics getVendorConfiguration() {
      // TODO Auto-generated method stub
      return stat;
   }

}
