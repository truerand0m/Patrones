//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "Arith.y"
  import java.lang.Math;
  import java.io.*;
  import java.util.*;
//#line 21 "Parser.java"




public class Parser
             implements ParserTokens
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Node
String   yytext;//user variable to return contextual strings
Node yyval; //used to return semantic vals from action routines
Node yylval;//the 'lval' (result) I got from yylex()
Node valstk[] = new Node[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Node();
  yylval=new Node();
  valptr=-1;
}
final void val_push(Node val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Node[] newstack = new Node[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Node val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Node val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Node dup_yyval(Node val)
{
  return val;
}
//#### end semantic value section ####
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    2,    2,    2,    4,    4,    1,    1,    3,
    3,    6,    6,    7,    7,   10,   10,    8,    5,    5,
   11,   11,   12,   12,   13,   13,   14,   14,   14,   15,
   15,    9,   16,   16,   17,   17,   18,   18,   19,   19,
   19,   19,   19,   19,   19,   19,   19,   19,   19,   20,
   20,   21,   21,   22,   22,   23,   23,   23,   24,   24,
   24,   24,   24,   25,   25,   25,   26,   26,   27,   27,
   27,   27,
};
final static short yylen[] = {                            2,
    0,    1,    1,    1,    2,    1,    4,    1,    2,    1,
    1,    1,    1,    4,    5,    4,    5,    4,    3,    2,
    1,    3,    1,    1,    1,    3,    1,    2,    3,    1,
    3,    1,    1,    3,    1,    3,    2,    1,    1,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    1,
    3,    1,    3,    1,    3,    1,    3,    3,    1,    3,
    3,    3,    3,    2,    2,    1,    1,    3,    1,    1,
    1,    1,
};
final static short yydefred[] = {                         0,
   69,   72,   70,   71,    0,    0,    0,    0,    0,    0,
    0,    0,    8,   10,   11,   12,   13,    0,    0,   21,
   23,   24,    0,    0,   35,   38,    0,    0,    0,   54,
    0,    0,   66,    0,   37,    0,    0,    0,   30,    0,
   64,   65,    9,    0,   20,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   26,   19,   22,    0,   36,   46,   40,   41,
   44,   43,   42,   45,    0,   47,   49,   48,    0,   55,
   57,   58,   60,   61,   63,   62,   68,    0,   18,    6,
    0,   31,    0,    0,    0,    0,    0,    0,    7,    0,
    0,   16,    0,   17,
};
final static short yydgoto[] = {                         11,
   12,    0,   13,   99,   14,   15,   16,   17,   18,  105,
   19,   20,   21,   22,   40,   23,   24,   25,   26,   27,
   28,   29,   30,   31,   32,   33,   34,
};
final static short yysindex[] = {                       125,
    0,    0,    0,    0,  182,  182,  182, -252,   -8,   -8,
    0,  125,    0,    0,    0,    0,    0, -280, -247,    0,
    0,    0, -243, -239,    0,    0,  -60, -263, -258,    0,
 -250, -249,    0, -244,    0, -254, -253,  182,    0, -211,
    0,    0,    0,  182,    0,  150,  182,  182,   -8,   -8,
   -8,   -8,   -8,   -8,   -8,   -8,   -8,   -8,   -8,   -8,
   -8,   -8,   -8,   -8,   -8,   -8,   -8,   -8,  160,  160,
 -211,  182,    0,    0,    0, -239,    0,    0,    0,    0,
    0,    0,    0,    0, -263,    0,    0,    0, -258,    0,
    0,    0,    0,    0,    0,    0,    0, -215,    0,    0,
 -201,    0,  125,  182, -190,   95, -235,  182,    0,  160,
 -204,    0,  160,    0,
};
final static short yyrindex[] = {                        88,
    0,    0,    0,    0,    0,    0,    0, -241,    0,    0,
    0,   99,    0,    0,    0,    0,    0, -237,    0,    0,
    0,    0, -197, -115,    0,    0,  -33,   30,  -96,    0,
 -142, -161,    0, -210,    0,    0,    0,    0,    0, -234,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -233,    0,    0,    0,    0,   84,    0,    0,    0,    0,
    0,    0,    0,    0,   47,    0,    0,    0,  -79,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    1,    0,    0,    0,   23,    0,    0,    0,    0,    0,
    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   -2,    0,  -12,  -59,  -55,    0,    0,    0,   -4,    0,
    0,   61,    0,    0,   70,    0,   62,   12,  402,    0,
   54,   51,  -42,  -23,   -3,    0,    0,
};
final static int YYTABLESIZE=463;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         43,
   14,   36,   37,   39,    1,   41,   42,    2,    3,    4,
  101,   45,   44,  100,  100,    5,   35,   27,   90,   91,
   92,   25,   15,   47,   28,   29,   48,    9,   10,   62,
   63,   60,   64,   39,   65,   66,   67,   61,   68,   73,
   93,   94,   95,   96,   69,   70,   72,   67,   67,  103,
  112,   38,   46,  114,  100,   67,   67,  100,   27,   77,
   32,   32,   25,  110,   97,   28,   29,  102,   67,   67,
   67,   67,  104,   67,   67,   67,   67,   67,   67,   67,
   67,   67,   67,  108,   67,   67,   67,    1,   67,   67,
   67,   67,   67,   43,  113,   32,   59,   59,    2,  107,
  106,   32,   32,  111,   59,   59,   75,   71,   76,   85,
   89,    0,    0,    0,    0,   56,   56,   59,   59,   59,
    0,    0,    0,   56,   56,   59,   59,   59,   59,   59,
   59,   59,    0,   59,   59,   59,   56,   59,   59,   59,
   59,   59,   33,   33,   56,   56,   56,   56,   56,   56,
   56,   33,   56,   56,   56,    0,   56,   56,   56,   56,
   56,   52,   52,    0,    0,    0,    0,    0,    0,   52,
   52,    0,    0,    0,    0,    0,    0,   33,   53,   53,
    0,    0,   52,   33,   33,    0,   53,   53,    0,    0,
   52,   52,   52,   52,   52,   52,   52,    0,   52,   53,
   52,    0,   52,   52,   52,   52,   52,   53,   53,   53,
   53,   53,   53,   53,    0,   53,    0,   53,   49,   53,
   53,   53,   53,   53,   39,   39,   50,   51,   52,   53,
   54,   55,   39,   39,    0,    0,   56,    0,    0,    0,
   57,   58,   59,    0,    0,    0,    0,    0,    1,    0,
    0,    2,    3,    4,    0,    0,    0,   14,    0,   39,
   14,   14,   14,    0,   14,   39,   39,    0,   14,    0,
   14,    9,   10,    0,    0,    0,   14,   14,    0,   15,
   14,   14,   15,   15,   15,    0,   15,   50,   50,    0,
   15,    0,   15,    0,    0,   50,   50,    0,   15,   15,
    0,    0,   15,   15,   51,   51,    0,    0,   50,    0,
    0,    0,   51,   51,    0,    0,   50,   50,   50,   50,
   50,   50,   50,    0,    0,   51,   50,    0,   50,   50,
   50,   50,   50,   51,   51,   51,   51,   51,   51,   51,
    0,   34,   34,   51,    0,   51,   51,   51,   51,   51,
   34,    1,    0,    0,    2,    3,    4,    0,  109,    0,
    0,    0,    5,    0,    6,    0,    0,    0,    0,    0,
    7,    8,    0,    0,    9,   10,   34,    0,    0,    0,
    0,    1,   34,   34,    2,    3,    4,    0,    0,    0,
    0,    0,    5,    0,    6,    0,    0,    0,    0,    0,
    7,    8,    0,    0,    9,   10,    1,    0,   74,    2,
    3,    4,    0,    0,    0,    0,    1,    5,   98,    2,
    3,    4,    0,    0,    0,    0,    8,    5,    0,    9,
   10,    0,    0,    0,    0,    0,    8,    0,    1,    9,
   10,    2,    3,    4,    0,    0,    0,    0,    0,    5,
   78,   79,   80,   81,   82,   83,   84,    0,   86,   87,
   88,    9,   10,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         12,
    0,    6,    7,    8,  257,    9,   10,  260,  261,  262,
   70,  259,  293,   69,   70,  268,    5,  259,   61,   62,
   63,  259,    0,  267,  259,  259,  266,  280,  281,  280,
  281,  295,  282,   38,  284,  285,  286,  296,  283,   44,
   64,   65,   66,   67,  299,  299,  258,  258,  259,  265,
  110,  304,  300,  113,  110,  266,  267,  113,  300,   48,
  258,  259,  300,  299,   68,  300,  300,   72,  279,  280,
  281,  282,  274,  284,  285,  286,  287,  288,  289,  290,
  291,  292,  293,  274,  295,  296,  297,    0,  299,  300,
  301,  302,  303,  106,  299,  293,  258,  259,    0,  104,
  103,  299,  300,  108,  266,  267,   46,   38,   47,   56,
   60,   -1,   -1,   -1,   -1,  258,  259,  279,  280,  281,
   -1,   -1,   -1,  266,  267,  287,  288,  289,  290,  291,
  292,  293,   -1,  295,  296,  297,  279,  299,  300,  301,
  302,  303,  258,  259,  287,  288,  289,  290,  291,  292,
  293,  267,  295,  296,  297,   -1,  299,  300,  301,  302,
  303,  258,  259,   -1,   -1,   -1,   -1,   -1,   -1,  266,
  267,   -1,   -1,   -1,   -1,   -1,   -1,  293,  258,  259,
   -1,   -1,  279,  299,  300,   -1,  266,  267,   -1,   -1,
  287,  288,  289,  290,  291,  292,  293,   -1,  295,  279,
  297,   -1,  299,  300,  301,  302,  303,  287,  288,  289,
  290,  291,  292,  293,   -1,  295,   -1,  297,  279,  299,
  300,  301,  302,  303,  258,  259,  287,  288,  289,  290,
  291,  292,  266,  267,   -1,   -1,  297,   -1,   -1,   -1,
  301,  302,  303,   -1,   -1,   -1,   -1,   -1,  257,   -1,
   -1,  260,  261,  262,   -1,   -1,   -1,  257,   -1,  293,
  260,  261,  262,   -1,  264,  299,  300,   -1,  268,   -1,
  270,  280,  281,   -1,   -1,   -1,  276,  277,   -1,  257,
  280,  281,  260,  261,  262,   -1,  264,  258,  259,   -1,
  268,   -1,  270,   -1,   -1,  266,  267,   -1,  276,  277,
   -1,   -1,  280,  281,  258,  259,   -1,   -1,  279,   -1,
   -1,   -1,  266,  267,   -1,   -1,  287,  288,  289,  290,
  291,  292,  293,   -1,   -1,  279,  297,   -1,  299,  300,
  301,  302,  303,  287,  288,  289,  290,  291,  292,  293,
   -1,  258,  259,  297,   -1,  299,  300,  301,  302,  303,
  267,  257,   -1,   -1,  260,  261,  262,   -1,  264,   -1,
   -1,   -1,  268,   -1,  270,   -1,   -1,   -1,   -1,   -1,
  276,  277,   -1,   -1,  280,  281,  293,   -1,   -1,   -1,
   -1,  257,  299,  300,  260,  261,  262,   -1,   -1,   -1,
   -1,   -1,  268,   -1,  270,   -1,   -1,   -1,   -1,   -1,
  276,  277,   -1,   -1,  280,  281,  257,   -1,  259,  260,
  261,  262,   -1,   -1,   -1,   -1,  257,  268,  259,  260,
  261,  262,   -1,   -1,   -1,   -1,  277,  268,   -1,  280,
  281,   -1,   -1,   -1,   -1,   -1,  277,   -1,  257,  280,
  281,  260,  261,  262,   -1,   -1,   -1,   -1,   -1,  268,
   49,   50,   51,   52,   53,   54,   55,   -1,   57,   58,
   59,  280,  281,
};
}
final static short YYFINAL=11;
final static short YYMAXTOKEN=304;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"CADENA","COMA","NEWLINE","IDENTIFIER","ENTERO","REAL","BOOLEAN",
"DEDENT","INDENT","AND","OR","NOT","FROM","WHILE","FOR","DEF","AS","ELIF",
"ELSE","IF","PRINT","RETURN","IN","MAS","MENOS","POR","POTENCIA","DIV",
"DIVENTERA","MODULO","LE","GR","LEQ","GRQ","EQUALS","DIFF","EQ","PA","XOR",
"ANDB","ORB","PC","DOBLEPUNTO","PUNTOCOMA","NOTIN","ISNOT","IS","\">>\"",
};
final static String yyrule[] = {
"$accept : input",
"input :",
"input : auxsuite",
"fi : NEWLINE",
"fi : stmt",
"fi : fi stmt",
"suite : simple",
"suite : NEWLINE INDENT auxsuite DEDENT",
"auxsuite : stmt",
"auxsuite : auxsuite stmt",
"stmt : simple",
"stmt : compound_stmt",
"compound_stmt : if_stmt",
"compound_stmt : while_stmt",
"if_stmt : IF test DOBLEPUNTO suite",
"if_stmt : IF test DOBLEPUNTO suite auxif",
"auxif : ELIF test DOBLEPUNTO suite",
"auxif : auxif ELIF test DOBLEPUNTO suite",
"while_stmt : WHILE test DOBLEPUNTO suite",
"simple : auxsimple PUNTOCOMA NEWLINE",
"simple : auxsimple NEWLINE",
"auxsimple : small_stmt",
"auxsimple : auxsimple PUNTOCOMA small_stmt",
"small_stmt : expr_stmt",
"small_stmt : print_stmt",
"expr_stmt : test",
"expr_stmt : test EQ test",
"print_stmt : PRINT",
"print_stmt : PRINT auxprint",
"print_stmt : PRINT \">>\" auxprint",
"auxprint : test",
"auxprint : auxprint COMA test",
"test : or_test",
"or_test : and_test",
"or_test : or_test OR and_test",
"and_test : not_test",
"and_test : and_test AND not_test",
"not_test : NOT not_test",
"not_test : comparison",
"comparison : expr",
"comparison : expr LE comparison",
"comparison : expr GR comparison",
"comparison : expr EQUALS comparison",
"comparison : expr GRQ comparison",
"comparison : expr LEQ comparison",
"comparison : expr DIFF comparison",
"comparison : expr IN comparison",
"comparison : expr NOTIN comparison",
"comparison : expr IS comparison",
"comparison : expr ISNOT comparison",
"expr : xor_expr",
"expr : expr ORB xor_expr",
"xor_expr : and_expr",
"xor_expr : xor_expr XOR and_expr",
"and_expr : arith_expr",
"and_expr : and_expr ANDB arith_expr",
"arith_expr : term",
"arith_expr : term MAS arith_expr",
"arith_expr : term MENOS arith_expr",
"term : factor",
"term : factor POR term",
"term : factor DIV term",
"term : factor MODULO term",
"term : factor DIVENTERA term",
"factor : MAS factor",
"factor : MENOS factor",
"factor : power",
"power : atom",
"power : atom POTENCIA factor",
"atom : CADENA",
"atom : ENTERO",
"atom : REAL",
"atom : IDENTIFIER",
};

