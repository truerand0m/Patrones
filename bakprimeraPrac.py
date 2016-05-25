2
#while x : print y;
#simple_stmt: small_stmt (';' small_stmt)* [';'] NEWLINE
#small_stmt: (expr_stmt | print_stmt )
#expr_stmt: test [('=' (test))]

#suite: simple_stmt | NEWLINE INDENT stmt+ DEDENT
#while_stmt: 'while' test ':' suite
#test: or_test
#or_test: and_test ('or' and_test)*
#and_test: not_test ('and' not_test)*
#not_test: 'not' not_test | comparison

#comparison: expr (comp_op expr)*
#comp_op: '<'|'>'|'=='|'>='|'<='|'<>'|'!='
#         |'in'|'not' 'in'|'is'|'is' 'not'


#expr: xor_expr ('|' xor_expr)*
#xor_expr: and_expr ('^' and_expr)*
#and_expr: arith_expr ('&' arith_expr)*

#arith_expr: term (('+'|'-') term)*
#term: factor (('*'|'/'|'%'|'//') factor)*
#factor: ('+'|'-') factor | power
#power: atom ['**' factor]
#atom: NAME | INTEGER | STRING | FLOAT)


#print y
#while_stmt: 'while' test ':' suite
#print x
#print "hello" , "world" , "this is a test"
i = (3 + 4); z = (7**3);

#4.3
#NAME | INTEGER | STRING | FLOAT)
#7 = (5 ** 3)
#5 & 2
#+3 * +4 + 8 - 7 & 5 + 2 | 3 = (1)
#3 - 5 +2
#+3 * +4 + 8 - 7
#5
#+3 * +4 ** 7 + 8 - 7
#5
#+3 * +4 ** 7 + 8 - 7
    #a -> +5 * 5 ** 3 +  3 * 1 + 1
