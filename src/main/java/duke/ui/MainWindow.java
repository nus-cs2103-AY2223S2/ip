package duke.ui;

import java.io.PrintStream;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import duke.App;
import duke.Duke;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainWindow implements Initializable {
    private Duke instance;

    private PrintStream outputStream;

    public void setDuke(Duke instance) {
        this.instance = instance;
        this.instance.setOutputStream(outputStream);
    }

    private LinkedList<String> prevCommands = new LinkedList<>();
    private int prevCommandIndex = -1;

    @FXML
    private TextArea outputTextBox;

    @FXML
    private TextField inputTextBox;

    @FXML
    private void closeDuke() {
        App.sendCloseRequest();
    }

    @FXML
    private void onSubmit() {
        String inputValue = inputTextBox.getText();
        if (inputValue == null || inputValue.isEmpty()) {
            // Ignore blank lines
            return;
        }

        outputStream.format("> %s\n", inputValue);
        inputTextBox.clear();
        this.instance.executeCommand(inputValue);

        prevCommands.push(inputValue);
        prevCommandIndex = -1;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inputTextBox.setOnKeyPressed(ke -> {
            switch (ke.getCode()) {
            case ENTER: {
                onSubmit();
                break;
            }
            case ESCAPE: {
                inputTextBox.clear();
                prevCommandIndex = -1;
                break;
            }
            case UP: {
                if (prevCommands.size() == 0) {
                    break;
                }

                prevCommandIndex++;
                if (prevCommandIndex == prevCommands.size()) {
                    prevCommandIndex = 0;
                }

                inputTextBox.setText(prevCommands.get(prevCommandIndex));
                break;
            } case DOWN: {
                if (prevCommands.size() == 0) {
                    break;
                }

                prevCommandIndex--;

                if (prevCommandIndex < 0) {
                    prevCommandIndex = prevCommands.size() - 1;
                }

                inputTextBox.setText(prevCommands.get(prevCommandIndex));
                break;
            }
            default: {
                return;
            }
            }
            ke.consume();
        });

        outputStream = new PrintStream(new TextBoxStream(outputTextBox), true);
    }
}
