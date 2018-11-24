package Interfaz;

import java.util.Scanner;
import java.util.ArrayList;
import Dominio.*;
import java.lang.Object;

/*
Joaquin Sommer - 184441
Marcelo Ferreira - 175240
 */
public class Validaciones {

    Scanner input = new Scanner(System.in);

    //Validacion de la entrada del menu
    public char opcionValida() {
        String opcion;
        opcion = stringNoVacio();
        char letra = opcion.charAt(0);
        boolean correcto = false;
        while (!correcto) {
            if (opcion.equals("a") || opcion.equals("b") || opcion.equals("c")
                    || opcion.equals("d") || opcion.equals("e")) {
                letra = opcion.charAt(0);
                correcto = true;
            } else {
                System.out.println("Ingrese una opcion valida");
                opcion = stringNoVacio();
            }
        }
        return letra;
    }

    /*Valido que lo que ingrese al movimiento sea un movimiento valido o que
    abandona o que cambia de visualizacion del tablero*/
    public String validarMovi() {
        String opcion;
        opcion = stringNoVacio();
        char num = '9';
        char letra = 'C';
        if (opcion.length() > 1) {
            num = opcion.charAt(0);
            letra = opcion.charAt(1);
        }
        boolean correcto = false;
        while (!correcto) {
            if (((letra == 'A' || letra == 'I' || letra == 'D') && (num == '1'
                    || num == '2' || num == '3' || num == '4' || num == '5'
                    || num == '6' || num == '7' || num == '8')
                    && opcion.length() < 3) || opcion.equals("VERR")
                    || opcion.equals("VERN") || opcion.equals("X")) {
                correcto = true;
            } else {
                System.out.println("Ingrese un movimiento valido o una "
                        + "forma de visualizacion valida");
                opcion = stringNoVacio();
                num = opcion.charAt(0);
                letra = opcion.charAt(1);
            }
        }
        return opcion;
    }

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
    public int validarEdad(int edad) {
        boolean esta = false;
        while (!esta) {
            if (edad < 120 && edad > 1) {
                esta = true;
            } else {
                System.out.println("Edad no realista. Reingrese");
                edad = input.nextInt();
                input.nextLine();
            }
        }

        return edad;
    }

    //Valido que solo pueda ingresar 1 o 2 o 3
    public int validarTipoPartida() {
        int tipo = trycatchint();
        boolean esta = false;
        while (!esta) {
            if (tipo == 1 || tipo == 2 || tipo == 3) {
                esta = true;
            } else {
                System.out.println("Tipo solo puede ser 1, 2 o 3. Reingrese");
                tipo = trycatchint();
            }
        }
        return tipo;
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
    public String soloLetras(String texto) {
        boolean esta = false;
        while (!esta) {
            if (texto.replace(" ", "").matches("^[ A-Za-z]+$")) {
                esta = true;
            } else {
                System.out.println("Nombre no valido. Solo se aceptan "
                        + "letras y espacios. Reingrese");
                texto = input.nextLine();
            }
        }

        return texto;
    }

    //Valido que solo pueda poner si quiere seguir jugando o no
    public String validarSiNo() {
        String texto = stringNoVacio();
        boolean esta = false;
        while (!esta) {
            if (texto.equals("si") || texto.equals("no")) {
                esta = true;
            } else {
                System.out.println("Solo se acepta si o no. Reingrese");
                texto = stringNoVacio();
            }
        }

        return texto;
    }

    //Valido que el alias sea letras y numeros pero solo una palabra
    public String soloLetrasNumeros(String texto) {
        boolean esta = false;
        while (!esta) {
            if (texto.matches("^[a-zA-Z0-9]+")) {
                esta = true;
            } else {
                System.out.println("Alias no valido. Solo se aceptan letras y "
                        + "numeros sin espacios. Reingrese");
                texto = input.nextLine();
            }
        }

        return texto;
    }

    //Valido que el jugador a elegir sea de la lista
    public int validarJugador(int jugador, ArrayList<Jugador> jugadores) {
        while (jugador > jugadores.size() - 1
                || jugador < 0) {
            System.out.println("Ingrese jugador valido");
            jugador = trycatchint();
        }
        return jugador;
    }

    //Valido que la partida a elegir sea de la lista
    public int validarPartida(ArrayList<Partida> partidas) {
        int partida = trycatchint();
        while (partida > partidas.size() - 1
                || partida < 0) {
            System.out.println("Ingrese partida valida");
            partida = trycatchint();
        }
        return partida;
    }

    //Valido que el jugador a elegir sea de la lista y que a parte no sea igual 
    //al primer jugador
    public int validarJugador2(int jugador, ArrayList<Jugador> jugadores, 
            Jugador jugadorUno) {
        while (jugador > jugadores.size() - 1
                || jugador < 0 || jugadores.get(jugador).equals(jugadorUno)) {
            System.out.println("Ingrese jugador valido");
            jugador = trycatchint();
        }
        return jugador;
    }

    public boolean soloNumeros(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
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

    //Me fijo si hay jugadores para jugar una partida
    public boolean hayJugadores(ArrayList<Jugador> jugadores) {
        boolean hay = true;
        if (jugadores.size() < 2) {
            System.out.println("No hay suficientes "
                    + "jugadores para empezar un partido");
            hay = false;
        }
        return hay;
    }

    //Me fijo si hay partidas para replicar
    public boolean hayPartidas(ArrayList<Partida> partidas) {
        boolean hay = true;
        if (partidas.isEmpty()) {
            System.out.println("No hay partidas registradas");
            hay = false;
        }
        return hay;
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
