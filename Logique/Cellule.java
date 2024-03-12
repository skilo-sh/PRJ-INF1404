// Je fais partit du package Logique
package Logique;

// J'ai besoin de ces classes pour travailler
import Logique.Utils.TypeObstacle;
import Logique.Utils.Vec2;

public class Cellule {
	// Attributs
	TypeObstacle type;
	Vec2 position;
	Vec2 orientation;

	// Constructeurs
	public Cellule(TypeObstacle type, Vec2 position) {
		this.type = type;
		this.position = position;
		this.orientation = orientation;
	}

	// Méthodes
	public String toString() {
		switch(this.type) {
			case VIDE:
        		return "_";
      		case MUR:
      			return "O";
      		case MIROIR:
      			return "|";
      		case SRC_LASER:
      			return ">";
		}

		return " ";
	}
}