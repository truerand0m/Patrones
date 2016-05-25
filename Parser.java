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
//#line 20 "Parser.java"




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
    0,    0,    2,    2,    4,    4,    1,    1,    5,    5,
    6,    6,    9,    9,    7,    3,    3,   10,   10,   11,
   11,   12,   12,   13,   13,   13,   14,   14,    8,   15,
   15,   16,   16,   17,   18,   18,   20,   20,   21,   21,
   21,   21,   21,   21,   21,   21,   21,   21,   19,   19,
   22,   22,   23,   23,   24,   24,   24,   25,   25,   25,
   25,   25,   26,   26,   26,   27,   27,   28,   28,   28,
   28,
};
final static short yylen[] = {                            2,
    0,    1,    1,    4,    1,    2,    1,    1,    1,    1,
    4,    5,    4,    5,    4,    3,    2,    1,    3,    1,
    1,    1,    3,    1,    2,    3,    1,    3,    1,    1,
    3,    1,    3,    1,    1,    2,    2,    3,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    3,
    1,    3,    1,    3,    1,    3,    3,    1,    3,    3,
    3,    3,    2,    2,    1,    1,    3,    1,    1,    1,
    1,
};
final static short yydefred[] = {                         0,
   68,   71,   69,   70,    0,    0,    0,    0,    0,    0,
    2,    7,    8,    9,   10,    0,    0,   18,   20,   21,
    0,    0,   32,   34,    0,    0,    0,   53,    0,    0,
   65,    0,    0,    0,    0,   27,    0,   63,   64,    0,
   17,    0,    0,    0,   45,   39,   40,   43,   42,   41,
   44,    0,   46,   48,   47,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   23,   16,   19,    0,   33,    0,    0,    0,    0,   54,
   56,   57,   59,   60,   62,   61,   67,    0,   15,    3,
    0,   28,    0,    0,    0,    0,    5,    0,    0,    0,
    4,    6,    0,    0,   13,    0,   14,
};
final static short yydgoto[] = {                         10,
   11,   89,   90,   98,   13,   14,   15,   16,   96,   17,
   18,   19,   20,   37,   21,   22,   23,   24,   25,   56,
   57,   26,   27,   28,   29,   30,   31,   32,
};
final static short yysindex[] = {                       170,
    0,    0,    0,    0, -186, -186, -251, -186, -186,    0,
    0,    0,    0,    0,    0, -293, -241,    0,    0,    0,
 -252, -249,    0,    0,  111, -276, -272,    0, -217, -154,
    0, -256, -229, -216, -186,    0, -173,    0,    0, -186,
    0,  164, -186, -186,    0,    0,    0,    0,    0,    0,
    0, -186,    0,    0,    0,   78, -186, -186, -186, -186,
 -186, -186, -186, -186, -186, -186,  195,  195, -173, -186,
    0,    0,    0, -249,    0, -276, -186, -211, -272,    0,
    0,    0,    0,    0,    0,    0,    0, -175,    0,    0,
 -183,    0, -211,  170, -186, -182,    0,  158, -202, -186,
    0,    0,  195, -200,    0,  195,    0,
};
final static short yyrindex[] = {                       104,
    0,    0,    0,    0,    0,    0, -239,    0,    0,    0,
    0,    0,    0,    0,    0, -231,    0,    0,    0,    0,
 -233, -151,    0,    0,    8,  -50, -113,    0, -132, -178,
    0, -245,    0,    0,    0,    0, -228,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   96,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -227,    0,
    0,    0,    0,  -86,    0,   30,    0,   47,  -67,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    1,    0,   85,    0,    0,   23,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  -82,  -19,    2,    0,    0,    0,    0,   -2,    0,    0,
   63,    0,    0,   71,    0,   77,   89,    0,    5,    0,
   73,   88,   83,   18,   74,   -1,    0,    0,
};
final static int YYTABLESIZE=476;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         40,
   11,   12,   33,   34,   36,    1,   38,   39,    2,    3,
    4,   97,   66,   66,   43,  102,   44,   41,   58,   24,
   66,   66,   12,   59,   29,   29,   66,   22,    8,    9,
   25,   26,   36,   66,   66,   66,   66,   71,   66,   66,
   66,   66,   66,   66,   66,   66,   66,   66,   91,   66,
   66,   66,   35,   66,   66,   66,   66,   66,   42,   29,
   24,   78,   60,   61,   87,   29,   29,   92,   22,   67,
    1,   25,   26,    2,    3,    4,   80,   81,   82,   58,
   58,   93,   68,  105,   70,   52,  107,   58,   58,   94,
   95,  100,   99,    8,    9,   12,  103,  104,  106,   12,
   58,   58,   58,    1,   73,   69,   30,   30,   58,   58,
   58,   58,   58,   58,   58,   30,   58,   58,   58,   74,
   58,   58,   58,   58,   58,   55,   55,   62,   77,   63,
   64,   65,   75,   55,   55,   83,   84,   85,   86,   76,
   79,   30,    0,    0,   51,   51,   55,   30,   30,    0,
    0,    0,   51,   51,   55,   55,   55,   55,   55,   55,
   55,    0,   55,   55,   55,   51,   55,   55,   55,   55,
   55,   31,   31,   51,   51,   51,   51,   51,   51,   51,
   31,   51,    0,   51,    0,   51,   51,   51,   51,   51,
   52,   52,    0,    0,    0,    0,    0,    0,   52,   52,
    0,    0,    0,    0,    0,    0,   31,   49,   49,    0,
    0,   52,   31,   31,    0,   49,   49,    0,    0,   52,
   52,   52,   52,   52,   52,   52,    0,   52,   49,   52,
    0,   52,   52,   52,   52,   52,   49,   49,   49,   49,
   49,   49,   49,    0,    0,    0,   49,    0,   49,   49,
   49,   49,   49,    0,    0,    0,    0,   11,    0,    0,
   11,   11,   11,    0,   11,   35,   35,    0,    0,    0,
   11,    0,    0,   35,   35,    0,   11,   11,    0,   12,
   11,   11,   12,   12,   12,    0,   12,   50,   50,    0,
    0,    0,   12,    0,    0,   50,   50,    0,   12,   12,
   35,    0,   12,   12,   37,   37,   35,   35,   50,    0,
    0,    0,   37,   37,    0,    0,   50,   50,   50,   50,
   50,   50,   50,    0,    0,   37,   50,    0,   50,   50,
   50,   50,   50,   37,   37,   37,   37,   37,   37,   37,
    0,    0,   38,   38,    0,   37,   37,   37,   37,   37,
   38,   38,    0,   36,   36,    0,   45,    0,    0,    0,
    0,   36,   36,   38,   46,   47,   48,   49,   50,   51,
    0,   38,   38,   38,   38,   38,   38,   38,   53,   54,
   55,    0,    0,   38,   38,   38,   38,   38,   36,   45,
    0,    0,    0,    0,   36,   36,    0,   46,   47,   48,
   49,   50,   51,    0,    0,    0,    0,   52,    0,    0,
    0,   53,   54,   55,    1,    0,    0,    2,    3,    4,
    1,  101,   72,    2,    3,    4,    1,    5,    0,    2,
    3,    4,    0,    6,    7,    0,    0,    8,    9,    5,
    7,    0,    0,    8,    9,    6,    7,    0,    0,    8,
    9,    1,    0,   88,    2,    3,    4,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    7,    0,    0,    8,    9,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                        293,
    0,    0,    5,    6,    7,  257,    8,    9,  260,  261,
  262,   94,  258,  259,  267,   98,  266,  259,  295,  259,
  266,  267,    0,  296,  258,  259,  283,  259,  280,  281,
  259,  259,   35,  279,  280,  281,  282,   40,  284,  285,
  286,  287,  288,  289,  290,  291,  292,  293,   68,  295,
  296,  297,  304,  299,  300,  301,  302,  303,  300,  293,
  300,   57,  280,  281,   66,  299,  300,   70,  300,  299,
  257,  300,  300,  260,  261,  262,   59,   60,   61,  258,
  259,   77,  299,  103,  258,  297,  106,  266,  267,  265,
  274,  274,   95,  280,  281,   94,  299,  100,  299,   98,
  279,  280,  281,    0,   42,   35,  258,  259,  287,  288,
  289,  290,  291,  292,  293,  267,  295,  296,  297,   43,
  299,  300,  301,  302,  303,  258,  259,  282,   56,  284,
  285,  286,   44,  266,  267,   62,   63,   64,   65,   52,
   58,  293,   -1,   -1,  258,  259,  279,  299,  300,   -1,
   -1,   -1,  266,  267,  287,  288,  289,  290,  291,  292,
  293,   -1,  295,  296,  297,  279,  299,  300,  301,  302,
  303,  258,  259,  287,  288,  289,  290,  291,  292,  293,
  267,  295,   -1,  297,   -1,  299,  300,  301,  302,  303,
  258,  259,   -1,   -1,   -1,   -1,   -1,   -1,  266,  267,
   -1,   -1,   -1,   -1,   -1,   -1,  293,  258,  259,   -1,
   -1,  279,  299,  300,   -1,  266,  267,   -1,   -1,  287,
  288,  289,  290,  291,  292,  293,   -1,  295,  279,  297,
   -1,  299,  300,  301,  302,  303,  287,  288,  289,  290,
  291,  292,  293,   -1,   -1,   -1,  297,   -1,  299,  300,
  301,  302,  303,   -1,   -1,   -1,   -1,  257,   -1,   -1,
  260,  261,  262,   -1,  264,  258,  259,   -1,   -1,   -1,
  270,   -1,   -1,  266,  267,   -1,  276,  277,   -1,  257,
  280,  281,  260,  261,  262,   -1,  264,  258,  259,   -1,
   -1,   -1,  270,   -1,   -1,  266,  267,   -1,  276,  277,
  293,   -1,  280,  281,  258,  259,  299,  300,  279,   -1,
   -1,   -1,  266,  267,   -1,   -1,  287,  288,  289,  290,
  291,  292,  293,   -1,   -1,  279,  297,   -1,  299,  300,
  301,  302,  303,  287,  288,  289,  290,  291,  292,  293,
   -1,   -1,  258,  259,   -1,  299,  300,  301,  302,  303,
  266,  267,   -1,  258,  259,   -1,  279,   -1,   -1,   -1,
   -1,  266,  267,  279,  287,  288,  289,  290,  291,  292,
   -1,  287,  288,  289,  290,  291,  292,  293,  301,  302,
  303,   -1,   -1,  299,  300,  301,  302,  303,  293,  279,
   -1,   -1,   -1,   -1,  299,  300,   -1,  287,  288,  289,
  290,  291,  292,   -1,   -1,   -1,   -1,  297,   -1,   -1,
   -1,  301,  302,  303,  257,   -1,   -1,  260,  261,  262,
  257,  264,  259,  260,  261,  262,  257,  270,   -1,  260,
  261,  262,   -1,  276,  277,   -1,   -1,  280,  281,  270,
  277,   -1,   -1,  280,  281,  276,  277,   -1,   -1,  280,
  281,  257,   -1,  259,  260,  261,  262,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  277,   -1,   -1,  280,  281,
};
}
final static short YYFINAL=10;
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
"input : stmt",
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
"not_test : comparison",
"comparison : expr",
"comparison : expr auxcomp",
"auxcomp : comp_op expr",
"auxcomp : auxcomp comp_op expr",
"comp_op : LE",
"comp_op : GR",
"comp_op : EQUALS",
"comp_op : GRQ",
"comp_op : LEQ",
"comp_op : DIFF",
"comp_op : IN",
"comp_op : NOTIN",
"comp_op : IS",
"comp_op : ISNOT",
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

