package Logique.Utils;

public class Vec2 {
    private int x;
    private int y;

    public Vec2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getter et setter pour x
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    // Getter et setter pour y
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Méthode pour ajouter un vecteur à ce vecteur
    public void add(Vec2 other) {
        this.x += other.x;
        this.y += other.y;
    }

    // Méthode pour soustraire un vecteur de ce vecteur
    public void subtract(Vec2 other) {
        this.x -= other.x;
        this.y -= other.y;
    }

    // Méthode pour multiplier ce vecteur par un scalaire
    public void multiply(int scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    // Méthode pour diviser ce vecteur par un scalaire
    public void divide(int scalar) {
        if (scalar != 0) {
            this.x /= scalar;
            this.y /= scalar;
        }
    }

    // Méthode pour calculer la norme (longueur) de ce vecteur
    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    // Méthode pour normaliser ce vecteur (le rendre unitaire)
    public void normalize() {
        double mag = magnitude();
        if (mag != 0) {
            this.x /= mag;
            this.y /= mag;
        }
    }

    public boolean eq(Vec2 o)
    {
        if(this.getX() == o.getX() && this.getY() == o.getY())
            return true;

        return false;
    }

    // Méthode pour créer une copie de ce vecteur
    public Vec2 copy() {
        return new Vec2(this.x, this.y);
    }

    // Méthode pour afficher ce vecteur sous forme de chaîne de caractères
    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
