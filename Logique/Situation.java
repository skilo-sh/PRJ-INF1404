// Je fais partit du package Logique
package Logique;

// J'ai besoin de ces classes pour travailler
import Logique.Utils.TypeObstacle;
import Logique.Utils.Vec2;

import java.util.ArrayList;

public class Situation {
	// Attributs
	Cellule laserCourant;
	ArrayList<Situation> evolutionPossibles = new ArrayList<Situation>();

	// Constructeur
	public Situation()
	{
		System.out.println("[!] Faut implem le constructeur de Situation");
	}

	public Situation(Plateau p, Cellule laserCourant)
	{
		Vec2 lp = laserCourant.getPosition();
		Vec2 lo = laserCourant.getOrientation();
		Cellule[][] g = p.getGrille();

		// init de situation avec 0, 0, 0

		// Determinons d'abord si le laser est à l'horizontale ou a la vertical
		boolean is_vertical = lo.getY() == 1 || lo.getY() == -1 ? true : false;

		// Regardons si c'est libre devant le laser
		if (is_vertical)
		{
			Cellule cell_devant = g[lp.getY() + lo.getY()][lp.getX()];

			if(cell_devant.est_libre())
			{
				Cellule cell_gauche = g[lp.getY() + lo.getY()][lp.getX() - 1];
				Cellule cell_droite = g[lp.getY() + lo.getY()][lp.getX() + 1];
				// On append le choix aller devant
				Situation aller_devant = new Situation();
				this.evolutionPossibles.add(aller_devant);

				if(cell_gauche.est_libre())
				{
					// On append le choix aller à gauche
					Situation aller_gauche = new Situation();
					this.evolutionPossibles.add(aller_gauche);
				}

				if(cell_droite.est_libre())
				{
					// On append le choix aller à droite
					Situation aller_droite = new Situation();
					this.evolutionPossibles.add(aller_droite);
				}
			}
		} else {
			Cellule cell_devant = g[lp.getY()][lp.getX() + lo.getX()];

			if(cell_devant.est_libre())
			{
				Cellule cell_gauche = g[lp.getY() + 1][lp.getX() + lo.getX()];
				Cellule cell_droite = g[lp.getY() + 1][lp.getX() + lo.getX()];
				// On append le choix aller devant
				Situation aller_devant = new Situation();
				this.evolutionPossibles.add(aller_devant);

				if(cell_gauche.est_libre())
				{
					// On append le choix aller à gauche
					Situation aller_gauche = new Situation();
					this.evolutionPossibles.add(aller_gauche);
				}
				if(cell_droite.est_libre())
				{
					// On append le choix aller à droite
					Situation aller_droite = new Situation();
					this.evolutionPossibles.add(aller_droite);
				}
			}
		}
	}

	// Méthodes
	public ArrayList<Situation> getEvolutions()
	{
		return this.evolutionPossibles;
	}
}