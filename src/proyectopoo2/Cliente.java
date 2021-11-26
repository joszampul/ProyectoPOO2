
package proyectopoo2;

import java.io.*;
import java.net.*;
import java.util.*;

public class Cliente {
    public static void main(String[] args){
        try{
            System.out.println("Cliente\n\n");
            Scanner l = new Scanner(System.in);
            Socket s= new Socket("localhost",5001);
            
            OutputStream sal=s.getOutputStream();
            DataOutputStream salDatos=new DataOutputStream(sal);
            //recivir
            Socket cliente =s;
            InputStream e=cliente.getInputStream();
            DataInputStream entrada =new DataInputStream(e);
           //enviar
         double num=-1;
         do{
            System.out.println("Ingresar datos23");
            String mensaje=l.nextLine();
            salDatos.writeUTF(mensaje);
           }while(num!=0);
            //recivir
            String v;
            v=entrada.readUTF();
           System.out.println("recibiendo:"+v);
            //salDatos.writeUTF(v.toUpperCase());
         
            cliente.close();
            
            
            s.close();
        }catch(IOException ex){
            System.out.println("error i/o");
        }
    
    }
}
