parser grammar AristaGrammar_acl;

options {
   tokenVocab = AristaLexer;
}

acl_stanza
:
	IP ACCESS_LIST aclName = NAME NEWLINE
	//{System.out.println("ACL found: " + _localctx.aclName.getText()); }
	(ANYTHING ~NEWLINE* NEWLINE)*
;
