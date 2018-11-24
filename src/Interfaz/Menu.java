package Interfaz;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import Dominio.*;
import java.util.*;

/*
Joaquin Sommer - 184441
Marcelo Ferreira - 175240
 */
public class Menu {

    Sistema sistema = new Sistema();
    Validaciones validaciones = new Validaciones();
    Scanner input = new Scanner(System.in);

    public void iniciar() {
        mostrarMenu();
    }

    //Menu
    public void mostrarMenu() {
        boolean salida = false;
        while (!salida) {
            try {
                opcionesMenu();
                switch (validaciones.opcionValida()) {
                    case 'a':
                        System.out.println("Registro Jugador");
                        sistema.agregarJugador(registrarJugador(
                                sistema.getListaJugadores()));

                        break;
                    case 'b':
                        if (validaciones.hayJugadores(
                                sistema.getListaJugadores())) {
                            sistema.agregarPartida(jugarPartida());
                        }
                        break;
                    case 'c':
                        if (validaciones.hayPartidas(
                                sistema.getListaPartidas())) {
                            replicarPartida(sistema.getListaPartidas());
                        }
                        break;
                    case 'd':
                        if (!sistema.getListaJugadores().isEmpty()){
                        Collections.sort(sistema.getListaJugadores(), 
                                Collections.reverseOrder());
                        imprimirRanking(sistema.getListaJugadores());
                        } else {
                            System.out.println("No hay jugadores registrados");
                        }
                        break;
                    case 'e':
                        salida = true;
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error, ingrese una opción valida.");
                input.nextLine();
            }
        }
    }

    //Registro jugador 
    public Jugador registrarJugador(ArrayList<Jugador> listaJugadores) {
        Jugador unJ;
        Scanner input = new Scanner(System.in);
        unJ = new Jugador();
        System.out.println("Ingrese Nombre ");
        unJ.setNombre(validaciones.soloLetras(input.nextLine()));
        System.out.println("Ingrese Alias ");
        String alias = validaciones.soloLetrasNumeros(input.nextLine());
        boolean esta = validaciones.chequearAlias(alias, listaJugadores);
        while (esta) {
            System.out.println("Alias ya existe. "
                    + "Ingrese un Alias nuevo");
            alias = input.nextLine();
            esta = validaciones.chequearAlias(alias, listaJugadores);
        }
        unJ.setAlias(alias);
        System.out.println("Ingrese Edad ");
        int edad = validaciones.trycatchint();
        unJ.setEdad(validaciones.validarEdad(edad));
        return unJ;
    }

    //Replico partida a partir de una lista de jugadas de una partida
    public void replicarPartida(ArrayList<Partida> partidas) {
        Tablero tablero = new Tablero();
        imprimirPartidas(partidas);
        int eleccion = validaciones.validarPartida(partidas);
        Partida partida = partidas.get(eleccion);
        ArrayList<Jugada> jugadas = partida.getListaJugadas();
        System.out.println("Repeticion de partida.Presione "
                + "ENTER para empezar.");
        for (int i = 0; i < jugadas.size(); i++) {
            if (validaciones.stringVacio()) {
                if (jugadas.get(i).getPieza().getColor().equals("Azul")) {
                    tablero.moverAzul(jugadas.get(i).getPieza(), jugadas
                            .get(i).getDireccion(), tablero.getTablero());
                    vern(tablero.getTablero());

                }
                if (jugadas.get(i).getPieza().getColor().equals("Rojo")) {
                    tablero.moverRojo(jugadas.get(i).getPieza(), jugadas
                            .get(i).getDireccion(), tablero.getTablero());
                    vern(tablero.getTablero());
                }
            }
        }
        System.out.println("Termino repeticion");

    }

    //Juego Partida y devuelo una partida
    public Partida jugarPartida() {
        Scanner input = new Scanner(System.in);
        Partida unaP = new Partida();
        Tablero tablero = new Tablero();
        imprimirJugadores(sistema.getListaJugadores());
        System.out.println("Seleccione jugador Azul");
        unaP.setJugadorAzul(sistema.getListaJugadores().get(validaciones.
                validarJugador(validaciones.trycatchint(), 
                        sistema.getListaJugadores())));
        System.out.println("Seleccione jugador Rojo");
        unaP.setJugadorRojo(sistema.getListaJugadores().get(validaciones.
                validarJugador2(validaciones.trycatchint(), 
                        sistema.getListaJugadores(), unaP.getJugadorAzul())));
        opcionesTer();
        unaP.setTipoPartida(validaciones.validarTipoPartida());
        int movi = cantidadMov(unaP.getTipoPartida());
        vern(tablero.getTablero());
        String vista = "VERN";
        boolean abandonoRojo = false;
        boolean abandonoAzul = false;
        /*Sigo jugando mientras no se haya terminado la partida del modo 
        prestablecido, mientras no haya abandonado ningun jugador y 
        finalmente me fijo si todas las piezas de algun jugador llegaron al 
        otro en el caso que hayan puesto muchos moviemientos*/
        while (!sistema.terminaPartida(unaP, movi, tablero.getTablero()) && 
                !abandonoRojo && !abandonoAzul && 
                !sistema.todasOpuesto(tablero.getTablero())) {
            boolean quieroJugar = true;
            ArrayList<Pieza> piezaMoverRojo = sistema.piezasRojo();
            /* Me fijo si tiene jugadas posibles para seguir jugando, tambien 
            si no termino la partida, si no abandono el rojo y finalmente si 
            quiere seguir jugando */
            while (sistema.posiblesJugadasRojo(tablero, piezaMoverRojo, 
                    unaP.getJugadorRojo()).size() > 0 && 
                    !sistema.terminaPartida(unaP, movi, tablero.getTablero()) 
                    && quieroJugar && !abandonoRojo && 
                    !sistema.todasOpuesto(tablero.getTablero())) {
                ArrayList<Jugada> jugadas = turnoRojo(unaP.getJugadorRojo(), 
                        tablero, piezaMoverRojo);
                if (jugadas.get(1).getDireccion() == 'X') {
                    abandonoRojo = true;
                }
                if (!abandonoRojo) {
                    unaP.setListaJugadas(jugadas.get(0));
                    piezaMoverRojo = sistema.piezasMovibles(tablero, 
                            jugadas.get(0).getPieza());
                    if (jugadas.get(1).getDireccion() == 'N') {
                        vista = "VERN";
                    }
                    if (jugadas.get(1).getDireccion() == 'R') {
                        vista = "VERR";
                    }
                    verTablero(tablero, vista);
                    if (!sistema.piezasMovibles(tablero, jugadas.get(0).
                            getPieza()).isEmpty() && 
                            !sistema.terminaPartida(unaP, movi, 
                                    tablero.getTablero())) {
                        System.out.println("Piezas que se pueden mover");
                        for (int i = 0; i < piezaMoverRojo.size(); i++) {
                            System.out.println(piezaMoverRojo.get(i));
                        }
                        System.out.println("Quiere seguir jugando");
                        String respuesta = validaciones.validarSiNo();
                        if (respuesta.equals("no")) {
                            quieroJugar = false;
                        }
                    }
                }
            }
            quieroJugar = true;
            ArrayList<Pieza> piezaMoverAzul = sistema.piezasAzul();
            /* Me fijo si tiene jugadas posibles para seguir jugando, tambien 
            si no termino la partida, si no abandono el rojo ni el azul  
            y finalmente si quiere seguir jugando */
            while (sistema.posiblesJugadasAzul(tablero, piezaMoverAzul, 
                    unaP.getJugadorAzul()).size() > 0 && 
                    !sistema.terminaPartida(unaP, movi, tablero.getTablero()) 
                    && quieroJugar && !abandonoAzul && !abandonoRojo 
                    && !sistema.todasOpuesto(tablero.getTablero())) {
                ArrayList<Jugada> jugadas = turnoAzul(unaP.getJugadorAzul(), 
                        tablero, piezaMoverAzul);
                if (jugadas.get(1).getDireccion() == 'X') {
                    abandonoAzul = true;
                }
                if (!abandonoAzul) {
                    unaP.setListaJugadas(jugadas.get(0));
                    piezaMoverAzul = sistema.piezasMovibles(tablero, 
                            jugadas.get(0).getPieza());
                    if (jugadas.get(1).getDireccion() == 'N') {
                        vista = "VERN";
                    }
                    if (jugadas.get(1).getDireccion() == 'R') {
                        vista = "VERR";
                    }
                    verTablero(tablero, vista);
                    if (!sistema.piezasMovibles(tablero, jugadas.get(0).
                            getPieza()).isEmpty() && !sistema.terminaPartida(
                                    unaP, movi, tablero.getTablero())) {
                        System.out.println("Piezas que se pueden mover");
                        for (int i = 0; i < piezaMoverAzul.size(); i++) {
                            System.out.println(piezaMoverAzul.get(i));
                        }
                        System.out.println("Quiere seguir jugando");
                        String respuesta = validaciones.soloLetras(
                                validaciones.stringNoVacio());
                        if (respuesta.equals("no")) {
                            quieroJugar = false;
                        }
                    }
                }
            }
        }
        System.out.println("Termino la partida");
        ganador(unaP, abandonoAzul, abandonoRojo, tablero);
        return unaP;
    }

    //Le paso la partida y si abandono algun jugador y imprimo quien gano 
    //o si es empate
    public void ganador(Partida unaP, boolean abandonoAzul, 
            boolean abandonoRojo, Tablero tablero) {
        boolean ganoRojo = false;
        boolean ganoAzul = false;
        boolean empate = false;
        int[] sumaM = sistema.sumaMitades(tablero.getTablero());
        int[] sumaL = sistema.sumaLados(tablero.getTablero());
        if (!abandonoAzul && !abandonoRojo) {
            if (unaP.getTipoPartida() == 1 && 
                    !sistema.todasOpuesto(tablero.getTablero())) {
                if (sumaM[0] > sumaM[1]) {
                    ganoRojo = true;
                } else if (sumaM[0] < sumaM[1]) {
                    ganoAzul = true;
                } else {
                    empate = true;
                }
            } else {
                if (sumaL[0] > sumaL[1]) {
                    ganoRojo = true;
                }
                if (sumaL[0] < sumaL[1]) {
                    ganoAzul = true;
                }
            }
        }
        if (abandonoAzul == true || ganoRojo == true) {
            System.out.println("El ganador es " + unaP.getJugadorRojo().
                    getAlias());
            unaP.getJugadorRojo().sumarUnaGanada();
        }
        if (abandonoRojo == true || ganoAzul == true) {
            System.out.println("El ganador es " + unaP.getJugadorAzul().
                    getAlias());
            unaP.getJugadorAzul().sumarUnaGanada();
        }
        if (empate == true) {
            System.out.println("Empate");
        }
    }

    /*Turno del rojo. Yo quiero que me devuelva una Jugada pero tambien 
    necesito saber si cuando ingreso un movimiento abandono o cambio 
    el modo de visualizacion. Como no puedo devolver dos cosas entonces lo
    que hago es crear otra jugada que en la direccion tiene X si abandono
    o R si es vista reducida o N si es vista normal. Entonces la jugada 1
    es la que voy a agregar a la lista de jugadas y la segunda jugada solo
    extraigo la direccion y despues no la uso. */
    public ArrayList<Jugada> turnoRojo(Jugador jugador, Tablero tablero, 
            ArrayList<Pieza> piezaMover) {
        ArrayList<Jugada> dosJugadas = new ArrayList<>();
        Jugada jugada = new Jugada();
        jugada.setJugador(jugador);
        System.out.println("Turno del jugador Rojo - " + jugador.getAlias());
        System.out.println("Ingrese movimiento");
        String jugadaAux = visual(validaciones.validarMovi());
        //Si abandono no hago nada 
        if (!jugadaAux.equals("X")) {
            if (piezaMover.size() == 8) {
                while (!tablero.validacionJugada(tablero.buscarPiezaRoja(
                        jugadaAux, tablero.getTablero()), jugadaAux.charAt(1), 
                        tablero.getTablero())) {
                    System.out.println("La jugada ingresada no es valida."
                            + " Reingrese");
                    jugadaAux = visual(validaciones.validarMovi());
                }
            } else {
                while ((!sistema.validarJugadasPosibles(piezaMover, 
                        jugadaAux.charAt(0))) || (!tablero.validacionJugada(
                                tablero.buscarPiezaRoja(jugadaAux, 
                                        tablero.getTablero()), 
                                jugadaAux.charAt(1), tablero.getTablero()))) {

                    System.out.println("La jugada ingresada no es valida. "
                            + "Reingrese");
                    jugadaAux = visual(validaciones.validarMovi());

                }
            }
            jugada.setPieza(tablero.buscarPiezaRoja(jugadaAux, 
                    tablero.getTablero()));
            jugada.setDireccion(jugadaAux.charAt(1));
            tablero.moverRojo(jugada.getPieza(), jugada.getDireccion(), 
                    tablero.getTablero());
        }
        dosJugadas.add(jugada);
        Jugada jugadaVisual = new Jugada();
        jugadaVisual.setDireccion(partirJugada(jugadaAux));
        dosJugadas.add(jugadaVisual);
        return dosJugadas;
    }

    /*Parto el String de jugada xq si queria cambiar el modo de visualizacion
    en la segundo parte tengo VERR o VERN. Si es VERR devuelvo R, si es VERN
    devuelo N y si el string es X devuelvo X */
    public char partirJugada(String visual) {
        char aux = 'Z';
        if (visual.length() > 3) {
            String[] partes = visual.split(" ");
            if (partes[1].equals("VERN")) {
                aux = 'N';
            }
            if (partes[1].equals("VERR")) {
                aux = 'R';
            }
        }
        if (visual.equals("X")) {
            aux = 'X';
        }
        return aux;
    }

    //Le paso VERR o VERN y veo el tablero de esa manera
    public void verTablero(Tablero tablero, String visual) {
        if (visual.equals("VERN")) {
            vern(tablero.getTablero());
        }
        if (visual.equals("VERR")) {
            verr(tablero.getTablero());
        }

    }

    /* Si el String es VERR o VERN entonces le pido que me pase otro string 
    que va a hacer la jugada que quiere hacer. Despues devuelvo la jugada
    en los dos primeros caracteres y separado por un espacio devuelvo 
    el modo de ver el tablero*/
    public String visual(String aux) {
        String visual = "";
        while (aux.equals("VERR") || aux.equals("VERN")) {
            visual = aux;
            aux = validaciones.validarMovi();
        }
        if (aux.equals("X")) {
            return aux;
        } else {
            return aux + " " + visual;
        }
    }

    //Igual que para el turno rojo
    public ArrayList<Jugada> turnoAzul(Jugador jugador, 
            Tablero tablero, ArrayList<Pieza> piezaMover) {
        ArrayList<Jugada> dosJugadas = new ArrayList<>();
        Jugada jugada = new Jugada();
        jugada.setJugador(jugador);
        System.out.println("Turno del jugador Azul - " + jugador.getAlias());
        System.out.println("Ingrese movimiento");
        String jugadaAux = visual(validaciones.validarMovi());
        if (!jugadaAux.equals("X")) {
            if (piezaMover.size() == 8) {
                while (!tablero.validacionJugada(tablero.buscarPiezaAzul(
                        jugadaAux, tablero.getTablero()), jugadaAux.charAt(1), 
                        tablero.getTablero())) {
                    System.out.println("La jugada ingresada no es valida. "
                            + "Reingrese");
                    jugadaAux = visual(validaciones.validarMovi());
                }
            } else {
                while (!tablero.validacionJugada(tablero.buscarPiezaAzul(
                        jugadaAux, tablero.getTablero()), jugadaAux.charAt(1), 
                        tablero.getTablero()) || 
                        !sistema.validarJugadasPosibles(piezaMover, 
                                jugadaAux.charAt(0))) {
                    System.out.println("La jugada ingresada no es valida. "
                            + "Reingrese");
                    jugadaAux = visual(validaciones.validarMovi());
                }
            }
            jugada.setPieza(tablero.buscarPiezaAzul(jugadaAux, 
                    tablero.getTablero()));
            jugada.setDireccion(jugadaAux.charAt(1));
            tablero.moverAzul(jugada.getPieza(), jugada.getDireccion(),
                    tablero.getTablero());
        }
        dosJugadas.add(jugada);
        Jugada jugadaVisual = new Jugada();
        jugadaVisual.setDireccion(partirJugada(jugadaAux));
        dosJugadas.add(jugadaVisual);
        return dosJugadas;
    }

    //Imprime la lista de jugadores registrados
    public void imprimirJugadores(ArrayList<Jugador> jugadores) {
        System.out.println("Seleccione 2 jugadores");
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugador = jugadores.get(i);
            System.out.println(i + " - " + jugador.getAlias());

        }
    }
    
