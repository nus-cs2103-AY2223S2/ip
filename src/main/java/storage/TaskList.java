package storage;
import java.util.ArrayList;

import exceptions.DukeException;
import exceptions.LoadTaskException;
import parser.Parser;
import tasks.Task;

/**
 * This class denotes a task list where users keep track of their tasks
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Creates a task list containing input data
     *
     * @param data string data from list to import
     * @throws LoadTaskException
     */
    public TaskList(String data) throws LoadTaskException {

        String[] arr = data.split("\n");

        for (int i = 0; i < arr.length; i++) {

            String[] taskStrings = arr[i].split("\\|");

            String[] str = new String[taskStrings.length];
            str[0] = taskStrings[0];
            for (int j = 2; j < taskStrings.length; j++) {
                str[j - 1] = taskStrings[j];
                if (j > 2) {
                    str[j - 1] = Parser.stringToParsedDateString(str[j - 1]);
                }
            }
            Task task = null;
            try {
                task = Task.createTask(str);
            } catch (DukeException e) {
                throw new LoadTaskException();
            }

            // all tasks are initiated as unmarked
            if (taskStrings[1].equals("true")) {
                task.mark();
            }
            this.add(task);
        }
    }

    /**
     * Creates an empty task list
     */
    public TaskList() {}

    /**
     * Gets all tasks in this task list as a string output
     *
     * @return string of all tasks in this task list
     */
    public String getAllAsString() {
        String s = "";
        for (int i = 0; i < this.size(); i++) {
            s += "\n    " + (i + 1) + ") " + this.get(i);
        }
        return s;
    }

    /**
     * Gets the size of the task list
     *
     * @return string of Duke's interpretation of the number of tasks in the task list
     */
    public String getSizeAsString() {
        int size = this.size();
        if (size == 0) {
            return "There are currently no items in your list.";
        } else {
            return "Currently, your list has " + size + " task"
                    + (size == 1
                        ? "."
                        : "s.");
        }
    }
}
