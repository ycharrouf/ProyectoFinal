/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus;

import Clases.*;
import java.sql.*;
import java.util.Scanner;

/**
 * Clase donde se encuentra las funcionalidades y menú de los Clientes.
 * @author Yassin
 */
public class MenuFuncionalidadClientes {
    private static Scanner sc = new Scanner(System.in);
    private static Cliente clienteActual=null;
    /**
     * Menú principal de los clientes.
     */
    public static void menuPrincipal(){
        int opcion=0;
        do {            
            System.out.println("");
            System.out.println("================BANCO: Clientes============= ");
            System.out.println("1.\tMostrar información del usuario actual");
            System.out.println("2.\tCambio de datos de Usuario.");
            System.out.println("3.\tTransferencias/Bizum");
            System.out.println("4.\tCertificado de titularidad");////falta¡¡¡¡¡
            opcion=sc.nextInt();
        } while (opcion<0&&opcion>5);
        switch (opcion) {
            case 1:
                System.out.println(clienteActual.toString());
                break;
            case 2:
               try {
                cambioInformacion();
            } catch (SQLException e) {
                   System.err.println("Error al cambiar la información del usuario");
            }
               break;
            case 3:
                enviarDinero();
                break;
            case 4:
                break;
            default:
                System.err.println("Error, la opción es invalida.");
        }
    }
    
    /**
     * Menú para enviar Dinero.
     */
    private static void enviarDinero(){
        int opcion=0;
        do {            
            System.out.println("===========Transferencias==========");
            System.out.println("1.\tTransferencia");
            System.out.println("2.\tBizum");
            System.out.println("3.\tAtras");
            opcion=sc.nextInt();
        } while (opcion<=0&&opcion>=4);
        
        switch (opcion) {
            case 1:
                try {
                transferencia();
            } catch (SQLException e) {
                    System.err.println("Error al enviar el dinero: "+e.getMessage());
            }
                break;
            case 2:
                try {
                bizum();
            } catch (SQLException e) {
                    System.err.println("Error al enviar dinero: "+e.getMessage());
            }
            case 3:
                break;
            default:
                System.err.println("Error, la opción es invalida.");
        }
        
    }
    
    /**
     * Metodo para cambiar datos del Clinete actual.
     * @throws SQLException en caso de que se produzca cualquier otro error.
     */
    private static void cambioInformacion() throws SQLException{
        System.out.println("===Cambiar información====");
        System.out.println(clienteActual.toString());
        System.out.println("? Si no quieres cambiar alguna opción solo dale a ENTER.");
        String temp="";//variable temporal.
        System.out.print("El nombre ("+clienteActual.getNombre()+") : ");
        String nombre = temp=sc.next().isEmpty() ? clienteActual.getNombre() : temp;
        System.out.print("Los apellidos ("+clienteActual.getApellido()+" : ");
        String apellidos = temp=sc.next().isEmpty() ? clienteActual.getApellido() : temp;
        int tempInt=0;
        System.out.print("La edad ("+clienteActual.getEdad()+" : ");
        int edad = sc.hasNextInt() ?  sc.nextInt() : clienteActual.getEdad();///Funciona pero con dos enter.
        System.out.println("El email ("+clienteActual.getEmail()+") : ");
        String email = temp=sc.next().isEmpty() ? clienteActual.getEmail() : temp;
        System.out.println("El telefono ("+clienteActual.getTelefono()+") : ");
        int telefono = tempInt= sc.hasNextInt() ? sc.nextInt() : clienteActual.getEdad();
        System.out.println("La dirección : ("+clienteActual.getDireccion()+") : ");
        String direccion = temp=sc.next().isEmpty() ? clienteActual.getDireccion() : temp;
        //Ya tendríamos los datos
        ClienteDAO clientedao = new ClienteDAO(Conexion.getConexion());
        clientedao.actualizarCliente(clienteActual);
       
    }
    
