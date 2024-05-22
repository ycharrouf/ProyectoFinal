/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import Clases.Conexion;
import Interfaces.EncriptacionPass;
import java.sql.*;
import java.util.Scanner;

/**
 * Clase para logearse en el banco.
 * @author Yassin
 */
public class Login implements EncriptacionPass {
    private static Scanner sc = new Scanner(System.in).useDelimiter("\n");
    private static Connection conexion = (Connection) Conexion.getConexion();
    

    private static int opcion=0;
    
    /**
     * Menú del login
     */
    static void login(){
        do {            
            System.out.println("==========================");
            System.out.println("Que tipo de usuario eres:");
            System.out.println("1.\tEmpleado");
            System.out.println("2.\tCliente");
            System.out.println("3.\tSalir");
            System.out.print(": ");
            opcion=sc.nextInt();
            
            switch (opcion) {
                case 1:
                    System.out.println("Has elegido la opción: Empleado.");
                    logUsuario();
                    break;
                case 2:
                    System.out.println("Has elegido la opción: Cliente.");
                    logUsuario();
                    break;
                case 3:
                    System.out.println("Saliendo del programa...\nPrograma cerrado con éxito");
                    break;
                default:
                    System.out.println("La opción es incorrecta\nPor favor escoga otra.");
            }
        } while (opcion!=3);
    }
    
    /**
     * Metodo para le verificación del usuario que intenta conectarse.
     */
    private static void logUsuario(){
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        String tipo = (opcion==1) ? "empleados" : "clientes";
        String dni;
        do {            
            System.out.print("Dime el DNI: ");dni=sc.next();
        } while (dni.length()!=9);
        
        ///obtenemos los datos el usuario.
        String NomYPass;
        //En caso de que no devuelva ningun dni volvemos al login.
        if ((NomYPass=obtenerNomAndPass(dni, tipo))==null){
            System.out.println("No se a encontrado ningun usuario con el DNI: "+dni+".");
            login();
        }
        //En este array estan los datos del usuario en la posición 0 esta el nombre y en la posición 1 esta la contraseña *Encriptada*
        String[] datos = NomYPass.split(";");
        ///
        String contraseña;
        int intentos=0;
        do {            
            System.out.println("?-> "+datos[0]+" en caso de que no te sepas la pass pulsa enter para salir.");
            System.out.print(datos[0]+" dime la contraseña: ");
            contraseña=sc.next();
            if(contraseña.length()==0){
                return;
            }
            intentos++;
        } while (!EncriptacionPass.encriptaPass(contraseña).equals(datos[1]) && intentos<3);
        
        
        System.out.println("==============================");
        System.out.println("   Logeado con éxito "+datos[0]);
        System.out.println("==============================");
    }
    
    /**
     * Metodo para obtener el nombre de usuario y el pass del usuario
     * @param dni con el dni obtenemos el nombre de usuraio en la base de datos.
     * @param tipo es el tipo de usuario para saber donde buscar en caso de que sea cliete o empleado
     * @return el nombre de usuario junto al pass de ese usuario.
     */
    private static String obtenerNomAndPass(String dni, String tipo){
        String nombreYpass="X";
        try {
            Statement consult = conexion.createStatement(); 
            String query ="select nombre, contraseña from "+tipo+" where dni=\""+dni+"\";";
            ResultSet rs = consult.executeQuery(query);
            
            while (rs.next()) {
                nombreYpass=rs.getString("nombre")+";"+rs.getString("contraseña");
            }
            
        } catch (SQLException e) {
            System.out.println("Error: "+e.getMessage());
        }
        return (nombreYpass.equals("X"))? null : nombreYpass;
    }
    
    public static void main(String[] args) {
        login();
        System.out.println(EncriptacionPass.encriptaPass("password"));
    }
}