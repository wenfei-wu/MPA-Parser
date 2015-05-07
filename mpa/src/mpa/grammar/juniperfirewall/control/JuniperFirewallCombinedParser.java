package mpa.grammar.juniperfirewall.control;

import mpa.grammar.MpaCombinedParser;
import mpa.grammar.juniperfirewall.JuniperFirewallGrammar;
import mpa.grammar.juniperfirewall.JuniperFirewallLexer;
import mpa.grammar.juniperfirewall.JuniperFirewallGrammar.Juniperfirewall_configurationContext;

public class JuniperFirewallCombinedParser  extends
                     MpaCombinedParser<JuniperFirewallGrammar, JuniperFirewallLexer> {
   
   public JuniperFirewallCombinedParser(String input) {
      super(JuniperFirewallGrammar.class,JuniperFirewallLexer.class, input);
   }
   
   @Override
   public Juniperfirewall_configurationContext parse() {
      return _parser.juniperfirewall_configuration();
   }
}
