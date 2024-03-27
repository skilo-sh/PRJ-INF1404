// Je fais partit du package Logique
package Logique;

// J'ai besoin de ces classes pour travailler
import Logique.Utils.TypeObstacle;
import Logique.Utils.Vec2;
import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

public class Plateau {
	// Attributs
	Vec2 dimensions;
	Cellule[][] grille;
	Cellule sourceLaser;
	boolean estSolutionne;

	// Constructeur
	public Plateau(Vec2 dim, Cellule sourceLaser, Cellule[] obstacles)
	{
		// Mise à jour des attributs
		this.sourceLaser = sourceLaser;
		this.estSolutionne = false;
		this.dimensions = dim;
		this.grille = new Cellule[dim.getY()][dim.getX()];

		// Remplissage de VIDE sur toutes les cases
		for(int y = 0; y < dim.getY(); y++)
			for(int x = 0; x < dim.getX(); x++)
				this.grille[y][x] = new Cellule(TypeObstacle.VIDE, new Vec2(x, y));

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

		// Todo : Changer cette manière de faire
		// On y ajoute la cellule initiale (celle juste devant la source du laser)
		Cellule c = this.sourceLaser.getFirstCellule(this);
		this.tempUpdate(c);
		maStack.push(c);

		// Tant que la stack n'est pas vide ...
		int counter = 0;
		while(!maStack.empty())
		{
			try 
			{
    			TimeUnit.SECONDS.sleep(1);
			}
			catch (InterruptedException ie)
			{
    			Thread.currentThread().interrupt();
			}
		
			// On récupère le top de la stack
			Cellule cellulePeek = maStack.peek();
			// Et la cellule suivante
			Cellule celluleSuivante = cellulePeek.choixSuivant(this);
			System.out.println("Counter = " + counter);

			// Si la cellule courante ne trouve pas de chemin
			if(celluleSuivante == null)
			{
				System.out.println("Je touche le fond");
				this.tempUpdate2(maStack.pop());
			}
			else
			{
				maStack.push(celluleSuivante);
				this.tempUpdate(celluleSuivante);
			} 
			System.out.print(this);

			counter += 1;
		}

		return true;
	}

	public void tempUpdate(Cellule c)
	{
		Vec2 np = c.getPosition(); 
		this.grille[np.getY()][np.getX()].setType(TypeObstacle.LASER);
	}
	public void tempUpdate2(Cellule c)
	{
		Vec2 np = c.getPosition(); 
		this.grille[np.getY()][np.getX()].setType(TypeObstacle.VIDE);
	}

	public String toString() {
		String buff = "";
		for(int y = 0; y < this.dimensions.getY(); y++)
		{
			for(int x = 0; x < this.dimensions.getX(); x++)
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

	// Des Getters
	public Cellule[][] getGrille(){return this.grille;}
	public Cellule getSourceLaser(){return this.sourceLaser;}
}