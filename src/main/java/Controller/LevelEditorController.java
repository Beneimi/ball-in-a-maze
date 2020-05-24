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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.awt.Point;
import java.io.IOException;

public class LevelEditorController {

    @FXML
    public TextField levelName;
    @FXML
    GridPane grid;

    Popup popup = new Popup();

    private Game game = new Game(8,new Point(0,0),new Point(7,7));
    private Node selected;


    public void handleMouseClick(MouseEvent mouseEvent) {
        Node clickedNode = mouseEvent.getPickResult().getIntersectedNode();
        if (selected == null){
            selected = clickedNode;
            selected.setStyle("-fx-background-color: gray");
        }
        else {
            // TODO: 2020. 05. 24. fix popup 
            try {
                game.setWall(new Point(GridPane.getRowIndex(selected), GridPane.getColumnIndex(selected)),
                        new Point(GridPane.getRowIndex(clickedNode), GridPane.getColumnIndex(clickedNode)));
            }catch (IllegalArgumentException ex){
                popup.getContent().add(new Label("Can't place a wall like that"));
                popup.show(levelName.getScene().getWindow());
            }


            selected.setStyle("-fx-background-color: white");
            selected = null;
            this.Update();
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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/menu.fxml"));
        stage.setScene(new Scene(root,600,600));
    }

    public void handleSaveButton(ActionEvent actionEvent) {
        GameDao gd = new GameDao();

        // TODO: 2020. 05. 24. handle exception properly 
        try {
            gd.SaveGame(this.levelName.getText(),this.game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

