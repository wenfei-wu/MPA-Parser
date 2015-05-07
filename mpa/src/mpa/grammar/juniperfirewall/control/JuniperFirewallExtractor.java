package mpa.grammar.juniperfirewall.control;


import mpa.grammar.MpaExtractor;
import mpa.grammar.juniperfirewall.JuniperFirewallGrammarBaseListener;
import mpa.representation.*;

public class JuniperFirewallExtractor extends JuniperFirewallGrammarBaseListener
        implements MpaExtractor {

    private Statistics stat = new Statistics();   

    @Override
    public Statistics getVendorConfiguration() {
        return stat;
    }   

}
