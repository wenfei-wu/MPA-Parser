parser grammar QuantaGrammar;

import
QuantaGrammar_interface, QuantaGrammar_acl;

options {
   superClass = 'mpa.grammar.MpaParser';
   tokenVocab = QuantaLexer;
}

@header {
package mpa.grammar.quanta;
}

@members {
}

quanta_configuration
:
   (
      acl_stanza
      | if_stanza
   )* NEWLINE* EOF
;
