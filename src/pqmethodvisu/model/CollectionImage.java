package pqmethodvisu.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class CollectionImage {
	
	private static String osName = System.getProperty("os.name").toLowerCase();
	
	private ArrayList<Image> corpus;
	private Integer imagesNumber;
	private Integer factorsNumber;
	
	//function importation picture with the path of the pictures
	//the path of the pictures is path of the folder with the number of the picture at the end
	public boolean importImage(String pathFolder)
	{
		boolean test = true;
		File repertoire = new File(pathFolder);
		this.imagesNumber = repertoire.listFiles().length;
		this.corpus = new ArrayList<Image> (imagesNumber);
		for (int i = 0; i < imagesNumber; i++)
		{
			String slash = null;
            osName = osName.toLowerCase(Locale.ENGLISH);
            if (osName.contains("windows")) {
            	slash = "\\";
            } else if (osName.contains("linux")
                    || osName.contains("mpe/ix")
                    || osName.contains("freebsd")
                    || osName.contains("irix")
                    || osName.contains("digital unix")
                    || osName.contains("unix")
                    || osName.contains("mac os")) {
            	slash = "/";
            }
            
            File image = new File(pathFolder+slash+(i+1)+".jpg");;
			if (image.isFile())
			{
				this.corpus.add(new Image(pathFolder+slash+(i+1)+".jpg"));
			} else {
				test = false;
				break;
			}
		}
		return test;
	}
	
	//function importation result from qmethod
	public Boolean importDataTXT(String pathData) {
		try{
			InputStream ips=new FileInputStream(pathData);
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			//take the line of the zscores of the pictures
			while ((ligne=br.readLine())!=null){
				if (ligne.contains("Factor Scores with Corresponding Ranks"))
					break;
			}
			//pass 2 empty line
			ligne=br.readLine();
			ligne=br.readLine();
			//catch the number of factor
			//this is the 2 ending caractere of the line
			int numberOfFactor = Integer.parseInt(ligne.substring(ligne.length()-1, ligne.length()));
			//add to the local variable
			//pass to the next line where the zscore and the classement number is
			ligne=br.readLine();
			for (int i = 0; i < imagesNumber; i++)
			{
				ligne=br.readLine();
				int x = 5;
				//name of the picture (5eme caractere to 60eme caractere of the line)
				//the same longer for every picture
				String name = ligne.substring(x, x+55);
				//add the name to the picture number i
				this.corpus.get(i).setName(name);
				//add the list of factor to the picture number i
				this.corpus.get(i).addListFactor(numberOfFactor);
				int borne1 = ligne.length()-2;
				int borne2 = ligne.length();
				for (int j = numberOfFactor; j > 0; j--)
				{
					String classement = ligne.substring(borne1,borne2);
					String zscore = ligne.substring(borne1-7,borne2-4);
					borne1 -= 11;
					borne2 -= 11;
					//add factor to the picture
					this.corpus.get(i).addFactorClass(j, Double.parseDouble(zscore.replaceAll(" ","")), Integer.parseInt(classement.replaceAll(" ","")));
				}
			}
			
			br.close();
			return true;
		}		
		catch (Exception e){
			return false;
		}
	}

	//function importation factors values from csv file
	public Boolean importDataCSV(String pathData) {
		try{
			InputStream ips=new FileInputStream(pathData);
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			String[] parseLigne;
			//pass the colonne ligne
			ligne=br.readLine();
			parseLigne = ligne.split(";");
			//catch the number of factor
			int numberOfFactor = parseLigne.length-1;
			//add to the local variable
			setFactorsNumber(numberOfFactor);
			//parcour all ligne
			int cptImage = 0;
			while ((ligne=br.readLine())!=null){
				parseLigne = ligne.split(";");
				String name = parseLigne[0];
				//add the name to the picture number i
				this.corpus.get(cptImage).setName(name);
				//add the list of factor to the picture number i
				this.corpus.get(cptImage).addListFactor(numberOfFactor);
				for(int i = 0; i < numberOfFactor; i++)
				{
					String zscore = parseLigne[i+1].replaceAll(" ","");
					//add factor to the picture, set classement to 0
					this.corpus.get(cptImage).addFactorClass(i+1, Double.parseDouble(zscore.replaceAll(",",".")),0);
				}
				cptImage++;
			}
			//calculate the classement
			calculateClassement();
			br.close();
			return true;
		}		
		catch (Exception e){
			System.out.println(e);
			return false;
		}
	}
	
	//calculate the classement and add to the corpus
	private void calculateClassement() {
		ArrayList<ArrayList<Double>> factors = new ArrayList<ArrayList<Double>>(this.factorsNumber);
		for(int j = 0; j < this.factorsNumber; j++)
		{
			ArrayList<Double> factor = new ArrayList<Double>(this.imagesNumber);
			for(int i = 0; i < this.imagesNumber; i++)
			{
				factor.add(this.corpus.get(i).getListFactor().get(j).getZscore());
			}
			factors.add(factor);
		}
		for(int j = 0; j < this.factorsNumber; j++)
		{
			ArrayList<Double> factorTri = factors.get(j);
			Collections.sort(factorTri,Collections.reverseOrder());
			for(int i = 0; i < this.imagesNumber; i++)
			{
				double zscore = this.corpus.get(i).getListFactor().get(j).getZscore();
				int count = count(factorTri,zscore);
				int classement = factorTri.indexOf(zscore)+count;
				this.corpus.get(i).getListFactor().get(j).setClassementNumber(classement);
			}
			factorTri.clear();
		}
	}
	
	//count element = elmt in a arraylist
	private int count(ArrayList<Double> array,double elmt) 
   {
      int count = 0;
      for(Double d : array)
      {
         if(d == elmt)
         {
            count++;
         }
      }
      return count;
   }
	
	//get the numbers of factors
	public Integer getFactorsNumber() {
		return factorsNumber;
	}

	//add the number of factors
	public void setFactorsNumber(Integer factorsNumber) {
		this.factorsNumber = factorsNumber;
	}
	
	//get the corpus of image
	public ArrayList<Image> getCorpus()
	{
		return corpus;
	}
}
