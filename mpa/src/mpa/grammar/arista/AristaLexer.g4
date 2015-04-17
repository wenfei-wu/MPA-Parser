lexer grammar AristaLexer;

options {
   superClass = 'mpa.grammar.MpaLexer';
}

@header {
package mpa.grammar.arista;
}

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
	| [\:] | [-] | ['/'] | [\.]
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
   ('-'|[a-zA-Z0-9]|'/')+
;

WS:
   [ \t]+ -> skip;





