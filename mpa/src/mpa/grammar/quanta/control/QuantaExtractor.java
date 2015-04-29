package mpa.grammar.quanta.control;

import org.antlr.v4.runtime.misc.NotNull;

import mpa.grammar.MpaExtractor;
import mpa.grammar.quanta.QuantaGrammar;
import mpa.grammar.quanta.QuantaGrammarBaseListener;
import mpa.representation.Identifiers;
import mpa.representation.Statistics;

public class QuantaExtractor extends QuantaGrammarBaseListener
        implements MpaExtractor {

    Statistics stat = new Statistics();

    @Override
    public Statistics getVendorConfiguration() {
        return stat;
    }

    @Override
    public void enterIf_stanza(@NotNull QuantaGrammar.If_stanzaContext ctx) {
        stat.AddRefEntity(Identifiers.IFACE_T, ctx.iname.getText());
    }

    @Override
    public void enterLacp(@NotNull QuantaGrammar.LacpContext ctx) {
        stat.L2ProtoInst("LACP", null);
    }

    @Override
    public void enterUdld(@NotNull QuantaGrammar.UdldContext ctx) {
        stat.L2ProtoInst("UDLD", null);
    }

    @Override
    public void enterAcl_stanza(@NotNull QuantaGrammar.Acl_stanzaContext ctx) {
        stat.AddRefEntity(Identifiers.ACCESS_T, ctx.aclName.getText());

    }
    
    @Override 
    public void enterVlan_declared(@NotNull QuantaGrammar.Vlan_declaredContext ctx) {
        stat.DeclareVlan(ctx.vlanNumber.getText());    
    }
    
    @Override
    public void enterMst_instance(@NotNull QuantaGrammar.Mst_instanceContext ctx) {
        stat.L2ProtoInst("MSTP", ctx.vlanRange.getText());
    }
    
    @Override
    public void exitQuanta_configuration(@NotNull QuantaGrammar.Quanta_configurationContext ctx) {
        stat.getL2protocols().ProcessSpecialProtocols();
    }
}
