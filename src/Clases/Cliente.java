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
    private String tipoCuenta;
    //Tipo de cuenta que puede ser de empresa o no
    
    /**
     * Constructor por parametros.
     */
    public Cliente(String nombre, String apellido, int edad, String email, String DNI, int telefono,String direccion, String contraseña, Cuenta cuenta, String tipoCuenta) {
        super(nombre, email, edad, email, DNI, telefono,direccion, contraseña);
        this.cuenta=cuenta;
        this.tipoCuenta=tipoCuenta;
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

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

}
