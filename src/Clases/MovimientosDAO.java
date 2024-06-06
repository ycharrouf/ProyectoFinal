/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import java.sql.*;
import java.util.ArrayList;
/**
 * 
 * @author Yassin charrouf errynda
 */
public class MovimientosDAO {
    private static Connection conexion;

    public MovimientosDAO(Connection conexion) {
        this.conexion = conexion;
    }
    
    /**
     * Metodo para registrar cuando se ha creado la cuenta el usuario.
     * @param cliente
     * @return true en caso de que se haya guardado y false en caso contrario.
     */
    public boolean registroMovimientoNuevaCuenta(Cliente cliente) throws SQLException{
        boolean paso=false;
        String sql = "insert into movimientos (numCuenta, concepto, importe, dniBeneficiario) values (?,?,?,?)";
        try(PreparedStatement statement = conexion.prepareStatement(sql)){
            statement.setString(1, cliente.getCuenta().getNumCuenta());
            statement.setString(2, "El cliente ha creado una nueva cuenta.");
            statement.setDouble(3, cliente.getCuenta().getSaldo());
            statement.setString(4, cliente.getDNI());
            
            if(statement.executeUpdate()>0){
                paso=true;
            }
        }
        return paso;
    }
    
    public ArrayList<Movimientos> obtenerMovimientosCliente(String dni) throws SQLException{
        ArrayList<Movimientos> movimientos = new ArrayList<>();
        String sql = "select movimientos.* from movimientos, clientes where clientes.NumCuenta=movimientos.numCuenta and dni=\""+dni+"\";";
        try(Statement statement = conexion.createStatement()){
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                movimientos.add(new Movimientos(rs.getString("concepto"), rs.getDouble("importe"), dni));
            }
        }
        if(!movimientos.isEmpty()){
            return movimientos;
        }else{
            throw new SQLException("No se a encontrado ningun movimiento con este dni");
        }
    }
    
    /**
     * Metodo para añadir un movimiento y que quede registrado cada vez que se haga un movimiento
     * @param mov
     * @param remitente
     * @throws SQLException 
     */
    public void añadirMovimiento(Movimientos mov, Cliente remitente) throws SQLException{
        String sql = "insert into movimientos (numCuenta, concepto, importe, dniBeneficiario) values (?,?,?,?)";
        try(PreparedStatement statement = conexion.prepareStatement(sql)){
            if(mov.getImporte()<0){
                statement.setString(1, remitente.getCuenta().getNumCuenta());
                statement.setString(2, mov.getConcepto());
                statement.setDouble(3, mov.getImporte());
                statement.setString(4, remitente.getDNI());
                statement.executeUpdate();
            }else{
                statement.setString(1, remitente.getCuenta().getNumCuenta());
                statement.setString(2, mov.getConcepto());
                statement.setDouble(3, mov.getImporte());
                statement.setString(4, mov.getBeneficiario());
                statement.executeUpdate();
            }
            
        }
    }
}
