parser grammar AristaGrammar;

import
AristaGrammar_interface, AristaGrammar_acl;

options {
   tokenVocab = AristaLexer;
}

@header {
//package demo;
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
