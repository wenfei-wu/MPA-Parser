package mpa.grammar.flatjuniper.control;

import org.antlr.v4.runtime.ParserRuleContext;

import mpa.grammar.MpaCombinedParser;
import mpa.grammar.flatjuniper.FlatJuniperLexer;
import mpa.grammar.flatjuniper.FlatJuniperParser;

public class FlatJuniperCombinedParser extends 
			MpaCombinedParser<FlatJuniperParser, FlatJuniperLexer> {

	public FlatJuniperCombinedParser(String input) {
		super(FlatJuniperParser.class, FlatJuniperLexer.class, input);
	}

	@Override
	public ParserRuleContext parse() {
		return _parser.flat_juniper_configuration();
	}
}
