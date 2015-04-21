// Generated from /afs/cs.wisc.edu/u/j/m/jmishra/mpa-git/cs740/mpa/src/mpa/grammar/arista/AristaGrammar.g4 by ANTLR 4.4

package mpa.grammar.arista;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AristaGrammar}.
 */
public interface AristaGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AristaGrammar#if_stanza}.
	 * @param ctx the parse tree
	 */
	void enterIf_stanza(@NotNull AristaGrammar.If_stanzaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AristaGrammar#if_stanza}.
	 * @param ctx the parse tree
	 */
	void exitIf_stanza(@NotNull AristaGrammar.If_stanzaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AristaGrammar#router_bgp_stanza}.
	 * @param ctx the parse tree
	 */
	void enterRouter_bgp_stanza(@NotNull AristaGrammar.Router_bgp_stanzaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AristaGrammar#router_bgp_stanza}.
	 * @param ctx the parse tree
	 */
	void exitRouter_bgp_stanza(@NotNull AristaGrammar.Router_bgp_stanzaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AristaGrammar#anything_else}.
	 * @param ctx the parse tree
	 */
	void enterAnything_else(@NotNull AristaGrammar.Anything_elseContext ctx);
	/**
	 * Exit a parse tree produced by {@link AristaGrammar#anything_else}.
	 * @param ctx the parse tree
	 */
	void exitAnything_else(@NotNull AristaGrammar.Anything_elseContext ctx);
	/**
	 * Enter a parse tree produced by {@link AristaGrammar#arista_configuration}.
	 * @param ctx the parse tree
	 */
	void enterArista_configuration(@NotNull AristaGrammar.Arista_configurationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AristaGrammar#arista_configuration}.
	 * @param ctx the parse tree
	 */
	void exitArista_configuration(@NotNull AristaGrammar.Arista_configurationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AristaGrammar#acl_stanza}.
	 * @param ctx the parse tree
	 */
	void enterAcl_stanza(@NotNull AristaGrammar.Acl_stanzaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AristaGrammar#acl_stanza}.
	 * @param ctx the parse tree
	 */
	void exitAcl_stanza(@NotNull AristaGrammar.Acl_stanzaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AristaGrammar#spanning_tree_stanza}.
	 * @param ctx the parse tree
	 */
	void enterSpanning_tree_stanza(@NotNull AristaGrammar.Spanning_tree_stanzaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AristaGrammar#spanning_tree_stanza}.
	 * @param ctx the parse tree
	 */
	void exitSpanning_tree_stanza(@NotNull AristaGrammar.Spanning_tree_stanzaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AristaGrammar#neighbor}.
	 * @param ctx the parse tree
	 */
	void enterNeighbor(@NotNull AristaGrammar.NeighborContext ctx);
	/**
	 * Exit a parse tree produced by {@link AristaGrammar#neighbor}.
	 * @param ctx the parse tree
	 */
	void exitNeighbor(@NotNull AristaGrammar.NeighborContext ctx);
}