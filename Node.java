/** Componente. The generic Node class. */
import java.util.ArrayList;

public class Node extends Object{
   Children childrenNodes;
   ParserVal value;
   int type;

   /* Added */
   Node node;
   int op;
   ArrayList<Node> nodos;

   public Children getChildren(){ return null; }

   /* Added */
   public ArrayList<Node> getNodos(){ return null; }

   /* added */
   public void addChild(Node n){}

   public Node getLeftChild(){ return null; }

   public Node getRightChild(){ return null; }

   public ParserVal getValue(){ return value; }

   public int getType(){ return type; }

   public void setValue(ParserVal nuevo){ value = nuevo; }

   public void setType(int nuevo){ type = nuevo; }

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

class FactorNode extends Node{
   int signo;
   Node f;
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

/* Esto me es util para nodos de hojas auxiliares,
   de forma Operador o Signo, Valor,
   ademas, estos pueden tener referencias a nodos de su
   mismo tipo
*/
/*
class Listable extends Node{
   String nodename;
   Listable(int op,Node node){
      nodename = "";
      this.op = op;
      this.node = node;
      this.nodos = new ArrayList<Node>();
   }

   Listable(int op,Node node,String nodename){
      this.nodename = nodename;
      this.op = op;
      this.node = node;
      this.nodos = new ArrayList<Node>();
   }

   public ArrayList<Node> getNodos(){
      return nodos;
   }

   public void addChild(Node n){
      nodos.add(n);
   }

   public String imprime(){
      System.out.println("dknow man!");
      return "Node\n "+"value: " + value +"\ntipo" + type;
   }

   //Debo fusionar el enum
   public void print(){
      System.out.println("<"+nodename+"> : "+EnumOp.getSymbol(op));
      node.print();
      System.out.println("Hijos:[");
      printChildren();
      System.out.println("]");
      System.out.println("</"+nodename+">");
   }

   public void printChildren(){
      for(int i=0;i<nodos.size();i++)
         nodos.get(i).print();
   }

   public void accept(Visitor v){
      v.visit(this);
   }
}

class AuxTermNode extends Listable{
   //Listable(int op,Node node,String nodename){
   public AuxTermNode(int op,Node n){
      super(op,n,"auxterm");

   }

   public void accept(Visitor v){
      v.visit(this);
   }
}

class TermNode extends Node{
   Node nodo;
   //Node hijos;
   ArrayList<Node> nodos;

   public TermNode(Node n){
      nodo = n;
      //hijos = null;
   }

   public TermNode(Node n,Node h){
      this.nodo = n;
      this.nodos = new ArrayList<Node>();
      this.nodos.add(h);
      for(int i=0;i<h.getNodos().size();i++)
         nodos.add(h.getNodos().get(i));
   }

   public void printChildren(){
      for(int i=0;i<nodos.size();i++)
         nodos.get(i).print();
   }
   public void print(){
      System.out.println("<Term Node>");
      System.out.println("#Hijos:"+nodos.size());
      nodo.print();
      printChildren();
      System.out.println("</TermNode>");
   }

   public void accept(Visitor v){
      v.visit(this);
   }
}
*/
/*
      ||||||||||||||||||   ||||        |||||||||||
      ||||           |||   ||||        |||      \\\
      |||            |||   ||||        |||      |||
      ||||||||||||||||||   |||||||||   ||||||||///
*/
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

class XorNode extends BinaryNode{
   public XorNode(Node l, Node r){
      super(l,r);
   }
   public void print(){
      System.out.println("Nodo Xor :");
      System.out.println("\nIzq[");
      getLeftChild().print();
      System.out.println("]");
      System.out.println("^");
      System.out.println("Der[");
      getRightChild().print();
      System.out.println("]");
   }

   public String imprime(){
      return " ^ ";
   }

   public void accept(Visitor v){
      v.visit(this);
   }
}


class EXPRN extends BinaryNode{
   public EXPRN(Node l, Node r){
      super(l,r);
   }
   public void print(){
      System.out.println("Nodo XPR :");
      System.out.println("\nIzq[");
      getLeftChild().print();
      System.out.println("]");
      System.out.println("|");
      System.out.println("Der[");
      getRightChild().print();
      System.out.println("]");
   }

