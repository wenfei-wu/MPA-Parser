parser grammar F5Parser;

import F5Parser_ltm, F5Parser_pool, F5Parser_profile, F5Parser_virtual, F5Parser_monitor;

options {
   superClass = 'mpa.grammar.MpaParser';
   tokenVocab = F5Lexer;
}

@header {
package mpa.grammar.f5;
}

@members {
}

f5_configuration
:
   (      
      ltm_stanza
      | monitor_stanza
      | pool_stanza
      | profile_stanza
      | virtual_stanza
   )* NEWLINE* EOF
;


