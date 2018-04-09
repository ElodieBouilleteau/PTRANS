package pqmethodvisu.controller;

import java.net.URL;
import java.util.ResourceBundle;

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
import pqmethodvisu.FXUtils;
import pqmethodvisu.model.CollectionImage;
import pqmethodvisu.model.Model;
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
	}
	
	/*
	 * Affiche la popup pour importer les images du dossier
	 */
	@FXML
	private void affichePopupImportImage()
	{
		Integer w = 400;	//Largeur de la fenetre popup d'importation des images
		Integer h = 100;	//Hauteur de la fenetre popup d'importation des images
		final Stage popup = new Stage();	//Cr�ation d'un stage
		popup.initModality(Modality.APPLICATION_MODAL);	//initialisation du stage "popup"
        VBox popupImportImages = new VBox();	//cr�ation d'un VBox
        HBox textinfoCenter = new HBox();	//cr�ation d'un HBox pour centr� le texte d'information
        textinfoCenter.setAlignment(Pos.CENTER);	//Centre le HBox "textinfoCenter"
        final Label textinfo = new Label("Veuillez entr�e le chemin vers le dossier contenant les images.");	//Cr�ation d'un label pour le texte d'information
        textinfo.setTranslateY(5);	//Y placement = 5
        textinfo.setFont(Font.font("Arial", FontWeight.BOLD, 12));	//Type "Arial", Gras, taille : 12.
        textinfoCenter.getChildren().add(textinfo);	//Ajouter le text "textinfo" dans le HBox "textinfoCenter"
        final TextField path = new TextField();	//Cr�ation d'un textField pour le chemin du dossier contenant les images
        path.setTranslateY(15);	//Y placement 15
        HBox submitcenter = new HBox();	//Cr�ation d'un HBox pour centr� le bouton "submit"
        submitcenter.setAlignment(Pos.CENTER);	//centr� le HBox "submitcenter"
        final Button submit = new Button("Submit");	//Cr�ation du bouton "submit"
        submit.setTranslateY(25);	//Y placement 25
        submitcenter.getChildren().add(submit);	//Ajouter le bouton "submit" au HBox "submitcenter" 
        popupImportImages.getChildren().addAll(textinfoCenter,path,submitcenter);	//Ajouter au VBox "popupImportImages" les champs "textinfocenter", "path" et "submitcenter"
        Scene popupImportImagesScene = new Scene(popupImportImages, w, h);	//Cr�ation d'une sc�ne initialiser avec la VBox "popupImportImages", et de taille : w et h.
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
            			alert.setContentText("Les images ont �t� r�cup�rer !");
            			alert.showAndWait();
        			}
        			else
        			{
        				Alert alert = new Alert(AlertType.ERROR);
            			alert.setTitle("Error");
            			alert.setHeaderText("Erreur d'entr�e");
            			alert.setContentText("Le chemin du dossier est incorrect");
            			alert.showAndWait();
        			}
        		}
        		else {
        			Alert alert = new Alert(AlertType.ERROR);
        			alert.setTitle("Error");
        			alert.setHeaderText("Erreur d'entr�e");
        			alert.setContentText("Vous n'avez entr�e aucun chemin vers le dossier des images");
        			alert.showAndWait();
        		}
        	}
        });
	}
	
	/*
	 * Affiche la popup pour importer les resultats de la Qmethod
	 */
	@FXML
	private void affichePopupImportResult()
	{
		Integer w = 400;	//Largeur de la fenetre popup d'importation des images
		Integer h = 100;	//Hauteur de la fenetre popup d'importation des images
		final Stage popup = new Stage();	//Cr�ation d'un stage
		popup.initModality(Modality.APPLICATION_MODAL);	//initialisation du stage "popup"
        VBox popupImportResults = new VBox();	//cr�ation d'un VBox
        HBox textinfoCenter = new HBox();	//cr�ation d'un HBox pour centr� le texte d'information
        textinfoCenter.setAlignment(Pos.CENTER);	//Centre le HBox "textinfoCenter"
        final Label textinfo = new Label("Veuillez entr�e le chemin vers du fichier de r�sultat.");	//Cr�ation d'un label pour le texte d'information
        textinfo.setTranslateY(5);	//Y placement = 5
        textinfo.setFont(Font.font("Arial", FontWeight.BOLD, 12));	//Type "Arial", Gras, taille : 12.
        textinfoCenter.getChildren().add(textinfo);	//Ajouter le text "textinfo" dans le HBox "textinfoCenter"
        final TextField path = new TextField();	//Cr�ation d'un textField pour le chemin du dossier contenant les images
        path.setTranslateY(15);	//Y placement 15
        HBox submitcenter = new HBox();	//Cr�ation d'un HBox pour centr� le bouton "submit"
        submitcenter.setAlignment(Pos.CENTER);	//centr� le HBox "submitcenter"
        final Button submit = new Button("Submit");	//Cr�ation du bouton "submit"
        submit.setTranslateY(25);	//Y placement 25
        submitcenter.getChildren().add(submit);	//Ajouter le bouton "submit" au HBox "submitcenter" 
        popupImportResults.getChildren().addAll(textinfoCenter,path,submitcenter);	//Ajouter au VBox "popupImportResults" les champs "textinfocenter", "path" et "submitcenter"
        Scene popupImportResultsScene = new Scene(popupImportResults, w, h);	//Cr�ation d'une sc�ne initialiser avec la VBox "popupImportResults", et de taille : w et h.
        popup.setTitle("Importation du r�sultat de la Qm�thode (format : .txt)");	//mettre un titre au stage "popup"
        popup.setScene(popupImportResultsScene);	//Ajouter la scene "popupImportResultsScene"
        popup.show();	//Afficher
        submit.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		if((path.getText().length()!=0)) {
        			Boolean test = model.getCollectionImage().importData(path.getText());
        			System.out.println("test : "+test);
        			if (test) 
        			{
        				menuImportResults.setDisable(true);
            			popup.close();
            			Alert alert = new Alert(AlertType.INFORMATION);
            			alert.setTitle("Information");
            			alert.setHeaderText(null);
            			alert.setContentText("Les donn�es ont �t� r�cup�rer !");
            			alert.showAndWait();
        			}
        			else
        			{
        				Alert alert = new Alert(AlertType.ERROR);
            			alert.setTitle("Error");
            			alert.setHeaderText("Erreur d'entr�e");
            			alert.setContentText("Le chemin du fichier des r�sultats est incorrect");
            			alert.showAndWait();
        			}
        		}
        		else {
        			Alert alert = new Alert(AlertType.ERROR);
        			alert.setTitle("Error");
        			alert.setHeaderText("Erreur d'entr�e");
        			alert.setContentText("Vous n'avez entr�e aucun chemin vers le fichier de r�sultat.");
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
	
	/*@FXML
	private AnchorPane ap;
	private String value;
	
	@FXML
	private void addItemsOnCb()
	{
		System.out.println("test");
		ComboBox facteur = FXUtils.getChildByID(ap, "facteurPrincipalCombobox");
        ObservableList<String> list = FXCollections.observableArrayList("1","2","3","4");
        cb.setItems(list);
	}*/
		
}
