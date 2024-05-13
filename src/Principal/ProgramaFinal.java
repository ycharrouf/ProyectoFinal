/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Principal;

import java.util.Scanner;

/**
 * La clase main donde se ejecutara el programa y realizará todas las funciones logicas.
 * @author Yassin Charrouf errynda
 */
public class ProgramaFinal {
    private static Scanner sc = new Scanner(System.in).useDelimiter("\n");
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    public static void menú(){
        int opcion=0;
        do {       
            System.out.println("");
            System.out.println("================BANCO============= ");
            System.out.println("1.\tGestión de usuarios.");
        } while (true);
    }
    
    public static void menuGestUsuarios(){
        int opcion=0;
        do {            
            System.out.println("");
            System.out.println("=========Gestión de usuarios=========");
            System.out.println("1.\tDar a un cliente de alta.");
            System.out.println("2.\tDar a un cliente de baja.");
            System.out.println("3.\tCambiar información de un cliente.");
            System.out.println("4.\tCertificado de titularidad.");
        } while (true);
    }
}
