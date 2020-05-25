package View.Main;

import Model.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.awt.Point;

public class Application extends javafx.application.Application {


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/menu.fxml"));
        stage.setScene(new Scene(root,600,600));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
        Game g = new Game(6,new Point(2,2),new Point(4,4));
        g.setWall(new Point(4,4),new Point(4,3));
    }
}