//#line 183 "Arith.y"
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
//#line 447 "Parser.java"
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
//#line 28 "Arith.y"
{
            Visitor v = new PrintVisitor();
            System.out.println("Llegue a input");
            System.out.println("*****************************");
            System.out.println("------------Accept-----------");
            val_peek(0).accept(v);
            System.out.println("------------Print-----------");
            /*$1.print();*/
            Printer p = new Printer(val_peek(0));
            p.print();
            System.out.println("*****************************");
      }
break;
case 3:
//#line 44 "Arith.y"
{ yyval = val_peek(0); }
break;
case 5:
//#line 49 "Arith.y"
{ yyval = val_peek(0); }
break;
case 6:
//#line 50 "Arith.y"
{
                              val_peek(1).addChild(val_peek(0));
                              yyval = val_peek(1);
                           }
break;
case 7:
//#line 56 "Arith.y"
{ yyval=val_peek(0);}
break;
case 8:
//#line 57 "Arith.y"
{ yyval=val_peek(0); }
break;
case 9:
//#line 60 "Arith.y"
{ yyval=val_peek(0); }
break;
case 10:
//#line 61 "Arith.y"
{ yyval=val_peek(0); }
break;
case 11:
//#line 65 "Arith.y"
{ new IfNode(val_peek(3),val_peek(0)); }
break;
case 12:
//#line 66 "Arith.y"
{
                                          yyval = new IfNode(val_peek(3),val_peek(1));
                                          val_peek(4).addChild(val_peek(0));
                                       }
