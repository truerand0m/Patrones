public class TypeTable{
   public static int[][] table = {
      {  -1,-1,-1,-1,-1 },
      {  -1,1,2,-1,-1   },
      {  -1,2,2,-1,-1   },
      {  -1,-1,-1,3,-1  },
      {  -1,-1,-1,-1,4  }
   };
   
   public static void main(String[] args){
      System.out.println(table[Integer.parseInt(args[0])][Integer.parseInt(args[1])]); 
   }
   
   public static String genConvCode(int ptype,int ctype){
      if(ptype == 2 && ctype == 1)
         return "i2f";
      return "";
   }
}
