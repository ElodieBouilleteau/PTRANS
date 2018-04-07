package pqmethodvisu;

/*fx:controller="APPLI.src.pqmethodvisu.view.MainAppController"*/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/MainApp.fxml"));
        primaryStage.setTitle("PQmethodVisu");
        primaryStage.setScene(new Scene(root, 200, 600));
        primaryStage.show();
        //crée un model et l'ajouter dans le controller
        //Model m = new Model();
        //MainAppController controllerMainApp =  new MainAppController(m);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
