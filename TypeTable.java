public class TypeTable{
   /*
      Renglon 1:= 1,2 = 2  ie Int y Float = Float
   */
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
}