   public String imprime(){
      return " | ";
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

class AndTestNode extends Node{
   Node node;
   ArrayList<Node> nodos;
   int type;

   //eliminar node
   public AndTestNode(Node n){
      this.node = n;
      nodos = new ArrayList<Node>();
      addChild(n);
   }

   public ArrayList<Node> getNodos(){
      return nodos;
   }

   public void addChild(Node n){
      this.nodos.add(n);
   }

   public void print(){
      System.out.println("AndTestNode\n "+"value: " + value +"\ntipo" + type);
      printChildren();
   }

   public String imprime(){
      //return "Node\n "+"value: " + value +"\ntipo" + type;
      String temp= "AndTestNode: "+node.imprime();
      for(int i=0;i<nodos.size();i++){
         temp+= nodos.get(i).imprime();
      }
      return temp;
   }

   public void printChildren(){
      for(int i=0;i<nodos.size();i++){
         nodos.get(i).print();
      }
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

/* Test */
class SStmtNode extends Node{
   Node node;
   ArrayList<Node> nodos;
   int type;

   //eliminar node
   public SStmtNode(Node n){
      this.node = n;
      nodos = new ArrayList<Node>();
      addChild(n);
   }

   public ArrayList<Node> getNodos(){
      return nodos;
   }

   public void addChild(Node n){
      this.nodos.add(n);
   }

   public void print(){
      //System.out.println("SStmtNode\n "+"value: " + value +"\ntipo" + type);
      printChildren();
   }

   public String imprime(){
      //return "Node\n "+"value: " + value +"\ntipo" + type;
      String temp= "SStmtNode: "+node.imprime();
      for(int i=0;i<nodos.size();i++){
         temp+= nodos.get(i).imprime();
      }
      return temp;
   }

   public void printChildren(){
      for(int i=0;i<nodos.size();i++){
         nodos.get(i).print();
      }
   }

   public void accept(Visitor v){
      v.visit(this);
   }
}

class StmtNode extends Node{
   Node node;
   ArrayList<Node> nodos;
   int type;

   //eliminar node
   public StmtNode(Node n){
      this.node = n;
      nodos = new ArrayList<Node>();
      addChild(n);
   }

   public ArrayList<Node> getNodos(){
      return nodos;
   }

   public void addChild(Node n){
      this.nodos.add(n);
   }

   public void print(){
      System.out.println("StmtNode\n "+"value: " + value +"\ntipo" + type);
      printChildren();
   }

   public String imprime(){
      //return "Node\n "+"value: " + value +"\ntipo" + type;
      String temp= "SStmtNode: "+node.imprime();
      for(int i=0;i<nodos.size();i++){
         temp+= nodos.get(i).imprime();
      }
      return temp;
   }

   public void printChildren(){
      for(int i=0;i<nodos.size();i++){
         nodos.get(i).print();
      }
   }

   public void accept(Visitor v){
      v.visit(this);
   }
}

class PrintNode extends Node{
   Node node;
   ArrayList<Node> nodos;
   int type;

   public PrintNode(){
      //is this right?
      this.node = null;
      this.nodos = new ArrayList<Node>();
   }

   //eliminar node
   public PrintNode(Node n){
      this.node = n;
      nodos = new ArrayList<Node>();
      addChild(n);
   }

   public ArrayList<Node> getNodos(){
      return nodos;
   }

   public void addChild(Node n){
      this.nodos.add(n);
   }

   public void print(){
      System.out.println("PrintNode: ");
      printChildren();
   }

   public String imprime(){
      //return "Node\n "+"value: " + value +"\ntipo" + type;
      String temp= "PrintNode: "+node.imprime();
      for(int i=0;i<nodos.size();i++){
         temp+= nodos.get(i).imprime();
      }
      return temp;
   }

   public void printChildren(){
      for(int i=0;i<nodos.size();i++){
         nodos.get(i).print();
      }
   }

   public void accept(Visitor v){
      v.visit(this);
   }
}

class IfNode extends BinaryNode{
   ArrayList<Node> nodos;
   public IfNode(Node test,Node suite){
      super(test,suite);
      this.nodos = new ArrayList<Node>();
   }

   public void print(){
      System.out.println("If:[");
      getLeftChild().print();
      System.out.println("]Then:[");
      getRightChild().print();
      System.out.println("]");
      //imprimo los elif
      for(int i=0;i<nodos.size();i++){
         System.out.println("Elif[");
         nodos.get(i).print();
         System.out.println("]");
      }
   }

   public void addChild(Node n){
      nodos.add(n);
   }

   public String imprime(){
      return "IfNode";
   }

   public void accept(Visitor v){
      v.visit(this);
   }
}

class WhileNode extends BinaryNode{
   public WhileNode(Node test,Node suite){
      super(test,suite);
   }

   public void print(){
      System.out.println("While:");
      System.out.println("\nIzq[");
      getLeftChild().print();
      System.out.println("]");
      System.out.println(" = ");
      System.out.println("Der[");
      getRightChild().print();
      System.out.println("]");
   }

   public String imprime(){
      return " While ";
   }

   public void accept(Visitor v){
      v.visit(this);
   }
}

//List
class CompNode extends Node{
   int op;
   Node node;
   ArrayList<Node> nodos;
   public CompNode(Node n){
      // o o
      this.op = -1;
      node = n;
      this.nodos = new ArrayList<Node>();
   }

   public CompNode(int opp,Node n){
      op = opp;
      node = n;
      nodos = new ArrayList<Node>();
   }

   public void addChild(Node n){
      nodos.add(n);
   }

   public void print(){
      if(op==-1){
         System.out.println("CompNode:");
         node.print();
         for(int i=0;i<nodos.size();i++)
            nodos.get(i).print();
      }else{
         System.out.println(op);
         node.print();
      }
   }

   public String imprime(){
      return "Comp:";
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
