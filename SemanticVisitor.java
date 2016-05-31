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

   public void visit(IdentifierLeaf n){
      //System.out.println("SemanticAnalysis for : "+ n.name);
      //Factorizar esto
      if(Parser.symtable.lookUp(n.name)!=null){
         //System.out.println("We can continue ");
      }else{
         System.out.println("We cant continue var "+n.name+" not defined");
         System.exit(1);
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
      System.out.println("Soy nodo term:[");
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

   
   public void visit(AndNode n){
      System.out.println("Soy nodo and:");
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

   public void visit(OrNode n){
      System.out.println("Soy nodo or:");
      Node l = n.getLeftChild();
      Node r = n.getRightChild();
      l.accept(this);
      r.accept(this);
   }

   /* THis is the assignation node */
   public void visit(EXPRN n){
      System.out.println("Soy nodo Expr__:");
      Node l = n.getLeftChild();
      //Sabemos que l es una variable, entonces obtengo el nombre y
      //verifico si esta en la SymbolTable
      Node r = n.getRightChild();
      l.accept(this);
      r.accept(this);
   }
   
   public void visit(ExprNode n){
      System.out.println("Soy nodo Expr:");
      Node l = n.getLeftChild();
      Node r = n.getRightChild();
      l.accept(this);
      r.accept(this);
   }

   public void visit(SStmtNode n){
      System.out.println("Soy nodo SStmtNode:");
      //System.out.println(n.node.imprime());
      System.out.println("Nodos hijos: "+n.nodos.size());
      String temp = "";
      System.out.println("[");
      for(int i = 0; i< n.nodos.size(); i++){
         Node nn = n.nodos.get(i);
         //System.out.println(nn.imprime());
         //nn.print();
         nn.accept(this);
         //temp+= "\n"+n.nodos.get(i).imprime();
      }
      System.out.println("]");
   }

   public void visit(PrintNode n){
      System.out.println("Soy nodo SStmtNode:");
      //System.out.println(n.node.imprime());
      System.out.println("Nodos hijos: "+n.nodos.size());
      String temp = "";
      System.out.println("[");
      for(int i = 0; i< n.nodos.size(); i++){
         Node nn = n.nodos.get(i);
         nn.accept(this);
      }
      System.out.println("]");
   }

   public void visit(StmtNode n){
      System.out.println("StmtNode:");
      //System.out.println(n.node.imprime());
      String temp = "";
      System.out.println("[");
      for(int i = 0; i< n.nodos.size(); i++){
         Node nn = n.nodos.get(i);
         //System.out.println(nn.imprime());
         nn.print();
         //temp+= "\n"+n.nodos.get(i).imprime();
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

   public void visit(IfNode n){
      System.out.println("Soy nodo IFNode:");
      n.print();
   }

   

   /* New Nodes */
   public void visit(AuxTermNode n){
      n.print();
   }

   public void visit(TermNode n){
      n.print();
   }

   public void visit(NotNode n){
      n.print();
   }

   public void visit(CmpNode n){
      n.print();
   }

   public void visit(IFNodeMejorado n){
      System.out.print("IfMejorado");
      n.print();
   }
   
   public void visit(ElifNode n){
      System.out.print("ELIF");
      n.print();
   }
   
   public void visit(SingleElifNode n){
      n.print();
   }
}
