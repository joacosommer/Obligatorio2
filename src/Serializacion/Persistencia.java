package Serializacion;
import Dominio.Sistema;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Persistencia {
     public boolean guardarSistema(Sistema sistema) {
        boolean ok = true;
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("salida"));
            out.writeObject(sistema);
            out.close();
        } catch (Exception err) {
            ok = false;
        }
        return ok;
    }

    public Sistema cargarSistema() {
        Sistema sistema = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("salida"));
            sistema = (Sistema) in.readObject();
        } catch (Exception err) {
            sistema = new Sistema();
        }
        return sistema;
    }
}

    
    

