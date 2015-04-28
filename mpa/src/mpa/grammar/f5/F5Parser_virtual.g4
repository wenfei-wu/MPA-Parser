parser grammar F5Parser_virtual;

options {
   tokenVocab = F5Lexer;
}


virtual_stanza
:
   VIRTUAL name = WORD OPEN_BRACE NEWLINE
   virtual_substanza*
   CLOSE_BRACE NEWLINE
;

virtual_substanza
:
   
   virtual_other_substanza
   | virtual_pool_substanza
   | virtual_profile_substanza
;

virtual_pool_substanza
:
   POOL name = WORD NEWLINE
;

virtual_profile_substanza
:
   PROFILE
   (
      name = WORD OPEN_BRACE CLOSE_BRACE 
      | OPEN_BRACE vp_profiles_substanza+ CLOSE_BRACE
   ) NEWLINE
;

vp_profiles_substanza
:
   name = WORD OPEN_BRACE CLOSE_BRACE NEWLINE
;

virtual_other_substanza
:
   (
      SNATPOOL
   ) ~NEWLINE* NEWLINE
;
