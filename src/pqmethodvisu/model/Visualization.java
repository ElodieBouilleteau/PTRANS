package pqmethodvisu.model;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;

public abstract class Visualization {
	protected int width, height;
	protected ArrayList<pqmethodvisu.model.Image> corpus;
	
	public Visualization(int width, int height, ArrayList<pqmethodvisu.model.Image> corpus) {
		this.width = width;
		this.height = height;
		this.corpus = corpus;
	}
    
	//This abstract method allow to launch the visualization
	//It must be implemented
    public abstract Canvas start();

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
	
    public ArrayList<pqmethodvisu.model.Image> getCorpus() {
		return corpus;
	}

	public void setCorpus(ArrayList<pqmethodvisu.model.Image> corpus) {
		this.corpus = corpus;
	}
}
