lexer grammar AristaLexer;

@header {
package mpa.grammar.arista;
}

@members {
boolean enableDEC = true;
}

options {
superClass = 'mpa.grammar.MpaLexer';
}

ACCESS_LIST
:
   'access-list'
;

BGP
:
    'bgp'
;

INTERFACE
:
   'interface'
;

IP
:
   'ip'
;

MODE
:
	'mode'
;

MSTP
:
	'mstp'
;


NEIGHBOR
:
    'neighbor'
;

OSPF
:
    'ospf'
;

REMOTE_AS
:
    'remote-as'
;

ROUTE_MAP
:
    'route-map'
;

ROUTER:
	'router'
;


SPANNING_TREE
:
    'spanning-tree'
;


ANYTHING
:
	'description'
	| 'load-interval'
	| 'mtu'
	| 'no'
	| 'ipv6'
	| 'port-channel'
	| 'ip address'
	| 'ip pim'
	| 'shutdown'
	| 'ip address'
	| 'channel-group'
	| 'vrf'
	| 'maximum-paths'
	| 'aggregate-address'
	| 'redistribute'
	| 'address-family'
	| 'network'
	| 'route-map'
	| 'maximum-routes'
	| 'update-source'
	| 'activate'
        | 'logging'
        | 'speed'
        | 'password'
        | 'peer-group'
	| [\:] | [-] | ['/'] | [\.] | [@] | [!] | ["] | ['['] | ['('] | ['_'] | [\]] | [#] | [')']
        | ['+'] | ['=']
;

DEC
:
   F_Digit
   {enableDEC}?

   F_Digit*
;

NEWLINE
:
   [\n]
;

NAME
:
   ('-'|[a-zA-Z0-9]|'/'|[\.]|[\:])+
;

WS:
   [ \t]+ -> skip;

fragment
F_Digit
:
   '0' .. '9'
;
