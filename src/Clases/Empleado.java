/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.InputMismatchException;
 
/**
 *
 * @author Yassin
 */
public class Empleado extends Persona{
    private boolean esJefe;
    public Empleado(String nombre, String apellido, int edad, String email, String DNI, int telefono, String direccion, String contraseña, boolean esJefe) throws InputMismatchException {
        super(nombre, apellido, edad, email, DNI, telefono, direccion, contraseña);
        this.esJefe=esJefe;
    }

    public boolean isEsJefe() {
        return esJefe;
    }

    public void setEsJefe(boolean esJefe) {
        this.esJefe = esJefe;
    }

    @Override
    public String toString() {
        return super.toString()+", ¿Es jefe?: "+esJefe;
    }
    
    
    
}
