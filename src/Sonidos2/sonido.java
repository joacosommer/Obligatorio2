/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sonidos2;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;
import javax.swing.JOptionPane;
import sun.audio.*;


/**
 *
 * @author Joaquin
 */
public class sonido {
    public void sonar() {
 
     File file = new File("prueba.wav");
     try {
 
         InputStream in = new FileInputStream(file);
         AudioStream as = new AudioStream(in);
         AudioPlayer.player.start(as);
     }
     catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
 }

}
