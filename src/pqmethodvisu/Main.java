package pqmethodvisu;

/*D:\Elodie\Documents\POLYTECHNANTES\4èm année\PTRANS\1 images URDLA online Copie : fichier des images*/
/*D:\Elodie\Documents\POLYTECHNANTES\4èm année\PTRANS\appli\résultats-pqmethod.txt : fichier du résultats*/

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pqmethodvisu.controller.MainAppControllerOverview;
import pqmethodvisu.model.Model;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
    	//commentaire
    	//Création d'un loader pour le fichier FXML
    	FXMLLoader loader = new FXMLLoader();
    	//Télécharger le fichier fxml
        loader.setLocation(Main.class.getResource("view/MainAppOverview.fxml"));
        //Création de l'anchor pane du menu
        AnchorPane MainApp = (AnchorPane) loader.load();
        primaryStage.setTitle("PQmethodVisu");
        primaryStage.setScene(new Scene(MainApp, 332, 600));
        primaryStage.show();
        // Acceder au controller du fichier fxml
        MainAppControllerOverview controller = loader.getController();
        //crée un model et l'ajouter dans le controller
        Model m = new Model();
        controller.setModel(m);
        //ajout de la stage primaire dans le controller
        controller.setMainStage(primaryStage);
        //fermer l'application
        primaryStage.setOnCloseRequest(e -> Platform.exit());
    }


    public static void main(String[] args) {
    	//tests
    	/*VisuBipolarTest vbt = new VisuBipolarTest();
    	System.out.println("Test resizew");
    	System.out.println(vbt.resizewTest(10, 0, 10));
    	System.out.println(vbt.resizewTest(0, 10, 10));
    	System.out.println(vbt.resizewTest(5, 5, 10));
    	System.out.println(vbt.resizewTest(2, 5, 0));
    	System.out.println("Test resizeh");
    	System.out.println(vbt.resizehTest(10, 0, 10));
    	System.out.println(vbt.resizehTest(0, 10, 10));
    	System.out.println(vbt.resizehTest(5, 5, 10));
    	System.out.println(vbt.resizehTest(2, 5, 0));*/
        launch(args);
    }
}
