/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus;

import java.util.Scanner;

/**
 * Clase donde se encuentra las funcionalidades y menú de los empleados.
 * @author Yassin 
 */
public class MenuFuncionalidadEmpleado {
    private static Scanner sc = new Scanner(System.in);
    
    /**
     * Menú principal que van a tener los trabajadores.
     */
    public static void menuPrincipalEmpleados(){
        int opcion=0;
        do {       
            System.out.println("");
            System.out.println("================BANCO: Empleado============= ");
            System.out.println("1.\tGestión de usuarios.");
            System.out.println("2.\tGestión de empleados.");
        } while (true);
    }
    
    /**
     * Menú para gestion enfocada a los clientes.
     */
    public static void menuGestUsuarios(){
        int opcion=0;
        do {            
            System.out.println("");
            System.out.println("=========Gestión de Clientes=========");
            System.out.println("1.\tDar a un cliente de alta.");
            System.out.println("2.\tDar a un cliente de baja.");
            System.out.println("3.\tCambiar información de un cliente.");
            System.out.println("4.\tCertificado de titularidad.");//Tiene que tener tanto el Cliente como el empleado
            System.out.println("5.\tMostrar informacion de un Cliente.");
            System.out.println("6.\tMostar todos los clientes");
            System.out.println("7.\tvolver atras.");
            
            System.out.print(": ");opcion=sc.nextInt();
            
            switch (opcion) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (true);
    }
    
    /**
     * Menú para la gestión enfocada a los empleados del banco
     */
    public static void menuGestionEmpleados(){
        int opcion=1;
        do {                
            System.out.println("");
            System.out.println("=======Gestión de Empleados=======");
            System.out.println("1.\tDar a un Empleado de alta.");
            System.out.println("2.\tDar a un Empleado de baja.");
            System.out.println("3.\tEstablecer a un empleado como Jefe");
            System.out.println("4.\tMostar la información de un empleado.");
            System.out.println("5.\tMostar la información de todos los empleados.");
            System.out.println("6.\tVolver atras");
            
            System.out.print(": ");opcion=sc.nextInt();
            
            switch (opcion) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (true);
    }
}
