grammar Expressions;

start : expression EOF ;

expression
: NUMBER                              # number
| '(' expression ')'                  # parenthesized
| '-' expression                      # negate
| expression ('*' | '/') expression   # binary
| expression ('+' | '-') expression   # binary
;

NUMBER : [0-9]+ ;

WS : [ \t\r\n]+ -> skip;
