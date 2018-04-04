package pqmethodvisu.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Factor {
	
	private DoubleProperty zscore;
	private IntegerProperty classementNumber;
	
	//factor constructor
	public Factor(double zscore, int classementNumber)
	{
		this.zscore = new SimpleDoubleProperty(zscore);
		this.classementNumber = new SimpleIntegerProperty(classementNumber);
	}
	
	//get the zscore of the factor
	public double getZscore() {
		return zscore.get();
	}
	
	//modify the zscore of the factor
	public void setZscore(double zscore) {
		this.zscore.set(zscore);
	}
	
	//acceder à la propriety du zscore
	public DoubleProperty ZscoreProperty() {
        return zscore;
    }
	
	//get the classement of the factor for a picture
	public int getClassementNumber() {
		return classementNumber.get();
	}
	
	//modify the classement
	public void setClassementNumber(int classementNumber) {
		this.classementNumber.set(classementNumber);
	}

	//acceder à la propriety du classement
	public IntegerProperty ClassementProperty() {
	   return classementNumber;
	}
}
