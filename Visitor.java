public interface Visitor{
   public void visit(Node n);
   public void visit(StringLeaf s);
   public void visit(IntLeaf n);
   public void visit(IdentifierLeaf n);
   public void visit(FloatLeaf n);
   public void visit(PowerNode p);
   public void visit(FactorNode p);
   public void visit(TermNodeX n);
   public void visit(ArithNode n);
   public void visit(AndNode n);
   public void visit(OrNode n);
   public void visit(ExprNode n);
   public void visit(EXPRN n);
   public void visit(SStmtNode n);
   public void visit(WhileNode n);
   public void visit(IFNodeMejorado n);
   public void visit(ElifNode n);
   public void visit(XorNode n);
   public void visit(AuxTermNode n);
   public void visit(TermNode n);
   public void visit(PrintNode n);
   public void visit(NotNode n);
   public void visit(CmpNode n);
   public void visit(SingleElifNode n);
}

class PrintVisitor implements Visitor{
   
   public void visit(SStmtNode n){
      System.out.println("Soy nodo SStmtNode:");
      System.out.println("Nodos hijos: "+n.nodos.size());
      String temp = "";
      System.out.println("[");
      for(int i = 0; i< n.nodos.size(); i++){
         Node nn = n.nodos.get(i);
         nn.print();
      }
      System.out.println("]");
   }
   
   public void visit(IFNodeMejorado n){
      System.out.print("IfMejorado");
      //public Node getLeftChild(){
      //public Node getRightChild(){
      n.print();
   }

   public void visit(ElifNode n){
      System.out.print("ELIF");
      n.print();
   }

   public void visit(SingleElifNode n){
      n.print();
   }
   
   public void visit(WhileNode n){
      System.out.println("While:(");
      n.getLeftChild().print();
      System.out.print("){\n");
      n.getRightChild().print();
      System.out.println("}");
   }
   
   /* Asignacion */
   public void visit(EXPRN n){
      System.out.println("Soy nodo Expr__:");
      Node l = n.getLeftChild();
      Node r = n.getRightChild();
      l.accept(this);
      r.accept(this);
   }
   
   public void visit(OrNode n){
      System.out.println("Soy nodo or:");
      Node l = n.getLeftChild();
      Node r = n.getRightChild();
      l.accept(this);
      r.accept(this);
   }
   
   public void visit(NotNode n){
      n.print();
   }

   public void visit(CmpNode n){
      n.print();
   }

   public void visit(ExprNode n){
      System.out.println("Soy nodo Expr:");
      Node l = n.getLeftChild();
      Node r = n.getRightChild();
      l.accept(this);
      r.accept(this);
   }
   
   public void visit(XorNode n){
      System.out.println("Soy nodo xor:");
      Node l = n.getLeftChild();
      Node r = n.getRightChild();
      l.accept(this);
      r.accept(this);
   }   

   public void visit(AndNode n){
      System.out.println("Soy nodo and:");
      Node l = n.getLeftChild();
      Node r = n.getRightChild();
      l.accept(this);
      r.accept(this);
   }
   
   public void visit(ArithNode n){
      System.out.println("Soy nodo Arith:[");
      Node l = n.getLeftChild();
      Node r = n.getRightChild();
      l.accept(this);
      System.out.println(n.op);
      r.accept(this);
      System.out.println("]");
   }

   public void visit(TermNodeX n){
      System.out.println("Soy nodo term:[");
   }
   
   public void visit(FactorNode n){
      System.out.println("Soy nodo factor ");
      Node f = n.getLeftChild();
      f.accept(this);
   }
   
   public void visit(PowerNode n){
      System.out.println("Soy nodo potencia ");
      Node l = n.getLeftChild();
      Node r = n.getRightChild();
      l.accept(this);
      r.accept(this);
   }

   public void visit(StringLeaf s){
      System.out.println("Soy nodo hoja con valor: "+ s.getValue().sval);
   }

   public void visit(IntLeaf n){
      System.out.println("Soy nodo entero con valor: "+ n.getValue().ival);
   }

   public void visit(IdentifierLeaf n){
      System.out.println("Soy nodo identificador con valor: "+ n.name);
   }
   
   public void visit(PrintNode n){
      System.out.println("Soy nodo PrintNode:");
      System.out.println("Nodos hijos: "+n.nodos.size());
      String temp = "";
      System.out.println("[");
      for(int i = 0; i< n.nodos.size(); i++){
         Node nn = n.nodos.get(i);
         nn.print();
      }
      System.out.println("]");
   }
   
   public void visit(FloatLeaf n){
      System.out.println("Soy nodo float valor: "+ n.getValue().dval);
   }
   
   public void visit(Node n){
      System.out.println("Soy clase generica Nodo");
   }
   
   public void visit(AuxTermNode n){
      System.out.println("SOY AUXTERMNODE NO DEBERIAS VER ESTO!");
      n.print();
   }

   public void visit(TermNode n){
      System.out.println("SOY TERMNODE NO DEBERIAS VER ESTO!");
      n.print();
   }
}
