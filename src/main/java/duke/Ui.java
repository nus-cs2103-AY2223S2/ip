package duke;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    /**
     * Prints welcome message.
     */
    public static String showWelcome() {
        String output = "Hello from DUKE!\n" + "What can I help you with?";
        return output;
    }

    /**
     * Creates a Scanner object to read user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the next command from user input.
     *
     * @return the next command from user input.
     */
    public String getInput() {
        return (scanner.nextLine());
    }

    /**
     * Returns the goodbye message and closes scanner.
     *
     * @return goodbye message.
     */
    public String showGoodbye() {
        String output = "Bye. Hope to see you again soon!";
        scanner.close();
        return output;
    }

    /**
     * Returns message when a task is added to the list.
     *
     * @param task the task to be stored in list.
     * @return message when a task is added to the list.
     */
    public String showTaskAdded(Task task) {
        String output = "I've added this task to your list:\n";
        output += task;
        return output;
    }

    /**
     * Returns the list of tasks.
     *
     * @param taskList list of tasks.
     * @return list of tasks.
     */
    public String printList(TaskList taskList) {
        if (taskList.getSize() == 0) {
            return "Your list is empty!";
        } else {
            String output = "Here is your list:\n";
            for (int i = 0; i < taskList.getSize(); i++) {
                output += (i + 1) + ". " + taskList.getTask(i) + "\n";
            }
            return output;
        }
    }

    /**
     * Returns the task that has been marked as done and the "marked" message.
     *
     * @param task the task to be marked as done.
     * @return task that has been marked as done and the "marked" message.
     */
    public String printMark(Task task) {
        String output = "Nice! I've marked this task as done:\n" + task.toString();
        return output;
    }

    /**
     * Returns the task that has been marked as not done and the "unmarked" message.
     *
     * @param task task that to be marked as not done.
     * @return task that has been marked as not done and the "unmarked" message.
     */
    public String printUnmark(Task task) {
        String output = "OK, I've marked this task as not done yet:\n" + task.toString();
        return output;
    }

    /**
     * Returns the deleted task.
     *
     * @param task task to be deleted.
     * @return deleted task.
     */
    public String printDeleteTask(Task task) {
        String output = "Noted. I've removed this task:\n" + task.toString();
        return output;
    }

    /**
     * Returns the items from the list that match the given keyword.
     *
     * @param taskList list of tasks.
     * @param indexArray list of items that match the keyword.
     * @return a list of items that match the given keyword.
     */
    public String printFind(TaskList taskList, int[] indexArray) {
        String output = "Here are the matching tasks in your list:\n";
        int counter = 1;
        for (int i = 0; i < taskList.getSize(); i++) {
            if (indexArray[i] == 1) {
                output += counter + ". " + taskList.getTask(i) + "\n";
                counter++;
            }
        }
        return output;
    }

    /**
     * Returns a message when no items in the list match the given keyword.
     *
     * @return message.
     */
    public String printNotInList() {
        String output = "There are no such items in your list!";
        return output;
    }

    public String printTagTask(Task task) {
        String output = "I have tagged the following task:\n" + task;
        return output;
    }

    public void showLoadingError() {
        System.out.println( "There was an error in loading your file!");
    }

    public String showError(DukeException e) {
        return e.getMessage();
    }
}
