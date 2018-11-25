package Dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Collections;
import java.util.*;


/*
Joaquin Sommer - 184441
Marcelo Ferreira - 175240
 */
public class Sistema extends Observable {

    private ArrayList<Jugador> listaJugadores;
    private ArrayList<Partida> listaPartidas;

    public void jugad() {
        Jugador j = new Jugador("Joaquin", "JSJS", 23, 1);
        Jugador m = new Jugador("Marcelo", "Marce", 26, 3);
        agregarJugador(j);
        agregarJugador(m);
    }

    public Sistema() {
        this.listaJugadores = new ArrayList();
        this.listaPartidas = new ArrayList();
    }

    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(ArrayList<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
        this.setChanged();
        this.notifyObservers();
    }

    public ArrayList<Partida> getListaPartidas() {
        return listaPartidas;
    }

    public void setListaPartidas(ArrayList<Partida> listaPartidas) {
        this.listaPartidas = listaPartidas;
        this.setChanged();
        this.notifyObservers();
    }

    public void agregarJugador(Jugador jugador) {
        this.getListaJugadores().add(jugador);
        this.setChanged();
        this.notifyObservers();
    }

    public void agregarPartida(Partida partida) {
        this.getListaPartidas().add(partida);
        this.setChanged();
        this.notifyObservers();
    }

    //Devuelvo true si se llego al maximo de jugadas(movi)
    public boolean maximoJugadas(Partida partida, int movi) {
        boolean ok = false;
        if (partida.getListaJugadas().size() == movi) {
            ok = true;
        }
        return ok;
    }

