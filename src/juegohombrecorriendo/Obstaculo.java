/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegohombrecorriendo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author josearielpereyra
 */
public class Obstaculo extends ElementoDinamico{
  Carretera carretera;
  Random numerosAleatorios = new Random();
  static int posicionX = 500;
  
  public Obstaculo(PanelDeJuego panelDeJuego, Carretera carretera) {
    super( panelDeJuego );
    this.panelDeJuego = panelDeJuego;
    this.limites = new Rectangle(50, 80);
    limites.x = posicionX;
    posicionX += 500;
    this.carretera = carretera;
  }

  public void dibujar(Graphics g) {
    limites.x = (limites.x < -limites.width ) ? panelDeJuego.getWidth() : limites.x - 20;
    limites.y = panelDeJuego.getHeight() - carretera.altura - limites.height;

    color = Color.magenta;
    Rectangle rectaguloInicial = new Rectangle(limites);
    g.setColor(color.darker());
    g.fillRect(rectaguloInicial.x, rectaguloInicial.y, rectaguloInicial.width, rectaguloInicial.height);
    
    
    limites.y = panelDeJuego.getHeight() - (int)(limites.height );
    Rectangle rectanguloFinal = new Rectangle(limites);
    rectanguloFinal.x -= 20;
    g.fillRect(rectanguloFinal.x, rectanguloFinal.y, rectanguloFinal.width, rectanguloFinal.height);
    
    
    int[] puntosEnX = {
      rectaguloInicial.x,
      rectanguloFinal.x,
      rectanguloFinal.x + limites.width,
      rectaguloInicial.x + limites.width
    };
    int[] puntosEnY = {
      rectaguloInicial.y,
      rectanguloFinal.y,
      rectanguloFinal.y,
      rectaguloInicial.y
    };
    
    g.drawPolygon( puntosEnX, puntosEnY, 4 );
    
    //g.setColor( new Color(color.getRed(), color.getGreen(), color.getBlue(), 128) );
    g.setColor(color);
    g.fillPolygon( puntosEnX, puntosEnY, 4 );
  }
}
