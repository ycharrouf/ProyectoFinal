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
public class ClienteDAO implements EncriptacionPass{
   private static Connection conexion;

    public ClienteDAO(Connection conexion) {
        this.conexion = conexion;
    }
   
    /**
     * Metodo para añadir un clietne a la base de datos.
     * @param cliente necesario para obtener la información del mismo
     * @throws SQLException en caso de que se produzca cualquier otro error.
     */
    public void darClienteAlta(Cliente cliente) throws SQLException{
        
        if(new CuentaDAO(conexion).registrarCuenta(cliente)){
            String sql="insert into clientes  values (?,?,?,?,?,?,?,?,?,?);";
            try(PreparedStatement statement = conexion.prepareStatement(sql)){
                statement.setString(1, cliente.getDNI());
                statement.setString(2, cliente.getNombre());
                statement.setString(3, cliente.getApellido());
                statement.setInt(4, cliente.getEdad());
                statement.setString(5, cliente.getEmail());
                statement.setString(6, cliente.getDireccion());
                statement.setInt(7,cliente.getTelefono());
                statement.setString(8, cliente.getTipoCuenta());
                statement.setString(9, EncriptacionPass.encriptaPass(cliente.getContaseña()));
                statement.setString(10, cliente.getCuenta().getNumCuenta());
                statement.executeUpdate();
            }
        }
    }
    
    
    
}
