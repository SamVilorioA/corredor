/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegohombrecorriendo;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author josearielpereyra
 */
public class Carretera {
    protected int altura = 100;
    PanelDeJuego panelDeJuego;
    int xLinea = 0;
    Color colorInicial = new Color( 120, 120, 120 ) ;
    Color colorFinal = new Color( 100, 100, 100 );

    public Carretera(PanelDeJuego panelDeJuego) {
      this.panelDeJuego = panelDeJuego;
    }
    
    public void dibujar( Graphics g) {
      int yCalle = (int)(panelDeJuego.getHeight() - altura);
      
      boolean esDeNoche = panelDeJuego.esDeNoche();
      GradientPaint gp = new GradientPaint( 0, yCalle, esDeNoche ? colorFinal : colorInicial, 0, altura, esDeNoche ? colorInicial : colorFinal );
      Graphics2D g2d = (Graphics2D) g;
      g2d.setPaint(gp);
      g2d.fillRect(0, yCalle, panelDeJuego.getWidth(), altura);
        
      g.setColor(Color.white);
      int longitudLinea = 60;
      for (int xCalle = xLinea ; xCalle < panelDeJuego.getWidth() + longitudLinea * 2;  xCalle += longitudLinea * 2) {
          g.fillRect(xCalle, yCalle + altura/ 3, longitudLinea, 10);
          xLinea -= 2;
          
          if( xLinea <= -longitudLinea * 2) {
            xLinea = 0;
          }
      }
      
    }

    public int getAltura() {
      return altura;
    }
  }
