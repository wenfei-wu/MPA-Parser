parser grammar CiscoGrammar;

import
CiscoGrammarCommonParser, CiscoGrammar_interface, CiscoGrammar_ospf, CiscoGrammar_bgp, CiscoGrammar_acl, CiscoGrammar_routemap;

options {
   tokenVocab = CiscoGrammarCommonLexer;
}

@header {
//package demo;
}

@members {
}

cisco_configuration
:
   (
      acl_stanza
      | interface_stanza
      | mstp_configuration_stanza
      | null_stanza
      | route_map_stanza
      | router_bgp_stanza
      | router_ospf_stanza
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
   ) ~NEWLINE* NEWLINE
;

udld_stanza
:
   UDLD AGGRESSIVE NEWLINE
;

