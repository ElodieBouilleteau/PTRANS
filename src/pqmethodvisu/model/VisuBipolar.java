package pqmethodvisu.model;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class VisuBipolar extends Visualization {
	
	protected int t1, t2, t3;
	protected Color CP, CM;
	protected double alpha;
	protected double HP, SP, BP, HM, SM, BM;
	//contient le numero de l'image
	private ArrayList<Integer> G1PI, G2PI, G3PI, G1MI, G2MI, G3MI, G0I;
	protected ArrayList<String> G1P, G2P, G3P, G1M, G2M, G3M, G0;
	protected int factor1, factor2;

	public VisuBipolar(int width, int height, int t1, int t3, Color CP, Color CM, double alpha,
			ArrayList<Image> corpus, int factor1, int factor2) {
		super(width, height, corpus);
		this.t1 = t1;
		this.t3 = t3;
		this.CP = CP;
		this.CM = CM;
		this.alpha = alpha/100;
		this.factor1 = factor1;
		this.factor2 = factor2;
		this.HP = CP.getHue();
		this.HM = CM.getHue();
		this.SP = CP.getSaturation();
		this.SM = CM.getSaturation();
		this.BP = CP.getBrightness();
		this.BM = CM.getBrightness();
		this.t2 = (int) (width*0.13);
		this.G1PI = new ArrayList<Integer>();
		this.G1MI = new ArrayList<Integer>();
		this.G2PI = new ArrayList<Integer>();
		this.G2MI = new ArrayList<Integer>();
		this.G3PI = new ArrayList<Integer>();
		this.G3MI = new ArrayList<Integer>();
		this.G0I = new ArrayList<Integer>();
		this.G1P = new ArrayList<String>();
		this.G1M = new ArrayList<String>();
		this.G2P = new ArrayList<String>();
		this.G2M = new ArrayList<String>();
		this.G3P = new ArrayList<String>();
		this.G3M = new ArrayList<String>();
		this.G0 = new ArrayList<String>();
		setGroupF1();
		convertGroupToStringList();
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
				this.G1PI.add(compteur);
			}
			else if(zscore > 1 && zscore <= 1.8) {
				this.G2PI.add(compteur);
			}
			else if(zscore > 1.8) {
				this.G3PI.add(compteur);
			}
			else if(zscore < 0 && zscore >= -1) {
				this.G1MI.add(compteur);
			}
			else if(zscore < -1 && zscore >= -1.8) {
				this.G2MI.add(compteur);
			}
			else if(zscore < -1.8) {
				this.G3MI.add(compteur);
			}
			else if(zscore == 0) {
				this.G0I.add(compteur);
			}
			compteur++;
		}
		compteur = 0;
		while (!(this.G0I.isEmpty())) {
			if (compteur%2 == 0) {
				this.G1PI.add(this.G0I.get(0));
				this.G0I.remove(0);
			}
			else {
				this.G1MI.add(this.G0I.get(0));
				this.G0I.remove(0);
			}
			compteur++;
		}
		setGroupeF2();
	}
	
	private void setGroupeF2() {
		/*This method allows to sort the group in comparison to the factor 2*/
		quickSort(G1PI, 0, G1PI.size()-1, factor2);
		quickSort(G2PI, 0, G2PI.size()-1, factor2);
		quickSort(G3PI, 0, G3PI.size()-1, factor2);
		quickSort(G1MI, 0, G1MI.size()-1, factor2);
		quickSort(G2MI, 0, G2MI.size()-1, factor2);
		quickSort(G3MI, 0, G3MI.size()-1, factor2);
	}
	
	private int partition(ArrayList<Integer> G, int left, int right, int factor){
		  /*Partitionner for the quicksort*/
	      int i = left, j = right;
	      Integer tmp;
	      //recuperer le numéro de l'image à la moitier de la liste
	      double pivot = getClassement(G.get((left + right) / 2), factor);
	      while (i <= j) {
	    	  while (getClassement(G.get(i), factor) < pivot)
	               	i++;
	          while (getClassement(G.get(j), factor) > pivot)
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

	/*
	 * Convert groups of integer into groups of picture name (string)
	 */
	private void convertGroupToStringList() {
		this.G1P = convertListIntToString(G1PI);
		this.G1M = convertListIntToString(G1MI);
		this.G2P = convertListIntToString(G2PI);
		this.G2M = convertListIntToString(G2MI);
		this.G3P = convertListIntToString(G3PI);
		this.G3M = convertListIntToString(G3MI);
		this.G0 = convertListIntToString(G0I);
	}
	
	/*
	 * Convert list of integer into list of picture name (string)
	 */
	private ArrayList<String> convertListIntToString(ArrayList<Integer> list) {
		ArrayList<String> newList = new ArrayList<String>(list.size());
		for(Integer i : list) {
			//take the number of the picture - 1 because list start to 0
			newList.add(this.corpus.get(i-1).getName());
		}
		return newList;
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
	
	private int getClassement(int i, int factor) {
		//take the factor - 1 because listfactor start to 0
		return this.corpus.get(i-1).getListFactor().get(factor-1).getClassementNumber();
	}
	
}
