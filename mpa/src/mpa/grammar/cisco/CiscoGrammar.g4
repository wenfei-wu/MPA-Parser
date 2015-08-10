parser grammar CiscoGrammar;

import
CiscoGrammarCommonParser, CiscoGrammar_interface, CiscoGrammar_ospf, CiscoGrammar_bgp, CiscoGrammar_acl, CiscoGrammar_routemap;

options {
   superClass = 'mpa.grammar.MpaParser';
   tokenVocab = CiscoGrammarCommonLexer;
}

@header {
package mpa.grammar.cisco;
}


cisco_configuration
:
   (
      acl_stanza
      | interface_stanza
      | mstp_configuration_stanza
      | mstp_stanza
      | null_stanza
      | route_map_stanza
      | router_bgp_stanza
      | router_ospf_stanza
      | udld_stanza
      | vlan_stanza
   )* NEWLINE* EOF
;

mstp_instance_substanza
:
   INSTANCE d = DEC VLAN r = range NEWLINE
;

mstp_null_substanza
:
   (
      NAME
      | REVISION
   ) ~NEWLINE* NEWLINE
;

mstp_stanza
:
   SPANNING_TREE MODE MST NEWLINE
;

mstp_configuration_stanza
:
   SPANNING_TREE MST CONFIGURATION NEWLINE
   (
      mstp_instance_substanza
      | mstp_null_substanza
   )*
;

null_stanza
:
   NO? 
   (
      HARDWARE
      | LOGGING
      | SET
   ) ~NEWLINE* NEWLINE
;

udld_stanza
:
   UDLD AGGRESSIVE NEWLINE
;

vlan_stanza
:
   VLAN v = DEC NEWLINE
;
