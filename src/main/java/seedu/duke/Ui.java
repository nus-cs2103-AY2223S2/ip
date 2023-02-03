package seedu.duke;

import java.util.Scanner;

/**
 * Ui object class.
 */
public class Ui {
    /** Scanner to take user input */
    private final Scanner sc;

    /**
     * Creates a Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns text to display on bot initialization.
     *
     * @return String for welcome.
     */
    public String spawnBot() {
        return "HEY! I'm GRUMMY!\nHow can I help you?";
    }

    /**
     * Returns text to display on bot exit.
     *
     * @return String for exit.
     */
    public String displayExit() {
        return "Goodbye! Hope to see you again :>";
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @param taskList the TaskList to return the number of tasks from.
     *
     * @return String for tasks left.
     */
    public String displayTaskList(TaskList taskList) {
        assert taskList.size() >= 0: "TaskList length cannot be negative.";
        if (taskList.size() == 0) {
            return "You have no tasks. Hooray!";
        } else {
            int index = 1;
            String list = "";
            while (index < (taskList.size() + 1)) {
                list = list + "\n   " + index + ". " + taskList.get(index - 1).toString();
                index++;
            }
            return "Here are your tasks:" + "\n" + list;
        }
    }

    /**
     * Returns the number of found tasks in the TaskList.
     *
     * @param taskList the TaskList to return the number of found tasks from.
     * @param keyword the keyword to search for.
     *
     * @return String the number of tasks in the TaskList
     */
    public String displayFindList(TaskList taskList, String keyword) {
        assert taskList.size() >= 0: "TaskList length cannot be negative.";
        if (taskList.size() == 0) {
            return "There are no such tasks.";
        } else {
            int index = 1;
            String list = "";
            while (index < (taskList.size() + 1)) {
                list = list + "\n   " + index + ". " + taskList.get(index - 1).toString();
                index++;
            }
            return "Here are the tasks with the keyword: " + keyword + "\n" + list + "\n" + checkList(taskList);
        }
    }

    /**
     * Returns text when a task is marked.
     *
     * @param taskList the TaskList to mark the task.
     * @param index    the index of the task to be marked.
     *
     * @return String for marked.
     */
    public static String displayMarked(TaskList taskList, int index) {
        assert taskList.size() >= 0: "TaskList length cannot be negative.";
        String markingTask = "Alright, marking this task as done:";
        return markingTask + "\n\n" + indent(taskList.get(index).toString());
    }

    /**
     * Returns text when a task is unmarked.
     *
     * @param taskList the TaskList to unmark the task.
     * @param index    the index of the task to be unmarked.
     *
     * @return String for unmarked.
     */
    public static String displayUnmarked(TaskList taskList, int index) {
        assert taskList.size() >= 0: "TaskList length cannot be negative.";
        String unmarkingTask = "Alright, marking this task not done yet:";
        return unmarkingTask + "\n\n" + indent(taskList.get(index).toString());
    }

    /**
     * Returns text when a task is deleted.
     *
     * @param taskList the TaskList to delete the task from.
     * @param index    the index of the task to be removed.
     *
     * @return String for deleted.
     */
    public static String displayDelete(TaskList taskList, int index) {
        assert taskList.size() >= 0: "TaskList length cannot be negative.";
        String removingTask = "Noted, removing this task:";
        Task t = taskList.get(index);
        return removingTask + "\n\n" + indent(t.toString());
    }

    /**
     * Indents the current line.
     *
     * @param output String of the response to be indented.
     *
     * @return Indented output.
     */
    // Create indents
    public static String indent(String output) {
        return "    " + output;
    }

    /**
     * Checks the grammar of the TaskList return.
     *
     * @param taskList the TaskList to check.
     *
     * @return String for TaskList size.
     */
    public static String checkList(TaskList taskList) {
        assert taskList.size() >= 0: "TaskList length cannot be negative.";
        if (taskList.size() == 1) {
            return "You have 1 task in the list.";
        } else if (taskList.size() == 0) {
            return "You have no tasks in the list.";
        } else {
            return "You have " + taskList.size() + " tasks in the list.";
        }
    }

    /**
     * The text when a task is added.
     *
     * @return String for addedTask.
     */
    public static String addedTask() {
        return "Got it. I've added this task:\n";
    }
}
