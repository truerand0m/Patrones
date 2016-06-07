yacc -dv -Jsemantic=Node Arith.y && java -jar "C:\Users\Dell\Documents\Clases\Compi\jflex-1.6.1.tar\jflex-1.6.1\lib\jflex-1.6.1.jar"  Flexer.flex
Reading "Flexer.flex"
Constructing NFA : 344 states in NFA
Converting NFA to DFA : 
........................................................................................................................................................................................
196 states before minimization, 111 states in minimized DFA
Old file "Flexer.java" saved as "Flexer.java~"
Writing code to "Flexer.java"
javac Parser.java && javac Flexer.java
java Parser primeraPrac.py
loop
yyn:0  state:0  yychar:-1
 next yychar:277
state 0, shifting to state 8
loop
yyn:0  state:8  yychar:-1
 next yychar:257
state 8, shifting to state 1
loop
state 1, reducing 1 by rule 69 (atom : CADENA)
reduce
after reduction, shifting from state 8 to state 34
loop
yyn:0  state:34  yychar:-1
 next yychar:300
reduce
state 34, reducing 1 by rule 67 (power : atom)
reduce
after reduction, shifting from state 8 to state 33
loop
state 33, reducing 1 by rule 66 (factor : power)
reduce
after reduction, shifting from state 8 to state 32
loop
yyn:0  state:32  yychar:300
reduce
state 32, reducing 1 by rule 59 (term : factor)
reduce
after reduction, shifting from state 8 to state 31
loop
yyn:0  state:31  yychar:300
reduce
state 31, reducing 1 by rule 56 (arith_expr : term)
reduce
after reduction, shifting from state 8 to state 30
loop
state 30, reducing 1 by rule 54 (and_expr : arith_expr)
reduce
after reduction, shifting from state 8 to state 29
loop
yyn:0  state:29  yychar:300
reduce
state 29, reducing 1 by rule 52 (xor_expr : and_expr)
reduce
after reduction, shifting from state 8 to state 28
loop
yyn:0  state:28  yychar:300
reduce
state 28, reducing 1 by rule 50 (expr : xor_expr)
reduce
after reduction, shifting from state 8 to state 27
loop
yyn:0  state:27  yychar:300
reduce
state 27, reducing 1 by rule 39 (comparison : expr)
reduce
after reduction, shifting from state 8 to state 26
loop
state 26, reducing 1 by rule 38 (not_test : comparison)
reduce
after reduction, shifting from state 8 to state 25
loop
state 25, reducing 1 by rule 35 (and_test : not_test)
reduce
after reduction, shifting from state 8 to state 24
loop
yyn:0  state:24  yychar:300
reduce
state 24, reducing 1 by rule 33 (or_test : and_test)
reduce
after reduction, shifting from state 8 to state 23
loop
yyn:0  state:23  yychar:300
reduce
state 23, reducing 1 by rule 32 (test : or_test)
reduce
after reduction, shifting from state 8 to state 39
loop
state 39, reducing 1 by rule 30 (auxprint : test)
reduce
after reduction, shifting from state 8 to state 40
loop
yyn:0  state:40  yychar:300
reduce
state 40, reducing 2 by rule 28 (print_stmt : PRINT auxprint)
reduce
after reduction, shifting from state 0 to state 22
loop
state 22, reducing 1 by rule 24 (small_stmt : print_stmt)
reduce
after reduction, shifting from state 0 to state 20
loop
state 20, reducing 1 by rule 21 (auxsimple : small_stmt)
reduce
after reduction, shifting from state 0 to state 19
loop
yyn:0  state:19  yychar:300
state 19, shifting to state 46
loop
yyn:0  state:46  yychar:-1
 next yychar:259
state 46, shifting to state 74
loop
state 74, reducing 3 by rule 19 (simple : auxsimple PUNTOCOMA NEWLINE)
reduce
after reduction, shifting from state 0 to state 14
loop
state 14, reducing 1 by rule 10 (stmt : simple)
reduce
after reduction, shifting from state 0 to state 13
loop
state 13, reducing 1 by rule 8 (auxsuite : stmt)
reduce
after reduction, shifting from state 0 to state 12
loop
yyn:0  state:12  yychar:-1
 next yychar:0
reduce
state 12, reducing 1 by rule 2 (input : auxsuite)
Llegue a input
*****************************
------------Accept-----------
Soy nodo SStmtNode:
Nodos hijos: 1
[
PrintNode: 
Soy hoja cadena::oye tranquilo viejo
]
--------------------------Arbol---------------------
(PrintNode: C:oye tranquilo viejo)
----------------------------------------------------
------------<SemanticAnalysis>-----------
Soy nodo SStmtNode:
Nodos hijos: 1
[
Soy nodo PrintNode:
Nodos hijos: 1
[
Soy nodo hoja con valor: oye tranquilo viejo
]
]
------------</SemanticAnalysis>-----------
------------<CodGen>-----------
.class public super Prueba
.super java/lang/Object
.method public static main ([Ljava/lang/String;)V

ldc "oye tranquilo viejo"
astore_2
getstatic java/lang/System/out Ljava/io/PrintStream;
aload_2
invokevirtual java/io/PrintStream/println (Ljava/lang/String;)V
return
.end method
------------</CodGen>-----------
reduce
After reduction, shifting from state 0 to state 11
