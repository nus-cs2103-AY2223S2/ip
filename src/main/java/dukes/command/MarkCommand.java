package dukes.command;

import dukes.util.*;
import dukes.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Subclass of Command that handles the command to mark task as done or undone.
 */
public class MarkCommand extends Command {
    protected int action;

    /**
     * Constructor of MarkCommand class.
     *
     * @param isExit show if the command is an ExitCommand.
     * @param isValid show if the command is valid.
     * @param header the type the command belongs to, e.g. "add", "delete".
     * @param body string index of the task to be marked.
     * @param action identifies mark from unmark command. action = 0 for mark, = 1 for unmark.
     */
    public MarkCommand(boolean isExit, boolean isValid,
                      String header, String body, int action) {
        // here body = index
        super(isExit, isValid, header, body);
        this.action = action;
    }

    /**
     * Mark the specific task from task list as done or undone;
     * save the new task list to hard disk, and provide feedback to user.
     *
     * @param tasks contains the task list.
     * @param ui the UI in charge of user interactions.
     * @param storage handles the loading and saving of files.
     * @throws DukeException if the index provided is out of bounds.
     */
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        List<Task> taskList = tasks.getTaskList();
        int index = Integer.parseInt(body);
        try {
            Task theTask = taskList.get(index - 1);
            if (this.action == 0) {
                theTask.setDone();
            } else {
                theTask.setUnDone();
            }
            ui.showMark(theTask, action);
            storage.save(tasks);
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("You have entered an invalid index.");
        }
    }
}
