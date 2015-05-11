package mpa.grammar.juniperfirewall.control;


import mpa.grammar.MpaExtractor;
import mpa.grammar.juniperfirewall.JuniperFirewallGrammar;
import mpa.grammar.juniperfirewall.JuniperFirewallGrammarBaseListener;
import mpa.representation.*;
import org.antlr.v4.runtime.misc.NotNull;

public class JuniperFirewallExtractor extends JuniperFirewallGrammarBaseListener
        implements MpaExtractor {

    private Statistics stat = new Statistics();
    private String policyName;

    @Override
    public Statistics getVendorConfiguration() {
        return stat;
    }
    
    @Override public void enterZoneWithVrouter(@NotNull JuniperFirewallGrammar.ZoneWithVrouterContext ctx) {
        stat.AddIntraRef(Identifiers.ZONE, ctx.zoneName.getText(), Identifiers.VROUTER, ctx.vrouterName.getText());
    }
    
    @Override public void enterInterfaceWithZone(@NotNull JuniperFirewallGrammar.InterfaceWithZoneContext ctx) {
        stat.AddIntraRef(Identifiers.INTERFACE, ctx.interfaceName.getText(), Identifiers.ZONE, ctx.zoneName.getText());
    }
    
    @Override public void enterInterfaceWithID(@NotNull JuniperFirewallGrammar.InterfaceWithIDContext ctx) {
        stat.AddIntraRef(Identifiers.INTERFACE, ctx.interfaceName.getText(), Identifiers.ZONE, ctx.zoneName.getText());
    }
    
    @Override public void enterInterfaceWithTAG(@NotNull JuniperFirewallGrammar.InterfaceWithTAGContext ctx) {
        stat.AddIntraRef(Identifiers.INTERFACE, ctx.interfaceName.getText(), Identifiers.ZONE, ctx.zoneName.getText());
    }
    
    @Override public void enterAddress(@NotNull JuniperFirewallGrammar.AddressContext ctx) { 
        stat.AddIntraRef(Identifiers.ADDRESS, ctx.addressName.getText(), Identifiers.ZONE, ctx.zoneName.getText());
    }
    
    @Override public void enterGroupAddressWithAddress(@NotNull JuniperFirewallGrammar.GroupAddressWithAddressContext ctx) {
        stat.AddIntraRef(Identifiers.GROUP_ADDRESS, ctx.groupName.getText(), Identifiers.ZONE, ctx.zoneName.getText());
    }
    
    @Override public void enterPolicyService(@NotNull JuniperFirewallGrammar.PolicyServiceContext ctx) {
        stat.AddIntraRef(Identifiers.POLICY, policyName, Identifiers.SERVICE, ctx.serviceName.getText());
    }
    
    @Override public void enterPolicy(@NotNull JuniperFirewallGrammar.PolicyContext ctx) { 
        policyName = ctx.policyName.getText();
    }
    
    @Override public void enterNsrp(@NotNull JuniperFirewallGrammar.NsrpContext ctx) {
        stat.L2ProtoInst("NSRP", null);
    }

}
