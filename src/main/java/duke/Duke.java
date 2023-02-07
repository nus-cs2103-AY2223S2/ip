package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import duke.exception.DukeException;
import duke.helper.Parser;
import duke.helper.Storage;
import duke.helper.TaskList;
import duke.helper.Ui;
import java.io.IOException;

public class Duke {
    Ui ui = new Ui();
    TaskList taskList = new TaskList();
    Parser parser = new Parser();
    Storage store = new Storage(taskList);

//    public static void main(String[] args) throws IOException {
//        ui.printGreeting();
//        String command = ui.readLine();
//        while (!command.equals("bye")) {
//            try {
//                parser.dispatch(command, ui, taskList);
//                store.saveTasks(taskList);
//            } catch (DukeException e) {
//                ui.printError(e);
//            }
//            command = ui.readLine();
//        }
//        ui.printBye();
//    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        assert !input.isEmpty();
        try {
            String message = parser.dispatch(input, ui, taskList);
            store.saveTasks(taskList);
            return message;
        } catch (DukeException e) {
            return ui.printError(e);
        }
    }
}
