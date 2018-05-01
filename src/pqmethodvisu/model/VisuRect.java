package pqmethodvisu.model;


import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class VisuRect extends VisuBipolar {

	private HashMap<String, Image> images;	
	//size parameters
	private int W, H1, H2, H3;
	
	public VisuRect(int width, int height, int t1, int t3, Color CP, Color CM, double alpha,
			ArrayList<pqmethodvisu.model.Image> corpus, int factor1, int factor2) {
		super(width, height, t1, t3, CP, CM, alpha, corpus, factor1, factor2);
		this.W = width;
		this.H1 = height-t1;
		this.H2 = (int) (H1*0.72);
		this.H3 = (int) (H1*0.27);
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
		
		for (double r = (H1/2+t1/2); r >= 0; r--) {
			gc.setFill(Color.hsb(HP, Math.max(0,SP-r*(SP-0)/(H1/2+t1/2)), Math.min(1, BP-r*(BP-1)/(H1/2+t1/2))));
			gc.fillRect(0,(H1/2+t1/2)-r,width,height-r);

		}
		for (double r = (H1/2+t1/2); r >= 0; r--) {
			gc.setFill(Color.hsb(HM, Math.max(0,SM-r*(SM-0)/(H1/2+t1/2)), Math.min(1, BM-r*(BM-1)/(H1/2+t1/2))));
			gc.fillRect(0,height/2+10,width,r);
		}
		
		setGradient(super.width/2,super.height/2,super.width,20,super.CP, super.CM, gc);
		
		gc.setGlobalAlpha(super.alpha);
		for(int i =0; i<super.G1P.size();i++){
		    double w = resizew(images.get(super.G1P.get(i)).getWidth(), images.get(super.G1P.get(i)).getHeight(), t1);
		    double h = resizeh(images.get(super.G1P.get(i)).getWidth(), images.get(super.G1P.get(i)).getHeight(), t1);
		    gc.drawImage(images.get(super.G1P.get(i)),super.width/2-W/2 + W*((super.G1P.size()-i)-1/2)/(super.G1P.size()+1)-w/2, super.height/2-H1/2-h/2, w,h);
		}
		for(int i =0; i<super.G1M.size();i++){
		    double w = resizew(images.get(super.G1M.get(i)).getWidth(), images.get(super.G1M.get(i)).getHeight(), t1);
		    double h = resizeh(images.get(super.G1M.get(i)).getWidth(), images.get(super.G1M.get(i)).getHeight(), t1);
		    gc.drawImage(images.get(super.G1M.get(i)),super.width/2-W/2 + W*((super.G1M.size()-i)-1/2)/(super.G1M.size()+1)-w/2, super.height/2+H1/2-h/2, w,h);
		}
		  
		  
		gc.setGlobalAlpha(super.alpha+(1-super.alpha)*0.4);
		for(int i =0; i<super.G2P.size();i++){
		    double w = resizew(images.get(super.G2P.get(i)).getWidth(), images.get(super.G2P.get(i)).getHeight(), t2);
		    double h = resizeh(images.get(super.G2P.get(i)).getWidth(), images.get(super.G2P.get(i)).getHeight(), t2);
		    gc.drawImage(images.get(super.G2P.get(i)),super.width/2-W/2 + W*((super.G2P.size()-i)-1/2)/(super.G2P.size()+1)-w/2, super.height/2-H2/2-h/2, w,h);
		}
		  
		for(int i =0; i<super.G2M.size();i++){
			double w = resizew(images.get(super.G2M.get(i)).getWidth(), images.get(super.G2M.get(i)).getHeight(), t2);
			double h = resizeh(images.get(super.G2M.get(i)).getWidth(), images.get(super.G2M.get(i)).getHeight(), t2);
			gc.drawImage(images.get(super.G2M.get(i)),super.width/2-W/2 + W*((super.G2M.size()-i)-1/2)/(super.G2M.size()+1)-w/2, super.height/2+H2/2-h/2, w,h);
		}
		  
		gc.setGlobalAlpha(1);
		for(int i =0; i<super.G3P.size();i++){
			double w = resizew(images.get(super.G3P.get(i)).getWidth(), images.get(super.G3P.get(i)).getHeight(), t3);
			double h = resizeh(images.get(super.G3P.get(i)).getWidth(), images.get(super.G3P.get(i)).getHeight(), t3);
			gc.drawImage(images.get(super.G3P.get(i)),super.width/2-W/2 + W*((super.G3P.size()-i)-1/2)/(super.G3P.size()+1)-w/2, super.height/2-H3/2-h/2, w,h);
		}
		  
		for(int i =0; i<super.G3M.size();i++){
			double w = resizew(images.get(super.G3M.get(i)).getWidth(), images.get(super.G3M.get(i)).getHeight(), t3);
			double h = resizeh(images.get(super.G3M.get(i)).getWidth(), images.get(super.G3M.get(i)).getHeight(), t3);
			gc.drawImage(images.get(super.G3M.get(i)),super.width/2-W/2 + W*((super.G3M.size()-i)-1/2)/(super.G3M.size()+1)-w/2, super.height/2+H3/2-h/2, w,h);
		}
		
		return(canvas);
    }

	void setGradient(double mx, double my, double w, double h, Color c1, Color c2, GraphicsContext gc){
		  
		  double R1, R2, G1, G2, Bl1, Bl2;
		  R1 = c1.getRed();
		  G1 = c1.getGreen();
		  Bl1 = c1.getBlue();
		  R2 = c2.getRed();
		  G2 = c2.getGreen();
		  Bl2 = c2.getBlue();
		  double x = mx-w/2;
		  double y = my-h/2;
		  
		  for( int i =0; i<=h; i++) {
		    gc.setFill(Color.color(R1+i*(R2-R1)/h, G1+i*(G2-G1)/h, Bl1+i*(Bl2-Bl1)/h));
		    gc.fillRect(x,y+i,w,1);
		  }
	}
}
