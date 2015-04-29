lexer grammar CitrixLexer;

@header {
package mpa.grammar.citrix;
}

options {
superClass = 'mpa.grammar.MpaLexer';
}

MIN
:
   'MIN'
;

ACTIVE
:
   'active'
   | 'ACTIVE'
;

ADD
:
   'add'
;

ANY
:
   'ANY'
;

BIND
:
   'bind'
;

CIP_FORMAL_ARG
:
   '-cip'
;

CIPHER
:
   'cipher'
;

CMP
:
   'cmp'
;

CS
:
   'cs'
;

DISABLED
:
   'DISABLED'
;

DNS
:
   'dns' | 'DNS'
;

DNS_TCP
:
   'DNS_TCP'
;

ENABLED
:
   'ENABLED'
;

HTTP
:
   'HTTP'
;

HTTP_ECV
:
   'HTTP-ECV'
;

INTERFACE
:
   'interface'
;

LACP_FORMAL_ARG
:
   '-lacpMode'
;

LB
:
   'lb'
;

MONITOR
:
   'monitor'
;

ORD
:
   'ORD'
;

PING
:
   'PING'
;

POLICY
:
   'policy'
;

POLICYNAME_FORMAL_ARG
:
   '-policyName'
;

RESPONDER
:
   'responder'
;

REWRITE
:
   'rewrite'
;

SERVICE
:
   'service'
;

SERVICEGROUP
:
   'serviceGroup'
;

SET
:
   'set'
;

SMTP
:
   'SMTP'
;

SSL
:
   'ssl' | 'SSL'
;

SSL_BRIDGE
:
   'SSL_BRIDGE'
;

TCP
:
   'TCP'
;

TCP_ECV
:
   'TCP-ECV'
;

UDP
:
   'UDP'
;

UDP_ECV
:
   'UDP-ECV'
;

VLAN
:
   'vlan'
;

VSERVER
:
   'vserver'
;

VSERVER_FORMAL_ARG
:
   '-vServer'
;

STAR
:
   '*'
;

NUMBER
:
   F_Digit+
;

IP_ADDRESS
:
   F_DecByte '.'
   F_DecByte '.' F_DecByte '.' F_DecByte
;

IPV6_ADDRESS
:
   (
      (
         ':'
         ':'
         (
            (
               F_HexDigit+ ':'
            )* F_HexDigit+
         )?
      )
      |
      (
         F_HexDigit+
         ':' ':'?
      )+
   )
   (
      F_HexDigit+
   )?
;

FORMAL_ARG
:
   '-' F_WordChar+
;

WORD
:
   F_BraceString
   | F_QuotedString
   | F_WordChar+
;

NEWLINE
:
   F_NewlineChar+
;

WS
:
   F_WhitespaceChar+ -> channel(HIDDEN)
;

fragment
F_NewlineChar
:
   [\r\n]
;

fragment
F_QuotedString
:
   '"' ~'"'* '"'
;

fragment
F_BraceString
:
   '{' ~'}'* '}'
;

fragment
F_WhitespaceChar
:
   [ \t\u000C]
;

fragment
F_WordChar
:
   ~[ \t\u000C\r\n;{}[\]"#()]
;

fragment
F_DecByte
:
   (
      F_PositiveDigit F_Digit F_Digit
   )
   |
   (
      F_PositiveDigit F_Digit
   )
   | F_Digit
;

fragment
F_Digit
:
   ('0'..'9')
;

fragment
F_HexDigit
:
   (
      '0' .. '9'
      | 'a' .. 'f'
      | 'A' .. 'F'
   )
;

fragment
F_PositiveDigit
:
   '1' .. '9'
;