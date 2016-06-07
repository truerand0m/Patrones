%{
  import java.lang.Math;
  import java.io.*;
  import java.util.*;
%}
/* ----------------------------------------------------------------------
   For the latest version  visit: https://github.com/truerand0m
   Also, if you are using linux replace java -jar "dir\jflex" by jflex 
   ----------------------------------------------------------------------
*/
/*
ISSUE:
[] NO RECONOCE NUMEROS de la forma ND . ceros
[] Si la cadena tiene como subcadena un . ,el reconocimiento falla:
ie "yo no soy 3.1416loto";                      SOLVED 
                                                agregar "." en CHARLITERAL

[] Si la cadena tiene una comilla simple, regresa la subcadena
   iniciando despues de la comilla simple.

[] NO Reconoce numeros de la forma [1-9]{0-9}*;
ie digitonocero con algun cero                  SOLVED

[] Ver si se conserva la precedencia de operaciones PARECE QUE SI xD
[] Verificar porque no funciona elif            SOLVED
[] Ver porque no funciona la anidacion       
[] el while solo funciona si el cuerpo esta en la misma linea
*/

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

input:
      | auxsuite {
            Visitor v = new PrintVisitor();
            System.out.println("Llegue a input");
            System.out.println("*****************************");
            System.out.println("------------Accept-----------");
            $1.accept(v);
            Printer p = new Printer($1);
            p.print();
            System.out.println("------------<SemanticAnalysis>-----------");
            Visitor sm = new SemanticVisitor();
            $1.accept(sm);
            System.out.println("------------</SemanticAnalysis>-----------");
            //imprimo las variables en la tabla de simbolos
            System.out.println("************USED VARS*******************");
            System.out.println("{");
            symtable.showAll();
            System.out.println("}");
            
            System.out.println("------------<CodGen>-----------");
            String header =   ".class public super Prueba\n"+
                              ".super java/lang/Object\n"+
                              ".method public static main ([Ljava/lang/String;)V\n";
            System.out.println(header);
            Visitor codgen = new CodeVisitor();
            $1.accept(codgen);
            String footer = "return\n"+".end method";
            System.out.println(footer);
            System.out.println("------------</CodGen>-----------");
      }
      ;
/* stmt + */
fi:   NEWLINE
   |  stmt  {$$ = new Lista($1);}
   |  fi stmt  { $1.addChild($2); }
   ;

/* suite: simple_stmt | NEWLINE INDENT stmt+ DEDENT */
suite:   simple  { $$ = $1; }
      |  NEWLINE INDENT auxsuite DEDENT { $$=$3; }
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

if_stmt: IF test DOBLEPUNTO suite { $$ = new IFNodeMejorado($2,$4); }
      |  IF test DOBLEPUNTO suite auxif {
                                          $$ = new IFNodeMejorado($2,$4);
                                          ArrayList<Node> nodos = $5.getNodos();
                                          System.out.println("Debug "+nodos.size());
                                          if(nodos.size()>0)
                                             $$.addChilds(nodos);
                                       } 
      ;
      
/* 
   ELIF tiene que tener una lista para ir agregando ahi los nodos ,
   una vez que auxif es reducida totalmente, tomo la lista de 
   nodos y la agrego al nodo if node o algo asi.
*/
auxif:   ELIF test DOBLEPUNTO suite        { $$ = new ElifNode($2,$4); }
      |  auxif ELIF test DOBLEPUNTO suite  { $1.addChild(new SingleElifNode($3,$5)); }
      ;
      
while_stmt: WHILE test DOBLEPUNTO suite  {$$ = new WhileNode($2,$4); }
          ;

/* simple_stmt: small_stmt (';' small_stmt)* [';'] NEWLINE */
simple:  auxsimple PUNTOCOMA NEWLINE   {  $$ = $1; }
      |  auxsimple NEWLINE             {  $$ = $1; }

/* CHECK */
auxsimple:     small_stmt                       {  $$ = new SStmtNode($1); }
            |  auxsimple PUNTOCOMA small_stmt   {
                                                   $1.addChild($3);
                                                   $$ = $1;
                                                }
small_stmt: expr_stmt  { $$ = $1; }
         |  print_stmt { $$ = $1; }
         ;

expr_stmt:  test            { $$ = $1; }
      |     test  EQ  test  { $$ = new EXPRN($1,$3);}
      ;

/* Duda: esto debo tratarlo como una lista */
print_stmt: PRINT                 { $$ = new PrintNode(); }
         |  PRINT auxprint        { $$ = $2; }
         |  PRINT '>>' auxprint   { $$ = $3; }
         ;
         
/* CHECK */
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
/* CHECK */
and_test:   not_test             { $$=$1; }
         |  and_test AND not_test {
                                    /* Posible error */
                                    /*
                                    $1.addChild($3);
                                    $$ = $1;
                                    */
                                    /* 
                                       Duda: como se hasta que parte bajar
                                       del Arbol
                                    */
                                    $$ = new AndNode($1,$3);
                                 }
         ;

/* falta el not not_test */
not_test:   NOT not_test   { $$= new NotNode($2); }
         |  comparison     { $$= $1; }

/* faltan reglas {} */
/* CHECK Asociativy */
comparison: expr              { $$ = $1; }
   |  expr LE comparison      { $$ = new CmpNode($1,EnumOp.LE,$3); }
   |  expr GR comparison      { $$ = new CmpNode($1,EnumOp.GR,$3); }
   |  expr EQUALS comparison  { $$ = new CmpNode($1,EnumOp.EQUALS,$3); }
   |  expr GRQ comparison     { $$ = new CmpNode($1,EnumOp.GRQ,$3); }
   |  expr LEQ comparison     { $$ = new CmpNode($1,EnumOp.LEQ,$3); }
   |  expr DIFF comparison    { $$ = new CmpNode($1,EnumOp.DIFF,$3); }
   |  expr IN comparison      { $$ = new CmpNode($1,EnumOp.IN,$3); }
   |  expr NOTIN comparison   { $$ = new CmpNode($1,EnumOp.NOTIN,$3); }
   |  expr IS comparison      { $$ = new CmpNode($1,EnumOp.IS,$3); }
   |  expr ISNOT comparison   { $$ = new CmpNode($1,EnumOp.ISNOT,$3); }
   ;

/* CHECK Asociativity */
expr:    xor_expr           { $$= $1;}
      |  expr ORB xor_expr { $$ = new ExprNode($1,$3);}
      ;

xor_expr:   and_expr                { $$=$1; }
         |  xor_expr XOR and_expr   { $$ = new XorNode($1,$3); }
         ;

/* and_expr: arith_expr ('&' arith_expr) */
/* FALTA ESTE NODO */
and_expr:   arith_expr               { $$ = $1; }
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
   |  factor MODULO term   { $$ = new TermNodeX($1,EnumOp.MODULO,$3); }
   |  factor DIVENTERA term { $$ = new TermNodeX($1,EnumOp.DIVENTERA,$3); }
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
      | IDENTIFIER        {
                              $$ = $1;
                          }
;

%%

/* a reference to the lexer object */
private Flexer lexer;
/* Added 26 */
public static SymbolTable symtable;
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
    symtable = new SymbolTable();
}

/* that's how you use the parser */
public static void main(String args[]) throws IOException {
    Parser yyparser = new Parser(new FileReader(args[0]));
    yyparser.yyparse();
    /* Imprimo la tabla de simbolos */
}