break;
case 13:
//#line 72 "Arith.y"
{ yyval = new IfNode(val_peek(2),val_peek(0)); }
break;
case 14:
//#line 73 "Arith.y"
{ val_peek(4).addChild(new IfNode(val_peek(2),val_peek(0))); }
break;
case 15:
//#line 76 "Arith.y"
{yyval = new WhileNode(val_peek(2),val_peek(0)); }
break;
case 16:
//#line 80 "Arith.y"
{  yyval = val_peek(2); }
break;
case 17:
//#line 81 "Arith.y"
{  yyval = val_peek(1); }
break;
case 18:
//#line 84 "Arith.y"
{  yyval = new SStmtNode(val_peek(0)); }
break;
case 19:
//#line 85 "Arith.y"
{
                                                   val_peek(2).addChild(val_peek(0));
                                                   yyval = val_peek(2);
                                                }
break;
case 20:
//#line 90 "Arith.y"
{ yyval = val_peek(0); }
break;
case 21:
//#line 91 "Arith.y"
{ yyval = val_peek(0); }
break;
case 22:
//#line 94 "Arith.y"
{ yyval = val_peek(0); }
break;
case 23:
//#line 95 "Arith.y"
{ yyval = new EXPRN(val_peek(2),val_peek(0));}
break;
case 24:
//#line 98 "Arith.y"
{ yyval = new PrintNode(); }
break;
case 25:
//#line 99 "Arith.y"
{ yyval = val_peek(0); }
break;
case 26:
//#line 100 "Arith.y"
{ yyval = val_peek(0); }
break;
case 27:
//#line 103 "Arith.y"
{ yyval=new PrintNode(val_peek(0)); }
break;
case 28:
//#line 104 "Arith.y"
{
                                 val_peek(2).addChild(val_peek(0));
                                 yyval = val_peek(2);
                              }
