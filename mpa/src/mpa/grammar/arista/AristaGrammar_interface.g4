parser grammar AristaGrammar_interface;

options {
   tokenVocab = AristaLexer;
}

if_stanza
:
    INTERFACE iname = NAME NEWLINE
	//{System.out.println("Interface found: " + _localctx.iname.getText()); }
	(ANYTHING ~NEWLINE* NEWLINE)*
;
