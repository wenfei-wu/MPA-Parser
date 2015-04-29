parser grammar CitrixParser;

//import F5Parser_ltm, F5Parser_pool, F5Parser_profile, F5Parser_virtual, F5Parser_monitor;

options {
   superClass = 'mpa.grammar.MpaParser';
   tokenVocab = CitrixLexer;
}

@header {
package mpa.grammar.citrix;
}

@members {
}

parameter
:
   MIN
   | ( CIP_FORMAL_ARG ( DISABLED | ENABLED WORD ) )
   | ( 
       FORMAL_ARG 
       ( 
   	      WORD 
   		  | NUMBER 
   		  | IP_ADDRESS 
   		  | DISABLED
   		  | ENABLED
   	   )? 
   	 )
;

interface_parameters
:
   parameter* ( LACP_FORMAL_ARG a = ACTIVE )? parameter*
;

generic_parameters
:
   parameter* ( POLICYNAME_FORMAL_ARG policy = WORD )? parameter*
;

citrix_configuration
:
   (
      add_lb_monitor
      | add_policy
      | add_service
      | add_servicegroup
      | add_ssl_cipher
      | add_vlan
      | add_vserver
      | bind_lb_monitor
      | bind_vlan
      | bind_vserver
      | bind_servicegroup
      | bind_ssl_cipher
      | set_interface
   )* NEWLINE* EOF
;

add_lb_monitor
:
   ADD LB MONITOR name = WORD protocols ~NEWLINE* NEWLINE
;

add_policy
:
   ADD ( CMP | CS | DNS | RESPONDER | REWRITE | SSL ) 
   POLICY name = WORD ~NEWLINE* NEWLINE
;

add_service
:
   ADD SERVICE name = WORD generic_parameters NEWLINE
;

add_servicegroup
:
   ADD SERVICEGROUP name = WORD protocols generic_parameters NEWLINE
;

add_ssl_cipher
:
   ADD SSL CIPHER name = WORD ~NEWLINE* NEWLINE
;

add_vlan
:
   ADD VLAN id = NUMBER generic_parameters NEWLINE
;

vserver
:
   protocols ( IP_ADDRESS | IPV6_ADDRESS | STAR ) ( NUMBER | STAR )
;

add_vserver
:
   ADD ( LB | CS ) VSERVER name = WORD 
   vserver
   generic_parameters NEWLINE
;

bind_lb_monitor
:
   BIND LB MONITOR m = WORD sg = WORD NEWLINE
;

bind_vlan
:
   BIND VLAN id = NUMBER ~NEWLINE* NEWLINE
;

bind_vserver
:
   BIND ( LB | CS | SSL ) VSERVER name = WORD generic_parameters NEWLINE
;

bind_servicegroup
:
   BIND SERVICEGROUP name = WORD ~NEWLINE* NEWLINE
;

bind_ssl_cipher
:
   BIND SSL CIPHER vs = WORD VSERVER_FORMAL_ARG ORD c = WORD NEWLINE
;

protocols
:
   ANY
   | DNS
   | DNS_TCP
   | HTTP
   | HTTP_ECV
   | PING
   | SMTP
   | SSL
   | SSL_BRIDGE
   | TCP
   | TCP_ECV
   | UDP
   | UDP_ECV
;

set_interface
:
   SET INTERFACE name = WORD interface_parameters NEWLINE
;