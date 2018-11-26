package Dominio;

import java.util.ArrayList;
import java.util.Date;

/*
Joaquin Sommer - 184441
Marcelo Ferreira - 175240
 */
public class Partida implements Comparable<Partida>{

    private Jugador jugadorAzul = new Jugador();
    private Jugador jugadorRojo = new Jugador();
    private int tipoPartida;
    private ArrayList<Jugada> listaJugadas;
    private Date fecha;
    private int movi; 

    public int getMovi() {
        return movi;
    }

    public void setMovi(int movi) {
        this.movi = movi;
    }
    
    public Jugador getJugadorAzul() {
        return jugadorAzul;
    }

    public void setJugadorAzul(Jugador unJugador) {
        jugadorAzul = unJugador;
    }

    public Jugador getJugadorRojo() {
        return jugadorRojo;
    }

    public void setJugadorRojo(Jugador unJugador) {
        jugadorRojo = unJugador;
    }

    public int getTipoPartida() {
        return tipoPartida;
    }

    public void setTipoPartida(int unTipoPartida) {
        tipoPartida = unTipoPartida;
    }

    public ArrayList<Jugada> getListaJugadas() {
        return listaJugadas;
    }

    public void setListaJugadas(Jugada unaJugada) {
        this.getListaJugadas().add(unaJugada);
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha() {
        this.fecha = new Date();
    }

    public Partida(Jugador jugadorAzul, Jugador jugadorRojo,
            int tipoPartida, ArrayList<Jugada> listaJugadas, int movi) {
        this.jugadorAzul = jugadorAzul;
        this.jugadorRojo = jugadorRojo;
        this.tipoPartida = tipoPartida;
        this.listaJugadas = listaJugadas;
        this.fecha = new Date();
        this.setMovi(movi);
    }

    public Partida() {
        this.setJugadorAzul(jugadorAzul);
        this.setJugadorRojo(jugadorRojo);
        this.setTipoPartida(0);
        listaJugadas = new ArrayList<Jugada>();
        this.setFecha();
        this.setMovi(0);

    }

    @Override
    public int compareTo(Partida p) {
        return getFecha().compareTo(p.getFecha());
    }

    @Override
    public String toString() {
        return jugadorAzul.getAlias() + " vs " + jugadorRojo.getAlias() 
                + " Fecha = " + fecha;
    }
}
