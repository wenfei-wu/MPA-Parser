parser grammar AristaGrammar_spanningtree;

options {
   tokenVocab = AristaLexer;
}

spanning_tree_stanza
:
	SPANNING_TREE MODE MSTP NEWLINE
	{System.out.println("MSTP enabled");}
	
;
