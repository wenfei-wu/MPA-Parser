parser grammar FlatJuniperParser;

import
FlatJuniper_common, FlatJuniper_firewall, FlatJuniper_interfaces, FlatJuniper_policy_options, FlatJuniper_protocols, FlatJuniper_routing_instances;

options {
   superClass = 'mpa.grammar.MpaParser';
   tokenVocab = FlatJuniperLexer;
}

@header {
package mpa.grammar.flatjuniper;
}

deactivate_line
:
   DEACTIVATE set_line_tail NEWLINE
;

flat_juniper_configuration
:
   NEWLINE?
   (
      deactivate_line
      | set_line
      | unset_line
   )+ NEWLINE? EOF
;

statement
:
   s_firewall
   | s_forwarding_options
   | s_interfaces
   | s_null
   | s_policy_options
   | s_protocols
   | s_routing_instances
   | s_routing_options
   | s_system
;

s_forwarding_options
:
   FORWARDING_OPTIONS
   ( 
      ( HELPERS BOOTP INTERFACE fo_helper_bootp_interface_tail )
      | ( DHCP_RELAY GROUP fo_dhcp_relay_group_tail )
      |  s_null_filler
   )
;

iface_vlan
:
   VARIABLE (PERIOD DEC)
;

fo_helper_bootp_interface_tail
:
   name = iface_vlan s_null_filler
;

fo_dhcp_relay_group_tail
:
   groupname = variable INTERFACE name = iface_vlan
;

s_groups
:
   GROUPS s_groups_named
;

s_groups_named
:
   name = variable s_groups_tail
;

s_groups_tail
:
// intentional blank

   | statement
;

s_null
:
   rit_null
;

s_system
:
   SYSTEM s_system_tail
;

s_system_tail
:
   st_host_name
   | st_null
;

s_version
:
   VERSION M_Version_VERSION_STRING
;

set_line
:
   SET set_line_tail NEWLINE
;

set_line_tail
:
   s_groups
   | statement
   | s_version
;

st_host_name
:
   HOST_NAME variable
;

st_null
:
   (
      ACCOUNTING
      | AUTHENTICATION_ORDER
      | BACKUP_ROUTER
      | DDOS_PROTECTION
      | DOMAIN_NAME
      | DOMAIN_SEARCH
      | LOGIN
      | NAME_SERVER
      | NTP
      | PORTS
      | ROOT_AUTHENTICATION
      | SERVICES
      | SYSLOG
      | TACPLUS_SERVER
      | TIME_ZONE
   ) s_null_filler
;

unset_line
:
   UNSET set_line_tail NEWLINE
;