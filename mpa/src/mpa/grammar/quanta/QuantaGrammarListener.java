// Generated from /afs/cs.wisc.edu/u/j/m/jmishra/mpa-git/cs740/mpa/src/mpa/grammar/quanta/QuantaGrammar.g4 by ANTLR 4.4

package mpa.grammar.quanta;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QuantaGrammar}.
 */
public interface QuantaGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QuantaGrammar#if_stanza}.
	 * @param ctx the parse tree
	 */
	void enterIf_stanza(@NotNull QuantaGrammar.If_stanzaContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuantaGrammar#if_stanza}.
	 * @param ctx the parse tree
	 */
	void exitIf_stanza(@NotNull QuantaGrammar.If_stanzaContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuantaGrammar#lacp}.
	 * @param ctx the parse tree
	 */
	void enterLacp(@NotNull QuantaGrammar.LacpContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuantaGrammar#lacp}.
	 * @param ctx the parse tree
	 */
	void exitLacp(@NotNull QuantaGrammar.LacpContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuantaGrammar#udld}.
	 * @param ctx the parse tree
	 */
	void enterUdld(@NotNull QuantaGrammar.UdldContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuantaGrammar#udld}.
	 * @param ctx the parse tree
	 */
	void exitUdld(@NotNull QuantaGrammar.UdldContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuantaGrammar#quanta_configuration}.
	 * @param ctx the parse tree
	 */
	void enterQuanta_configuration(@NotNull QuantaGrammar.Quanta_configurationContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuantaGrammar#quanta_configuration}.
	 * @param ctx the parse tree
	 */
	void exitQuanta_configuration(@NotNull QuantaGrammar.Quanta_configurationContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuantaGrammar#anything_else}.
	 * @param ctx the parse tree
	 */
	void enterAnything_else(@NotNull QuantaGrammar.Anything_elseContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuantaGrammar#anything_else}.
	 * @param ctx the parse tree
	 */
	void exitAnything_else(@NotNull QuantaGrammar.Anything_elseContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuantaGrammar#acl_stanza}.
	 * @param ctx the parse tree
	 */
	void enterAcl_stanza(@NotNull QuantaGrammar.Acl_stanzaContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuantaGrammar#acl_stanza}.
	 * @param ctx the parse tree
	 */
	void exitAcl_stanza(@NotNull QuantaGrammar.Acl_stanzaContext ctx);
}