/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegohombrecorriendo;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author josearielpereyra
 */
public class Jugador extends ElementoDinamico {

    private int numeroImagen = 0;
    int posicionSalto = 0;
    boolean estaCorriendo = true;
    boolean estaSubiendo = false;
    String[] imagenes;
    String[] personajes;
    int personajeActual;

    public Jugador(PanelDeJuego panelDeJuego) {
        super(panelDeJuego);
        try {
            personajes = new File(getClass().getResource("/imagenes/skins/").toURI()).list();
            Arrays.sort(personajes);
            personajeActual = 0;
        } catch (URISyntaxException ex) {
            Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
        }

        actualizarSkin(0);

        panelDeJuego.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (estaCorriendo) {
                        estaCorriendo = false;
                        estaSubiendo = true;

                        try {
                            File salto = new File(getClass().getResource("/sonidos/salto.wav").toURI());
                            Clip clip = AudioSystem.getClip();
                            clip.open(AudioSystem.getAudioInputStream(salto));
                            clip.start();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public void cargarImagen() {
        numeroImagen++;
        if (numeroImagen < 0 || numeroImagen > imagenes.length - 1) {
            numeroImagen = 0;
        }
        panelDeJuego.repaint();
    }

    @Override
    public void dibujar(Graphics g) {
        if (estaSubiendo) {
            posicionSalto += 15;

            if (posicionSalto > 130) {
                posicionSalto = 130;
                estaSubiendo = false;
            }
        } else {
            posicionSalto -= 15;

            if (posicionSalto < 0) {
                posicionSalto = 0;
                estaCorriendo = true;
            }
        }

        int yJugador = 220 + posicionSalto;

        System.out.println("posicionSalto: " + posicionSalto);
        System.out.println("yJugador: " + yJugador);

        numeroImagen = estaCorriendo ? numeroImagen : imagenes.length / 2;
        Image imagen = new javax.swing.ImageIcon(getClass().getResource("/imagenes/skins/" + personajes[personajeActual] + "/" + imagenes[numeroImagen])).getImage();

        Rectangle hombre = new Rectangle(10, panelDeJuego.getHeight() - yJugador, 130, 180);

        this.limites = hombre;

        g.drawImage(imagen, hombre.x, hombre.y, hombre.width, hombre.height, panelDeJuego);
        //g.drawRect(hombre.x, hombre.y, hombre.width, hombre.height);
    }

    protected void actualizarSkin(int personajeActual) {
        try {
            this.personajeActual = personajeActual;
            String personaje = personajes[personajeActual];
            imagenes = new File(getClass().getResource("/imagenes/skins/" + personaje).toURI()).list();
            Arrays.sort(imagenes);
        } catch (URISyntaxException ex) {
            Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
