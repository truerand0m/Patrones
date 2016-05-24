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
    0,    0,    1,    1,    2,    3,    3,    4,    4,    5,
    5,    5,    6,    6,    6,    6,    6,    7,    7,    7,
    8,    8,    9,    9,    9,    9,
};
final static short yylen[] = {                            2,
    0,    2,    1,    5,    1,    1,    3,    1,    3,    1,
    3,    3,    1,    3,    3,    3,    3,    2,    2,    1,
    1,    3,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
   23,   26,   24,   25,    0,    0,    0,    0,    0,    0,
    0,    8,    0,    0,   20,    0,   18,   19,    2,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    9,   11,   12,   14,   15,   17,   16,   22,    0,
    4,
};
final static short yydgoto[] = {                          7,
    8,    9,   10,   11,   12,   13,   14,   15,   16,
};
final static short yysindex[] = {                      -200,
    0,    0,    0,    0, -200, -200,    0, -255, -278, -276,
 -262,    0, -249, -279,    0, -246,    0,    0,    0, -250,
 -200, -200, -200, -200, -200, -200, -200, -200, -200, -200,
 -262,    0,    0,    0,    0,    0,    0,    0,    0, -242,
    0,
};
final static short yyrindex[] = {                        42,
    0,    0,    0,    0,    0,    0,    0,    0, -210, -257,
 -243,    0, -245, -251,    0, -258,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -234,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
    0,   26,    0,   43,  -12,   -9,    3,    0,    0,
};
final static int YYTABLESIZE=80;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         21,
    5,   25,   19,   26,   27,   28,   13,   17,   18,   32,
   33,   34,   10,   20,    6,   35,   36,   37,   38,   21,
   21,   21,   21,    7,   21,   21,   21,   13,   13,   23,
   24,   39,   22,   21,    5,   29,   21,   21,   21,    5,
   13,    1,   30,   13,   13,   13,   10,    3,    6,   10,
   10,   10,    6,    6,   41,   40,    1,    7,    2,    3,
    4,    7,    7,   31,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    5,    6,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                        258,
  258,  281,  258,  283,  284,  285,  258,    5,    6,   22,
   23,   24,  258,  292,  258,   25,   26,   27,   28,  296,
  279,  280,  281,  258,  283,  284,  285,  279,  280,  279,
  280,   29,  295,  292,  292,  282,  295,  296,  297,  297,
  292,    0,  293,  295,  296,  297,  292,  258,  292,  295,
  296,  297,  296,  297,  297,   30,  257,  292,  259,  260,
  261,  296,  297,   21,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  279,  280,
};
}
final static short YYFINAL=7;
final static short YYMAXTOKEN=299;
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
null,null,null,"CADENA","NEWLINE","IDENTIFIER","ENTERO","REAL","BOOLEAN",
"DEDENT","INDENT","AND","OR","NOT","FROM","WHILE","FOR","DEF","AS","ELIF",
"ELSE","IF","PRINT","RETURN","IN","MAS","MENOS","POR","POTENCIA","DIV",
"DIVENTERA","MODULO","LE","GR","LEQ","GRQ","EQUALS","DIFF","EQ","PA","XOR",
"ANDB","ORB","PC","DOBLEPUNTO","PUNTOCOMA",
};
final static String yyrule[] = {
"$accept : input",
"input :",
"input : expr NEWLINE",
"expr : test",
"expr : test EQ PA test PC",
"test : or_test",
"or_test : and_expr",
"or_test : or_test ORB and_expr",
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

//#line 82 "Arith.y"
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
//#line 286 "Parser.java"
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
//#line 24 "Arith.y"
{
            Visitor v = new PrintVisitor();
            System.out.println("Llegue a input");
            System.out.println("*****************************");
            System.out.println("------------Accept-----------");
            val_peek(1).accept(v);
            System.out.println("------------Print-----------");
            val_peek(1).print();
            Printer p = new Printer(val_peek(1));
            p.print();
            System.out.println("*****************************");
      }
break;
case 3:
//#line 39 "Arith.y"
{ yyval = val_peek(0); }
break;
case 4:
//#line 40 "Arith.y"
{ yyval = new ExprNode(val_peek(4),val_peek(1));}
break;
case 5:
//#line 43 "Arith.y"
{ yyval = val_peek(0); }
break;
case 6:
//#line 46 "Arith.y"
{ yyval = val_peek(0); }
break;
case 7:
//#line 47 "Arith.y"
{ yyval = new OrNode(val_peek(2),val_peek(0)); }
break;
case 8:
//#line 50 "Arith.y"
{ yyval = val_peek(0); }
break;
case 9:
//#line 51 "Arith.y"
{ yyval = new AndNode(val_peek(2),val_peek(0)); }
break;
case 10:
//#line 54 "Arith.y"
{ yyval = val_peek(0); }
break;
case 11:
//#line 55 "Arith.y"
{ yyval = new ArithNode(val_peek(2),EnumOp.MAS,val_peek(0));}
break;
case 12:
//#line 56 "Arith.y"
{ yyval = new ArithNode(val_peek(2),EnumOp.MENOS,val_peek(0)); }
break;
case 13:
//#line 59 "Arith.y"
{ yyval = val_peek(0); }
break;
case 14:
//#line 60 "Arith.y"
{ yyval = new TermNodeX(val_peek(2),EnumOp.POR,val_peek(0)); }
break;
case 15:
//#line 61 "Arith.y"
{ yyval = new TermNodeX(val_peek(2),EnumOp.DIV,val_peek(0)); }
break;
case 16:
//#line 62 "Arith.y"
{ yyval = new TermNodeX(val_peek(2),EnumOp.MODULO,val_peek(0)); }
break;
case 17:
//#line 63 "Arith.y"
{ yyval = new TermNodeX(val_peek(2),EnumOp.DIVENTERA,val_peek(0)); }
break;
case 18:
//#line 66 "Arith.y"
{ yyval = new FactorNode(EnumOp.MAS,val_peek(0)); }
break;
case 19:
//#line 67 "Arith.y"
{ yyval = new FactorNode(EnumOp.MENOS,val_peek(0)); }
break;
case 20:
//#line 68 "Arith.y"
{ yyval = val_peek(0); }
break;
case 21:
//#line 71 "Arith.y"
{yyval = val_peek(0); }
break;
case 22:
//#line 72 "Arith.y"
{ yyval = new PowerNode(val_peek(2),val_peek(0)); }
break;
case 23:
//#line 76 "Arith.y"
{yyval = val_peek(0);}
break;
case 24:
//#line 77 "Arith.y"
{yyval = val_peek(0);}
break;
case 25:
//#line 78 "Arith.y"
{yyval = val_peek(0);}
break;
case 26:
//#line 79 "Arith.y"
{yyval = val_peek(0);}
break;
//#line 546 "Parser.java"
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
