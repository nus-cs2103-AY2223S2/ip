package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * Duke contains the main logic for the task management program.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private UI ui;
    private Parser parser;

    /**
     * Constructor for Duke.
     *
     * @param directory directory of the storage file.
     * @param path path of thr storage file
     */
    public Duke(String directory, String path) {
        storage = new Storage(directory, path);
        taskList = new TaskList();
        ui = new UI();
        parser = new Parser();
    }

    /**
     * Loads tasks from data file and print success/error message.
     */
    public void loadTasksFromFile() {
        assert storage != null;
        assert taskList != null;
        try {
            storage.loadTasks(taskList);
            System.out.println("Successfully loaded from data file");
        } catch (FileNotFoundException e) {
            System.out.println("No save data found, starting with empty list");
        } catch (IOException | DukeException e) {
            System.out.println("Error loading save data, starting with empty list");
        }
    }

    /**
     * Saves tasks to data file and print success/error message.
     */
    public void saveTasksToFile() {
        assert storage != null;
        assert taskList != null;
        try {
            storage.saveTasks(taskList);
            System.out.println("Successfully saved data to file");
        } catch (IOException e) {
            System.out.println("Error save data, data not saved!");
        }
    }

    /**
     * Get response from Duke according to commands from user.
     *
     * @param input Command from the user
     * @return Response from Duke
     */
    public String getResponse(String input) {
        String[] command = input.split(" ", 2);
        try {
            switch (command[0]) {
            case "bye":
                saveTasksToFile();
                return "";
            case "list":
                return taskList.listTasks();
            case "mark": {
                int taskNum = parser.getTaskNum(command);
                taskList.markTask(parser.getTaskNum(command));
                return ui.formatSuccessMessage("Nice! I've marked this task as done:",
                        taskList.getTask(taskNum));
            }
            case "unmark": {
                int taskNum = parser.getTaskNum(command);
                taskList.unmarkTask(taskNum);
                return ui.formatSuccessMessage("OK, I've marked this task as not done yet:",
                        taskList.getTask(taskNum));
            }
            case "delete": {
                int taskNum = parser.getTaskNum(command);
                Task removedTask = taskList.deleteTask(taskNum);
                return ui.formatTaskMessage("Noted. I've removed this task:",
                        removedTask, taskList.getSize());
            }
            case "find":
                String keyword = parser.getKeyword(command);
                return ui.formatFindResult(taskList.getTasksWithKeyword(keyword));
            case "todo":
            case "deadline":
            case "event":
                taskList.addTask(parser.getTaskToAdd(command));
                return ui.formatTaskMessage("Got it. I've added this task:",
                        taskList.getLatestTask(), taskList.getSize());
            default:
                throw new DukeException("I do not understand");
            }
        } catch (DukeException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return "Valid task number required";
        }
    }
}
