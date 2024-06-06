/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.time.LocalDateTime;

/**
 * Clase movimiento donde se registrarán los movimiento de los clientes y se podrá obtener la información de la misma.
 * @author Yassin
 */
public class Movimientos {
    private String concepto;
    private double importe;
    private String beneficiario;
    private String fecha;

    public Movimientos(String concepto, double importe, String beneficiario) {
        this.concepto = concepto;
        this.importe = importe;
        this.beneficiario = beneficiario;
        //Obtenemos solo la fecha.
        fecha=String.valueOf(LocalDateTime.now()).substring(0,10);
    }

    public String getConcepto() {
        return concepto;
    }

    public double getImporte() {
        return importe;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public String getFecha() {
        return fecha;
    }
    
    
    @Override
    public String toString() {
        return "Movimiento -->\nconcepto: " + concepto + "\nimporte:" + importe + "\nbeneficiario: " + beneficiario + "\nfecha: " + fecha ;
    }
    
}
