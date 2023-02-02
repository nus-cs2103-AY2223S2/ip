package duke;

import duke.commands.Command;
import duke.exceptions.*;
import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.utils.Parser;
import duke.utils.Storage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Duke is the class that represents the chat bot.
 */
public class Duke extends Application {
    private TaskList commandList;
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private static final File savedFile = new File("savedFile.txt");

    @Override
    public void start(Stage stage) throws Exception {
        parser = new Parser();
        ui = new Ui();
        storage = new Storage();
        storage.loadFromFile(new File("savedFile.txt"));
        commandList = new TaskList();
        commandList.setTaskList(this.storage.getStorage());
        loadMainWindow(stage);
    }

    private void loadMainWindow(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Performs an action in response to the command and return the response.
     *
     * @return The Response to be displayed.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input, commandList, storage, ui, savedFile);
            String response = command.action();
            return response;
        } catch (InvalidCmdValueException | InvalidTaskTypeException |
                 EmptyCommandException | InvalidTimeException | InvalidDateException e) {
            return Ui.HORIZONTAL_LINE + "\n" + e.getMessage() + "\n" + Ui.HORIZONTAL_LINE;
        }
    }
}


