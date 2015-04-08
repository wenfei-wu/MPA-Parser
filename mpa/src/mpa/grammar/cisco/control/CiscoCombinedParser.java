package mpa.grammar.cisco.control;

import mpa.grammar.MpaCombinedParser;
import mpa.grammar.cisco.CiscoGrammar;
import mpa.grammar.cisco.CiscoGrammarCommonLexer;
import mpa.grammar.cisco.CiscoGrammar.Cisco_configurationContext;

public class CiscoCombinedParser  extends
                     MpaCombinedParser<CiscoGrammar, CiscoGrammarCommonLexer> {
   
   public CiscoCombinedParser(String input) {
      super(CiscoGrammar.class, CiscoGrammarCommonLexer.class, input);
   }
   
   @Override
   public Cisco_configurationContext parse() {
      return _parser.cisco_configuration();
   }
}
