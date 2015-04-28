parser grammar F5Parser_profile;

options {
   tokenVocab = F5Lexer;
}

profile_stanza
:
   PROFILE profile_protocols name = WORD OPEN_BRACE NEWLINE
   CLOSE_BRACE NEWLINE
;

profile_protocols
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
      | ICAP
      | IPOTHER
      | MBLB
      | MSSQL
      | NTLM
      | ONE_CONNECT
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