    //Imprime el ranking oredenado con el jugador con mas victorias arriba 
    public void imprimirRanking(ArrayList<Jugador> jugadores) {
        System.out.println("Ranking de Jugadores");
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugador = jugadores.get(i);
            System.out.println(i + 1 + " - " + jugador.getAlias() + 
                    " Victorias = " + jugador.getCantidadGanadas());

        }
    }

    //Imprime las partidas que se han jugado
    public void imprimirPartidas(ArrayList<Partida> partidas) {
        System.out.println("Seleccione una Partida");
        for (int i = 0; i < partidas.size(); i++) {
            Partida partida = partidas.get(i);
            System.out.println(i + " - " + partida);
        }
    }

    //Imprime el tablero de la forma reducida
    public void verr(Pieza[][] tablero) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 9; j++) {

                System.out.print(tablero[i][j] + " ");

                if (j == 8) {
                    System.out.println();
                }
            }
        }
    }

    //Imprime el tablero en la forma normal
    public void vern(Pieza[][] tablero) {
        for (int i = 0; i < 8; i++) {
            System.out.println("+-+-+-+-+-+-+-+-+-+");
            for (int j = 0; j < 9; j++) {
                if (tablero[i][j].getValor() == 0) {
                    System.out.print("| ");
                } else {
                    System.out.print("|" + tablero[i][j]);
                }
                if (j == 8) {
                    System.out.print("|");
                    System.out.println();
                }
            }
        }
        System.out.println("+-+-+-+-+-+-+-+-+-+");
    }

    public void opcionesMenu() {
        System.out.println("Menu");
        System.out.println("a - Registro de Jugador");
        System.out.println("b - Jugar Partida");
        System.out.println("c - Replicar Partida");
        System.out.println("d - Ranking");
        System.out.println("e - Fin");
    }

    public void opcionesTer() {
        System.out.println("Ingrese forma de termiancion:");
        System.out.println("1 - Cantidad de movimientos");
        System.out.println("2 - Una pieza al lado opuesto");
        System.out.println("3 - Todas las piezas al lado opuesto");
    }

    //Si el tipo de partida es 1 entonces le pido que ingrese movimientos
    // totales a realizar
    public int cantidadMov(int tipoPartida) {
        int i = 0;
        if (tipoPartida == 1) {
            System.out.println("Ingrese la cantidad de movimientos totales");
            i = validaciones.validarNumeroPositivo();
        }
        return i;
    }
}
