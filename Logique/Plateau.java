// Je fais partit du package Logique
package Logique;

// J'ai besoin de ces classes pour travailler
import Logique.Utils.TypeObstacle;
import Logique.Utils.Vec2;

public class Plateau {
	// Attributs
	Vec2 dimensions;
	Cellule[][] grille;
	Cellule sourceLaser;
	boolean estSolutionne;

	public Plateau(Vec2 dim, Cellule sourceLaser, Cellule[] obstacles) {
		// Mise à jour des attributs
		this.sourceLaser = sourceLaser;
		this.estSolutionne = false;
		this.grille = new Cellule[dim.getY()][dim.getX()];

		// Remplissage de vide sur toute les cases
		for(int y = 0; y < this.grille.length; y++)
			for(int x = 0; x < this.grille.length; x++)
				this.grille[y][x] = new Cellule(TypeObstacle.VIDE, new Vec2(y, x));

		// Remplissages des obstacles
		for(Cellule obs : obstacles)
			this.grille[obs.position.getY()][obs.position.getX()] = obs;

		// Ajout de la source du laser
		this.grille[sourceLaser.position.getY()][sourceLaser.position.getX()] = sourceLaser;
	}

	// Premier constructeur avec juste les dimensions -> remplissement random

	// // Méthodes
	// public boolean resoudre()
	// {
	// 	while (!this.estSolutionne)
	// 	{

	// 	}
	// 	return;
	// }

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

}