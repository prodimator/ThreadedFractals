public class Complex {
	public double r,i;
	public Complex(double r, double i){
		this.r=r;
		this.i=i;
	}
	public static Complex add(Complex a, Complex b){
		a.r+=b.r;
		a.i+=b.i;
		return a;

	}
	public static Complex sub(Complex a, Complex b){
		a.r-=b.r;
		a.i-=b.i;
		return a;
	}
	public static Complex mul(Complex a, Complex b){
		Complex c = new Complex(0,0);
		c.r= (a.r*b.r)-(a.i*b.i);
		c.i= (a.r*b.i)+(a.i*b.r);
		return c;
	}
	public static double abs(Complex c){
		return Math.sqrt((c.r*c.r)+(c.i*c.i));
	}
	public String toString(){
		return this.r+" "+this.i+"i";
	}
	public Complex copy(){
		//what does this actually do?
		Complex copy = new Complex(0,0);
		copy.r=this.r;
		copy.i=this.i;
		return copy;
	}

}