//#line 276 "Arith.y"
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
//#line 450 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 2:
//#line 49 "Arith.y"
{
            Visitor v = new PrintVisitor();
            System.out.println("Llegue a input");
            System.out.println("*****************************");
            System.out.println("------------Accept-----------");
            /*exp*/
            /*
            for(int i=0;i<$1.getNodos().size();i++){
               System.out.println("||||||||||||||||||||||||||||||||||||||||");
               $1.getNodos().get(i).accept(v);
               System.out.println("||||||||||||||||||||||||||||||||||||||||");
            }
            */
            val_peek(0).accept(v);
            Printer p = new Printer(val_peek(0));
            p.print();
            /*
            System.out.println("------------Print-----------");
            //$1.print();
            Printer p = new Printer($1);
            p.print();
            System.out.println("*****************************");
            */
      }
break;
case 4:
//#line 76 "Arith.y"
{yyval = new Lista(val_peek(0));}
break;
case 5:
//#line 77 "Arith.y"
{ val_peek(1).addChild(val_peek(0)); }
break;
case 6:
//#line 81 "Arith.y"
{ yyval = val_peek(0); }
break;
case 7:
//#line 82 "Arith.y"
{ yyval=val_peek(1); }
break;
case 8:
//#line 86 "Arith.y"
{ yyval = val_peek(0); }
break;
case 9:
//#line 87 "Arith.y"
{
                              val_peek(1).addChild(val_peek(0));
                              yyval = val_peek(1);
                           }
