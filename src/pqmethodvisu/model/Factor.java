package pqmethodvisu.model;

public class Factor {
	
	private Double zscore;
	private Integer classementNumber;
	
	//factor constructor
	public Factor(double zscore, int classementNumber)
	{
		this.zscore = zscore;
		this.classementNumber = classementNumber;
	}
	
	//get the zscore of the factor
	public double getZscore() {
		return zscore;
	}
	
	//modify the zscore of the factor
	public void setZscore(double zscore) {
		this.zscore = zscore;
	}
	
	//get the classement of the factor for a picture
	public int getClassementNumber() {
		return classementNumber;
	}
	
	//modify the classement
	public void setClassementNumber(int classementNumber) {
		this.classementNumber = classementNumber;
	}
}
