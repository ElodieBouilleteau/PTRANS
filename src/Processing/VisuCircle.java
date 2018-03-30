package Processing;

import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;


public class VisuCircle extends PApplet{
	private ArrayList<PImage> images = new ArrayList<PImage>(43);
	  
	//parametre taille
	private double pw = 1; //
	private double ph = 1;
	private double pt = 0.4;
	  
	//taille des images en px
	private double t1 = 80;
	private double t3 = 180;
	private double t2 = Math.floor(t1+(t3-t1)*0.4); 
	  
	private double D1, D2, D3, d1, d2, d3;
	
	//couleurs et caractéristiques des couleurs pour les dégradés
	Color CP, CM;
	double HP, SP, BP, HM, SM, BM;

	public static void main(String[] args) {
		PApplet.main("VisuCircle");
	}
	
	public void Settings() {
		size(1200,700);
		background(255);
	}
	
	public void setup() {
		D1 = Math.floor(width-t1);
		D2 = Math.floor(D1*0.72);
		D3 = Math.floor(D1*0.27);
		d1 = height-t1;
		d2 = Math.floor(d1*0.72);
		d3 = Math.floor(d1*0.27);
		
		CP = new Color(84,192,254);
		CM = new Color(254,1,1);
		
	}

}
