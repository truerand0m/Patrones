/* Types:
* 1 - Integer
* 2 - Float
* 3 - Boolean
* 4 - String
*/
public class Leaf extends Node{
   public void print(){
      System.out.println("Soy hoja:");
   }

   public String imprime(){
      return "Hoja: ";
   }
}

class StringLeaf extends Leaf
{
   public StringLeaf(String v){
      value = new ParserVal(v);
      type = 4;
   }

   public void print(){
      System.out.println("Soy hoja cadena: "+this.getClass()+" : "+this.value);
   }

   public String imprime(){
      return "C:"+this.value.sval;
   }

   public void accept(Visitor v){
      v.visit(this);
   }
}

class IntLeaf extends Leaf{
   public IntLeaf(int i){
      value = new ParserVal(i);
      type = 1;
   }

   public void print(){
      System.out.println("Soy hoja entera: "+this.getClass()+" : "+this.value.ival);
   }

   public String imprime(){
      return "I:"+this.value.ival;
   }

   public void accept(Visitor v){
      v.visit(this);
   }
}

class FloatLeaf extends Leaf{
   public FloatLeaf(double i){
      value = new ParserVal(i);
      type = 2;
   }

   public void print(){
      System.out.println("Soy hoja real: "+this.getClass()+" : "+this.value.dval);
   }

   public String imprime(){
      return "R:"+this.value.dval;
   }

   public void accept(Visitor v){
      v.visit(this);
   }
}

class IdentifierLeaf extends Leaf{
   String name;

   public IdentifierLeaf(String id){
      name = id;
   }

   public void print(){
      System.out.println("Soy un identificador. "+" : "+this.name);
   }

   public String imprime(){
      return "Id:"+this.name;
   }

   public void accept(Visitor v){
      v.visit(this);
   }
}
