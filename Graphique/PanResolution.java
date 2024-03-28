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
                  parent.p[x][y] = new Cellule(TypeObstacle.SRC_LASER, new Vec2(x, y),new Vec2(0,-1));
                  revalidate();
                  repaint();
                  lancer_resolution(parent,parent.p[x][y]);
               }
            });
            add(button);
         }
         
      }
	}

   private void lancer_resolution(Affichage parent,Cellule maSrcLaser)
   {  
      Vec2 maDimension = new Vec2(parent.WIDTH,parent.HEIGHT);
      int cpt = 0;
      for ( int i = 0 ; i < parent.HEIGHT; i++ )
      {
         for (int j = 0; j < parent.WIDTH; j++ )
         {
            if (parent.p[i][j].getType() == TypeObstacle.MUR)
            {
               cpt ++;
            }
         }
      }
      Cellule[] mesObstacles = new Cellule[cpt];
      cpt = 0;
      for ( int i = 0 ; i < parent.HEIGHT; i++ )
      {
         for (int j = 0; j < parent.WIDTH; j++ )
         {
            if (parent.p[i][j].getType() == TypeObstacle.MUR)
            {
            mesObstacles[cpt] =  new Cellule(TypeObstacle.MUR, new Vec2(i, j)); 
            cpt ++;
            }
         }
      }
      

      Plateau plateau = new Plateau(maDimension, maSrcLaser, mesObstacles);
      //lancer la resolution 
      plateau.resoudre();
      parent.p = plateau.getMeilleureGrille();

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
                  button = new MirroirButton(parent.p.getOrientation());
                  parent.p[i][j] = new Cellule(TypeObstacle.VIDE, new Vec2(i,j));
                  break;                  

               case SRC_LASER:
                  parent.p[i][j] = new Cellule(TypeObstacle.VIDE, new Vec2(i,j));
                  break; 

               case LASER:
                  button = new LaserButton(parent.p.getOrientation());
                  button.setBackground(Color.RED);
                  parent.p[i][j] = new Cellule(TypeObstacle.VIDE, new Vec2(i,j));
                  break; 

                  
            }
            add(button);
         }
      }
      revalidate();
      repaint();
   }

}