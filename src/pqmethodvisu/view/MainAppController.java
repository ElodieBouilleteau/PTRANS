package pqmethodvisu.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pqmethodvisu.FXUtils;

public class MainAppController {
	
	/*
	@FXML
	private AnchorPane ap;
	private String value;
	
	@FXML
	private void addItemsOnCb()
	{
		System.out.println("test");
		ComboBox cb = FXUtils.getChildByID(ap, "facteurPrincipalCombobox");
        ObservableList<String> list = FXCollections.observableArrayList("1","2","3","4");
        cb.setItems(list);
	}
	
	
	@FXML
	private void affichePopup()
	{
		System.out.println("test");
		final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("Importation des images (format : .jpg) :"));
        final TextField path = new TextField();
        final Button submit = new Button();
        dialogVbox.getChildren().addAll(path,submit);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
        submit.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		value = path.getText();
        		System.out.println(value);
        		dialog.close();
        	}
        });
	}
	*/
}