break;
case 10:
//#line 93 "Arith.y"
{ yyval=val_peek(0);}
break;
case 11:
//#line 94 "Arith.y"
{ yyval=val_peek(0); }
break;
case 12:
//#line 97 "Arith.y"
{ yyval=val_peek(0); }
break;
case 13:
//#line 98 "Arith.y"
{ yyval=val_peek(0); }
break;
case 14:
//#line 101 "Arith.y"
{ yyval = new IFNodeMejorado(val_peek(2),val_peek(0)); }
break;
case 15:
//#line 102 "Arith.y"
{
                                          yyval = new IFNodeMejorado(val_peek(3),val_peek(1));
                                          ArrayList<Node> nodos = val_peek(0).getNodos();
                                          if(nodos.size()>0)
                                             yyval.addChilds(nodos);
                                       }
break;
case 16:
//#line 114 "Arith.y"
{ yyval = new ElifNode(val_peek(2),val_peek(0)); }
break;
case 17:
//#line 115 "Arith.y"
{ val_peek(4).addChild(new SingleElifNode(val_peek(2),val_peek(0))); }
break;
case 18:
//#line 120 "Arith.y"
{yyval = new WhileNode(val_peek(2),val_peek(0)); }
break;
case 19:
//#line 124 "Arith.y"
{  yyval = val_peek(2); }
break;
case 20:
//#line 125 "Arith.y"
{  yyval = val_peek(1); }
break;
case 21:
//#line 128 "Arith.y"
{  yyval = new SStmtNode(val_peek(0)); }
break;
case 22:
//#line 129 "Arith.y"
{
                                                   val_peek(2).addChild(val_peek(0));
                                                   yyval = val_peek(2);
                                                }
