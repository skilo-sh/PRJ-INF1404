import Logique.Cellule;
import Logique.Plateau;
import Logique.Utils.TypeObstacle;
import Logique.Utils.Vec2;
import Graphique.Affichage;
import javax.swing.*;
import java.awt.event.*;import java.util.Random;

/* 
    Todo :
        - Changer la manière de choisir la première Cellule, la méthode actuel empêche le gauche/droite en première action ;
        - Lors de l'affichage différencier miroir et laser ;
        - ...
*/

public class App {
    public static void main(String[] args) {
        Vec2 maDimension = new Vec2(20, 13);
        Cellule maSrcLaser  = new Cellule(TypeObstacle.SRC_LASER, new Vec2(2, 2), new Vec2(0, -1));

        // Remplissage avec 50 murs choisit aléatoirement
        Cellule[] mesObstacles = new Cellule[50];
        Random rand = new Random();
        for(int i = 0; i < 50; i++)
        {
            int rand_y = rand.nextInt(13);
            int rand_x = rand.nextInt(20);

            mesObstacles[i] = new Cellule(TypeObstacle.MUR, new Vec2(rand_x, rand_y));
        }

        Plateau p = new Plateau(maDimension, maSrcLaser, mesObstacles);
        System.out.print(p);
        p.resoudre();
    }
}