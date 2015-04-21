lexer grammar QuantaLexer;

@header {
package mpa.grammar.quanta;
}

options {
superClass = 'mpa.grammar.MpaLexer';
}

ANYTHING
:
	'description'
	|'load-interval'
	|'shutdown'
	|'ip address'
	|'addport'
	|'service-policy'
	| 'set'
	| 'spanning-tree'
	| 'vlan'
	| 'lldp'
	| 'no'
	| 'classofservice'
	| 'snmp-server'
	| 'sflow'		
	| [\:] | [-] | [_] | [,] | ["] | ['/'] | [\.]
;

UDLD
:
	'udld'
;

LACP
:
	'lacp'
;

INTERFACE
:
   'interface'
;

MANAGEMENT
:
   'management'
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
   ('-' | [a-zA-Z0-9] | '/' | '"')+
;

WS:
   [ \t]+ -> skip;





