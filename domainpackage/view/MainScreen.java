package domainpackage.view;

import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MainScreen extends BorderPane {
    private static final List<String> OPTION_NAMES = List.of(
            "Option 1 [Image]", "Option 2", "Option 3", "Option 4", "Option 5", 
            "Option 6", "Option 7", "Option 8", "Option 9", "Option 10");

    private final Label selectedOption = new Label("Nothing selected");
    private final Label statusLabel = new Label("Icon  |  Icon  |  Icon");

    public MainScreen() {
        getStyleClass().add("main-screen");
        setCenter(createCanvas());
        setBottom(createBottomPanel());
    }

    private VBox createCanvas() {
        Label canvasLabel = new Label("JavaFX Canvas (Grid)");
        canvasLabel.getStyleClass().add("canvas-label");

        VBox canvas = new VBox(canvasLabel);
        canvas.getStyleClass().add("canvas");
        canvas.setAlignment(Pos.CENTER);
        return canvas;
    }

    private VBox createBottomPanel() {
        HBox tabs = new HBox(
                createTabButton("Build", true),
                createTabButton("Zone", false),
                createTabButton("Info", false));
        tabs.getStyleClass().add("tabs");

        ScrollPane optionsPanel = createOptionsPanel();
        VBox.setVgrow(optionsPanel, Priority.ALWAYS);

        VBox optionsSection = new VBox(tabs, optionsPanel);
        optionsSection.getStyleClass().add("options-section");
        VBox.setVgrow(optionsPanel, Priority.ALWAYS);

        VBox informationPanel = createInformationPanel();
        HBox content = new HBox(optionsSection, informationPanel);
        content.getStyleClass().add("bottom-content");
        HBox.setHgrow(optionsSection, Priority.ALWAYS);

        VBox bottomPanel = new VBox(content, createStatusBar());
        bottomPanel.getStyleClass().add("bottom-panel");
        return bottomPanel;
    }

    private Button createTabButton(String text, boolean selected) {
        Button button = new Button(text);
        button.getStyleClass().add("tab-button");
        if (selected) {
            button.getStyleClass().add("selected");
        }
        return button;
    }

    private ScrollPane createOptionsPanel() {
        GridPane optionGrid = new GridPane();
        optionGrid.getStyleClass().add("option-grid");
        optionGrid.setHgap(0);
        optionGrid.setVgap(0);

        for (int index = 0; index < OPTION_NAMES.size(); index++) {
            Button optionButton = createOptionButton(OPTION_NAMES.get(index));
            optionGrid.add(optionButton, index % 4, index / 4);
        }

        ScrollPane optionsPanel = new ScrollPane(optionGrid);
        optionsPanel.getStyleClass().add("options-scroll");
        optionsPanel.setFitToWidth(true);
        optionsPanel.setFitToHeight(true);
        optionsPanel.setPannable(true);
        return optionsPanel;
    }

    private Button createOptionButton(String text) {
        Button button = new Button(text);
        button.getStyleClass().add("option-button");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMinHeight(42);
        button.setOnAction(event -> {
            selectedOption.setText(text);
            statusLabel.setText(text + " selected");
        });
        return button;
    }

    private VBox createInformationPanel() {
        Label heading = new Label("Information");
        heading.getStyleClass().add("information-heading");

        Label description = new Label(
                "You have currently selected\nsomething and it can do something");
        description.getStyleClass().add("information-description");

        Label upkeep = new Label("Upkeep $1000/mo +25 Safety");
        upkeep.getStyleClass().add("information-upkeep");

        VBox information = new VBox(heading, description, selectedOption, upkeep);
        information.getStyleClass().add("information-panel");
        information.setPadding(new Insets(14, 20, 14, 20));
        return information;
    }

    private HBox createStatusBar() {
        Label population = new Label("Pop 100");
        Label day = new Label("Day 15 00:00");
        Label money = new Label("$1000");

        HBox statusBar = new HBox(statusLabel, population, day, money);
        statusBar.getStyleClass().add("status-bar");
        statusBar.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(statusLabel, Priority.ALWAYS);
        return statusBar;
    }
}
