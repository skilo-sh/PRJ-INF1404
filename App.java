import Logique.Cellule;
import Logique.Plateau;
import Logique.Utils.TypeObstacle;
import Logique.Utils.Vec2;

public class App {
    public static void main(String[] args) {
        Vec2 maDimension = new Vec2(5, 5);
        Cellule maSrcLaser  = new Cellule(TypeObstacle.SRC_LASER, new Vec2(0, 0));
        Cellule[] mesObstacles = { 
            new Cellule(TypeObstacle.MUR, new Vec2(1, 2)),
            new Cellule(TypeObstacle.MUR, new Vec2(2, 2)),
            new Cellule(TypeObstacle.MUR, new Vec2(3, 2)),
            new Cellule(TypeObstacle.MUR, new Vec2(4, 1)),
            new Cellule(TypeObstacle.MUR, new Vec2(1, 1))
        };

        Plateau p = new Plateau(maDimension, maSrcLaser, mesObstacles);
        p.print();
    }
}