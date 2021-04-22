package juegohombrecorriendo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author josearielpereyra
 */
public class PanelDeJuego extends JPanel {

  int posicionNubeX = 0;
  int posicionNubeY = 20;
  int anchuraNube = 300;
  int posicionNube2X = 0;
  int posicionNube2Y = 60;
  int anchuraNube2 = 400;
  Obstaculo[] obstaculos;
  Juego juego;
  Bosque bosque;
  Jugador jugador;
  Sol[] estrellas;
  Nube nube;
  JButton botonReiniciar = new JButton("Iniciar");

  public PanelDeJuego(Juego juego) {
    this.juego = juego;
    this.bosque = new Bosque(0, new Color(120, 80, 80), this);
    this.jugador = new Jugador(this);
    this.setLayout(null);
    botonReiniciar.setSize(200, 50);
    botonReiniciar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        juego.iniciarODetener("Iniciar");
        botonReiniciar.setVisible(false);
      }
    });

    this.add(botonReiniciar);

    Image imagen = new javax.swing.ImageIcon(getClass().getResource("/imagenes/sol3.png")).getImage();
    
    estrellas = new Sol[50];
    Random numerosAleatorios = new Random();
    for(int i = 0; i < estrellas.length; i++) {
      int diametroSol = numerosAleatorios.nextInt( 41 )  + 10;
      int xSol = numerosAleatorios.nextInt( 900 );
      int ySol = numerosAleatorios.nextInt( 100 );

      this.estrellas[i] = new Sol(new Rectangle(xSol, ySol, diametroSol, diametroSol), imagen, this);
    }
    

    Image imagen2 = new javax.swing.ImageIcon(getClass().getResource("/imagenes/nube2.png")).getImage();
    int diametroNube = 370;
    int xNube = this.getWidth() - diametroNube;
    int yNube = 10;

    this.nube = new Nube(new Rectangle(xNube, yNube, diametroNube, diametroNube), imagen2, this);

  }

  public boolean esDeNoche() {
    return juego.esDeNoche();
  }

  public boolean estaNevando() {
    return juego.estaNevando();
  }

  public boolean estaLloviendo() {
    return juego.estaLloviendo();
  }

  public void cargarImagen() {
    jugador.cargarImagen();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Color color = esDeNoche() ? new Color(60, 60, 60) : new Color(60, 170, 230);
    this.setBackground((estaLloviendo() || estaNevando()) ? color.darker() : color);

    String imagenAMostrar = esDeNoche() ? "luna" : "sol3";
    Image imagen = new javax.swing.ImageIcon(getClass().getResource("/imagenes/" + imagenAMostrar + ".png")).getImage();
    
    nube.establecerDireccion("derecha");
    nube.actualizar();
    nube.dibujar(g);
       
    if (!estaLloviendo() && !estaNevando()) {
      //dibujar el estrellas (o la luna)
      g.setColor(esDeNoche() ? new Color(200, 200, 200) : Color.YELLOW);

      int diametroAstro = esDeNoche() ? 150 : 400;
      int x = esDeNoche() ? 10 : this.getWidth() - diametroAstro;
      int y = esDeNoche() ? 60 : 0;
      g.drawImage(imagen, x, y, diametroAstro, diametroAstro, this);
      
      if( esDeNoche() ) {
        for (Sol estrella : estrellas) {
          if (estrella.limites.x < -estrella.limites.width) {
            estrella.limites.x = this.getWidth() - estrella.limites.width;

            if (nube.limites.x < -nube.limites.width) {
              nube.limites.x = this.getWidth() - nube.limites.width;
            }
          }
          estrella.dibujar(g);
        }
      }
    }

    if (bosque != null) {
      bosque.dibujar(g);

      if (obstaculos == null) {
        obstaculos = new Obstaculo[]{ new Obstaculo(this, bosque.getCarretera()) };
      }

      for (Obstaculo obstaculo : obstaculos) {
        obstaculo.dibujar(g);
      }
    }

    jugador.dibujar(g);

    boolean seAcaboElJuego = false;
    for (Obstaculo obstaculo : obstaculos) {
      Rectangle rectanguloHombre = new Rectangle(jugador.limites);

      if (rectanguloHombre.intersects(obstaculo.limites)) {
        seAcaboElJuego = true;
        break;
      }
    }

    if (seAcaboElJuego) {
      g.setFont(new Font("Arial", Font.BOLD, 40));
      g.setColor(Color.red);
      g.drawString("Game Over!", getWidth() / 2, getHeight() / 2);

      botonReiniciar.setLocation(getWidth() / 2, getHeight() / 2);
      botonReiniciar.setVisible(true);
      juego.terminar();
    }

    if (estaLloviendo() || estaNevando()) {
      g.setColor(new Color(255, 255, 255, 50));
      Random numerosAleatorios = new Random();
      for (int i = 0; i < 1000; i++) {
        int xGota = numerosAleatorios.nextInt(this.getWidth());
        int yGota = numerosAleatorios.nextInt(this.getWidth());
        int tamanioGotas = 6;

        if (estaLloviendo()) {
          g.drawLine(xGota, yGota, xGota + tamanioGotas, yGota - tamanioGotas);
          g.drawLine(xGota + 1, yGota, xGota + tamanioGotas + 1, yGota - tamanioGotas);
        } else {
          //dibujar copos de nieve
          g.fillOval(xGota - tamanioGotas / 2, yGota - tamanioGotas / 2, tamanioGotas * 2, tamanioGotas);
          g.fillOval(xGota, yGota - tamanioGotas, tamanioGotas, tamanioGotas * 2);
        }
      }
    } //dibujar la nube
    else {
      imagen = new javax.swing.ImageIcon(getClass().getResource("/imagenes/nube.png")).getImage();
      g.drawImage(imagen, posicionNubeX--, posicionNubeY, anchuraNube, anchuraNube / 2, this);

      imagen = new javax.swing.ImageIcon(getClass().getResource("/imagenes/nube4.png")).getImage();
      g.drawImage(imagen, posicionNube2X -= 2, posicionNube2Y, anchuraNube2, anchuraNube2 / 2, this);

      imagen = new javax.swing.ImageIcon(getClass().getResource("/imagenes/nube2.png")).getImage();
      g.drawImage(imagen, posicionNube2X, posicionNubeY, anchuraNube2, anchuraNube2 / 2, this);

      if (posicionNubeX + anchuraNube < 0) {
        posicionNubeX = this.getWidth();
      }

      if (posicionNube2X + anchuraNube2 < 0) {
        posicionNube2X = this.getWidth();
      }
    }

    this.requestFocusInWindow();
  }
}
