package proyectopoo2;

import java.io.*;
import java.net.*;
import java.util.Vector;

public class Servidor {
    
    static Archivo_generics<DatosCliente> clientes_vec = new Archivo_generics<DatosCliente>("clientes.ser");
    static Vector<DatosCliente> vector = new Vector<DatosCliente>();
    
    public static void main(String[] args) {
        vector = clientes_vec.leerRegistros();
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
            int comando2;
            
            do {
                mensaje = entrada.readUTF();

                //System.out.println("recibiendo:" + mensaje.toUpperCase());
                String cadena[];
                String respuesta = " ";
                
                cadena = mensaje.split("/");
                comando = Integer.parseInt(cadena[0]);
//                if (comando != 2 && comando != 3) {
//
//                    inf = cadena[1];
//                }

                switch (comando) {
                    case 1: {
                        System.out.println("Altas");
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
                        clientes_vec.actualizarRegistros(vector);
                    }
                    break;
                    case 2: {  //consulta de vector
                        System.out.println("consulta");
                        String correo = cadena[1];
                        respuesta = "no se encontro";
                        for (int i = 0; i < vector.size(); i++) {
                            if (vector.elementAt(i).getCorreo().compareToIgnoreCase(correo) == 0) {
                                
                                System.out.println("\n" + "Nombre:" + vector.elementAt(i).getNombre() + "\n" + " Apellido" + vector.elementAt(i).getApellido() + "\n" + " correo" + vector.elementAt(i).getCorreo()
                                        + "\n" + "Telefono:" + vector.elementAt(i).getTelefono() + "\n" + "Ocupacion:" + vector.elementAt(i).getOcupacion() + "\n" + "Monto inicial:" + vector.elementAt(i).getMontoI()
                                        + "\n" + "Monto a retirar:" + vector.elementAt(i).getMinimoR());
                                
                                respuesta = "\n" + "Nombre:" + vector.elementAt(i).getNombre() + "\n" + " Apellido" + vector.elementAt(i).getApellido() + "\n" + " correo" + vector.elementAt(i).getCorreo()
                                        + "\n" + "Telefono:" + vector.elementAt(i).getTelefono() + "\n" + "Ocupacion:" + vector.elementAt(i).getOcupacion() + "\n" + "Monto inicial:" + vector.elementAt(i).getMontoI()
                                        + "\n" + "Monto a retirar:" + vector.elementAt(i).getMinimoR();
                                break;
                            }
                        }
                        salDatos.writeUTF(respuesta);
                        clientes_vec.actualizarRegistros(vector);
                    }
                    break;
                    case 3://retiro 
                    {
                        System.out.println("consulta");
                        String correo = cadena[1];
                        respuesta = "no se encontro";
                        double retiro = Double.parseDouble(cadena[2]);
                        for (int i = 0; i < vector.size(); i++) {
                            if (vector.elementAt(i).getCorreo().compareToIgnoreCase(correo) == 0) {
                                vector.elementAt(i).setMontoI(vector.elementAt(i).getMontoI() - retiro);
                                //respuesta = String.valueOf(vector.elementAt(i).getMontoI());
                            }
                        }
                        salDatos.writeUTF(respuesta);
                    }
                    
                    break;
                    case 4: //deposito
                    {
                        System.out.println("deposito");
                        String correo = cadena[1];
                         respuesta = "no se encontro";
                        double deposito = Double.parseDouble(cadena[2]);
                        for (int i = 0; i < vector.size(); i++) {
                            if (vector.elementAt(i).getCorreo().compareToIgnoreCase(correo) == 0) {
                                vector.elementAt(i).setMontoI(vector.elementAt(i).getMontoI() + deposito);
                                respuesta = String.valueOf(vector.elementAt(i).getMontoI());
                            }
                        }
                        salDatos.writeUTF(respuesta);
                    }
                    
                    break;
                    case 5://Modificaciones
                    {
                        //consulta de vector
                        System.out.println("Modificaciones");
                        String correo = cadena[1];
                        
                        String nombre = cadena[2];
                        String apellido = cadena[3];
                        String correo_nuevo = cadena[4];
                        String ocupacion = cadena[5];
                        String telefono = cadena[6];
                        double minimoR = Double.parseDouble(cadena[7]);
                        
                        for (int i = 0; i < vector.size(); i++) {
                            if (vector.elementAt(i).getCorreo().compareToIgnoreCase(correo) == 0) {
                                
                                vector.elementAt(i).setNombre(nombre);
                                vector.elementAt(i).setApellido(apellido);
                                vector.elementAt(i).setCorreo(correo_nuevo);
                                
                                vector.elementAt(i).setOcupacion(ocupacion);
                                vector.elementAt(i).setTelefono(telefono);
                                vector.elementAt(i).setMinimoR(minimoR);
                                
                                respuesta = "\n" + "Nombre:" + vector.elementAt(i).getNombre() + "\n" + " Apellido" + vector.elementAt(i).getApellido() + "\n" + " correo" + vector.elementAt(i).getCorreo()
                                        + "\n" + "Telefono:" + vector.elementAt(i).getTelefono() + "\n" + "Ocupacion:" + vector.elementAt(i).getOcupacion() + "\n" + "Monto inicial:" + vector.elementAt(i).getMontoI()
                                        + "\n" + "Monto a retirar:" + vector.elementAt(i).getMinimoR();
                                
                            }
                            clientes_vec.actualizarRegistros(vector);
                        }
                    }
                    break;
                }
//                salDatos.writeUTF(respuesta);
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
