package InterfazUI;

import Dominio.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
import Serializacion.*;
import com.google.gson.*;

/**
 *
 * @author Marce
 */
public class VentanaPrincipal extends javax.swing.JFrame implements Observer {

    private Sistema sistema;

    public VentanaPrincipal(Sistema unSistema) {
        //sistema.jugad();

        initComponents();
        
        setSistema(unSistema);
        Gson gson = new Gson();
        sistema.addObserver(this);

        actualizarVentana();
        this.setEnabled(true);
        
    }

    public Sistema getSistema() {
        return sistema;
    }

    public void setSistema(Sistema unSistema) {
        this.sistema = unSistema;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuJugador = new javax.swing.JMenu();
        jMenuItemRegistroJugador = new javax.swing.JMenuItem();
        jMenuItemRanking = new javax.swing.JMenuItem();
        jMenuPartida = new javax.swing.JMenu();
        jMenuItemJugarPartida = new javax.swing.JMenuItem();
        jMenuItemReplicarPartida = new javax.swing.JMenuItem();
        jMenuSalir = new javax.swing.JMenu();
        salirJuego = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jMenuJugador.setText("Jugador");

        jMenuItemRegistroJugador.setText("Registro Jugador");
        jMenuItemRegistroJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRegistroJugadorActionPerformed(evt);
            }
        });
        jMenuJugador.add(jMenuItemRegistroJugador);

        jMenuItemRanking.setText("Ranking");
        jMenuItemRanking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRankingActionPerformed(evt);
            }
        });
        jMenuJugador.add(jMenuItemRanking);

        jMenuBar1.add(jMenuJugador);

        jMenuPartida.setText("Partida");

        jMenuItemJugarPartida.setText("Jugar Partida");
        jMenuItemJugarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemJugarPartidaActionPerformed(evt);
            }
        });
        jMenuPartida.add(jMenuItemJugarPartida);

        jMenuItemReplicarPartida.setText("Replicar Partida");
        jMenuPartida.add(jMenuItemReplicarPartida);

        jMenuBar1.add(jMenuPartida);

        jMenuSalir.setText("Salir");

        salirJuego.setText("Salir del Juego");
        salirJuego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirJuegoActionPerformed(evt);
            }
        });
        jMenuSalir.add(salirJuego);

        jMenuItem1.setText("Mostrar Json");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuSalir.add(jMenuItem1);

        jMenuBar1.add(jMenuSalir);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(160, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemRegistroJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRegistroJugadorActionPerformed
        VentanaRegistroJugador ventana = new VentanaRegistroJugador(this.getSistema());
        ventana.setVisible(true);
        
    }//GEN-LAST:event_jMenuItemRegistroJugadorActionPerformed

    private void jMenuItemJugarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemJugarPartidaActionPerformed
        VentanaJugarPartida partida = new VentanaJugarPartida(this.getSistema());
        partida.setVisible(true);
        
    }//GEN-LAST:event_jMenuItemJugarPartidaActionPerformed

    private void jMenuItemRankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRankingActionPerformed
        VentanaRanking ranking = new VentanaRanking(this.getSistema());
        ranking.setVisible(true);
        
    }//GEN-LAST:event_jMenuItemRankingActionPerformed

    private void salirJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirJuegoActionPerformed
        guardar();
        System.exit(0);
    }//GEN-LAST:event_salirJuegoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    public void guardar() {
        Persistencia per = new Persistencia();
        per.guardarSistema(sistema);
    }
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemJugarPartida;
    private javax.swing.JMenuItem jMenuItemRanking;
    private javax.swing.JMenuItem jMenuItemRegistroJugador;
    private javax.swing.JMenuItem jMenuItemReplicarPartida;
    private javax.swing.JMenu jMenuJugador;
    private javax.swing.JMenu jMenuPartida;
    private javax.swing.JMenu jMenuSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem salirJuego;
    // End of variables declaration//GEN-END:variables

    
    
    @Override
        public void update(Observable o, Object o1) {
        actualizarVentana();
    }

    public void actualizarVentana() {
        
        if (this.getSistema().getListaJugadores().size() > 1) {
            jMenuPartida.setEnabled(true);
        } else {
            jMenuPartida.setEnabled(false);
        }
        if (this.getSistema().getListaPartidas().size() > 0) {
            jMenuItemReplicarPartida.setEnabled(true);
        } else {
            jMenuItemReplicarPartida.setEnabled(false);
        }
        if (this.getSistema().getListaJugadores().size() > 0) {
            jMenuItemRanking.setEnabled(true);
        } else {
            jMenuItemRanking.setEnabled(false);
        }
        this.setEnabled(true);
    }

}
