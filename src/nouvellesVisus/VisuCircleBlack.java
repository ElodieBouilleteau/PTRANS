package nouvellesVisus;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class VisuCircleBlack extends Application {

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
		  
		int D1, D2, D3, d1, d2, d3;
		
		int t1, t2, t3;
		
		CP = Color.hsb(240,1,1);
		CM = Color.hsb(1,1,1);
		
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
	  
	    D1 = (int) (canvas.getWidth()-t1);
	    D2 = (int) (D1*0.72);
	    D3 = (int) (D1*0.27);
	    d1 = (int) (canvas.getHeight()-t1);
	    d2 = (int) (d1*0.72);
	    d3 = (int) (d1*0.27);
	
	    gc.setFill(Color.BLACK);
	    gc.fillRect(0, 0, width, height);
	    
		for (double i = (D1+t1); i >= 0; i--) {
			gc.setFill(Color.hsb(HP, SP, (i-(D1+t1))*(0-BP)/(D1+t1)));
			gc.fillArc(canvas.getWidth()/2-i/2,
					canvas.getHeight()/2-i*(d1+t1/2)/(2*(D1+t1))-10,
					i,
					i*(d1+t1/2)/(D1+t1),
					0,180,ArcType.ROUND);

		}
		for (double i = (D1+t1); i >= 0; i--) {
			gc.setFill(Color.hsb(HM, SM, (i-(D1+t1))*(0-BM)/(D1+t1)));
			gc.fillArc(canvas.getWidth()/2-i/2,
					canvas.getHeight()/2-i*(d1+t1/2)/(2*(D1+t1))+10,
					i,
					i*(d1+t1/2)/(D1+t1),
					180,180,ArcType.ROUND);
		}
		
		setGradient(width/2,height/2,width,20,CP, CM, gc);
		
		gc.setGlobalAlpha(0.3);
		for(int i =1; i<=G1P.size();i++){
		    double w = resizew(images.get(G1P.get(i-1)-1).getWidth(), images.get(G1P.get(i-1)-1).getHeight(), t1);
		    double h = resizeh(images.get(G1P.get(i-1)-1).getWidth(), images.get(G1P.get(i-1)-1).getHeight(), t1);
		    gc.drawImage(images.get(G1P.get(i-1)-1), width/2+Math.cos(i*Math.PI/(G1P.size()+1))*D1/2-w/2, height/2-Math.sin(i*Math.PI/(G1P.size()+1))*d1/2-h/2, w, h);
		  }
		  
		  for(int i =1; i<=G1M.size();i++){
		    double w = resizew(images.get(G1M.get(i-1)-1).getWidth(), images.get(G1M.get(i-1)-1).getHeight(), t1);
		    double h = resizeh(images.get(G1M.get(i-1)-1).getWidth(), images.get(G1M.get(i-1)-1).getHeight(), t1);
		    gc.drawImage(images.get(G1M.get(i-1)-1), width/2+Math.cos((G1M.size()+1+i)*Math.PI/(G1M.size()+1))*D1/2-w/2, height/2-Math.sin((G1M.size()+1+i)*Math.PI/(G1M.size()+1))*d1/2-h/2, w, h);
		  }
		  
		  
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
	  Color ci;
	  double x = mx-w/2;
	  double y = my-h/2;
	  for (int i = 0; i < h; i++) {
	    //création couleur
	    ci = Color.color(c1.getRed()+i*(c2.getRed()-c1.getRed())/h, c1.getGreen()+i*(c2.getGreen()-c1.getGreen())/h,c1.getBlue()+i*(c2.getBlue()-c1.getBlue())/h);
	    for (double j = x; j <= x+w/2; j++) {
	        Color c = Color.hsb(ci.getHue(), ci.getSaturation(),(j-x)*(ci.getBrightness()-0)/(w/2));
	        gc.setFill(c);
	        gc.fillRect(j, y+i, 1, 1);
	    }
	    for (double j = x+w/2; j <= x+w; j++) {
	        Color c = Color.hsb(ci.getHue(), ci.getSaturation(),ci.getBrightness()+(j-(x+w/2))*(0-ci.getBrightness())/(w/2));
	        gc.setFill(c);
	        gc.fillRect(j, y+i, 1, 1);
	    }
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
