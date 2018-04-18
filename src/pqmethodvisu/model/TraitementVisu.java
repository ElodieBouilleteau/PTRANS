package pqmethodvisu.model;

import javafx.scene.paint.Color;
import java.util.ArrayList;

import javafx.scene.canvas.Canvas;

public class TraitementVisu {
	
	private static TraitementVisu uniqueInstance;
	private ArrayList<pqmethodvisu.model.Image> corpus;
	private Visualization visualization;
	
	private int width, height; //size parameters
	private int t1, t3; //picture size in px
	
	//colors and colors' features
	private Color CP, CM; //colorP = up part color and colorM = low part color
	
	private int factor1, factor2; //factor1 = main factor, factor2 = secondary factor
	private int alpha; //transparency for the little images, between 0 and 255
	private String savePath; //path to the saving directory
	private String format; //format of the file (png or jpg);
	private String name;
	private boolean save; //true is we want to save the visualization, false else
	
	
	private TraitementVisu() {
		this.width = 1200;
		this.height = 700;
		this.t1 = 80;
		this.t3 = 180;
		this.CP = Color.hsb(240,1,1);
		this.CM = Color.hsb(1,1,1);
		this.alpha = 100;
		this.factor1 = 1;
		this.factor2 = 2;
		this.savePath = "C:\\Users\\journ\\Images\\test.png";
		this.save = false;
	}
	
	public static TraitementVisu getInstance() {
		TraitementVisu.uniqueInstance = new TraitementVisu();
		return TraitementVisu.uniqueInstance;
	}
	
	public void setCanvasVisuCircle() {
		this.visualization = new VisuCircle(width, height, t1, t3, CP, CM, alpha, corpus, factor1, factor2);
	}
	
	public void setCanvasVisuCircleBlack() {
		this.visualization = new VisuCircleBlack(width, height, t1, t3, CP, CM, alpha, corpus, factor1, factor2);
	}
	
	public void setCanvasVisuRect() {
		this.visualization = new VisuRect(width, height, t1, t3, CP, CM, alpha, corpus, factor1, factor2);
	}
	
	public void setCanvasVisuRectBlack() {
		this.visualization = new VisuRectBlack(width, height, t1, t3, CP, CM, alpha, corpus, factor1, factor2);
	}
	
	public Canvas startVisu() {
		return visualization.start();
	}
	
	public void setCorpus(ArrayList<pqmethodvisu.model.Image> corpus) {
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

	/*public int getAlpha() {
		return alpha1;
	}

	public void setAlpha(int alpha) {
		this.alpha1 = alpha;
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