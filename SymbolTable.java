import java.util.Iterator;
import java.util.*;

public class SymbolTable {
   static int usedvars=0;
   private HashMap st = new HashMap();

   public void put(Symbol s) {
      st.put(s.getKey(), s.getValues());
   }
   
   static void imprime(Object ar){
      int[] arr = (int[])ar;
      for(int i=0;i<arr.length;i++)
         System.out.println(arr[i]);
   }
   
   public int[] lookUp(String key){
      if(st.get(key)!=null){
         int[] vararray = getVarInfo(key);
         return vararray;
      }
      return null;
   }

   public int[] getVarInfo(String key){
      Object o = st.get(key);
      return (int[])o;
   }
   
   public int getVarType(String key){
      if(getVarInfo(key)!=null)
         return getVarInfo(key)[0];
      return -1;
   }
   
   public int getVarIndex(String key){
      if(getVarInfo(key)!=null)
         return getVarInfo(key)[1];
      return -1;
   }
   
   public int size(){
      return st.size();
   }
   
   public static void imprime(int[] arr){
      System.out.println("type; "+arr[0]+"idx; "+arr[1]);
   }
   
   public void showAll() {
      Iterator it = st.entrySet().iterator();
      while(it.hasNext()){
         Map.Entry pair = (Map.Entry)it.next();
         System.out.println("name "+pair.getKey());
         int[] c= lookUp(""+pair.getKey());
         imprime(c);
      }
   }
}
