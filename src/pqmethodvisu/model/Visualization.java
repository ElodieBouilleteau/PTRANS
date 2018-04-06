package pqmethodvisu.model;

import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;

public class Visualization {
	
	private static Visualization uniqueInstance = new Visualization();
	
	private Color CP, CM; //colorP = up part color and colorM = low part color
	private int factor1, factor2; //factor1 = main factor, factor2 = secondary factor
	private int t1, t3;
	private int width, height;
	private int alpha1, alpha2, alpha3; //transparency between 0 and 255
	private ArrayList<Image> corpus;
	private ArrayList<Image> GP1, GP2, GP3, GM1, GM2, GM3, G0;
	
	
	private Visualization() {
		this.CP = Color.BLUE;
		this.CM = Color.RED;
		this.t1 = 80;
		this.t3 = 180;
		this.width = 1200;
		this.height = 700;
		this.alpha1 = 100;
		this.alpha2 = 200;
		this.alpha3 = 255;
		this.factor1 = 1;
		this.factor2 = 2;
		this.GP1 = new ArrayList<Image>();
		this.GP2 = new ArrayList<Image>();
		this.GP3 = new ArrayList<Image>();
		this.GM1 = new ArrayList<Image>();
		this.GM2 = new ArrayList<Image>();
		this.GM3 = new ArrayList<Image>();
		this.G0 = new ArrayList<Image>();
	}
	
	public static Visualization getInstance() {
		return Visualization.uniqueInstance;
	}
	
	public void setGroup() {
		double zscore;
		for(Image i : corpus) {
			zscore = i.getListFactor().get(factor1-1).getZscore();
			if (zscore > 0 && zscore <= 1) {
				this.GP1.add(i);
			}
			else if(zscore > 1 && zscore <= 1.8) {
				this.GP2.add(i);
			}
			else if(zscore > 1.8 && zscore <= 2) {
				this.GP3.add(i);
			}
			else if(zscore < 0 && zscore >= -1) {
				this.GM1.add(i);
			}
			else if(zscore < -1 && zscore >= -1.8) {
				this.GM2.add(i);
			}
			else if(zscore < -1.8 && zscore >= -2) {
				this.GP3.add(i);
			}
			else if(zscore == 0) {
				this.G0.add(i);
			}
			while (this.G0.isEmpty() == false) {
				if (this.GP1.size() < this.GM1.size()) {
					this.GP1.add(this.G0.get(0));
					this.G0.remove(0);
				}
				else {
					this.GM1.add(this.G0.get(0));
					this.G0.remove(0);
				}
				
			}
		}
	}
	
	public void showRectangularVisualization( ) {
		VisuRect.setT1(this.t1);
		VisuRect.setT3(this.t3);
		VisuRect.setWidth(this.width);
		VisuRect.setHeight(this.height);
		VisuRect.setCP(this.CP);
		VisuRect.setCM(this.CM);
		PApplet.main("VisuRect");
	}
	
	public void showCircularVisualization() {
		VisuCircle.setT1(this.t1);
		VisuCircle.setT3(this.t3);
		VisuCircle.setWidth(this.width);
		VisuCircle.setHeight(this.height);
		VisuCircle.setCP(this.CP);
		VisuCircle.setCM(this.CM);
		PApplet.main("VisuCircle");
	}
	
	public void showBlackRectangularVisualization( ) {
		VisuRectBlack.setT1(this.t1);
		VisuRectBlack.setT3(this.t3);
		VisuRectBlack.setWidth(this.width);
		VisuRectBlack.setHeight(this.height);
		VisuRectBlack.setCP(this.CP);
		VisuRectBlack.setCM(this.CM);
		PApplet.main("VisuRect");
	}
	
	public void showBlackCircularVisualization() {
		VisuCircleBlack.setT1(this.t1);
		VisuCircleBlack.setT3(this.t3);
		VisuCircleBlack.setWidth(this.width);
		VisuCircleBlack.setHeight(this.height);
		VisuCircleBlack.setCP(this.CP);
		VisuCircleBlack.setCM(this.CM);
		PApplet.main("VisuCircleBlack");
	}
	
	public void setCorpus(ArrayList<Image> corpus) {
		this.corpus = corpus;
	}
	

	public Color getCP() {
		return CP;
	}

	public void setCP(Color CP) {
		this.CP = CP;
	}

	public Color getCM() {
		return CM;
	}

	public void setCM(Color CM) {
		this.CM = CM;
	}

	public int getFactor1() {
		return factor1;
	}

	public void setFactor1(int factor1) {
		this.factor1 = factor1;
	}

	public int getFactor2() {
		return factor2;
	}

	public void setFactor2(int factor2) {
		this.factor2 = factor2;
	}

	public int getT1() {
		return t1;
	}

	public void setT1(int t1) {
		this.t1 = t1;
	}

	public int getT3() {
		return t3;
	}

	public void setT3(int t3) {
		this.t3 = t3;
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

	public int getAlpha1() {
		return alpha1;
	}

	public void setAlpha1(int alpha1) {
		this.alpha1 = alpha1;
	}

	public int getAlpha2() {
		return alpha2;
	}

	public void setAlpha2(int alpha2) {
		this.alpha2 = alpha2;
	}

	public int getAlpha3() {
		return alpha3;
	}

	public void setAlpha3(int alpha3) {
		this.alpha3 = alpha3;
	}
	
	

}
