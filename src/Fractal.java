import java.io.*;


public abstract class Fractal{
	Complex	low,	high;	 //	lower-left	and	upper-right	coordinates
	int	nrows,	ncols;	 //	pixel	counting
	int	maxIters;	 //	how	many	iterations	to	consider
	int[][]escapeVals; //	cached	answers	for	each	point's	iterations	to	escape
	Complex	c; //	what	is	the	c	value	of	the	iteration	function?	(boring	for	Mandelbrot)

	public abstract	int	escapeCount(Complex	p);

	public int[][] escapes(){
		double real = low.r;		//these vals used to iterate across or down
		double imag = high.i;
		double dx= (high.r-low.r)/(ncols-1);	//how much to iterate
		double di= (high.i-low.i)/(nrows-1);	//with -2,2, these vals are .285 for example 
		Complex p = new Complex(0,0);			//point to pass into escapeCount
		
		escapeVals = new int[nrows][ncols];
		for( int i = 0; i<nrows;i++){		//iterate down (imaginary axis)
			for (int j =0;j<ncols;j++){		//iterate across (real axis)
				p.r=real; p.i=imag;			
				escapeVals[i][j]=escapeCount(p);
				real=real+dx;				//moves p (1/ncols) over
			}
			real=low.r;						//goes back to left side of coordinate area
			imag=imag-di;					//moves p (1/rows) down
		}
		
		return escapeVals;
	}

	public void	write(String filename) {
		try{
			File file = new File(System.getProperty("user.dir")+"/"+filename);
			
			
			//this jumbled mess is just making the output in the .txt file pretty
			PrintWriter pw = new PrintWriter(file);
			BufferedWriter bw = new BufferedWriter(pw);
			bw.write(Integer.toString(nrows)+" "+Integer.toString(ncols)+" "+Integer.toString(maxIters)+"\n");
			bw.write(Double.toString(low.r)+" "+Double.toString(high.r)+" "
				+Double.toString(low.i)+" "+Double.toString(high.i)+"\n");
			bw.write(Double.toString(c.r)+" "+Double.toString(c.i)+"\n\n");
			for (int i=0;i<nrows;i++){
				for (int j = 0;j<ncols;j++){
					bw.write(Integer.toString(escapeVals[i][j]));
					if (escapeVals[i][j]<10){		//different padding depending on the number 
						bw.write("  ");				//so all columns line up right
					}
					else{
						bw.write(" ");
					}
				}
				bw.write("\n");
			}
			bw.close();


		}
		catch (IOException e){
			System.out.println("Woops");
		}
	}

	public void	zoom(double	factor){
		low.i=low.i/factor;
		low.r= low.r/factor;
		high.i=high.i/factor;
		high.r= high.r/factor;
	}
	
	public void	updateDimensions(Complex newLow, Complex newHigh){
		low=newLow;
		high=newHigh;
	} 

	@Override public String	toString(){
		return "such prettyness";
	}	

}