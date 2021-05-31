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
  // codigo kingcode arreglo de estrellas de bonos y atributos de la estrellita
  int pEstX = 1500, pEstY = 560, ancEst = 60;
  Estrella estrellitas;
  
  Juego juego;
  VentanaJuego ventanaJuego;
  Bosque bosque;
  Sol[] estrellas;
  Nube nube;
  JButton botonReiniciar = new JButton("Iniciar");

  public PanelDeJuego(VentanaJuego ventanaJuego, Juego juego) {
    this.juego = juego;
    this.ventanaJuego = ventanaJuego;
    this.bosque = new Bosque(0, new Color(120, 80, 80), this);
    this.setLayout(null);
    botonReiniciar.setSize(200, 50);
    botonReiniciar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ventanaJuego.iniciarODetener("Iniciar");
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
    
    //codigo kingcode carga de imagen
    //int diamEst = 60, xEst = this.getWidth() - diamEst, yEst = 560;
    Image imagenEst = new javax.swing.ImageIcon(getClass().getResource("/imagenes/estrella.png")).getImage();
    this.estrellitas = new Estrella(new Rectangle(this.pEstX , this.pEstY, this.ancEst, this.ancEst), imagenEst, this);
            

  }

  public boolean esDeNoche() {
    return juego != null ? juego.esDeNoche() : false;
  }

  public boolean estaNevando() {
    return juego != null ? juego.estaNevando() : false;
  }

  public boolean estaLloviendo() {
    return juego != null ? juego.estaLloviendo() : false;
  }

  public void cargarImagen() {
    if(juego != null) {
      juego.jugador.cargarImagen();
    }
    
    System.out.println("juego: " + juego);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Color color = esDeNoche() ? new Color(60, 60, 60) : new Color(60, 170, 230);
    this.setBackground((estaLloviendo() || estaNevando()) ? color.darker() : color);
    
     
    String imagenAMostrar = esDeNoche() ? "luna" : "sol3";
    Image imagen = new javax.swing.ImageIcon(getClass().getResource("/imagenes/" + imagenAMostrar + ".png")).getImage();
    //imagen = new javax.swing.ImageIcon(getClass().getResource("/imagenes/nube.png")).getImage();
            //g.drawImage(imagenEst, pEstX--, pEstY, ancEst, ancEst / 2, this);
    // codigo kingCode establecer comportamiento de la estrellita
    estrellitas.establecerDireccion("izquierda");
    estrellitas.actualizar();
    estrellitas.dibujar(g);
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
            if(estrellitas.limites.x < -estrellitas.limites.width)
                estrellitas.limites.x = this.getWidth() - estrellitas.limites.width;
          }
          estrella.dibujar(g);
        }
      }
    }

    if (bosque != null) {
      bosque.dibujar(g);
      // codigo kingcode
      /*if( estrellitas == null){
          //
      }*/
      
      if (obstaculos == null) {
        obstaculos = new Obstaculo[]{ new Obstaculo(this, bosque.getCarretera()) };
      }
      
      for (Obstaculo obstaculo : obstaculos) {
        obstaculo.dibujar(g);
      }
    }

    if( juego != null ) {
      juego.jugador.dibujar(g);

      boolean seAcaboElJuego = false;
      for (Obstaculo obstaculo : obstaculos) {
        Rectangle rectanguloHombre = new Rectangle(juego.jugador.limites);

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
    // codigo kingCode
    imagen = new javax.swing.ImageIcon(getClass().getResource("/imagenes/estrella.png")).getImage();
    g.drawImage(imagen, pEstX-=20, pEstY,ancEst,ancEst,  this);
    if(pEstX + ancEst < 0)
        pEstX = this.getWidth();

    this.requestFocusInWindow();
  }
}
