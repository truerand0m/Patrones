/** Componente. The generic Node class. */
import java.util.ArrayList;
/*
*/
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
   public void addChilds(ArrayList<Node> nodes){}
   /* added */
   public void addChild(Node n){}
   /* added */
   public Node getChild(){ return null; }

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

/*
      ||||||||||||||||||   ||||        |||||||||||
      |||            |||   ||||        |||      \\\
      |||            |||   ||||        |||      |||
      ||||||||||||||||||   |||||||||   ||||||||///
*/

//egrep -o "(\s(rs\S+))" data.txt > filter.txt

//xor_expr: and_expr ('^' and_expr)*
//and_expr: arith_expr ('&' arith_expr)*
   
/* No lo uso */
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

// I dont know what im doing
class Lista extends Node{
   public Lista(Node n){
      this.nodos = new ArrayList<Node>();
      nodos.add(n);
   }

   public void addChild(Node n){
      nodos.add(n);
   }

   public ArrayList<Node> getNodos(){
      return nodos;
   }
}

/* CHECK No lo uso */
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

//rename this
class IFNodeMejorado extends BinaryNode{
   ArrayList<Node> hijos;
   public IFNodeMejorado(Node test,Node suite){
      super(test,suite);
      this.hijos = new ArrayList<Node>();
   }
   
   public void print(){
      System.out.println("<IF>:");
      System.out.println("\nCondicion[");
      getLeftChild().print();
      System.out.println("]");
      System.out.println(" THENIF ");
      System.out.println("[");
      getRightChild().print();
      if(hijos.size()>0){
         System.out.println("<IFHijos>:{");
         printChildren();
         System.out.println("}</IFHijos>");
      }
      System.out.println("]\n</IF>");
   }
   
   public void printChildren(){
      for(int i=0;i<hijos.size();i++)
         hijos.get(i).print();
   }

   public void addChild(Node n){
      hijos.add(n);
   }
   
   /* */
   public void addChilds(ArrayList<Node> nodes){
      for(int i=0; i< nodes.size(); i++){
         hijos.add(nodes.get(i));
      }
   }
   
   /* */ 
   public ArrayList<Node> getChilds(){
      return hijos;
   }
   
   public String imprime(){
      return " If ";
   }

   public void accept(Visitor v){
      v.visit(this);
   }
}

/* Wrapper para nodos elif */
class ElifNode extends BinaryNode{
   ArrayList<Node> hijos;
   public ElifNode(Node test,Node suite){
      super(test,suite);
      this.hijos = new ArrayList<Node>();
      hijos.add(new SingleElifNode(test,suite));
   }
   
   public void addChild(Node n){
      this.hijos.add(n);
   }
   
   public void print(){
      System.out.println("<ELIF>:");
      System.out.println("\nCond(");
      getLeftChild().print();
      System.out.println(")");
      System.out.println(" ELIFTHEN ");
      System.out.println("[");
      getRightChild().print();
      System.out.println("]\n</ELIF>");
   }
   
   public ArrayList<Node> getNodos(){
      return this.hijos;
   }

   public String imprime(){
      return " ELIF ";
   }

   public void accept(Visitor v){
      v.visit(this);
   }
}

class SingleElifNode extends BinaryNode{
   public SingleElifNode(Node test,Node suite){
      super(test,suite);
   }
   
   public void print(){
      System.out.println("<ELIF>:");
      System.out.println("\nCond(");
      getLeftChild().print();
      System.out.println(")");
      System.out.println(" ELIFTHEN ");
      System.out.println("[");
      getRightChild().print();
      System.out.println("]\n</ELIF>");
   }

   public String imprime(){
      return " ELIF ";
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
      System.out.println("<While>:");
      System.out.println("\nIzq[");
      getLeftChild().print();
      System.out.println("]");
      System.out.println(" = ");
      System.out.println("Der[");
      getRightChild().print();
      System.out.println("]\n</While>");
   }

