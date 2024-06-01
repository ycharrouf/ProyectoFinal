/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Clases;


/**
 * Clase cliente donde se recoge la informacion del cliente.
 * @author Yassin Charrouf Errynda
 */
public class Cliente extends Persona{
    private Cuenta cuenta;
    
    /**
     * Constructor por parametros.
     */
    public Cliente(String nombre, String apellido, int edad, String email, String DNI, int telefono,String direccion,String contraseña){
      super(nombre, email, edad, email, DNI, telefono,direccion, contraseña);  
    }
    /**
     * Constructor por parametros y con cuenta.
     */
    public Cliente(String nombre, String apellido, int edad, String email, String DNI, int telefono,String direccion, String contraseña, Cuenta cuenta) {
        super(nombre, email, edad, email, DNI, telefono,direccion, contraseña);
        this.cuenta=cuenta;
        //Cuando el cliente crea la cuenta se añade un registro para cuando de crea la cuenta.
        Cuenta.movimientos.add(new Movimientos(("El clietne "+super.getNombre()+" a abierto una cuenta nueva"),cuenta.getSaldo(), this ));
    }
    //Setter and Getter

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public String toString() {
        return super.toString()+", Dirrección: "+this.getDireccion();
    }
    
    

}
