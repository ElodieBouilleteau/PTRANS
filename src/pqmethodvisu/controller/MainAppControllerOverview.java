package pqmethodvisu.controller;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
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
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
		WidthCursor.setMax(primaryScreenBounds.getWidth()-primaryScreenBounds.getWidth()*0.05);
		WidthCursor.setValue(1200);
		HeightCursor.setBlockIncrement(1);
		HeightCursor.setMin(primaryScreenBounds.getHeight()/3);
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
				//on recr�e la liste des valeurs de la combobox du facteur minor en enlevant la valeur selectionner dans le combobox du factor major
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
		final Stage popup = new Stage();	//Cr�ation d'un stage
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
			Scene popupImportResultsScene = new Scene(root, WidthCursor.getValue(), HeightCursor.getValue());	//Cr�ation d'une sc�ne initialiser avec la VBox "popupImportResults", et de taille : w et h.
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
		
		final Stage popup = new Stage();	//Cr�ation d'un stage
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
}