    //Devuelvo true si hay una pieza en el lado opuesto
    public boolean piezaOpuesto(Pieza[][] tablero) {
        boolean ok = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 9; j++) {
                if (tablero[0][j].getColor().equals("Rojo")) {
                    ok = true;
                }
                if (tablero[7][j].getColor().equals("Azul")) {
                    ok = true;
                }
            }
        }
        return ok;
    }

    //Sumo el valor de las piezas que estan en el lado opuesto. 
    //El primer lugar es la suma del Rojo y el segundo del Azul
    public int[] sumaLados(Pieza[][] tablero) {
        int[] suma = {0, 0};
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 9; j++) {
                if (tablero[0][j].getColor().equals("Rojo")) {
                    suma[0] = tablero[0][j].getValor() + suma[0];
                }
                if (tablero[7][j].getColor().equals("Azul")) {
                    suma[1] = tablero[7][j].getValor() + suma[1];
                }
            }
        }
        return suma;
    }

    //Sumo el valor de las piezas que estan en la otra mitad del tablero. 
    //El primer lugar es la suma del Rojo y el segundo del Azul
    public int[] sumaMitades(Pieza[][] tablero) {
        int[] suma = {0, 0};
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 9; j++) {
                if (i < 4) {
                    if (tablero[i][j].getColor().equals("Rojo")) {
                        suma[0] = tablero[i][j].getValor() + suma[0];
                    }
                }
                if (i > 3) {
                    if (tablero[i][j].getColor().equals("Azul")) {
                        suma[1] = tablero[i][j].getValor() + suma[1];
                    }
                }
            }
        }
        return suma;
    }

    //Devuelvo true si todas las piezas de algun jugador estan del 
    //lado lado opuesto
    public boolean todasOpuesto(Pieza[][] tablero) {
        boolean ok = false;
        int rojo = 0;
        int azul = 0;
        for (int j = 0; j < 9; j++) {
            if (tablero[0][j].getColor().equals("Rojo")) {
                rojo++;
            }
            if (tablero[7][j].getColor().equals("Azul")) {
                azul++;
            }
        }
        if (rojo == 8 || azul == 8) {
            ok = true;
        }
        return ok;
    }

    //Me pasan el tipo de partida y devuelvo true si se termino
    public boolean terminaPartida(Partida partida, int movi,
            Pieza[][] tablero) {
        boolean ok = false;
        if (partida.getTipoPartida() == 1) {
            ok = maximoJugadas(partida, movi);
        }
        if (partida.getTipoPartida() == 2) {
            ok = piezaOpuesto(tablero);
        }
        if (partida.getTipoPartida() == 3) {
            ok = todasOpuesto(tablero);
        }
        return ok;
    }

    //Me devuelve un arraylist con los movimientos posibles despues de 
    //mover una pieza. Puede que haya repetidos
    public ArrayList<Pieza> movimientosPosibles(Tablero tablero, Pieza pieza) {
        ArrayList<Pieza> piezasMovibles = new ArrayList<>();
        int[] pos = tablero.posPieza(tablero.getTablero(), pieza);
        int sumaDiag1 = 0;
        int sumaDiag2 = 0;
        int vertical = 0;
        int horiz = 0;
        for (int i = 1; i < 9; i++) {
            if ((pos[0] - i >= 0) && (pos[1] - i >= 0)) {
                sumaDiag1 = tablero.getTablero()[pos[0] - i][pos[1] - i]
                        .getValor() + sumaDiag1;
            }
            if ((pos[0] + i < 8) && (pos[1] + i < 9)) {
                sumaDiag1 = tablero.getTablero()[pos[0] + i][pos[1] + i]
                        .getValor() + sumaDiag1;

            }
            if ((pos[0] - i >= 0) && (pos[1] + i < 9)) {
                sumaDiag2 = tablero.getTablero()[pos[0] - i][pos[1] + i]
                        .getValor() + sumaDiag2;
            }
            if ((pos[0] + i < 8) && (pos[1] - i >= 0)) {
                sumaDiag2 = tablero.getTablero()[pos[0] + i][pos[1] - i]
                        .getValor() + sumaDiag2;
            }
            if (pos[0] - i >= 0) {
                vertical = tablero.getTablero()[pos[0] - i][pos[1]]
                        .getValor() + vertical;
            }
            if (pos[0] + i < 8) {
                vertical = tablero.getTablero()[pos[0] + i][pos[1]]
                        .getValor() + vertical;

            }
            if (pos[1] + i < 9) {
                horiz = tablero.getTablero()[pos[0]][pos[1] + i]
                        .getValor() + horiz;
            }
            if (pos[1] - i >= 0) {
                horiz = tablero.getTablero()[pos[0]][pos[1] - i]
                        .getValor() + horiz;
            }
        }
        if (sumaDiag1 + pieza.getValor() < 9 && sumaDiag1 != 0) {

            piezasMovibles.add(new Pieza(pieza.getColor(), sumaDiag1
                    + pieza.getValor()));
        }
        if (sumaDiag2 + pieza.getValor() < 9 && sumaDiag2 != 0) {
            piezasMovibles.add(new Pieza(pieza.getColor(), sumaDiag2
                    + pieza.getValor()));
        }
        if (vertical + pieza.getValor() < 9 && vertical != 0) {
            piezasMovibles.add(new Pieza(pieza.getColor(), vertical
                    + pieza.getValor()));
        }
        if (horiz + pieza.getValor() < 9 && horiz != 0) {
            piezasMovibles.add(new Pieza(pieza.getColor(), horiz
                    + pieza.getValor()));
        }
        return piezasMovibles;
    }

    //Devuelvo las piezas que si o si puedo mover despues de validar 
    //cada movimiento posible
    //No tiene repetidos y esta ordenado por valor de la pieza
    public ArrayList<Pieza> piezasMovibles(Tablero tablero, Pieza pieza) {
        ArrayList<Pieza> piezaMover = movimientosPosibles(tablero, pieza);
        ArrayList<Pieza> aux = new ArrayList<>();
        Character[] direc = {'A', 'D', 'I'};
        if (piezaMover.size() == 8) {
            for (int i = 0; i < piezaMover.size(); i++) {
                for (int j = 0; j < 3; j++) {
                    if (tablero.validacionJugada(piezaMover.get(i), direc[j],
                            tablero.getTablero())) {
                        aux.add(piezaMover.get(i));
                    }
                }
            }
        } else if (!piezaMover.isEmpty()) {
            for (int i = 0; i < piezaMover.size(); i++) {
                for (int j = 0; j < 3; j++) {
                    if (tablero.validacionJugada(piezaMover.get(i), direc[j],
                            tablero.getTablero())) {
                        aux.add(piezaMover.get(i));
                    }
                }
            }
        }
        for (int i = 0; i < aux.size(); i++) {
            for (int j = i + 1; j < aux.size(); j++) {
                if (aux.get(i).equals(aux.get(j))) {
                    aux.remove(j);
                    j--;
                }
            }
        }
        Collections.sort(aux);
        return aux;
    }

    //Devuelvo las posibles jugadas que puede hacer el jugador Azul
    //No tiene mucho uso pero capaz despues la puedo utilizar mas
    public ArrayList<Jugada> posiblesJugadasAzul(Tablero tablero,
            ArrayList<Pieza> piezaMover, Jugador jugadorAzul) {
        ArrayList<Jugada> posiblesJugadas = new ArrayList<>();
        Character[] direc = {'A', 'D', 'I'};
        if (piezaMover.size() == 8) {

            for (int i = 0; i < piezaMover.size(); i++) {
                for (int j = 0; j < 3; j++) {
                    if (tablero.validacionJugada(piezaMover.get(i),
                            direc[j], tablero.getTablero())) {
                        Jugada aux = new Jugada(piezaMover.get(i),
                                jugadorAzul, direc[j]);
                        posiblesJugadas.add(aux);

                    }
                }
            }
        } else if (!piezaMover.isEmpty()) {
            for (int i = 0; i < piezaMover.size(); i++) {
                for (int j = 0; j < 3; j++) {
                    if (tablero.validacionJugada(piezaMover.get(i), direc[j],
                            tablero.getTablero())) {
                        Jugada aux = new Jugada(piezaMover.get(i), jugadorAzul,
                                direc[j]);
                        posiblesJugadas.add(aux);
                    }
                }
            }
        }
        return posiblesJugadas;
    }

    //Devuelvo las posibles jugadas que puede hacer el jugador Rojo
    //No tiene mucho uso pero capaz despues la puedo utilizar mas
    public ArrayList<Jugada> posiblesJugadasRojo(Tablero tablero,
            ArrayList<Pieza> piezaMover, Jugador jugadorRojo) {
        ArrayList<Jugada> posiblesJugadas = new ArrayList<>();
        Character[] direc = {'A', 'D', 'I'};
        if (piezaMover.size() == 8) {
            for (int i = 0; i < piezaMover.size(); i++) {
                for (int j = 0; j < 3; j++) {
                    if (tablero.validacionJugada(piezaMover.get(i),
                            direc[j], tablero.getTablero())) {
                        Jugada aux = new Jugada(piezaMover.get(i),
                                jugadorRojo, direc[j]);
                        posiblesJugadas.add(aux);

                    }
                }
            }
        } else if (!piezaMover.isEmpty()) {
            for (int i = 0; i < piezaMover.size(); i++) {
                for (int j = 0; j < 3; j++) {
                    if (tablero.validacionJugada(piezaMover.get(i),
                            direc[j], tablero.getTablero())) {
                        Jugada aux = new Jugada(piezaMover.get(i),
                                jugadorRojo, direc[j]);
                        posiblesJugadas.add(aux);
                    }
                }
            }
        }
        return posiblesJugadas;
    }

    //Valido si la pieza que desea mover es efectivamente una de las 
    //piezas que se pueden mover
    public boolean validarJugadasPosibles(ArrayList<Pieza> piezaMover,
            char num) {
        boolean ok = false;
        int aux = Character.getNumericValue(num);
        for (int i = 0; i < piezaMover.size(); i++) {
            if (piezaMover.get(i).getValor() == aux) {
                ok = true;
            }
        }
        return ok;
    }

    //Crea una lista con las 8 piezas Azules
    public ArrayList<Pieza> piezasAzul() {
        ArrayList<Pieza> aux = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            aux.add(new Pieza("Azul", i));
        }
        return aux;
    }

    //Crea una lista con las 8 piezas Rojas
    public ArrayList<Pieza> piezasRojo() {
        ArrayList<Pieza> aux = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            aux.add(new Pieza("Rojo", i));
        }
        return aux;
    }
    
    public Jugador devolverJugador(Object alias){
        Jugador j = new Jugador();
        for (int i=0; i<this.getListaJugadores().size();i++){
            if (alias.equals(this.getListaJugadores().get(i).getAlias())){
                j = this.getListaJugadores().get(i);
            }
        }
        return j;
    }
}
