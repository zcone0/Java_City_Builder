package domainpackage.view;

import javafx.geometry.Pos;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;

public class MainMenuScreen extends StackPane {
    private final Button startButton = new Button("Start");

    public MainMenuScreen() {
        final Button quitButton = new Button("Quit");
        getStyleClass().add("main-menu-screen");
        startButton.getStyleClass().add("main-menu-button");
        quitButton.getStyleClass().add("main-menu-button");
        startButton.setMaxWidth(Double.MAX_VALUE);
        quitButton.setMaxWidth(Double.MAX_VALUE);
        quitButton.setOnAction(event -> Platform.exit());

        VBox menuBox = new VBox(10, startButton, quitButton);
        menuBox.getStyleClass().add("main-menu-box");
        menuBox.setAlignment(Pos.CENTER);

        Label title = new Label("EquiCity");
        title.getStyleClass().add("main-menu-title");

        VBox menuContent = new VBox(18, title, menuBox);
        menuContent.getStyleClass().add("main-menu-content");
        menuContent.setAlignment(Pos.CENTER);

        setAlignment(Pos.CENTER);
        getChildren().addAll(createBackground(), createDarkeningLayer(), menuContent);
    }

    public void setStartAction(Runnable action) {
        startButton.setOnAction(event -> action.run());
    }

    private Node createBackground() {
        URL backgroundResource = MainMenuScreen.class.getResource("/assets/background.png");
        if (backgroundResource == null) {
            backgroundResource = MainMenuScreen.class.getResource("/resources/assets/background.png");
        }
        if (backgroundResource == null) {
            return new Region();
        }

        ImageView background = new ImageView(new Image(backgroundResource.toExternalForm()));
        background.setPreserveRatio(false);
        background.fitWidthProperty().bind(widthProperty());
        background.fitHeightProperty().bind(heightProperty());
        background.setEffect(new GaussianBlur(4));
        return background;
    }

    private Region createDarkeningLayer() {
        Region darkeningLayer = new Region();
        darkeningLayer.getStyleClass().add("main-menu-darkening");
        return darkeningLayer;
    }
}
