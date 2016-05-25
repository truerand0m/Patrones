%{
  import java.lang.Math;
  import java.io.*;
%}
/* YACC Declarations */
%token CADENA COMA
%token NEWLINE IDENTIFIER ENTERO REAL
%token BOOLEAN DEDENT INDENT
%token AND OR NOT FROM WHILE
%token FOR DEF AS ELIF ELSE IF
%token PRINT RETURN IN
%token MAS MENOS POR POTENCIA DIV  /* + | - | * | ** | / */
%token DIVENTERA MODULO LE GR LEQ /* // | % | < | > | <= */
%token GRQ EQUALS DIFF EQ PA /* >= | == | != | = | ( */
%token XOR ANDB ORB /* ^ | & | "|" */
%token PC DOBLEPUNTO PUNTOCOMA   /* ) | : | ; */
/* "<" ">" "<=" ">=" "==" "!=" "=" */
%token LE GR LEQ GRQ EQUALS DIFF EQ
/*     "^" "&"  "|" "and" "not in" "is not" "is" "not"  */
%token XOR ANDB ORB AND NOTIN ISNOT IS NOT
/* Grammar follows */
%%
/*
   Hace falta ver porque no regresa los identificadores como nodo,
   terminar hasta aqui, la gramatica y hacer pruebas
*/
input:
      | stmt {
            Visitor v = new PrintVisitor();
            System.out.println("Llegue a input");
            System.out.println("*****************************");
            System.out.println("------------Accept-----------");
            $1.accept(v);
            System.out.println("------------Print-----------");
            //$1.print();
            Printer p = new Printer($1);
            p.print();
            System.out.println("*****************************");
      }
      ;

//
/* suite: simple_stmt | NEWLINE INDENT stmt+ DEDENT */
suite:   simple  { $$ = $1; }
      |  NEWLINE INDENT auxsuite DEDENT
      ;

/* auxsuite es stmt+ */
auxsuite:   stmt           { $$ = $1; }
         |  auxsuite stmt  {
                              $1.addChild($2);
                              $$ = $1;
                           }
         ;

stmt: simple         { $$=$1;}
   |  compound_stmt  { $$=$1; }
   ;

compound_stmt: if_stmt     { $$=$1; }
            |  while_stmt  { $$=$1; }
            ;


if_stmt: IF test DOBLEPUNTO suite { new IfNode($1,$4); }
      |  IF test DOBLEPUNTO suite auxif {
                                          $$ = new IfNode($2,$4);
                                          $1.addChild($5);
                                       }
      ;

auxif:   ELIF test DOBLEPUNTO suite        { $$ = new IfNode($2,$4); }
      |  auxif ELIF test DOBLEPUNTO suite  { $1.addChild(new IfNode($3,$5)); }
      ;

while_stmt: WHILE test DOBLEPUNTO suite {$$ = new WhileNode($2,$4); }
          ;

/* simple_stmt: small_stmt (';' small_stmt)* [';'] NEWLINE */
simple:  auxsimple PUNTOCOMA NEWLINE   {  $$ = $1; }
      |  auxsimple NEWLINE             {  $$ = $1; }


auxsimple:     small_stmt                       {  $$ = new SStmtNode($1); }
            |  auxsimple PUNTOCOMA small_stmt   {
                                                   $1.addChild($3);
                                                   $$ = $1;
                                                }

small_stmt: expr_stmt { $$ = $1; }
         |  print_stmt { $$ = $1; }
         ;

expr_stmt:    test { $$ = $1; }
      |  test   EQ  test  { $$ = new EXPRN($1,$3);}
      ;

print_stmt: PRINT                 { $$ = new PrintNode(); }
         |  PRINT auxprint        { $$ = $2; }
         |  PRINT '>>' auxprint   { $$ = $3; }
         ;

auxprint:   test              { $$=new PrintNode($1); }
         |  auxprint COMA test{
                                 $1.addChild($3);
                                 $$ = $1;
                              }
         ;

/* test: or_test */
test: or_test  { $$ = $1; }
    ;
/* or_test: and_test ('or' and_test)est */
or_test: and_test { $$ = $1; }
      |  or_test OR and_test { $$ = new OrNode($1,$3); }
      ;
/* falta el otro and, and_test */
and_test:   not_test             { $$=$1; }
         |  and_test AND not_test {
                                    /* Posible error */
                                    $1.addChild($3);
                                    $$ = $1;
                                 }
         ;
/* falta el not not_test */
not_test: comparison { $$= $1; }

/* faltan reglas {} */
comparison: expr { $$=$1; }
         |  expr auxcomp
         ;

auxcomp: comp_op expr
      |  auxcomp comp_op expr
      ;

comp_op: LE | GR | EQUALS | GRQ | LEQ
      |  DIFF | IN | NOTIN | IS | ISNOT
      ;

expr:    xor_expr           { $$= $1;}
      |  expr ORB xor_expr { $$ = new ExprNode($1,$3);}
      ;

xor_expr:   and_expr                { $$=$1; }
         |  xor_expr XOR and_expr   { $$ = new XorNode($1,$3); }
         ;

/* and_expr: arith_expr ('&' arith_expr) */
and_expr:   arith_expr { $$ = $1; }
         |  and_expr ANDB arith_expr { $$ = new AndNode($1,$3); }
         ;

/* arith_expr: term (('+'|'-') term)* */
arith_expr: term { $$ = $1; }
         |  term MAS arith_expr { $$ = new ArithNode($1,EnumOp.MAS,$3);}
         |  term MENOS arith_expr { $$ = new ArithNode($1,EnumOp.MENOS,$3); }
         ;
/* term: factor (('*'|'/'|'%'|'//') factor)* */
term: factor               { $$ = $1; }
   |  factor POR term      { $$ = new TermNodeX($1,EnumOp.POR,$3); }
   |  factor DIV term      { $$ = new TermNodeX($1,EnumOp.DIV,$3); }
   |  factor MODULO term      { $$ = new TermNodeX($1,EnumOp.MODULO,$3); }
   |  factor DIVENTERA term      { $$ = new TermNodeX($1,EnumOp.DIVENTERA,$3); }
   ;
/*    ('+'|'-') factor | power   */
factor:  MAS factor { $$ = new FactorNode(EnumOp.MAS,$2); }
      |  MENOS factor  { $$ = new FactorNode(EnumOp.MENOS,$2); }
      |  power { $$ = $1; }
      ;
/* power: atom ['**' factor] */
power:   atom                    { $$ = $1; }
      |  atom POTENCIA factor    { $$ = new PowerNode($1,$3); }
      ;

/* atom: IDENTIFIER | INTEGER | STRING | FLOAT */
atom:   CADENA            {$$ = $1;}
      | ENTERO            {$$ = $1;}
      | REAL              {$$ = $1;}
      | IDENTIFIER        {$$ = $1;}
;
%%
/* a reference to the lexer object */
private Flexer lexer;

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
    lexer = new Flexer(r, this);
    yydebug = true;
}

/* that's how you use the parser */
public static void main(String args[]) throws IOException {
    Parser yyparser = new Parser(new FileReader(args[0]));
    yyparser.yyparse();
}
