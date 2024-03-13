// Je fais partit du package Logique
package Logique;

// J'ai besoin de ces classes pour travailler
import Logique.Utils.TypeObstacle;
import Logique.Utils.Vec2;

public class Plateau {
	// Attributs
	Vec2 dimensions;
	Cellule[][] grille;

	// Premier constructeur avec juste les dimensions -> remplissement par défault
	public Plateau(Vec2 dim) {
		this.grille = new Cellule[dim.getY()][dim.getX()];
		for(int y = 0; y < this.grille.length; y++)
		{
			for(int x = 0; x < this.grille.length; x++)
			{
				this.grille[y][x] = new Cellule(TypeObstacle.VIDE, new Vec2(y, x));
			}
		}
		return;
	}

	// Premier constructeur avec juste les dimensions -> remplissement random

	// Méthodes
	public void print() {
		for(int y = 0; y < this.grille.length; y++)
		{
			for(int x = 0; x < this.grille.length; x++)
			{
        		System.out.print(this.grille[y][x].toString());
			}
        	System.out.println(); 
		}
        return;
	}

}