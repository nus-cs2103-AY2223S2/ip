package duke;

import java.io.File;
import java.io.IOException;

import duke.commands.Command;
import duke.exceptions.EmptyCommandException;
import duke.exceptions.InvalidCommandValueException;
import duke.exceptions.InvalidDateException;
import duke.exceptions.InvalidTaskTypeException;
import duke.exceptions.InvalidTimeException;
import duke.exceptions.InvalidUndoException;
import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.utils.Parser;
import duke.utils.Storage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



/**
 * Duke is the class that represents the chat-bot.
 */
public class Duke extends Application {
    private static final File savedFile = new File("savedFile.txt");
    private TaskList commandList;
    private Ui ui;
    private Storage storage;
    private Parser parser;

    @Override
    public void start(Stage stage) throws Exception {
        parser = new Parser();
        ui = new Ui();
        storage = new Storage();
        boolean isSaveFileCreated = savedFile.exists();
        if (!isSaveFileCreated) {
            try {
                savedFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Error while creating save file: " + e);
            }
        }
        storage.loadFromFile(savedFile);
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
     * @param input The command that is to be parsed.
     * @return The response to be displayed.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input, commandList, storage, ui, savedFile);
            String response = command.action();
            return response;
        } catch (InvalidCommandValueException | InvalidTaskTypeException
                 | EmptyCommandException | InvalidTimeException | InvalidDateException
                 | InvalidUndoException e) {
            return ui.formatString(e.getMessage());
        }
    }
}


