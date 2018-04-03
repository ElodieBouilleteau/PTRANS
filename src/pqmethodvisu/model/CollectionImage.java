package pqmethodvisu.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CollectionImage {
	
	private ArrayList<Image> corpus;
	private IntegerProperty imagesNumber;

	//function importation picture with the path of the pictures
	//the path of the pictures is path of the folder with the number of the picture at the end
	public void importImage(String pathFolder)
	{
		File repertoire = new File(pathFolder);
		this.imagesNumber = new  SimpleIntegerProperty(repertoire.listFiles().length);
		this.corpus = new ArrayList<Image> (imagesNumber.get());
		for (int i = 0; i < imagesNumber.get(); i++)
		{
			this.corpus.add(new Image(pathFolder+"\\"+(i+1)+".jpg"));
		}
	}
	
	//function importation result from qmethod
	public void importData(String pathData) {
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
			//pass to the next line where the zscore and the classement number is
			ligne=br.readLine();
			for (int i = 0; i < imagesNumber.get(); i++)
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
					this.corpus.get(i).addFactor(j, Double.parseDouble(zscore.replaceAll(" ","")), Integer.parseInt(classement.replaceAll(" ","")));
				}
			}

			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
}