break;
case 23:
//#line 133 "Arith.y"
{ yyval = val_peek(0); }
break;
case 24:
//#line 134 "Arith.y"
{ yyval = val_peek(0); }
break;
case 25:
//#line 137 "Arith.y"
{ yyval = val_peek(0); }
break;
case 26:
//#line 138 "Arith.y"
{ yyval = new EXPRN(val_peek(2),val_peek(0));}
break;
case 27:
//#line 142 "Arith.y"
{ yyval = new PrintNode(); }
break;
case 28:
//#line 143 "Arith.y"
{ yyval = val_peek(0); }
break;
case 29:
//#line 144 "Arith.y"
{ yyval = val_peek(0); }
break;
case 30:
//#line 148 "Arith.y"
{ yyval=new PrintNode(val_peek(0)); }
break;
case 31:
//#line 149 "Arith.y"
{
                                 val_peek(2).addChild(val_peek(0));
                                 yyval = val_peek(2);
                              }
break;
case 32:
//#line 156 "Arith.y"
{ yyval = val_peek(0); }
break;
case 33:
//#line 160 "Arith.y"
{ yyval = val_peek(0); }
break;
case 34:
//#line 161 "Arith.y"
{ yyval = new OrNode(val_peek(2),val_peek(0)); }
break;
case 35:
//#line 166 "Arith.y"
{ yyval=val_peek(0); }
break;
case 36:
//#line 167 "Arith.y"
{
                                    /* Posible error */
                                    /*
                                    $1.addChild($3);
                                    $$ = $1;
                                    */
                                    /* 
                                       Duda: como se hasta que parte bajar
                                       del Arbol
                                    */
                                    yyval = new AndNode(val_peek(2),val_peek(0));
                                 }
