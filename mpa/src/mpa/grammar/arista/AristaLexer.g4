lexer grammar AristaLexer;

@header {
package mpa.grammar.arista;
}

options {
superClass = 'mpa.grammar.MpaLexer';
}

SPANNING_TREE
:
	'spanning-tree'
;

MODE
:
	'mode'
;

MSTP
:
	'mstp'
;

ROUTER:
	'router'
;

NEIGHBOR
:
	'neighbor'
;

REMOTE_AS
:
	'remote-as'
;

ANYTHING
:
	'description'
	|'load-interval'
	|'mtu'
	|'no'
	|'ipv6'
	|'port-channel'
	|'ip address'
	|'ip pim'
	|'shutdown'
	|'ip address'
	|'channel-group'
	|'vrf'
	|'bgp'
	|'maximum-paths'
	|'aggregate-address'
	|'redistribute'
	|'address-family'
	|'network'
	|'route-map'
	|'maximum-routes'
	|'update-source'
	|'activate'
	| [\:] | [-] | ['/'] | [\.] | [@] | [!]
;

INTERFACE
:
   'interface'
;

IP
:
   'ip'
;

ACCESS_LIST
:
   'access-list'
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

