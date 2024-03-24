import Logique.Cellule;
import Logique.Plateau;
import Logique.Utils.TypeObstacle;
import Logique.Utils.Vec2;
import Graphique.Affichage;
import javax.swing.*;
import java.awt.event.*;

/* Todo :
    - Gérer l'erreur posssible d'out of bounds sur la grille dans Situation.java
    - Vérifier tous les code de getFils car surement des issues
*/

public class App {
    public static void main(String[] args) {
        Vec2 maDimension = new Vec2(4, 4);
        Cellule maSrcLaser  = new Cellule(TypeObstacle.SRC_LASER, new Vec2(2, 2), new Vec2(0, -1));
        Cellule[] mesObstacles = { 
            new Cellule(TypeObstacle.MUR, new Vec2(1, 2)),
            new Cellule(TypeObstacle.MUR, new Vec2(2, 3)),
            new Cellule(TypeObstacle.MUR, new Vec2(3, 2)),
            new Cellule(TypeObstacle.MUR, new Vec2(3, 1)),
            // new Cellule(TypeObstacle.MUR, new Vec2(0, 0)),
            new Cellule(TypeObstacle.MUR, new Vec2(1, 1))
        };

        Plateau p = new Plateau(maDimension, maSrcLaser, mesObstacles);
        System.out.print(p);
        p.resoudre();
        // JFrame frame = new Affichage();
    }
}