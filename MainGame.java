import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Explicit cross-package imports required for the launcher
import domainpackage.view.MainScreen;

public class MainGame extends Application
{
    @Override
    public void start(Stage primaryStage) {
        //FinanceModel model = new FinanceModel();
        MainScreen view = new MainScreen();
        //FinanceController controller = new FinanceController(model, view);
        
        Scene scene = new Scene(view.getRoot(), 400, 450);
        primaryStage.setTitle("EquiCity");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}