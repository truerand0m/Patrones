public class Symbol{
   String name;
   int[] values;
   int type;
   int idx;

   public Symbol(String name,int type){
      this.name = name;
      this.type = type;
      this.idx = SymbolTable.usedvars++;
      this.values = new int[]{type,SymbolTable.usedvars++};
   }
   
   public Symbol(String name,int type,int idx){
      this.name = name;
      this.type = type;
      this.idx = idx;
      this.values = new int[]{type,idx};
   }

   String getKey(){
      return this.name;
   }

   int getValue(){
      return this.type;
   }
   
   int[] getValues(){
      return this.values;
   }
   
   int getIndex(){
      return this.values[1];
   }
   
   void setName(String name){
      this.name = name;
   }

   void setType(int type){
      this.type = type;
   }

   public String toString(){
      return "name:"+name+" type: "+type+" idx"+idx;
   }
}
