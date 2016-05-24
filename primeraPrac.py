i
#4.3
#"holi" + "bye"
#NAME | INTEGER | STRING | FLOAT)
#7 = (5 ** 3)
#5 & 2
#[('=' (test))]
#+3 * +4 + 8 - 7 & 5 + 2 | 3 = (1)
#3 - 5 +2
#+3 * +4 + 8 - 7
#5

#+3 * +4 ** 7 + 8 - 7
#5
#+3 * +4 ** 7 + 8 - 7

#arith_expr: term (('+'|'-') term)*
#term: factor (('*'|'/'|'%'|'//') factor)*
#factor: ('+'|'-') factor | power
#power: atom ['**' factor]
#atom: NAME | INTEGER | STRING | FLOAT)

#a   ->  t1 + t2 - t3
#t1  ->  f1a * f1b
#f1a ->  + 5
#f1b ->  5 ** 3
#t1  ->  +5 * 5 ** 3
#
#t2  ->  f2a * f2b
#f2a ->  + f2a'
#f2a'->  3
#f2b ->  1
#t2  -> 3 * 1

#t3  ->  1
#a -> +5 * 5 ** 3 +  3 * 1 + 1
