import java.util.*;
public class CodeVisitor implements Visitor{
   public void visit(Node n){
      System.out.println("Soy clase generica Nodo ERROR");
      System.exit(0);
   }

   public void visit(StringLeaf s){
      //System.out.println("Soy nodo hoja con valor: "+ s.getValue().sval);
      System.out.print("ldc \""+s.getValue().sval+"\"");
   }

   public void visit(IntLeaf n){
      System.out.print("entero"+ n.getValue().ival);
   }

   public void visit(IdentifierLeaf n){
      /*
      n.print();
      if(Parser.symtable.lookUp(n.name)==null){
         System.out.println("We cant continue var "+n.name+" not defined");
         System.exit(1);
      }
      */
   }

   public void visit(PowerNode n){
      /*
      System.out.println("Soy nodo potencia ");
      Node l = n.getLeftChild();
      Node r = n.getRightChild();
      l.accept(this);
      r.accept(this);
      */
   }

   public void visit(FactorNode n){
      System.out.println("Soy nodo factor ");
      Node f = n.getLeftChild();
      f.accept(this);
   }

   public void visit(TermNodeX n){
      /*
      Node i = n.getLeftChild();
      Node d = n.getRightChild();
      i.accept(this);
      d.accept(this);
      */
   }

   public void visit(ArithNode n){
      /*
      Node l = n.getLeftChild();
      Node r = n.getRightChild();
      //System.out.println(n.op);
      l.accept(this);
      r.accept(this);
      */
   }

   public void visit(AndNode n){
      /*
      //System.out.println("Soy nodo and:");
      Node l = n.getLeftChild();
      Node r = n.getRightChild();
      l.accept(this);
      r.accept(this);
      */
   }

   public void visit(XorNode n){
      /*
      //System.out.println("Soy nodo xor:");
      Node l = n.getLeftChild();
      Node r = n.getRightChild();
      l.accept(this);
      r.accept(this);
      */
   }

   public void visit(OrNode n){
      /*
      //System.out.println("Soy nodo or:");
      Node l = n.getLeftChild();
      Node r = n.getRightChild();
      l.accept(this);
      r.accept(this);
      */
   }

   /* THis is the assignation node */
   public void visit(EXPRN n){
      /*
      System.out.println("Soy nodo Expr__:");
      Node l = n.getLeftChild();
      Node r = n.getRightChild();
      l.accept(this);
      r.accept(this);
      */
   }
   
   /* What the fuck is this ? */
   public void visit(ExprNode n){
      /*
      Node l = n.getLeftChild();
      Node r = n.getRightChild();
      l.accept(this);
      r.accept(this);
      */
   }

   /* Lista de stmts */
   public void visit(SStmtNode n){
      //System.out.println("Soy nodo SStmtNode:");
      //System.out.println("Nodos hijos: "+n.nodos.size());
      String temp = "";
      //System.out.println("[");
      for(int i = 0; i< n.nodos.size(); i++){
         Node nn = n.nodos.get(i);
         nn.accept(this);
      }
      //System.out.println("]");
   }

   public void visit(PrintNode n){
      ArrayList<Node> nodos = n.getChilds();
      for(int i=0; i<nodos.size();i++){
         Node nn = nodos.get(i);
         nn.accept(this);
         /*
         
         */
         System.out.println("astore_2");
         System.out.println("getstatic java/lang/System/out Ljava/io/PrintStream;");
         System.out.println("aload_2");
         System.out.println("invokevirtual java/io/PrintStream/println (Ljava/lang/String;)V");
      }
   }
   
   /* TOCHECK: How 2 analyze this*/
   public void visit(WhileNode n){
      System.out.println("While:(");
      n.getLeftChild().print();
      System.out.print("){\n");
      n.getRightChild().print();
      System.out.println("}");
   }

   /* New Nodes */
   public void visit(AuxTermNode n){
      System.out.println("SOY AUXTERMNODE NO DEBERIAS VER ESTO!");
      n.print();
   }

   /* Falta visitar esto */
   /* Creo que este nodo no lo utilizo */
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

   /* TOCHECK: How 2 analyze this*/
   /* 
      IfNode Mejorado es un if con una lista (posiblemente vacia ) de elif 
      Por lo que  tengo que visitar el if y luego visitar los elif
   */
   public void visit(IFNodeMejorado n){
      System.out.print("IfMejorado");
      //Visitamos la condicion y el cuerpo
      Node cond = n.getLeftChild();
      System.out.println("Pase nodo cond");
      Node body = n.getRightChild();
      System.out.println("Pase nodo body");
      cond.accept(this);
      body.accept(this);
      //Ahora visitamos los elifs
      ArrayList<Node> elifs = n.getChilds();      
      if(elifs!=null){
         for(int i=0;i<elifs.size();i++){
            Node h = elifs.get(i);
            h.accept(this);
         }
      }
      
   }
   
   /* TOCHECK: How 2 analyze this*/
   /* Creo que no utilizo este nodo, es un nodo temporal para 
   almacenar elifs, pero despues se destruye 
   */
   public void visit(ElifNode n){
      System.out.print("ELIF");
      System.out.println("SOY ElifNode NO DEBERIAS VER ESTO!");
      n.print();
   }
   
   /* TOCHECK: How 2 analyze this*/
   public void visit(SingleElifNode n){
      n.print();
      Node cond = n.getLeftChild();
      Node body = n.getRightChild();
      cond.accept(this);
      body.accept(this);
   }
}
