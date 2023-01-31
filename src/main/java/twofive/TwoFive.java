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

    public String handleUserInput(String fullCommand) {
        String commandResult = "";
        try {
            Command c = Parser.parse(fullCommand);
            commandResult = c.execute(tasks, storage);
        } catch (TwoFiveException e) {
            commandResult = e.getMessage();
        } catch (NumberFormatException e) {
            commandResult = ":( OOPS!!! The task number provided must be a number.";
        }
        return commandResult;
    }

    public ArrayList<Task> getTaskList() {
        return tasks.getTasks();
    }
}
