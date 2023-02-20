package duke;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * duke.Ui class represents User interface when using the chatbox.
 */
public class Ui {
    public Ui() {
    }

    /**
     * Returns the starting message of the chatbot.
     *
     * @return The starting message of the chatbot.
     */
    public static String getIntroMessage() {
        return "Hello! I'm Duke\n What can I do for you?";
    }

    /**
     * Returns the outro message of the chatbot.
     *
     * @return The outro message of the chatbot.
     */
    public static String getOutroMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints the tasks list stored in the bot.
     *
     * @param taskList A duke.TaskList Object encapsulating the all tasks in the chatbot.
     * @return The message of the chatbot to list all tasks.
     */
    public static String getTaskListMessage(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        String tasksList = "";
        Task currentTask;
        for (int i = 0; i < tasks.size(); i++) {
            currentTask = tasks.get(i);
            tasksList += i + 1 + "." + currentTask.toString() + "\n";
        }
        return "Here are the tasks in your list:\n" + tasksList;
    }

    /**
     * Prints a message corresponding to the adding action of argument task.
     *
     * @param newTask The new task being added.
     * @param size The current size of task list.
     * @return The message from chatbot after adding a task.
     */
    public static String getAddTaskMessage(Task newTask, int size) {
        return "Got it. I've added this task:\n  "
                + newTask
                + "\nNow you have " + size + " tasks in the list";
    }

    /**
     * Prints a message corresponding to the marking action of the argument task.
     *
     * @param target The task being marked.
     * @return The message of the chatbot after marking a task.
     */
    public static String getMarkTaskMessage(Task target) {
        return "Nice! I've marked this task as above:\n  " + target;
    }

    /**
     * Prints a message corresponding to the unmarking action of the argument task.
     *
     * @param target The task being unmarked.
     * @return The message of the chatbot after unmarking a task.
     */
    public static String getUnmarkTaskMessage(Task target) {
        return "OK, I've marked this task as not done yet:\n  " + target;
    }

    /**
     * Prints a message corresponding to the deleting action of the argument task.
     *
     * @param target The duke.Task being deleted from the list.
     * @param size The current size of task list.
     * @return The message of the chatbot after deleting a task.
     */
    public static String getDeleteTaskMessage(Task target, int size) {
        return "Noted. I've removed this task:\n"
                + target + "\nNow you have " + size + " task(s) in the list";

    }

    /**
     * Prints a message corresponding to the finding action with the list of task found.
     *
     * @param resultedTasks An Stream of Task object encapsulating all tasks found.
     * @return The message of the chatbot after finding tasks by a keyword
     */
    public static String getFindTaskMessage(Stream<Task> resultedTasks) {
        Stream<String> searchResult = resultedTasks.map((task) -> task.toString());
        String listingResultMessage = searchResult.reduce("", (result, element) -> result + element + "\n");
        return "Here are the matching tasks in your list:\n" + listingResultMessage;
    }
}
