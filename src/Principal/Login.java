/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import java.util.Scanner;

/**
 * Clase para logearse en el banco.
 * @author Yassin
 */
public class Login {
    private static Scanner sc = new Scanner(System.in).useDelimiter("\n");
    
    static void login(){
        int opción=0;
        do {            
            System.out.println("==========================");
            System.out.println("Que tipo de usuario eres:");
            System.out.println("1.\tEmpleado");
            System.out.println("2.\tCLiente");
            System.out.print(": ");
            opción=sc.nextInt();
            
            switch (opción) {
                case 1:
                    System.out.println("empleado");
                    break;
                case 2:
                    
                    break;
                default:
                    System.out.println("La opción es incorrecta\nPor favor escoga otra.");
            }
        } while (opción!=1 && opción!=2);
    }
    public static void main(String[] args) {
    }
}
