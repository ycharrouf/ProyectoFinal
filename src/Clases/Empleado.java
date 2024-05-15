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
    int departamento;//tiene que haber tepartamentos
    //Banca personal
    //Banca cooperativa
    //banca de inversión
    
    public Empleado(String nombre, String apellido, int edad, String email, String DNI, int telefono) throws InputMismatchException {
        super(nombre, apellido, edad, email, DNI, telefono);
        ///int departamento (opciones estan al lado del atributo)
    }
    
}
