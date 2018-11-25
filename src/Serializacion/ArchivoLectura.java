package Serializacion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Marce
 */
public class ArchivoLectura {
    private String linea;
    private BufferedReader in;

    public ArchivoLectura(String unNombre) {
        try {
            in = new BufferedReader(new FileReader(unNombre));
        } catch (FileNotFoundException e) {

        }
    }

    public boolean hayMasLineas() {
        try {
            linea = in.readLine();
        } catch (IOException e) {
            linea = null;
        }

        return (linea != null);
    }
        
    public String linea(){
        return linea;
    }
        
    public boolean cerrar(){
    boolean ok = true;
      try {
            in.close();
        } catch (Exception e) {
            ok = false;
        }
      return ok;
    }
    

}


