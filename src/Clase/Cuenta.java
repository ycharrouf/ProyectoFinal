/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clase;
import java.util.*;
/**
 *
 * @author Yassin
 */
public class Cuenta {
    private int NumCuenta;
    private Cliente cliente;
    private double saldo;
    //private//  ///Array de movimientos para que esten todos asociados a la cuenta.
    private ArrayList<Movimientos> movimientos;

    public Cuenta(int NumCuenta, Cliente cliente, double saldo, ArrayList<Movimientos> movimientos) {
        this.NumCuenta = NumCuenta;
        this.cliente = cliente;
        this.saldo = saldo;
        this.movimientos = movimientos;
    }

    public int getNumCuenta() {
        return NumCuenta;
    }

    public void setNumCuenta(int NumCuenta) {
        this.NumCuenta = NumCuenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
    //Posible falta de toString????
}
