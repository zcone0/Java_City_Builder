package domainpackage.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MenuOverlay extends StackPane {
    private Runnable mainMenuAction;
    private Runnable hideAction;
    private boolean pauseVisible;

    public MenuOverlay(Pane background) {
        getStyleClass().add("menu-overlay");
        setVisible(false);
        setAlignment(Pos.CENTER);
        setPickOnBounds(true);
    }

    public void setMainMenuAction(Runnable action) {
        mainMenuAction = action;
    }

    public void setHideAction(Runnable action) {
        hideAction = action;
    }

    public boolean isShowing() {
        return isVisible();
    }

    public boolean isPauseVisible() {
        return pauseVisible;
    }

    public void showPause() {
        pauseVisible = true;
        VBox panel = createPanel("Pause");
        panel.getStyleClass().add("pause-panel");
        panel.getChildren().addAll(
                createOverlayButton("Resume", this::hide),
                createOverlayButton("Settings", this::showSettings),
                createOverlayButton("Save", this::showSaves),
                createOverlayButton("Main Menu", () -> {
                    if (mainMenuAction != null) {
                        mainMenuAction.run();
                    }
                }));
        show(panel);
    }

    public void showSettings() {
        pauseVisible = false;
        VBox panel = createPanel("Settings");
        panel.getStyleClass().add("settings-panel");
        show(panel);
    }

    public void showSaves() {
        pauseVisible = false;
        VBox panel = createPanel("Save");
        panel.getStyleClass().add("saves-panel");
        VBox slots = new VBox(8);
        slots.getStyleClass().add("save-slots");

        for (int slot = 1; slot <= 5; slot++) {
            HBox row = new HBox();
            row.getStyleClass().add("save-slot-row");

            Label slotLabel = new Label("Save Slot " + slot);
            slotLabel.getStyleClass().add("save-slot-label");
            HBox.setHgrow(slotLabel, Priority.ALWAYS);

            Button saveButton = new Button("Save");
            saveButton.getStyleClass().add("save-slot-button");
            row.getChildren().addAll(slotLabel, saveButton);
            slots.getChildren().add(row);
        }

        panel.getChildren().add(slots);
        show(panel);
    }

    public void hide() {
        pauseVisible = false;
        setVisible(false);
        getChildren().clear();
        if (hideAction != null) {
            hideAction.run();
        }
    }

    private VBox createPanel(String title) {
        Label heading = new Label(title);
        heading.getStyleClass().add("overlay-heading");

        VBox panel = new VBox(12);
        panel.getStyleClass().add("overlay-panel");
        panel.setAlignment(Pos.TOP_CENTER);
        panel.setPrefWidth(340);
        panel.setMaxWidth(340);
        panel.setPadding(new Insets(24));
        panel.getChildren().add(heading);
        return panel;
    }

    private Button createOverlayButton(String text, Runnable action) {
        Button button = new Button(text);
        button.getStyleClass().add("overlay-button");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnAction(event -> action.run());
        return button;
    }

    private void show(VBox panel) {
        getChildren().clear();
        Pane backdrop = new Pane();
        backdrop.getStyleClass().add("overlay-backdrop");
        getChildren().addAll(backdrop, panel);
        setVisible(true);
    }
}
