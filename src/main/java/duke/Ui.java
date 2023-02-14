package duke;

/**
 * Handles interactions with the users.
 */
public class Ui {

    /**
     * Creates new UI object.
     */
    public Ui() {
        dukeGreeting();
    }

    /**
     * Prints out the initial duke greeting when the program first runs.
     */
    public static String dukeGreeting() {
        String greeting = "";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        greeting = "Hello from\n" + logo + "Hello! I'm Duke\n" + "What can I do for you?";
        return greeting;
    }

    /**
     * Shows marking of task.
     *
     * @param taskToMark The marked task.
     * @return Response to let the user know the task has been marked.
     */
    public String showMark(Task taskToMark) {
        return "Nice! I've marked this task as done:\n" + taskToMark;
    }

    /**
     * Shows unmarking of task.
     *
     * @param taskToUnmark The unmarked task.
     * @return Response to let the user know the task has been unmarked.
     */
    public String showUnmark(Task taskToUnmark) {
        return "OK, I've marked this task as not done yet:\n" + taskToUnmark;
    }

    /**
     * Shows all tasks after adding of tasks.
     *
     * @param task Task that was added.
     * @param size Size of the list of tasks.
     * @return Response to let the user know task has been added.
     */
    public String showTaskOutput(Task task, int size) {
        return "Got it. I've added this task:\n" + task + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Shows removing of tasks.
     *
     * @param task Task that was removed.
     * @param size Size of the list of tasks.
     * @return Response to let the user know task has been removed.
     */
    public String showDelete(Task task, int size) {
        return "Noted. I've removed this task:\n" + task + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Shows updating of tasks.
     *
     * @param taskToUpdate Task that was updated.
     * @return Response to let the user know task has been updated.
     */
    public String showUpdate(Task taskToUpdate) {
        return "OK, I've updated this task:\n" + taskToUpdate;
    }

    /**
     * Shows all matching tasks.
     *
     * @param tasklist The list of tasks to match the keyword with.
     * @return Response to show users the matching tasks.
     */
    public String printMatchingTasks(TaskList tasklist) {
        String result = "Here are the matching tasks in your list:\n";
        if (tasklist.getNumberOfTasks() == 0) {
            return "There are no matching tasks in your list.";
        }
        for (int i = 1; i < tasklist.getNumberOfTasks() + 1; i++) {
            result += i + ". " + tasklist.getTask(i - 1) + "\n";
        }
        return result;
    }

    /**
     * Shows error message.
     *
     * @param e Exception with error message.
     * @return Response to show error message.
     */
    public String errorMessage(Exception e) {
        return e.getMessage();
    }

    /**
     * Terminates the program.
     */
    public String exit() {
        System.exit(0);
        return "Bye. Hope to see you again soon!";
    }
}
