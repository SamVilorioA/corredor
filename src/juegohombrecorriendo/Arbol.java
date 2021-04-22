/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegohombrecorriendo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author josearielpereyra
 */
public class Arbol{
    Rectangle contenedor;
    String rutaImagen = "/imagenes/arbolEscalado.png";

    public Arbol(Rectangle contenedor) {
      this.contenedor = contenedor;
    }
    
    public void dibujar( Graphics g) {
      Image imagen = new javax.swing.ImageIcon(getClass().getResource( rutaImagen )).getImage();
      g.drawImage(imagen, contenedor.x, contenedor.y, contenedor.width, contenedor.height, null);
    }

    private int getPosicionY() {
      return contenedor.y;
    }

    private int getAltura() {
      return contenedor.height;
    }
  }