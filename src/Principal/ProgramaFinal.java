/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Principal;

import Clases.*;
import com.sun.source.tree.BreakTree;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * La clase main donde se ejecutara el programa y realizará todas las funciones logicas.
 * @author Yassin Charrouf errynda
 */
public class ProgramaFinal {
    private static Scanner sc = new Scanner(System.in).useDelimiter("\n");
    private static Cliente clienteActual=null;
    private static Empleado empleadoActual=null;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int num = Login.login();
        if(num==2){
            try {
                obtenerClinete();
            } catch (SQLException e) {
                System.err.println("Error en el login: "+e.getMessage());
            }
        }if(num==1){
            try {
                obtenerEmpleado();
            } catch (SQLException e) {
                System.err.println("Error en el login: "+e.getMessage());
            }
        }
        if(clienteActual!=null){
            Menus.MenuFuncionalidadClientes.setUsuarioActual(clienteActual);
            Menus.MenuFuncionalidadClientes.menuPrincipal();
        }if(empleadoActual!=null){
            Menus.MenuFuncionalidadEmpleado.setEmpleadoActual(empleadoActual);
            Menus.MenuFuncionalidadEmpleado.menuPrincipalEmpleados();
        }
        System.out.println("Saliendo del programa...\nAdios.");
    }
    
    private static void obtenerClinete() throws SQLException{
        ClienteDAO clietnedao = new ClienteDAO(Conexion.getConexion());
        clienteActual = clietnedao.obtenerCliente(Login.dniCliente);
    }
    
    private static void obtenerEmpleado() throws SQLException{
        EmpleadoDAO empleadodao = new EmpleadoDAO(Conexion.getConexion());
        empleadoActual= empleadodao.obtenerEmpleado(Login.dniCliente);
    }
    
}
