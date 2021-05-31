/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegohombrecorriendo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author josearielpereyra
 */
public class Estrella extends ElementoDinamico{
  public Estrella(Rectangle limites, Image imagen, PanelDeJuego panelDeJuego){
        super(limites, imagen, panelDeJuego);
        this.velocidad = 20;
     
    }
    public void dibujar(Graphics g){
        g.drawImage(imagen, limites.x, limites.y, limites.width, limites.height, panelDeJuego);
    }
}
