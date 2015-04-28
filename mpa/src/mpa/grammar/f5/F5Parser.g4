parser grammar F5Parser;

import F5Parser_ltm, F5Parser_pool, F5Parser_profile, F5Parser_virtual, F5Parser_monitor;

options {
   superClass = 'mpa.grammar.MpaParser';
   tokenVocab = F5Lexer;
}

@header {
package mpa.grammar.f5;
}

@members {
}

f5_configuration
:
   (      
      ltm_stanza
      | monitor_stanza
      | pool_stanza
      | profile_stanza
      | virtual_stanza
   )* NEWLINE* EOF
;



protocols
:
   (
      CERTIFICATE_AUTHORITY
      | CLASSIFICATION
      | CLIENT_SSL
      | CLIENTSSL
      | DIAMETER
      | DNS
      | FASTHTTP
      | FASTL4
      | FIX
      | FTP
      | GTP
      | HTML
      | HTTP
      | HTTPCLASS
      | HTTP_COMPRESSION
      | HTTPS
      | ICAP
      | IPOTHER
      | MBLB
      | MSSQL
      | NTLM
      | ONE_CONNECT
      | ONECONNECT
      | PERSIST
      | PPTP
      | QOE
      | RADIUS
      | REQUEST_ADAPT
      | REQUEST_LOG
      | RESPONSE_ADAPT
      | REWRITE
      | RTSP
      | SCTP
      | SERVER_SSL
      | SERVERSSL
      | SIP
      | SMTP
      | SMTPS
      | SOCKS
      | SPDY
      | STATISTICS
      | STREAM
      | TCP
      | UDP
      | WEB_ACCELERATION
      | XML
   )
;
