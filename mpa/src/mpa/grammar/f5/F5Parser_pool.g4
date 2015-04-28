parser grammar F5Parser_pool;

options {
   tokenVocab = F5Lexer;
}

pool_stanza
:
   POOL name = WORD OPEN_BRACE NEWLINE   
   (
      pool_monitor_substanza
      | pool_other_substanza
   )* CLOSE_BRACE NEWLINE
;

pool_other_substanza
:
   (
      LB
      | MEMBER
   ) ~NEWLINE* NEWLINE
;

pool_monitor_substanza
:
   MONITOR ALL 
   ( 
      pm_monitor ( AND pm_monitor )+
      | MIN NUMBER OF pm_monitor+
      | protocols
   ) NEWLINE
;

pm_monitor
:
   name = WORD
;