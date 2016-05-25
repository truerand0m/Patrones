%{
  import java.lang.Math;
  import java.io.*;
%}
/* YACC Declarations */
%token NEWLINE
%token ENDMARKER
%token IF
%token ELIF
%token WHILE
%token FOR
%token IN
%token DEDENT
%token INDENT
%token OR
%token AND
%token NOT
%token MULT
%token DIV
%token PORC
%token DIVI
%token MAS
%token MENOS
%token NAME
%token INTEGER
%token STRING
%token FLOAT
%token POTENCIA

/* GRAMMAR FOLLOWS */
%%

file_input: 	ENDMARKER
			|	NEWLINE file_input
			|   stmt file_input
			;

stmt:		simple_stmt
		|	compound_stmt
		;

simple_stmt:		small_stmt NEWLINE
				| 	small_stmt ';' NEWLINE
				|	small_stmt auxsimple NEWLINE
				| 	small_stmt auxsimple ';' NEWLINE
				;

auxsimple:		';' small_stmt
			|	auxsimple ';' small_stmt
			;

small_stmt:		expr_stmt
			|	print_stmt
			;

expr_stmt:		test
			|	test '=' test
			;

print_stmt: 	'print' test
			|	'print' test ','
			|   'print' test auxprint
			|	'print' test auxprint ','
			|	'print' '>>' test
			|	'print' '>>' test auxprint
			|	'print' '>>' test auxprint ','
			;

auxprint:	 	',' test
			|	auxprint ',' test
			;

compound_stmt:		if_stmt
				| while_stmt
				| for_stmt
				;

if_stmt: 	IF test ':' suite
		|	IF test ':' suite  auxif
		;

auxif:		ELIF test ':' suite
		|	auxif ELIF test ':' suite
		;

while_stmt:	WHILE test ':' suite;

for_stmt: FOR expr IN test ':' suite

suite:		simple_stmt
		| 	NEWLINE INDENT auxstmt DEDENT
		;

auxstmt:	stmt
		|	auxstmt stmt
		;

test:	or_test	;

or_test:	and_test
		|	and_test auxor
		;

auxor:		OR and_test
		|	auxor OR and_test
		;

and_test:	not_test
		|	not_test auxand1
		;

auxand1:	AND not_test
		|	auxand1 AND not_test
		;

not_test:	NOT not_test
		|	comparison
		;

comparison:		expr
			|	expr auxcomp
			;

auxcomp:	comp_op expr
		|	auxcomp comp_op expr
		;

comp_op:	 '<'
			|'>'
			|'=='
			|'>='
			|'<='
			|'<>'
			|'!='
			|'in'
			|'not in'
			|'is'
			|'is not'
			;

expr:		xor_expr
		|	xor_expr auxexpr
		;

auxexpr:	'|' xor_expr
		|	auxexpr '|' xor_expr
		;

xor_expr:	and_expr
		|	and_expr auxxor
		;

auxxor:		'^' and_expr
		|	auxxor '^' and_expr
		;

and_expr:	shift_expr
		|	shift_expr auxandexpr2

auxandexpr2:	'&' shift_expr
			|	auxandexpr2 '&' shift_expr
			;

shift_expr:		arith_expr
			|	arith_expr auxshift
			;

auxshift:		'<<' arith_expr
			|	'>>' arith_expr
			|	auxshift '<<' arith_expr
			|	auxshift '>>' arith_expr
			;

arith_expr:		term
			|	term auxar
			;

auxar:		MAS term
		|	MENOS	term
		|	auxar MAS term
		|	auxar MENOS term
		;

term:		factor
		|	factor auxterm
		;

auxterm:	MULT factor
		|	DIV	 factor
		|	PORC factor
		|	DIVI factor
		|	auxterm MULT factor
		|	auxterm DIV factor
		|	auxterm PORC factor
		|	auxterm DIVI factor
		;

factor:		MAS factor
		|	MENOS factor
		|	power
		;

power:		atom
		|	atom POTENCIA factor
		;

atom:		NAME
		|	INTEGER
		|	STRING
		|	FLOAT
		;

%%
/* a reference to the lexer object */
private Yylex lexer;

/* interface to the lexer */
private int yylex () {
    int yyl_return = -1;
    try {
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
}

/* error reporting */
public void yyerror (String error) {
    System.err.println ("Error: " + error);
}

/* lexer is created in the constructor */
public Parser(Reader r) {
    lexer = new Yylex(r, this);
}

/* that's how you use the parser */
public static void main(String args[]) throws IOException {
    Parser yyparser = new Parser(new FileReader(args[0]));
    yyparser.yyparse();
}
