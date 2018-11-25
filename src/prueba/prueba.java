
package prueba;

import Dominio.Sistema;
import InterfazUI.VentanaPrincipal;
import javax.swing.JOptionPane;
import Serializacion.*;
public class prueba {
    
    public static void main(String[] args) {
        Persistencia persistencia = new Persistencia();
        Sistema sistema = persistencia.cargarSistema();
        VentanaPrincipal ventana = new VentanaPrincipal(sistema);
        asignarLookAndFeel();
        ventana.setVisible(true);

    }
    
    public static void asignarLookAndFeel()
    {
         try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al asignar Look And Feel", "Error", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
}
