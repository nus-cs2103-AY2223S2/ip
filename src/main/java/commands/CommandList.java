package commands;

import features.DukeException;
import features.Storage;
import features.TaskList;
import features.Ui;

/**
 * Handles 'list' command.
 */
public class CommandList extends Command {
    /**
     * Finds and returns all tasks in the taskList in String form.
     * @param userInput The user's String input in array form.
     * @return The list confirmation message.
     * @throws DukeException Thrown if an error occurs.
     */
    @Override
    public String handle(String[] userInput) throws DukeException {
        // ERROR: list format is anything other than [ list ]s
        Ui ui = new Ui();
        TaskList taskList = new Storage().loadTaskList();
        if (userInput.length > 1) {
            throw new DukeException(ui.formatCommandError("list",
                    "list"));
        }
        if (taskList.size() == 0) {
            return ("You don't have anything to do right now!");
        } else {
            return ("Here are your tasks:\n" + listMake(taskList));
        }
    }
    /**
     * Generates a list of the tasks present in the input taskList.
     * @param taskList The user's TaskList.
     * @return The built list.
     */
    public String listMake(TaskList taskList) {
        StringBuilder newList = new StringBuilder();
        for (int i = 1; i < taskList.size() + 1; i++) {
            newList.append(i).append(". ").append(taskList.get(i - 1).toString());
            if (i < taskList.size()) {
                newList.append("\n");
            }
        }
        assert (!newList.toString().equals(""));
        return newList.toString();
    }
}
