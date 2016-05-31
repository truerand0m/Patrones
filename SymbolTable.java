import java.util.Iterator;
import java.util.*;

public class SymbolTable {
   private HashMap st = new HashMap();

   public void put(Symbol s) {
      st.put(s.getKey(), s.getValue());
   }
   
   
   public String get(String key){
      return st.get(key).toString();
   }

   /*
       Simbolo LookUp(String var):
       Busca en la tabla la información de la variable con nombre var.
       Si no la encuentra regresa null.
   */
   public Symbol lookUp(String key){
      if(st.get(key)!=null)
         return new Symbol(key,(String)st.get(key));
      return null;
   }

   public int size(){
      return st.size();
   }

   /* Show all the vars */
   public void showAll() {
      Iterator itval = st.entrySet().iterator();
      while(itval.hasNext())
         System.out.println(itval.next());
   }

   /***********************************************************************
   * Test
   **********************************************************************/
   public static void main(String[] args) {
      SymbolTable st = new SymbolTable();

      // make some symbols
      Symbol[] symbols = new Symbol[]{
         new Symbol("a","int"),
         new Symbol("b","float"),
         new Symbol("c","Object")
      };

      //add them
      for(int i=0;i<symbols.length;i++)
         st.put(symbols[i]);

      // searching
      System.out.println("\nProbando LookUp");
      System.out.println(st.lookUp("d"));
      System.out.println(st.lookUp("a"));

      System.out.println("\nMuestro todas las llaves:");
      st.showAll();
   }
}
