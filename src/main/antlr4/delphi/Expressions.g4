grammar Expressions;

start : expression EOF ;

expression : NUMBER
| '-' expression
| expression ('*' | '/') expression
| expression ('+' | '-') expression ;

NUMBER : [0-9]+ ;

WS : [ \t\r\n]+ -> skip;
