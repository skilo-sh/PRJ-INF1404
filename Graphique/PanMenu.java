package Graphique;
import Logique.Cellule;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class PanMenu extends JPanel {
    public PanMenu(Affichage parent) {
        JButton resolution = new JButton("Lancer la résolution");
        JButton map = new JButton("Créer une map");




        resolution.addActionListener(e -> {
            parent.changePanel(new PanResolution(parent));
        });

        map.addActionListener(e -> {
            parent.changePanel(new PanCreerMap(parent));

        });

        add(map);
        add(resolution);




    }




}