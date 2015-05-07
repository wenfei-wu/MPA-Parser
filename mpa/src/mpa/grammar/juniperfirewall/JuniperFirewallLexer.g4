lexer grammar JuniperFirewallLexer;

@header {
package mpa.grammar.juniperfirewall;
}

options {
superClass = 'mpa.grammar.MpaLexer';
}

SET
:
    'set'
;

SERVICE
:
    'service'
;

PROTOCOL
:
    'protocol'
;

SRC_PORT
:
    'src-port'
;

DST_PORT
:
    'dst-port'
;

ZONE
:
    'zone'
;

VROUTER
:
    'vrouter'
;

INTERFACE
:
    'interface'
;

ID
:
    'id'
;

TAG
:
    'TAG'
;

ADDRESS
:
    'address'
;

GROUP
:
    'group'
;

ADD
:
    'add'
;

POLICY
:
    'policy'
;

FROM
:
    'from'
;

TO
:
    'to'
;

SRC_ADDRESS
:
    'src-address'
;

DST_ADDRESS
:
    'dst-address'
;

GLOBAL
:
    'global'
;

TYPE
:
    'type'
;

CODE
:
    'code'
;

NEWLINE
:
   [\n]
;

NAME
:
   ('-'|[a-zA-Z0-9]|'/'|[\.]|[\:]|["]|[\:] | [-] | ['/'] | [\.] | [@] | [!] | ["] | ['['] | ['('] | ['_'] | [\]] | [#] | [')']| ['+'] | ['='] | ['*'])+
;

WS:
   [ \t]+ -> skip;

