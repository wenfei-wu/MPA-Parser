parser grammar QuantaGrammar;

import
QuantaGrammar_interface, QuantaGrammar_acl;

options {
   tokenVocab = QuantaLexer;
}

@header {
//package demo;
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
