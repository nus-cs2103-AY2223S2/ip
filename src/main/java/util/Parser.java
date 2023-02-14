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
     * @return Output to be shown to the user.
     * @throws DukeException
     */
    public static String handleGeneralCommand(String command, TaskList tasks, Storage storage, Ui ui) {
        assert tasks != null : "TaskList should be already initialised";
        assert storage != null : "Storage should be already initialised";
        assert ui != null : "UI should already be initialised";
        try {
            if (command.startsWith("list")) {
                return tasks.listTasks();
            } else if ((command.startsWith("mark")) || (command.startsWith("unmark"))
                    || command.startsWith("delete")) {
                return tasks.manageTask(command);
            } else if (command.equals("bye")) {
                storage.saveTasks(tasks);
                return ui.closeCommand();
            } else if (command.startsWith("event")) {
                return Event.createEvent(command, tasks);
            } else if (command.startsWith("deadline")) {
                return DeadlineTask.createDeadlineTask(command, tasks);
            } else if (command.startsWith("todo")) {
                return ToDo.createToDo(command, tasks);
            } else if (command.startsWith("find")) {
                return tasks.findTasks(command);
            } else if (command.startsWith("snooze")) {
                return tasks.snoozeTask(command);
            }
        } catch (DukeException e) {
            return e.toString();
        }
        return "Sorry I do not understand your command :(";
    }
}