break;
case 29:
//#line 111 "Arith.y"
{ yyval = val_peek(0); }
break;
case 30:
//#line 114 "Arith.y"
{ yyval = val_peek(0); }
break;
case 31:
//#line 115 "Arith.y"
{ yyval = new OrNode(val_peek(2),val_peek(0)); }
break;
case 32:
//#line 118 "Arith.y"
{ yyval=val_peek(0); }
break;
case 33:
//#line 119 "Arith.y"
{
                                    /* Posible error */
                                    val_peek(2).addChild(val_peek(0));
                                    yyval = val_peek(2);
                                 }
break;
case 34:
//#line 126 "Arith.y"
{ yyval= val_peek(0); }
break;
case 35:
//#line 129 "Arith.y"
{ yyval=val_peek(0); }
break;
case 49:
//#line 141 "Arith.y"
{ yyval= val_peek(0);}
break;
case 50:
//#line 142 "Arith.y"
{ yyval = new ExprNode(val_peek(2),val_peek(0));}
break;
case 51:
//#line 145 "Arith.y"
{ yyval=val_peek(0); }
break;
case 52:
//#line 146 "Arith.y"
{ yyval = new XorNode(val_peek(2),val_peek(0)); }
break;
case 53:
//#line 150 "Arith.y"
{ yyval = val_peek(0); }
break;
case 54:
//#line 151 "Arith.y"
{ yyval = new AndNode(val_peek(2),val_peek(0)); }
break;
case 55:
//#line 155 "Arith.y"
{ yyval = val_peek(0); }
break;
case 56:
//#line 156 "Arith.y"
{ yyval = new ArithNode(val_peek(2),EnumOp.MAS,val_peek(0));}
break;
case 57:
//#line 157 "Arith.y"
{ yyval = new ArithNode(val_peek(2),EnumOp.MENOS,val_peek(0)); }
break;
case 58:
//#line 160 "Arith.y"
{ yyval = val_peek(0); }
break;
case 59:
//#line 161 "Arith.y"
{ yyval = new TermNodeX(val_peek(2),EnumOp.POR,val_peek(0)); }
break;
case 60:
//#line 162 "Arith.y"
{ yyval = new TermNodeX(val_peek(2),EnumOp.DIV,val_peek(0)); }
break;
case 61:
//#line 163 "Arith.y"
{ yyval = new TermNodeX(val_peek(2),EnumOp.MODULO,val_peek(0)); }
break;
case 62:
//#line 164 "Arith.y"
{ yyval = new TermNodeX(val_peek(2),EnumOp.DIVENTERA,val_peek(0)); }
break;
case 63:
//#line 167 "Arith.y"
{ yyval = new FactorNode(EnumOp.MAS,val_peek(0)); }
break;
case 64:
//#line 168 "Arith.y"
{ yyval = new FactorNode(EnumOp.MENOS,val_peek(0)); }
break;
case 65:
//#line 169 "Arith.y"
{ yyval = val_peek(0); }
break;
case 66:
//#line 172 "Arith.y"
{ yyval = val_peek(0); }
break;
case 67:
//#line 173 "Arith.y"
{ yyval = new PowerNode(val_peek(2),val_peek(0)); }
break;
case 68:
//#line 177 "Arith.y"
{yyval = val_peek(0);}
break;
case 69:
//#line 178 "Arith.y"
{yyval = val_peek(0);}
break;
case 70:
//#line 179 "Arith.y"
{yyval = val_peek(0);}
break;
case 71:
//#line 180 "Arith.y"
{yyval = val_peek(0);}
break;
//#line 847 "Parser.java"
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
