package InterfazUI;

import Interfaz.*;
import java.util.Scanner;
import java.util.ArrayList;
import Dominio.*;
import java.lang.Object;

/*
Joaquin Sommer - 184441
Marcelo Ferreira - 175240
 */
public class Validaciones1 {

    Scanner input = new Scanner(System.in);

    

   

    //Devuelvo true si el alias esta repetido
    public boolean chequearAlias(String alias,
            ArrayList<Jugador> listaJugadores) {
        boolean esta = false;
        for (int i = 0; i < listaJugadores.size(); i++) {
            if (alias.equals(listaJugadores.get(i).getAlias())) {
                esta = true;
            }
        }
        return esta;
    }

    //Valido que la edad sea positiva y realista
    public boolean validarEdad(String str) {
        boolean esta = false;
        if (str.matches("^[0-9]+")) {
            int edad = Integer.parseInt(str);
            if (edad < 120 && edad > 1) {
                esta = true;
            }
        }
        return esta;
    }



    //Valido que el numero sea psoitivo
    public int validarNumeroPositivo() {
        int num = trycatchint();
        boolean esta = false;
        while (!esta) {
            if (num > 0) {
                esta = true;
            } else {
                System.out.println("Ingrese un numero mayor a 0");
                num = trycatchint();
            }
        }
        return num;
    }

    //Valido que el string tnega solo letras
    public boolean soloLetras(String texto) {
        boolean esta = false;
        if (texto.replace(" ", "").matches("^[ A-Za-z]+$")) {
            esta = true;
        }
        return esta;
    }

    //Valido que el alias sea letras y numeros pero solo una palabra
    public boolean soloLetrasNumeros(String texto) {
        boolean esta = false;
        if (texto.matches("^[a-zA-Z0-9]+")) {
            esta = true;
        }
        return esta;
    }

    public boolean soloNumeros(String str) {
        boolean esta = false;
        if (str.matches("^[0-9]+") && Integer.parseInt(str) > 0) {
            esta = true;
        }
        return esta;
    }

    //Valido que sea un numero el input
    public int trycatchint() {
        int aux = 0;
        Scanner input = new Scanner(System.in);
        boolean ok = false;
        while (!ok) {
            try {
                aux = input.nextInt();

                ok = true;
            } catch (Exception e) {
                input.nextLine();
                System.out.println("Ingrese un numero");

            }
        }
        return aux;
    }

    public String stringNoVacio() {
        Scanner input = new Scanner(System.in);
        String vacio = input.nextLine();

        while (vacio.length() < 1) {
            System.out.println("Vacio no es una opcion. Reingrese");
            vacio = input.nextLine();
        }
        return vacio;
    }

    public boolean stringVacio() {
        boolean ok = true;
        Scanner input = new Scanner(System.in);
        String vacio = input.nextLine();
        while (vacio.length() > 0) {
            System.out.println("Presione ENTER para continuar");
            vacio = input.nextLine();
        }
        return ok;
    }
}
