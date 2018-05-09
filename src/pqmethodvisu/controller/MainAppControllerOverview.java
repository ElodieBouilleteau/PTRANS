package pqmethodvisu.controller;

import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pqmethodvisu.model.CollectionImage;
import pqmethodvisu.model.Model;
import pqmethodvisu.model.TraitementVisu;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MainAppControllerOverview implements Initializable {
	
	private Model model;
	
	private Stage mainStage;
	
	@FXML
	private AnchorPane ap;
	
	@FXML
	private MenuItem menuImportImmages;
	@FXML
	private MenuItem menuImportResults;
	@FXML
	private MenuItem menuImportResultsTXT;
	@FXML
	private MenuItem menuImportResultsCSV;
	@FXML
	private MenuItem menuSaveImage;
	@FXML
	private ComboBox<String> MajorFactorCombobox;
	@FXML
	private ComboBox<String> MinorFactorCombobox;
	@FXML
	private ComboBox<String> TypeVizuComboBox;
	@FXML
	private ComboBox<String> ColorVizuComboBox;
	@FXML
	private ColorPicker Color1Vizu;
	@FXML
	private ColorPicker Color2Vizu;
	@FXML
	private Slider WidthCursor;
	@FXML
	private Label WidthLabel;
	@FXML
	private Slider HeightCursor;
	@FXML
	private Label TransparentLabel;
	@FXML
	private Slider TransparentCursor;
	@FXML
	private Label HeightLabel;
	@FXML
	private Slider MaxSizeCursor;
	@FXML
	private Label MaxSizeLabel;
	@FXML
	private Slider MinSizeCursor;
	@FXML
	private Label MinSizeLabel;
	@FXML
	private Button ApplyButton;
	
	/*canvas for the visualization*/
	private ArrayList<Canvas> listVizuCanvas;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//initialize the number of vizu at the same time max
		int maxVizu = 4;
		
		//initializer of the visualization list and compteurVizu
		listVizuCanvas = new ArrayList<Canvas>(maxVizu);
		for(int i = 0; i < maxVizu; i++)
		{
			listVizuCanvas.add(null);
		}
		
		//initializer of comboBox about visu type
		TypeVizuComboBox.setItems(FXCollections.observableArrayList("Rectangle","Circle"));
		TypeVizuComboBox.getSelectionModel().select("Rectangle");
		
		//initializer of comboBox about color choice
		ColorVizuComboBox.setItems(FXCollections.observableArrayList("White","Black"));
		ColorVizuComboBox.getSelectionModel().select("White");
	
		//initializer of coulors of combobox
		Color1Vizu.setValue(Color.hsb(240,1,1));
		Color2Vizu.setValue(Color.hsb(1,1,1));
		
		//Screen size
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

		//initializer of size cursors
		WidthCursor.setBlockIncrement(1);
		WidthCursor.setMin(primaryScreenBounds.getWidth()/3);
		//primaryScreenBounds.getWidth()-primaryScreenBounds.getWidth()*0.05
		WidthCursor.setMax(primaryScreenBounds.getWidth()-primaryScreenBounds.getWidth()*0.05);
		WidthCursor.setValue(1200);
		HeightCursor.setBlockIncrement(1);
		HeightCursor.setMin(primaryScreenBounds.getHeight()/3);
		//primaryScreenBounds.getHeight()-primaryScreenBounds.getHeight()*0.05
		HeightCursor.setMax(primaryScreenBounds.getHeight()-primaryScreenBounds.getHeight()*0.05);
		HeightCursor.setValue(700);
		
		//Change labels's values when Cursor's value change
		WidthCursor.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,Number old_val, Number new_val) {
            		WidthCursor.setValue(Math.round(new_val.doubleValue()));
            		WidthLabel.setText(String.valueOf(Math.round(WidthCursor.getValue()*100)/100));
            		MaxSizeCursor.setMin(WidthCursor.getValue()*0.13);
            		MinSizeCursor.setMax(WidthCursor.getValue()*0.13);
            }
        });
		HeightCursor.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,Number old_val, Number new_val) {
            		HeightCursor.setValue(Math.round(new_val.doubleValue()));
            		HeightLabel.setText(String.valueOf(Math.round(HeightCursor.getValue()*100)/100));
            }
        });

		TransparentCursor.setValue(60);
		TransparentLabel.setText("60%");
		TransparentCursor.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov,Number old_val, Number new_val) {
				TransparentCursor.setValue(Math.round(new_val.doubleValue()));
				TransparentLabel.setText(String.valueOf(Math.round(TransparentCursor.getValue()*100)/100) + "%");
			}
		});
		
		//Initializer of max size cursors
		//MAx cursor min = width*0.13F et Min cursor max = width*0.13F
		MaxSizeCursor.setMin(1200*0.13);
		MaxSizeCursor.setValue(180);
		MaxSizeCursor.setBlockIncrement(1);
		//Change double value to integer value
		MaxSizeCursor.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
            		MaxSizeCursor.setValue(Math.round(new_val.doubleValue()));
            		MaxSizeLabel.setText(String.valueOf(Math.round(MaxSizeCursor.getValue()*100)/100));
            }
        });
		MinSizeCursor.setMax(1200*0.13);
		MinSizeCursor.setValue(80);
		MinSizeCursor.setBlockIncrement(1);
		//Change double value to integer value
		MinSizeCursor.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
            		MinSizeCursor.setValue(Math.round(new_val.doubleValue()));
            		MinSizeLabel.setText(String.valueOf(Math.round(MinSizeCursor.getValue()*100)/100));
            }
        });
		
		//Initializer labels's values
		WidthLabel.setText(String.valueOf(WidthCursor.getValue()));
		HeightLabel.setText(String.valueOf(HeightCursor.getValue()));
		MaxSizeLabel.setText(String.valueOf(MaxSizeCursor.getValue()));
		MinSizeLabel.setText(String.valueOf(MinSizeCursor.getValue()));
	}
	
	/*
	 * Add model to controler
	 */
	public void setModel(Model model) {
		this.model = model;
		CollectionImage collectionImages = new CollectionImage();
		model.setCollectionImage(collectionImages);
		TraitementVisu visualization = TraitementVisu.getInstance();
		model.setTraitementVisualization(visualization);
	}
	
	/*
	 * Function set the primary stage
	 */
	public void setMainStage(Stage mainStage)
	{
		this.mainStage = mainStage;
	}
	
	/*
	 * Poste popup in order to import folder's pictures
	 */
	@FXML
	private void affichePopupImportImage()
	{
		DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select the folder of pictures (format : .jpg)");
		File selectedDirectory = directoryChooser.showDialog(this.mainStage);
        
		if(selectedDirectory != null){
            Boolean test = model.getCollectionImage().importImage(selectedDirectory.getAbsolutePath());
			if (test) 
			{
				menuImportImmages.setDisable(true);
				menuImportResults.setDisable(false);
				menuImportResultsTXT.setDisable(false);
				menuImportResultsCSV.setDisable(false);
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Information");
    			alert.setHeaderText(null);
    			alert.setContentText("Importation success");
    			alert.showAndWait();
			}
			else
			{
				Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error");
    			alert.setHeaderText("Importation error");
    			alert.setContentText("The path of the folder is not correct.");
    			alert.showAndWait();
			}
        }
	}
	
	/*
	 * Block one of the values of combobox about factors
	 */
	@FXML
	private void onActionFactor() {
		//check si les valeurs des combobox ne sont pas null
		if ((MajorFactorCombobox.getValue()!=null)&&(MinorFactorCombobox.getValue()!=null))
		{
			int valueMajorFactor = Integer.parseInt(MajorFactorCombobox.getValue());
			int valueMinorFactor = Integer.parseInt(MinorFactorCombobox.getValue());
			//si les valeurs sont identiques
			if (valueMajorFactor==valueMinorFactor)
			{
				int FactorsNumber = model.getCollectionImage().getFactorsNumber();
				//on recrée la liste des valeurs de la combobox du facteur minor en enlevant la valeur selectionner dans le combobox du factor major
				ObservableList<String> newlist2 = FXCollections.observableArrayList();
				for (int j = 1; j<=FactorsNumber;j++)
				{
					if(j!=valueMajorFactor)
					{
						newlist2.add(Integer.toString(j));
					}
				}
				MinorFactorCombobox.getItems().clear();
				MinorFactorCombobox.setItems(newlist2);
				MinorFactorCombobox.getSelectionModel().select(newlist2.get(0));
			}
		}
	}
	
	/*
	 * Poste popup in order to import qmethod's results
	 */
	@FXML
	private void affichePopupImportResultTXT()
	{
		FileChooser fileChooser = new FileChooser();
        
        //Set extension filter
        FileChooser.ExtensionFilter extFiltertxt = new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFiltertxt);
        
        //Show import results file dialog
        File file = fileChooser.showOpenDialog(this.mainStage);
        
        if(file != null){
        	Boolean test = model.getCollectionImage().importDataTXT(file.getAbsolutePath());
			if (test) 
			{
				menuImportResults.setDisable(true);
				menuImportResultsTXT.setDisable(true);
				menuImportResultsCSV.setDisable(true);
				ApplyButton.setDisable(false);
				initComboboxFactors();
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Information");
    			alert.setHeaderText(null);
    			alert.setContentText("Importation success");
    			alert.showAndWait();
			}
			else
			{
				Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error");
    			alert.setHeaderText("Importation error");
    			alert.setContentText("The path of the file is not correct.");
    			alert.showAndWait();
			}
        }
	}

	/*
	 * Poste popup in order to import factors values
	 */
	@FXML
	private void affichePopupImportResultCSV()
	{
		FileChooser fileChooser = new FileChooser();
        
        //Set extension filter
        FileChooser.ExtensionFilter extFiltertxt = new FileChooser.ExtensionFilter("csv files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFiltertxt);
        
        //Show import results file dialog
        File file = fileChooser.showOpenDialog(this.mainStage);
        
        if(file != null){
        	Boolean test = model.getCollectionImage().importDataCSV(file.getAbsolutePath());
			if (test) 
			{
				menuImportResults.setDisable(true);
				menuImportResultsTXT.setDisable(true);
				menuImportResultsCSV.setDisable(true);
				ApplyButton.setDisable(false);
				initComboboxFactors();
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Information");
    			alert.setHeaderText(null);
    			alert.setContentText("Importation success");
    			alert.showAndWait();
			}
			else
			{
				Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error");
    			alert.setHeaderText("Importation error");
    			alert.setContentText("The path of the file is not correct.");
    			alert.showAndWait();
			}
        }
	}
	
	
	/*
	 * Function initialize comboBox of factors
	 */
	private void initComboboxFactors() {
		//initializer le comboBox du majorFactor
		int FactorsNumber = model.getCollectionImage().getFactorsNumber();
		ObservableList<String> list = FXCollections.observableArrayList();
		for (int i = 1; i<=FactorsNumber;i++)
		{
			list.add(Integer.toString(i));
		}
		MajorFactorCombobox.setItems(list);
		MajorFactorCombobox.getSelectionModel().select(list.get(0));
		//initializer le comboBox du minorFactor
		ObservableList<String> list2 = FXCollections.observableArrayList();
		for (int j = 2; j<=FactorsNumber;j++)
		{
			list2.add(Integer.toString(j));
		}
		MinorFactorCombobox.setItems(list2);
		MinorFactorCombobox.getSelectionModel().select(list2.get(0));
	}
	
	
	/*
	 * Function apply values and poste the visualization
	 */
	@FXML
	private void applyValue()
	{
		//Add elements to TraitementVisualization
		model.getTraitementVisualization().setCorpus(model.getCollectionImage().getCorpus());
		model.getTraitementVisualization().setWidth((int) WidthCursor.getValue());
		model.getTraitementVisualization().setHeight((int) HeightCursor.getValue());
		model.getTraitementVisualization().setAlpha((int) TransparentCursor.getValue());
		model.getTraitementVisualization().setFactor1(Integer.parseInt(MajorFactorCombobox.getValue()));
		model.getTraitementVisualization().setFactor2(Integer.parseInt(MinorFactorCombobox.getValue()));
		model.getTraitementVisualization().setCP(Color1Vizu.getValue());
		model.getTraitementVisualization().setCM(Color2Vizu.getValue());
		model.getTraitementVisualization().setT1((int) MinSizeCursor.getValue());
		model.getTraitementVisualization().setT3((int) MaxSizeCursor.getValue());
		
		//Creation of popup fenetre which contain visualization
		final Stage popup = new Stage();	//Création d'un stage
		Group root = new Group();
		if(TypeVizuComboBox.getValue()=="Rectangle"&ColorVizuComboBox.getValue()=="White")
		{
			model.getTraitementVisualization().setCanvasVisuRect();
		}
		else if(TypeVizuComboBox.getValue()=="Rectangle"&ColorVizuComboBox.getValue()=="Black")
		{
			model.getTraitementVisualization().setCanvasVisuRectBlack();
		}
		else if(TypeVizuComboBox.getValue()=="Circle"&ColorVizuComboBox.getValue()=="White")
		{
			model.getTraitementVisualization().setCanvasVisuCircle();
		}
		else if(TypeVizuComboBox.getValue()=="Circle"&ColorVizuComboBox.getValue()=="Black")
		{
			model.getTraitementVisualization().setCanvasVisuCircleBlack();
		}
		//ajouter la fenetre de la visualisation dans une liste de fenetre de canvas
		int numCase = 0;
		if (checkNotEmpty(listVizuCanvas)) {
			for (int i = 0 ; i < listVizuCanvas.size() ; i++)
			{
				if(listVizuCanvas.get(i) == null)	//on ajoute le canvas, dans la premiere case qui est null puis on conserve son numero
				{
					listVizuCanvas.set(i,model.getTraitementVisualization().startVisu());
					numCase = i+1;
					break;
				}
			}
			root.getChildren().add(listVizuCanvas.get(numCase-1));
			//option save active
			menuSaveImage.setDisable(false);
			//poste the scene of the visualization
			Scene popupImportResultsScene = new Scene(root, WidthCursor.getValue(), HeightCursor.getValue());	//Création d'une scène initialiser avec la VBox "popupImportResults", et de taille : w et h.
	        popup.setTitle("Visualisation"+numCase);	//mettre un titre au stage "popup"
	        popup.setScene(popupImportResultsScene);	//Ajouter la scene "popupImportResultsScene"
	        popup.show();	//Afficher
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Number of visualization");
			alert.setContentText("Number of visualization is not correct. More than 4.");
			alert.showAndWait();
		}
        //si on ferme une visu alors on decremente le compteur et on enleve le canvas correspondant
        popup.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent arg0) {
				int numCanvas = 0;
				Pattern p = Pattern.compile("\\d+");
				Matcher m = p.matcher(popup.getTitle());
				while(m.find())
					numCanvas = Integer.parseInt(m.group());
				listVizuCanvas.set(numCanvas-1,null);
				if(checkNull(listVizuCanvas)) {
					//option save desactive
					menuSaveImage.setDisable(true);
				}
			}
        });
	}
	
	/*
	 * function Save visualization
	 */
	@FXML
	private void saveVisualization() {
		Integer w = 400;	//Largeur de la fenetre popup de sauvegarde de la visualization
		Integer h = 100;	//Hauteur de la fenetre popup de sauvegarde de la visualization
		
		final Stage popup = new Stage();	//Création d'un stage
		popup.initModality(Modality.APPLICATION_MODAL);	//initialisation du stage "popup"
		
		Scene popupSaveVizu = new Scene(new Group(), w, h);
        
        final Label textselectvizu = new Label("Please select the visualisation");	
        textselectvizu.setFont(Font.font("Arial", FontWeight.BOLD, 12));	
        
        //observable list of visualization
        ObservableList<String> listVizu = FXCollections.observableArrayList();
        for (int i = 0; i < listVizuCanvas.size(); i++) {
        	if (listVizuCanvas.get(i) != null)
        	{
        		String nom = "Visualization"+(i+1);
        		listVizu.add(nom);
        	}
        }
        
        ComboBox<String> comboboxselectvizu = new ComboBox<String>(listVizu);
        comboboxselectvizu.getSelectionModel().select(listVizu.get(0));
       
        final Button save = new Button("Save");
        save.setPrefHeight(30);
        save.setPrefWidth(60);
        
		GridPane grid = new GridPane();
		grid.setHgap(20);
        grid.setVgap(10);
        grid.setPadding(new Insets(15, 40, 10, 40));
        grid.add(textselectvizu, 0, 0);
        grid.add(comboboxselectvizu, 1, 0);
        grid.add(save, 1, 2);
        
        Group root = (Group)popupSaveVizu.getRoot();
        root.getChildren().add(grid);
        
        popup.setTitle("Qmethod save image");	//mettre un titre au stage "popup"
        popup.setScene(popupSaveVizu);	//Ajouter la scene "popupImportResultsScene"
        popup.show();	//Afficher
        
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	
            	FileChooser fileChooser = new FileChooser();
                
                //Set extension filter
                FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
                FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
                fileChooser.getExtensionFilters().add(extFilterpng);
                fileChooser.getExtensionFilters().add(extFilterjpg);
                
                //Show save file dialog
                File file = fileChooser.showSaveDialog(popup);
                
                int numCanvas = 0;
				Pattern p = Pattern.compile("\\d+");
				Matcher m = p.matcher(comboboxselectvizu.getValue());
				while(m.find())
					numCanvas = Integer.parseInt(m.group());
                
				Canvas canvas = listVizuCanvas.get(numCanvas-1);
				int canvasWidth = (int) canvas.getWidth();
				int canvasHeight = (int) canvas.getHeight();
				
                if(file != null){
                    try {
                    	SnapshotParameters parameters = new SnapshotParameters();
                        parameters.setFill(Color.TRANSPARENT);
                        WritableImage writableImage = new WritableImage(canvasWidth, canvasHeight);
                        canvas.snapshot(parameters, writableImage);                       RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                        ImageIO.write(renderedImage, "png", file);
                        popup.close();
                    } catch (IOException ex) {
                        Logger.getLogger(MainAppControllerOverview.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
       
	}
	
	/*
	 * Check if the list contain only null values
	 */
	private boolean checkNull(ArrayList<Canvas> List) {
		boolean bool = true;
		for (int i = 0; i < List.size(); i++)
		{
			if(List.get(i) != null)
			{
				bool = false;
			}
		}
		return bool;
	}
	
	/*
	 * Check if the list contain only canvas
	 */
	private boolean checkNotEmpty(ArrayList<Canvas> List) {
		boolean bool = false;
		for (int i = 0; i < List.size(); i++)
		{
			if(List.get(i) == null)
			{
				bool = true;
			}
		}
		return bool;
	}
	
	/*
	 * Help function
	 */
	@FXML
	private void helpfunction() {
		Integer w = 550;	//Largeur de la fenetre popup d'aide
		Integer h = 400;	//Hauteur de la fenetre popup d'aide
		
		final Stage popup = new Stage();	//Création d'un stage
		
		TextFlow flow = new TextFlow();
		/*Step 1*/
        Text title1 = new Text(">> Etape 1 : importer le dossier d'images \n\n");
        title1.setStyle("-fx-fill: #4F8A10;-fx-font-weight:bold;");
        Text etape1txt = new Text(">> Vérifier que le dossier contient des images numéroté de 1 à n. \n"
        		+ ">> Vérifier que les images sont au format .JPG. \n"
        		+ ">> Allez dans le menu \"File\" puis cliquez sur \"Import images...\" \n"
        		+ ">> Sélectionnez le dossier contenant les images. \n\n");
        Text error1 = new Text(">> Un message d'erreur s'affiche si vous n'avez pas selectionner de dossier ou si le dossier selectionné est incorrecte. \n\n");
        error1.setStyle("-fx-fill: RED;-fx-font-weight:normal;");
        /*Step 2*/
        Text title2 = new Text(">> Etape 2 : importer les zscores des images \n\n");
        title2.setStyle("-fx-fill: #4F8A10;-fx-font-weight:bold;");
        Text subtitle2txt = new Text("> Soit importer les résultats de sortie du logiciel \"Qmethod\" \n");
        subtitle2txt.setStyle("-fx-fill: #4B108A;-fx-font-weight:bold;");
        Text etape2txt = new Text(">> Allez dans le menu \"File\" puis sur \"Import results...\" et cliquer sur \"Import results file TXT\"\n"
        		+ ">> Sélectionnez le fichier contenant les résultats. \n"
        		+ ">> Vérifier que le fichier des résultats correspond à ");
        Hyperlink hyperlink2txt = new Hyperlink("resultats-pqmethod-exemple");
        hyperlink2txt.setOnAction(new EventHandler<ActionEvent>()
    	{

			@Override
    		public void handle(ActionEvent event)
    		{
				TextFlow flow = new TextFlow();
    			String path = "exempleHelpFunction\\résultats-pqmethod.txt";
    			InputStream ips = null;
				try {
					ips = new FileInputStream(path);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
    			InputStreamReader ipsr=new InputStreamReader(ips);
    			BufferedReader br=new BufferedReader(ipsr);
    			String ligne;
    			int i = 1;
    			try {
					while ((ligne=br.readLine())!=null){
						Text txt = new Text(i+"\t"+ligne+"\n");
						flow.getChildren().add(txt);
						i++;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
    			flow.setDisable(false);
    			ScrollPane scrollpane = new ScrollPane();
    			scrollpane.setContent(flow); // Set content for ScrollPane
    			scrollpane.setVbarPolicy(ScrollBarPolicy.ALWAYS); // Setting vertical scroll bar is never displayed.
    			scrollpane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED); // Horizontal scroll bar is only displayed when needed
    			final Stage pqmethodex = new Stage();	//Création d'un stage
    			pqmethodex.initModality(Modality.APPLICATION_MODAL);	//initialisation du stage "popup"
    			Scene pqmethodexScene = new Scene(scrollpane, 650, 600);
    			pqmethodex.setTitle("pdmethod-results-exemple");	//mettre un titre au stage
    			pqmethodex.setScene(pqmethodexScene);	//Ajouter la scene
    			pqmethodex.show();	//Afficher
    			
    		}
    	});
        Text error2txt = new Text("\n>> Un message d'erreur s'affiche si vous n'avez pas selectionner de fichier ou si le fichier selectionné est incorrecte.\n\n");
        error2txt.setStyle("-fx-fill: RED;-fx-font-weight:normal;");
        Text subtitle2csv = new Text("> Soit importer les zscores des images contenu dans un tableau au format .CSV \n");
        subtitle2csv.setStyle("-fx-fill: #4B108A;-fx-font-weight:bold;");
        Text etape2csv = new Text(">> Allez dans le menu \"File\" puis \"Import results...\" et cliquer sur \"Import results file CSV\"\n"
        		+ ">> Sélectionnez le fichier contenant les zscores. \n"
        		+ ">> Vérifier que le séparateur de texte est \";\".\n"
        		+ ">> Vérifier que les nombres ont des \",\" comme séparateur.\n"
        		+ ">> Vérifier que le nombre de ligne correspond au nombre d'images importer via le dossier.\n"
        		+ ">> Les numéros de ligne corresponde au nom de l'image contenu dans le dossier.\n"
        		+ ">> Vérifier que le fichier des résultats correspond à ");
        Hyperlink hyperlink2csv = new Hyperlink("resultats-csv-exemple");
        hyperlink2csv.setOnAction(new EventHandler<ActionEvent>()
    	{

			@Override
    		public void handle(ActionEvent event)
    		{
    			GridPane grid = new GridPane();
    			//grid.setHgap(20);
    	        //grid.setVgap(10);
    	        grid.setPadding(new Insets(15, 40, 10, 40));
    			String path = "exempleHelpFunction\\resultsCSV.csv";
    			InputStream ips = null;
				try {
					ips = new FileInputStream(path);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
    			InputStreamReader ipsr=new InputStreamReader(ips);
    			BufferedReader br=new BufferedReader(ipsr);
    			String ligne;
    			String[] parseLigne;
    			try {
    				int numline = 0;
					while ((ligne=br.readLine())!=null){
						parseLigne = ligne.split(";");
						int numberOfFactor = parseLigne.length; //catch the number of factor
						Text col1 = new Text(" "+parseLigne[0]+" ");
						grid.add(col1, 0, numline);
						for(int i = 1; i < numberOfFactor; i++)
						{
							Text coli = new Text(" "+parseLigne[i]+" ");
							grid.add(coli, i, numline);
						}
						numline++;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
    			grid.setStyle("-fx-background-color: white; -fx-grid-lines-visible: true");
    			ScrollPane scrollpane = new ScrollPane();
    			scrollpane.setContent(grid); // Set content for ScrollPane
    			scrollpane.setVbarPolicy(ScrollBarPolicy.ALWAYS); // Setting vertical scroll bar is never displayed.
    			scrollpane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED); // Horizontal scroll bar is only displayed when needed
    			final Stage pqmethodex = new Stage();	//Création d'un stage
    			pqmethodex.initModality(Modality.APPLICATION_MODAL);	//initialisation du stage "popup"
    			Scene pqmethodexScene = new Scene(scrollpane, 540, 600);
    			pqmethodex.setTitle("results-csv-exemple");	//mettre un titre au stage
    			pqmethodex.setScene(pqmethodexScene);	//Ajouter la scene
    			pqmethodex.show();	//Afficher
    			
    		}
    	});
        Text error2csv = new Text("\n>> Un message d'erreur s'affiche si vous n'avez pas selectionner de fichier ou si le fichier selectionné est incorrecte.\n\n");
        error2csv.setStyle("-fx-fill: RED;-fx-font-weight:normal;");
        /*Step 3*/
        Text title3 = new Text(">> Etape 3 : afficher la visualisation \n\n");
        title3.setStyle("-fx-fill: #4F8A10;-fx-font-weight:bold;");
        Text etape3txt = new Text(">> Changer les paramètres de la visualisation.\n\n"
        		+ "> Major correspond au facteur principal (Vertical). \n"
        		+ "> Minor correspond au facteur secondaire (Horizontal). \n"
        		+ "> Visualization Style correspond au style de la visualisation (Position des images en Rectangle ou en Cercle). \n"
        		+ "> Color Style correspond au style de couleur de fond de la visualisation (Noir ou Blanc). \n"
        		+ "> Color1 correspond à la couleur de la partie supérieur de la visualisation (Zscore positif). \n"
        		+ "> Color2 correspond à la couleur de la partie inférieur de la visualisation (Zscore négatif). \n"
        		+ "> Width correspond à la largeur de la visualisation (ne peut pas dépaser la taille de l'écran). \n"
        		+ "> Height correspond à la hauteur de la visualisation (ne peut pas dépaser la taille de l'écran). \n"
        		+ "> Transparence correspond au dégré de transparence des images (degré 0 : les images de zscores à 0 disparaise). \n"
        		+ "> MaxSize correspond à la taille des images centrales de la visualisation. \n"
        		+ "> MinSize correspond à la taille des images en périphérie de la visualisation. \n\n"
        		+ ">> Cliquez sur la bouton \"Apply\" \n\n");
        Text error3 = new Text(">> ATTENTION : Vous ne pouvez afficher que seulement 4 visualisations en même temps !\n"
        		+">> Un message d'erreur s'affichera si vous essayer dans afficher plus de 4.\n");
        error3.setStyle("-fx-fill: RED;-fx-font-weight:normal;");
        Text complement3 = new Text(">> Les visualisations sont numérotés de 1 à 4.\n"
        		+"> Exemple : vous avez 3 visualisations d'affichées, vous supprimez la visualisation 2,\n" 
        		+"> si vous en affichez une nouvelle alors celle-ci aura pour nom \"visualisation 2\".\n\n");
        /*Step 4*/
        Text title4 = new Text(">> Etape 4 : Sauvegarder une visualisation \n\n");
        title4.setStyle("-fx-fill: #4F8A10;-fx-font-weight:bold;");
        Text etape4txt = new Text(">> Allez dans le menu \"Save Image\"\n"
        		+ ">> Une fenètre de choix s'ouvrira.\n"
        		+ ">> Sélectionner la visualisation que vous souhaiter sauvegarder.\n"
        		+ ">> Cliquez sur le bouton \"Save\"\n"
        		+ ">> Choisissez le dossier de sauvegarde.\n"
        		+ ">> Choisissez le nom de la visualisation et son format (PNG ou JPG).\n"
        		+ ">> Cliquez sur sauvegarder.\n");
        
        flow.getChildren().addAll(title1,etape1txt,error1
        		,title2,subtitle2txt,etape2txt,hyperlink2txt,error2txt
        		,subtitle2csv,etape2csv,hyperlink2csv,error2csv
        		,title3,etape3txt,error3,complement3
        		,title4,etape4txt);
		
		ScrollPane scrollpane = new ScrollPane();
		
		scrollpane.setContent(flow); // Set content for ScrollPane
		scrollpane.setVbarPolicy(ScrollBarPolicy.ALWAYS); // Setting vertical scroll bar is never displayed.
		scrollpane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED); // Horizontal scroll bar is only displayed when needed
		
		Scene popupHelp = new Scene(scrollpane, w, h);
		popup.setTitle("Help");	//mettre un titre au stage "popup"
        popup.setScene(popupHelp);	//Ajouter la scene "popupHelp"
        popup.show();	//Afficher
	}
}
