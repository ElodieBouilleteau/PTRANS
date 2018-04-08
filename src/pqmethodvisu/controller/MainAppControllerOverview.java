package pqmethodvisu.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//initializer le comboBox du type de visu
		ComboBox<String> typeVizuComboBox = FXUtils.getChildByID(ap, "TypeVizuComboBox");
		ObservableList<String> listType = FXCollections.observableArrayList("Rectangle","Circle");
		typeVizuComboBox.setItems(listType);
		typeVizuComboBox.getSelectionModel().select("Rectangle");
		
		//initializer le comboBox du choix de couleur
		ComboBox<String> colorVizuComboBox = FXUtils.getChildByID(ap, "ColorVizuComboBox");
		ObservableList<String> listColorVizu = FXCollections.observableArrayList("ColorFull","Black/White");
		colorVizuComboBox.setItems(listColorVizu);
		colorVizuComboBox.getSelectionModel().select("ColorFull");
	
		//INITIALISER LES COULEURS DES COMBOBOX
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
        			System.out.println("test : "+test);
        			if (test) 
        			{
        				menuImportResults.setDisable(true);
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
