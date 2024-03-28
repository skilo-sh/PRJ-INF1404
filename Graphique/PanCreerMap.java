package Graphique;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import Logique.Cellule;
import Logique.Utils.TypeObstacle;
import Logique.Utils.Vec2;




public class PanCreerMap extends JPanel{

	public PanCreerMap (Affichage parent)
	{
        super();

        // Initialisation de la disposition en grille après l'appel du constructeur de la classe parente
        setLayout(new GridLayout(parent.HEIGHT, parent.WIDTH));
		for ( int i = 0 ; i < parent.HEIGHT ; i++)
		{
			for (int j = 0 ; j < parent.WIDTH ; j++)
			{
				System.out.println(i);
				int x = i;
				int y = j;
				JButton button = new JButton("");
				button.setBackground(parent.p[i][j].getType()==TypeObstacle.VIDE?Color.WHITE:Color.BLACK);
				button.addActionListener(e -> {
					/*button.setBackground(button.getBackground()==Color.WHITE?Color.BLACK:Color.WHITE);*/

					if (button.getBackground() == Color.WHITE)
					{
						button.setBackground(Color.BLACK);
						parent.p[x][y]= new Cellule(TypeObstacle.MUR, new Vec2(1, 2));
					}
					else if (button.getBackground() == Color.BLACK)
					{
						button.setBackground(Color.WHITE);
						parent.p[x][y]= new Cellule(TypeObstacle.VIDE, new Vec2(1, 2));

					}
			});	
				add(button);
			}
		}



	
	}

	public void enregistrer()
	{

	}

	public void charger()
	{

	}
}