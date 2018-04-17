package pqmethodvisu.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JPanel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pqmethodvisu.model.CollectionImage;
import pqmethodvisu.model.Model;
import pqmethodvisu.model.VisuCircle;
import pqmethodvisu.model.TraitementVisu;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MainAppControllerOverview implements Initializable {
	
	private Model model;
	
	@FXML
	private AnchorPane ap;
	
	@FXML
	private MenuItem menuImportImmages;
	@FXML
	private MenuItem menuImportResults;
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
	private Label HeightLabel;
	@FXML
	private Slider MaxSizeCursor;
	@FXML
	private Label MaxSizeLabel;
	@FXML
	private Slider MinSizeCursor;
	@FXML
	private Label MinSizeLabel;
	
	/*canvas for the visualization*/
	private Canvas visu;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//initializer of comboBox about visu type
		TypeVizuComboBox.setItems(FXCollections.observableArrayList("Rectangle","Circle"));
		TypeVizuComboBox.getSelectionModel().select("Rectangle");
		
		//initializer of comboBox about color choice
		ColorVizuComboBox.setItems(FXCollections.observableArrayList("ColorFull","Black/White"));
		ColorVizuComboBox.getSelectionModel().select("ColorFull");
	
		//initializer of coulors of combobox
		Color1Vizu.setValue(Color.hsb(240,1,1));
		Color2Vizu.setValue(Color.hsb(1,1,1));
		
		//initializer of size cursors
		WidthCursor.setBlockIncrement(1);
		WidthCursor.setMin(500);
		WidthCursor.setValue(1200);
		HeightCursor.setBlockIncrement(1);
		HeightCursor.setMin(100);
		HeightCursor.setValue(700);
		
		//Change labels's values when Cursor's value change
		WidthCursor.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,Number old_val, Number new_val) {
            		WidthCursor.setValue(Math.round(new_val.doubleValue()));
            		WidthLabel.setText(String.valueOf(WidthCursor.getValue()));
            		MaxSizeCursor.setMin(WidthCursor.getValue()*0.13);
            		MinSizeCursor.setMax(WidthCursor.getValue()*0.13);
            }
        });
		HeightCursor.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,Number old_val, Number new_val) {
            		HeightCursor.setValue(Math.round(new_val.doubleValue()));
            		HeightLabel.setText(String.valueOf(HeightCursor.getValue()));
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
            		MaxSizeLabel.setText(String.valueOf(MaxSizeCursor.getValue()));
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
            		MinSizeLabel.setText(String.valueOf(MinSizeCursor.getValue()));
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
	 * Poste popup in order to import folder's pictures
	 */
	@FXML
	private void affichePopupImportImage()
	{
		Integer w = 400;	//Largeur de la fenetre popup d'importation des images
		Integer h = 100;	//Hauteur de la fenetre popup d'importation des images
		final Stage popup = new Stage();	//Création d'un stage
		popup.initModality(Modality.APPLICATION_MODAL);	//initialisation du stage "popup"
        VBox popupImportImages = new VBox();	//création d'un VBox
        HBox textinfoCenter = new HBox();	//création d'un HBox pour centré le texte d'information
        textinfoCenter.setAlignment(Pos.CENTER);	//Centre le HBox "textinfoCenter"
        final Label textinfo = new Label("Veuillez entrée le chemin vers le dossier contenant les images.");	//Création d'un label pour le texte d'information
        textinfo.setTranslateY(5);	//Y placement = 5
        textinfo.setFont(Font.font("Arial", FontWeight.BOLD, 12));	//Type "Arial", Gras, taille : 12.
        textinfoCenter.getChildren().add(textinfo);	//Ajouter le text "textinfo" dans le HBox "textinfoCenter"
        final TextField path = new TextField();	//Création d'un textField pour le chemin du dossier contenant les images
        path.setTranslateY(15);	//Y placement 15
        HBox submitcenter = new HBox();	//Création d'un HBox pour centré le bouton "submit"
        submitcenter.setAlignment(Pos.CENTER);	//centré le HBox "submitcenter"
        final Button submit = new Button("Submit");	//Création du bouton "submit"
        submit.setTranslateY(25);	//Y placement 25
        submitcenter.getChildren().add(submit);	//Ajouter le bouton "submit" au HBox "submitcenter" 
        //EXEMPLE A MODIFIER
        path.setText("D:\\Elodie\\Documents\\POLYTECHNANTES\\4èm année\\PTRANS\\1 images URDLA online Copie");
        /**/
        popupImportImages.getChildren().addAll(textinfoCenter,path,submitcenter);	//Ajouter au VBox "popupImportImages" les champs "textinfocenter", "path" et "submitcenter"
        Scene popupImportImagesScene = new Scene(popupImportImages, w, h);	//Création d'une scène initialiser avec la VBox "popupImportImages", et de taille : w et h.
        popup.setTitle("Importation des images (format : .jpg)");	//mettre un titre au stage "popup"
        popup.setScene(popupImportImagesScene);	//Ajouter la scene "popupImportImagesScene"
        popup.show();	//Afficher
        submit.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		if((path.getText().length()!=0)) {
        			Boolean test = model.getCollectionImage().importImage(path.getText());
        			if (test) 
        			{
        				menuImportImmages.setDisable(true);
        				menuImportResults.setDisable(false);
            			popup.close();
            			Alert alert = new Alert(AlertType.INFORMATION);
            			alert.setTitle("Information");
            			alert.setHeaderText(null);
            			alert.setContentText("Les images ont été récupérer !");
            			alert.showAndWait();
        			}
        			else
        			{
        				Alert alert = new Alert(AlertType.ERROR);
            			alert.setTitle("Error");
            			alert.setHeaderText("Erreur d'entrée");
            			alert.setContentText("Le chemin du dossier est incorrect");
            			alert.showAndWait();
        			}
        		}
        		else {
        			Alert alert = new Alert(AlertType.ERROR);
        			alert.setTitle("Error");
        			alert.setHeaderText("Erreur d'entrée");
        			alert.setContentText("Vous n'avez entrée aucun chemin vers le dossier des images");
        			alert.showAndWait();
        		}
        	}
        });
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
	private void affichePopupImportResult()
	{
		Integer w = 400;	//Largeur de la fenetre popup d'importation des images
		Integer h = 100;	//Hauteur de la fenetre popup d'importation des images
		final Stage popup = new Stage();	//Création d'un stage
		popup.initModality(Modality.APPLICATION_MODAL);	//initialisation du stage "popup"
        VBox popupImportResults = new VBox();	//création d'un VBox
        HBox textinfoCenter = new HBox();	//création d'un HBox pour centré le texte d'information
        textinfoCenter.setAlignment(Pos.CENTER);	//Centre le HBox "textinfoCenter"
        final Label textinfo = new Label("Veuillez entrée le chemin vers du fichier de résultat.");	//Création d'un label pour le texte d'information
        textinfo.setTranslateY(5);	//Y placement = 5
        textinfo.setFont(Font.font("Arial", FontWeight.BOLD, 12));	//Type "Arial", Gras, taille : 12.
        textinfoCenter.getChildren().add(textinfo);	//Ajouter le text "textinfo" dans le HBox "textinfoCenter"
        final TextField path = new TextField();	//Création d'un textField pour le chemin du dossier contenant les images
        path.setTranslateY(15);	//Y placement 15
        HBox submitcenter = new HBox();	//Création d'un HBox pour centré le bouton "submit"
        submitcenter.setAlignment(Pos.CENTER);	//centré le HBox "submitcenter"
        final Button submit = new Button("Submit");	//Création du bouton "submit"
        submit.setTranslateY(25);	//Y placement 25
        submitcenter.getChildren().add(submit);	//Ajouter le bouton "submit" au HBox "submitcenter" 
        //EXEMPLE A MODIFIER
        path.setText("D:\\Elodie\\Documents\\POLYTECHNANTES\\4èm année\\PTRANS\\appli\\résultats-pqmethod.txt");
        /**/
        popupImportResults.getChildren().addAll(textinfoCenter,path,submitcenter);	//Ajouter au VBox "popupImportResults" les champs "textinfocenter", "path" et "submitcenter"
        Scene popupImportResultsScene = new Scene(popupImportResults, w, h);	//Création d'une scène initialiser avec la VBox "popupImportResults", et de taille : w et h.
        popup.setTitle("Importation du résultat de la Qméthode (format : .txt)");	//mettre un titre au stage "popup"
        popup.setScene(popupImportResultsScene);	//Ajouter la scene "popupImportResultsScene"
        popup.show();	//Afficher
        submit.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		if((path.getText().length()!=0)) {
        			Boolean test = model.getCollectionImage().importData(path.getText());
        			if (test) 
        			{
        				menuImportResults.setDisable(true);
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
            			popup.close();
            			Alert alert = new Alert(AlertType.INFORMATION);
            			alert.setTitle("Information");
            			alert.setHeaderText(null);
            			alert.setContentText("Les données ont été récupérer !");
            			alert.showAndWait();
        			}
        			else
        			{
        				Alert alert = new Alert(AlertType.ERROR);
            			alert.setTitle("Error");
            			alert.setHeaderText("Erreur d'entrée");
            			alert.setContentText("Le chemin du fichier des résultats est incorrect");
            			alert.showAndWait();
        			}
        		}
        		else {
        			Alert alert = new Alert(AlertType.ERROR);
        			alert.setTitle("Error");
        			alert.setHeaderText("Erreur d'entrée");
        			alert.setContentText("Vous n'avez entrée aucun chemin vers le fichier de résultat.");
        			alert.showAndWait();
        		}
        	}
        });
	}

	@FXML
	private void applyValue()
	{
		//Add elements to TraitementVisualization
		model.getTraitementVisualization().setCorpus(model.getCollectionImage().getCorpus());
		model.getTraitementVisualization().setWidth((int) WidthCursor.getValue());
		model.getTraitementVisualization().setHeight((int) HeightCursor.getValue());
		model.getTraitementVisualization().setFactor1(Integer.parseInt(MajorFactorCombobox.getValue()));
		model.getTraitementVisualization().setFactor2(Integer.parseInt(MinorFactorCombobox.getValue()));
		model.getTraitementVisualization().setCP(Color1Vizu.getValue());
		model.getTraitementVisualization().setCM(Color2Vizu.getValue());
		model.getTraitementVisualization().setT1((int) MinSizeCursor.getValue());
		model.getTraitementVisualization().setT3((int) MaxSizeCursor.getValue());
		/*System.out.println("Major Factor: " + MajorFactorCombobox.getValue());
		System.out.println("Minor Factor: " + MinorFactorCombobox.getValue());
		System.out.println("TraitementVisu Type: " + TypeVizuComboBox.getValue());
		System.out.println("TraitementVisu Color: " + ColorVizuComboBox.getValue());
		System.out.println("First Color: " + Color1Vizu.getValue());
		System.out.println("Second Color: " + Color2Vizu.getValue());
		System.out.println("Width: " + WidthCursor.getValue());
		System.out.println("Height: " + HeightCursor.getValue());
		System.out.println("Max Size: " + MaxSizeCursor.getValue());
		System.out.println("Min Size: " + MinSizeCursor.getValue());*/
		//Creation of popup fenetre which contain visualization
		final Stage popup = new Stage();	//Création d'un stage
		popup.initModality(Modality.APPLICATION_MODAL);	//initialisation du stage "popup"
		Group root = new Group();
		model.getTraitementVisualization().setCanvasVisuCircle();
		Canvas canvas = model.getTraitementVisualization().startVisu();
		root.getChildren().add(canvas);
		Scene popupImportResultsScene = new Scene(root, WidthCursor.getValue(), HeightCursor.getValue());	//Création d'une scène initialiser avec la VBox "popupImportResults", et de taille : w et h.
        popup.setTitle("Visualisation :");	//mettre un titre au stage "popup"
        popup.setScene(popupImportResultsScene);	//Ajouter la scene "popupImportResultsScene"
        popup.show();	//Afficher
	}
	
}
