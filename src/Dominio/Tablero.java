package Dominio;

import java.util.ArrayList;

/*
Joaquin Sommer - 184441
Marcelo Ferreira - 175240
 */
public class Tablero {

    private Pieza[][] tablero = new Pieza[8][9];

    public Pieza[][] getTablero() {
        return tablero;
    }

    public void setTablero(Pieza[][] tablero) {
        this.tablero = tablero;
    }

    //Genero el tablero base
    public Tablero() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 9; j++) {
                Pieza aux = new Pieza();
                if (i == 0 && j > 0) {
                    aux.setColor("Azul");
                    aux.setValor(j);
                }
                if (i == 7 && j < 9) {
                    aux.setColor("Rojo");
                    aux.setValor(8 - j);
                }
                this.tablero[i][j] = aux;
            }
        }
    }

    //Valido que el moviemiento de la pieza que se quiere hacer sea posible
    //Que no se salga de la matriz ni que haya otra pieza en el lugar
    public boolean validacionJugada(Pieza pieza, char direc,
            Pieza[][] tablero) {
        boolean ok = false;
        int fila = posPieza(tablero, pieza)[0];
        int colu = posPieza(tablero, pieza)[1];
        if (pieza.getColor().equals("Azul")) {
            if (direc == 'A') {
                if (fila + 1 < 8) {
                    if (tablero[fila + 1][colu].getValor() == 0) {
                        ok = true;
                    }
                }
            }
            if (direc == 'I') {
                if (fila + 1 < 8 && colu - 1 >= 0) {
                    if (tablero[fila + 1][colu - 1].getValor() == 0) {
                        ok = true;
                    }
                }
            }
            if (direc == 'D') {
                if (fila + 1 < 8 && colu + 1 < 9) {
                    if (tablero[fila + 1][colu + 1].getValor() == 0) {
                        ok = true;
                    }
                }
            }
        }
        if (pieza.getColor().equals("Rojo")) {
            if (direc == 'A') {
                if (fila - 1 >= 0) {
                    if (tablero[fila - 1][colu].getValor() == 0) {
                        ok = true;
                    }
                }
            }
            if (direc == 'I') {
                if (fila - 1 >= 0 && colu - 1 >= 0) {
                    if (tablero[fila - 1][colu - 1].getValor() == 0) {
                        ok = true;
                    }
                }
            }
            if (direc == 'D') {
                if (fila - 1 >= 0 && colu + 1 < 9) {
                    if (tablero[fila - 1][colu + 1].getValor() == 0) {
                        ok = true;
                    }
                }
            }
        }
        return ok;
    }

    //Muevo la pieza a la direccion que me pasaron y devuelvo el tablero 
    public Pieza[][] moverAzul(Pieza pieza, char direc, Pieza[][] tablero) {
        int fila = posPieza(tablero, pieza)[0];
        int colu = posPieza(tablero, pieza)[1];
        Pieza aux = new Pieza();
        if (direc == 'A') {
            tablero[fila][colu] = aux;
            tablero[fila + 1][colu] = pieza;
        }
        if (direc == 'I') {
            tablero[fila][colu] = aux;
            tablero[fila + 1][colu - 1] = pieza;
        }
        if (direc == 'D') {
            tablero[fila][colu] = aux;
            tablero[fila + 1][colu + 1] = pieza;
        }
        return tablero;
    }

    //Muevo la pieza a la direccion que me pasaron y devuelvo el tablero 
    public Pieza[][] moverRojo(Pieza pieza, char direc, Pieza[][] tablero) {
        int fila = posPieza(tablero, pieza)[0];
        int colu = posPieza(tablero, pieza)[1];
        Pieza aux = new Pieza();
        if (direc == 'A') {
            tablero[fila][colu] = aux;
            tablero[fila - 1][colu] = pieza;
        }
        if (direc == 'I') {
            tablero[fila][colu] = aux;
            tablero[fila - 1][colu - 1] = pieza;
        }
        if (direc == 'D') {
            tablero[fila][colu] = aux;
            tablero[fila - 1][colu + 1] = pieza;
        }
        return tablero;
    }

    //Devuelvo la posicion de la pieza en un array. 
    //La fila primero y la columna despues
    public int[] posPieza(Pieza[][] tablero, Pieza pieza) {
        int[] lista = new int[2];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 9; j++) {
                if (tablero[i][j].equals(pieza)) {
                    lista[0] = i;
                    lista[1] = j;
                }
            }
        }
        return lista;
    }

    //Busco la pieza roja en string que me pasaron y devuelvo 
    //la pieza del tablero
    public Pieza buscarPiezaRoja(String pieza, Pieza[][] tablero) {
        Pieza aux = new Pieza();
        int lugar = Character.getNumericValue(pieza.charAt(0));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 9; j++) {
                if (tablero[i][j].getValor() == lugar
                        && tablero[i][j].getColor().equals("Rojo")) {
                    aux = tablero[i][j];
                }
            }
        }
        return aux;
    }

    //Busco la pieza azul en string que me pasaron y devuelvo 
    //la pieza del tablero
    public Pieza buscarPiezaAzul(String pieza, Pieza[][] tablero) {
        Pieza aux = new Pieza();
        int lugar = Character.getNumericValue(pieza.charAt(0));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 9; j++) {
                if (tablero[i][j].getValor() == lugar
                        && tablero[i][j].getColor().equals("Azul")) {
                    aux = tablero[i][j];

                }
            }
        }
        return aux;
    }
}
