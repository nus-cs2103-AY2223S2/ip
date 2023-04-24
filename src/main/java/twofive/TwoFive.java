package twofive;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javafx.application.Application;
import twofive.command.Command;
import twofive.data.TaskList;
import twofive.exception.TwoFiveException;
import twofive.storage.Storage;
import twofive.task.Task;
import twofive.ui.Ui;
import twofive.utils.Parser;

/**
 * Represents a chatbot that allows users to manage daily tasks.
 */
public class TwoFive {
    private TaskList tasks;
    private Storage storage;

    /**
     * Represents a constructor for the TwoFive class.
     *
     * @param filePath String containing file path.
     */
    public TwoFive(String filePath) throws IOException, TwoFiveException, DateTimeParseException {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public static void main(String[] args) {
        Application.launch(Ui.class, args);
    }

    /**
     * Returns a String which is the result of the command executed according to
     * the user's input.
     *
     * @param fullCommand String representing the command entered by the user.
     * @return String containing command execution result.
     */
    public String[] handleUserInput(String fullCommand) {
        String[] commandResult;
        try {
            Command c = Parser.parse(fullCommand);
            commandResult = new String[]{c.execute(tasks, storage), "-fx-text-fill: #000000"};
        } catch (TwoFiveException e) {
            commandResult = new String[]{e.getMessage(), "-fx-text-fill: #FF0000"};
        } catch (NumberFormatException e) {
            commandResult = new String[]{":( OOPS!!! The task number provided must be a number.", "-fx-text-fill: "
                    + "#FF0000"};
        }
        return commandResult;
    }

    public ArrayList<Task> getTaskList() {
        return tasks.getTasks();
    }
}
