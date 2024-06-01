/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Clase movimiento donde se registrarán los movimiento de los clientes y se podrá obtener la información de la misma.
 * @author Yassin
 */
public class Movimientos {
    private String concepto;
    private double importe;
    private Cliente beneficiario;
    private String fecha;

    public Movimientos(String concepto, double importe, Cliente beneficiario) {
        this.concepto = concepto;
        this.importe = importe;
        this.beneficiario = beneficiario;
        //Obtenemos solo la fecha.
        fecha=String.valueOf(LocalDateTime.now()).substring(0,10);
    }
    
    @Override
    public String toString() {
        return "Movimiento->\nconcepto: " + concepto + "\nimporte:" + importe + "\nbeneficiario: " + beneficiario + "\nfecha: " + fecha ;
    }
    
}
