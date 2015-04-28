parser grammar F5Parser_profile;

options {
   tokenVocab = F5Lexer;
}

profile_stanza
:
   PROFILE profile_protocols ( profile_protocols | name = WORD ) OPEN_BRACE NEWLINE
   CLOSE_BRACE NEWLINE
;

profile_protocols
:
   protocols
;
