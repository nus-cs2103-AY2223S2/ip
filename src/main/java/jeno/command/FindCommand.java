package jeno.command;

import jeno.exception.JenoException;
import jeno.storage.Note;
import jeno.storage.TaskList;
import jeno.task.Task;

import java.util.ArrayList;

/**
 * Class for FindCommand.
 */
public class FindCommand extends Command {

    /**
     * Constructor for FindCommand.
     * @param userInput UserInput.
     */
    public FindCommand(String userInput) {
        super(userInput);
    }

    /**
     * Get keyword from find command
     * @param input User input containing find command
     * @return String representation of keyword
     */
    public String getFindKeyword(String input) throws JenoException {
        assert input.length() > 5: "Find keyword cannot be empty";
        try {
            input.substring(5);
        } catch (StringIndexOutOfBoundsException e) {
            throw new JenoException("Oops! Please enter a keyword.");
        }
        return input.substring(5);
    }

    /**
     * List all tasks in a task list.
     * @param tasks Task list.
     * @return List of all tasks in String format.
     */
    public String listTasks(ArrayList<Task> tasks) {
        String returnMessage = "";
        int taskNumber = 1;
        for (int i = 0; i < tasks.size(); i++) {
            String foundTask = taskNumber + ". " + tasks.get(i).toString() + "\n";
            returnMessage += foundTask;
            taskNumber++;
        }
        return returnMessage;
    }

    /**
     * Executes user input and finds a list of task matching specified keyword.
     * @param tasks Current TaskList.
     * @param notes Current Note.
     * @return List of tasks which match keyword in String format.
     */
    @Override
    public String execute(TaskList tasks, Note notes) throws JenoException {
        String keyword = getFindKeyword(userInput);
        ArrayList<Task> foundTasks = tasks.filterTasks(keyword);
        if (foundTasks.size() == 0) {
            return "There are no matching tasks\n";
        } else {
            return "Here are the matching tasks in your list: \n"
                    + listTasks(foundTasks);
        }
    }
}
