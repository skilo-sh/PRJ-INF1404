import Logique.Cellule;
import Logique.Plateau;
import Logique.Utils.TypeObstacle;
import Logique.Utils.Vec2;
import Graphique.Affichage;
import javax.swing.*;
import java.awt.event.*;import java.util.Random;

/* 
    Todo :
        - Gérer les superpositions de laser ;
        - ...
*/

public class App {
    public static void main(String[] args) {
        Vec2 maDimension = new Vec2(5, 5);
        Cellule maSrcLaser  = new Cellule(TypeObstacle.SRC_LASER, new Vec2(2, 2), new Vec2(0, -1));

        // Remplissage avec n murs choisit aléatoirement
        int n = 6;
        Cellule[] mesObstacles = new Cellule[n];
        Random rand = new Random();
        for(int i = 0; i < n; i++)
        {
            int rand_y = rand.nextInt(maDimension.getY());
            int rand_x = rand.nextInt(maDimension.getX());

            mesObstacles[i] = new Cellule(TypeObstacle.MUR, new Vec2(rand_x, rand_y));
        }

        Plateau p = new Plateau(maDimension, maSrcLaser, mesObstacles);
        System.out.print(p);
        p.resoudre();
        Cellule[][] bestGrille = p.getMeilleureGrille();
        System.out.println(bestGrille);
        String buff = "";
        for(int y = 0; y < maDimension.getY(); y++)
        {
            for(int x = 0; x < maDimension.getX(); x++)
            {
                buff += bestGrille[y][x].toString() + " ";
            }
            buff += "\n";
        }
        // Affichage de la meilleure grille
        System.out.println(buff);
        // Affichage du meilleur score
        System.out.println(p.getScoreMeilleureGrille());
    }
}