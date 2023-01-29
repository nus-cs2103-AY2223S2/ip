package util;

import duke.DeadlineTask;
import duke.Event;
import duke.ToDo;

/**
 * Parser class to handle command input from User.
 * @author Merrick
 */
public class Parser {
    /**
     * Static method to handle command input from User and carry out the appropriate actions.
     * @param command Action input from the user.
     * @param tasks TaskList object to manage tasks.
     * @return Indicates if the Duke Program should continue to run.
     * @throws DukeException
     */
    public static boolean handleGeneralCommand(String command, TaskList tasks, Storage storage) throws DukeException {
        if (command.startsWith("list")) {
            tasks.listTasks();
        } else if ((command.startsWith("mark")) || (command.startsWith("unmark"))
                || command.startsWith("delete")) {
            tasks.manageTask(command);
        } else if (command.equals("bye")) {
            storage.saveTasks(tasks);
            return false;
        } else if (command.startsWith("event")) {
            Event.createEvent(command, tasks);
        } else if (command.startsWith("deadline")) {
            DeadlineTask.createDeadlineTask(command, tasks);
        } else if (command.startsWith("todo")) {
            ToDo.createToDo(command, tasks);
        } else if (command.startsWith("find")) {
            tasks.findTasks(command);
        }
        else {
            throw new DukeException();
        }
        return true;
    }
}
