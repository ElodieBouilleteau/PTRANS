package pqmethodvisu;

/*D:\Elodie\Documents\POLYTECHNANTES\4�m ann�e\PTRANS\1 images URDLA online Copie : fichier des images*/
/*D:\Elodie\Documents\POLYTECHNANTES\4�m ann�e\PTRANS\appli\r�sultats-pqmethod.txt : fichier du r�sultats*/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pqmethodvisu.controller.MainAppControllerOverview;
import pqmethodvisu.model.Model;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
    	//Cr�ation d'un loader pour le fichier FXML
    	FXMLLoader loader = new FXMLLoader();
    	//T�l�charger le fichier fxml
        loader.setLocation(Main.class.getResource("view/MainAppOverview.fxml"));
        //Cr�ation de l'anchor pane du menu
        AnchorPane MainApp = (AnchorPane) loader.load();
        primaryStage.setTitle("PQmethodVisu");
        primaryStage.setScene(new Scene(MainApp, 200, 600));
        primaryStage.show();
        // Acceder au controller du fichier fxml
        MainAppControllerOverview controller = loader.getController();
        //cr�e un model et l'ajouter dans le controller
        Model m = new Model();
        controller.setModel(m);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
