package View.GUI;

import Model.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.awt.*;

public class Application extends javafx.application.Application {

    private static Game activeGame;

    public static Game GetActiveGame(){
        return activeGame;
    }

    public static Game InitializeNewGame(Point start,Point goal){
        Application.activeGame = new Game(8,start,goal);
        activeGame.SetWall(new Point(4,4),new Point(3,4));
        activeGame.SetWall(new Point(0,1),new Point(0,2));
        activeGame.SetWall(new Point(5,3),new Point(5,4));


        return  activeGame;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menu/menu.fxml"));
        stage.setScene(new Scene(root,600,600));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
        Game g = new Game(6,new Point(2,2),new Point(4,4));
        g.SetWall(new Point(4,4),new Point(4,3));
    }
}
