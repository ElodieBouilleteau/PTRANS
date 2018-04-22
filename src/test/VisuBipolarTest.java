package test;

public class VisuBipolarTest {

	public VisuBipolarTest() {}
	
	public double resizewTest(double w, double h, double d){
	      double H = Math.sqrt(w*w + h*h);
	  	  return w/H*d;
	}
	
	public double resizehTest(double w, double h, double d){
		double H = Math.sqrt(w*w + h*h);
	  	return h/H*d;
	}
}
