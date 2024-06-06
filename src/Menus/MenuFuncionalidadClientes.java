/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus;

import Clases.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase donde se encuentra las funcionalidades y menú de los Clientes.
 * @author Yassin
 */
public class MenuFuncionalidadClientes {
    private static Scanner sc = new Scanner(System.in).useDelimiter("\n");
    private static Cliente clienteActual=null;
    /**
     * Metodo para establecer el cliente actual.
     * @param cliente 
     */
    public static void setUsuarioActual(Cliente cliente){
        clienteActual=cliente;
    }
    
    /**
     * Menú principal de los clientes.
     */
    public static void menuPrincipal(){
        int opcion=0;
        do {            
            System.out.println("");
            System.out.println("================BANCO: Clientes============= ");
            System.out.println("1.\tMostrar información del usuario actual");
            System.out.println("2.\tMostrar Movimientos");
            System.out.println("3.\tCambio de datos de Usuario.");
            System.out.println("4.\tTransferencias/Bizum");
            System.out.println("5.\tCertificado de titularidad");////falta¡¡¡¡¡
            System.out.println("6.\tSalir");
            System.out.print(": ");
            opcion=sc.nextInt();
            switch (opcion) {
                
            case 1:
                infoClienteActual();
                break;
            case 2:
                obtenerMovimientos();
                break;
            case 3:
               try {
                cambioInformacion();
            } catch (SQLException e) {
                   System.err.println("Error al cambiar la información del usuario");
            }
               break;
            case 4:
                enviarDinero();
                break;
            case 5:
                try {
                    generarCertificadoTitularidad(clienteActual);
                    System.out.println("Certificado creado con exito¡¡");
            } catch (IOException e) {
                    System.err.println("Error al crear el certificado de titularidad: "+e.getMessage());
            }
                
                break;
            case 6:
                break;
            default:
                System.err.println("Error, la opción es invalida.");
            }
        } while (opcion!=6);
        
    }
    /**
     * Info del cliente
     */
    private static void infoClienteActual(){
        System.out.println("====Información Del Cliente Actual====");
        System.out.println("*DNI: "+clienteActual.getDNI());
        System.out.println("*Nombre: "+clienteActual.getNombre());
        System.out.println("*Apellidos: "+clienteActual.getApellido());
        System.out.println("*Edad: "+clienteActual.getEdad());
        System.out.println("*Email:"+clienteActual.getEmail());
        System.out.println("*Dirección: "+clienteActual.getDireccion());
        System.out.println("*Telefono: "+clienteActual.getTelefono());
        System.out.println("");
        System.out.println("====Información de la cuenta====");
        System.out.println("*Número de Cuenta: "+clienteActual.getCuenta().getNumCuenta());
        System.out.println("*Saldo: "+clienteActual.getCuenta().getSaldo());
    }
    
