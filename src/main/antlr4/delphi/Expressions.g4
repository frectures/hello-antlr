grammar Expressions;

start : expression EOF ;

expression : NUMBER
| parExpression
| '-' expression
| expression ('*' | '/') expression
| expression ('+' | '-') expression ;

parExpression : '(' expression ')' ;

NUMBER : [0-9]+ ;

WS : [ \t\r\n]+ -> skip;
