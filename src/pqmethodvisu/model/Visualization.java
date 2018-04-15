package pqmethodvisu.model;

import javafx.scene.paint.Color;
import java.util.ArrayList;

import javafx.scene.canvas.Canvas;

public class Visualization {
	
	private static Visualization uniqueInstance;
	private ArrayList<Image> corpus;
	
	private int width, height; //size parameters
	private int t1, t3; //picture size in px
	
	//colors and colors' features
	private ArrayList<Integer> G1P, G2P, G3P, G1M, G2M, G3M;
	private Color CP, CM; //colorP = up part color and colorM = low part color
	
	private int factor1, factor2; //factor1 = main factor, factor2 = secondary factor
	//private int alpha1; //transparency for the little images, between 0 and 255
	private String savePath; //path to the saving directory
	private String format; //format of the file (png or jpg);
	private String name;
	private boolean save; //true is we want to save the visualization, false else
	
	
	protected Visualization() {
		this.width = 1200;
		this.height = 700;
		this.t1 = 80;
		this.t3 = 180;
		this.CP = Color.hsb(240,1,1);
		this.CM = Color.hsb(1,1,1);
		//this.alpha1 = 100;
		this.factor1 = 1;
		this.factor2 = 2;
		this.G1P = new ArrayList<Integer>();
		this.G2P = new ArrayList<Integer>();
		this.G3P = new ArrayList<Integer>();
		this.G1M = new ArrayList<Integer>();
		this.G2M = new ArrayList<Integer>();
		this.G3M = new ArrayList<Integer>();
		this.savePath = "C:\\Users\\journ\\Images\\test.png";
		this.save = false;
	}
	
	public static Visualization getInstance() {
		Visualization.uniqueInstance = new Visualization();
		return Visualization.uniqueInstance;
	}
	
	public void init(CollectionImage collectionImages)
	{
		this.corpus = collectionImages.getCorpus();
		setGroup();
	}
	
	private void setGroup() {
		double zscore;
		int compteur = 0;
		for(Image i : corpus) {
			zscore = i.getListFactor().get(factor1-1).getZscore();
			if (zscore >= 0 && zscore <= 1) {
				this.G1P.add(compteur);
			}
			else if(zscore > 1 && zscore <= 1.8) {
				this.G2P.add(compteur);
			}
			else if(zscore > 1.8 && zscore <= 2) {
				this.G3P.add(compteur);
			}
			else if(zscore <= 0 && zscore >= -1) {
				this.G1M.add(compteur);
			}
			else if(zscore < -1 && zscore >= -1.8) {
				this.G2M.add(compteur);
			}
			else if(zscore < -1.8 && zscore >= -2) {
				this.G3M.add(compteur);
			}
			compteur++;
		}
	}
	
	public Canvas getCanvasVisuCircle() {
		VisuCircle visualisation = new VisuCircle();
		visualisation.setCorpus(corpus);
		visualisation.setGroupes(G1P, G2P, G3P, G1M, G2M, G3M);
		visualisation.setT1(this.t1);
		visualisation.setT3(this.t3);
		visualisation.setWidth(this.width);
		visualisation.setHeight(this.height);
		visualisation.setCP(this.CP);
		visualisation.setCM(this.CM);
		//visualisation.setAlpha1(alpha1);
		return visualisation.start();
	}
	
	public Canvas getCanvasVisuCircleBlack() {
		VisuCircleBlack visualisation = new VisuCircleBlack();
		visualisation.setT1(this.t1);
		visualisation.setT3(this.t3);
		visualisation.setWidth(this.width);
		visualisation.setHeight(this.height);
		visualisation.setCP(this.CP);
		visualisation.setCM(this.CM);
		//visualisation.setAlpha1(alpha1);
		return visualisation.start();
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

	/*public int getAlpha1() {
		return alpha1;
	}

	public void setAlpha1(int alpha1) {
		this.alpha1 = alpha1;
	}*/

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSave() {
		return save;
	}

	public void setSave(boolean save) {
		this.save = save;
	}

}
