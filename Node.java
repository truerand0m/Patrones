/** Componente. The generic Node class. */
import java.util.ArrayList;

public class Node extends Object{

   Children childrenNodes;
   ParserVal value;
   int type;

   public Children getChildren(){
      return null;
   }

   public Node getLeftChild(){
      return null;
   }

   public Node getRightChild(){
      return null;
   }

   public ParserVal getValue(){
      return value;
   }

   public int getType(){
      return type;
   }

   public void setValue(ParserVal nuevo){
      value = nuevo;
   }

   public void setType(int nuevo){
      type = nuevo;
   }

   public void print(){
      System.out.println("Node\n "+"value: " + value +"\ntipo" + type);
      printChildren();
   }

   public String imprime(){
      return "Node\n "+"value: " + value +"\ntipo" + type;
   }

   public void printChildren(){
      return;
   }

   public void accept(Visitor v){
      v.visit(this);
   }
}

class EmptyNode extends Node{
   public String imprime(){
      return "";
   }
}

class EndNode extends Node{
   public String imprime(){
      return "";
   }
}
/** Componente. The generic Node class. */
class Composite extends Node{
   public Children getChildren(){
      return childrenNodes;
   }
}

class BinaryNode extends Composite{
   public BinaryNode(Node l, Node r){
      childrenNodes = new ChildrenArrayList(2);
      this.setLeftChild(l);
      this.setRightChild(r);
   }

   public Node getLeftChild(){
      return childrenNodes.getLeftChild();
   }

   public Node getRightChild(){
      return childrenNodes.getRightChild();
   }

   public void setLeftChild(Node l){
      childrenNodes.setLeftChild(l);
   }

   public void setRightChild(Node r){
      childrenNodes.setRightChild(r);
   }

   public void printChildren(){
      getLeftChild().print();
      getRightChild().print();
   }
}


class PowerNode extends BinaryNode{
   public PowerNode(Node l, Node r){
      super(l,r);
   }

   public void print(){
      getLeftChild().print();
      System.out.println("**");
      getRightChild().print();
   }

   public String imprime(){
      return "**";
   }

   public void accept(Visitor v){
      v.visit(this);
   }
}

/* ----------------------------------------------------------------- */
class FactorNode extends Node{
   int signo;
   Node f;
   //OMITIMOS EL CONSTRUCTOR  CUANDO HAY UN SOLO PARAMETRO
   //('+'|'-') factor | power
   public FactorNode(int signo,Node f){
      this.signo = signo;
      this.f = f;
   }

   public Node getLeftChild(){
      return f;
   }

   public void print(){
      System.out.println("Nodo factor: "+EnumOp.getSymbol(signo)+" ");
      f.print();
   }

   public String imprime(){
      return "F:"+EnumOp.getSymbol(signo);
   }

   public void accept(Visitor v){
      v.visit(this);
   }
}

class TermNodeX extends Node{
   //term: factor (('*'|'/'|'%'|'//') factor)*
   int op;
   ArrayList<Node> nodes;
   Node l;
   Node p;
   public TermNodeX(Node l,int op,Node p){
      this.l = l;
      this.op = op;
      this.p = p;
      this.nodes = new ArrayList<Node>();
   }

   public void addChildX(Node n){
      nodes.add(n);
   }

   public Node getLeftChild(){
      return l;
   }

   public Node getRightChild(){
      return p;
   }

   public void print(){
      System.out.println("\nIzq[");
      l.print();
      System.out.println("]");
      System.out.println(EnumOp.getSymbol(op));
      System.out.println("Der[");
      p.print();
      System.out.println("]");
   }

   public String imprime(){
      return "T:"+EnumOp.getSymbol(op);
   }

   public void accept(Visitor v){
      v.visit(this);
   }
}

//egrep -o "(\s(rs\S+))" data.txt > filter.txt
class ArithNode extends Node{
   //term: factor (('*'|'/'|'%'|'//') factor)*
   int op;
   ArrayList<Node> nodes;
   Node l;
   Node p;
   public ArithNode(Node l,int op,Node p){
      this.l = l;
      this.op = op;
      this.p = p;
      this.nodes = new ArrayList<Node>();
   }

   public void addChild(Node n){
      nodes.add(n);
   }

   public Node getLeftChild(){
      return l;
   }

   public Node getRightChild(){
      return p;
   }

   public void print(){
      System.out.println("Nodo Arith :");
      System.out.println("\nIzq[");
      l.print();
      System.out.println("]");
      System.out.println(EnumOp.getSymbol(op));
      System.out.println("Der[");
      p.print();
      System.out.println("]");
   }

   public String imprime(){
      return "Ar:"+EnumOp.getSymbol(op);
   }

   public void accept(Visitor v){
      v.visit(this);
   }
}

//xor_expr: and_expr ('^' and_expr)*
//and_expr: arith_expr ('&' arith_expr)*
class AndNode extends BinaryNode{
   public AndNode(Node l, Node r){
      super(l,r);
   }
   public void print(){
      System.out.println("Nodo And :");
      System.out.println("\nIzq[");
      getLeftChild().print();
      System.out.println("]");
      System.out.println("&");
      System.out.println("Der[");
      getRightChild().print();
      System.out.println("]");
   }

   public String imprime(){
      return " & ";
   }

   public void accept(Visitor v){
      v.visit(this);
   }
}

class OrNode extends BinaryNode{
   public OrNode(Node l, Node r){
      super(l,r);
   }
   public void print(){
      System.out.println("Nodo Or :");
      System.out.println("\nIzq[");
      getLeftChild().print();
      System.out.println("]");
      System.out.println(" or ");
      System.out.println("Der[");
      getRightChild().print();
      System.out.println("]");
   }

   public String imprime(){
      return " or ";
   }

   public void accept(Visitor v){
      v.visit(this);
   }
}

class ExprNode extends BinaryNode{
   public ExprNode(Node l, Node r){
      super(l,r);
   }
   public void print(){
      System.out.println("Nodo Expr :");
      System.out.println("\nIzq[");
      getLeftChild().print();
      System.out.println("]");
      System.out.println(" = ");
      System.out.println("Der[");
      getRightChild().print();
      System.out.println("]");
   }

   public String imprime(){
      return " = ";
   }

   public void accept(Visitor v){
      v.visit(this);
   }
}

/* -----------------------------    ------------------------------------ */
class AST{
   Node root;
   public AST(Node node){
      root = node;
   }

   public void print(){
      root.print();
   }

   public static void main(String[] args){
      Node hoja = new StringLeaf("cadena");
      AST ast = new AST(hoja);
      ast.print();
   }
}
