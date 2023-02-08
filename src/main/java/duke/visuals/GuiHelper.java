package duke.visuals;

import duke.helpers.Parser;
import duke.helpers.Storage;
import duke.helpers.TaskList;
import duke.helpers.Ui;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.PrintWriter;

public class GuiHelper {

    public static void addEventListeners
            (Button sendButton, TextField userInput, VBox dialogContainer,
             PrintWriter pw, String textDir, TaskList tasks, Stage stage,
             Image user, Image muse, ScrollPane scrollPane) {
        sendButton.setOnMouseClicked((event) -> {
            if (userInput.getText().equals("bye")) {
                try {
                    handleClose(pw, textDir, tasks);
                    stage.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            Parser.handleInputs(dialogContainer, tasks, userInput, user, muse);
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            if (userInput.getText().equals("bye")) {
                try {
                    handleClose(pw, textDir, tasks);
                    stage.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            Parser.handleInputs(dialogContainer, tasks, userInput, user, muse);
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable -> {
            scrollPane.setVvalue(1.0);
        }));
    }

    private static void handleClose(PrintWriter pw, String textDir, TaskList tasks) throws IOException {
        Storage.saveData(pw, textDir, tasks);
        Ui.doFarewell();
    }
}