    /**
     * Metodo para obtener los movimientos asociados a este cliente.
     */
    private static void obtenerMovimientos(){
        System.out.println("====Movimientos====");
        MovimientosDAO movimientodao = new  MovimientosDAO(Conexion.getConexion());
        try {
            for(Movimientos mov : movimientodao.obtenerMovimientosCliente(clienteActual.getDNI())){
                System.out.println(mov.toString());
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los movimientos: "+e.getMessage());
        }
    }
    
    /**
     * Metodo para cambiar datos del Clinete actual.
     * @throws SQLException en caso de que se produzca cualquier otro error.
     */
    private static void cambioInformacion() throws SQLException{
        ClienteDAO clientedao = new ClienteDAO(Conexion.getConexion());

        System.out.println("===Cambiar información====");
        System.out.println("? Si no quieres cambiar alguna opción solo dale a ENTER.");
        //Nombre
        System.out.print("El nombre ("+clienteActual.getNombre()+") : ");
        String nombre = sc.next();
        if(!nombre.isEmpty())
            clienteActual.setNombre(nombre);
        //Apellidos
        System.out.print("Los apellidos ("+clienteActual.getApellido()+") : ");
        String apellidos = sc.next();
        if(!apellidos.isEmpty())
            clienteActual.setApellido(apellidos);
        //Edad
        System.out.print("La edad ("+clienteActual.getEdad()+") : ");
        String edad = sc.next();
        if(!edad.isEmpty())
            clienteActual.setEdad(Integer.parseInt(edad));
        //Email
        System.out.print("El email ("+clienteActual.getEmail()+") : ");
        String email = sc.next();
        if(!email.isEmpty())
            clienteActual.setEmail(email);
        //Telefono
        System.out.print("El telefono ("+clienteActual.getTelefono()+") : ");
        String telefono = sc.next();
        if(!telefono.isEmpty())
            clienteActual.setTelefono(Integer.parseInt(telefono));
        //Dirección
        System.out.print("La dirección : ("+clienteActual.getDireccion()+") : ");
        String direccion = sc.next();
        if(!direccion.isEmpty())
            clienteActual.setDireccion(direccion);
        
        clientedao.actualizarCliente(clienteActual);
       
    }
    
    /**
     * Menú para enviar Dinero.
     */
    private static void enviarDinero(){
        int opcion=0;
        
        System.out.println("===========Transferencias==========");
        System.out.println("1.\tTransferencia");
        System.out.println("2.\tBizum");
        System.out.println("3.\tAtras");
        opcion=sc.nextInt();

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
     * Metodo para hacer el bizum
     * @throws SQLException 
     */
    private static void bizum() throws SQLException{
        CuentaDAO cuentadao = new CuentaDAO(Conexion.getConexion());
        MovimientosDAO movdao = new MovimientosDAO(Conexion.getConexion());
        System.out.println("====Bizum====");
        System.out.print("Dime el número de la persona que vas a enviar el dinero: ");
        int telefono=sc.nextInt();
        Cliente destinatario = obtenerClienteBizum(telefono);
        System.out.print("Dime el importe a enviar: ");double importe=sc.nextInt();
        if(importe<=clienteActual.getCuenta().getSaldo()){
            //Destinatario
            destinatario.getCuenta().setSaldo(destinatario.getCuenta().getSaldo()+importe);
            cuentadao.actualizarCuenta(destinatario.getCuenta().getNumCuenta(), destinatario.getCuenta().getSaldo());//Actualizamos el saldo de la cuenta
            Movimientos movimientosBeneficiario = new Movimientos("Ha realizado una transferencia: +"+importe+"€", importe, destinatario.getDNI());
            destinatario.getCuenta().getMovimientos().add(movimientosBeneficiario);
            movdao.añadirMovimiento(movimientosBeneficiario,clienteActual);
            
            //Remitente
            clienteActual.getCuenta().setSaldo(clienteActual.getCuenta().getSaldo()-importe);
            cuentadao.actualizarCuenta(clienteActual.getCuenta().getNumCuenta(), clienteActual.getCuenta().getSaldo());//Actualizamos el saldo de la cuenta
            importe=importe*-1;
            Movimientos actual = new Movimientos("Envio de dinero", importe, destinatario.getDNI());
            movdao.añadirMovimiento(actual,clienteActual);
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
        Cliente cliente = null;//Beneficiario del bizum
        ClienteDAO clientedao = new ClienteDAO(Conexion.getConexion());
        ArrayList<Cliente> clientes = clientedao.infotodosCLientes();
        for (Cliente client : clientes){
            if(telefono==client.getTelefono()){
                cliente=client;
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
        CuentaDAO cuentadao = new CuentaDAO(Conexion.getConexion());
        MovimientosDAO movdao = new MovimientosDAO(Conexion.getConexion());
        System.out.print("Dime el numero de cuenta del destinatario: ");
        String cuentaDestinatario = sc.next();
        Cliente destinatario = obtenerClienteTransferencia(cuentaDestinatario);
        System.out.print("Dime el importe a enviar: ");double importe=sc.nextInt();
        if(importe<=clienteActual.getCuenta().getSaldo()){
            ///Añadimos el saldo y registramos el movimiento para el destinatario
            destinatario.getCuenta().setSaldo(destinatario.getCuenta().getSaldo()+importe);
            cuentadao.actualizarCuenta(destinatario.getCuenta().getNumCuenta(), destinatario.getCuenta().getSaldo());//Actualizamos el saldo de la cuenta
            Movimientos movimientosBeneficiario = new Movimientos("Ha realizado una transferencia: +"+importe+"€", importe, destinatario.getDNI());
            movdao.añadirMovimiento(movimientosBeneficiario,clienteActual);
            destinatario.getCuenta().getMovimientos().add(movimientosBeneficiario);
            
            ///Actualizamos y registramos movimiento del usuario actual.
            clienteActual.getCuenta().setSaldo(clienteActual.getCuenta().getSaldo()-importe);
            cuentadao.actualizarCuenta(clienteActual.getCuenta().getNumCuenta(), clienteActual.getCuenta().getSaldo());
            importe=importe*-1;
            Movimientos actual = new Movimientos("Envio de dinero", importe, destinatario.getDNI());
            movdao.añadirMovimiento(actual,clienteActual);
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
        Cliente clienteTransf = null;
        ArrayList<Cliente> clientes = clientedao.infotodosCLientes();
        for (Cliente cliente : clientes){
            if(numCuenta.equals(cliente.getCuenta().getNumCuenta())){
                clienteTransf=cliente;
            }
        }
        if(clienteTransf!=null){
            return clienteTransf;
        }else{
            throw new SQLException("No se a encontrado al usuario con el numero de cuenta indicado.");
        }
    }
    
    /**
     * Metodo para crear el certifciado de titularidad del cliente.
     * @param cliente
     * @return 
     */
    private static void generarCertificadoTitularidad(Cliente cliente) throws IOException{
        String ruta= "src/Certificados/certificado_"+cliente.getNombre()+"_"+cliente.getApellido()+".html";
        CertificadoTitularidad.generarCertTitular(ruta, cliente);
    }
}
