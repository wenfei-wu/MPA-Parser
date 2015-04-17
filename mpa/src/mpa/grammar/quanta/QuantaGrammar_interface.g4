parser grammar QuantaGrammar_interface;

options {
   tokenVocab = QuantaLexer;
}

if_stanza
:
    INTERFACE iname = NAME NEWLINE
	{System.out.println("Interface found: " + _localctx.iname.getText()); }
	(ANYTHING ~NEWLINE* NEWLINE)*
;
