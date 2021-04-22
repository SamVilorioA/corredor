/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegohombrecorriendo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author josearielpereyra
 */
public abstract class ElementoDinamico {

    Rectangle limites;
    Image imagen;
    int velocidad = 1;
    Color color = Color.RED;
    PanelDeJuego panelDeJuego;
    String direccion = "izquierda";

    public ElementoDinamico(Rectangle limites, Image imagen, PanelDeJuego panelDeJuego) {
        this.limites = limites;
        this.imagen = imagen;
        this.panelDeJuego = panelDeJuego;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public ElementoDinamico(PanelDeJuego panelDeJuego) {
        this(new Rectangle(100, 100), null, panelDeJuego);
    }

    public void actualizar() {
        limites.x += direccion.equals("izquierda") ? -velocidad : velocidad;
    }
    
    public void establecerDireccion(String direccion){
        this.direccion = direccion;
    }
    public abstract void dibujar(Graphics g);
}
