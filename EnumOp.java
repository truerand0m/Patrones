/*
%token MAS MENOS POR POTENCIA DIV  /* + | - | * | ** | /
%token DIVENTERA MODULO LE GR LEQ /* // | % | < | > | <=
%token GRQ EQUALS DIFF EQ PA /* >= | == | != | = | (
*/
public class EnumOp{
   public static final int MAS = 1;
   public static final int MENOS = 2;
   public static final int POR = 3;
   public static final int POTENCIA = 4;
   public static final int DIV = 5;
   public static final int DIVENTERA = 6;
   public static final int MODULO = 7;
   public static final int LE = 8;
   public static final int GR = 9;
   public static final int EQUALS = 10;
   public static final int GRQ = 11;
   public static final int LEQ = 12;
   public static final int DIFF = 13;
   public static final int IN = 14;
   public static final int NOTIN = 15;
   public static final int IS = 16;
   public static final int ISNOT = 17;

   static String getSymbol(int n){
      switch(n){
         case 1:
            return "+";
         case 2:
            return "-";
         case 3:
            return "*";
         case 4:
            return "**";
         case 5:
            return "/";
         case 6:
            return "//";
         case 7:
            return "%";
         //
         case 8:
            return "<";
         case 9:
            return ">";
         case 10:
            return "==";
         case 11:
            return ">=";
         case 12:
            return "<=";
         case 13:
            return "!=";
         case 14:
            return "IN";
         case 15:
            return "NOTIN";
         case 16:
            return "IS";
         case 17:
            return "ISNOT";
         default:
            return "?";
      }
   }
   
   //Metodo para generar la instruccion, dependiendo
   //del tipo y la instruccion
   public static String getOpcode(int type,int op){
      /*
      *Types:
      * 0 null1 ,Integer, Float Boolean4 - String
      */
      String opcode = "";
      /* La numeracion de ops empiezan en 1: esto es un error notime*/
      String[] ops = {"","add","sub","mul","dknowpow","div","divi","rem"};
      if(op<=ops.length)
         opcode = ops[op];
      switch(type){
         case 0:
            opcode = "x"+opcode;
            break;
         case 1:
            opcode = "i"+opcode;
            break;
         case 2:
            opcode = "f"+opcode;
            break;
         //Me hace falta definir como tratar las operaciones
         //con booleanos y cadenas
         /*
         case 3:
            opcode = "i"+opcode;
            break;
         case 3:
            opcode = "i"+opcode;
            break;
         */
         default:
            opcode = "";
      }
      return opcode;
   }
   
   public static void main(String[] args){
      //'+'|'-'
      //'*'|'/'|'%'|'//')
      //resta de enteros
      int type = Integer.parseInt(args[0]);
      int op = Integer.parseInt(args[1]);
      System.out.println(getOpcode(type,op));
   }
}
