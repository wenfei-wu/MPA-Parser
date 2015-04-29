parser grammar QuantaGrammar_vlan;

options {
   tokenVocab = QuantaLexer;
}

vlan_declared
:
	VLAN_NAME vlanNumber = NAME (~NEWLINE)* NEWLINE
;