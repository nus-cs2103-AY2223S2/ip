package duke.ui;

import java.util.ArrayList;

import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * Ui class to perform the outputting to user
 */
public class Ui {
    private static final String INTRO = "Hello! I'm Duke\nWhat can I do for you?\n";
    private static final String OUTRO = "Bye. Hope to see you again soon!\n";

    public Ui() {}

    /**
     * Returns the intro of Duke CLI
     *
     * @return output string
     */
    public String getIntro() {
        return Ui.INTRO;
    }

    /**
     * Returns the outro of Duke CLI
     *
     * @return output string
     */
    public String getOutro() {
        return Ui.OUTRO;
    }

    /**
     * Format the string and tasklist size to return to user
     *
     * @param s String to be wrapped
     * @param size Size of TaskList
     * @return Formatted string
     */
    public String wrap(String s, int size) {
        //wrap string with the correct indentation and lines when returning add task string
        //assumes s contains the nextline character
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n  ").append(s);
        sb.append(String.format("Now you have %d tasks in the list.\n", size));
        return sb.toString();
    }

    /**
     * Returns the ouput string after adding a Deadline
     *
     * @param taskString String version of the Deadline task
     * @param size Size of TaskList after adding
     *
     * @return output string
     */
    public String showAddDeadlineResult(String taskString, int size) {
        return this.wrap(taskString, size);
    }

    /**
     * Print the output after adding an Event
     *
     * @param taskString String version of Event task
     * @param size Size of TaskList after adding
     */
    public String showAddEventResult(String taskString, int size) {
        return this.wrap(taskString, size);
    }

    /**
     * Returns the output string after adding Todo
     *
     * @param taskString String version of Todo task
     * @param size Size of TaskList after adding
     */
    public String showAddTodoResult(String taskString, int size) {
        return this.wrap(taskString, size);
    }

    /**
     * Returns the string output after marking a Task as done
     *
     * @param taskString
     * @param index
     * @return output string
     */
    public String showMarkResult(String taskString, int index) {
        String s = "Nice! I've marked this task as done:\n";
        s += "  " + taskString;
        return s;
    }

    /**
     * Returns the string output after marking Task as undone
     *
     * @param taskString
     * @param index
     * @return output string
     */
    public String showUnmarkResult(String taskString, int index) {
        String s = "OK, I've marked this task as not done yet:\n";
        s += "  " + taskString;
        return s;
    }

    /**
     * Returns the string output after deleting Task
     *
     * @param taskString String version of Task deleted
     * @param size Size of TaskList after delete
     * @return output string
     */
    public String showDeleteResult(String taskString, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:\n  ").append(taskString);
        sb.append(String.format("Now you have %d tasks in the list.\n", size));
        return sb.toString();
    }

    /**
     * Return the string version of all the task matching to user input
     *
     * @param taskArr ArrayList containing tasks with matching taskname
     * @return output string
     */
    public String showFindTaskResult(ArrayList<Task> taskArr) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        int counter = 1;
        for (Task task : taskArr) {
            sb.append(String.format("%s%d.%s", " ", counter, task.toString()));
            counter += 1;
        }
        return sb.toString();
    }

    /**
     * Return the string version of all the Task in TaskList
     * after user calls 'list' command
     *
     * @param tl TaskList containing all the task
     * @return output string
     */
    public String showGetAllTaskResult(TaskList tl) {
        StringBuilder s = new StringBuilder();
        s.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tl.getSize(); i++) {
            s.append(String.format("%s%d.%s", " ", i + 1, tl.getTask(i).toString()));
        }
        return s.toString();
    }
}
