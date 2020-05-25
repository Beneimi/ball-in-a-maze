package Controller;

import Dao.GameDao;
import Dao.PlayerDao;
import Dao.SavedGame;
import Model.Game;
import Model.Tile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    @FXML
    public Label playerName;

    @FXML
    public Label score;

    @Setter
    private Game game;

    private String levelName;


    private FXMLLoader fxmlLoader;

    public void SetGame(SavedGame savedGame){
        this.game = savedGame.getGame();
        this.levelName = savedGame.getName();
        score.setText("Moves: " + game.getScore() );
        Update();
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

        score.setText("Moves: " + game.getScore() );

        if(this.game.GoalReached()){
            this.saveScore();
            this.loadLevelComplete();
        }
    }

    private void saveScore() {
        PlayerDao pd = new PlayerDao();
        // TODO: 2020. 05. 25. handle this properly
        try {
            pd.SaveScore(playerName.getText(),levelName,game.getScore());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadLevelComplete(){

        Stage stage = (Stage) grid.getScene().getWindow();

        fxmlLoader = new FXMLLoader();

        fxmlLoader.setLocation(getClass().getClassLoader().getResource("fxml/levelComplete.fxml"));
        Parent root = null;
        // TODO: 2020. 05. 25. handle this properly
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fxmlLoader.<LevelCompleteController>getController().setScore(game.getScore());
        stage.setScene(new Scene(root,600,600));
    }


    public void handleBackButton(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/menu.fxml"));
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

        this.Update();
    }


    public void SetPlayerName(String name) {
        this.playerName.setText(name);
    }
}
