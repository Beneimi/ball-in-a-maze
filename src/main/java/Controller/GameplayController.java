package Controller;

import Model.Game;
import Model.Tile;
import View.GUI.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;


public class GameplayController {

    @FXML
    public GridPane grid;

    @Setter
    private Game game;
/*
    @FXML
    private void initialize(){
        Draw();
    }*/

    public void SetGame(Game game){
        this.game = game;
        Draw();
    }

    public void Draw(){
        StackPane sp;
        Tile t;

        for(int i = 0; i < grid.getChildren().size(); i++){
            sp = (StackPane) grid.getChildren().get(i);

            t = game.getBoard().GetTile(GridPane.getRowIndex(sp), GridPane.getColumnIndex(sp));

            if(t.isDown()){
                sp.getChildren().stream()
                        .filter(p -> (StackPane.getAlignment(p) == Pos.BOTTOM_CENTER))
                        .findFirst().get()
                        .setStyle("-fx-background-color: black");
            }
            if(t.isUp()){
                sp.getChildren().stream()
                        .filter(p -> (StackPane.getAlignment(p) == Pos.TOP_CENTER))
                        .findFirst().get()
                        .setStyle("-fx-background-color: black");
            }
            if(t.isLeft()){
                sp.getChildren().stream()
                        .filter(p -> (StackPane.getAlignment(p) == Pos.TOP_LEFT))
                        .findFirst().get()
                        .setStyle("-fx-background-color: black");
            }
            if(t.isRight()){
                sp.getChildren().stream()
                        .filter(p -> (StackPane.getAlignment(p) == Pos.TOP_RIGHT))
                        .findFirst().get()
                        .setStyle("-fx-background-color: black");
            }

            if(t.isGoal()){
                sp.setStyle("-fx-background-color: chocolate");
            }
            else if(GridPane.getRowIndex(sp) == game.getBallPosition().x
                    && GridPane.getColumnIndex(sp) == game.getBallPosition().y){
                sp.setStyle("-fx-background-color: coral");
            }else {
                sp.setStyle("-fx-background-color: white");
            }
        }
    }


    public void handleBackButton(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menu/menu.fxml"));
        stage.setScene(new Scene(root,600,600));
    }

    public void handleKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.S){
            game.MoveBall(Game.DIRECTION.DOWN);
        }
        if (keyEvent.getCode() == KeyCode.W){
            game.MoveBall(Game.DIRECTION.UP);
        }
        if (keyEvent.getCode() == KeyCode.A){
            game.MoveBall(Game.DIRECTION.LEFT);
        }
        if (keyEvent.getCode() == KeyCode.D){
            game.MoveBall(Game.DIRECTION.RIGHT);
        }

        this.Draw();
    }
}
