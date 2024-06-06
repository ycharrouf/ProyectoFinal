/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;


import Interfaces.EncriptacionPass;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Yassin
 */
public class EmpleadoDAO implements EncriptacionPass{
    private static Connection conexion;
    private static int numEmpleados;

    public EmpleadoDAO(Connection conexion) {
        this.conexion = conexion;
    }
    
    /**
     * Metodo para añadir el empleado
     * @param empleado para ser añadido a la base de datos.
     * @throws SQLException en caso de que se produzca cualquier error.
     */
    public void añadirEmpleado(Empleado empleado) throws SQLException{
        String sql="insert into empleados  values (?,?,?,?,?,?,?,?,?);";
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
    
    /**
     * Eliminar empleado de la base de datos
     * @param dni necesario para buscarlo
     * @throws SQLException en caso de que se produzca cualquier otro error.
     */
    public void eliminarEmpleado(String dni) throws SQLException{
        String sql ="delete from empleados where dni= ? ";
        try (PreparedStatement statement = conexion.prepareStatement(sql)){
            statement.setString(1, dni);
            statement.executeUpdate();
        }
    }
    
    /**
     * Establecer un empleado como jefe
     * @param dni necesario para identificarlo
     * @throws SQLException en caso de que se produzca cualquier otro error.
     */
    public void establecerEmpleadoAJefe(String dni) throws SQLException{
       String sql ="update empleados set esJefe=1 where dni= ? ";
        try (PreparedStatement statement = conexion.prepareStatement(sql)){
            statement.setString(1, dni);
            statement.executeUpdate();
        }
    }
    /**
     * Establecer un empleado como jefe
     * @param dni necesario para identificarlo
     * @throws SQLException en caso de que se produzca cualquier otro error.
     */
    public void establecerJefeEmpleado(String dni) throws SQLException{
       String sql ="update empleados set esJefe=0 where dni= ? ";
        try (PreparedStatement statement = conexion.prepareStatement(sql)){
            statement.setString(1, dni);
            statement.executeUpdate();
        }
    }
    
    /**
     * Metodo para obtener un empleado concreto
     * @param dni para diferenciarlo de los demas
     * @return un empleado
     * @throws SQLException en caso de que se produzca cualquier otro error.
     */
    public Empleado obtenerEmpleado(String dni) throws SQLException{
        Empleado empleado = null;
        String sql = "select * from empleados where dni= '"+dni+"';";
        try(Statement statement = conexion.createStatement()){
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                empleado= new Empleado(rs.getString("nombre"), rs.getString("apellidos"), rs.getInt("edad"), rs.getString("email"), rs.getString("dni"), rs.getInt("telefono"), rs.getString("direccion"), rs.getString("contraseña"), rs.getBoolean("esJefe"));
            }
        }
        //comprobamos el cliente si esta a null o no
        if(empleado!=null){
            return empleado;
        }else{
            throw new SQLException("Error al obtener información del cliente.");
        }
    }
    
    /**
     * Metodo para obtener todos los empleado registrados.
     * @return un resulset con todos los clientes.
     * @throws SQLException en caso de que se produzca cualquier otro error.
     */
    public ArrayList<Empleado> infotodosEmpleados () throws SQLException{
        ArrayList<Empleado> empleados = new ArrayList<>();
        String sql = "select * from empleados;";
        try(Statement statement = conexion.createStatement()){
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {                
                empleados.add(new Empleado(rs.getString("nombre"), rs.getString("apellidos"), rs.getInt("edad"), rs.getString("email"), rs.getString("dni"), rs.getInt("telefono"), rs.getString("direccion"), rs.getString("contraseña"), rs.getBoolean("esjefe")));
            }
        }
        if(!empleados.isEmpty()){
            return empleados;
        }else{
            throw new SQLException("No se a encontrado ningun empleado registrado.");
        }
    }
}
