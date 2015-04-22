package mpa.grammar.juniper.control;

import org.antlr.v4.runtime.ParserRuleContext;

import mpa.grammar.MpaCombinedParser;
import mpa.grammar.juniper.JuniperLexer;
import mpa.grammar.juniper.JuniperParser;

public class JuniperCombinedParser extends 
				MpaCombinedParser<JuniperParser, JuniperLexer> {

	public JuniperCombinedParser(String input) {
		super(JuniperParser.class, JuniperLexer.class, input);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ParserRuleContext parse() {
		return _parser.juniper_configuration();
	}

}
