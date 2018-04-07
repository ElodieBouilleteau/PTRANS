package pqmethodvisu.model;

import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.IntList;

public class VisuCircle extends PApplet{
	private static ArrayList<PImage> images = new ArrayList<PImage>(43);
	  
	//size parameters 
	private static int width, height;
	private static float pw = 1; //
	private static float ph = 1;
	private static float pt = 0.4F;
	  
	//picture size in px
	private static int t1 = 80;
	private static int t3 = 180;
	private static int t2 = PApplet.floor(t1+(t3-t1)*pt); 
	  
	private static float D1, D2, D3, d1, d2, d3;
	
	//colors and colors' features
	private static int RP, GP, BlP, RM, GM, BlM;
	private static int CP, CM; //colors are represented by int
	private static float HP, SP, BP, HM, SM, BM;
	
	private static IntList G1P = new IntList();
	private static IntList G2P = new IntList();
	private static IntList G3P = new IntList();
	private static IntList G1M = new IntList();
	private static IntList G2M = new IntList();
	private static IntList G3M = new IntList();
	
	//save
	private static String savePath;
	private static boolean save;
	
	public void settings() {
		size(1200,700);
	}
	
	public void setup() {
		background(255);
		D1 = floor(width*pw-t1);
		D2 = floor(D1*0.72F);
		D3 = floor(D1*0.27F);
		d1 = floor(height*ph-t1);
		d2 = floor(d1*0.72F);
		d3 = floor(d1*0.27F);
		
		CP = color(RP,GP,BlP);
		CM = color(RM,GM,BlM);
		
		HP = hue(CP);
		SP = saturation(CP);
		BP = brightness(CP);
		HM = hue(CM);
		SM = saturation(CM);
		BM = brightness(CM);
		
		G3P.append(22);
		G3P.append(30);
		  
		G2P.append(25);
		G2P.append(5);
		G2P.append(42);
		G2P.append(19);
		G2P.append(26);
		G2P.append(21);
		G2P.append(17);
		G2P.append(41);
		  
		G1P.append(1);
		G1P.append(2);
		G1P.append(10);
		G1P.append(7);
		G1P.append(4);
		G1P.append(37);
		G1P.append(3);
		G1P.append(35);
		G1P.append(11);
		  
		G3M.append(12);
		G3M.append(40);
		  
		G2M.append(32);
		G2M.append(11);
		G2M.append(15);
		G2M.append(24);
		
		G1M.append(16);
		G1M.append(43);
		G1M.append(39);
		G1M.append(36);
		G1M.append(34);
		G1M.append(38);
		G1M.append(32);
		G1M.append(29);
		G1M.append(14);
		G1M.append(13);
		G1M.append(8);
		G1M.append(31);
		G1M.append(20);
		G1M.append(9);
		G1M.append(6);
		G1M.append(28);
		G1M.append(23);
		G1M.append(27);
		
		for (int i = 1; i<=43; i++){
		  images.add(loadImage("images/"+i+".jpg"));
		}
		
		//radial gradient
		
		//transition
		setGradient(width/2, height/2, D1+t1, 10, CP, CM);
		  
		noStroke();
		colorMode(HSB, 360, 100, 100);
		 
		//upper part
		for (int r = (int) (D1+t1); r >=0; r--) {
		  fill(HP, SP-r*(SP-0)/(D1+t1), BP-r*(BP-100)/(D1+t1));
		  arc(width/2, height/2-5, r,r*(d1+t1)/(D1+t1), PI, TWO_PI, OPEN);
		}
		  
		 
		//lower part  
		for (int r = (int) (D1+t1); r >=0; r--) {
		  fill(HM, SM-r*(SM-0)/(D1+t1), BM-r*(BM-100)/(D1+t1));
		  arc(width/2, height/2+5, r,r*(d1+t1)/(D1+t1), 0, PI, OPEN);
		}
		
		
		//placing pictures
		colorMode(RGB, 255,255,255,255);
		imageMode(CENTER);
		tint(255,255,255, 100);
		for(int i =1; i<=G1P.size();i++){
		  int w = resizew(images.get(G1P.get(i-1)-1).width, images.get(G1P.get(i-1)-1).height, t1);
		  int h = resizeh(images.get(G1P.get(i-1)-1).width, images.get(G1P.get(i-1)-1).height, t1);
		  image(images.get(G1P.get(i-1)-1), width/2+cos(i*PI/(G1P.size()+1))*D1/2, height/2-sin(i*PI/(G1P.size()+1))*d1/2, w, h);
		}
		  
		for(int i =1; i<=G1M.size();i++){
		  int w = resizew(images.get(G1M.get(i-1)-1).width, images.get(G1M.get(i-1)-1).height, t1);
		  int h = resizeh(images.get(G1M.get(i-1)-1).width, images.get(G1M.get(i-1)-1).height, t1);
		  image(images.get(G1M.get(i-1)-1), width/2+cos((G1M.size()+1+i)*PI/(G1M.size()+1))*D1/2, height/2-sin((G1M.size()+1+i)*PI/(G1M.size()+1))*d1/2, w, h);
		}
		  
		  
		tint(255,255,255,200);
		for(int i =1; i<=G2P.size();i++){
		  int w = resizew(images.get(G2P.get(i-1)-1).width, images.get(G2P.get(i-1)-1).height, t2);
		  int h = resizeh(images.get(G2P.get(i-1)-1).width, images.get(G2P.get(i-1)-1).height, t2);
		  image(images.get(G2P.get(i-1)-1), width/2+cos(i*PI/(G2P.size()+1))*D2/2, height/2-sin(i*PI/(G2P.size()+1))*d2/2, w, h);
		}
		  
		for(int i =1; i<=G2M.size();i++){
		  int w = resizew(images.get(G2M.get(i-1)-1).width, images.get(G2M.get(i-1)-1).height, t2);
		  int h = resizeh(images.get(G2M.get(i-1)-1).width, images.get(G2M.get(i-1)-1).height, t2);
		  image(images.get(G2M.get(i-1)-1), width/2+cos((G2M.size()+1+i)*PI/(G2M.size()+1))*D2/2, height/2-sin((G2M.size()+1+i)*PI/(G2M.size()+1))*d2/2, w, h);
		}
		  
		  
		tint(255,255,255,255);
		for(int i =1; i<=G3P.size();i++){
		  int w = resizew(images.get(G3P.get(i-1)-1).width, images.get(G3P.get(i-1)-1).height, t3);
		  int h = resizeh(images.get(G3P.get(i-1)-1).width, images.get(G3P.get(i-1)-1).height, t3);
		  image(images.get(G3P.get(i-1)-1), width/2+cos(i*PI/(G3P.size()+1))*D3/2, height/2-sin(i*PI/(G3P.size()+1))*d3/2, w, h);
		}
		  
		for(int i =1; i<=G3M.size();i++){
		  int w = resizew(images.get(G3M.get(i-1)-1).width, images.get(G3M.get(i-1)-1).height, t3);
		  int h = resizeh(images.get(G3M.get(i-1)-1).width, images.get(G3M.get(i-1)-1).height, t3);
		  image(images.get(G3M.get(i-1)-1), width/2+cos((G3M.size()+1+i)*PI/(G3M.size()+1))*D3/2, height/2-sin((G3M.size()+1+i)*PI/(G3M.size()+1))*d3/2, w, h);
		}
		
		
		if (save) {
			this.save(savePath);
		}
	}

	
	int resizew(int w, int h, int d){
		/*This function take the diameter of a circle and the size of a picture 
		 * and return the width of the same picture which can hold in the circle*/
		  
		float H = sqrt(sq(w) + sq(h));
		  
		return floor(w/H*d);
	}

