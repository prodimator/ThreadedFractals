public class Mandelbrot extends Fractal{
	public Mandelbrot(Complex low, Complex high, int nrows,	int	ncols, int maxIters){
		this.low=low;
		this.high=high;
		this.nrows=nrows;
		this.ncols=ncols;
		this.maxIters=maxIters;
		this.c=new Complex(0,0);
	}

	public int escapeCount(Complex p){
		Complex z = new Complex(0,0);
		int escape=0;
		for (int i=0;i<=maxIters;i++){

			Complex znext = new Complex(0,0);		//create new z
			
			znext.r= (z.r*z.r)-(z.i*z.i);		//calculate z^2
			znext.i= (z.r*z.i)+(z.i*z.r);
			
			znext.r+=p.r;		//add p to z
			znext.i+=p.i;
			
			escape=i;
			if (Math.abs(Math.sqrt(znext.r*znext.r+znext.i*znext.i))>2){			//instead of 2
				return escape;		//is magnitude of z >2
			}
			z=znext;
		}
		return escape;
	}

	@Override public String	toString(){
		String pretty="Mandelbrot: "+ nrows+" "+ncols+" "+maxIters;
		pretty+="\n	"+low+" "+high+"\n";
		return pretty;
	}

	public static void	main(String[] args){
		double r= Double.parseDouble(args[0]);		
		double i= Double.parseDouble(args[2]);
		Complex low=new Complex(r,i);		//creates a complex low from args

		r= Double.parseDouble(args[1]);
		i= Double.parseDouble(args[3]);
		Complex high= new Complex(r,i);		//creates complex high from args

		String filename= args[7];
		Mandelbrot man= new Mandelbrot(low, high, Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6]));


		man.escapes();
		man.write(filename);
		System.out.println(man);		//just prints out info into console
	}

}