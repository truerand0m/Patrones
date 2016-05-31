public class Symbol{
   String name;
   String type;

   // key -> name
   // type -> value
   public Symbol(String name,String type){
      this.name = name;
      this.type = type;
   }

   String getKey(){
      return this.name;
   }

   String getValue(){
      return this.type;
   }

   void setName(String name){
      this.name = name;
   }

   void setType(String type){
      this.type = type;
   }

   public String toString(){
      return "name:"+name+"\ttype: "+type;
   }
}
