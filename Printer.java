import java.util.*;
public class Printer{
   Node node;
   int tam;

   public Printer(Node n){
      node = n;
      tam = 4;
   }

   public Printer(Node n,int t){
      node = n;
      tam = t;
   }

   public void print(){
      levelOrderQueue(node);
   }

   public String fixLength(String s){
      while(s.length()<tam)
         s+=" ";
      return s;
   }

   public void levelOrderQueue(Node root){
      ArrayList<String> nodos = new ArrayList<String>();
      ArrayList lnodos = new ArrayList();
      Queue q = new LinkedList();
 		int levelNodes =0;
		if(root==null)
         return;
 		q.add(root);
 		while(!q.isEmpty()){
 			levelNodes = q.size();
 			while(levelNodes>0){
				Node n = (Node)q.remove();
            nodos.add("(" +fixLength(n.imprime())+")");
            String s = ""+n.getClass();
            if(n.getLeftChild()!=null){
               q.add(n.getLeftChild());
            }else{
               if(s.equals("class EmptyNode")){
                     q.add(new EndNode());
               }else{
                  if(!s.equals("class EndNode"))
                     q.add(new EmptyNode());
               }
            }
            if(n.getRightChild()!=null){
               q.add(n.getRightChild());
            }else{
               if(s.equals("class EmptyNode")){
                     q.add(new EndNode());
               }else{
                  if(!s.equals("class EndNode"))
                     q.add(new EmptyNode());
               }
            }
				levelNodes--;
			}
         lnodos.add(nodos);
         nodos = new ArrayList<String>();
		}
      System.out.println("--------------------------Arbol---------------------");
      addPadding(quitaNiveles(lnodos));
      System.out.println("----------------------------------------------------");
	}

   /* quita niveles vacios */
   public ArrayList quitaNiveles(ArrayList lnodos){
      ArrayList lista = new ArrayList();
      for(int i=0; i<lnodos.size(); i++){
         ArrayList temp = (ArrayList) lnodos.get(i);
         //this is bad, how can improve this
         String temp2 = temp.toString().replace("(","").replace(")","").replace("[","").replace("]","").replace(",","").replace(" ","");
         if(temp2.length()>0)
            lista.add(temp);
      }
      return lista;
   }

   public void addPadding(ArrayList lista){
      int size = lista.size();
      for(int i=0;i<lista.size();i++){
         ArrayList temp = (ArrayList)lista.get(i);
         int padnodo = getPaddingNivel(size,i,tam);
         String s = "";
         for(int j = 0; j< temp.size(); j++)
            s+=getPad(padnodo)+temp.get(j)+getPad(padnodo);
         System.out.println(s);
         // con esto quito los parentesis
         //System.out.println(s.replace("("," ").replace(")"," "));
      }
   }

   public int getPaddingNivel(int size,int i,int tam){
      //Aqui puedo factorizar, no ahora porfavor xD
      int paddingtotal = (int)(Math.pow(2,size-1))*tam - (int)(Math.pow(2,i))*tam;
      int paddingblock = paddingtotal / ((int)(Math.pow(2,i)) + 1);
      return paddingblock;
   }

   public String getPad(int pad){
      String t = "";
      for(int i=0; i<pad; i++)
         t+=" ";
      return t;
   }
}
