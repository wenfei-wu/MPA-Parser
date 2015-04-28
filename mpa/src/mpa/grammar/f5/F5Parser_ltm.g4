parser grammar F5Parser_ltm;

options {
   tokenVocab = F5Lexer;
}

ltm_stanza
:
	LTM
	(
	   MONITOR ltm_monitor_tail
	   | POOL ltm_pool_tail
	   | PROFILE profile_protocols ltm_profile_tail
	   | VIRTUAL ltm_virtual_tail
	   | ltm_other_tail
	)? OPEN_BRACE NEWLINE
	ltm_substanza* CLOSE_BRACE NEWLINE
;

ltm_monitor_tail
:
    ltm_protocols name = WORD
;

ltm_pool_tail
:
    name = WORD
;

ltm_profile_tail
:
    name = WORD
;

ltm_virtual_tail
:
    name = WORD
;

ltm_substanza
:
    ~( CLOSE_BRACE | NEWLINE )* NEWLINE
;

ltm_protocols
:
   DIAMETER
   | DNS
   | EXTERNAL
   | FIREPASS
   | FTP
   | GATEWAY_ICMP
   | HTTP
   | HTTPS
   | ICMP
   | IMAP
   | INBAND
   | LADP
   | MODULE_SCORE
   | MSSQL
   | MYSQL
   | NNTP
   | NONE
   | ORACLE
   | POP3
   | POSTGRESQL
   | RADIUS
   | RADIUS_ACCOUNTING
   | REAL_SERVER
   | RPC
   | SASP
   | SCRIPTED
   | SIP
   | SMB
   | SMTP
   | SNMP_DCA
   | SNMP_DCA_BASE
   | SOAP
   | TCP
   | TCP_ECHO
   | TCP_HALF_OPEN
   | UDP
   | VIRTUAL_LOCALTION
   | WAP
   | WMI
;

ltm_other_tail
:
   (
       AUTH
       | DATA_GROUP
       | DEFAULT_NODE_MONITOR
       | DNS
       | GLOBAL_SETTINGS
       | MESSAGE_ROUTING
       | NODE
       | PERSISTENCE
       | POLICY
       | POLICY_STRATEGY
       | RULE
       | SNAT
       | SNATPOOL
       | SNAT_TRANSLATION
       | VIRTUAL_ADDRESS
    ) ~OPEN_BRACE*
;