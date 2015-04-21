// Generated from /afs/cs.wisc.edu/u/j/m/jmishra/mpa-git/cs740/mpa/src/mpa/grammar/quanta/QuantaLexer.g4 by ANTLR 4.4

package mpa.grammar.quanta;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QuantaLexer extends mpa.grammar.MpaLexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ANYTHING=1, UDLD=2, LACP=3, INTERFACE=4, MANAGEMENT=5, ACCESS_LIST=6, 
		NEWLINE=7, NAME=8, WS=9;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'"
	};
	public static final String[] ruleNames = {
		"ANYTHING", "UDLD", "LACP", "INTERFACE", "MANAGEMENT", "ACCESS_LIST", 
		"NEWLINE", "NAME", "WS"
	};


	public QuantaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "QuantaLexer.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\13\u00c8\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\u008e\n"+
		"\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\6\t\u00be\n\t\r\t\16\t"+
		"\u00bf\3\n\6\n\u00c3\n\n\r\n\16\n\u00c4\3\n\3\n\2\2\13\3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\3\2\6\b\2$$)).\61<<^^aa\3\2\f\f\7\2$$//\61;"+
		"C\\c|\4\2\13\13\"\"\u00d7\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\3"+
		"\u008d\3\2\2\2\5\u008f\3\2\2\2\7\u0094\3\2\2\2\t\u0099\3\2\2\2\13\u00a3"+
		"\3\2\2\2\r\u00ae\3\2\2\2\17\u00ba\3\2\2\2\21\u00bd\3\2\2\2\23\u00c2\3"+
		"\2\2\2\25\26\7f\2\2\26\27\7g\2\2\27\30\7u\2\2\30\31\7e\2\2\31\32\7t\2"+
		"\2\32\33\7k\2\2\33\34\7r\2\2\34\35\7v\2\2\35\36\7k\2\2\36\37\7q\2\2\37"+
		"\u008e\7p\2\2 !\7n\2\2!\"\7q\2\2\"#\7c\2\2#$\7f\2\2$%\7/\2\2%&\7k\2\2"+
		"&\'\7p\2\2\'(\7v\2\2()\7g\2\2)*\7t\2\2*+\7x\2\2+,\7c\2\2,\u008e\7n\2\2"+
		"-.\7u\2\2./\7j\2\2/\60\7w\2\2\60\61\7v\2\2\61\62\7f\2\2\62\63\7q\2\2\63"+
		"\64\7y\2\2\64\u008e\7p\2\2\65\66\7k\2\2\66\67\7r\2\2\678\7\"\2\289\7c"+
		"\2\29:\7f\2\2:;\7f\2\2;<\7t\2\2<=\7g\2\2=>\7u\2\2>\u008e\7u\2\2?@\7c\2"+
		"\2@A\7f\2\2AB\7f\2\2BC\7r\2\2CD\7q\2\2DE\7t\2\2E\u008e\7v\2\2FG\7u\2\2"+
		"GH\7g\2\2HI\7t\2\2IJ\7x\2\2JK\7k\2\2KL\7e\2\2LM\7g\2\2MN\7/\2\2NO\7r\2"+
		"\2OP\7q\2\2PQ\7n\2\2QR\7k\2\2RS\7e\2\2S\u008e\7{\2\2TU\7u\2\2UV\7g\2\2"+
		"V\u008e\7v\2\2WX\7u\2\2XY\7r\2\2YZ\7c\2\2Z[\7p\2\2[\\\7p\2\2\\]\7k\2\2"+
		"]^\7p\2\2^_\7i\2\2_`\7/\2\2`a\7v\2\2ab\7t\2\2bc\7g\2\2c\u008e\7g\2\2d"+
		"e\7x\2\2ef\7n\2\2fg\7c\2\2g\u008e\7p\2\2hi\7n\2\2ij\7n\2\2jk\7f\2\2k\u008e"+
		"\7r\2\2lm\7p\2\2m\u008e\7q\2\2no\7e\2\2op\7n\2\2pq\7c\2\2qr\7u\2\2rs\7"+
		"u\2\2st\7q\2\2tu\7h\2\2uv\7u\2\2vw\7g\2\2wx\7t\2\2xy\7x\2\2yz\7k\2\2z"+
		"{\7e\2\2{\u008e\7g\2\2|}\7u\2\2}~\7p\2\2~\177\7o\2\2\177\u0080\7r\2\2"+
		"\u0080\u0081\7/\2\2\u0081\u0082\7u\2\2\u0082\u0083\7g\2\2\u0083\u0084"+
		"\7t\2\2\u0084\u0085\7x\2\2\u0085\u0086\7g\2\2\u0086\u008e\7t\2\2\u0087"+
		"\u0088\7u\2\2\u0088\u0089\7h\2\2\u0089\u008a\7n\2\2\u008a\u008b\7q\2\2"+
		"\u008b\u008e\7y\2\2\u008c\u008e\t\2\2\2\u008d\25\3\2\2\2\u008d \3\2\2"+
		"\2\u008d-\3\2\2\2\u008d\65\3\2\2\2\u008d?\3\2\2\2\u008dF\3\2\2\2\u008d"+
		"T\3\2\2\2\u008dW\3\2\2\2\u008dd\3\2\2\2\u008dh\3\2\2\2\u008dl\3\2\2\2"+
		"\u008dn\3\2\2\2\u008d|\3\2\2\2\u008d\u0087\3\2\2\2\u008d\u008c\3\2\2\2"+
		"\u008e\4\3\2\2\2\u008f\u0090\7w\2\2\u0090\u0091\7f\2\2\u0091\u0092\7n"+
		"\2\2\u0092\u0093\7f\2\2\u0093\6\3\2\2\2\u0094\u0095\7n\2\2\u0095\u0096"+
		"\7c\2\2\u0096\u0097\7e\2\2\u0097\u0098\7r\2\2\u0098\b\3\2\2\2\u0099\u009a"+
		"\7k\2\2\u009a\u009b\7p\2\2\u009b\u009c\7v\2\2\u009c\u009d\7g\2\2\u009d"+
		"\u009e\7t\2\2\u009e\u009f\7h\2\2\u009f\u00a0\7c\2\2\u00a0\u00a1\7e\2\2"+
		"\u00a1\u00a2\7g\2\2\u00a2\n\3\2\2\2\u00a3\u00a4\7o\2\2\u00a4\u00a5\7c"+
		"\2\2\u00a5\u00a6\7p\2\2\u00a6\u00a7\7c\2\2\u00a7\u00a8\7i\2\2\u00a8\u00a9"+
		"\7g\2\2\u00a9\u00aa\7o\2\2\u00aa\u00ab\7g\2\2\u00ab\u00ac\7p\2\2\u00ac"+
		"\u00ad\7v\2\2\u00ad\f\3\2\2\2\u00ae\u00af\7c\2\2\u00af\u00b0\7e\2\2\u00b0"+
		"\u00b1\7e\2\2\u00b1\u00b2\7g\2\2\u00b2\u00b3\7u\2\2\u00b3\u00b4\7u\2\2"+
		"\u00b4\u00b5\7/\2\2\u00b5\u00b6\7n\2\2\u00b6\u00b7\7k\2\2\u00b7\u00b8"+
		"\7u\2\2\u00b8\u00b9\7v\2\2\u00b9\16\3\2\2\2\u00ba\u00bb\t\3\2\2\u00bb"+
		"\20\3\2\2\2\u00bc\u00be\t\4\2\2\u00bd\u00bc\3\2\2\2\u00be\u00bf\3\2\2"+
		"\2\u00bf\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\22\3\2\2\2\u00c1\u00c3"+
		"\t\5\2\2\u00c2\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4"+
		"\u00c5\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c7\b\n\2\2\u00c7\24\3\2\2"+
		"\2\7\2\u008d\u00bd\u00bf\u00c4\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}