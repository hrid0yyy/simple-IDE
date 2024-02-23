package sample.ide;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SplashScreenController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         new SplashScreen().start();
    }

    class SplashScreen extends Thread
    {

        @Override
        public void run()
        {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        rootPane.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
                            @Override
                            public void handle(KeyEvent keyEvent) {

                                    Parent root = null;
                                    try {
                                        root = FXMLLoader.load(getClass().getResource("compiler.fxml"));
                                    } catch (IOException e) {

                                        throw new RuntimeException(e);
                                    }

                                    Scene scene = new Scene(root);
                                    Stage stage = new Stage();
                                    stage.setScene(scene);
                                    stage.show();

                                    rootPane.getScene().getWindow().hide();


                            }
                        });






                        //////

                    }
                });



        }
    }
}
