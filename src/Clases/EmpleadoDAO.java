/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;


import Interfaces.EncriptacionPass;
import java.sql.*;
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
        try {
            numEmpleados+=numEmpleados();
            System.out.println(numEmpleados);
        } catch (SQLException e) {
            System.out.println("Error al contar los empleados: "+e.getMessage());
        }
    }
    /**
     * Metodo para contar el número de empleados
     * @return
     * @throws SQLException 
     */
    private int numEmpleados() throws SQLException{
        int contador=0;
        String sql ="select count(dni) from Empleados;";
        try (Statement statement = conexion.createStatement()){
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                contador++;
            }
        }
        return contador;
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
                numEmpleados++;
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
            numEmpleados--;
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
    public ResultSet infotodosEmpleados () throws SQLException{
        String sql = "select * from empleados;";
        try(Statement statement = conexion.createStatement()){
            ResultSet resulset = statement.executeQuery(sql);
            return  resulset;
        }
    }
    
    /**
     * Probado funciona
     * @param dni
     * @throws SQLException 
     */
    ///Revisar lo de contar los empleados
    public static void main(String[] args) {
        Empleado emple =  new  Empleado("juan", "es empleado", 23, "juan@mgi", "12345678Z", 666666666, "no lose", "pass", false);
        Connection conexión = Conexion.getConexion();
        EmpleadoDAO update = new EmpleadoDAO(conexión);
        System.out.println("----------"+numEmpleados);
        try {
            update.añadirEmpleado(emple);
            System.out.println("Empleado añadido con exito");
            
        } catch (SQLException e) {
            System.out.println("Error: "+e.getMessage());
        }
        Scanner sc = new Scanner(System.in);
        sc.next();
        try {
            update.establecerEmpleadoAJefe(emple.getDNI());
        } catch (SQLException e) {
            System.out.println("Error "+e.getMessage());
        }
        System.out.println(numEmpleados);
        sc.next();
        try {
            update.eliminarEmpleado(emple.getDNI());
            System.out.println("empleado eliminado con exito");
        } catch (SQLException e) {
            System.out.println("Error: "+e.getMessage());
        }
        System.out.println(numEmpleados);
    }
}
