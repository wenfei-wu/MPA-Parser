// Generated from /afs/cs.wisc.edu/u/j/m/jmishra/mpa-git/cs740/mpa/src/mpa/grammar/quanta/QuantaGrammar.g4 by ANTLR 4.4

package mpa.grammar.quanta;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QuantaGrammar extends mpa.grammar.MpaParser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		UDLD=2, ACCESS_LIST=6, ANYTHING=1, LACP=3, MANAGEMENT=5, INTERFACE=4, 
		NEWLINE=7, WS=9, NAME=8;
	public static final String[] tokenNames = {
		"<INVALID>", "ANYTHING", "'udld'", "'lacp'", "'interface'", "'management'", 
		"'access-list'", "NEWLINE", "NAME", "WS"
	};
	public static final int
		RULE_quanta_configuration = 0, RULE_if_stanza = 1, RULE_udld = 2, RULE_lacp = 3, 
		RULE_anything_else = 4, RULE_acl_stanza = 5;
	public static final String[] ruleNames = {
		"quanta_configuration", "if_stanza", "udld", "lacp", "anything_else", 
		"acl_stanza"
	};

	@Override
	public String getGrammarFileName() { return "QuantaGrammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }



	public QuantaGrammar(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Quanta_configurationContext extends ParserRuleContext {
		public If_stanzaContext if_stanza(int i) {
			return getRuleContext(If_stanzaContext.class,i);
		}
		public List<Acl_stanzaContext> acl_stanza() {
			return getRuleContexts(Acl_stanzaContext.class);
		}
		public Acl_stanzaContext acl_stanza(int i) {
			return getRuleContext(Acl_stanzaContext.class,i);
		}
		public TerminalNode EOF() { return getToken(QuantaGrammar.EOF, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(QuantaGrammar.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(QuantaGrammar.NEWLINE, i);
		}
		public List<If_stanzaContext> if_stanza() {
			return getRuleContexts(If_stanzaContext.class);
		}
		public Quanta_configurationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quanta_configuration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuantaGrammarListener ) ((QuantaGrammarListener)listener).enterQuanta_configuration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuantaGrammarListener ) ((QuantaGrammarListener)listener).exitQuanta_configuration(this);
		}
	}

	public final Quanta_configurationContext quanta_configuration() throws RecognitionException {
		Quanta_configurationContext _localctx = new Quanta_configurationContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_quanta_configuration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==INTERFACE || _la==MANAGEMENT) {
				{
				setState(14);
				switch (_input.LA(1)) {
				case MANAGEMENT:
					{
					setState(12); acl_stanza();
					}
					break;
				case INTERFACE:
					{
					setState(13); if_stanza();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(18);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(22);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(19); match(NEWLINE);
				}
				}
				setState(24);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(25); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class If_stanzaContext extends ParserRuleContext {
		public Token iname;
		public List<LacpContext> lacp() {
			return getRuleContexts(LacpContext.class);
		}
		public LacpContext lacp(int i) {
			return getRuleContext(LacpContext.class,i);
		}
		public TerminalNode NAME() { return getToken(QuantaGrammar.NAME, 0); }
		public UdldContext udld(int i) {
			return getRuleContext(UdldContext.class,i);
		}
		public List<UdldContext> udld() {
			return getRuleContexts(UdldContext.class);
		}
		public TerminalNode NEWLINE() { return getToken(QuantaGrammar.NEWLINE, 0); }
		public Anything_elseContext anything_else(int i) {
			return getRuleContext(Anything_elseContext.class,i);
		}
		public TerminalNode INTERFACE() { return getToken(QuantaGrammar.INTERFACE, 0); }
		public List<Anything_elseContext> anything_else() {
			return getRuleContexts(Anything_elseContext.class);
		}
		public If_stanzaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_stanza; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuantaGrammarListener ) ((QuantaGrammarListener)listener).enterIf_stanza(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuantaGrammarListener ) ((QuantaGrammarListener)listener).exitIf_stanza(this);
		}
	}

	public final If_stanzaContext if_stanza() throws RecognitionException {
		If_stanzaContext _localctx = new If_stanzaContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_if_stanza);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27); match(INTERFACE);
			setState(28); ((If_stanzaContext)_localctx).iname = match(NAME);
			setState(29); match(NEWLINE);
			System.out.println("Interface found: " + _localctx.iname.getText()); 
			setState(36);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANYTHING) | (1L << UDLD) | (1L << LACP))) != 0)) {
				{
				setState(34);
				switch (_input.LA(1)) {
				case UDLD:
					{
					setState(31); udld();
					}
					break;
				case LACP:
					{
					setState(32); lacp();
					}
					break;
				case ANYTHING:
					{
					setState(33); anything_else();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(38);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UdldContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(QuantaGrammar.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(QuantaGrammar.NEWLINE, i);
		}
		public TerminalNode UDLD() { return getToken(QuantaGrammar.UDLD, 0); }
		public UdldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_udld; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuantaGrammarListener ) ((QuantaGrammarListener)listener).enterUdld(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuantaGrammarListener ) ((QuantaGrammarListener)listener).exitUdld(this);
		}
	}

	public final UdldContext udld() throws RecognitionException {
		UdldContext _localctx = new UdldContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_udld);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(39); match(UDLD);
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANYTHING) | (1L << UDLD) | (1L << LACP) | (1L << INTERFACE) | (1L << MANAGEMENT) | (1L << ACCESS_LIST) | (1L << NAME) | (1L << WS))) != 0)) {
				{
				{
				setState(40);
				_la = _input.LA(1);
				if ( _la <= 0 || (_la==NEWLINE) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(46); match(NEWLINE);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LacpContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(QuantaGrammar.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(QuantaGrammar.NEWLINE, i);
		}
		public TerminalNode LACP() { return getToken(QuantaGrammar.LACP, 0); }
		public LacpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lacp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuantaGrammarListener ) ((QuantaGrammarListener)listener).enterLacp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuantaGrammarListener ) ((QuantaGrammarListener)listener).exitLacp(this);
		}
	}

	public final LacpContext lacp() throws RecognitionException {
		LacpContext _localctx = new LacpContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_lacp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(48); match(LACP);
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANYTHING) | (1L << UDLD) | (1L << LACP) | (1L << INTERFACE) | (1L << MANAGEMENT) | (1L << ACCESS_LIST) | (1L << NAME) | (1L << WS))) != 0)) {
				{
				{
				setState(49);
				_la = _input.LA(1);
				if ( _la <= 0 || (_la==NEWLINE) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(55); match(NEWLINE);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Anything_elseContext extends ParserRuleContext {
		public TerminalNode ANYTHING() { return getToken(QuantaGrammar.ANYTHING, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(QuantaGrammar.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(QuantaGrammar.NEWLINE, i);
		}
		public Anything_elseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anything_else; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuantaGrammarListener ) ((QuantaGrammarListener)listener).enterAnything_else(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuantaGrammarListener ) ((QuantaGrammarListener)listener).exitAnything_else(this);
		}
	}

	public final Anything_elseContext anything_else() throws RecognitionException {
		Anything_elseContext _localctx = new Anything_elseContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_anything_else);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(57); match(ANYTHING);
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANYTHING) | (1L << UDLD) | (1L << LACP) | (1L << INTERFACE) | (1L << MANAGEMENT) | (1L << ACCESS_LIST) | (1L << NAME) | (1L << WS))) != 0)) {
				{
				{
				setState(58);
				_la = _input.LA(1);
				if ( _la <= 0 || (_la==NEWLINE) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(64); match(NEWLINE);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Acl_stanzaContext extends ParserRuleContext {
		public Token aclName;
		public List<TerminalNode> ANYTHING() { return getTokens(QuantaGrammar.ANYTHING); }
		public TerminalNode ANYTHING(int i) {
			return getToken(QuantaGrammar.ANYTHING, i);
		}
		public TerminalNode NAME() { return getToken(QuantaGrammar.NAME, 0); }
		public TerminalNode ACCESS_LIST() { return getToken(QuantaGrammar.ACCESS_LIST, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(QuantaGrammar.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(QuantaGrammar.NEWLINE, i);
		}
		public TerminalNode MANAGEMENT() { return getToken(QuantaGrammar.MANAGEMENT, 0); }
		public Acl_stanzaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_acl_stanza; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuantaGrammarListener ) ((QuantaGrammarListener)listener).enterAcl_stanza(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuantaGrammarListener ) ((QuantaGrammarListener)listener).exitAcl_stanza(this);
		}
	}

	public final Acl_stanzaContext acl_stanza() throws RecognitionException {
		Acl_stanzaContext _localctx = new Acl_stanzaContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_acl_stanza);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66); match(MANAGEMENT);
			setState(67); match(ACCESS_LIST);
			setState(68); ((Acl_stanzaContext)_localctx).aclName = match(NAME);
			setState(69); match(NEWLINE);
			System.out.println("ACL found: " + _localctx.aclName.getText()); 
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ANYTHING) {
				{
				{
				setState(71); match(ANYTHING);
				setState(75);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANYTHING) | (1L << UDLD) | (1L << LACP) | (1L << INTERFACE) | (1L << MANAGEMENT) | (1L << ACCESS_LIST) | (1L << NAME) | (1L << WS))) != 0)) {
					{
					{
					setState(72);
					_la = _input.LA(1);
					if ( _la <= 0 || (_la==NEWLINE) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					}
					}
					setState(77);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(78); match(NEWLINE);
				}
				}
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\13W\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\3\2\7\2\21\n\2\f\2\16\2\24\13\2"+
		"\3\2\7\2\27\n\2\f\2\16\2\32\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7"+
		"\3%\n\3\f\3\16\3(\13\3\3\4\3\4\7\4,\n\4\f\4\16\4/\13\4\3\4\3\4\3\5\3\5"+
		"\7\5\65\n\5\f\5\16\58\13\5\3\5\3\5\3\6\3\6\7\6>\n\6\f\6\16\6A\13\6\3\6"+
		"\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7L\n\7\f\7\16\7O\13\7\3\7\7\7R\n\7"+
		"\f\7\16\7U\13\7\3\7\2\2\b\2\4\6\b\n\f\2\3\3\2\t\t[\2\22\3\2\2\2\4\35\3"+
		"\2\2\2\6)\3\2\2\2\b\62\3\2\2\2\n;\3\2\2\2\fD\3\2\2\2\16\21\5\f\7\2\17"+
		"\21\5\4\3\2\20\16\3\2\2\2\20\17\3\2\2\2\21\24\3\2\2\2\22\20\3\2\2\2\22"+
		"\23\3\2\2\2\23\30\3\2\2\2\24\22\3\2\2\2\25\27\7\t\2\2\26\25\3\2\2\2\27"+
		"\32\3\2\2\2\30\26\3\2\2\2\30\31\3\2\2\2\31\33\3\2\2\2\32\30\3\2\2\2\33"+
		"\34\7\2\2\3\34\3\3\2\2\2\35\36\7\6\2\2\36\37\7\n\2\2\37 \7\t\2\2 &\b\3"+
		"\1\2!%\5\6\4\2\"%\5\b\5\2#%\5\n\6\2$!\3\2\2\2$\"\3\2\2\2$#\3\2\2\2%(\3"+
		"\2\2\2&$\3\2\2\2&\'\3\2\2\2\'\5\3\2\2\2(&\3\2\2\2)-\7\4\2\2*,\n\2\2\2"+
		"+*\3\2\2\2,/\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\60\3\2\2\2/-\3\2\2\2\60\61\7"+
		"\t\2\2\61\7\3\2\2\2\62\66\7\5\2\2\63\65\n\2\2\2\64\63\3\2\2\2\658\3\2"+
		"\2\2\66\64\3\2\2\2\66\67\3\2\2\2\679\3\2\2\28\66\3\2\2\29:\7\t\2\2:\t"+
		"\3\2\2\2;?\7\3\2\2<>\n\2\2\2=<\3\2\2\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@"+
		"B\3\2\2\2A?\3\2\2\2BC\7\t\2\2C\13\3\2\2\2DE\7\7\2\2EF\7\b\2\2FG\7\n\2"+
		"\2GH\7\t\2\2HS\b\7\1\2IM\7\3\2\2JL\n\2\2\2KJ\3\2\2\2LO\3\2\2\2MK\3\2\2"+
		"\2MN\3\2\2\2NP\3\2\2\2OM\3\2\2\2PR\7\t\2\2QI\3\2\2\2RU\3\2\2\2SQ\3\2\2"+
		"\2ST\3\2\2\2T\r\3\2\2\2US\3\2\2\2\f\20\22\30$&-\66?MS";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}