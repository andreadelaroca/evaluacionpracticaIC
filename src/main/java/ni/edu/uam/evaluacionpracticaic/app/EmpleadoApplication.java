package ni.edu.uam.evaluacionpracticaic.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EmpleadoApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EmpleadoApplication.class.getResource("/ni/edu/uam/evaluacionpracticaic/view/Formulario1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
