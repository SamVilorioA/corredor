/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegohombrecorriendo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author josearielpereyra
 */
public class Bosque {
    ArrayList<Arbol> arboles;
    int posicionY;
    Color colorSuelo;
    PanelDeJuego panelDeJuego;
    Carretera carretera;
    private final Random numerosAleatorios;

    public Bosque(int posicionY, Color colorSuelo, PanelDeJuego panelDeJuego) {
      this.posicionY = posicionY;
      this.colorSuelo = colorSuelo;
      this.panelDeJuego = panelDeJuego;
      this.carretera = new Carretera( panelDeJuego );
      numerosAleatorios = new Random(100);
      
      arboles = new ArrayList<>();
//
//    int x, y, anchura, altura;
//    for (int i = 0; i < 10; i++) {
//      x = numerosAleatorios.nextInt( panelDeJuego.getWidth() + 200 ) ;
//      anchura = numerosAleatorios.nextInt( 200 ) + 200;
//      altura = numerosAleatorios.nextInt( 200 ) + 200;
//
//      do {
//        y = numerosAleatorios.nextInt( panelDeJuego.getHeight() + 200 ) - 200;
//      }while (y + altura > panelDeJuego.getHeight() - carretera.getAltura() || y + altura < posicionY);
//
//      Arbol arbolActual = new Arbol( new Rectangle(x,y,anchura,altura) );
//      if( arboles.isEmpty() ) {
//        arboles.add( arbolActual );
//      }          
//      else {
//        boolean eraMayor = true;
//
//        for(int j = arboles.size() - 1; j >= 0; j--) {
//          int lineaInferior = arboles.get( j ).getPosicionY() + arboles.get( j ).getAltura(); 
//          arbolActual = new Arbol( new Rectangle(x, y, anchura, altura) );
//
//          if( y + altura > lineaInferior ) {
//            arboles.add( j, arbolActual);
//            eraMayor = false;
//            break;
//          }
//        }
//
//        if( eraMayor ) {
//          arboles.add( arbolActual);
//        }
//      }
//
//    }
    }
    
    public Carretera getCarretera() {
      return carretera;
    }
    
    public void dibujar(Graphics g) {
      int altura = posicionY - (panelDeJuego.getHeight() - carretera.getAltura());
      g.setColor(colorSuelo);
      g.fillRect(0, posicionY, panelDeJuego.getWidth(), altura );
      
      for (Arbol arbol : arboles) {
        arbol.dibujar(g);
      }
      
      carretera.dibujar(g);
    }
  }
