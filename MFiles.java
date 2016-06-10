import java.io.*;
import java.util.*;
public class MFiles{
   public static void write(String code){
      try{
         PrintWriter pw = new PrintWriter("program.j");
         pw.println(code);
         pw.close();
      }catch(Exception e){/* idm */}
   }
}
