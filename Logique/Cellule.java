// Je fais partit du package Logique
package Logique;

// J'ai besoin de ces classes pour travailler
import Logique.Utils.TypeObstacle;
import Logique.Utils.Vec2;

public class Cellule {
	// Attributs
	TypeObstacle type;
	Vec2 position;
	Vec2 orientation; // X c'est ±1 et Y c'est ±1 aussi

	// Constructeurs
	public Cellule(TypeObstacle type, Vec2 position) {
		this.type = type;
		this.position = position;
		this.orientation = orientation;
	}

	public Cellule(TypeObstacle type, Vec2 position, Vec2 orientation) {
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
      			return "X";
      		case MIROIR:
      			return "|";
      		case SRC_LASER:
      			return "o";
		}

		return " ";
	}

	public boolean est_libre()
	{
		if(this.type != TypeObstacle.MUR     &
		   this.type != TypeObstacle.MIROIR  & 
		   this.type != TypeObstacle.SRC_LASER)
		{
			return true;
		} else {
			return false;
		}
	}

	public Vec2 getPosition()
	{
		return this.position;
	}

	public Vec2 getOrientation()
	{
		return this.orientation;
	}
}