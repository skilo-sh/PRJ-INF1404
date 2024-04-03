package Graphique;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import Logique.Utils.Vec2;


public class LaserButton extends JButton
{	
	Vec2 sens;
	public LaserButton(Vec2 sens,boolean croise)
	{
		super("");
		this.setBackground(Color.WHITE);
		this.sens = croise?new Vec2(1,1):sens;
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
	} 

    @Override
    protected void paintComponent(Graphics g) {
        // Appeler la méthode paintComponent du parent pour dessiner le fond du bouton
    	super.paintComponent(g);
    
    	// Convertir l'objet Graphics en Graphics2D
    	Graphics2D g2d = (Graphics2D) g;
    
    	// Définir l'épaisseur du trait (taille du "pinceau")
    	float strokeWidth = 6.0f; // Choisissez la taille de votre pinceau
    	g2d.setStroke(new BasicStroke(strokeWidth));
        // Dessiner un trait rouge au centre du bouton
        
        g.setColor(Color.RED);
        if (this.sens.getX() != 0) 
        {
            g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);

        }
        if (this.sens.getY() != 0) 
        {
            g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
        }
            
    }
}