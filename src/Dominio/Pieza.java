package Dominio;

import java.util.Objects;

/*
Joaquin Sommer - 184441
Marcelo Ferreira - 175240
 */
public class Pieza implements Comparable<Pieza> {

    private String color;
    private int valor;

    public String getColor() {
        return color;
    }

    public int getValor() {
        return valor;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Pieza(String color, int valor) {
        this.color = color;
        this.valor = valor;
    }

    public Pieza() {
        this.setColor("");
        this.setValor(0);
    }

    @Override
    public String toString() {
        String aux = "\033[30m-";
        if (this.getValor() == 0) {
            aux = "\033[30m-";
        } else if (this.getColor().equals("Rojo")) {
            aux = "\033[31m" + this.getValor();
        } else if (this.getColor().equals("Azul")) {
            aux = "\033[34m" + this.getValor();
        }
        return aux + "\u001B[0m";
    }

    @Override
    public boolean equals(Object obj) {
        Pieza p = (Pieza) obj;
        return this.getColor().equals(p.getColor())
                && this.getValor() == p.getValor();
    }

    @Override
    public int compareTo(Pieza p) {
        return Integer.compare(this.getValor(), p.getValor());
    }
}
