package mpa.grammar.arista.control;

import org.antlr.v4.runtime.misc.NotNull;

import mpa.grammar.MpaExtractor;
import mpa.grammar.arista.AristaGrammar;
import mpa.grammar.arista.AristaGrammarBaseListener;
import mpa.representation.*;

public class AristaExtractor extends AristaGrammarBaseListener
        implements MpaExtractor {

    private Statistics stat = new Statistics();
    private String localASNumber;

    @Override
    public Statistics getVendorConfiguration() {
        return stat;
    }

    @Override
    public void enterIf_stanza(@NotNull AristaGrammar.If_stanzaContext ctx) {
        stat.AddRefEntity(Identifiers.IFACE_T, ctx.iname.getText());
    }

    @Override
    public void enterAcl_stanza(@NotNull AristaGrammar.Acl_stanzaContext ctx) {
        stat.AddRefEntity(Identifiers.ACCESS_T, ctx.aclName.getText());
    }

    @Override
    public void enterSpanning_tree_stanza(
            @NotNull AristaGrammar.Spanning_tree_stanzaContext ctx) {
        stat.DeclareL2Proto("MSTP");
    }

    @Override public void enterRouter_bgp_stanza(
            @NotNull AristaGrammar.Router_bgp_stanzaContext ctx) {
        localASNumber = ctx.asnumber.getText();
    }

    @Override public void enterNeighbor(
            @NotNull AristaGrammar.NeighborContext ctx) {
        stat.BgpNeighborAs(localASNumber, ctx.ipaddress.getText(), ctx.ipaddress
                .getText().contains(":") ? "128" : "32",
                ctx.asnumber.getText());
    }

}
