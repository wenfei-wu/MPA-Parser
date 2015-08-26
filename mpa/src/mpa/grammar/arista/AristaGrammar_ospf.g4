parser grammar AristaGrammar_ospf;

options {
   tokenVocab = AristaLexer;
}

router_ospf_stanza
:
    ROUTER OSPF procnum = DEC NEWLINE	
	(anything_else)*
;

anything_else
:
	(ANYTHING ~NEWLINE* NEWLINE)
;
 