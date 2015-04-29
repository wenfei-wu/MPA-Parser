parser grammar QuantaGrammar_mst;

options {
   tokenVocab = QuantaLexer;
}

mst_instance
:
	SPANNING_TREE_MST_VLAN vlanNumber = NAME vlanRange = NAME NEWLINE
;