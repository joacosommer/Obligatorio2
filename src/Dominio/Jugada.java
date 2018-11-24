package Dominio;

/*
Joaquin Sommer - 184441
Marcelo Ferreira - 175240
 */
public class Jugada {

    private Pieza pieza = new Pieza();
    private Jugador jugador = new Jugador();
    private char direccion;

    public Pieza getPieza() {
        return pieza;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public char getDireccion() {
        return direccion;
    }

    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setDireccion(char direccion) {
        this.direccion = direccion;
    }

    public Jugada(Pieza unaPieza, Jugador unJugador, char unaDirec) {
        this.setPieza(unaPieza);
        this.setJugador(unJugador);
        this.setDireccion(unaDirec);
    }

    public Jugada() {
        this.setPieza(pieza);
        this.setJugador(jugador);
        this.setDireccion('A');
    }
}