	int resizeh(int w, int h, int d){
		/*This function take the diameter of a circle and the size of a picture 
		 * and return the height of the same picture which can hold in the circle*/
		  
		float H = sqrt(sq(w) + sq(h));
		  
		return floor(h/H*d);
	}

	void setGradient(int mx, int my, float w, float h, int c1, int c2){
		/* This function build the transition between the upper part and the lower part*/
		
		noFill();
		colorMode(RGB, 255);
		float inter;
		int ci;
		int x = mx-floor(w/2);
		int y = my-floor(h/2);
		for (int i = 0; i < h; i++) {
		  colorMode(RGB, 255, 255, 255);
		  inter = map(i, 0, h, 0, 1);
		  ci = lerpColor(c1,c2,inter);
		  for (int j = x; j <= x+w/2; j++) {
		      inter = map(j, x, x+w/2, 0, 1);
		      int c = lerpColor(color(255,255,255), ci, inter);
		      stroke(c);
		      line(j, y+i, j, y+i+1);
		  }
		  for (int j = x+floor(w/2); j <= x+w; j++) {
		      inter = map(j, x+w/2, x+w, 0, 1);
		      int c = lerpColor(ci, color(255,255,255), inter);
		      stroke(c);
		      line(j, y+i, j, y+i+1);
		  }
		}
	}

	public static void setWidth(int width) {
		VisuCircle.width = width;
	}

	public static void setHeight(int height) {
		VisuCircle.height = height;
	}

	public static void setT1(int t1) {
		VisuCircle.t1 = t1;
	}

	public static void setT3(int t3) {
		VisuCircle.t3 = t3;
	}
	
	public static void setCP(Color CP) {
		VisuCircle.RP = CP.getRed();
		VisuCircle.GP = CP.getGreen();
		VisuCircle.BlP = CP.getBlue();
	}
	
	public static void setCM(Color CM) {
		VisuCircle.RM = CM.getRed();
		VisuCircle.GM = CM.getGreen();
		VisuCircle.BlM = CM.getBlue();
	}
	
	public static void setSave(boolean save) {
		VisuCircle.save = save;
	}
	
	public static void setSavePath(String savePath) {
		VisuCircle.savePath = savePath;
	}
}