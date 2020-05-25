package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tinylog.Logger;


public class Application extends javafx.application.Application {


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/menu.fxml"));
        stage.setScene(new Scene(root,600,600));
        stage.show();
        Logger.info("Application running");
    }

}
