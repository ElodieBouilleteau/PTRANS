package pqmethodvisu.model;

import java.util.ArrayList;

public class Image {
	
	private String path;
	private String name;
	private ArrayList<Factor> listFactor;
	private Integer numberOfFactor;
	
	//picture constructor
	//when you create an picture, you put in only the path
	//after you add the other caracteristics
	public Image(String path) {
		this.path = path;
	}
	
	//modify the name of the picture
	public void setName(String name) {
		this.name = name;
	}
	
	//get the name of the picture
	public String getName() {
		return name;
	}
	
	//get the path of the picture
	public String getPath() {
		return path;
	}
	
	//get the number of factors for a picture
	public Integer getNumberOfFactor() {
		return numberOfFactor;
	}
	
	//add listFactor
	public void addListFactor(int numberOfFactor)
	{
		this.numberOfFactor = numberOfFactor;
		this.setListFactor(new ArrayList<Factor>(numberOfFactor));
		for (int i = 0; i < numberOfFactor; i++)
		{
			this.getListFactor().add(new Factor(0,0));
		}
	}
	
	//add a factor into the factor list
	//position 0 matches with factor 1
	public void addFactor(int numberFactor, double zscore, int classementNumber)
	{
		this.getListFactor().set(numberFactor-1,new Factor(zscore,classementNumber));
	}

	public ArrayList<Factor> getListFactor() {
		return listFactor;
	}

	public void setListFactor(ArrayList<Factor> listFactor) {
		this.listFactor = listFactor;
	}
}
