parser grammar AristaGrammar_bgp;

options {
   tokenVocab = AristaLexer;
}

router_bgp_stanza
:
    ROUTER ANYTHING asnumber = NAME NEWLINE
	{System.out.println("AS NUMBER " + _localctx.asnumber.getText()); }
	(anything_else | neighbor)*
;

anything_else
:
	(ANYTHING ~NEWLINE* NEWLINE)
;

neighbor
:
	(NEIGHBOR ipaddress = NAME REMOTE_AS asnumber = NAME NEWLINE)
	{System.out.println("neighbor IP address " + _localctx.ipaddress.getText()); }
	{System.out.println("neighbor AS NUMBER " + _localctx.asnumber.getText()); }
	| (NEIGHBOR NAME ANYTHING ~NEWLINE* NEWLINE)
		
;
