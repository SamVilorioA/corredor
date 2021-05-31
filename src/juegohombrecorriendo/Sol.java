/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegohombrecorriendo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author vikst
 */

public class Sol extends ElementoDinamico {
    
    public Sol(Rectangle limites, Image imagen, PanelDeJuego panelDeJuego){
        super(limites, imagen, panelDeJuego);
    }
    
      public void dibujar(Graphics g){
          g.drawImage(imagen, limites.x, limites.y , limites.width, limites.height, panelDeJuego);
          limites.x -= velocidad;
      }
}
