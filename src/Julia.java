public class Julia extends Fractal{
	public Julia(Complex low, Complex high, int nrows,	int	ncols, int maxIters, Complex c){
		this.low=low;
		this.high=high;
		this.nrows=nrows;
		this.ncols=ncols;
		this.maxIters=maxIters;
		this.c=new Complex(c.r,c.i);
	}

	public int escapeCount(Complex p){
		Complex z = new Complex(p.r,p.i);
		int escape=0;
		for (int i=0;i<=maxIters;i++){
			
			Complex znext = new Complex(0,0);	//create new z
			
			
			znext.r=(z.r*z.r)-(z.i*z.i);	//calculate z^2
			znext.i=(z.r*z.i)+(z.i*z.r);
			
			
			znext.r+=c.r;	//add p to z
			znext.i+=c.i;

			escape=i;
			if (Math.abs(Math.sqrt(znext.r*znext.r+znext.i*znext.i))>=2){
				return escape;		//if magnitude of z > 2
			}
			z=znext;
			
		}
		return escape;
	}

	@Override public String	toString(){
		String pretty="Julia: "+ nrows+" "+ncols+" "+maxIters;
		pretty+="\n	"+low+" "+high;
		pretty+="\n	"+c;
		return pretty;
	}

	public static void main(String[] args){
		double r= Double.parseDouble(args[0]);
		double i= Double.parseDouble(args[2]);
		Complex low=new Complex(r,i);		//create complex low from args

		r= Double.parseDouble(args[1]);
		i= Double.parseDouble(args[3]);
		Complex high= new Complex(r,i);		//create complex high from args

		r= Double.parseDouble(args[7]);
		i= Double.parseDouble(args[8]);
		Complex point = new Complex(r,i);		//create point c to be used later from args

		String filename= args[9];
		Julia jul= new Julia(low, high, Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6]), point);
		
		jul.escapes();
		jul.write(filename);
		System.out.println(jul);
		
	}
}