   public String imprime(){
      return " While ";
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
      //String temp= "SStmtNode: "+node.imprime();
      String temp= "SStmtNode: ";
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
/* CHECK: Asignar lo del lado derecho al izquierdo */ 
/* THis is the node for Assignation */
class EXPRN extends BinaryNode{
   public EXPRN(Node l, Node r){
      super(l,r);
      setType();
   }
   public void print(){
      System.out.println("Nodo XPR_STMT :");
      System.out.println("\nIzq[");
      getLeftChild().print();
      //SEMANTICTEST: Sabemos que el lado izq es un id, tomamos el 
      //id y lo agregamos a la tabla
      /*
      IdentifierLeaf idlf = (IdentifierLeaf)getLeftChild();
      //DEBUGSystem.out.println("MUAJAJA id "+idlf.name);
      Parser.symtable.put(new Symbol(idlf.name,"undefined"));
      */
      //FIN SEMANTICTEST
      System.out.println("]");
      System.out.println("=");
      System.out.println("Der[");
      getRightChild().print();
      System.out.println("]");
   }

   public String imprime(){
      return this.getLeftChild().imprime()+" = "+this.getRightChild().imprime();
   }

   public void accept(Visitor v){
      v.visit(this);
   }
   
   public void setType(){
      IdentifierLeaf idlf = (IdentifierLeaf)getLeftChild();
      //DEBUGSystem.out.println("MUAJAJA id "+idlf.name);
      //DEBUG
      int rtype = getRightChild().getType();
      Parser.symtable.put(new Symbol(idlf.name,""+rtype));
      System.out.println("tipe "+idlf.imprime()+" -> "+rtype);
      System.out.println("tipe de la tabla: "+Parser.symtable.get(idlf.name));
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
   
   public ArrayList<Node> getChilds(){
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
      String temp= "PrintNode: ";
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

/* CHECK: Falta asignar tipo */
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

/* CHECK: Falta asignar tipo */
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

/* CHECK: Falta asignar tipo */
class NotNode extends Node{
   Node node;
   //('+'|'-') factor | power
   public NotNode(Node n){
      node = n;
   }

   public Node getChild(){
      return node;
   }

   //Solo es para imprimir
   public Node getLeftChild(){
      return node;
   }

   public void print(){
      System.out.println("Nodo NOT:\nNOT");
      node.print();
   }

   public String imprime(){
      return "NOT";
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
class CmpNode extends Node{
   //term: factor (('*'|'/'|'%'|'//') factor)*
   int op;
   ArrayList<Node> nodes;
   Node l;
   Node p;
   public CmpNode(Node l,int op,Node p){
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
      return "Cmp:"+EnumOp.getSymbol(op);
   }

   public void accept(Visitor v){
      v.visit(this);
   }
}

/* CHECK: Falta asignar tipo */
/* CHECK no lo uso */
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

/* CHECK: Falta asignar tipo */
// expr: xor_expr ('|' xor_expr)*
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

/* CHECK: Falta asignar tipo */
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

/* CHECK: Falta asignar tipo */
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
      setType();
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
   
   public void setType(){
      int ltype = this.getLeftChild().getType();
      int rtype = this.getRightChild().getType();
      System.out.println("tipe ArithType>------------");
      System.out.println("tipe DEBUG: tipoizq "+ltype+" tipoder "+rtype);
      int tipo = TypeTable.table[ltype][rtype];
      System.out.println("tipe resultante: "+tipo);
      System.out.println("tipe ArithTypeFIN ------------");
      if(tipo == -1){
         System.out.println("ERROR DE TIPOS con ");
         getLeftChild().print();
         getRightChild().print();
         System.exit(0);
      }
      this.type = tipo;
   }
}

/* Term no agrega nada a su lista */
/* Es un arbol binario */
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
      setType();
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
   
   public void setType(){
      int ltype = this.getLeftChild().getType();
      int rtype = this.getRightChild().getType();
      System.out.println("tipe TermType>------------");
      System.out.println("tipe DEBUG: tipoizq "+ltype+" tipoder "+rtype);
      int tipo = TypeTable.table[ltype][rtype];
      System.out.println("tipe resultante: "+tipo);
      System.out.println("tipe TermTypeFIN------------");
      if(tipo == -1){
         System.out.println("ERROR DE TIPOS con ");
         getLeftChild().print();
         getRightChild().print();
         System.exit(0);
      }
      this.type = tipo;
   }
}

class FactorNode extends Node{
   int signo;
   Node f;
   //('+'|'-') factor | power
   public FactorNode(int signo,Node f){
      this.signo = signo;
      this.f = f;
      setType();
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
   
   public void setType(){
      System.out.println("tipe FactorType");
      this.type = getLeftChild().getType();
      System.out.println("tipe "+this.type);
      System.out.println("tipe FactorFin");
   }
}


class PowerNode extends BinaryNode{
   public PowerNode(Node l, Node r){
      super(l,r);
      setType();
   }

   public void print(){
      System.out.println("<POWER>:");
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
   
   public void setType(){
      //tomo el tipo de izq y de derecho y los comparo
      int ltype = this.getLeftChild().getType();
      int rtype = this.getRightChild().getType();
      System.out.println("tipe PowerType");
      System.out.println("tipe DEBUG: tipoizq "+ltype+" tipoder "+rtype);
      int tipo = TypeTable.table[ltype][rtype];
      System.out.println("tipe resultante: "+tipo);
      System.out.println("tipe PowerTypeFIN ");
      if(tipo == -1){
         System.out.println("ERROR DE TIPOS con ");
         getLeftChild().print();
         getRightChild().print();
         System.exit(0);
      }
      this.type = tipo;
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
