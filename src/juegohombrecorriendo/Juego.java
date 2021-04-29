/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegohombrecorriendo;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author josearielpereyra
 */
public class Juego {

  enum Clima {LLOVIENDO, NEVANDO, NORMAL};
  enum Tiempo {DIA, NOCHE};
  enum EstadoJugador {DETENIDO, CORRIENDO, SALTANDO};
  
  PanelDeJuego panelDeJuego;
  Tiempo tiempo;
  Clima clima;
  EstadoJugador estadoJugador;
  private final Timer temporizador;
  Jugador jugador;
  
  public Juego() {
    tiempo = Tiempo.DIA;
    clima = Clima.NORMAL;
    estadoJugador = EstadoJugador.DETENIDO;
    temporizador = new Timer(100, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        panelDeJuego.cargarImagen();
      }
    });

    actualizarDelay(100);
  }
  
  public void setPanelDeJuego( PanelDeJuego panelDeJuego ) {
    this.panelDeJuego = panelDeJuego;
    jugador = new Jugador(panelDeJuego);
  }
  
  public void actualizarDelay( int velocidad ) {
    temporizador.setDelay(velocidad);
  }
  
  public void iniciar() {
    temporizador.start();
  }
  
  void pausar() {
    temporizador.stop();
  }
  
  /**
   * Termina el juego
   */
  void terminar() {
    pausar();
    //TODO validar lo que necesitamos hacer adicionalmente
  }
  
  public void cambiarClima( Clima clima ) {
    this.clima = clima;
  }
  
  public void cambiarTiempo( Tiempo tiempo ) {
    this.tiempo = tiempo;
  }
  
  public boolean esDeNoche() {
    return tiempo == Tiempo.NOCHE;
  }

  public boolean estaNevando() {
    return clima == Clima.NEVANDO;
  }

  public boolean estaLloviendo() {
    return clima == Clima.LLOVIENDO;
  }
  
/**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new VentanaJuego().setVisible(true);
      }
    });
  }
  
}
