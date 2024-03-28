package Graphique;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import Logique.Cellule;
import Logique.Utils.TypeObstacle;
import Logique.Utils.Vec2;

public  class PanResolution extends JPanel
{
	public PanResolution(Affichage parent)
	{
	super(new GridLayout(parent.HEIGHT,parent.WIDTH));

      for (int i = 0; i < parent.HEIGHT; i++) 
      {
         for (int j = 0; j < parent.WIDTH; j++)
         {
            int x = i;
            int y = j;
            JButton button = new JButton("");
            switch(parent.p[i][j].getType()) 
            {
               case VIDE:
                  button.setBackground(Color.WHITE);
                  break;
               case MUR:
                  button.setBackground(Color.BLACK);
                  break;                  
               case MIROIR:
                  button.setBackground(Color.GRAY);
                  break;                  

               case SRC_LASER:
                  button.setBackground(Color.RED);
                  break; 

                  
            }
            button.addActionListener( e-> 
            {
               if(button.getBackground()==Color.WHITE)
               {
                  button.setBackground(Color.RED);
                  parent.p[x][y] = new Cellule(TypeObstacle.SRC_LASER, new Vec2(1, 2),new Vec2(0,-1));
                  revalidate();
                  repaint();
                  lancer_resolution(parent);
               }
            });
            add(button);
         }
         
      }
	}

   private void lancer_resolution(Affichage parent)
   {  
      //Vec2 maDimension = new Vec2(parent.WIDTH,parent.HEIGHT);

      //Plateau plateau = new Plateau(maDimension, maSrcLaser, mesObstacles);
      //lancer la resolution 
      removeAll();
      setLayout(new GridLayout(parent.HEIGHT, parent.WIDTH));

      for (int i = 0; i < parent.HEIGHT ; i++)
      {
         for (int j = 0; j < parent.WIDTH; j++)
         {
            JButton button = new JButton("");

            switch(parent.p[i][j].getType()) 
            {
               case VIDE:
                  button.setBackground(Color.WHITE);                  
                  break;

               case MUR:
                  button.setBackground(Color.BLACK);
                  break;  

               case MIROIR:
                  button.setBackground(Color.GRAY);
                  parent.p[i][j] = new Cellule(TypeObstacle.VIDE, new Vec2(2,1));
                  break;                  

               case SRC_LASER:
                  button.setBackground(Color.RED);
                  parent.p[i][j] = new Cellule(TypeObstacle.VIDE, new Vec2(2,1));
                  break; 

/*               case LASER:
                  button.setBackground(Color.RED);
                  parentp.[i][j] = new Cellule(TypeObstacle.VIDE, new Vec2(2,1));
                  break; */

                  
            }
            add(button);
         }
      }
      revalidate();
      repaint();
   }

}