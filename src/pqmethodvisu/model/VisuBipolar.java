package pqmethodvisu.model;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class VisuBipolar extends Visualization{
	
	protected int t1, t2, t3;
	protected Color CP, CM;
	protected double alpha;
	protected double HP, SP, BP, HM, SM, BM;
	protected ArrayList<Integer> G1P, G2P, G3P, G1M, G2M, G3M, G0;
	protected int factor1, factor2;

	public VisuBipolar(int width, int height, int t1, int t3, Color CP, Color CM, double alpha,
			ArrayList<Image> corpus, int factor1, int factor2) {
		super(width, height, corpus);
		this.t1 = t1;
		this.t3 = t3;
		this.CP = CP;
		this.CM = CM;
		this.alpha = alpha;
		this.factor1 = factor1;
		this.factor2 = factor2;
		this.HP = CP.getHue();
		this.HM = CM.getHue();
		this.SP = CP.getSaturation();
		this.SM = CM.getSaturation();
		this.BP = CP.getBrightness();
		this.BM = CM.getBrightness();
		this.t2 = (int) (width*0.13);
		setGroupF1();
	}
	
	@Override
	public Canvas start() {
		return null;
	}
	
	private void setGroupF1() {
		/*This method allows to set up the group of images according to the two factors*/
		double zscore;
		int compteur = 1;
		for(Image i : corpus) {
			zscore = i.getListFactor().get(factor1-1).getZscore();
			if (zscore > 0 && zscore <= 1) {
				this.G1P.add(compteur);
			}
			else if(zscore > 1 && zscore <= 1.8) {
				this.G2P.add(compteur);
			}
			else if(zscore > 1.8 && zscore <= 2) {
				this.G3P.add(compteur);
			}
			else if(zscore < 0 && zscore >= -1) {
				this.G1M.add(compteur);
			}
			else if(zscore < -1 && zscore >= -1.8) {
				this.G2M.add(compteur);
			}
			else if(zscore < -1.8 && zscore >= -2) {
				this.G3M.add(compteur);
			}
			else if(zscore == 0) {
				this.G0.add(compteur);
			}
			compteur++;
		}
		compteur = 0;
		while (!(this.G0.isEmpty())) {
			if (compteur%2 == 0) {
				this.G1P.add(this.G0.get(0));
				this.G0.remove(0);
			}
			else {
				this.G2P.add(this.G0.get(0));
				this.G0.remove(0);
			}
		}
		setGroupeF2();
	}
	
	private void setGroupeF2() {
		/*This method allows to sort the group in comparison to the factor 2*/
		quickSort(G1P, 0, G1P.size(), factor2);
		quickSort(G2P, 0, G2P.size(), factor2);
		quickSort(G3P, 0, G3P.size(), factor2);
		quickSort(G1M, 0, G1M.size(), factor2);
		quickSort(G2M, 0, G2M.size(), factor2);
		quickSort(G3M, 0, G3M.size(), factor2);
	}
	
	private int partition(ArrayList<Integer> G, int left, int right, int factor){
		/*Partitionner for the quicksort*/
	      int i = left, j = right;
	      int tmp;
	      double pivot = getZscore((left + right) / 2, factor);    
	      while (i <= j) {
	            while (getZscore(i, factor) < pivot)
	                  i++;
	            while (getZscore(j, factor) > pivot)
	                  j--;
	            if (i <= j) {
	                  tmp = G.get(i);
	                  G.set(i, G.get(j));
	                  G.set(j, tmp);
	                  i++;
	                  j--;
	            }
	      };
	      return i;
	}
	 
	private void quickSort(ArrayList<Integer> G, int left, int right, int factor) {
	      int index = partition(G, left, right, factor);
	      if (left < index - 1)
	            quickSort(G, left, index - 1, factor);
	      if (index < right)
	            quickSort(G, index, right, factor);
	}

	protected double resizew(double w, double h, double d){
      double H = Math.sqrt(w*w + h*h);
  	  return w/H*d;
  	}

	protected double resizeh(double w, double h, double d){
  	  double H = Math.sqrt(w*w + h*h);
  	  return h/H*d;
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

	public Color getCP() {
		return CP;
	}

	public void setCP(Color cP) {
		CP = cP;
	}

	public Color getCM() {
		return CM;
	}

	public void setCM(Color cM) {
		CM = cM;
	}

	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}
	
	private double getZscore(int i, int factor) {
		return this.corpus.get(i).getListFactor().get(factor).getZscore();
	}
	
	public void setGroupes(ArrayList<Integer> G1P, ArrayList<Integer> G2P, ArrayList<Integer> G3P, ArrayList<Integer> G1M, ArrayList<Integer> G2M, ArrayList<Integer> G3M)
	{
		this.G1P = G1P;
		this.G2P = G2P;
		this.G3P = G3P;
		this.G1M = G1M;
		this.G2M = G2M;
		this.G3M = G3M;
	}
}

}
