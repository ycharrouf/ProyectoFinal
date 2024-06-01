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
            String sql="insert into clientes  values (?,?,?,?,?,?,?,?,?);";
            try(PreparedStatement statement = conexion.prepareStatement(sql)){
                statement.setString(1, cliente.getDNI());
                statement.setString(2, cliente.getNombre());
                statement.setString(3, cliente.getApellido());
                statement.setInt(4, cliente.getEdad());
                statement.setString(5, cliente.getEmail());
                statement.setString(6, cliente.getDireccion());
                statement.setInt(7,cliente.getTelefono());
                statement.setString(9, EncriptacionPass.encriptaPass(cliente.getContaseña()));
                statement.setString(10, cliente.getCuenta().getNumCuenta());
                statement.executeUpdate();
            }
        }
    }
    
    /**
     * Metodo para eliminar un cliente de la base de datos.
     * @param dni para poder diferenciar al cliente de los demas
     * @throws SQLException en caso de que se produzca cualquier otro error.
     */
    public void darBajaCLiente (String dni) throws SQLException{
        String sql ="delete from clientes where dni = ?";
        try(PreparedStatement statement = conexion.prepareStatement(sql)){
            statement.setString(1, dni);
            statement.executeUpdate();
        }
    }
    
    /**
     * metodo para obtener la información especifica de un cliente.
     * @param dni para poder diferenciarlo.
     * @return un clinete
     * @throws SQLException en caso de que se produzca cualquier otro error.
     */
    public Cliente obtenerCliente(String dni) throws SQLException{
        Cliente cliente = null;
        String sql = "select * from clientes where dni= '"+dni+"';";
        try(Statement statement = conexion.createStatement()){
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                cliente= new Cliente(rs.getString("nombre"), rs.getString("apellidos"), rs.getInt("edad"), rs.getString("email"), rs.getString("dni"), rs.getInt("telefono"), rs.getString("direccion"), rs.getString("contraseña"));
            }
        }
        //comprobamos el cliente si esta a null o no
        if(cliente!=null){
            return cliente;
        }else{
            throw new SQLException("Error al obtener información del cliente.");
        }
    }
    
    /**
     * Metodo para actualizar la información del cliente
     * @param cliente para obtener la información
     * @return el número de filas afectadas
     * @throws SQLException en caso de que se produzca cualquier otro error.
     */
    public int actualizarCliente (Cliente cliente) throws SQLException{
        String sql = "update clientes set nombre='?', apellidos='?', edad=?, email='?', direccion='?', telefono=? where dni='?'";
        
        try(PreparedStatement statement = conexion.prepareStatement(sql)){
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellido());
            statement.setInt(3, cliente.getEdad());
            statement.setString(4, cliente.getEmail());
            statement.setString(5, cliente.getDireccion());
            statement.setInt(6, cliente.getTelefono());
            statement.setString(7, cliente.getDNI());
            
            return statement.executeUpdate();
        }
    }
    
    /**
     * Metodo para obtener todos los clientes.
     * @return un resulset con todos los clientes.
     * @throws SQLException en caso de que se produzca cualquier otro error.
     */
    public ResultSet infotodosCLientes () throws SQLException{
        String sql = "select * from clientes;";
        try(Statement statement = conexion.createStatement()){
            ResultSet resulset = statement.executeQuery(sql);
            return  resulset;
        }
    }
}
