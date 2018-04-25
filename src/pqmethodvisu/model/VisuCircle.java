package pqmethodvisu.model;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class VisuCircle extends VisuBipolar {
	
	private HashMap<String, Image> images;
	
	//size parameters
	private int D1, D2, D3, d1, d2, d3;
	
	public VisuCircle(int width, int height, int t1, int t3, Color CP, Color CM, double alpha,
			ArrayList<pqmethodvisu.model.Image> corpus, int factor1, int factor2) {
		super(width, height, t1, t3, CP, CM, alpha, corpus, factor1, factor2);
	    D1 = (int) (width-t1);
	    D2 = (int) (D1*0.72);
	    D3 = (int) (D1*0.27);
	    d1 = (int) (height-t1);
	    d2 = (int) (d1*0.72);
	    d3 = (int) (d1*0.27);
	}
	
	@Override
	public Canvas start() {
		Canvas canvas = new Canvas(super.width,super.height);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		int numberImages = super.getCorpus().size();
		images = new HashMap<String, Image>(numberImages);
		for (int i = 0; i<numberImages; i++){
	      Image img = new Image("file:"+super.getCorpus().get(i).getPath());
		  images.put(super.getCorpus().get(i).getName(), img);
		}
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
		setGradient(super.getWidth()/2,super.getHeight()/2,super.getWidth(),20,super.getCP(), super.getCM(), gc);
		gc.setGlobalAlpha(super.alpha);
		for(int i =1; i<=super.G1P.size();i++){
		    double w = resizew(images.get(super.G1P.get(i-1)).getWidth(), images.get(super.G1P.get(i-1)).getHeight(), t1);
		    double h = resizeh(images.get(super.G1P.get(i-1)).getWidth(), images.get(super.G1P.get(i-1)).getHeight(), t1);
		    gc.drawImage(images.get(super.G1P.get(i-1)), super.getWidth()/2+Math.cos(i*Math.PI/(super.G1P.size()+1))*D1/2-w/2, super.getHeight()/2-Math.sin(i*Math.PI/(super.G1P.size()+1))*d1/2-h/2, w, h);
		  }
		for(int i =0; i<super.G1M.size();i++){
		    double w = resizew(images.get(super.G1M.get(i)).getWidth(), images.get(super.G1M.get(i)).getHeight(), t1);
		    double h = resizeh(images.get(super.G1M.get(i)).getWidth(), images.get(super.G1M.get(i)).getHeight(), t1);
		    gc.drawImage(images.get(super.G1M.get(i)), super.getWidth()/2+Math.cos((super.G1M.size()+1+(super.G1M.size()-i))*Math.PI/(super.G1M.size()+1))*D1/2-w/2, super.getHeight()/2-Math.sin((super.G1M.size()+1+(super.G1M.size()-i))*Math.PI/(super.G1M.size()+1))*d1/2-h/2, w, h);
		}
		gc.setGlobalAlpha(super.alpha+(1-super.alpha)*0.4);
		for(int i =1; i<=super.G2P.size();i++){
		    double w = resizew(images.get(super.G2P.get(i-1)).getWidth(), images.get(super.G2P.get(i-1)).getHeight(), t2);
		    double h = resizeh(images.get(super.G2P.get(i-1)).getWidth(), images.get(super.G2P.get(i-1)).getHeight(), t2);
		    gc.drawImage(images.get(super.G2P.get(i-1)), super.getWidth()/2+Math.cos(i*Math.PI/(super.G2P.size()+1))*D2/2-w/2, super.getHeight()/2-Math.sin(i*Math.PI/(super.G2P.size()+1))*d2/2-h/2, w, h);
		}
		  
		for(int i =0; i<super.G2M.size();i++){
			double w = resizew(images.get(super.G2M.get(i)).getWidth(), images.get(super.G2M.get(i)).getHeight(), t2);
			double h = resizeh(images.get(super.G2M.get(i)).getWidth(), images.get(super.G2M.get(i)).getHeight(), t2);
			gc.drawImage(images.get(super.G2M.get(i)), super.getWidth()/2+Math.cos((super.G2M.size()+1+(super.G2M.size()-i))*Math.PI/(super.G2M.size()+1))*D2/2-w/2, super.getHeight()/2-Math.sin((super.G2M.size()+1+(super.G2M.size()-i))*Math.PI/(super.G2M.size()+1))*d2/2-h/2, w, h);
		}
		
		gc.setGlobalAlpha(1);
		for(int i =1; i<=super.G3P.size();i++){
			double w = resizew(images.get(super.G3P.get(i-1)).getWidth(), images.get(super.G3P.get(i-1)).getHeight(), t3);
			double h = resizeh(images.get(super.G3P.get(i-1)).getWidth(), images.get(super.G3P.get(i-1)).getHeight(), t3);
			gc.drawImage(images.get(super.G3P.get(i-1)), super.getWidth()/2+Math.cos(i*Math.PI/(super.G3P.size()+1))*D3/2-w/2, super.getHeight()/2-Math.sin(i*Math.PI/(super.G3P.size()+1))*d3/2-h/2, w, h);
		}
		  
		for(int i =0; i<super.G3M.size();i++){
			double w = resizew(images.get(super.G3M.get(i)).getWidth(), images.get(super.G3M.get(i)).getHeight(), t3);
			double h = resizeh(images.get(super.G3M.get(i)).getWidth(), images.get(super.G3M.get(i)).getHeight(), t3);
			gc.drawImage(images.get(super.G3M.get(i)), super.getWidth()/2+Math.cos((super.G3M.size()+1+(super.G3M.size()-i))*Math.PI/(super.G3M.size()+1))*D3/2-w/2, super.getHeight()/2-Math.sin((super.G3M.size()+1+(super.G3M.size()-i))*Math.PI/(super.G3M.size()+1))*d3/2-h/2, w, h);
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
	        Color c = Color.hsb(ci.getHue(), Math.max((j-x)*ci.getSaturation()/(w/2),0),Math.min(1+(j-x)*(ci.getBrightness()-1)/(w/2),1));
	        gc.setFill(c);
	        gc.fillRect(j, y+i, 1, 1);
	    }
	    for (double j = x+w/2; j <= x+w; j++) {
	        Color c = Color.hsb(ci.getHue(), Math.max(0, ci.getSaturation()+(j-(x+w/2))*(0-ci.getSaturation())/(w/2)),Math.min(ci.getBrightness()+(j-(x+w/2))*(1-ci.getBrightness())/(w/2),1));
	        gc.setFill(c);
	        gc.fillRect(j, y+i, 1, 1);
	    }
	  }
	}
}