break;
case 37:
//#line 182 "Arith.y"
{ yyval= new NotNode(val_peek(0)); }
break;
case 38:
//#line 183 "Arith.y"
{ yyval= val_peek(0); }
break;
case 39:
//#line 187 "Arith.y"
{ yyval = val_peek(0); }
break;
case 40:
//#line 188 "Arith.y"
{ yyval = new CmpNode(val_peek(2),EnumOp.LE,val_peek(0)); }
break;
case 41:
//#line 189 "Arith.y"
{ yyval = new CmpNode(val_peek(2),EnumOp.GR,val_peek(0)); }
break;
case 42:
//#line 190 "Arith.y"
{ yyval = new CmpNode(val_peek(2),EnumOp.EQUALS,val_peek(0)); }
break;
case 43:
//#line 191 "Arith.y"
{ yyval = new CmpNode(val_peek(2),EnumOp.GRQ,val_peek(0)); }
break;
case 44:
//#line 192 "Arith.y"
{ yyval = new CmpNode(val_peek(2),EnumOp.LEQ,val_peek(0)); }
break;
case 45:
//#line 193 "Arith.y"
{ yyval = new CmpNode(val_peek(2),EnumOp.DIFF,val_peek(0)); }
break;
case 46:
//#line 194 "Arith.y"
{ yyval = new CmpNode(val_peek(2),EnumOp.IN,val_peek(0)); }
break;
case 47:
//#line 195 "Arith.y"
{ yyval = new CmpNode(val_peek(2),EnumOp.NOTIN,val_peek(0)); }
break;
case 48:
//#line 196 "Arith.y"
{ yyval = new CmpNode(val_peek(2),EnumOp.IS,val_peek(0)); }
break;
case 49:
//#line 197 "Arith.y"
{ yyval = new CmpNode(val_peek(2),EnumOp.ISNOT,val_peek(0)); }
break;
case 50:
//#line 213 "Arith.y"
{ yyval= val_peek(0);}
break;
case 51:
//#line 214 "Arith.y"
{ yyval = new ExprNode(val_peek(2),val_peek(0));}
break;
case 52:
//#line 217 "Arith.y"
{ yyval=val_peek(0); }
break;
case 53:
//#line 218 "Arith.y"
{ yyval = new XorNode(val_peek(2),val_peek(0)); }
break;
case 54:
//#line 223 "Arith.y"
{ yyval = val_peek(0); }
break;
case 55:
//#line 224 "Arith.y"
{ yyval = new AndNode(val_peek(2),val_peek(0)); }
break;
case 56:
//#line 228 "Arith.y"
{ yyval = val_peek(0); }
break;
case 57:
//#line 229 "Arith.y"
{ yyval = new ArithNode(val_peek(2),EnumOp.MAS,val_peek(0));}
break;
case 58:
//#line 230 "Arith.y"
{ yyval = new ArithNode(val_peek(2),EnumOp.MENOS,val_peek(0)); }
break;
case 59:
//#line 234 "Arith.y"
{ yyval = val_peek(0); }
break;
case 60:
//#line 235 "Arith.y"
{ yyval = new TermNodeX(val_peek(2),EnumOp.POR,val_peek(0)); }
break;
case 61:
//#line 236 "Arith.y"
{ yyval = new TermNodeX(val_peek(2),EnumOp.DIV,val_peek(0)); }
break;
case 62:
//#line 237 "Arith.y"
{ yyval = new TermNodeX(val_peek(2),EnumOp.MODULO,val_peek(0)); }
break;
case 63:
//#line 238 "Arith.y"
{ yyval = new TermNodeX(val_peek(2),EnumOp.DIVENTERA,val_peek(0)); }
break;
case 64:
//#line 242 "Arith.y"
{ yyval = new FactorNode(EnumOp.MAS,val_peek(0)); }
break;
case 65:
//#line 243 "Arith.y"
{ yyval = new FactorNode(EnumOp.MENOS,val_peek(0)); }
break;
case 66:
//#line 244 "Arith.y"
{ yyval = val_peek(0); }
break;
case 67:
//#line 248 "Arith.y"
{ yyval = val_peek(0); }
break;
case 68:
//#line 249 "Arith.y"
{ yyval = new PowerNode(val_peek(2),val_peek(0)); }
break;
case 69:
//#line 253 "Arith.y"
{yyval = val_peek(0);}
break;
case 70:
//#line 254 "Arith.y"
{yyval = val_peek(0);}
break;
case 71:
//#line 255 "Arith.y"
{yyval = val_peek(0);}
break;
case 72:
//#line 256 "Arith.y"
{yyval = val_peek(0);}
break;
//#line 927 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
