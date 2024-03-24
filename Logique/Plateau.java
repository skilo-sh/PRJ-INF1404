// Je fais partit du package Logique
package Logique;

// J'ai besoin de ces classes pour travailler
import Logique.Utils.TypeObstacle;
import Logique.Utils.Vec2;
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
		// Création de la stack vide
		Stack<Noeud> maStack = new Stack<Noeud>();

		// Création et ajout du Noeud initiale sur la Stack
		Noeud nInitiale = new Noeud(this, this.sourceLaser);
		maStack.push(nInitiale);

		// Tant que la stack n'est pas vide...
		while(!maStack.isEmpty())
		{
			// On mets dans `nCourant` le top de la stack
			Noeud nCourant = maStack.pop();

			// On mets à jour le Plateau en fonction du `nCourant`
			this.update(nCourant);

			// On push sur la stack chaque fils de `nCourant`
			for(Noeud n : nCourant.getFils())
				maStack.push(n);
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

	public void ajoutObstacle(Vec2 emplacement) {
		this.grille[emplacement.getY()][emplacement.getX()] = new Cellule(TypeObstacle.MUR, new Vec2(emplacement.getY(), emplacement.getX()));
	}

	public void reset()
	{
		for(int y = 0; y < this.grille.length; y++)
			for(int x = 0; x < this.grille.length; x++)
				if(this.grille[y][x].getType() == TypeObstacle.MUR || this.grille[y][x].getType() == TypeObstacle.LASER)
					this.grille[y][x].setType(TypeObstacle.VIDE);
	}

	// Getter
	public Cellule[][] getGrille()
	{
		return this.grille;
	}
}