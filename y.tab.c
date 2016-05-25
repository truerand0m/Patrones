#ifndef lint
static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";
#endif
#define YYBYACC 1
#line 2 "Arith.y"
  import java.lang.Math;
  import java.io.*;
#line 9 "y.tab.c"
#define CADENA 257
#define COMA 258
#define NEWLINE 259
#define IDENTIFIER 260
#define ENTERO 261
#define REAL 262
#define BOOLEAN 263
#define DEDENT 264
#define INDENT 265
#define AND 266
#define OR 267
#define NOT 268
#define FROM 269
#define WHILE 270
#define FOR 271
#define DEF 272
#define AS 273
#define ELIF 274
#define ELSE 275
#define IF 276
#define PRINT 277
#define RETURN 278
#define IN 279
#define MAS 280
#define MENOS 281
#define POR 282
#define POTENCIA 283
#define DIV 284
#define DIVENTERA 285
#define MODULO 286
#define LE 287
#define GR 288
#define LEQ 289
#define GRQ 290
#define EQUALS 291
#define DIFF 292
#define EQ 293
#define PA 294
#define XOR 295
#define ANDB 296
#define ORB 297
#define PC 298
#define DOBLEPUNTO 299
#define PUNTOCOMA 300
#define NOTIN 301
#define ISNOT 302
#define IS 303
#define YYERRCODE 256
short yylhs[] = {                                        -1,
    0,    0,    2,    2,    4,    4,    1,    1,    5,    5,
    6,    6,    9,    9,    7,    3,    3,   10,   10,   11,
   11,   12,   12,   13,   13,   13,   14,   14,    8,   15,
   15,   16,   16,   17,   18,   18,   20,   20,   21,   21,
   21,   21,   21,   21,   21,   21,   21,   21,   19,   19,
   22,   22,   23,   23,   24,   24,   24,   25,   25,   25,
   25,   25,   26,   26,   26,   27,   27,   28,   28,   28,
   28,
};
short yylen[] = {                                         2,
    0,    1,    1,    4,    1,    2,    1,    1,    1,    1,
    4,    5,    4,    5,    4,    3,    2,    1,    3,    1,
    1,    1,    3,    1,    2,    3,    1,    3,    1,    1,
    3,    1,    3,    1,    1,    2,    2,    3,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    3,
    1,    3,    1,    3,    1,    3,    3,    1,    3,    3,
    3,    3,    2,    2,    1,    1,    3,    1,    1,    1,
    1,
};
short yydefred[] = {                                      0,
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
short yydgoto[] = {                                      10,
   11,   89,   90,   98,   13,   14,   15,   16,   96,   17,
   18,   19,   20,   37,   21,   22,   23,   24,   25,   56,
   57,   26,   27,   28,   29,   30,   31,   32,
};
short yysindex[] = {                                    170,
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
short yyrindex[] = {                                    104,
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
short yygindex[] = {                                      0,
  -82,  -19,    2,    0,    0,    0,    0,   -2,    0,    0,
   63,    0,    0,   71,    0,   77,   89,    0,    5,    0,
   73,   88,   83,   18,   74,   -1,    0,    0,
};
#define YYTABLESIZE 476
short yytable[] = {                                      40,
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
short yycheck[] = {                                     293,
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
#define YYFINAL 10
#ifndef YYDEBUG
#define YYDEBUG 0
#endif
#define YYMAXTOKEN 304
#if YYDEBUG
char *yyname[] = {
"end-of-file",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"CADENA","COMA","NEWLINE",
"IDENTIFIER","ENTERO","REAL","BOOLEAN","DEDENT","INDENT","AND","OR","NOT",
"FROM","WHILE","FOR","DEF","AS","ELIF","ELSE","IF","PRINT","RETURN","IN","MAS",
"MENOS","POR","POTENCIA","DIV","DIVENTERA","MODULO","LE","GR","LEQ","GRQ",
"EQUALS","DIFF","EQ","PA","XOR","ANDB","ORB","PC","DOBLEPUNTO","PUNTOCOMA",
"NOTIN","ISNOT","IS","\">>\"",
};
char *yyrule[] = {
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
#endif
#ifndef YYSTYPE
typedef int YYSTYPE;
#endif
#define yyclearin (yychar=(-1))
#define yyerrok (yyerrflag=0)
#ifdef YYSTACKSIZE
#ifndef YYMAXDEPTH
#define YYMAXDEPTH YYSTACKSIZE
#endif
#else
#ifdef YYMAXDEPTH
#define YYSTACKSIZE YYMAXDEPTH
#else
#define YYSTACKSIZE 500
#define YYMAXDEPTH 500
#endif
#endif
int yydebug;
int yynerrs;
int yyerrflag;
int yychar;
short *yyssp;
YYSTYPE *yyvsp;
YYSTYPE yyval;
YYSTYPE yylval;
short yyss[YYSTACKSIZE];
YYSTYPE yyvs[YYSTACKSIZE];
#define yystacksize YYSTACKSIZE
#line 181 "Arith.y"
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
#line 383 "y.tab.c"
#define YYABORT goto yyabort
#define YYACCEPT goto yyaccept
#define YYERROR goto yyerrlab
int
yyparse()
{
    register int yym, yyn, yystate;
#if YYDEBUG
    register char *yys;
    extern char *getenv();

    if (yys = getenv("YYDEBUG"))
    {
        yyn = *yys;
        if (yyn >= '0' && yyn <= '9')
            yydebug = yyn - '0';
    }
#endif

    yynerrs = 0;
    yyerrflag = 0;
    yychar = (-1);

    yyssp = yyss;
    yyvsp = yyvs;
    *yyssp = yystate = 0;

yyloop:
    if (yyn = yydefred[yystate]) goto yyreduce;
    if (yychar < 0)
    {
        if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, reading %d (%s)\n", yystate,
                    yychar, yys);
        }
#endif
    }
    if ((yyn = yysindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: state %d, shifting to state %d (%s)\n",
                    yystate, yytable[yyn],yyrule[yyn]);
#endif
        if (yyssp >= yyss + yystacksize - 1)
        {
            goto yyoverflow;
        }
        *++yyssp = yystate = yytable[yyn];
        *++yyvsp = yylval;
        yychar = (-1);
        if (yyerrflag > 0)  --yyerrflag;
        goto yyloop;
    }
    if ((yyn = yyrindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
        yyn = yytable[yyn];
        goto yyreduce;
    }
    if (yyerrflag) goto yyinrecovery;
#ifdef lint
    goto yynewerror;
#endif
yynewerror:
    yyerror("syntax error");
#ifdef lint
    goto yyerrlab;
#endif
yyerrlab:
    ++yynerrs;
yyinrecovery:
    if (yyerrflag < 3)
    {
        yyerrflag = 3;
        for (;;)
        {
            if ((yyn = yysindex[*yyssp]) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: state %d, error recovery shifting\
 to state %d\n", *yyssp, yytable[yyn]);
#endif
                if (yyssp >= yyss + yystacksize - 1)
                {
                    goto yyoverflow;
                }
                *++yyssp = yystate = yytable[yyn];
                *++yyvsp = yylval;
                goto yyloop;
            }
            else
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: error recovery discarding state %d\n",
                            *yyssp);
#endif
                if (yyssp <= yyss) goto yyabort;
                --yyssp;
                --yyvsp;
            }
        }
    }
    else
    {
        if (yychar == 0) goto yyabort;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, error recovery discards token %d (%s)\n",
                    yystate, yychar, yys);
        }
#endif
        yychar = (-1);
        goto yyloop;
    }
yyreduce:
#if YYDEBUG
    if (yydebug)
        printf("yydebug: state %d, reducing by rule %d (%s)\n",
                yystate, yyn, yyrule[yyn]);
#endif
    yym = yylen[yyn];
    yyval = yyvsp[1-yym];
    switch (yyn)
    {
case 2:
#line 28 "Arith.y"
{
            Visitor v = new PrintVisitor();
            System.out.println("Llegue a input");
            System.out.println("*****************************");
            System.out.println("------------Accept-----------");
            yyvsp[0].accept(v);
            System.out.println("------------Print-----------");
            /*$1.print();*/
            Printer p = new Printer(yyvsp[0]);
            p.print();
            System.out.println("*****************************");
      }
break;
case 3:
#line 44 "Arith.y"
{ yyval = yyvsp[0]; }
break;
case 5:
#line 49 "Arith.y"
{ yyval = yyvsp[0]; }
break;
case 6:
#line 50 "Arith.y"
{
                              yyvsp[-1].addChild(yyvsp[0]);
                              yyval = yyvsp[-1];
                           }
break;
case 7:
#line 56 "Arith.y"
{ yyval=yyvsp[0];}
break;
case 8:
#line 57 "Arith.y"
{ yyval=yyvsp[0]; }
break;
case 9:
#line 60 "Arith.y"
{ yyval=yyvsp[0]; }
break;
case 10:
#line 61 "Arith.y"
{ yyval=yyvsp[0]; }
break;
case 11:
#line 65 "Arith.y"
{ new IfNode(yyvsp[-3],yyvsp[0]); }
break;
case 12:
#line 66 "Arith.y"
{
                                          yyval = new IfNode(yyvsp[-3],yyvsp[-1]);
                                          yyvsp[-4].addChild(yyvsp[0]);
                                       }
break;
case 13:
#line 72 "Arith.y"
{ yyval = new IfNode(yyvsp[-2],yyvsp[0]); }
break;
case 14:
#line 73 "Arith.y"
{ yyvsp[-4].addChild(new IfNode(yyvsp[-2],yyvsp[0])); }
break;
case 15:
#line 76 "Arith.y"
{yyval = new WhileNode(yyvsp[-2],yyvsp[0]); }
break;
case 16:
#line 80 "Arith.y"
{  yyval = yyvsp[-2]; }
break;
case 17:
#line 81 "Arith.y"
{  yyval = yyvsp[-1]; }
break;
case 18:
#line 84 "Arith.y"
{  yyval = new SStmtNode(yyvsp[0]); }
break;
case 19:
#line 85 "Arith.y"
{
                                                   yyvsp[-2].addChild(yyvsp[0]);
                                                   yyval = yyvsp[-2];
                                                }
break;
case 20:
#line 90 "Arith.y"
{ yyval = yyvsp[0]; }
break;
case 21:
#line 91 "Arith.y"
{ yyval = yyvsp[0]; }
break;
case 22:
#line 94 "Arith.y"
{ yyval = yyvsp[0]; }
break;
case 23:
#line 95 "Arith.y"
{ yyval = new EXPRN(yyvsp[-2],yyvsp[0]);}
break;
case 24:
#line 98 "Arith.y"
{ yyval = new PrintNode(); }
break;
case 25:
#line 99 "Arith.y"
{ yyval = yyvsp[0]; }
break;
case 26:
#line 100 "Arith.y"
{ yyval = yyvsp[0]; }
break;
case 27:
#line 103 "Arith.y"
{ yyval=new PrintNode(yyvsp[0]); }
break;
case 28:
#line 104 "Arith.y"
{
                                 yyvsp[-2].addChild(yyvsp[0]);
                                 yyval = yyvsp[-2];
                              }
break;
case 29:
#line 111 "Arith.y"
{ yyval = yyvsp[0]; }
break;
case 30:
#line 114 "Arith.y"
{ yyval = yyvsp[0]; }
break;
case 31:
#line 115 "Arith.y"
{ yyval = new OrNode(yyvsp[-2],yyvsp[0]); }
break;
case 32:
#line 118 "Arith.y"
{ yyval=yyvsp[0]; }
break;
case 33:
#line 119 "Arith.y"
{
                                    yyvsp[-2].add(yyvsp[0]);
                                    yyval = yyvsp[-2];
                                 }
break;
case 34:
#line 125 "Arith.y"
{ yyval= yyvsp[0]; }
break;
case 35:
#line 127 "Arith.y"
{ yyval=yyvsp[0]; }
break;
case 49:
#line 139 "Arith.y"
{ yyval= yyvsp[0];}
break;
case 50:
#line 140 "Arith.y"
{ yyval = new ExprNode(yyvsp[-2],yyvsp[0]);}
break;
case 51:
#line 143 "Arith.y"
{ yyval=yyvsp[0]; }
break;
case 52:
#line 144 "Arith.y"
{ yyval = new XorNode(yyvsp[-2],yyvsp[0]); }
break;
case 53:
#line 148 "Arith.y"
{ yyval = yyvsp[0]; }
break;
case 54:
#line 149 "Arith.y"
{ yyval = new AndNode(yyvsp[-2],yyvsp[0]); }
break;
case 55:
#line 153 "Arith.y"
{ yyval = yyvsp[0]; }
break;
case 56:
#line 154 "Arith.y"
{ yyval = new ArithNode(yyvsp[-2],EnumOp.MAS,yyvsp[0]);}
break;
case 57:
#line 155 "Arith.y"
{ yyval = new ArithNode(yyvsp[-2],EnumOp.MENOS,yyvsp[0]); }
break;
case 58:
#line 158 "Arith.y"
{ yyval = yyvsp[0]; }
break;
case 59:
#line 159 "Arith.y"
{ yyval = new TermNodeX(yyvsp[-2],EnumOp.POR,yyvsp[0]); }
break;
case 60:
#line 160 "Arith.y"
{ yyval = new TermNodeX(yyvsp[-2],EnumOp.DIV,yyvsp[0]); }
break;
case 61:
#line 161 "Arith.y"
{ yyval = new TermNodeX(yyvsp[-2],EnumOp.MODULO,yyvsp[0]); }
break;
case 62:
#line 162 "Arith.y"
{ yyval = new TermNodeX(yyvsp[-2],EnumOp.DIVENTERA,yyvsp[0]); }
break;
case 63:
#line 165 "Arith.y"
{ yyval = new FactorNode(EnumOp.MAS,yyvsp[0]); }
break;
case 64:
#line 166 "Arith.y"
{ yyval = new FactorNode(EnumOp.MENOS,yyvsp[0]); }
break;
case 65:
#line 167 "Arith.y"
{ yyval = yyvsp[0]; }
break;
case 66:
#line 170 "Arith.y"
{yyval = yyvsp[0]; }
break;
case 67:
#line 171 "Arith.y"
{ yyval = new PowerNode(yyvsp[-2],yyvsp[0]); }
break;
case 68:
#line 175 "Arith.y"
{yyval = yyvsp[0];}
break;
case 69:
#line 176 "Arith.y"
{yyval = yyvsp[0];}
break;
case 70:
#line 177 "Arith.y"
{yyval = yyvsp[0];}
break;
case 71:
#line 178 "Arith.y"
{yyval = yyvsp[0];}
break;
#line 773 "y.tab.c"
    }
    yyssp -= yym;
    yystate = *yyssp;
    yyvsp -= yym;
    yym = yylhs[yyn];
    if (yystate == 0 && yym == 0)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: after reduction, shifting from state 0 to\
 state %d\n", YYFINAL);
#endif
        yystate = YYFINAL;
        *++yyssp = YYFINAL;
        *++yyvsp = yyval;
        if (yychar < 0)
        {
            if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
            if (yydebug)
            {
                yys = 0;
                if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
                if (!yys) yys = "illegal-symbol";
                printf("yydebug: state %d, reading %d (%s)\n",
                        YYFINAL, yychar, yys);
            }
#endif
        }
        if (yychar == 0) goto yyaccept;
        goto yyloop;
    }
    if ((yyn = yygindex[yym]) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn];
    else
        yystate = yydgoto[yym];
#if YYDEBUG
    if (yydebug)
        printf("yydebug: after reduction, shifting from state %d \
to state %d\n", *yyssp, yystate);
#endif
    if (yyssp >= yyss + yystacksize - 1)
    {
        goto yyoverflow;
    }
    *++yyssp = yystate;
    *++yyvsp = yyval;
    goto yyloop;
yyoverflow:
    yyerror("yacc stack overflow");
yyabort:
    return (1);
yyaccept:
    return (0);
}
