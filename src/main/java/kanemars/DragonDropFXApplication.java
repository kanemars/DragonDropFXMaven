package kanemars;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;


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

    private void accessGoogleDrive () {

    }

    public static void main(String[] args) throws IOException, GeneralSecurityException{
        if (args.length > 0) {
            launch(args);
        } else {
            DriveQuickStart.TryDrive();

        }
    }
}
