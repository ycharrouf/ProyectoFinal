/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus;

import java.util.Scanner;
import Clases.*;
import java.sql.*;

/**
 * Clase donde se encuentra las funcionalidades y menú de los empleados.
 * @author Yassin 
 */
public class MenuFuncionalidadEmpleado {
    private static Scanner sc = new Scanner(System.in).useDelimiter("\n");
    private static Connection conexion;
    private static ClienteDAO clienteDao = new ClienteDAO(conexion);
    private static EmpleadoDAO empleadodao = new EmpleadoDAO(conexion);
    
    /**
     * Menú principal que van a tener los trabajadores.
     */
    public static void menuPrincipalEmpleados(){
        int opcion=0;
        do {       
            System.out.println("");
            System.out.println("================BANCO: Empleado============= ");
            System.out.println("1.\tGestión de usuarios.");
            System.out.println("2.\tGestión de empleados.");
        } while (true);
    }
    
    /**
     * Menú para gestion enfocada a los clientes.
     */
    public static void menuGestUsuarios(){
        int opcion=0;
        do {            
            System.out.println("");
            System.out.println("=========Gestión de Clientes=========");
            System.out.println("1.\tDar a un cliente de alta.");
            System.out.println("2.\tDar a un cliente de baja.");
            System.out.println("3.\tCambiar información de un cliente.");
            System.out.println("4.\tCertificado de titularidad.");//Tiene que tener tanto el Cliente como el empleado
            System.out.println("5.\tMostrar informacion de un Cliente.");
            System.out.println("6.\tMostar todos los clientes");
            System.out.println("7.\tvolver atras.");
            
            System.out.print(": ");opcion=sc.nextInt();
            
            switch (opcion) {
                case 1:
                    try {
                        darAltaCliente();
                } catch (SQLException e) {
                        System.err.println("Error al dar de alta al cliente: "+e.getMessage());
                }
                    break;
                case 2:
                    try {
                        darBajaCliente();
                } catch (SQLException e) {
                        System.err.println("Error al dar de baja a un cliente: "+e.getMessage());
                }
                    break;
                case 3:
                    try {
                        cambiarInformaciónCliente();
                } catch (SQLException e) {
                        System.err.println("Error al cambiar la información del cliente: "+e.getMessage());
                }
                    break;
                case 4:
                    try {
                    
                } catch (Exception e) {
                }
                    break;
                case 5:
                    try {
                        obtenerInfoCliente();
                } catch (SQLException e) {
                        System.err.println("Error al obtener información de cliente :"+e.getMessage());
                }
                    break;
                case 6:
                    try {
                    obtenerIntoAllCLientes();
                } catch (SQLException e) {
                        System.err.println("Error al obtener info de todos los clientes: "+e.getMessage());
                }
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion<=0&&opcion>7);
    }
    
    /**
     * Menú para la gestión enfocada a los empleados del banco
     */
    public static void menuGestionEmpleados(){
        int opcion=1;
        do {                
            System.out.println("");
            System.out.println("=======Gestión de Empleados=======");
            System.out.println("1.\tDar a un Empleado de alta.");
            System.out.println("2.\tDar a un Empleado de baja.");
            System.out.println("3.\tEstablecer a un empleado como Jefe");
            System.out.println("4.\tMostar la información de un empleado.");
            System.out.println("5.\tMostar la información de todos los empleados.");
            System.out.println("6.\tVolver atras");
            
            System.out.print(": ");opcion=sc.nextInt();
            
            switch (opcion) {
                case 1:
                    try {
                        darEmpleadoAlta();
                } catch (SQLException e) {
                        System.err.println("Error a dar empleado de alta: "+e.getMessage());
                }
                case 2:
                    try {
                        darEmpleadoBaja();
                } catch (SQLException e) {
                        System.err.println("Error al dar de baja a empleado: "+e.getMessage());
                }
                case 3:
                    try {
                        establecerEmpleadoJefe();
                } catch (SQLException e) {
                        System.err.println("Error al establecer empleado como jefe: "+e.getMessage());
                }
                case 4:
                    try {
                        obtenerEmpleado();
                } catch (SQLException e) {
                        System.err.println("Error al obtener info de empleado: "+e.getMessage());
                }
                case 5:
                    try {
                        obtenerAllEmpleados();
                } catch (SQLException e) {
                        System.err.println("Erro al obtener los empleados: "+e.getMessage());
                }
                case 6:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion<=0&&opcion>7);
    }
    
    /**
     * Metodo para dar un cliente de alta de el banco.
     * @throws SQLException 
     */
    private static void darAltaCliente() throws SQLException{
        //Pedimos la información de cliente.
        System.out.println("===Dar de alta a un cliente===");
        System.out.print("Dime el nombre del cliente: ");String nombre=sc.next();
        System.out.print("Dime los apellidos de "+nombre+" : ");String apellidos=sc.next();
        System.out.print("Dime el DNI de "+nombre+" : ");String dni=sc.next();
        System.out.print("Dime la edad de "+nombre+" : ");int edad = sc.nextInt();
        System.out.print("Dime el email de "+nombre+" : ");String email=sc.next();
        System.out.print("Dime le telefono de "+nombre+" : ");int telefono=sc.nextInt();
        System.out.print("Dime la dirección de "+nombre+" : ");String direccion=sc.next();
        System.out.print("Dime la contraseña de "+nombre+" : ");String contraseña=sc.next();
        //Creamos la cuenta del cliente
        Cuenta cuenta = creacionCuenta();
        clienteDao.darClienteAlta(new Cliente(nombre, apellidos, edad, email, dni, telefono, direccion, contraseña, cuenta));
    }
    
    /**
     * Con este metodo creamos la cuenta para registrar al cliente.
     * @return una cuenta asociada al cliente.
     */
    private static Cuenta creacionCuenta(){
        System.out.print("Dime la canitdad a depositar en la nueva cuenta: ");double saldo = sc.nextDouble();
        return new Cuenta(saldo);
    }
    
    /**
     * Metodo para eliminar cliente de la base de datos.
     * @throws SQLException en caso de que se produzca cualquier otro errror.
     */
    private static void darBajaCliente() throws SQLException{
        System.out.println("===Dar de baja a un cliente===");
        System.out.print("Dime el DNI del cliente a dar de baja: ");String dni = sc.next();
        clienteDao.darBajaCLiente(dni);
    }
    
    /**
     * Metodo para cambiar la información del cliente.
     * @throws SQLException en caso de que se produzca cualquier otro error.
     */
    private static void cambiarInformaciónCliente() throws SQLException{
        System.out.println("===Cambiar información de cliente====");
        System.out.print("Dime el dni del cliente a cambiar al información: ");String dni=sc.next();
        Cliente cliente = clienteDao.obtenerCliente(dni);
        System.out.println(cliente.toString());
        System.out.println("? Si no quieres cambiar alguna opción solo dale a ENTER.");
        String temp="";//variable temporal.
        System.out.print("El nombre ("+cliente.getNombre()+") : ");
        String nombre = temp=sc.next().isEmpty() ? cliente.getNombre() : temp;
        System.out.print("Los apellidos ("+cliente.getApellido()+" : ");
        String apellidos = temp=sc.next().isEmpty() ? cliente.getApellido() : temp;
        int tempInt=0;
        System.out.print("La edad ("+cliente.getEdad()+" : ");
        int edad = sc.hasNextInt() ?  sc.nextInt() : cliente.getEdad();///Funciona pero con dos enter.
        System.out.println("El email ("+cliente.getEmail()+") : ");
        String email = temp=sc.next().isEmpty() ? cliente.getEmail() : temp;
        System.out.println("El telefono ("+cliente.getTelefono()+") : ");
        int telefono = tempInt= sc.hasNextInt() ? sc.nextInt() : cliente.getEdad();
        System.out.println("La dirección : ("+cliente.getDireccion()+") : ");
        String direccion = temp=sc.next().isEmpty() ? cliente.getDireccion() : temp;
        //Ya tendríamos los datos
        clienteDao.actualizarCliente(new Cliente(nombre, apellidos, edad, email, dni, telefono, direccion, cliente.getContaseña()));
        
        
                
        
    }
    
    /**
     * Metodo para obtener info de todos los clientes.
     * @throws SQLException en caso de que se produzca cualquier otro error.
     */
    private static void obtenerInfoCliente() throws SQLException {
        System.out.print("Dime el dni del cliente a obtener información: ");
        String dni = sc.next();
        Cliente cliente = clienteDao.obtenerCliente(dni);
        System.out.println(cliente.toString());
    }
    
    /**
     * Metodo para obtener infor de todos los clientes.
     * @throws SQLException en caso de que se produzca cualquier otro error.
     */
    private static void obtenerIntoAllCLientes() throws SQLException{
        ResultSet rs = clienteDao.infotodosCLientes();
        while (rs.next()) {            
            System.out.println();
            System.out.println("--> DNI: "+rs.getString("dni")+", Nombre: "+rs.getString("nombre")
            +"Apellidos: "+rs.getString("apellidos")+", Edad: "+rs.getInt("edad")+", Telefono: "+rs.getInt("telefono")
            +", Dirección: "+rs.getString("direccion"));
        }
    }
    
    /**
     * Metodo para añadir empleado a la base de datos.
     * @throws SQLException en caso de que se produzca cualquier otro error.
     */
    private static void darEmpleadoAlta() throws SQLException{
        System.out.println("===Dar de alta a un Empleado===");
        System.out.print("Dime el nombre del cliente: ");String nombre=sc.next();
        System.out.print("Dime los apellidos de "+nombre+" : ");String apellidos=sc.next();
        System.out.print("Dime el DNI de "+nombre+" : ");String dni=sc.next();
        System.out.print("Dime la edad de "+nombre+" : ");int edad = sc.nextInt();
        System.out.print("Dime el email de "+nombre+" : ");String email=sc.next();
        System.out.print("Dime le telefono de "+nombre+" : ");int telefono=sc.nextInt();
        System.out.print("Dime la dirección de "+nombre+" : ");String direccion=sc.next();
        System.out.print("Dime la contraseña de "+nombre+" : ");String contraseña=sc.next();
        System.out.print("El emplado "+nombre+" es jefe? (si/no):");
        boolean esJefe = (sc.next().equalsIgnoreCase("si")) ? true : false;
        Empleado empleado = new Empleado(nombre, apellidos, edad, email, dni, telefono, direccion, contraseña, esJefe);
        empleadodao.añadirEmpleado(empleado);
        
        
    }
    
    /**
     * Metodo para dar de baja a un empleado.
     * @throws SQLException en caso de que se produzca cualquier otro error.
     */
    private static void darEmpleadoBaja() throws SQLException{
        System.out.println("===Dar de baja a un Empleado===");
        System.out.print("Dime el DNI del cliente a dar de baja: ");String dni = sc.next(); 
        empleadodao.eliminarEmpleado(dni);
    }
    
    /**
     * Metodo para establecer un empleado como jefe.
     * @throws SQLException 
     */
    private static void establecerEmpleadoJefe() throws SQLException{
        System.out.println("===Establecer empleado como Jefe===");
        System.out.print("Dime el DNI del cliente a dar de baja: ");String dni = sc.next(); 
        empleadodao.establecerEmpleadoAJefe(dni);
    }
    
    /**
     * Metodo para obtener un empleado concreto.
     * @throws SQLException en cado de que se produzca cualquier otro error.
     */
    private static void obtenerEmpleado() throws SQLException{
        System.out.print("Dime el dni del cliente a obtener información: ");
        String dni = sc.next();
        Empleado empleado = empleadodao.obtenerEmpleado(dni);
        System.out.println(empleado.toString());
    }
    
    /**
     * Metodo para obtener info de todos los empleados
     * @throws SQLException en cado de que se produzca cualquier otro error.
     */
    private static void obtenerAllEmpleados() throws SQLException{
       ResultSet rs = empleadodao.infotodosEmpleados();
        while (rs.next()) {            
            System.out.println();
            System.out.println("--> DNI: "+rs.getString("dni")+", Nombre: "+rs.getString("nombre")
            +"Apellidos: "+rs.getString("apellidos")+", Edad: "+rs.getInt("edad")+", Telefono: "+rs.getInt("telefono")
            +", Dirección: "+rs.getString("direccion")+", ¿Es jefe? :"+rs.getBoolean("esJefe"));
        } 
    }
}
