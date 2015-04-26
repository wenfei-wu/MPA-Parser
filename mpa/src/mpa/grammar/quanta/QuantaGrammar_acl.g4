parser grammar QuantaGrammar_acl;

options {
   tokenVocab = QuantaLexer;
}

acl_stanza
:
	MANAGEMENT ACCESS_LIST aclName = NAME NEWLINE
	//{System.out.println("ACL found: " + _localctx.aclName.getText()); }
	(ANYTHING ~NEWLINE* NEWLINE)*
;
