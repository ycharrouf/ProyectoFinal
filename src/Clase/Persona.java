/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clase;

import java.util.InputMismatchException;

/**
 *
 * @author Yassin
 */
abstract class Persona {
    private String nombre;
    private String apellido;
    private int edad;
    private String email;///hay que validarlos con java.net.uri;
    private String DNI; //validar con un metodo
    private int telefono;

    protected Persona(String nombre, String apellido, int edad, String email, String DNI, int telefono) throws InputMismatchException{
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.email = email;
        if(isValidoDNI(DNI))
            this.DNI = DNI;
        else{
            throw new InputMismatchException("El dni no es valido.");
        }
        if(isTelefonoValido(telefono))
            this.telefono = telefono;
        else{
            throw new InputMismatchException("El numero de telefono es incorrecto");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    
    @Override
    public String toString() {
        return "Cliente[nombre: " + nombre + ", apellido: " + apellido + ", edad: " + edad + ", email:" + email + ", DNI: " + DNI + ", telefono: " + telefono + ", estadoCivil: ";
    }
    
    /**
     * Metodo para validar el dni del cliente
     * @param dni para poder validarlo
     * @return un boolean true en caso de que sea valido o false en caso contrario.
     */
    private static boolean isValidoDNI(String dni){
        //En caso de que el dni no menos de 8 digitos.
        if (dni.length()!=9){
           return false;
        }else{
            //Array de char para comprobar si la letra es correcta.
            char[] letras = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
            int numeros = Integer.parseInt(dni.substring(0, 8));
            int resto = numeros%23;
            
            return (String.valueOf(dni.charAt(8)).equalsIgnoreCase(String.valueOf(letras[resto])));
        }
        
    }
    
    /**
     * Metodo para comprobar el telefono
     * @param telefono para poder hacer las comprobaciónes
     * @return true en caso de que sea valido y false en caso contrario
     */
    private static boolean isTelefonoValido(int telefono){
        String NumbrePhone = String.valueOf((Integer) telefono);
        return (NumbrePhone.length()==8);
    }
    
}
