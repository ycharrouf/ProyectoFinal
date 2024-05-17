/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

/**
 * Interfaz para poder encriptar la contraseñas de los clientes o empleados
 * Esta clase implementa el cifrado ROOT13 pero con ñ, por lo que cualquier desencriptador online no funciona.
 * @author Yassin
 */
public interface EncriptacionPass {
    static String letrasMay = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
    static String letrasMin = letrasMay.toLowerCase();

    static String encriptaPass(String pass){
        String passEncrip="";
        
        for (char i : pass.toCharArray()){
            //Primero comprueba si es una ñ o es un digito
            if(Character.isDigit(i))
                passEncrip+=i;
            else if(Character.isLowerCase(i)){//y después comprueba si es en minuscula
                passEncrip+=caracterCambiadolower(i);
            }else if (Character.isUpperCase(i) ){//O es mayuscula
                passEncrip+=caracterCambiadoUper(i);
            }else{//En caso contrario como simbolos pues que simplemente los concatene.
                passEncrip+=i;
            }
        }
        
        return passEncrip;
    }
    
    /**
     * Metodo para obtener la letra cambiada para letras en minuscula
     * @param a la letra en cuestion
     * @return la letra correspondiente
     */
    private static char caracterCambiadoUper(char a){
        int contador=0;
        //con esto obtengo la posición de la letra en el array.
        for (char i :letrasMay.toCharArray()){
            if(a==i){
                break;
            }
            contador++;
        }
        contador+=13;
        //Calculamos el la posición
        if(contador>=letrasMay.length())
            contador-=letrasMay.length();
        return letrasMay.charAt(contador);
    }
    
    /**
     * Metodo para obtener la letra cambiada para letras en minuscula
     * @param a la letra en cuestion
     * @return la letra correspondiente
     */
    private static char caracterCambiadolower(char a){
        int contador=0;
        //con esto obtengo la posición de la letra en el array.
        for (char i :letrasMin.toCharArray()){
            if(a==i){
                break;
            }
            contador++;
        }
        contador+=13;
        //Calculamos el la posición
        if(contador>=letrasMin.length())
            contador-=letrasMin.length();
        return letrasMin.charAt(contador);
    }
}
