/** Represents a 2D Position */
public class Posn{
	double x;
	double y;

	Posn(double x, double y){
		this.x = x;
		this.y = y;
	}
	/** Compute the distance between this and the given Posns */
	double dist(Posn that){
		return Math.sqrt(Math.pow(this.x-that.x, 2)+
				Math.pow(this.y-that.y, 2));
	}
	/** Move this Posn in the given direction */
	void move(double dx, double dy){
		this.x += dx;
		this.y += dy;
	}

	/** Is this Posn the same as the given Object */
	public boolean equals(Object o){
		if(!(o instanceof Posn))return false;
		Posn p = (Posn)o;
		return p.x == this.x && p.y == this.y;
	}

	/** Compute a hash code for this Posn */
	public int hashCode(){
		return new Double(this.x + this.y * 3).hashCode();
	}
}