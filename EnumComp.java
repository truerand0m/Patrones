/* '<'|'>'|'=='|'>='|'<='|'<>'|'!='|'in'|'not' 'in'|'is'|'is' 'not' */
public class EnumComp{
/*
LE GR LEQ                < | > | <=
GRQ EQUALS DIFF EQ PA    >= | == | != | =
*/
   public static final int LE = 1;
   public static final int GR = 2;
   public static final int EQUALS = 3;
   public static final int GRQ = 4;
   public static final int LEQ = 5;
   public static final int DIFF = 6;
   public static final int IN = 7;
   public static final int NOTIN = 8;
   public static final int IS = 9;
   public static final int ISNOT = 10;

   static String getSymbol(int n){
      switch(n){
         case 1:
            return "<";
         case 2:
            return ">";
         case 3:
            return "==";
         case 4:
            return ">=";
         case 5:
            return "<=";
         case 6:
            return "!=";
         case 7:
            return "IN";
         case 8:
            return "NOTIN";
         case 9:
            return "IS";
         case 10:
            return "ISNOT";
         default:
            return "?";
      }
   }
}
