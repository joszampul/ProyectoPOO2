package proyectopoo2;

import java.io.*;
import java.net.*;
import java.util.Vector;

public class Servidor {

    public static void main(String[] args) {
        Vector<DatosCliente> vector = new Vector<DatosCliente>();
        try {
            System.out.println("Servidor\n\n");
            ServerSocket ss = new ServerSocket(5001);
            System.out.println("servidor listo");
            Socket cliente = ss.accept();

            InputStream e = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(e);

            OutputStream sal = cliente.getOutputStream();
            DataOutputStream salDatos = new DataOutputStream(sal);
            String mensaje;
            int comando;
            
do{
            mensaje = entrada.readUTF();

            //System.out.println("recibiendo:" + mensaje.toUpperCase());
            
            String cadena[];
            String inf = " " ;

            cadena = mensaje.split("/");
            comando = Integer.parseInt(cadena[0]);
            if(comando!=2){
            
           inf = cadena[1];
            }
            
            
            

            switch (comando) {
                case 1:
                    
                        // inf=inf.toUpperCase();
                        String nombre = cadena[1];
                        String apellido = cadena[2];
                        String correo = cadena[3];
                        String ocupacion = cadena[4];
                        String telefono = cadena[5];
                        double montoI = Double.parseDouble(cadena[6]);
                        double minimoR = Double.parseDouble(cadena[7]);
                        //obj
                        DatosCliente datos = new DatosCliente(nombre, apellido, correo, ocupacion, telefono, montoI, minimoR);
                       
                        //agregando datos al vector
                        vector.addElement(datos);

                        
                        
                    
                    break;
                case 2:
             for (int i = 0; i < vector.size(); i++) {
                            System.out.println("\n" + "Nombre:" + vector.elementAt(i).getNombre() + "\n" + " Apellido" + vector.elementAt(i).getApellido() + "\n" + " correo" + vector.elementAt(i).getCorreo()
                                    + "\n" + "Telefono:" + vector.elementAt(i).getTelefono() + "\n" + "Ocupacion:" + vector.elementAt(i).getOcupacion() + "\n" + "Monto inicial:" + vector.elementAt(i).getMontoI()
                                    + "\n" + "Monto a retirar:" + vector.elementAt(i).getMinimoR());
                        }
                    break;
            }
            salDatos.writeUTF(inf);
} while (comando != 99);
            cliente.close();

            ss.close();
        } catch (UnknownHostException ex) {
            System.out.println("no se encnontro servidor");
        } catch (IOException ex) {
            System.out.println("error i/o" + ex.getMessage());

        }
    }
}
