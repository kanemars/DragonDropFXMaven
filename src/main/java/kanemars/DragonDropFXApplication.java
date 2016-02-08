/**
 * Created by P10158774 on 05/02/2016.
 */

package kanemars;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;

public class DragonDropFXApplication extends Application {

    public static List<String> parameters;

    @Override
    public void start(Stage primaryStage) throws Exception{
        parameters = getParameters().getRaw();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("dragondrop.fxml"));
        Image ico = new Image("Dragon-Age-Origins-new-4-icon.png");
        primaryStage.getIcons().add(ico);
        primaryStage.setTitle("Dragon Drop FX");
        primaryStage.setScene(new Scene(root, 700, 1000));

        primaryStage.show();
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            launch(args);
        }
    }
}
