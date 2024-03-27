// Je fais partit du package Logique
package Logique;

// J'ai besoin de ces classes pour travailler
import Logique.Utils.TypeObstacle;
import Logique.Utils.Vec2;
import java.util.ArrayList;
import java.util.Stack;

public class Plateau {
	// Attributs
	Vec2 dimensions;
	Cellule[][] grille;
	Cellule sourceLaser;
	boolean estSolutionne;

	// Premier constructeur avec les dimensions, la src laser et les murs
	public Plateau(Vec2 dim, Cellule sourceLaser, Cellule[] obstacles) {
		// Mise à jour des attributs
		this.sourceLaser = sourceLaser;
		this.estSolutionne = false;
		this.dimensions = dim;
		this.grille = new Cellule[dim.getY()][dim.getX()];

		// Remplissage de vide sur toute les cases
		for(int y = 0; y < this.grille.length; y++)
			for(int x = 0; x < this.grille.length; x++)
				this.grille[y][x] = new Cellule(TypeObstacle.VIDE, new Vec2(y, x));

		// Remplissage des obstacles
		for(Cellule obs : obstacles)
			this.grille[obs.position.getY()][obs.position.getX()] = obs;

		// Ajout de la source du laser
		this.grille[sourceLaser.position.getY()][sourceLaser.position.getX()] = sourceLaser;
	}


	// Méthodes
	public boolean resoudre()
	{
		// On initialise la stack de backtrace
		Stack<Cellule> maStack = new Stack<Cellule>();

		// On y ajoute la cellule initiale (celle juste devant la source du laser)
		maStack.push(this.sourceLaser.getFirstCellule(this));

		// Tant que la stack n'est pas vide ...
		while(!maStack.empty())
		{
			Cellule CelluleTop = maStack.peek();
			Cellule CelluleSuivante = CelluleTop.nextCellule();
			return true;
		}

		return true;
	}

	public String toString() {
		String buff = "";
		for(int y = 0; y < this.grille.length; y++)
		{
			for(int x = 0; x < this.grille.length; x++)
			{
        		buff += this.grille[y][x].toString() + " ";
			}
			buff += "\n";
		}
        return buff;
	}

	public void ajoutObstacle(Vec2 emplacement)
	{
		this.grille[emplacement.getY()][emplacement.getX()] = new Cellule(TypeObstacle.MUR, new Vec2(emplacement.getY(), emplacement.getX()));
	}

	public void reset()
	{
		for(int y = 0; y < this.grille.length; y++)
			for(int x = 0; x < this.grille.length; x++)
				if(this.grille[y][x].getType() == TypeObstacle.MUR || this.grille[y][x].getType() == TypeObstacle.LASER)
					this.grille[y][x].setType(TypeObstacle.VIDE);
	}

	public boolean isValidCoordinate(int x, int y)
	{
		if((x < 0 || y < 0) || 
		   (x >= this.dimensions.getX() || y >= this.dimensions.getY()))
		{
			return false;
		} else { 
			return true;
		}
	}

	// Getter
	public Cellule[][] getGrille()
	{
		return this.grille;
	}

	public Cellule getSourceLaser()
	{
		return this.sourceLaser;
	}
}