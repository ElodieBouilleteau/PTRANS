package pqmethodvisu.model;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Image {
	
	private StringProperty path;
	private StringProperty name;
	private ArrayList<Factor> listFactor;
	private IntegerProperty numberOfFactor;
	
	//picture constructor
	//when you create an picture, you put in only the path
	//after you add the other caracteristics
	public Image(String path) {
		this.path = new SimpleStringProperty(path);
	}
	
	//modify the name of the picture
	public void setName(String name) {
		this.name.set(name);
	}
	
	//acceder à la propriety du name
	public StringProperty NameProperty() {
		return name;
	}
	
	//get the path of the picture
	public String getPath() {
		return path.get();
	}
	
	//acceder à la propriety du path
	public StringProperty PathProperty() {
	    return path;
	}
	
	//get the number of factors for a picture
	public Integer getNumberOfFactor() {
		return numberOfFactor.get();
	}
	
	//acceder à la propriety du nombre de facteur
	public IntegerProperty FactorProperty() {
	    return numberOfFactor;
	}
	
	//add listFactor
	public void addListFactor(int numberOfFactor)
	{
		this.numberOfFactor = new SimpleIntegerProperty(numberOfFactor);
		this.listFactor = new ArrayList<Factor>(numberOfFactor);
		for (int i = 0; i < numberOfFactor; i++)
		{
			this.listFactor.add(new Factor(0,0));
		}
	}
	
	//add a factor into the factor list
	//position 0 matches with factor 1
	public void addFactor(int numberFactor, double zscore, int classementNumber)
	{
		this.listFactor.set(numberFactor-1,new Factor(zscore,classementNumber));
	}

}
