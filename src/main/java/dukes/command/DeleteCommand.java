package dukes.command;

import dukes.util.DukeException;
import dukes.util.TaskList;
import dukes.util.UI;
import dukes.util.Storage;

import dukes.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Subclass of Command that handles the command to delete task from list.
 */
public class DeleteCommand extends Command {

    /**
     * Constructor of DeleteCommand class.
     *
     * @param isExit show if the command is an ExitCommand.
     * @param isValid show if the command is valid.
     * @param header the type the command belongs to, e.g. "add", "delete".
     * @param body the string index of the task to be deleted.
     */
    public DeleteCommand(boolean isExit, boolean isValid,
                       String header, String body) {
        // here body = index
        super(isExit, isValid, header, body);
    }

    /**
     * Delete the specific task from task list;
     * save the new task list to hard disk, and provide feedback to user.
     *
     * @param tasks contains the task list.
     * @param ui the UI in charge of user interactions.
     * @param storage handles the loading and saving of files.
     * @throws DukeException if the index provided is out of bounds.
     * @return method feedback
     */
    public String runCommand(TaskList tasks, UI ui, Storage storage) throws DukeException {
        List<Task> taskList = tasks.getTaskList();
        int index = Integer.parseInt(body);
        try {
            Task theTask = taskList.get(index - 1);
            taskList.remove(theTask);
            storage.save(tasks);
            return ui.returnDelete(theTask, tasks);
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("You have entered an invalid index.");
        }
    }
}
