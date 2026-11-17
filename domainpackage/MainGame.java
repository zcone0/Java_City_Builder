package domainpackage;

import domainpackage.view.MainScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class MainGame extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainScreen mainScreen = new MainScreen();
        Scene scene = new Scene(mainScreen, 960, 720);
        URL stylesheetResource = MainGame.class.getResource("/assets/ui/styles.css");
        if (stylesheetResource == null) {
            // BlueJ uses the project directory as the classpath, unlike Maven.
            stylesheetResource = MainGame.class.getResource("/resources/assets/ui/styles.css");
        }
        if (stylesheetResource == null) {
            throw new IllegalStateException("Could not find resources/assets/ui/styles.css");
        }
        String stylesheet = stylesheetResource.toExternalForm();
        scene.getStylesheets().add(stylesheet);

        primaryStage.setTitle("EquiCity");
        primaryStage.setMinWidth(720);
        primaryStage.setMinHeight(520);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
