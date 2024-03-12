import Logique.Cellule;
import Logique.Plateau;
import Logique.Utils.TypeObstacle;
import Logique.Utils.Vec2;

public class App {
    public static void main(String[] args) {
        // Vec2 dimension = Vec2("")
        Plateau p = new Plateau(new Vec2(20, 13));
        p.print();
    }
}