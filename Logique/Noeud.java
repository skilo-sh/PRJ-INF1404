// Je fais partit du package Logique
package Logique;

// J'ai besoin de ces classes pour travailler
import Logique.Utils.TypeObstacle;
import Logique.Utils.Vec2;

import java.util.ArrayList;

/*
	- Obtenir les enfants directs (pas résoudre l'arbre complet)
	- Lui associer une position/orientation (extends de Cellule)
	- 
*/

public class Noeud extends Cellule {
	// Attributs
	private Noeud parent;
	private boolean filsRecupere;
	ArrayList<Noeud> fils = new ArrayList<Noeud>();


	public Noeud(Vec2 position, Vec2 orientation)
	{
		super(position, orientation);
		this.filsRecupere = false;
	}

	public Noeud(Cellule c)
	{
		super(c.getPosition(), c.getOrientation());
		this.filsRecupere = false;
	}

	public Noeud(Vec2 position, Vec2 orientation, Noeud parent)
	{
		super(position, orientation);
		this.filsRecupere = false;
	}

	// Méthodes
	public ArrayList<Noeud> getFils(Plateau p)
	{
		if(this.filsRecupere)
			return this.fils;
		
		Cellule[][] g = p.getGrille();
		int x, y;
	
		// Récupération de la position du noeud actuel
		Vec2 np = this.getPosition();
		// Récupération de l'orientation du noeud actuel
		Vec2 no = this.getOrientation();

		// Determination de l'orientation du laser
		boolean is_vertical = no.getY() == 1 || no.getY() == -1 ? true : false;

		if(is_vertical)
		{
			// Get de la cellule juste devant
			x = np.getX(); y = np.getY() + no.getY();
			if(p.isValidCoordinate(x, y))
			{
				Cellule cell_devant = g[y][x];
				if(cell_devant.est_libre())
				{
					// Ajout du noeud de devant à la liste des fils
					Vec2 new_pos = new Vec2(np.getX(), np.getY() + no.getY());
					Vec2 new_ori = new Vec2(no.getX(), no.getY());
					this.fils.add(new Noeud(new_pos, new_ori, this));
	
					// Get de la cellule juste à dvt à gauche
					x = np.getX() - 1; y = np.getY() + no.getY();
					if(p.isValidCoordinate(x, y))
					{
						Cellule cell_gauche = g[y][x];
						if(cell_gauche.est_libre())
						{
							// Ajout du noeud de devant à gauche à la liste des fils
							new_pos = new Vec2(np.getX() - 1, np.getY() + no.getY());
							new_ori = new Vec2(-1, 0);
							this.fils.add(new Noeud(new_pos, new_ori, this));
						}
					}
	
					// Get de la cellule juste à dvt à droite
					x = np.getX() + 1; y = np.getY() + no.getY();
					if(p.isValidCoordinate(x, y))
					{
						Cellule cell_droite = g[y][x];
						if(cell_droite.est_libre())
						{
							// Ajout du noeud de devant à droite à la liste des fils
							new_pos = new Vec2(np.getX() + 1, np.getY() + no.getY());
							new_ori = new Vec2(1, 0);
							this.fils.add(new Noeud(new_pos, new_ori, this));
						}
					}
				}
			}
		} else {
			// Get de la cellule juste devant
			x = np.getX() + no.getX(); y = np.getY();
			if(p.isValidCoordinate(x, y))
			{
				Cellule cell_devant = g[y][x];

				if(cell_devant.est_libre())
				{
					// Ajout du noeud de devant à la liste des fils
					Vec2 new_pos = new Vec2(np.getX() + no.getX(), np.getY());
					Vec2 new_ori = new Vec2(no.getX(), no.getY());
					this.fils.add(new Noeud(new_pos, new_ori, this));

					// Get de la cellule juste à dvt à gauche
					x = np.getX() + no.getX(); y = np.getY() - 1;
					if(p.isValidCoordinate(x, y))
					{
						Cellule cell_gauche = g[y][x];
						if(cell_gauche.est_libre())
						{
							// Ajout du noeud de devant à gauche à la liste des fils
							new_pos = new Vec2(np.getX() + no.getX(), np.getY() - 1);
							new_ori = new Vec2(0, -1);
							this.fils.add(new Noeud(new_pos, new_ori, this));
						}
					}

					// Get de la cellule juste à dvt à droite
					x = np.getX() + no.getX(); y = np.getY() + 1;
					if(p.isValidCoordinate(x, y))
					{
						Cellule cell_droite = g[y][x];
						if(cell_droite.est_libre())
						{
							// Ajout du noeud de devant à droite à la liste des fils
							new_pos = new Vec2(np.getX() + no.getX(), np.getY() + 1);
							new_ori = new Vec2(0, 1);
							this.fils.add(new Noeud(new_pos, new_ori, this));
						}
					}
				}
			}
		}
		this.filsRecupere = true;
		return this.fils;
	}
}