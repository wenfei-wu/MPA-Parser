// Generated from /afs/cs.wisc.edu/u/j/m/jmishra/mpa-git/cs740/mpa/src/mpa/grammar/arista/AristaGrammar.g4 by ANTLR 4.4

package mpa.grammar.arista;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AristaGrammar extends mpa.grammar.MpaParser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		REMOTE_AS=6, MSTP=3, NEWLINE=11, MODE=2, ROUTER=4, IP=9, SPANNING_TREE=1, 
		NAME=12, ACCESS_LIST=10, ANYTHING=7, INTERFACE=8, NEIGHBOR=5, WS=13;
	public static final String[] tokenNames = {
		"<INVALID>", "'spanning-tree'", "'mode'", "'mstp'", "'router'", "'neighbor'", 
		"'remote-as'", "ANYTHING", "'interface'", "'ip'", "'access-list'", "NEWLINE", 
		"NAME", "WS"
	};
	public static final int
		RULE_arista_configuration = 0, RULE_if_stanza = 1, RULE_acl_stanza = 2, 
		RULE_spanning_tree_stanza = 3, RULE_router_bgp_stanza = 4, RULE_anything_else = 5, 
		RULE_neighbor = 6;
	public static final String[] ruleNames = {
		"arista_configuration", "if_stanza", "acl_stanza", "spanning_tree_stanza", 
		"router_bgp_stanza", "anything_else", "neighbor"
	};

	@Override
	public String getGrammarFileName() { return "AristaGrammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }



	public AristaGrammar(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Arista_configurationContext extends ParserRuleContext {
		public If_stanzaContext if_stanza(int i) {
			return getRuleContext(If_stanzaContext.class,i);
		}
		public List<Spanning_tree_stanzaContext> spanning_tree_stanza() {
			return getRuleContexts(Spanning_tree_stanzaContext.class);
		}
		public List<Acl_stanzaContext> acl_stanza() {
			return getRuleContexts(Acl_stanzaContext.class);
		}
		public Acl_stanzaContext acl_stanza(int i) {
			return getRuleContext(Acl_stanzaContext.class,i);
		}
		public Spanning_tree_stanzaContext spanning_tree_stanza(int i) {
			return getRuleContext(Spanning_tree_stanzaContext.class,i);
		}
		public Router_bgp_stanzaContext router_bgp_stanza(int i) {
			return getRuleContext(Router_bgp_stanzaContext.class,i);
		}
		public TerminalNode EOF() { return getToken(AristaGrammar.EOF, 0); }
		public List<Router_bgp_stanzaContext> router_bgp_stanza() {
			return getRuleContexts(Router_bgp_stanzaContext.class);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(AristaGrammar.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(AristaGrammar.NEWLINE, i);
		}
		public List<If_stanzaContext> if_stanza() {
			return getRuleContexts(If_stanzaContext.class);
		}
		public Arista_configurationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arista_configuration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AristaGrammarListener ) ((AristaGrammarListener)listener).enterArista_configuration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AristaGrammarListener ) ((AristaGrammarListener)listener).exitArista_configuration(this);
		}
	}

	public final Arista_configurationContext arista_configuration() throws RecognitionException {
		Arista_configurationContext _localctx = new Arista_configurationContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_arista_configuration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SPANNING_TREE) | (1L << ROUTER) | (1L << INTERFACE) | (1L << IP))) != 0)) {
				{
				setState(18);
				switch (_input.LA(1)) {
				case IP:
					{
					setState(14); acl_stanza();
					}
					break;
				case INTERFACE:
					{
					setState(15); if_stanza();
					}
					break;
				case SPANNING_TREE:
					{
					setState(16); spanning_tree_stanza();
					}
					break;
				case ROUTER:
					{
					setState(17); router_bgp_stanza();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(22);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(26);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(23); match(NEWLINE);
				}
				}
				setState(28);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(29); match(EOF);
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
		public List<TerminalNode> ANYTHING() { return getTokens(AristaGrammar.ANYTHING); }
		public TerminalNode ANYTHING(int i) {
			return getToken(AristaGrammar.ANYTHING, i);
		}
		public TerminalNode NAME() { return getToken(AristaGrammar.NAME, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(AristaGrammar.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(AristaGrammar.NEWLINE, i);
		}
		public TerminalNode INTERFACE() { return getToken(AristaGrammar.INTERFACE, 0); }
		public If_stanzaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_stanza; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AristaGrammarListener ) ((AristaGrammarListener)listener).enterIf_stanza(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AristaGrammarListener ) ((AristaGrammarListener)listener).exitIf_stanza(this);
		}
	}

	public final If_stanzaContext if_stanza() throws RecognitionException {
		If_stanzaContext _localctx = new If_stanzaContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_if_stanza);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31); match(INTERFACE);
			setState(32); ((If_stanzaContext)_localctx).iname = match(NAME);
			setState(33); match(NEWLINE);
			System.out.println("Interface found: " + _localctx.iname.getText()); 
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ANYTHING) {
				{
				{
				setState(35); match(ANYTHING);
				setState(39);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SPANNING_TREE) | (1L << MODE) | (1L << MSTP) | (1L << ROUTER) | (1L << NEIGHBOR) | (1L << REMOTE_AS) | (1L << ANYTHING) | (1L << INTERFACE) | (1L << IP) | (1L << ACCESS_LIST) | (1L << NAME) | (1L << WS))) != 0)) {
					{
					{
					setState(36);
					_la = _input.LA(1);
					if ( _la <= 0 || (_la==NEWLINE) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					}
					}
					setState(41);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(42); match(NEWLINE);
				}
				}
				setState(47);
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

	public static class Acl_stanzaContext extends ParserRuleContext {
		public Token aclName;
		public List<TerminalNode> ANYTHING() { return getTokens(AristaGrammar.ANYTHING); }
		public TerminalNode ANYTHING(int i) {
			return getToken(AristaGrammar.ANYTHING, i);
		}
		public TerminalNode NAME() { return getToken(AristaGrammar.NAME, 0); }
		public TerminalNode ACCESS_LIST() { return getToken(AristaGrammar.ACCESS_LIST, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(AristaGrammar.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(AristaGrammar.NEWLINE, i);
		}
		public TerminalNode IP() { return getToken(AristaGrammar.IP, 0); }
		public Acl_stanzaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_acl_stanza; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AristaGrammarListener ) ((AristaGrammarListener)listener).enterAcl_stanza(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AristaGrammarListener ) ((AristaGrammarListener)listener).exitAcl_stanza(this);
		}
	}

	public final Acl_stanzaContext acl_stanza() throws RecognitionException {
		Acl_stanzaContext _localctx = new Acl_stanzaContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_acl_stanza);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48); match(IP);
			setState(49); match(ACCESS_LIST);
			setState(50); ((Acl_stanzaContext)_localctx).aclName = match(NAME);
			setState(51); match(NEWLINE);
			System.out.println("ACL found: " + _localctx.aclName.getText()); 
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ANYTHING) {
				{
				{
				setState(53); match(ANYTHING);
				setState(57);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SPANNING_TREE) | (1L << MODE) | (1L << MSTP) | (1L << ROUTER) | (1L << NEIGHBOR) | (1L << REMOTE_AS) | (1L << ANYTHING) | (1L << INTERFACE) | (1L << IP) | (1L << ACCESS_LIST) | (1L << NAME) | (1L << WS))) != 0)) {
					{
					{
					setState(54);
					_la = _input.LA(1);
					if ( _la <= 0 || (_la==NEWLINE) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					}
					}
					setState(59);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(60); match(NEWLINE);
				}
				}
				setState(65);
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

	public static class Spanning_tree_stanzaContext extends ParserRuleContext {
		public TerminalNode MSTP() { return getToken(AristaGrammar.MSTP, 0); }
		public TerminalNode NEWLINE() { return getToken(AristaGrammar.NEWLINE, 0); }
		public TerminalNode SPANNING_TREE() { return getToken(AristaGrammar.SPANNING_TREE, 0); }
		public TerminalNode MODE() { return getToken(AristaGrammar.MODE, 0); }
		public Spanning_tree_stanzaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_spanning_tree_stanza; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AristaGrammarListener ) ((AristaGrammarListener)listener).enterSpanning_tree_stanza(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AristaGrammarListener ) ((AristaGrammarListener)listener).exitSpanning_tree_stanza(this);
		}
	}

	public final Spanning_tree_stanzaContext spanning_tree_stanza() throws RecognitionException {
		Spanning_tree_stanzaContext _localctx = new Spanning_tree_stanzaContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_spanning_tree_stanza);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66); match(SPANNING_TREE);
			setState(67); match(MODE);
			setState(68); match(MSTP);
			setState(69); match(NEWLINE);
			System.out.println("MSTP enabled");
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

	public static class Router_bgp_stanzaContext extends ParserRuleContext {
		public Token asnumber;
		public TerminalNode ANYTHING() { return getToken(AristaGrammar.ANYTHING, 0); }
		public TerminalNode NAME() { return getToken(AristaGrammar.NAME, 0); }
		public TerminalNode NEWLINE() { return getToken(AristaGrammar.NEWLINE, 0); }
		public TerminalNode ROUTER() { return getToken(AristaGrammar.ROUTER, 0); }
		public Anything_elseContext anything_else(int i) {
			return getRuleContext(Anything_elseContext.class,i);
		}
		public NeighborContext neighbor(int i) {
			return getRuleContext(NeighborContext.class,i);
		}
		public List<Anything_elseContext> anything_else() {
			return getRuleContexts(Anything_elseContext.class);
		}
		public List<NeighborContext> neighbor() {
			return getRuleContexts(NeighborContext.class);
		}
		public Router_bgp_stanzaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_router_bgp_stanza; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AristaGrammarListener ) ((AristaGrammarListener)listener).enterRouter_bgp_stanza(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AristaGrammarListener ) ((AristaGrammarListener)listener).exitRouter_bgp_stanza(this);
		}
	}

	public final Router_bgp_stanzaContext router_bgp_stanza() throws RecognitionException {
		Router_bgp_stanzaContext _localctx = new Router_bgp_stanzaContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_router_bgp_stanza);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72); match(ROUTER);
			setState(73); match(ANYTHING);
			setState(74); ((Router_bgp_stanzaContext)_localctx).asnumber = match(NAME);
			setState(75); match(NEWLINE);
			System.out.println("AS NUMBER " + _localctx.asnumber.getText()); 
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEIGHBOR || _la==ANYTHING) {
				{
				setState(79);
				switch (_input.LA(1)) {
				case ANYTHING:
					{
					setState(77); anything_else();
					}
					break;
				case NEIGHBOR:
					{
					setState(78); neighbor();
					}
					break;
				default:
					throw new NoViableAltException(this);
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

	public static class Anything_elseContext extends ParserRuleContext {
		public TerminalNode ANYTHING() { return getToken(AristaGrammar.ANYTHING, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(AristaGrammar.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(AristaGrammar.NEWLINE, i);
		}
		public Anything_elseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anything_else; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AristaGrammarListener ) ((AristaGrammarListener)listener).enterAnything_else(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AristaGrammarListener ) ((AristaGrammarListener)listener).exitAnything_else(this);
		}
	}

	public final Anything_elseContext anything_else() throws RecognitionException {
		Anything_elseContext _localctx = new Anything_elseContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_anything_else);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(84); match(ANYTHING);
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SPANNING_TREE) | (1L << MODE) | (1L << MSTP) | (1L << ROUTER) | (1L << NEIGHBOR) | (1L << REMOTE_AS) | (1L << ANYTHING) | (1L << INTERFACE) | (1L << IP) | (1L << ACCESS_LIST) | (1L << NAME) | (1L << WS))) != 0)) {
				{
				{
				setState(85);
				_la = _input.LA(1);
				if ( _la <= 0 || (_la==NEWLINE) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(91); match(NEWLINE);
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

	public static class NeighborContext extends ParserRuleContext {
		public Token ipaddress;
		public Token asnumber;
		public TerminalNode NAME(int i) {
			return getToken(AristaGrammar.NAME, i);
		}
		public TerminalNode ANYTHING() { return getToken(AristaGrammar.ANYTHING, 0); }
		public List<TerminalNode> NAME() { return getTokens(AristaGrammar.NAME); }
		public List<TerminalNode> NEWLINE() { return getTokens(AristaGrammar.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(AristaGrammar.NEWLINE, i);
		}
		public TerminalNode NEIGHBOR() { return getToken(AristaGrammar.NEIGHBOR, 0); }
		public TerminalNode REMOTE_AS() { return getToken(AristaGrammar.REMOTE_AS, 0); }
		public NeighborContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_neighbor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AristaGrammarListener ) ((AristaGrammarListener)listener).enterNeighbor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AristaGrammarListener ) ((AristaGrammarListener)listener).exitNeighbor(this);
		}
	}

	public final NeighborContext neighbor() throws RecognitionException {
		NeighborContext _localctx = new NeighborContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_neighbor);
		int _la;
		try {
			setState(111);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(93); match(NEIGHBOR);
				setState(94); ((NeighborContext)_localctx).ipaddress = match(NAME);
				setState(95); match(REMOTE_AS);
				setState(96); ((NeighborContext)_localctx).asnumber = match(NAME);
				setState(97); match(NEWLINE);
				}
				System.out.println("neighbor IP address " + _localctx.ipaddress.getText()); 
				System.out.println("neighbor AS NUMBER " + _localctx.asnumber.getText()); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(101); match(NEIGHBOR);
				setState(102); match(NAME);
				setState(103); match(ANYTHING);
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SPANNING_TREE) | (1L << MODE) | (1L << MSTP) | (1L << ROUTER) | (1L << NEIGHBOR) | (1L << REMOTE_AS) | (1L << ANYTHING) | (1L << INTERFACE) | (1L << IP) | (1L << ACCESS_LIST) | (1L << NAME) | (1L << WS))) != 0)) {
					{
					{
					setState(104);
					_la = _input.LA(1);
					if ( _la <= 0 || (_la==NEWLINE) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					}
					}
					setState(109);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(110); match(NEWLINE);
				}
				}
				break;
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\17t\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\3\2\7\2\25\n\2"+
		"\f\2\16\2\30\13\2\3\2\7\2\33\n\2\f\2\16\2\36\13\2\3\2\3\2\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\7\3(\n\3\f\3\16\3+\13\3\3\3\7\3.\n\3\f\3\16\3\61\13\3\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\7\4:\n\4\f\4\16\4=\13\4\3\4\7\4@\n\4\f\4\16\4"+
		"C\13\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6R\n\6\f"+
		"\6\16\6U\13\6\3\7\3\7\7\7Y\n\7\f\7\16\7\\\13\7\3\7\3\7\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\bl\n\b\f\b\16\bo\13\b\3\b\5\br\n\b"+
		"\3\b\2\2\t\2\4\6\b\n\f\16\2\3\3\2\r\rz\2\26\3\2\2\2\4!\3\2\2\2\6\62\3"+
		"\2\2\2\bD\3\2\2\2\nJ\3\2\2\2\fV\3\2\2\2\16q\3\2\2\2\20\25\5\6\4\2\21\25"+
		"\5\4\3\2\22\25\5\b\5\2\23\25\5\n\6\2\24\20\3\2\2\2\24\21\3\2\2\2\24\22"+
		"\3\2\2\2\24\23\3\2\2\2\25\30\3\2\2\2\26\24\3\2\2\2\26\27\3\2\2\2\27\34"+
		"\3\2\2\2\30\26\3\2\2\2\31\33\7\r\2\2\32\31\3\2\2\2\33\36\3\2\2\2\34\32"+
		"\3\2\2\2\34\35\3\2\2\2\35\37\3\2\2\2\36\34\3\2\2\2\37 \7\2\2\3 \3\3\2"+
		"\2\2!\"\7\n\2\2\"#\7\16\2\2#$\7\r\2\2$/\b\3\1\2%)\7\t\2\2&(\n\2\2\2\'"+
		"&\3\2\2\2(+\3\2\2\2)\'\3\2\2\2)*\3\2\2\2*,\3\2\2\2+)\3\2\2\2,.\7\r\2\2"+
		"-%\3\2\2\2.\61\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\5\3\2\2\2\61/\3\2\2\2"+
		"\62\63\7\13\2\2\63\64\7\f\2\2\64\65\7\16\2\2\65\66\7\r\2\2\66A\b\4\1\2"+
		"\67;\7\t\2\28:\n\2\2\298\3\2\2\2:=\3\2\2\2;9\3\2\2\2;<\3\2\2\2<>\3\2\2"+
		"\2=;\3\2\2\2>@\7\r\2\2?\67\3\2\2\2@C\3\2\2\2A?\3\2\2\2AB\3\2\2\2B\7\3"+
		"\2\2\2CA\3\2\2\2DE\7\3\2\2EF\7\4\2\2FG\7\5\2\2GH\7\r\2\2HI\b\5\1\2I\t"+
		"\3\2\2\2JK\7\6\2\2KL\7\t\2\2LM\7\16\2\2MN\7\r\2\2NS\b\6\1\2OR\5\f\7\2"+
		"PR\5\16\b\2QO\3\2\2\2QP\3\2\2\2RU\3\2\2\2SQ\3\2\2\2ST\3\2\2\2T\13\3\2"+
		"\2\2US\3\2\2\2VZ\7\t\2\2WY\n\2\2\2XW\3\2\2\2Y\\\3\2\2\2ZX\3\2\2\2Z[\3"+
		"\2\2\2[]\3\2\2\2\\Z\3\2\2\2]^\7\r\2\2^\r\3\2\2\2_`\7\7\2\2`a\7\16\2\2"+
		"ab\7\b\2\2bc\7\16\2\2cd\7\r\2\2de\3\2\2\2ef\b\b\1\2fr\b\b\1\2gh\7\7\2"+
		"\2hi\7\16\2\2im\7\t\2\2jl\n\2\2\2kj\3\2\2\2lo\3\2\2\2mk\3\2\2\2mn\3\2"+
		"\2\2np\3\2\2\2om\3\2\2\2pr\7\r\2\2q_\3\2\2\2qg\3\2\2\2r\17\3\2\2\2\16"+
		"\24\26\34)/;AQSZmq";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}