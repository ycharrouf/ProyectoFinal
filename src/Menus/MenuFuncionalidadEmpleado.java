/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus;

import java.util.Scanner;
import Clases.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Clase donde se encuentra las funcionalidades y menú de los empleados.
 * @author Yassin 
 */
public class MenuFuncionalidadEmpleado {
    private static Scanner sc = new Scanner(System.in).useDelimiter("\n");
    private static Connection conexion;
    private static ClienteDAO clienteDao = new ClienteDAO(Conexion.getConexion());
    private static EmpleadoDAO empleadodao = new EmpleadoDAO(Conexion.getConexion());
    private static Empleado empleadoActual=null;
    
    /**
     * Metodo para establecer el cliente actual.
     * @param empleado 
     */
    public static void setEmpleadoActual(Empleado empleado){
        empleadoActual=empleado;
    }
    
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
            System.out.println("3.\tSalir del programa.");
            System.out.print(": ");
            opcion=sc.nextInt();
            
            switch (opcion) {
                case 1:
                    menuGestUsuarios();
                    break;
                case 2:
                    menuGestionEmpleados();
                    break;
                case 3: 
                    break;
                default:
                    System.err.println("La opción es invalida.");
            }
        } while (opcion!=3);
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
            System.out.println("4.\tMostrar informacion de un Cliente.");
            System.out.println("5.\tMostrar todos los movimientos de un Cliente.");
            System.out.println("6.\tMostar todos los clientes");
            System.out.println("7.\tvolver atras.");
            
            System.out.print(": ");opcion=sc.nextInt();
            
            switch (opcion) {
                case 1:
                    try {
                        darAltaCliente();
                } catch (SQLException | java.util.InputMismatchException e) {
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
                        obtenerInfoCliente();
                } catch (SQLException e) {
                        System.err.println("Error al obtener información de cliente :"+e.getMessage());
                }
                    break;
                case 5:
                    //Excepción caputurada en su metodo.
                    obtenerAllMovimientos();
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
        } while (opcion!=7);
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
        if(!isTelefonoValido(telefono)){
            throw new java.util.InputMismatchException("El numero esta repetido.");
        }
        System.out.print("Dime la dirección de "+nombre+" : ");String direccion=sc.next();
        System.out.print("Dime la contraseña de "+nombre+" : ");String contraseña=sc.next();
        //Creamos la cuenta del cliente
        Cuenta cuenta = creacionCuenta();
        clienteDao.darClienteAlta(new Cliente(nombre, apellidos, edad, email, dni, telefono, direccion, contraseña, cuenta));
    }
    
    /**
     * Metodo para comprobar que el telefono no esta repetido
     * @param telefono para poder hacer las comprobaciónes
     * @return true en caso de que sea valido y false en caso contrario
     */
    private static boolean isTelefonoValido(int telefono){
        boolean isValido=true;
        ArrayList<Cliente> clientes=new ArrayList<>();
        //Salta excepción si ni encuentra ningun cliente registrado.
        try {
            clientes= clienteDao.infotodosCLientes();
        } catch (SQLException e) {
            return true;
        }
        for(Cliente client : clientes){
            if(client.getTelefono()==telefono){
                isValido=false;
            }
        }
        return isValido;
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
        //Nombre
        System.out.print("El nombre ("+cliente.getNombre()+") : ");
        String nombre = sc.next();
        if(!nombre.isEmpty())
            cliente.setNombre(nombre);
        //Apellidos
        System.out.print("Los apellidos ("+cliente.getApellido()+") : ");
        String apellidos = sc.next();
        if(!apellidos.isEmpty())
            cliente.setApellido(apellidos);
        //Edad
        System.out.print("La edad ("+cliente.getEdad()+") : ");
        String edad = sc.next();
        if(!edad.isEmpty())
            cliente.setEdad(Integer.parseInt(edad));
        //Email
        System.out.print("El email ("+cliente.getEmail()+") : ");
        String email = sc.next();
        if(!email.isEmpty())
            cliente.setEmail(email);
        //Telefono
        System.out.print("El telefono ("+cliente.getTelefono()+") : ");
        String telefono = sc.next();
        if(!telefono.isEmpty())
            cliente.setTelefono(Integer.parseInt(telefono));
        //Dirección
        System.out.print("La dirección : ("+cliente.getDireccion()+") : ");
        String direccion = sc.next();
        if(!direccion.isEmpty())
            cliente.setDireccion(direccion);
        //Ya tendríamos los datos
        clienteDao.actualizarCliente(cliente);
    }
    
    /**
     * Metodo para obtener info de todos los clientes.
     * @throws SQLException en caso de que se produzca cualquier otro error.
     */
    private static void obtenerInfoCliente() throws SQLException {
        System.out.print("Dime el dni del cliente a obtener información: ");
        String dni = sc.next();
        Cliente cliente = clienteDao.obtenerCliente(dni);
        System.out.println(cliente.toString()+"\n\t\t"+cliente.getCuenta().toString());
    }
    
    /**
     * Metodo para obtener los movimientos de un respectivo cliente.
     */
    private static void obtenerAllMovimientos(){
        System.out.println("====Movimientos de un cliente:====");
        System.out.print("Dime el dni del cliente que quieres obtener los movimientos: ");
        String dni=sc.next();
        MovimientosDAO movimientodao = new  MovimientosDAO(Conexion.getConexion());
        try {
            for(Movimientos mov : movimientodao.obtenerMovimientosCliente(dni)){
                System.out.println(mov.toString());
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los movimientos del empleado indicado: "+e.getMessage());
        }
    }
    
    /**
     * Metodo para obtener infor de todos los clientes.
     * @throws SQLException en caso de que se produzca cualquier otro error.
     */
    private static void obtenerIntoAllCLientes() throws SQLException{
        System.out.println("====Todos los clientes registradros====");
        ArrayList<Cliente> clientes = clienteDao.infotodosCLientes();
        for (Cliente cliente : clientes){
            System.out.println("========");
            System.out.println(cliente.toString()+"\n\t\t"+cliente.getCuenta().toString());
        }
        
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
            System.out.println("4.\tEstablecer Jefe como Empleado");
            System.out.println("5.\tMostar la información de un empleado.");
            System.out.println("6.\tMostar la información de todos los empleados.");
            System.out.println("7.\tVolver atras");
            
            System.out.print(": ");opcion=sc.nextInt();
            
            switch (opcion) {
                case 1:
                    if(!comprobarAutorizacion()){
                        System.out.println("No tienes autorización");
                        break;
                    }
                    try {
                        darEmpleadoAlta();
                } catch (SQLException | java.util.InputMismatchException e) {
                        System.err.println("Error a dar empleado de alta: "+e.getMessage());
                }
                    break;

                case 2:
                    if(!comprobarAutorizacion()){
                        System.out.println("No tienes autorización");
                        break;
                    }
                    try {
                        darEmpleadoBaja();
                } catch (SQLException e) {
                        System.err.println("Error al dar de baja a empleado: "+e.getMessage());
                }
                    break;
                case 3:
                    if(!comprobarAutorizacion()){
                        System.out.println("No tienes autorización");
                        break;
                    }
                    try {
                        establecerEmpleadoJefe();
                } catch (SQLException e) {
                        System.err.println("Error al establecer empleado como jefe: "+e.getMessage());
                }
                    break;
                case 4: 
                    if(!comprobarAutorizacion()){
                        System.out.println("No tienes autorización");
                        break;
                    }
                    try {
                    establecerJefeEmpleado();
                } catch (SQLException e) {
                        System.err.println("Erro al establecer jefe como empleado: "+e.getMessage());
                }
                   break;
                case 5:
                    try {
                        obtenerEmpleado();
                } catch (SQLException e) {
                        System.err.println("Error al obtener info de empleado: "+e.getMessage());
                }
                    break;
                case 6:
                    try {
                        obtenerAllEmpleados();
                } catch (SQLException e) {
                        System.err.println("Erro al obtener los empleados: "+e.getMessage());
                }
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion!=7);
    }
    
    /**
     * Metodo para comprobar si el empleado actual puede realizar las acciones o no.
     * @return 
     */
    private static boolean comprobarAutorizacion(){
        if(empleadoActual.isEsJefe()){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Metodo para añadir empleado a la base de datos.
     * @throws SQLException en caso de que se produzca cualquier otro error.
     */
    private static void darEmpleadoAlta() throws SQLException{
        System.out.println("===Dar de alta a un Empleado===");
        System.out.print("Dime el nombre del Empleado: ");String nombre=sc.next();
        System.out.print("Dime los apellidos de "+nombre+" : ");String apellidos=sc.next();
        System.out.print("Dime el DNI de "+nombre+" : ");String dni=sc.next();
        System.out.print("Dime la edad de "+nombre+" : ");int edad = sc.nextInt();
        System.out.print("Dime el email de "+nombre+" : ");String email=sc.next();
        System.out.print("Dime le telefono de "+nombre+" : ");int telefono=sc.nextInt();
        System.out.print("Dime la dirección de "+nombre+" : ");String direccion=sc.next();
        System.out.print("Dime la contraseña de "+nombre+" : ");String contraseña=sc.next();
        System.out.print("El emplado "+nombre+" es jefe? (si/no):");
        boolean esJefe = (sc.next().equalsIgnoreCase("si"));
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
        System.out.print("Dime el DNI del Empleado a establecer como Jefe: ");String dni = sc.next(); 
        empleadodao.establecerEmpleadoAJefe(dni);
    }
    /**
     * Metodo para establecer un empleado como jefe.
     * @throws SQLException 
     */
    private static void establecerJefeEmpleado() throws SQLException{
        System.out.println("===Establecer Jefe como Empleado===");
        System.out.print("Dime el DNI del Jefe a establecer como Empleado: ");String dni = sc.next(); 
        empleadodao.establecerJefeEmpleado(dni);
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
        System.out.println("====Todos los Empleados registradros====");
       ArrayList<Empleado> empleados = empleadodao.infotodosEmpleados();
        for (Empleado emple : empleados){
            System.out.println("========");
            System.out.println(emple.toString());
        } 
    }
}
