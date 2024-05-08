/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Clase;


/**
 * Clase cliente donde se recoge la informacion del cliente.
 * @author Yassin Charrouf Errynda
 */
public class Cliente extends Persona{
    private final String estadoCivil;

    /**
     * Constructor por parametros.
     */
    public Cliente(String nombre, String apellido, int edad, String email, String DNI, int telefono, String estadoCivil) {
        super(nombre, email, edad, email, DNI, telefono);
        this.estadoCivil = estadoCivil;
    }
    //Setter and Getter

    @Override
    public void setTelefono(int telefono) {
        super.setTelefono(telefono); 
    }

    @Override
    public int getTelefono() {
        return super.getTelefono(); 
    }

    @Override
    public void setDNI(String DNI) {
        super.setDNI(DNI); 
    }

    @Override
    public String getDNI() {
        return super.getDNI();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email); 
    }

    @Override
    public String getEmail() {
        return super.getEmail(); 
    }

    @Override
    public void setEdad(int edad) {
        super.setEdad(edad); 
    }

    @Override
    public int getEdad() {
        return super.getEdad(); 
    }

    @Override
    public void setApellido(String apellido) {
        super.setApellido(apellido);
    }

    @Override
    public String getApellido() {
        return super.getApellido();
    }

    @Override
    public void setNombre(String nombre) {
        super.setNombre(nombre);
    }

    @Override
    public String getNombre() {
        return super.getNombre(); 
    }

    @Override
    public String toString() {
        return super.toString()+", Estado Civil: "+estadoCivil+"]"; 
    }

    
}