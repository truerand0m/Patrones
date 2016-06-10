import java.util.*;
public class CodeVisitor implements Visitor{
   /* Intento generar cdigo para estos nodos */
   //alv
   static String code="";
   public void visit(ArithNode n){
      Node i = n.getLeftChild();
      Node d = n.getRightChild();
      i.accept(this);
      if(i.getType()!=n.getType())
         code+="\n"+TypeTable.genConvCode(n.getType(),i.getType());
      d.accept(this);
      if(d.getType()!=n.getType())
         code+="\n"+TypeTable.genConvCode(n.getType(),d.getType());
      code+="\n"+EnumOp.getOpcode(n.getType(),n.op);
   }

   public void visit(TermNodeX n){
      Node i = n.getLeftChild();
      Node d = n.getRightChild();
      i.accept(this);
      if(i.getType()!=n.getType())
         code+="\n"+TypeTable.genConvCode(n.getType(),i.getType());
      d.accept(this);
      if(d.getType()!=n.getType())
         code+="\n"+TypeTable.genConvCode(n.getType(),d.getType());
      code+="\n"+EnumOp.getOpcode(n.getType(),n.op);
   }
   
   public void visit(FactorNode n){
      System.out.println("Soy nodo factor ");
      Node f = n.getLeftChild();
      f.accept(this);
   }
   
   //not yet
   public void visit(PowerNode n){
      /*
      System.out.println("Soy nodo potencia ");
      Node l = n.getLeftChild();
      Node r = n.getRightChild();
      l.accept(this);
      r.accept(this);
      */
   }
   
   public void visit(StringLeaf s){
      //DEBUGSystem.out.println("Soy nodo hoja con valor: "+ s.getValue().sval);
      //System.out.println("ldc \""+s.getValue().sval+"\"");
      code+="\nldc \""+s.getValue().sval+"\"";
   }

   public void visit(FloatLeaf f){
      code+="\nldc "+f.value.dval;
   }
   
   public void visit(IntLeaf n){
      code+="\nsipush "+n.value.ival;
   }

   public void visit(IdentifierLeaf n){
      if(Parser.symtable.lookUp(n.name)==null){
         System.out.println("We cant continue var "+n.name+" not defined");
         System.exit(1);
      }
      else{
         int idx = Parser.symtable.getVarIndex(n.name);
         int idtype = Parser.symtable.getVarType(n.name);
         n.type = idtype;
         String loadtype = EnumOp.getInstruction(idtype,"load");
         code+="\n"+loadtype+" "+idx;
      }
   }
   
   /* 
      
   */
   public void visit(OrNode n){
      /*
      //System.out.println("Soy nodo or:");
      Node l = n.getLeftChild();
      Node r = n.getRightChild();
      l.accept(this);
      r.accept(this);
      */
   }
   
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

   /* TOCHECK: How 2 analyze this*/
   public void visit(WhileNode n){
      System.out.println("While:(");
      n.getLeftChild().print();
      System.out.print("){\n");
      n.getRightChild().print();
      System.out.println("}");
   }
      
   /* Lista de stmts */
   public void visit(SStmtNode n){
      String temp = "";
      for(int i = 0; i< n.nodos.size(); i++){
         Node nn = n.nodos.get(i);
         nn.accept(this);
      }
   }
   
   /* THis is the assignation node */
   public void visit(EXPRN n){
      Node l = n.getLeftChild();
      Node r = n.getRightChild();
      r.accept(this);
      int rtype = n.getRightChild().getType();
      String storetype = EnumOp.getInstruction(rtype,"store");
      IdentifierLeaf idf = (IdentifierLeaf)n.getLeftChild();
      String idname = idf.name;
      Parser.symtable.put(new Symbol(idname,rtype));
      code+="\n"+storetype+" "+Parser.symtable.getVarIndex(idname);
   }

   public void visit(PrintNode n){
      ArrayList<Node> nodos = n.getChilds();
      int stores = 0;
      for(int i=0; i<nodos.size();i++){
         Node nn = nodos.get(i);
         code+="\ngetstatic java/lang/System/out Ljava/io/PrintStream;";
         nn.accept(this);
         int type = nn.getType();
         String stype = "";
         switch(type){
            case 0:
               stype = "error";
               break;
            case 1:
               stype = "I";
               break;
            case 2:
               stype = "F";
               break;
            /* CHECK verificar char bool cad */
            case 3:
               stype = "B";
               break;
            case 4:
               stype = "Ljava/lang/String;";
               break;
            default:
               stype = "dgaf";
         }
         code+="\ninvokevirtual java/io/PrintStream/println("+stype+")V";
      }
   }
   
   public void visit(NotNode n){
      Node h = n.getChild();
      h.accept(this);
   }

   public void visit(CmpNode n){
      n.print();
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
   
   public void visit(XorNode n){
      /*
      //System.out.println("Soy nodo xor:");
      Node l = n.getLeftChild();
      Node r = n.getRightChild();
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
   
   public void visit(Node n){
      System.out.println("Soy clase generica Nodo ERROR");
      //this shouldnt happen!
      System.exit(0);
   }
   
   /* ------------------------------------------------------------- */
   /* ------------------------------------------------------------- */
   public void visit(TermNode n){
      System.out.println("SOY TERMNODE NO DEBERIAS VER ESTO!");
      n.print();
   }
   
   public void visit(AuxTermNode n){
      System.out.println("SOY AUXTERMNODE NO DEBERIAS VER ESTO!");
      n.print();
   }
}
