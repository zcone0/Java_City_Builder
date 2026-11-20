package domainpackage.view;

import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MainScreen extends BorderPane {
    private static final List<String> BUILD_OPTIONS = List.of(
            "Road", "Path", "Park", "Power Plant", "Water Tower", "School",
            "Hospital", "Police Station", "Fire Station", "Transit Stop",
            "Library", "Stadium");

    private final Label selectedOption = new Label("Nothing selected");
    private final Label statusLabel = new Label("Icon  |  Icon  |  Icon");
    private final VBox optionsSection = new VBox();

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
        Button buildTab = createTabButton("Build");
        Button zoneTab = createTabButton("Zone");
        Button infoTab = createTabButton("Info");
        HBox tabs = new HBox(buildTab, zoneTab, infoTab);
        tabs.getStyleClass().add("tabs");

        optionsSection.getChildren().addAll(tabs, createBuildOptions());
        optionsSection.getStyleClass().add("options-section");
        VBox.setVgrow(optionsSection.getChildren().get(1), Priority.ALWAYS);

        buildTab.setOnAction(event -> selectTab(buildTab, createBuildOptions()));
        zoneTab.setOnAction(event -> selectTab(zoneTab, createZoneOptions()));
        infoTab.setOnAction(event -> selectTab(infoTab, createTabInfo()));

        VBox informationPanel = createInformationPanel();
        HBox content = new HBox(optionsSection, informationPanel);
        content.getStyleClass().add("bottom-content");
        HBox.setHgrow(optionsSection, Priority.ALWAYS);
        HBox.setHgrow(informationPanel, Priority.SOMETIMES);

        VBox bottomPanel = new VBox(content, createStatusBar());
        bottomPanel.getStyleClass().add("bottom-panel");
        VBox.setVgrow(content, Priority.ALWAYS);
        return bottomPanel;
    }

    private Button createTabButton(String text) {
        Button button = new Button(text);
        button.getStyleClass().add("tab-button");
        if ("Build".equals(text)) {
            button.getStyleClass().add("selected");
        }
        return button;
    }

    private void selectTab(Button selectedTab, javafx.scene.Node content) {
        for (javafx.scene.Node node : selectedTab.getParent().getChildrenUnmodifiable()) {
            node.getStyleClass().remove("selected");
        }
        selectedTab.getStyleClass().add("selected");
        optionsSection.getChildren().set(1, content);
        VBox.setVgrow(content, Priority.ALWAYS);
    }

    private ScrollPane createBuildOptions() {
        GridPane optionGrid = new GridPane();
        optionGrid.getStyleClass().add("option-grid");
        optionGrid.setHgap(0);
        optionGrid.setVgap(0);
        optionGrid.setMaxWidth(Double.MAX_VALUE);
        for (int column = 0; column < 4; column++) {
            ColumnConstraints constraints = new ColumnConstraints();
            constraints.setPercentWidth(25);
            constraints.setHgrow(Priority.ALWAYS);
            optionGrid.getColumnConstraints().add(constraints);
        }

        for (int index = 0; index < BUILD_OPTIONS.size(); index++) {
            Button optionButton = createOptionButton(BUILD_OPTIONS.get(index));
            optionGrid.add(optionButton, index % 4, index / 4);
            GridPane.setHgrow(optionButton, Priority.ALWAYS);
        }

        return createOptionsScrollPane(optionGrid);
    }

    private VBox createZoneOptions() {
        VBox zoneOptions = new VBox(10);
        zoneOptions.getStyleClass().add("menu-content");
        ObservableList<String> items = FXCollections.observableArrayList(
            "Residential", "Industrial", "Commercial"
        );
        ComboBox zoneType = new ComboBox(items);
        zoneOptions.getChildren().add(createMenuHeading("Type:"));
        zoneOptions.getChildren().add(zoneType);
        VBox container = new VBox();
        zoneType.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                container.getChildren().clear();
            }
            if (newVal == "Residential"){
                container.getChildren().add(createMenuButton("Low Density Residential"));
                container.getChildren().add(createMenuButton("Medium Density Residential"));
                container.getChildren().add(createMenuButton("High Density Residential"));
                zoneOptions.getChildren().add(container);
            }
            else if (newVal == "Industrial"){
                container.getChildren().add(createMenuButton("Light Industrial"));
                container.getChildren().add(createMenuButton("Heavy Industrial"));
                zoneOptions.getChildren().add(container);
            }
            else if (newVal == "Commercial"){
                container.getChildren().add(createMenuButton("Local Commercial"));
                container.getChildren().add(createMenuButton("Retail Commercial"));
                zoneOptions.getChildren().add(container);
            }
        
        });

        ScrollPane scrollPane = new ScrollPane(zoneOptions);
        scrollPane.getStyleClass().add("options-scroll");
        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);
        return wrapContent(scrollPane);
    }

    private VBox createTabInfo() {
        VBox info = new VBox(12,
                createMenuHeading("Information"),
                new Label("Choose Build to place city services."),
                new Label("Choose Zone to set residential, industrial, or commercial areas."),
                new Label("More city-building options will be added here."));
        info.getStyleClass().add("menu-content");
        for (javafx.scene.Node node : info.getChildren()) {
            if (node instanceof Label label) {
                label.setWrapText(true);
            }
        }
        return info;
    }

    private ScrollPane createOptionsScrollPane(GridPane optionGrid) {
        ScrollPane optionsPanel = new ScrollPane(optionGrid);
        optionsPanel.getStyleClass().add("options-scroll");
        optionsPanel.setFitToWidth(true);
        optionsPanel.setPannable(true);
        return optionsPanel;
    }

    private VBox wrapContent(javafx.scene.Node content) {
        VBox wrapper = new VBox(content);
        VBox.setVgrow(content, Priority.ALWAYS);
        return wrapper;
    }

    private Label createMenuHeading(String text) {
        Label heading = new Label(text);
        heading.getStyleClass().add("menu-heading");
        return heading;
    }

    private Button createMenuButton(String text) {
        return createOptionButton(text);
    }

    private Button createOptionButton(String text) {
        Button button = new Button(text);
        button.getStyleClass().add("option-button");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setPrefHeight(44);
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
        Label population = new Label("| Pop 100");
        Label day = new Label("| Day 00 00:00");
        Label money = new Label("| $0000");

        HBox statusBar = new HBox(statusLabel, population, day, money);
        statusBar.getStyleClass().add("status-bar");
        statusBar.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(statusLabel, Priority.ALWAYS);
        return statusBar;
    }
}
