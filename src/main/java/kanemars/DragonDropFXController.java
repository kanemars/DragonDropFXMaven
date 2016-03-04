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
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by P10158774 on 05/02/2016.
 */
public class DragonDropFXController implements Initializable {
    public TabPane tabPanes;
    public TextField findText;
    static String newline = "\n";
    static int newlineLength = newline.length();
    final Clipboard clipboard = Clipboard.getSystemClipboard();
    ArrayList<TextArea> textAreas = new ArrayList<TextArea>();

    public void initialize(URL location, ResourceBundle resources) {
        final String cutAndPasteFile = DragonDropFXApplication.parameters.get(0);
        findText.textProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.length() > 1) {
                for (TextArea t : textAreas) {
                    int offset = t.getText().indexOf(newValue);
                    if (offset != -1) {
                        t.selectRange(offset, offset + newValue.length());

                        return;
                    }
                }
            }
        });


        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(cutAndPasteFile)));
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
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage() + ex.getStackTrace());
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
        textAreas.add(newArea);

        newArea.setOnMouseClicked((MouseEvent event) -> {
            if (newArea.getSelectedText().length() == 0) {
                int caret = newArea.getCaretPosition();
                int lineStart = caret;
                int lineEnd = caret;
                while (lineStart > 0 && !newArea.getText(lineStart, lineEnd).startsWith(newline))
                    lineStart--;

                // Adjustments
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

    {
    }
}