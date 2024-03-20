package Graphique;

import javax.swing.*;
import java.awt.event.*;

public class Affichage extends JFrame {

   public Affichage() {
      super("Ma Superbe App");

      WindowListener l = new WindowAdapter() {
         public void windowClosing(WindowEvent e){
            System.exit(0);
         }
      };

      addWindowListener(l);
      setSize(200,100);
      setVisible(true);
   }
}