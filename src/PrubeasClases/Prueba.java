/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PrubeasClases;

import Clases.Cliente;
import Clases.ClienteDAO;
import Clases.Conexion;
import Clases.Cuenta;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author ----
 */
public class Prueba {
    public static void main(String[] args) {
        Cuenta cuenta1 = new Cuenta(1000, "particular");
        Cliente a = new Cliente("Juan", "Perez Gomez", 21, "paco@gmail.com", "12345678Z", 666666666, "La case nose","pass"
                , cuenta1, "particular");
        
        Connection conexion = Conexion.getConexion();
        ClienteDAO Client = new ClienteDAO(conexion);
        try {
            Client.darClienteAlta(a);
        } catch (SQLException e) {
            System.err.println("Error: "+e.getMessage());
        }
        System.out.println(a.getCuenta().getMovimientos());
        
    }
}
