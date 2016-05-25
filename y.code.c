#ifndef lint
static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";
#endif
#define YYBYACC 1
#line 2 "Arith.y"
  import java.lang.Math;
  import java.io.*;
#line 9 "y.code.c"
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
#define YYTABLESIZE 718
#define YYFINAL 10
#ifndef YYDEBUG
#define YYDEBUG 0
#endif
#define YYMAXTOKEN 304
#ifndef YYSTYPE
typedef int YYSTYPE;
#endif
extern short yylhs[];
extern short yylen[];
extern short yydefred[];
extern short yydgoto[];
extern short yysindex[];
extern short yyrindex[];
extern short yygindex[];
extern short yytable[];
extern short yycheck[];
#if YYDEBUG
extern char *yyname[];
extern char *yyrule[];
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
#line 203 "Arith.y"
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
#line 137 "y.code.c"
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
#line 25 "Arith.y"
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
#line 55 "Arith.y"
{ yyval = yyvsp[0]; }
break;
case 5:
#line 60 "Arith.y"
{ yyval = yyvsp[0]; }
break;
case 6:
#line 61 "Arith.y"
{
                              yyvsp[-1].addChild(yyvsp[0]);
                              yyval = yyvsp[-1];
                           }
break;
case 7:
#line 67 "Arith.y"
{ yyval=yyvsp[0];}
break;
case 8:
#line 68 "Arith.y"
{ yyval=yyvsp[0]; }
break;
case 9:
#line 71 "Arith.y"
{ yyval=yyvsp[0]; }
break;
case 10:
#line 72 "Arith.y"
{ yyval=yyvsp[0]; }
break;
case 11:
#line 77 "Arith.y"
{ new IfNode(yyvsp[-3],yyvsp[0]); }
break;
case 12:
#line 78 "Arith.y"
{
                                          yyval = new IfNode(yyvsp[-3],yyvsp[-1]);
                                          yyvsp[-4].addChild(yyvsp[0]);
                                       }
break;
case 13:
#line 84 "Arith.y"
{ yyval = new IfNode(yyvsp[-2],yyvsp[0]); }
break;
case 14:
#line 85 "Arith.y"
{ yyvsp[-4].addChild(new IfNode(yyvsp[-2],yyvsp[0])); }
break;
case 15:
#line 88 "Arith.y"
{yyval = new WhileNode(yyvsp[-2],yyvsp[0]); }
break;
case 16:
#line 92 "Arith.y"
{  yyval = yyvsp[-2]; }
break;
case 17:
#line 93 "Arith.y"
{  yyval = yyvsp[-1]; }
break;
case 18:
#line 96 "Arith.y"
{  yyval = new SStmtNode(yyvsp[0]); }
break;
case 19:
#line 97 "Arith.y"
{
                                                   yyvsp[-2].addChild(yyvsp[0]);
                                                   yyval = yyvsp[-2];
                                                }
break;
case 20:
#line 104 "Arith.y"
{ yyval = yyvsp[0]; }
break;
case 21:
#line 105 "Arith.y"
{ yyval = yyvsp[0]; }
break;
case 22:
#line 107 "Arith.y"
{ yyval = new PrintNode(); }
break;
case 23:
#line 108 "Arith.y"
{ yyval = yyvsp[0]; }
break;
case 24:
#line 109 "Arith.y"
{ yyval = yyvsp[0]; }
break;
case 25:
#line 112 "Arith.y"
{ yyval=new PrintNode(yyvsp[0]); }
break;
case 26:
#line 113 "Arith.y"
{
                                 yyvsp[-2].addChild(yyvsp[0]);
                                 yyval = yyvsp[-2];
                              }
break;
case 27:
#line 122 "Arith.y"
{ yyval = yyvsp[0]; }
break;
case 28:
#line 123 "Arith.y"
{ yyval = new EXPRN(yyvsp[-4],yyvsp[-1]);}
break;
case 29:
#line 127 "Arith.y"
{ yyval = yyvsp[0]; }
break;
case 30:
#line 131 "Arith.y"
{ yyval = yyvsp[0]; }
break;
case 31:
#line 132 "Arith.y"
{ yyval = new OrNode(yyvsp[-2],yyvsp[0]); }
break;
case 32:
#line 136 "Arith.y"
{ yyval=yyvsp[0]; }
break;
case 33:
#line 137 "Arith.y"
{
                                    yyvsp[-2].add(yyvsp[0]);
                                    yyval = yyvsp[-2];
                                 }
