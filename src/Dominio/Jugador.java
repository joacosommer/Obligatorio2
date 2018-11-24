package Dominio;

import java.util.Objects;

/*
Joaquin Sommer - 184441
Marcelo Ferreira - 175240
 */
public class Jugador implements Comparable<Jugador> {

    private String nombre;
    private String alias;
    private int edad;
    private int cantidadGanadas;

    public int getCantidadGanadas() {
        return cantidadGanadas;
    }

    public void setCantidadGanadas(int unaCantidadGanadas) {
        this.cantidadGanadas = unaCantidadGanadas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String unNombre) {
        nombre = unNombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String unAlias) {
        alias = unAlias;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int unaEdad) {
        edad = unaEdad;
    }

    public Jugador() {
        this.setNombre("A definir");
        this.setAlias("A definir");
        this.setEdad(1);
        this.setCantidadGanadas(0);

    }

    public Jugador(String unNombre, String unAlias, int unaEdad
            , int cantidadGanadas) {
        this.setNombre(unNombre);
        this.setAlias(unAlias);
        this.setEdad(unaEdad);
        this.setCantidadGanadas(cantidadGanadas);
    }

    @Override
    public boolean equals(Object obj) {
        return this.getAlias().equals(((Jugador) obj).getAlias());
    }

    @Override
    public String toString() {
        return "Jugador{" + "Nombre= " + nombre + ", Alias= " + alias + ","
                + " Edad= " + edad + '}';
    }

    @Override
    public int compareTo(Jugador j) {
        return Integer.compare(this.getCantidadGanadas(), j.cantidadGanadas);
    }

    //Suma un 1 a las cantidad de partidas ganadas del jugador
    public void sumarUnaGanada() {
        this.setCantidadGanadas(this.getCantidadGanadas() + 1);
    }
}
