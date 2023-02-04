package dukes.command;

import dukes.util.DukeException;
import dukes.util.TaskList;
import dukes.util.UI;
import dukes.util.Storage;

import dukes.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Subclass of Command that handles the command to list tasks.
 */
public class ListCommand extends Command {
    protected LocalDate date;
    /** Identifies list from search. action = 0 for list, = 1 for search. */
    protected int action;

    /**
     * Constructor of ListCommand class. Used by list command.
     *
     * @param isExit show if the command is an ExitCommand.
     * @param isValid show if the command is valid.
     * @param header the type the command belongs to, e.g. "add", "delete".
     * @param body nothing.
     */
    public ListCommand(boolean isExit, boolean isValid,
                       String header, String body) {
        // here body = index
        super(isExit, isValid, header, body);
        this.date = LocalDate.parse("1970-01-01");
        this.action = 0;
    }

    /**
     * Constructor of ListCommand class. Used by search command.
     *
     * @param isExit show if the command is an ExitCommand.
     * @param isValid show if the command is valid.
     * @param header the type the command belongs to, e.g. "add", "delete".
     * @param body nothing.
     * @param theDate the date for the task to be searched.
     */
    public ListCommand(boolean isExit, boolean isValid,
                       String header, String body, LocalDate theDate) {
        // here body = index
        super(isExit, isValid, header, body);
        this.date = theDate;
        this.action = 1;
    }


    /**
     * List all the task, or the specific task in the given date, from the task list.
     * Then return the list string.
     *
     * @param tasks contains the task list.
     * @param ui the UI in charge of user interactions.
     * @param storage handles the loading and saving of files.
     * @throws DukeException if the index provided is out of bounds.
     * @return method feedback
     */
    public String runCommand(TaskList tasks, UI ui, Storage storage) throws DukeException {
        List<Task> taskList = tasks.getTaskList();
        if (action == 0) { // just list
            String output = processList(taskList);
            return ui.returnList(output, 0);
        } else { // search
            List<Task> targetTasks = new ArrayList<>();
            int counter = 0;
            for (int i = 0; i < taskList.size(); i++) {
                Task theTask = taskList.get(i);
                if (isWithinDate(theTask)) {
                    counter += 1;
                    targetTasks.add(theTask);
                }
            }
            String output = processList(targetTasks);

            return ui.returnList(output, 1);
        }
    }

    /**
     * Private util method to check if the task is related to the date
     *
     * @param theTask the target task
     * @return if the task is related to the given date
     */
    private boolean isWithinDate(Task theTask) {
        boolean hasDeadline = theTask.getTag().equals("D") &&
                theTask.getDeadLine().equals(date);
        boolean hasEvent = theTask.getTag().equals("E") &&
                (theTask.getStart().isBefore(date) || theTask.getStart().equals(date)) &&
                (theTask.getEnd().isAfter(date) || theTask.getEnd().equals(date));
        return hasDeadline || hasEvent;
    }
}
