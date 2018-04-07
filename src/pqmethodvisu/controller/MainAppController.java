package pqmethodvisu.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pqmethodvisu.model.Model;
//ne pas toucher
import javafx.fxml.FXML;

public class MainAppController {
	
	Model model;
	
	public MainAppController(Model model) {
		this.model = model;
	}
	
	/*
	 * Affiche la popup pour importer les images du dossier
	 * 
	 */
	//ne pas toucher
	@FXML
	private void affichePopupImportImage()
	{
		Integer w = 400;
		Integer h = 100;
		final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox();
        HBox textinfoCenter = new HBox();
        textinfoCenter.setAlignment(Pos.CENTER);
        final Label textinfo = new Label("Veuillez entrée le chemin vers le dossier contenant les images.");
        textinfo.setTranslateY(5);
        textinfo.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        textinfoCenter.getChildren().add(textinfo);
        final TextField path = new TextField();
        path.setTranslateY(15);
        HBox submitcenter = new HBox();
        submitcenter.setAlignment(Pos.CENTER);
        final Button submit = new Button("Submit");
        submit.setTranslateY(25);
        submitcenter.getChildren().add(submit);
        dialogVbox.getChildren().addAll(textinfoCenter,path,submitcenter);
        Scene dialogScene = new Scene(dialogVbox, w, h);
        dialog.setTitle("Importation des images (format : .jpg)");
        dialog.setScene(dialogScene);
        dialog.show();
        submit.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		if(path.getText().length()!=0) {
        			path.getText();
            		dialog.close();
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
