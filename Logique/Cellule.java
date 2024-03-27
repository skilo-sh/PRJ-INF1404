// Je fais partit du package Logique
package Logique;

// J'ai besoin de ces classes pour travailler
import Logique.Utils.TypeObstacle;
import Logique.Utils.Direction;
import Logique.Utils.Vec2;

public class Cellule {
	// Attributs
	protected Vec2 position;    // (X, Y) contrairement aux accès d'array qui se font en [Y][X]
	protected Vec2 orientation; // X c'est ±1/0 et Y c'est ±1/0 aussi
	protected TypeObstacle type;
	protected Direction directionSuivante;

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
		this.directionSuivante = Direction.GAUCHE;
	}

	public Cellule(TypeObstacle type, Vec2 position, Vec2 orientation)
	{
		this.type = type;
		this.position = position;
		this.orientation = orientation;
		this.directionSuivante = Direction.GAUCHE;
	}

	// Méthodes
	public String toString()
	{
		switch(this.type)
		{
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

	public boolean estLibre()
	{
		if(this.type != TypeObstacle.MUR     &
		   this.type != TypeObstacle.MIROIR  & 
		   this.type != TypeObstacle.SRC_LASER)
			return true;
		else
			return false;
	}

	// TODO : Cette méthode néglige le gauche/droite sur le premier déplacement, à changer
	public Cellule getFirstCellule(Plateau p)
	{
		if(this.type != TypeObstacle.SRC_LASER)
			throw new java.lang.Error("Impossible d'utiliser cette méthode si tu n'es pas la source laser :(");

		int new_y = this.position.getY() + this.orientation.getY();
		int new_x = this.position.getX() + this.orientation.getX();

		if(!p.isValidCoordinate(new_x,  new_y))
			throw new java.lang.Error("La source laser est mal placée/orientée :(");

		Cellule firstCellule = p.getGrille()[new_y][new_x];
		firstCellule.setOrientation(this.orientation);

		return firstCellule;
	}

	public Cellule choixSuivant(Plateau p)
	{
		// Récupération de la position et orientation de la cellule actuel
		Vec2 np = this.getPosition();
		Vec2 no = this.getOrientation();

		// Determination de l'orientation du laser
		boolean isVertical = no.getY() == 1 || no.getY() == -1 ? true : false;
		Vec2 new_pos; Cellule newCellule;

		switch(this.directionSuivante)
		{
			case GAUCHE:
			{
				System.out.println("GAUCHE");
				this.directionSuivante = Direction.DEVANT;
				if(isVertical)
				{
					new_pos = new Vec2(np.getX() + no.getY(), np.getY());

					if(!p.isValidCoordinate(new_pos.getX(), new_pos.getY()) || 
					   !p.getGrille()[new_pos.getY()][new_pos.getX()].estLibre())
					{
						this.directionSuivante = Direction.DEVANT;
						return this.choixSuivant(p);
					}

					newCellule = p.getGrille()[new_pos.getY()][new_pos.getX()];
					newCellule.setOrientation(new Vec2(-1, 0));
				} else {
					new_pos = new Vec2(np.getX(), np.getY() - no.getX());

					if(!p.isValidCoordinate(new_pos.getX(), new_pos.getY()) || 
					   !p.getGrille()[new_pos.getY()][new_pos.getX()].estLibre())
					{
						this.directionSuivante = Direction.DEVANT;
						return this.choixSuivant(p);
					}

					newCellule = p.getGrille()[new_pos.getY()][new_pos.getX()];
					newCellule.setOrientation(new Vec2(0, -1));
				}
				return newCellule;
			}
				
			case DEVANT:
			{
				System.out.println("DVT");
				this.directionSuivante = Direction.DROITE;
				if(isVertical)
				{
					new_pos = new Vec2(np.getX(), np.getY() + no.getY());

					if(!p.isValidCoordinate(new_pos.getX(), new_pos.getY()) || 
					   !p.getGrille()[new_pos.getY()][new_pos.getX()].estLibre())
					{
						this.directionSuivante = Direction.DROITE;
						return this.choixSuivant(p);
					}

					newCellule = p.getGrille()[new_pos.getY()][new_pos.getX()];
					newCellule.setOrientation(new Vec2(no.getX(), no.getY()));
				} else {
					new_pos = new Vec2(np.getX() + no.getX(), np.getY());

					if(!p.isValidCoordinate(new_pos.getX(), new_pos.getY()) || 
					   !p.getGrille()[new_pos.getY()][new_pos.getX()].estLibre())
					{
						this.directionSuivante = Direction.DROITE;
						return this.choixSuivant(p);
					}

					newCellule = p.getGrille()[new_pos.getY()][new_pos.getX()];
					newCellule.setOrientation(new Vec2(no.getX(), no.getY()));
				}
				return newCellule;
			}
			
			case DROITE:
			{
				System.out.println("DROITE");
				this.directionSuivante = Direction.FINIT;
				if(isVertical)
				{
					new_pos = new Vec2(np.getX() - no.getY(), np.getY());

					if(!p.isValidCoordinate(new_pos.getX(), new_pos.getY()) || 
					   !p.getGrille()[new_pos.getY()][new_pos.getX()].estLibre())
					{
						return null;
					}

					newCellule = p.getGrille()[new_pos.getY()][new_pos.getX()];
					newCellule.setOrientation(new Vec2(1, 0));
				} else {
					new_pos = new Vec2(np.getX(), np.getY() + no.getX());

					if(!p.isValidCoordinate(new_pos.getX(), new_pos.getY()) || 
					   !p.getGrille()[new_pos.getY()][new_pos.getX()].estLibre())
					{
						return null;
					}
					newCellule = p.getGrille()[new_pos.getY()][new_pos.getX()];
					newCellule.setOrientation(new Vec2(0, 1));
				}
				return newCellule;
			}

			case FINIT:
				return null;
			default:
				throw new java.lang.Error("Unreachable !!!");
		}
	}

	// Des Getters et des Setters
	public Vec2 getPosition(){return this.position;}
	public TypeObstacle getType(){return this.type;}
	public Vec2 getOrientation(){return this.orientation;}
	public void setType(TypeObstacle t){this.type = t;return;}
	public void setOrientation(Vec2 o){this.orientation = o;return;}
}