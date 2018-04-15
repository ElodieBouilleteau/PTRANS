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

public class VisuRect extends Application {

	@Override
    public void start(Stage primaryStage) throws Exception{
		Group root = new Group();
		Canvas canvas = new Canvas(1200,700);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		ArrayList<Image> images = new ArrayList<Image>(43);
		
		Color CP, CM;
		double HP, SP, BP, HM, SM, BM;
		
		ArrayList<Integer> G1P, G2P, G3P, G1M, G2M, G3M;
		G1P = new ArrayList<Integer>();
		G2P = new ArrayList<Integer>();
		G3P = new ArrayList<Integer>();
		G1M = new ArrayList<Integer>();
		G2M = new ArrayList<Integer>();
		G3M = new ArrayList<Integer>();
		
		//remplissage des listes
		G3P.add(22);
		G3P.add(30);
		 
		G2P.add(25);
		G2P.add(5);
		G2P.add(42);
		G2P.add(19);
		G2P.add(26);
		G2P.add(21);
		G2P.add(17);
		G2P.add(41);
		
		G1P.add(1);
		G1P.add(2);
		G1P.add(10);
		G1P.add(7);
		G1P.add(4);
		G1P.add(37);
		G1P.add(3);
		G1P.add(35);
		G1P.add(11);
		
		G3M.add(12);
		G3M.add(40);
		
		G2M.add(32);
		G2M.add(11);
		G2M.add(15);
		G2M.add(24);
		
		G1M.add(16);
		G1M.add(43);
		G1M.add(39);
		G1M.add(36);
		G1M.add(34);
		G1M.add(38);
		G1M.add(32);
		G1M.add(29);
		G1M.add(14);
		G1M.add(13);
		G1M.add(8);
		G1M.add(31);
		G1M.add(20);
		G1M.add(9);
		G1M.add(6);
		G1M.add(28);
		G1M.add(23);
		G1M.add(27);
		
		for (int i = 1; i<=43; i++){
	      Image img = new Image("file:C:\\Users\\journ\\Documents\\ETUDE\\PTRANS\\images\\1 images URDLA online Copie\\"+i+".jpg");
		  images.add(img);
		}
		
		int width, height;
		
		int t1, t2, t3;
		
		width = 1200;
		height = 700;
		
		t1 = 80;
		t2 = 100;
		t3 = 180;
		  
		int W = width;
		int H1 = height-t1;
		int H2 = (int) (H1*0.72);
		int H3 = (int) (H1*0.27);
		
		CP = Color.BLUE;
		CM = Color.RED;
		
		HP = CP.getHue();
		HM = CM.getHue();
		SP = CP.getSaturation();
		SM = CM.getSaturation();
		BP = CP.getBrightness();
		BM = CM.getBrightness();
		
		width = 1200;
		height = 700;
		
		t1 = 80;
		t2 = 120;
		t3 = 180;
	  
		for (double r = (H1/2+t1/2); r >= 0; r--) {
			gc.setFill(Color.hsb(HP, SP-r*(SP-0)/(H1/2+t1/2), BP-r*(BP-1)/(H1/2+t1/2)));
			gc.fillRect(0,(H1/2+t1/2)-r,width,height-r);

		}
		for (double r = (H1/2+t1/2); r >= 0; r--) {
			gc.setFill(Color.hsb(HM, SM-r*(SM-0)/(H1/2+t1/2), BM-r*(BM-1)/(H1/2+t1/2)));
			gc.fillRect(0,height/2+10,width,r);
		}
		
		setGradient(width/2,height/2,width,20,CP, CM, gc);
		
		gc.setGlobalAlpha(0.3);
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
		  
		  
		  gc.setGlobalAlpha(0.7);
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
		
		root.getChildren().add(canvas);
		Scene canvasScene = new Scene(root, 1200, 700);	//Création d'une scène initialiser avec la VBox "popupImportResults", et de taille : w et h.
        primaryStage.setTitle("PQmethodVisu");
        primaryStage.setScene(canvasScene);
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
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
    double resizew(double w, double h, double d){
	  
      double H = Math.sqrt(w*w + h*h);
	  
	  return w/H*d;
	}

    double resizeh(double w, double h, double d){
	  
	  double H = Math.sqrt(w*w + h*h);
	  
	  return h/H*d;
	}
}
