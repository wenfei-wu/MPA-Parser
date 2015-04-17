parser grammar AristaGrammar;

import
AristaGrammar_interface, AristaGrammar_acl;

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
   )* NEWLINE* EOF
;
