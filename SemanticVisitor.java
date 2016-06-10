import java.util.*;
public class SemanticVisitor implements Visitor{
   public void visit(Node n){
      System.out.println("Soy clase generica Nodo");
   }

   public void visit(StringLeaf s){
      System.out.println("Soy nodo hoja con valor: "+ s.getValue().sval);
   }

   public void visit(IntLeaf n){
      System.out.println("Soy nodo entero con valor: "+ n.getValue().ival);
   }
   
   public void visit(FloatLeaf n){
      System.out.println("Soy nodo entero con valor: "+ n.getValue().dval);
   }

   public void visit(IdentifierLeaf n){
      n.print();
      if(Parser.symtable.lookUp(n.name)==null){
         System.out.println("We cant continue var "+n.name+" not defined");
         System.exit(1);
      }
      else{
      }
   }

   public void visit(PowerNode n){
      System.out.println("Soy nodo potencia ");
      Node l = n.getLeftChild();
      Node r = n.getRightChild();
      l.accept(this);
      r.accept(this);
   }

   public void visit(FactorNode n){
      System.out.println("Soy nodo factor ");
      Node f = n.getLeftChild();
      f.accept(this);
   }

   public void visit(TermNodeX n){
      Node i = n.getLeftChild();
      Node d = n.getRightChild();
      i.accept(this);
      d.accept(this);
   }

   public void visit(ArithNode n){
      Node l = n.getLeftChild();
      Node r = n.getRightChild();
      //System.out.println(n.op);
      l.accept(this);
      r.accept(this);
   }

   public void visit(AndNode n){
      Node l = n.getLeftChild();
      Node r = n.getRightChild();
      l.accept(this);
      r.accept(this);
   }

   public void visit(XorNode n){
      Node l = n.getLeftChild();
      Node r = n.getRightChild();
      l.accept(this);
      r.accept(this);
   }

   public void visit(OrNode n){
      Node l = n.getLeftChild();
      Node r = n.getRightChild();
      l.accept(this);
      r.accept(this);
   }

   public void visit(EXPRN n){
      System.out.println("Soy nodo Expr__:");
      Node l = n.getLeftChild();
      Node r = n.getRightChild();
      l.accept(this);
      r.accept(this);
   }
   
   public void visit(ExprNode n){
      Node l = n.getLeftChild();
      Node r = n.getRightChild();
      l.accept(this);
      r.accept(this);
   }

   /* Lista de stmts */
   public void visit(SStmtNode n){
      System.out.println("Soy nodo SStmtNode:");
      System.out.println("Nodos hijos: "+n.nodos.size());
      String temp = "";
      System.out.println("[");
      for(int i = 0; i< n.nodos.size(); i++){
         Node nn = n.nodos.get(i);
         nn.accept(this);
      }
      System.out.println("]");
   }

   public void visit(PrintNode n){
      System.out.println("Soy nodo PrintNode:");
      System.out.println("Nodos hijos: "+n.nodos.size());
      System.out.println("[");
      ArrayList<Node> nodos = n.getChilds();
      for(int i=0; i<nodos.size();i++){
         Node nn = nodos.get(i);
         nn.accept(this);
      }
      System.out.println("]");
   }
   
   public void visit(WhileNode n){
      System.out.println("While:(");
      n.getLeftChild().print();
      System.out.print("){\n");
      n.getRightChild().print();
      System.out.println("}");
   }

   public void visit(AuxTermNode n){
      System.out.println("SOY AUXTERMNODE NO DEBERIAS VER ESTO!");
      n.print();
   }
   
   public void visit(TermNode n){
      System.out.println("SOY TERMNODE NO DEBERIAS VER ESTO!");
      n.print();
   }

   public void visit(NotNode n){
      Node h = n.getChild();
      h.accept(this);
   }

   public void visit(CmpNode n){
      n.print();
   }

   public void visit(IFNodeMejorado n){
      System.out.print("IfMejorado");
      Node cond = n.getLeftChild();
      System.out.println("Pase nodo cond");
      Node body = n.getRightChild();
      System.out.println("Pase nodo body");
      cond.accept(this);
      body.accept(this);
      ArrayList<Node> elifs = n.getChilds();      
      if(elifs!=null){
         for(int i=0;i<elifs.size();i++){
            Node h = elifs.get(i);
            h.accept(this);
         }
      }
   }
   
   public void visit(ElifNode n){
      System.out.print("ELIF");
      System.out.println("SOY ElifNode NO DEBERIAS VER ESTO!");
      n.print();
   }
   
   public void visit(SingleElifNode n){
      n.print();
      Node cond = n.getLeftChild();
      Node body = n.getRightChild();
      cond.accept(this);
      body.accept(this);
   }
}
