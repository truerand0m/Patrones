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
         default:
            return "?";
      }
   }
}
