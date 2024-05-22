/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Interfaces.EncriptacionPass;
import java.sql.*;

/**
 * 
 * @author Yassin
 */
public class EmpleadoDAO implements EncriptacionPass{
    private Connection conexion;

    public EmpleadoDAO(Connection conexion) {
        this.conexion = conexion;
    }
    
    public void añadirEmpleado(Empleado empleado) throws SQLException{
        String sql="insert into clientes  values (?,?,?,?,?,?,?,?,?,);";
            try(PreparedStatement statement = conexion.prepareStatement(sql)){
                statement.setString(1, empleado.getDNI());
                statement.setString(2, empleado.getNombre());
                statement.setString(3, empleado.getApellido());
                statement.setInt(4, empleado.getEdad());
                statement.setString(5, empleado.getEmail());
                statement.setString(6, empleado.getDireccion());
                statement.setInt(7,empleado.getTelefono());
                statement.setString(8, EncriptacionPass.encriptaPass(empleado.getContaseña()));
                statement.setBoolean(9, empleado.isEsJefe());
                statement.executeUpdate();
            }
    }
}