break;
case 34:
#line 143 "Arith.y"
{ yyval= yyvsp[0]; }
break;
case 35:
#line 145 "Arith.y"
{ yyval=yyvsp[0]; }
break;
case 36:
#line 147 "Arith.y"
{ yyval = new CompNode(yyvsp[0]); }
break;
case 37:
#line 148 "Arith.y"
{ yyvsp[-2].addChild(new CompNode(EnumComp.LE,yyvsp[0]));}
break;
case 38:
#line 149 "Arith.y"
{ yyvsp[-2].addChild(new CompNode(EnumComp.GR,yyvsp[0]));}
break;
case 39:
#line 150 "Arith.y"
{ yyvsp[-2].addChild(new CompNode(EnumComp.EQUALS,yyvsp[0]));}
break;
case 40:
#line 151 "Arith.y"
{ yyvsp[-2].addChild(new CompNode(EnumComp.GRQ,yyvsp[0]));}
break;
case 41:
#line 152 "Arith.y"
{ yyvsp[-2].addChild(new CompNode(EnumComp.LEQ,yyvsp[0]));}
break;
case 42:
#line 153 "Arith.y"
{ yyvsp[-2].addChild(new CompNode(EnumComp.DIFF,yyvsp[0]));}
break;
case 43:
#line 154 "Arith.y"
{ yyvsp[-2].addChild(new CompNode(EnumComp.IN,yyvsp[0]));}
break;
case 44:
#line 155 "Arith.y"
{ yyvsp[-2].addChild(new CompNode(EnumComp.NOTIN,yyvsp[0]));}
break;
case 45:
#line 156 "Arith.y"
{ yyvsp[-2].addChild(new CompNode(EnumComp.IS,yyvsp[0]));}
break;
case 46:
#line 157 "Arith.y"
{ yyvsp[-2].addChild(new CompNode(EnumComp.ISNOT,yyvsp[0]));}
break;
case 47:
#line 160 "Arith.y"
{ yyval= yyvsp[0];}
break;
case 48:
#line 161 "Arith.y"
{ yyval = new ExprNode(yyvsp[-2],yyvsp[0]);}
break;
case 49:
#line 164 "Arith.y"
{ yyval=yyvsp[0]; }
break;
case 50:
#line 165 "Arith.y"
{ yyval = new XorNode(yyvsp[-2],yyvsp[0]); }
break;
case 51:
#line 169 "Arith.y"
{ yyval = yyvsp[0]; }
break;
case 52:
#line 170 "Arith.y"
{ yyval = new AndNode(yyvsp[-2],yyvsp[0]); }
break;
case 53:
#line 175 "Arith.y"
{ yyval = yyvsp[0]; }
break;
case 54:
#line 176 "Arith.y"
{ yyval = new ArithNode(yyvsp[-2],EnumOp.MAS,yyvsp[0]);}
break;
case 55:
#line 177 "Arith.y"
{ yyval = new ArithNode(yyvsp[-2],EnumOp.MENOS,yyvsp[0]); }
break;
case 56:
#line 180 "Arith.y"
{ yyval = yyvsp[0]; }
break;
case 57:
#line 181 "Arith.y"
{ yyval = new TermNodeX(yyvsp[-2],EnumOp.POR,yyvsp[0]); }
break;
case 58:
#line 182 "Arith.y"
{ yyval = new TermNodeX(yyvsp[-2],EnumOp.DIV,yyvsp[0]); }
break;
case 59:
#line 183 "Arith.y"
{ yyval = new TermNodeX(yyvsp[-2],EnumOp.MODULO,yyvsp[0]); }
break;
case 60:
#line 184 "Arith.y"
{ yyval = new TermNodeX(yyvsp[-2],EnumOp.DIVENTERA,yyvsp[0]); }
break;
case 61:
#line 187 "Arith.y"
{ yyval = new FactorNode(EnumOp.MAS,yyvsp[0]); }
break;
case 62:
#line 188 "Arith.y"
{ yyval = new FactorNode(EnumOp.MENOS,yyvsp[0]); }
break;
case 63:
#line 189 "Arith.y"
{ yyval = yyvsp[0]; }
break;
case 64:
#line 192 "Arith.y"
{yyval = yyvsp[0]; }
break;
case 65:
#line 193 "Arith.y"
{ yyval = new PowerNode(yyvsp[-2],yyvsp[0]); }
break;
case 66:
#line 197 "Arith.y"
{yyval = yyvsp[0];}
break;
case 67:
#line 198 "Arith.y"
{yyval = yyvsp[0];}
break;
case 68:
#line 199 "Arith.y"
{yyval = yyvsp[0];}
break;
case 69:
#line 200 "Arith.y"
{yyval = yyvsp[0];}
break;
#line 571 "y.code.c"
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
