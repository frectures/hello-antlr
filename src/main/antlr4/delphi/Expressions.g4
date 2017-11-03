grammar Expressions;

start : expression EOF ;

expression
: NUMBER                                                  # number
| '(' expression ')'                                      # parenthesized
| '-' expression                                          # negate
| left=expression operator=('*' | '/') right=expression   # binary
| left=expression operator=('+' | '-') right=expression   # binary
;

NUMBER : [0-9]+ ;

WS : [ \t\r\n]+ -> skip;
