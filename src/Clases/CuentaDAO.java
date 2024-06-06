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
        boolean paso1=false;
        boolean paso2=false;
        String sql="insert into cuenta values(?,?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)){
            statement.setString(1, cliente.getCuenta().getNumCuenta());
            statement.setDouble(2, cliente.getCuenta().getSaldo());
            if (statement.executeUpdate()>0){
                paso1=true;
            }
        }
        MovimientosDAO movimientodao = new MovimientosDAO(conexion);
        if(movimientodao.registroMovimientoNuevaCuenta(cliente)){
            paso2=true;
        }
        if(paso1&&paso2){
            return true;
        }else{
            return false;
        }
        
    }
    
    /**
     * Metodo para actualizar el saldo de la cuenta.
     * @param numCuenta
     * @param saldo
     * @return el número de filas afectadas.
     * @throws SQLException 
     */
    public int actualizarCuenta(String numCuenta, double saldo) throws SQLException{
        String sql = "update cuenta set saldo="+saldo+" where numCuenta='"+numCuenta+"';";
        try(Statement st = conexion.createStatement()){
            return st.executeUpdate(sql);
        }
    }
}
