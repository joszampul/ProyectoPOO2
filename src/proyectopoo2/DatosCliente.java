
package proyectopoo2;

import java.io.Serializable;


class DatosCliente implements Serializable{
   private String nombre;
   private String apellido;
   private String correo;
   private String ocupacion;
   private String telefono;
   private double montoI;
   private double minimoR;
   
   //constructor
   DatosCliente(String nombre,String  apellido,String correo,String ocupacion,String telefono,double montoI,double minimoR){
       this.nombre=nombre;
       this.apellido=apellido;
       this.correo=correo;
       this.ocupacion=ocupacion;
       this.telefono=telefono;
       this.montoI=montoI;
       this.minimoR=minimoR;
       
   }

    

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the ocupacion
     */
    public String getOcupacion() {
        return ocupacion;
    }

    /**
     * @param ocupacion the ocupacion to set
     */
    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the montoI
     */
    public double getMontoI() {
        return montoI;
    }

    /**
     * @param montoI the montoI to set
     */
    public void setMontoI(double montoI) {
        this.montoI = montoI;
    }

    /**
     * @return the minimoR
     */
    public double getMinimoR() {
        return minimoR;
    }

    /**
     * @param minimoR the minimoR to set
     */
    public void setMinimoR(double minimoR) {
        this.minimoR = minimoR;
    }
}