    /**
     * Metodo para hacer el bizum
     * @throws SQLException 
     */
    private static void bizum() throws SQLException{
        System.out.println("====Bizum====");
        System.out.print("Dime el número de la persona que vas a enviar el dinero: ");
        int telefono=sc.nextInt();
        Cliente destinatario = obtenerClienteBizum(telefono);
        System.out.print("Dime el importe a enviar: ");double importe=sc.nextInt();
        if(importe<clienteActual.getCuenta().getSaldo()){
            destinatario.getCuenta().setSaldo(destinatario.getCuenta().getSaldo()+importe);
            Movimientos movimientosBeneficiario = new Movimientos("+"+importe+"€", importe, destinatario);
            destinatario.getCuenta().getMovimientos().add(movimientosBeneficiario);
            clienteActual.getCuenta().setSaldo(clienteActual.getCuenta().getSaldo()-importe);
            Movimientos actual = new Movimientos("Envio de dinero", importe, destinatario);
            clienteActual.getCuenta().getMovimientos().add(actual);
        }else{
            System.out.println("No tienes suficiente saldo para realizar esta operación.");
        }
        
    }
    
    /**
     * Metodo para obtener el cliente para realizar el bizum.
     * @param telefono
     * @return el cliente destinatario.
     * @throws SQLException en caso de que produzca cualquier otro error.
     */
    private static Cliente obtenerClienteBizum(int telefono) throws SQLException{
        Cliente cliente = null;
        ClienteDAO clientedao = new ClienteDAO(Conexion.getConexion());
        ResultSet rs = clientedao.infotodosCLientes();
        while (rs.next()) {            
            if(rs.getString("telefono").equalsIgnoreCase(String.valueOf(telefono))){
                cliente = new Cliente(rs.getString("nombre"), rs.getString("apellidos"),
                        rs.getInt("edad"), rs.getString("email"), rs.getString("dni"), 
                        telefono, rs.getString("direccion"), rs.getString("contraseña"));
            }
        }
        if(cliente!=null){
            return cliente;
        }else{
            throw new SQLException("No se a encontrado al usuario con el telefono indicado.");
        }
    }
    
    /**
     * Metodo para realizar la transferencia
     * @throws SQLException 
     */
    private static void transferencia() throws SQLException{
        System.out.print("Dime el numero de cuenta del destinatario: ");
        String cuentaDestinatario = sc.next();
        Cliente destinatario = obtenerClienteTransferencia(cuentaDestinatario);
        System.out.print("Dime el importe a enviar: ");double importe=sc.nextInt();
        if(importe<clienteActual.getCuenta().getSaldo()){
            destinatario.getCuenta().setSaldo(destinatario.getCuenta().getSaldo()+importe);
            Movimientos movimientosBeneficiario = new Movimientos("+"+importe+"€", importe, destinatario);
            destinatario.getCuenta().getMovimientos().add(movimientosBeneficiario);
            clienteActual.getCuenta().setSaldo(clienteActual.getCuenta().getSaldo()-importe);
            Movimientos actual = new Movimientos("Envio de dinero", importe, destinatario);
            clienteActual.getCuenta().getMovimientos().add(actual);
        }else{
            System.out.println("No tienes suficiente saldo para realizar esta operación.");
        }
    }
    
    /**
     * Metodo para obtener cliente beneficiario de transferencia
     * @param numCuenta
     * @return el cliente
     * @throws SQLException 
     */
    private static Cliente obtenerClienteTransferencia(String numCuenta) throws SQLException{
        ClienteDAO clientedao = new ClienteDAO(Conexion.getConexion());
        Cliente cliente = null;
        ResultSet rs = clientedao.infotodosCLientes();
        while (rs.next()) {            
            if(rs.getString("numCuenta").equalsIgnoreCase(numCuenta)){
                cliente = new Cliente(rs.getString("nombre"), rs.getString("apellidos"),
                        rs.getInt("edad"), rs.getString("email"), rs.getString("dni"), 
                        rs.getInt("telefono"), rs.getString("direccion"), 
                        rs.getString("contraseña"));
            }
        }
        if(cliente!=null){
            return cliente;
        }else{
            throw new SQLException("No se a encontrado al usuario con el numero de cuenta indicado.");
        }
    }
}
