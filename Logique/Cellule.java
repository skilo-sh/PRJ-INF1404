// Je fais partit du package Logique
package Logique;

// J'ai besoin de ces classes pour travailler
import Logique.Utils.TypeObstacle;
import Logique.Utils.Direction;
import Logique.Utils.Vec2;

public class Cellule {
	// Attributs
	protected TypeObstacle type;
	protected Vec2 position; // (X, Y) contrairement aux accès d'array qui se font en [Y][X]
	protected Vec2 orientation; // X c'est ±1 et Y c'est ±1 aussi
	protected Direction directionSuivante = 0;

	// Constructeurs
	public Cellule(TypeObstacle type, Vec2 position)
	{
		if(this.type == TypeObstacle.LASER   ||
		   this.type == TypeObstacle.MIROIR  ||
		   this.type == TypeObstacle.SRC_LASER)
		{
			throw new java.lang.Error("You need an orientation to build LASER/MIROIR/SRC_LASER");
		}

		this.type = type;
		this.position = position;
		this.orientation = orientation;
	}

	public Cellule(TypeObstacle type, Vec2 position, Vec2 orientation)
	{
		this.type = type;
		this.position = position;
		this.orientation = orientation;
	}

	// Méthodes
	// public boolean choixSuivant()
	// {		
	// }

	public String toString()
	{
		switch(this.type) {
			case VIDE:
        		return "_";
      		case MUR:
      			return "X";
      		case MIROIR:
      			return "|";
      		case SRC_LASER:
      			return "o";
      		case LASER:
      			return "L";
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

	public Cellule getFirstCellule(Plateau p)
	{
		if(this.type != TypeObstacle.SRC_LASER)
			throw new java.lang.Error("Impossible d'utiliser cette méthode si tu n'est pas la source laser :(");

		int new_y = this.position.getY() + this.orientation.getY();
		int new_x = this.position.getX() + this.orientation.getX();

		if(!p.isValidCoordinate(new_x,  new_y))
			throw new java.lang.Error("La source laser est mal placée/orientée :(");

		return p.getGrille()[new_y][new_x];
	}

	public Vec2 getPosition()
	{
		return this.position;
	}

	public TypeObstacle getType()
	{
		return this.type;
	}

	public void setType(TypeObstacle t)
	{
		this.type = t;
		return; 
	}

	public Vec2 getOrientation()
	{
		return this.orientation;
	}
}