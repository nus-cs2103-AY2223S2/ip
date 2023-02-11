package duke;

/**
 * Handles interactions with the users.
 */
public class Ui {

    public Ui() {
        dukeGreeting();
    }

    /**
     * Prints out the initial duke greeting when the program first runs.
     */
    public String dukeGreeting() {
        String greeting = "";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        greeting = "Hello from\n" + logo + "Hello! I'm Duke\n" + "What can I do for you?";
        return greeting;
    }

    public String showMark(Task taskToMark) {
        return "Nice! I've marked this task as done:\n" + taskToMark;
    }

    public String showUnmark(Task taskToUnmark) {
        return "OK, I've marked this task as not done yet:\n" + taskToUnmark;
    }

    public String showTaskOutput(Task task, int size) {
        return "Got it. I've added this task:\n" + task + "\nNow you have " + size + " tasks in the list.";
    }

    public String showDelete(Task task, int size) {
        return "Noted. I've removed this task:\n" + task + "\nNow you have " + size + " tasks in the list.";
    }

    public String printMatchingTasks(TaskList tasklist) {
        String result = "Here are the matching tasks in your list:\n";
        for (int i = 1; i < tasklist.getNumberOfTasks() + 1; i++) {
            result += i + ". " + tasklist.getTask(i - 1) + "\n";
        }
        return result;
    }

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
