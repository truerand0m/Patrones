import java.util.LinkedList;
import java.lang.ArrayStoreException;
public interface Children{

   abstract Node getLeftChild();
   abstract Node getRightChild();
   abstract void setLeftChild(Node l);
   abstract void setRightChild(Node r);

   //abstract void addChild(Node l);
}

class ChildrenArrayList implements Children{

   private Node[] children;

   public ChildrenArrayList(int n){
      children = new Node[n];
   }

   public Node getRightChild(){
      return children[1];
   }

   public Node getLeftChild(){
      return children[0];
   }

   public void setRightChild(Node r){
      children[1] = r;
   }

   public void setLeftChild(Node l){
      children[0] = l;
   }
}

   // class ChildrenList implements Children
   // {

   //     private LikedList<Node> children;

   //     public ChildrenArrayList(){
   // 	children = new Node[2];
   //     }

   //     public Node getRightChild(){
   // 	return children[1];
   //     }

   //     public Node getLeftChild(){
   // 	return children[0];
   //     }

   //     public void setRightChild(Node r){
   // 	children[1] = r;
   //     }

   //     public void setLeftChild(Node l){
   // 	children[0] = l;
   //     }
   // }
