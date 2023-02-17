import java.util.ArrayList;

/**
 * Ui class represents User interface when using the chatbox.
 */
public class Ui {
    private static final String DIVIDER_LINE = "____________________________________________________\n";

    public Ui() {
    }

    /**
     * Prints the welcoming message.
     */
    public void start() {
        reply("Hello! I'm Duke\n What can I do for you?" + "\n");
    }

    public static void displayOutro() {
        reply("Bye. Hope to see you again soon!" + "\n");
    }

    /**
     * Returns an UI formatted message from the string argument.
     *
     * @param command A string message need to put in the write UI formatted message.
     */
    public static void reply(String command) {
        System.out.println(DIVIDER_LINE + command + DIVIDER_LINE);
    }

    /**
     * Prints the tasks list stored in the bot.
     *
     * @param taskList A TaskList Object encapsulating the all tasks in the chatbot.
     */
    public static void displayTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        String tasksList = "";
        Task currentTask;
        for (int i = 0; i < tasks.size(); i++) {
            currentTask = tasks.get(i);
            tasksList += i + 1 + "." + currentTask.toString() + "\n";
        }
        reply("Here are the tasks in your list:\n" + tasksList);
    }

    /**
     * Prints a message corresponding to the adding action of argument task.
     *
     * @param newTask The new task being added.
     * @param size The current size of task list.
     */
    public static void getAddTaskMessage(Task newTask, int size) {
        reply("Got it. I've added this task:\n  "
                + newTask
                + "\nNow you have " + size + " tasks in the list\n");
    }

    /**
     * Prints a message corresponding to the marking action of the argument task.
     *
     * @param target The task being marked.
     */
    public static void getMarkTaskMessage(Task target) {
        reply("Nice! I've marked this task as above:\n  " + target + "\n");
    }

    /**
     * Prints a message corresponding to the unmarking action of the argument task.
     *
     * @param target The task being unmarked.
     */
    public static void getUnmarkTaskMessage(Task target) {
        reply("OK, I've marked this task as not done yet:\n  " + target + "\n");
    }

    /**
     * Prints a message corresponding to the deleting action of the argument task.
     *
     * @param target The Task being deleted from the list.
     * @param size The current size of task list.
     */
    public static void getDeleteTaskMessage(Task target, int size) {
        reply("Noted. I've removed this task:\n"
                + target + "\nNow you have " + size + " task(s) in the list\n");

    }
}
