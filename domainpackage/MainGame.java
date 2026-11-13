package domainpackage;

import domainpackage.view.MainScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainGame extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainScreen mainScreen = new MainScreen();
        Scene scene = new Scene(mainScreen, 960, 720);
        String stylesheet = MainGame.class.getResource("/assets/ui/styles.css").toExternalForm();
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
