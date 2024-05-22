/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import java.util.*;

/**
 * Clase cuenta donde se al que va asocidado un historial de movimientos, saldo y el tipo de cuenta.
 * @author Yassin
 */
public class Cuenta {
    private String NumCuenta;
    private double saldo;
    private String tipoCuenta;
    //private//  ///Array de movimientos para que esten todos asociados a la cuenta.
    protected static ArrayList<Movimientos> movimientos = new ArrayList<>();

    public Cuenta( double saldo, String tipoCuenta) {
        
        this.NumCuenta = "ES"+generarIBAN();
        this.saldo = saldo;
        //Cuanto el cliente crea al cuenta queda registrado.
        this.tipoCuenta=tipoCuenta;
    }

    public String getNumCuenta() {
        return NumCuenta;
    }

    public void setNumCuenta(String NumCuenta) {
        this.NumCuenta = NumCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public ArrayList<Movimientos> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(ArrayList<Movimientos> movimientos) {
        this.movimientos = movimientos;
    }
    
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
    
    //Posible falta de toString????
    
    /**
     * Metodo para generar el numero de iban para cada cliente, este iban tiene que ser unico e identificativo para cada cuenta.
     * @return un String con los numeros de la cuenta al falta de las letras del pais, como el "ES".
     */
    private static String generarIBAN(){
        //Partes de la cuenta.
        String controlIBAN="89";
        String codigoBanco="2039";
        String sucursalCuenta="3457";
        String controlBanco="55";
        
        Random rd = new Random();
        //Generamos el número de cuenta en dos pasos.
        int numCuenta1=0, numCuenta2=0;
        String numCuentaF="";
        while ((numCuentaF=String.valueOf(numCuenta1)+String.valueOf(numCuenta2)).length()!=10) {
            numCuenta1= rd.nextInt(0,99999);
            numCuenta2= rd.nextInt(0,99999);
            
        }
        return controlIBAN+codigoBanco+sucursalCuenta+controlBanco+numCuentaF;
    }
    
}
