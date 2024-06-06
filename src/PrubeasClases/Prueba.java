/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PrubeasClases;

import Clases.Cliente;
import Clases.ClienteDAO;
import Clases.Conexion;
import Clases.Cuenta;
import Interfaces.EncriptacionPass;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author ----
 */
public class Prueba implements EncriptacionPass{
    public static void main(String[] args) {
        System.out.println(EncriptacionPass.encriptaPass("password"));
        
    }
}
