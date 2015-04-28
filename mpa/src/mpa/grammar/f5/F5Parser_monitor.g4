parser grammar F5Parser_monitor;

options {
   tokenVocab = F5Lexer;
}

monitor_stanza
:
   MONITOR name = WORD OPEN_BRACE NEWLINE
   monitor_substanza*
   CLOSE_BRACE NEWLINE
;

monitor_substanza
:
   (
      DEFAULTS
   ) ~NEWLINE* NEWLINE
;