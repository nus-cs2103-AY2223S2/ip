package duke;

import duke.tasks.Task;
import duke.tasks.TaskList;



/**
 * Deals with interactions with the user
 */
public class Ui {

    /**
     * Displays the initial message
     */
    public String initialDisplay() {
        String displayResult = "";
        displayResult += "Welcome! I'm Duke.\n";
        displayResult += "What can I do for you?\n";
        return displayResult;
    }

    /**
     * Displays text after adding task
     *
     * @param task the task object
     * @param listLength the length of the TaskList
     */
    public String taskAddDisplay(Task task, int listLength) {
        String displayResult = "";
        displayResult += "Got it. I've added this task:\n";
        displayResult += "\t" + task.toString() +'\n';
        displayResult += displayTasks(listLength);
        return displayResult;
    }

    /**
     * Displays text after deleting task
     *
     * @param list the TaskList storing all the tasks
     * @param taskIndices the index of task to be deleted
     */
    public String taskDeleteDisplay(TaskList list, String[] taskIndices) {
        String displayResult = "";
        displayResult += "Noted. I've removed the tasks:\n";
        for (int i = 0; i < taskIndices.length; i++) {
            int index = Integer.parseInt(taskIndices[i]);
            displayResult += "\t" + list.getTask(index).toString() + '\n';
        }

        return displayResult;
    }

    /**
     * Prints the list
     *
     * @param list the TaskList storing the Task objects
     */
    public String displayList(TaskList list) {
        String displayResult = "";
        int listSize = list.getListLength();
        if(listSize == 0) {
            return "You have no tasks...";
        }
        for(int i = 1; i <= listSize; i++) {
            displayResult += i + ". " + list.getTask(i).toString() + '\n';
        }
        return displayResult;
    }

    /**
     * Prints the number of tasks in the TaskList
     *
     * @param listLength the length of the TaskList
     */
    public String displayTasks(int listLength) {
        return String.format("Now you have %d tasks in the list.", listLength);
    }

    /**
     * Marks the task as incomplete
     *
     * @param task the Task object
     */
    public String unmarkTaskDisplay(Task task) {
        String displayResult = "";
        displayResult += "OK, I've marked this task as not done yet:\n";
        displayResult += "\t" + task.toString();
        return displayResult;
    }

    /**
     * Marks the task as complete
     *
     * @param task the Task object
     */
    public String markTaskDisplay(Task task) {
        String displayResult = "";
        displayResult += "Nice! I've marked this task as done:\n";
        displayResult += "\t" + task.toString();
        return displayResult;
    }

    /**
     * Displays text after exit message
     */
    public String exitDisplay() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Prints text if no matches found in the list
     */
    public String noMatchFoundDisplay() {
        return "No matches found...\n";
    }

    /**
     * Prints list if match is found
     * @param list the TaskList storing the Task objects
     */
    public String matchFoundDisplay(TaskList list) {
        String displayResult = "Here are the matching tasks in your list:\n";
        displayResult += displayList(list);
        return displayResult;
    }
}
