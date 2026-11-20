package domainpackage;

import domainpackage.view.MainScreen;
import domainpackage.view.MainMenuScreen;
import domainpackage.view.MenuOverlay;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;

public class MainGame extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainScreen mainScreen = new MainScreen();
        StackPane gameContainer = new StackPane(mainScreen);
        MenuOverlay menuOverlay = new MenuOverlay(gameContainer);
        gameContainer.getChildren().add(menuOverlay);
        menuOverlay.setHideAction(() -> mainScreen.setEffect(null));

        MainMenuScreen mainMenuScreen = new MainMenuScreen();
        StackPane appRoot = new StackPane(mainMenuScreen, gameContainer);
        gameContainer.setVisible(false);

        mainMenuScreen.setStartAction(() -> {
            appRoot.getChildren().get(0).setVisible(false);
            gameContainer.setVisible(true);
            mainScreen.requestFocus();
        });

        menuOverlay.setMainMenuAction(() -> {
            menuOverlay.hide();
            mainScreen.setEffect(null);
            gameContainer.setVisible(false);
            mainMenuScreen.setVisible(true);
        });

        Scene scene = new Scene(appRoot, 960, 720);
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
        primaryStage.setMinWidth(960);
        primaryStage.setMinHeight(720);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                event.consume();
                if (!gameContainer.isVisible()) {
                    return;
                }
                if (menuOverlay.isShowing()) {
                    if (menuOverlay.isPauseVisible()) {
                        menuOverlay.hide();
                        mainScreen.setEffect(null);
                    } else {
                        menuOverlay.showPause();
                    }
                } else {
                    menuOverlay.showPause();
                    mainScreen.setEffect(new GaussianBlur(5));
                }
            }
        });

        appRoot.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
