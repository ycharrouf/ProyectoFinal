/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import Clases.*;
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
    public static String dniCliente=null;
    


    private static int opcion=0;
    
    /**
     * Menú del login
     */
    static int login(){
        int tipo=0;
        do {  
            if(dniCliente!=null){
                System.out.println("Pulsa enter para continuar");
                sc.next();
                return opcion;
            }
            System.out.println("==========================");
            System.out.println("Que tipo de usuario eres:");
            System.out.println("1.\tEmpleado");
            System.out.println("2.\tCliente");
            System.out.println("3.\tSalir");
            System.out.print(": ");
            try {
                opcion=sc.nextInt();
            } catch (Exception e) {
                System.err.println("Introduce un número del menú");
                opcion=3;
            }

            switch (opcion) {
                case 1:
                    System.out.println("Has elegido la opción: Empleado.");
                    logUsuario();
                    tipo=1;
                    break;
                    
                case 2:
                    System.out.println("Has elegido la opción: Cliente.");
                    logUsuario();
                    tipo=2;
                    break;
                case 3:
                    tipo=3;
                    break;
                default:
                    System.out.println("La opción es incorrecta\nPor favor escoga otra.");
            }
        } while (opcion!=3);
        return tipo;
    }
    
    /**
     * Metodo para le verificación del usuario que intenta conectarse.
     */
    private static void logUsuario() {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        String tipo = (opcion==1) ? "empleados" : "clientes";
        String dni;
        do {            
            System.out.print("Dime el DNI: ");dni=sc.next();
        } while (dni.length()!=9);
        
        ///obtenemos los datos el usuario.
        String NomYPass=obtenerNomAndPass(dni, tipo);
        //En caso de que no devuelva ningun dni volvemos al login.
        if (NomYPass==null){
            System.out.println("No se a encontrado ningun usuario con el DNI: "+dni+".");
            return;
        }
        //En este array estan los datos del usuario en la posición 0 esta el nombre y en la posición 1 esta la contraseña *Encriptada*
        String[] datos = NomYPass.split(";");
        dniCliente=dni;
        //
        String contraseña;
        int intentos=0;
        System.out.println("?-> "+datos[0]+" en caso de que no te sepas la pass pulsa enter para salir.");
        do {            
            System.out.print(datos[0]+" dime la contraseña: ");
            contraseña=sc.next();
            if(contraseña.length()==0){
                dniCliente=null;
                return;
            }
            intentos++;
            if(intentos>=3){
                System.out.println("Has superado el numero máximo de intentos.");
                dniCliente=null;
                return;
            }
        } while (!EncriptacionPass.encriptaPass(contraseña).equals(datos[1]));
        
        if(dniCliente!=null){
            System.out.println("==============================");
            System.out.println("   Logeado con éxito "+datos[0]);
            System.out.println("==============================");
        }
    }
    
    /**
     * Metodo para obtener el nombre de usuario y el pass del usuario
     * @param dni con el dni obtenemos el nombre de usuraio en la base de datos.
     * @param tipo es el tipo de usuario para saber donde buscar en caso de que sea cliete o empleado
     * @return el nombre de usuario junto al pass de ese usuario.
     */
    private static String obtenerNomAndPass(String dni, String tipo) {
        String nombreYpass=null;
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
        if(nombreYpass!=null){
            return nombreYpass;
        }else{
            return null;
        }
    }
}