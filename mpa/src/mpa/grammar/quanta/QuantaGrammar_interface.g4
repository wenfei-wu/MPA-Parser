parser grammar QuantaGrammar_interface;

options {
   tokenVocab = QuantaLexer;
}

if_stanza
:
    INTERFACE iname = NAME NEWLINE
	//{System.out.println("Interface found: " + _localctx.iname.getText()); }
	(udld | lacp | anything_else)*
;

udld
:
	(UDLD ~NEWLINE* NEWLINE)	
;

lacp
:
	(LACP ~NEWLINE* NEWLINE)
;

anything_else
:
	(ANYTHING ~NEWLINE* NEWLINE)
;
