package kanemars;

import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.DoubleAccumulator;

public class DragonDropFXController implements Initializable {
    public TabPane tabPanes;
    public TextField findText;
    private static String newline = "\n";
    private static int newlineLength = newline.length();
    private final Clipboard clipboard = Clipboard.getSystemClipboard();

    public void initialize(URL location, ResourceBundle resources) {
        addFindTextFunctionality();

        copyFileContentsToTextAreas();
    }

    private void addFindTextFunctionality() {
        findText.textProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.length() > 1) {
                for (Tab tab : tabPanes.getTabs()) {
                    TextArea textArea = (TextArea) tab.getContent();
                    int offset = textArea.getText().toLowerCase().indexOf(newValue.toLowerCase());
                    if (offset != -1) {
                        textArea.selectRange(offset, offset + newValue.length());
                        tabPanes.getSelectionModel().select(tab);
                        return;
                    }
                }
            }
        });
    }

    private void copyFileContentsToTextAreas() {
        final String cutAndPasteFile = DragonDropFXApplication.parameters.get(0);
        try {
            var br = new BufferedReader(new InputStreamReader(new FileInputStream(cutAndPasteFile)));
            String strLine = br.readLine();

            if (strLine != null) {
                TextArea textArea;
                if (strLine.startsWith("#")) {
                    textArea = addTabAndReturnItsTextArea(strLine.substring(1));
                } else {
                    textArea = addTabAndReturnItsTextArea("Main");
                    textArea.appendText(strLine + newline);
                }
                while ((strLine = br.readLine()) != null) {
                    if (strLine.startsWith("#")) {
                        textArea = addTabAndReturnItsTextArea(strLine.substring(1));
                    } else {
                        textArea.appendText(strLine + newline);
                    }
                }
                //textArea.setScrollTop(Double.MIN_VALUE); Want to have the scrollbar at the top initially, when there is lots of text
            }
        } catch (IOException ex) {
            System.out.printf("%s%s%n", ex.getMessage(), Arrays.toString(ex.getStackTrace()));
        }
    }

    private TextArea addTabAndReturnItsTextArea(String tabTitle) {
        TextArea newArea = addTextArea();

        Tab tab = new Tab(tabTitle);
        tabPanes.getTabs().add(tab);
        tab.setContent(newArea);
        return newArea;
    }

    private TextArea addTextArea() {
        TextArea newArea = new TextArea();
        newArea.setOnMouseClicked((MouseEvent event) -> {
            if (newArea.getSelectedText().length() == 0) {
                int caret = newArea.getCaretPosition();
                int lineStart = caret;
                int lineEnd = caret;
                while (lineStart > 0 && !newArea.getText(lineStart, lineEnd).startsWith(newline))
                    lineStart--;

                if (newArea.getText(lineStart, lineEnd).startsWith(newline))
                    lineStart += newlineLength;

                lineEnd = newArea.getText().indexOf(newline, lineEnd);

                newArea.selectRange(lineStart, lineEnd);

            }
            copyToClipboard(newArea.getSelectedText().trim());

        });
        return newArea;
    }

    private void copyToClipboard(String s) {
        if (s.length() > 0) {
            final ClipboardContent content = new ClipboardContent();
            content.putString(s);
            clipboard.setContent(content);
        }
    }
}