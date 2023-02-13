package duke;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulates the messages seen in the User Interface.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Creates a new UI object.
     */
    Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Returns the user input.
     * @return The string format of the scanned user input.
     */
    String readInput() {
        return scanner.nextLine();
    }

    /**
     * Closes the scanner.
     */
    void close() {
        scanner.close();
    }

    /**
     * Prints a formatted message.
     * @param s The string input to be printed.
     */
    public String printMessage(String s) {
        return ("\t" + s);
    }

    /**
     * Prints a greeting message to the user.
     * @return the greeting message in String format.
     */
    public String greetingMessage() {
        return printMessage("Hello! I'm Duke\n\tWhat can I do for you?");
    }

    /**
     * Prints a goodbye message to the user.
     * @return The goodbye message in String format.
     */
    public String goodbyeMessage() {
        return printMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a formatted message showing that a task has been added to the list.
     * @param t The Task object to be added to the list.
     * @param size The size of the list after adding the new Task.
     * @return The message that tells user that a task has been added.
     */
    public String addedTaskMessage(Task t, int size) {
        String s1 = ("\tGot it. I've added this task:");
        String s2 = ("\n\t" + t);
        String s3 = ("\n\tNow you have " + size + " tasks in the list.");
        return s1 + s2 + s3;
    }

    /**
     * Prints a formatted message showing that a task has been deleted from the list.
     * @param t The Task object to be deleted from the list.
     * @param size The size of the list after the task has been deleted.
     * @return the message in string format that shows the deleted task and the number of tasks left.
     */
    public String deletedTaskMessage(Task t, int size) {
        String s1 = ("\tNoted. I've removed this task:");
        String s2 = ("\n\t" + t);
        String s3 = ("\n\tNow you have " + size + " tasks in the list");
        return s1 + s2 + s3;
    }

    /**
     * Prints a message that is displayed if there are any matching tasks to the query.
     * @return the message that is seen before printing found tasks.
     */
    public String findTasksMessage() {
       return ("Here are the matching tasks in your list:");
    }

    /**
     * Prints the tasks found using the query function.
     * @param taskList The TaskList object containing the ArrayList of tasks
     * @param taskNumbers The Integer ArrayList containing the task numbers to retrieve from the TaskList.
     * @return the String notation of the tasks in the StringBuilder
     */
    public String printFoundTasks(TaskList taskList, ArrayList<Integer> taskNumbers) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < taskNumbers.size(); j++) {
            int tempTaskNumber = taskNumbers.get(j);
            Task tempTask = taskList.getTask(tempTaskNumber);
            sb.append(String.format("\n\t%d. %s", tempTaskNumber, tempTask));
        }
        return sb.toString();
    }

    /**
     * Prints a message to confirm that the task has been marked as done.
     * @param t The Task object to be marked as done.
     * @return the message in String format that shows the task modified
     */
    public String markTaskAsDoneMessage(Task t) {
        return printMessage("Nice! I've marked this task as done:\n\t" + t);
    }

    /**
     * Prints a message to confirm that the task has been marked as incomplete.
     * @param t The Task object to be marked as incomplete.
     * @return the message in String format that shows the task modified
     */
    public String markTaskAsIncompleteMessage(Task t) {
        return printMessage("OK, I've marked this task as not done yet:\n\t" + t);

    }

    /**
     * Prints the content of ArrayList of Task objects.
     * @param tasks The ArrayList of Tasks to be printed.
     * @return the String format of the StringBuilder containing the tasks in the list.
     */
    public String printList(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            int taskNumber = i + 1;
           sb.append(String.format("\t%d. %s\n", taskNumber, tasks.get(i)));
        }
        return sb.toString();
    }
}