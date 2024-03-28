package Graphique;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import Logique.Cellule;
import Logique.Utils.TypeObstacle;
import Logique.Utils.Vec2;


public class Affichage extends JFrame {

   private JPanel currentPanel ;
   private JMenuItem chargerItem;
   private JMenuItem enregistrerItem;
   public final int WIDTH = 5;
   public final int HEIGHT = 5;
   public Cellule[][] p;

   public Affichage() {
      super("Ma Superbe App");
      p = new Cellule[HEIGHT][WIDTH];
      for (int i = 0; i<HEIGHT; i++)
        {
         for(int j = 0; j<WIDTH; j++)
         {
            p[i][j] = new Cellule(TypeObstacle.VIDE, new Vec2(i, j));
         }
        }
      WindowListener l = new WindowAdapter() 
      {
         public void windowClosing(WindowEvent e)
         {
            System.exit(0);
         }
      };

      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      setSize(screenSize.width, screenSize.height);
      addWindowListener(l);
      


     




// Créer la barre de menu et le menu
      JMenuBar menuBar = new JMenuBar();
      JMenu fileMenu = new JMenu("Fichier");
      enregistrerItem = new JMenuItem("Enregistrer");
      chargerItem = new JMenuItem("Charger");
      JMenuItem menuItem = new JMenuItem("Menu");
      JMenuItem quitterItem = new JMenuItem("Quitter");

      quitterItem.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            System.exit(0);
         }
      });

      enregistrerItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
               File selectedFile = fileChooser.getSelectedFile();
               System.out.println(selectedFile);
               try 
               {

                  
                  // Créer un objet FileWriter pour écrire dans le fichier
                  FileWriter writer = new FileWriter(selectedFile);
                  
                  // Créer un objet BufferedWriter pour écrire des données en tampon
                  BufferedWriter bufferWriter = new BufferedWriter(writer);
                  
                  for (int i = 0;i<HEIGHT;i++)
                  {
                     for (int j = 0; j<WIDTH; j++)
                     {
                        bufferWriter.write(p[i][j].toString());
                     }
                     bufferWriter.newLine();
                  }
                  
                  // Fermer le BufferedWriter
                  bufferWriter.close();
                  
                  System.out.println("Les données ont été écrites dans le fichier avec succès.");
               } 
               catch (IOException x) 
               {
                  System.out.println("Une erreur s'est produite lors de l'écriture dans le fichier : " + x.getMessage());
               }
            }
         }
      });

      chargerItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) 
            {
               File selectedFile = fileChooser.getSelectedFile();
               System.out.println(selectedFile);
               try 
               {
                  // Créer un objet FileReader pour lire à partir du fichier
                  FileReader reader = new FileReader(selectedFile);
                  // Créer un objet BufferedReader pour lire les données en tampon
                  BufferedReader bufferedReader = new BufferedReader(reader);
                  
                  // Lire les lignes du fichier une par une
                  String ligne;
                  for (int i =0; i < HEIGHT ; i++)
                  {
                     ligne = bufferedReader.readLine();
                     for(int j = 0; j < WIDTH;j++)
                     {
                        if(ligne.charAt(j) == 'X')
                        {
                           p[i][j]=new Cellule(TypeObstacle.MUR, new Vec2(i, j));
                        }
                        else if (ligne.charAt(j) == '_')
                        {
                           p[i][j]=new Cellule(TypeObstacle.VIDE, new Vec2(i, j));
                        }
                     }
                  }
                  
                  // Fermer le BufferedReader
                  bufferedReader.close();
               } catch (IOException x) 
               {
                  System.out.println("Une erreur s'est produite lors de la lecture du fichier : " + x.getMessage());
               }
               changePanel(new PanCreerMap(Affichage.this));
            }
         }
      });

      menuItem.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {  
            changePanel(new PanMenu(Affichage.this));
            enregistrerItem.setEnabled(false);
            chargerItem.setEnabled(false);
         }
      });






      // Ajouter des éléments de menu au menu
      fileMenu.add(menuItem);

      fileMenu.add(enregistrerItem);
      fileMenu.add(chargerItem);
      fileMenu.add(quitterItem);



      // Ajouter le menu à la barre de menu
      menuBar.add(fileMenu);



      // Définir la barre de menu sur ce JFrame
      setJMenuBar(menuBar);
      enregistrerItem.setEnabled(false);
      chargerItem.setEnabled(false);


      currentPanel = new PanMenu(this);
      add(currentPanel);
      //On affiche la fenêtre
      setVisible(true);
   }


   public void changePanel(JPanel newPanel) {
        // Supprimer le panneau actuel
        remove(this.currentPanel);
        
        // Mise à jour de la référence au panneau actuel avec le nouveau panneau
        currentPanel = newPanel;
        
        // Ajouter le nouveau panneau au conteneur parent
        add(currentPanel);
        
        // Activer ou désactiver les éléments de menu appropriés selon le nouveau panneau
        if (newPanel instanceof PanCreerMap) {
            enregistrerItem.setEnabled(true);
            chargerItem.setEnabled(true);
        } else {
            enregistrerItem.setEnabled(false);
            chargerItem.setEnabled(false);
        }
        
        // Revalider et redessiner le conteneur parent pour refléter les modifications
        revalidate();
        repaint();
    }




}