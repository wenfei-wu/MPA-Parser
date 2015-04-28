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
   'fastl4'
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

LADP
:
   'ladp'
;

MESSAGE_ROUTING
:
   'message-routing'
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

NEWLINE
:
   F_NewlineChar+
;

WORD
:
   F_QuotedString
   | F_WordChar+
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
   [ \t\u000C\r\n]
;

fragment
F_WordChar
:
   ~[ \t\u000C\r\n;{}[\]"#()]
;