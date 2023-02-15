package commands;

import features.DukeException;
import features.Storage;
import features.TaskList;
import features.Ui;
import tasks.Task;
import tasks.ToDo;

/**
 * Handles 'todo' command.
 */
public class CommandToDo extends Command {
    /**
     * Adds a ToDo task to the taskList and returns a String form of the action.
     * @param userInput The user's String input in array form.
     * @return The todo addition confirmation message.
     * @throws DukeException Thrown if an error occurs.
     */
    @Override
    public String handle(String[] userInput) throws DukeException {
        Ui ui = new Ui();
        TaskList taskList = new Storage().loadTaskList();
        try {
            String toDoName = userInput[1];
            // ERROR: To-Do description is blank.
            if (toDoName.strip().length() == 0) {
                throw new DukeException(ui.formatLogicError("Tasks.ToDo description cannot be blank."));
            }
            assert (!toDoName.equals(""));
            Task taskToAdd = new ToDo(toDoName);
            taskList.add(new ToDo(toDoName));
            autoSave(taskList);
            return ("Task added:\n " + taskToAdd + "\n" + "There are now " + taskList.size()
                    + " task(s) in your list.");
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException err) {
            throw new DukeException(ui.formatCommandError("todo",
                    "todo <insert description>"));
        }
    }
}
