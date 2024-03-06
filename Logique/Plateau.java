// Je fais partit du package Logique
package Logique;

// J'ai besoin de ces classes pour travailler
import Logique.Utils.TypeObstacle;
import Logique.Utils.Vec2;

public class Plateau {
	// Attributs
	Vec2 dimension;
	Cellule[][] grille;

	// Constructeur
	public Plateau() {

	}

	// Méthodes
	public void print() {
        System.out.println("Je print le plateau."); 
	}

}