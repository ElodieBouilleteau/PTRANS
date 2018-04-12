package pqmethodvisu.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pqmethodvisu.model.CollectionImage;
import pqmethodvisu.model.Model;
import pqmethodvisu.model.Visualization;
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
	private Slider HeightCursor;
	@FXML
	private Slider MaxSizeCursor;
	@FXML
	private Slider MinSizeCursor;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//initializer le comboBox du type de visu
		TypeVizuComboBox.setItems(FXCollections.observableArrayList("Rectangle","Circle"));
		TypeVizuComboBox.getSelectionModel().select("Rectangle");
		
		//initializer le comboBox du choix de couleur
		ColorVizuComboBox.setItems(FXCollections.observableArrayList("ColorFull","Black/White"));
		ColorVizuComboBox.getSelectionModel().select("ColorFull");
	
		//INITIALISER LES COULEURS DES COMBOBOX
		Color1Vizu.setValue(Color.BLUE);
		Color2Vizu.setValue(Color.RED);
	}
	
	/*
	 * Ajoute le model dans le controler
	 */
	public void setModel(Model model) {
		this.model = model;
		CollectionImage collectionImages = new CollectionImage();
		model.setCollectionImage(collectionImages);
		Visualization visualization = Visualization.getInstance(model);
		model.setVisualization(visualization);
	}
	
	/*
	 * Affiche la popup pour importer les images du dossier
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
	 * Bloquer certaines valeurs des combobox des facteurs
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
	 * Affiche la popup pour importer les resultats de la Qmethod
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
		System.out.println("Major Factor: " + MajorFactorCombobox.getValue());
		System.out.println("Minor Factor: " + MinorFactorCombobox.getValue());
		System.out.println("Visualization Type: " + TypeVizuComboBox.getValue());
		System.out.println("Visualization Color: " + ColorVizuComboBox.getValue());
		System.out.println("First Color: " + Color1Vizu.getValue());
		System.out.println("Second Color: " + Color2Vizu.getValue());
		System.out.println("Width: " + WidthCursor.getValue());
		System.out.println("Height: " + HeightCursor.getValue());
		System.out.println("Max Size: " + MaxSizeCursor.getValue());
		System.out.println("Min Size: " + MinSizeCursor.getValue());
	}
	
}
