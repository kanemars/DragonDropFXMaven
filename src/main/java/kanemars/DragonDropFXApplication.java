package kanemars;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.List;

public class DragonDropFXApplication extends Application {

    static List<String> parameters;

    @Override
    public void start(Stage primaryStage) throws Exception{
        parameters = getParameters().getRaw();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("dragondrop.fxml"));
        Image ico = new Image("Dragon-Age-Origins-new-4-icon.png");
        primaryStage.getIcons().add(ico);
        primaryStage.setTitle("Dragon Drop FX");
        primaryStage.setScene(new Scene(root, 700, 600));

        primaryStage.show();
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            launch(args);
        }
        else {
            System.out.println("Usage: java kanemars.DragonDropFXApplication localFileNameAndPath");
            System.out.println("Usage: java kanemars.DragonDropFXApplication -drive fileNameOnGoogleDrive");
        }
    }
}
