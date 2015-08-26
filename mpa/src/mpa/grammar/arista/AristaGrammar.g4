parser grammar AristaGrammar;

import
AristaGrammar_interface, AristaGrammar_acl, AristaGrammar_spanningtree, AristaGrammar_bgp, AristaGrammar_ospf;

options {
   superClass = 'mpa.grammar.MpaParser';
   tokenVocab = AristaLexer;
}


@header {
package mpa.grammar.arista;
}

@members {
}

arista_configuration
:
   (
      acl_stanza
      	| if_stanza
		| spanning_tree_stanza
		| router_bgp_stanza
		| router_ospf_stanza
   )* NEWLINE* EOF
;
