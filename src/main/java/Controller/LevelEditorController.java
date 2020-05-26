package Controller;

import Dao.GameDao;
import Model.Game;
import Model.Tile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.awt.Point;
import java.io.IOException;

public class LevelEditorController {

    @FXML
    public TextField levelName;
    @FXML
    GridPane grid;

    private FXMLLoader fxmlLoader;

    private Game game;
    private Node selected;

    public void initialize(){
        this.game = new Game(8,new Point(0,0),new Point(7,7));
        this.Update();
    }

    private void handleLeftClick(MouseEvent mouseEvent){
        Node clickedNode = mouseEvent.getPickResult().getIntersectedNode();
        if (selected == null){
            selected = clickedNode;
            selected.setStyle("-fx-background-color: gray");
        }
        else {
            if(selected == clickedNode){
                game.setGoal(GridPane.getRowIndex(selected),GridPane.getColumnIndex(selected));
            }
            else {
                try {
                    game.setWall(new Point(GridPane.getRowIndex(selected), GridPane.getColumnIndex(selected)),
                            new Point(GridPane.getRowIndex(clickedNode), GridPane.getColumnIndex(clickedNode)));
                }catch (IllegalArgumentException e){
                    Logger.error(e);
                }
            }
            selected = null;
            this.Update();
        }
    }

    private void handleRightClick(MouseEvent mouseEvent){
        Node clickedNode = mouseEvent.getPickResult().getIntersectedNode();
        try {
            game.setBallPosition(GridPane.getRowIndex(clickedNode), GridPane.getColumnIndex(clickedNode));
        }catch (IllegalArgumentException e){
            Logger.error(e);
        }
        Update();
    }

    public void handleMouseClick(MouseEvent mouseEvent) {
        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
            handleLeftClick(mouseEvent);
        }else {
            handleRightClick(mouseEvent);
        }

    }

    public void Update(){
        StackPane sp;
        Tile t;

        for(int i = 0; i < grid.getChildren().size(); i++){
            sp = (StackPane) grid.getChildren().get(i);

            t = game.getTile(GridPane.getRowIndex(sp), GridPane.getColumnIndex(sp));

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
                sp.setStyle("-fx-background-color: darkgreen");
            }
            else if(GridPane.getRowIndex(sp) == game.getBallPosition().x
                    && GridPane.getColumnIndex(sp) == game.getBallPosition().y){
                sp.setStyle("-fx-background-color: deepskyblue");
            }else {
                sp.setStyle("-fx-background-color: white");
            }
        }
    }

    public void handleBackButton(ActionEvent actionEvent){
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getClassLoader().getResource("fxml/menu.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            Logger.error(e);
        }
        stage.setScene(new Scene(root,600,600));
    }

    public void handleSaveButton(ActionEvent actionEvent) {
        GameDao gd = new GameDao();
        try {
            gd.SaveGame(this.levelName.getText(),this.game);
        } catch (IOException e) {
            Logger.error(e);
        }
        handleBackButton(actionEvent);

    }

}

