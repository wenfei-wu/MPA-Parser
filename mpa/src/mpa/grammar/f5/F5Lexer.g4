lexer grammar F5Lexer;

@header {
package mpa.grammar.f5;
}

options {
superClass = 'mpa.grammar.MpaLexer';
}


OPEN_BRACE
:
   '{'
;

CLOSE_BRACE
:
   '}'
;


OPEN_BRACKET
:
   '['
;

CLOSE_BRACKET
:
   ']'
;

ADDRESS
:
   'address'
;

AND
:
   'and'
;

ANY
:
   'any'
;

CERTIFICATE_AUTHORITY
:
   'certificate-authority'
;

CLASSIFICATION
:
   'classification'
;

FASTHTTP
:
   'fasthttp'
;

FIX
:
   'fix'
;

GTP
:
   'gtp'
;

HTML
:
   'html'
;

HTTP_COMPRESSION
:
   'http-compression'
;

ICAP
:
   'icap'
;

IPOTHER
:
   'ipother'
;

MBLB
:
   'mblb'
;

NTLM
:
   'ntlm'
;

ONE_CONNECT
:
   'one-connect'
;

PPTP
:
   'pptp'
;

QOE
:
   'qoe'
;

REQUEST_ADAPT
:
   'request-adapt'
;

REQUEST_LOG
:
   'request-log'
;

RESPONSE_ADAPT
:
   'response-adapt'
;

REWRITE
:
   'rewrite'
;

RTSP
:
   'rtsp'
;

SCTP
:
   'sctp'
;

SERVER_SSL
:
   'server-ssl'
;

SMTPS
:
   'smtps'
;

SOCKS
:
   'socks'
;

SPDY
:
   'spdy'
;


STATISTICS
:
   'statistics'
;

STREAM
:
   'stream'
;

WEB_ACCELERATION
:
   'web-acceleration'
;

XML
:
   'xml'
;

LTM
:
   'ltm'
;

MONITOR
:
   'monitor'
;

POOL
:
   'pool'
;

PROFILE
:
   'profile'
;

PROFILES
:
   'profiles'
;

VIRTUAL
:
   'virtual'
;

LB
:
   'lb'
;

MEMBER
:
   'member'
;

ALL
:
   'all'
;

DEFAULTS
:
   'defaults'
;

AUTH
:
   'auth'
;

CLIENT_SSL
:
   'client-ssl'
;

CLIENTSSL
:
   'clientssl'
;

DATA_GROUP
:
   'data-group'
;

DEFAULT_NODE_MONITOR
:
   'default-node-monitor'
;

DIAMETER
:
   'diameter'
;

DNS
:
   'dns'
;

EXTERNAL
:
   'external'
;

FASTL4
:
   'fastL4' | 'fastl4'
;

FIREPASS
:
   'firepass'
;

FTP
:
   'ftp'
;

GATEWAY_ICMP
:
   'gateway-icmp'
;

GLOBAL_SETTINGS
:
   'global-settings'
;

HTTP
:
   'http'
;

HTTPCLASS
:
   'httpclass'
;

HTTPS
:
   'https'
;

ICMP
:
   'icmp'
;

IMAP
:
   'imap'
;

INBAND
:
   'inband'
;

LDAP
:
   'ldap'
;

MESSAGE_ROUTING
:
   'message-routing'
;

MIN
:
   'min'
;

MODULE_SCORE
:
   'module-score'
;

MSSQL
:
   'mssql'
;

MYSQL
:
   'mysql'
;

NNTP
:
   'nntp'
;

NODE
:
   'node'
;

NONE
:
   'none'
;

OF
:
   'of'
;

ONECONNECT
:
   'oneconnect'
;
ORACLE
:
   'oracle'
;

PERSIST
:
   'persist'
;

PERSISTENCE
:
   'persistence'
;

POLICY
:
   'policy'
;

POLICY_STRATEGY
:
   'policy-strategy'
;
POP3
:
   'pop3'
;

POSTGRESQL
:
   'postgresql'
;

RADIUS
:
   'radius'
;

RADIUS_ACCOUNTING
:
   'radius-accounting'
;

REAL_SERVER
:
   'real-server'
;

RPC
:
   'rpc'
;

RULE
:
   'rule'
;

SASP
:
   'sasp'
;

SCRIPTED
:
   'scripted'
;

SERVERSSL
:
   'serverssl'
;

SIP
:
   'sip'
;

SMB
:
   'smb'
;

SMTP
:
   'smtp'
;

SNAT
:
   'snat'
;

SNATPOOL
:
   'snatpool'
;

SNAT_TRANSLATION
:
   'snat-translation'
;

SNMP_DCA
:
   'snmp-dca'
;

SNMP_DCA_BASE
:
   'snmp-dca-base'
;

SOAP
:
   'soap'
;

TCP
:
   'tcp'
;

TCP_ECHO
:
   'tcp-echo'
;

TCP_HALF_OPEN
:
   'tcp-half-open'
;

UDP
:
   'udp'
;

VIRTUAL_ADDRESS
:
   'virtual-address'
;

VIRTUAL_LOCALTION
:
   'virtual-location'
;

WAP
:
   'wap'
;

WMI
:
   'wmi'
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

WORD
:
   F_QuotedString
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
F_PositiveDigit
:
   '1' .. '9'
;