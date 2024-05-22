/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.*;

/**
 * Esta clase es para conectarse a la base de datos
 * @author Yassin
 */
public class Conexion {
    private static final String BD = "jdbc:mysql://localhost:3306/proyectofinal";
    private static final String USER="yassin";
    private static final String PASS="RTYU77@@";
    private static Connection conexion;

    /**
     * Metodo para crear la conexión con la base de datos.
     * @return un objeto conexión con la base de datos
     */
    public static Connection getConexion() {
        if (conexion==null){
            try {
                conexion= DriverManager.getConnection(BD,USER,PASS);
                System.out.println("Conexión establecida con exito con la base de datos.\n");
            } catch (SQLException e) {
                System.err.println("Error al conectar con la base de datos: "+e.getMessage());
            }
        }
        return conexion;
    }
    
    /**
     * Metodo para cerrar conexion con la base de datos.
     */
    public static void cerrarConexion(){
        if(conexion!=null){
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión con base de datos : "+e.getMessage());
            }
        }
    }
    
}
