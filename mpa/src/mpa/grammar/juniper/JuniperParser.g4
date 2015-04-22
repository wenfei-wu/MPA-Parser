parser grammar JuniperParser;

options {
   superClass = 'mpa.grammar.MpaParser';
   tokenVocab = JuniperLexer;
}

@header {
package mpa.grammar.juniper;
}

braced_clause
:
   OPEN_BRACE statement* CLOSE_BRACE
;

bracketed_clause
:
   OPEN_BRACKET word+ CLOSE_BRACKET
;

juniper_configuration
:
   statement+ EOF
;

statement
:
   INACTIVE? word+
   (
      braced_clause
      |
      (
         bracketed_clause terminator
      )
      | terminator
   )
;

terminator
:
   SEMICOLON
;

word
:
   WORD
;