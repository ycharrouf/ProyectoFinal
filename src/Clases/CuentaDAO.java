/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import java.sql.*;
/**
 *
 * @author Yassin
 */
public class CuentaDAO {
    private static Connection conexion;

    public CuentaDAO(Connection conexion) {
        this.conexion = conexion;
    }
    
    /**
     * Antes de añadir el ciente es necesario añadir la cuenta por lo que primero hay que añadir la y después se puede añadir el cliente
     * @param cliente necesario para obtener la información de la cuenta
     * @return true si se a podido insertar en la tabla y falso en caso contrario.
     * @throws SQLException en caso de que se produzca cualquier otro error
     */
    public boolean registrarCuenta(Cliente cliente) throws SQLException{
        boolean paso=false;
        String sql="insert into cuenta values(?,?,?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)){
            statement.setString(1, cliente.getCuenta().getNumCuenta());
            statement.setDouble(2, cliente.getCuenta().getSaldo());
            statement.setString(3, cliente.getCuenta().getTipoCuenta());
            
            if (statement.executeUpdate()>0){
                return true;
            }
        }
        return paso;
    }
}
