package pqmethodvisu.model;


import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class VisuRectBlack extends VisuBipolar {

	private ArrayList<pqmethodvisu.model.Image> corpus;
	private ArrayList<Image> images;
	
	//size parameters
	private int W, H1, H2, H3;
	
	//colors and colors' features
	private ArrayList<Integer> G1P, G2P, G3P, G1M, G2M, G3M;
	
	//save
	//private static String savePath;
	//private static boolean save;
	
	public VisuRectBlack(int width, int height, int t1, int t3, Color CP, Color CM, double alpha,
			ArrayList<pqmethodvisu.model.Image> corpus, int factor1, int factor2) {
		super(width, height, t1, t3, CP, CM, alpha, corpus, factor1, factor2);
		this.W = width;
		this.H1 = height-t1;
		this.H2 = (int) (H1*0.72);
		this.H3 = (int) (H1*0.27);
	}
	
	@Override
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
	  

		for (double r = (H1/2+t1/2); r >= 0; r--) {
			gc.setFill(Color.hsb(HP, SP, BP-r*(BP-0)/(H1/2+t1/2)));
			gc.fillRect(0,(H1/2+t1/2)-r,width,height-r);

		}
		for (double r = (H1/2+t1/2); r >= 0; r--) {
			gc.setFill(Color.hsb(HM, SM, BM-r*(BM-0)/(H1/2+t1/2)));
			gc.fillRect(0,height/2+10,width,r);
		}
		
		setGradient(width/2,height/2,width,20,CP, CM, gc);
		
		gc.setGlobalAlpha(this.alpha);
		for(int i =1; i<=G1P.size();i++){
		    double w = resizew(images.get(G1P.get(i-1)-1).getWidth(), images.get(G1P.get(i-1)-1).getHeight(), t1);
		    double h = resizeh(images.get(G1P.get(i-1)-1).getWidth(), images.get(G1P.get(i-1)-1).getHeight(), t1);
		    gc.drawImage(images.get(G1P.get(i-1)-1),width/2-W/2 + W*(i-1/2)/(G1P.size()+1)-w/2, height/2-H1/2-h/2, w,h);
		  }
		  
		  for(int i =1; i<=G1M.size();i++){
		    double w = resizew(images.get(G1M.get(i-1)-1).getWidth(), images.get(G1M.get(i-1)-1).getHeight(), t1);
		    double h = resizeh(images.get(G1M.get(i-1)-1).getWidth(), images.get(G1M.get(i-1)-1).getHeight(), t1);
		    gc.drawImage(images.get(G1M.get(i-1)-1),width/2-W/2 + W*(i-1/2)/(G1M.size()+1)-w/2, height/2+H1/2-h/2, w,h);
		  }
		  
		  
		  gc.setGlobalAlpha(this.alpha+(1-this.alpha)*0.4);
		  for(int i =1; i<=G2P.size();i++){
		    double w = resizew(images.get(G2P.get(i-1)-1).getWidth(), images.get(G2P.get(i-1)-1).getHeight(), t2);
		    double h = resizeh(images.get(G2P.get(i-1)-1).getWidth(), images.get(G2P.get(i-1)-1).getHeight(), t2);
		    gc.drawImage(images.get(G2P.get(i-1)-1),width/2-W/2 + W*(i-1/2)/(G2P.size()+1)-w/2, height/2-H2/2-h/2, w,h);
		  }
		  
		  for(int i =1; i<=G2M.size();i++){
			double w = resizew(images.get(G2M.get(i-1)-1).getWidth(), images.get(G2M.get(i-1)-1).getHeight(), t2);
			double h = resizeh(images.get(G2M.get(i-1)-1).getWidth(), images.get(G2M.get(i-1)-1).getHeight(), t2);
			gc.drawImage(images.get(G2M.get(i-1)-1),width/2-W/2 + W*(i-1/2)/(G2M.size()+1)-w/2, height/2+H2/2-h/2, w,h);
		  }
		  
		  gc.setGlobalAlpha(1);
		  for(int i =1; i<=G3P.size();i++){
			double w = resizew(images.get(G3P.get(i-1)-1).getWidth(), images.get(G3P.get(i-1)-1).getHeight(), t3);
			double h = resizeh(images.get(G3P.get(i-1)-1).getWidth(), images.get(G3P.get(i-1)-1).getHeight(), t3);
			gc.drawImage(images.get(G3P.get(i-1)-1),width/2-W/2 + W*(i-1/2)/(G3P.size()+1)-w/2, height/2-H3/2-h/2, w,h);
		  }
		  
		  for(int i =1; i<=G3M.size();i++){
			double w = resizew(images.get(G3M.get(i-1)-1).getWidth(), images.get(G3M.get(i-1)-1).getHeight(), t3);
			double h = resizeh(images.get(G3M.get(i-1)-1).getWidth(), images.get(G3M.get(i-1)-1).getHeight(), t3);
			gc.drawImage(images.get(G3M.get(i-1)-1),width/2-W/2 + W*(i-1/2)/(G3M.size()+1)-w/2, height/2+H3/2-h/2, w,h);
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
