package Controller;

import Model.Game;
import Model.Tile;
import View.GUI.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;




public class GameplayController {

    @FXML
    public GridPane grid;

    private Game game;


    //private Game game = new Game(8,);


    public void SetGame(Game game){
        this.game = game;
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

    public void Draw2(){
        Game game = Application.GetActiveGame();
        BorderPane b;
        Tile t;
        for(int i = 0; i < grid.getChildren().size(); i++){
            b = (BorderPane)grid.getChildren().get(i);
            t = game.getBoard().GetTile(GridPane.getRowIndex(b), GridPane.getColumnIndex(b));
            if (t.isDown()) {
                b.getBottom().setStyle("-fx-background-color: black");
            }
            if (t.isUp()) {
                b.getTop().setStyle("-fx-background-color: black");
            }
            if (t.isLeft()) {
                b.getLeft().setStyle("-fx-background-color: black");
            }
            if (t.isRight()) {
                b.getRight().setStyle("-fx-background-color: black");
            }
            if (t.isGoal()) {
                b.getCenter().setStyle("-fx-background-color: chartreuse");
            }
            else if(GridPane.getRowIndex(b) == game.getBallPosition().x
                    && GridPane.getColumnIndex(b) == game.getBallPosition().y){
                b.getCenter().setStyle("-fx-background-color: coral");
            }else {
                b.getCenter().setStyle("-fx-background-color: white");
            }
        }
    }

    public void handleBackButton(ActionEvent actionEvent) {
        this.Draw();
    }

    public void handleKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.S){
            Application.GetActiveGame().MoveBall(Game.DIRECTION.DOWN);
        }
        if (keyEvent.getCode() == KeyCode.W){
            Application.GetActiveGame().MoveBall(Game.DIRECTION.UP);
        }
        if (keyEvent.getCode() == KeyCode.A){
            Application.GetActiveGame().MoveBall(Game.DIRECTION.LEFT);
        }
        if (keyEvent.getCode() == KeyCode.D){
            Application.GetActiveGame().MoveBall(Game.DIRECTION.RIGHT);
        }

        this.Draw();
    }
}
