package Model;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class Model implements Observable{
	private Color color1, color2; // couleur de fond de la partie haute et basse de la visualisation
	private int width, height; // largeur et hauteur de la visualisation
	private int t; // taille des images les plus importante (le reste calculé selon cette derniere)
	private ArrayList<Image> listeImage; //images
	
	
	public Model(ArrayList<Image> listeImage) {
		color1 = Color.BLUE;
		color2 = Color.RED;
		width = 1200;
		height = 700;
		t = 120;
		this.listeImage = listeImage;
	}


	public Color getColor1() {
		return color1;
	}


	public void setColor1(Color color1) {
		this.color1 = color1;	
	}


	public Color getColor2() {
		return color2;
	}


	public void setColor2(Color color2) {
		this.color2 = color2;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public int getT() {
		return t;
	}


	public void setT(int t) {
		this.t = t;
	}


	@Override
	public void addListener(InvalidationListener arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void removeListener(InvalidationListener arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
