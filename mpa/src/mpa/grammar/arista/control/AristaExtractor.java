package mpa.grammar.arista.control;

import org.antlr.v4.runtime.misc.NotNull;

import mpa.grammar.MpaExtractor;
import mpa.grammar.arista.AristaGrammar;
import mpa.grammar.arista.AristaGrammarBaseListener;
import mpa.representation.*;

public class AristaExtractor extends AristaGrammarBaseListener
         implements MpaExtractor {
   Statistics stat = new Statistics();
   @Override
   public Statistics getVendorConfiguration() {
      return stat;
   }
   
   
   
   @Override 
   public void enterIf_stanza(@NotNull AristaGrammar.If_stanzaContext ctx) { 
 //    this.stat.interfaces.add(ctx.iname.getText());   
   }
   
   @Override
   public void enterAcl_stanza(@NotNull AristaGrammar.Acl_stanzaContext ctx) {   
 //     this.stat.acls.add(ctx.aclName.getText());
   }
   
   @Override 
   public void enterSpanning_tree_stanza(@NotNull AristaGrammar.Spanning_tree_stanzaContext ctx) { 
      stat.DeclareL2Proto("MSTP");   
   }
   
   @Override public void enterRouter_bgp_stanza(@NotNull AristaGrammar.Router_bgp_stanzaContext ctx) { 
//      this.stat.hasBGP=true;
//      this.stat.BGPInst += 1;   
//      this.stat.asNumber = ctx.asnumber.getText();
   }
   
   @Override public void enterNeighbor(@NotNull AristaGrammar.NeighborContext ctx) { 
//      BGPNeighbor neighbor = new BGPNeighbor();
//      neighbor.asNumber = ctx.asnumber.getText();
//      neighbor.ipAddress = ctx.ipaddress.getText();
//      this.stat.bgpNeighbors.add(neighbor);
   }
   
}
