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
	int scoreMeilleureGrille;
	Cellule[][] meilleureGrille;

	// Constructeur
	public Plateau(Vec2 dim, Cellule sourceLaser, Cellule[] obstacles)
	{
		// Mise à jour des attributs
		this.sourceLaser = sourceLaser;
		this.estSolutionne = false;
		this.dimensions = dim;
		this.scoreMeilleureGrille = 0;
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
	public void resoudre()
	{
		// On initialise la stack de backtrace
		Stack<Cellule> maStack = new Stack<Cellule>();

		// Todo : Changer cette manière de faire
		// On y ajoute la cellule initiale (celle juste devant la source du laser)
		Cellule c = this.sourceLaser.getFirstCellule(this);
		c.setType(TypeObstacle.LASER);
		this.tempUpdate(c);
		maStack.push(c);

		// Tant que la stack n'est pas vide ...
		int counter = 0;
		while(!maStack.empty())
		{
			try 
			{
    			TimeUnit.SECONDS.sleep(2);
			}
			catch (InterruptedException ie)
			{
    			Thread.currentThread().interrupt();
			}
		
			// On récupère le top de la stack
			Cellule cellulePeek = maStack.peek();
			// Et la cellule suivante
			// System.out.println("truc :" + cellulePeek);
			Cellule celluleSuivante = cellulePeek.choixSuivant(this);
			// System.out.println("Counter = " + counter);

			// Si la cellule courante ne trouve pas de chemin
			if(celluleSuivante == null)
			{
				this.majMeilleureGrille();
				// System.out.println("Je touche le fond");
				this.tempUpdate2(maStack.pop());
			}
			else
			{
				maStack.push(celluleSuivante);
				this.tempUpdate(celluleSuivante);
			} 
			System.out.print(this);
			System.out.println("-----------");

			counter += 1;
		}
	}

	public void majMeilleureGrille()
	{
		int current_score = this.getScore();
		if(current_score > scoreMeilleureGrille)
		{
			// System.out.println(this);
			this.meilleureGrille = this.copyCurrentGrille();
			this.scoreMeilleureGrille = current_score;
		}
	}

	public int getScore()
	{
		int score = 0;
		for(int y = 0; y < this.dimensions.getY(); y++)
			for(int x = 0; x < this.dimensions.getX(); x++)
				if(this.grille[y][x].getType() == TypeObstacle.LASER || this.grille[y][x].getType() == TypeObstacle.MIROIR)
					score ++;
		return score;
	}

	public Cellule[][] copyCurrentGrille()
	{
		Cellule[][] newGrille = new Cellule[this.dimensions.getY()][this.dimensions.getX()];

		// Remplissage de VIDE sur toutes les cases
		for(int y = 0; y < this.dimensions.getY(); y++)
			for(int x = 0; x < this.dimensions.getX(); x++)
				newGrille[y][x] = this.grille[y][x].copy();

		return newGrille;
	}

	public void tempUpdate(Cellule c)
	{
		Vec2 np = c.getPosition(); 
		this.grille[np.getY()][np.getX()].setType(c.getType());
	}
	public void tempUpdate2(Cellule c)
	{
		Vec2 np = c.getPosition();
		if(c.getType() != TypeObstacle.LASER)
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
	public Cellule[][] getMeilleureGrille(){return this.meilleureGrille;}
	public int  getScoreMeilleureGrille(){return this.scoreMeilleureGrille;}
}