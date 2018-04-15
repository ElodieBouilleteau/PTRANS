package ancienneVisus;

import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.IntList;

public class VisuRect extends PApplet {
	
	public VisuRect() {
		
	}
	
	private static ArrayList<PImage> images = new ArrayList<PImage>(43);
	  
	//size of the window
	private static int width, height;
	private static int W, H1, H2, H3;
	  
	//picture size in px
	private static int t1;
	private static int t3;
	private static int t2; 
	
	//transparency
	private static float alpha1, alpha2, alpha3;
	
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
		size(width,height);

	}
	
	public void setup() {
		background(255);
		W = floor(width);
		H1 = floor(height)-t1;
		H2 = floor(H1*0.72F);
		H3 = floor(H1*0.27F);
		
		t2 = PApplet.floor(width*0.13F); 
		
		alpha3 = 1;
		alpha2 = alpha1+(alpha3-alpha1)*0.4F;
		
		CP = color(RP,GP,BlP);
		CM = color(RM,GM,BlM);
		
		HP = this.hue(CP);
		SP = this.saturation(CP);
		BP = this.brightness(CP);
		HM = this.hue(CM);
		SM = this.saturation(CM);
		BM = this.brightness(CM);
		
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
		
		//loading pictures
		for (int i = 1; i<=43; i++){
		  images.add(loadImage("images/"+i+".jpg"));
		}
		
		//linear gradient
		
		//transition
		setGradient(floor(width/2), height/2-10, floor(width/2)+W, height/2+10, CP, CM);
		 
		noStroke();
		colorMode(HSB, 360, 100, 100);
		
		//upper part
		for (int r = (H1/2+t1/2); r >=0; r--) {
		  fill(HP, SP-r*(SP-0)/(H1/2+t1/2), BP-r*(BP-100)/(H1/2+t1/2));
		  rect(width/2, height/2-r-10, W, r);
		}
		
		//lower part
		for (int r = (H1/2+t1/2); r >=0; r--) {
		  fill(HM, SM-r*(SM-0)/(H1/2+t1/2), BM-r*(BM-100)/(H1/2+t1/2));
		  rect(width/2, height/2+10, W, r);
		}
		
		
		//placing pictures
		colorMode(RGB, 255,255,255,255);
		tint(255,255,255, 255*alpha1);
		imageMode(CENTER);
		for(int i =1; i<=G1P.size();i++){
		  int w = resizew(images.get(G1P.get(i-1)-1).width, images.get(G1P.get(i-1)-1).height, t1);
		  int h = resizeh(images.get(G1P.get(i-1)-1).width, images.get(G1P.get(i-1)-1).height, t1);
		  image(images.get(G1P.get(i-1)-1),width/2-W/2 + W*(i-1/2)/(G1P.size()+1), height/2-H1/2, w,h);
		}
		
		for(int i =1; i<=G1M.size();i++){
		  int w = resizew(images.get(G1M.get(i-1)-1).width, images.get(G1M.get(i-1)-1).height, t1);
		  int h = resizeh(images.get(G1M.get(i-1)-1).width, images.get(G1M.get(i-1)-1).height, t1);
		  image(images.get(G1M.get(i-1)-1),width/2-W/2 + W*(i-1/2)/(G1M.size()+1), height/2+H1/2, w,h);
		}
		
		tint(255,255,255,255*alpha2);
		for(int i =1; i<=G2P.size();i++){
		  int w = resizew(images.get(G2P.get(i-1)-1).width, images.get(G2P.get(i-1)-1).height, t2);
		  int h = resizeh(images.get(G2P.get(i-1)-1).width, images.get(G2P.get(i-1)-1).height, t2);
		  image(images.get(G2P.get(i-1)-1),width/2-W/2 + W*(i-1/2)/(G2P.size()+1), height/2-H2/2, w,h);
		}
		
		for(int i =1; i<=G2M.size();i++){
		  int w = resizew(images.get(G2M.get(i-1)-1).width, images.get(G2M.get(i-1)-1).height, t2);
		  int h = resizeh(images.get(G2M.get(i-1)-1).width, images.get(G2M.get(i-1)-1).height, t2);
		  image(images.get(G2M.get(i-1)-1),width/2-W/2 + W*(i-1/2)/(G2M.size()+1), height/2+H2/2, w,h);
		}
		
		tint(255,255,255,255*alpha3);
		for(int i =1; i<=G3P.size();i++){
		  int w = resizew(images.get(G3P.get(i-1)-1).width, images.get(G3P.get(i-1)-1).height, t3);
		  int h = resizeh(images.get(G3P.get(i-1)-1).width, images.get(G3P.get(i-1)-1).height, t3);
		  image(images.get(G3P.get(i-1)-1),width/2-W/2 + W*(i-1/2)/(G3P.size()+1), height/2-H3/2, w,h);
		}
		
		for(int i =1; i<=G3M.size();i++){
		  int w = resizew(images.get(G3M.get(i-1)-1).width, images.get(G3M.get(i-1)-1).height, t3);
		  int h = resizeh(images.get(G3M.get(i-1)-1).width, images.get(G3M.get(i-1)-1).height, t3);
		  image(images.get(G3M.get(i-1)-1),width/2-W/2 + W*(i-1/2)/(G3M.size()+1), height/2+H3/2, w,h);
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
	
	void setGradient(int x1, int y1, int x2, int y2, int C1, int C2){
		/* This function build the transition between the upper part and the lower part*/
		  
		float R1, R2, G1, G2, Bl1, Bl2;
		R1 = red(C1);
		G1 = green(C1);
		Bl1 = blue(C1);
		R2 = red(C2);
		G2 = green(C2);
		Bl2 = blue(C2);
		  
		  
		for( int i =0; i<(y2-y1); i++) {
		  stroke(color(R1+i*(R2-R1)/(y2-y1), G1+i*(G2-G1)/(y2-y1), Bl1+i*(Bl2-Bl1)/(y2-y1)));
		  line(x1,y1+i,x2,y1+i);
		}
	}
	
	public static void show() {
		PApplet.main("VisuRect");
	}
	
	public static void setImages(ArrayList<PImage> images) {
		VisuRect.images = images;
	}

	public static void setWidth(int width) {
		VisuRect.width = width;
	}

	public static void setHeight(int height) {
		VisuRect.height = height;
	}

	public static void setT1(int t1) {
		VisuRect.t1 = t1;
	}

	public static void setT3(int t3) {
		VisuRect.t3 = t3;
	}

	public static void setW(int w) {
		VisuRect.W = w;
	}

	public static void setCP(Color CP) {
		VisuRect.RP = CP.getRed();
		VisuRect.GP = CP.getGreen();
		VisuRect.BlP = CP.getBlue();
	}
	
	public static void setCM(Color CM) {
		VisuRect.RM = CM.getRed();
		VisuRect.GM = CM.getGreen();
		VisuRect.BlM = CM.getBlue();
	}
	
	public static void setAlpha1(float alpha1) {
		VisuRect.alpha1 = alpha1;
	}

	public static void setSave(boolean save) {
		VisuRect.save = save;
	}
	
	public static void setSavePath(String savePath) {
		VisuRect.savePath = savePath;
	}

}
