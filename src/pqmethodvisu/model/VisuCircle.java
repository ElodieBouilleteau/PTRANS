package pqmethodvisu.model;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class VisuCircle {
	
	private ArrayList<pqmethodvisu.model.Image> corpus;
	private ArrayList<Image> images;
	
	//size parameters
	private int width, height;
	
	//picture size in px
	private int t1, t2, t3;
	
	private int D1, D2, D3, d1, d2, d3;
	
	//colors and colors' features
	private ArrayList<Integer> G1P, G2P, G3P, G1M, G2M, G3M;
	private Color CP, CM;
	private double HP, SP, BP, HM, SM, BM;
	
	//save
	//private static String savePath;
	//private static boolean save;
	
	public Canvas start() {
		System.out.println("debut canvas");
		Canvas canvas = new Canvas(width,height);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		int numberImages = corpus.size();
		images = new ArrayList<Image>(numberImages);
		for (int i = 1; i<numberImages; i++){
		  System.out.println(corpus.get(i).getPath());
	      Image img = new Image("file:"+corpus.get(i).getPath());
		  images.add(img);
		}
		System.out.println("CP");
		HP = CP.getHue();
		HM = CM.getHue();
		SP = CP.getSaturation();
		SM = CM.getSaturation();
		BP = CP.getBrightness();
		BM = CM.getBrightness();
		
		t2 = (int) (width*0.13);
	  
	    D1 = (int) (canvas.getWidth()-t1);
	    D2 = (int) (D1*0.72);
	    D3 = (int) (D1*0.27);
	    d1 = (int) (canvas.getHeight()-t1);
	    d2 = (int) (d1*0.72);
	    d3 = (int) (d1*0.27);
	    System.out.println("debut calcul");
		for (double i = (D1+t1); i >= 0; i--) {
			gc.setFill(Color.hsb(HP, (i-(D1+t1))*(0-SP)/(D1+t1), 1+(i-(D1+t1))*(1-BP)/(D1+t1)));
			gc.fillArc(canvas.getWidth()/2-i/2,
					canvas.getHeight()/2-i*(d1+t1/2)/(2*(D1+t1))-10,
					i,
					i*(d1+t1/2)/(D1+t1),
					0,180,ArcType.ROUND);

		}
		for (double i = (D1+t1); i >= 0; i--) {
			gc.setFill(Color.hsb(HM, (i-(D1+t1))*(0-SM)/(D1+t1), 1+(i-(D1+t1))*(1-BM)/(D1+t1)));
			gc.fillArc(canvas.getWidth()/2-i/2,
					canvas.getHeight()/2-i*(d1+t1/2)/(2*(D1+t1))+10,
					i,
					i*(d1+t1/2)/(D1+t1),
					180,180,ArcType.ROUND);
		}
		System.out.println("G1P");
		setGradient(width/2,height/2,width,20,CP, CM, gc);
		gc.setGlobalAlpha(0.3);
		System.out.println("G1P_bis : "+G1P.size());
		/*PROBLEME*/
		for(int i =1; i<=G1P.size();i++){
		    double w = resizew(images.get(G1P.get(i-1)-1).getWidth(), images.get(G1P.get(i-1)-1).getHeight(), t1);
		    double h = resizeh(images.get(G1P.get(i-1)-1).getWidth(), images.get(G1P.get(i-1)-1).getHeight(), t1);
		    gc.drawImage(images.get(G1P.get(i-1)), width/2+Math.cos(i*Math.PI/(G1P.size()+1))*D1/2-w/2, height/2-Math.sin(i*Math.PI/(G1P.size()+1))*d1/2-h/2, w, h);
		  }
		System.out.println("G1M");
		  for(int i =1; i<=G1M.size();i++){
		    double w = resizew(images.get(G1M.get(i-1)-1).getWidth(), images.get(G1M.get(i-1)-1).getHeight(), t1);
		    double h = resizeh(images.get(G1M.get(i-1)-1).getWidth(), images.get(G1M.get(i-1)-1).getHeight(), t1);
		    gc.drawImage(images.get(G1M.get(i-1)-1), width/2+Math.cos((G1M.size()+1+i)*Math.PI/(G1M.size()+1))*D1/2-w/2, height/2-Math.sin((G1M.size()+1+i)*Math.PI/(G1M.size()+1))*d1/2-h/2, w, h);
		  }
		  
		  System.out.println("G2P");
		  gc.setGlobalAlpha(0.7);
		  for(int i =1; i<=G2P.size();i++){
		    double w = resizew(images.get(G2P.get(i-1)-1).getWidth(), images.get(G2P.get(i-1)-1).getHeight(), t2);
		    double h = resizeh(images.get(G2P.get(i-1)-1).getWidth(), images.get(G2P.get(i-1)-1).getHeight(), t2);
		    gc.drawImage(images.get(G2P.get(i-1)-1), width/2+Math.cos(i*Math.PI/(G2P.size()+1))*D2/2-w/2, height/2-Math.sin(i*Math.PI/(G2P.size()+1))*d2/2-h/2, w, h);
		  }
		  
		  for(int i =1; i<=G2M.size();i++){
			double w = resizew(images.get(G2M.get(i-1)-1).getWidth(), images.get(G2M.get(i-1)-1).getHeight(), t2);
			double h = resizeh(images.get(G2M.get(i-1)-1).getWidth(), images.get(G2M.get(i-1)-1).getHeight(), t2);
			gc.drawImage(images.get(G2M.get(i-1)-1), width/2+Math.cos((G2M.size()+1+i)*Math.PI/(G2M.size()+1))*D2/2-w/2, height/2-Math.sin((G2M.size()+1+i)*Math.PI/(G2M.size()+1))*d2/2-h/2, w, h);
		  }
		  
		  System.out.println("G3P");
		  gc.setGlobalAlpha(1);
		  for(int i =1; i<=G3P.size();i++){
			double w = resizew(images.get(G3P.get(i-1)-1).getWidth(), images.get(G3P.get(i-1)-1).getHeight(), t3);
			double h = resizeh(images.get(G3P.get(i-1)-1).getWidth(), images.get(G3P.get(i-1)-1).getHeight(), t3);
			gc.drawImage(images.get(G3P.get(i-1)-1), width/2+Math.cos(i*Math.PI/(G3P.size()+1))*D3/2-w/2, height/2-Math.sin(i*Math.PI/(G3P.size()+1))*d3/2-h/2, w, h);
		  }
		  
		  for(int i =1; i<=G3M.size();i++){
			double w = resizew(images.get(G3M.get(i-1)-1).getWidth(), images.get(G3M.get(i-1)-1).getHeight(), t3);
			double h = resizeh(images.get(G3M.get(i-1)-1).getWidth(), images.get(G3M.get(i-1)-1).getHeight(), t3);
			gc.drawImage(images.get(G3M.get(i-1)-1), width/2+Math.cos((G3M.size()+1+i)*Math.PI/(G3M.size()+1))*D3/2-w/2, height/2-Math.sin((G3M.size()+1+i)*Math.PI/(G3M.size()+1))*d3/2-h/2, w, h);
		  }
		
		return(canvas);
    }

    private void setGradient(double mx, double my, double w, double h, Color c1, Color c2, GraphicsContext gc){
	  Color ci;
	  double x = mx-w/2;
	  double y = my-h/2;
	  for (int i = 0; i < h; i++) {
	    //création couleur
	    ci = Color.color(c1.getRed()+i*(c2.getRed()-c1.getRed())/h, c1.getGreen()+i*(c2.getGreen()-c1.getGreen())/h,c1.getBlue()+i*(c2.getBlue()-c1.getBlue())/h);
	    for (double j = x; j <= x+w/2; j++) {
	        Color c = Color.hsb(ci.getHue(), (j-x)*ci.getSaturation()/(w/2),1+(j-x)*(ci.getBrightness()-1)/(w/2));
	        gc.setFill(c);
	        gc.fillRect(j, y+i, 1, 1);
	    }
	    for (double j = x+w/2; j <= x+w; j++) {
	        Color c = Color.hsb(ci.getHue(), Math.max(0, ci.getSaturation()+(j-(x+w/2))*(0-ci.getSaturation())/(w/2)),ci.getBrightness()+(j-(x+w/2))*(1-ci.getBrightness())/(w/2));
	        gc.setFill(c);
	        gc.fillRect(j, y+i, 1, 1);
	    }
	  }
	}
    
    private double resizew(double w, double h, double d){
      double H = Math.sqrt(w*w + h*h);
	  return w/H*d;
	}

    private double resizeh(double w, double h, double d){
	  double H = Math.sqrt(w*w + h*h);
	  return h/H*d;
	}

    public void setT1(int t1) {
		this.t1 = t1;
	}

    public void setT3(int t3) {
		this.t3 = t3;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setCP(javafx.scene.paint.Color cp) {
		this.CP = cp;
	}

	public void setCM(Color cm) {
		this.CM = cm;
	}
	
	public void setCorpus(ArrayList<pqmethodvisu.model.Image> corpus)
	{
		this.corpus = corpus;
	}
	
	public void setGroupes(ArrayList<Integer> G1P, ArrayList<Integer> G2P, ArrayList<Integer> G3P, ArrayList<Integer> G1M, ArrayList<Integer> G2M, ArrayList<Integer> G3M)
	{
		this.G1P = G1P;
		this.G2P = G2P;
		this.G3P = G3P;
		this.G1M = G1M;
		this.G2M = G2M;
		this.G3M = G3M;
	}
}
