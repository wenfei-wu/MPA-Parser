parser grammar QuantaGrammar;

import
QuantaGrammar_interface, QuantaGrammar_acl, QuantaGrammar_vlan, QuantaGrammar_mst;

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
      | vlan_declared
      | mst_instance
   )* NEWLINE* EOF
;
