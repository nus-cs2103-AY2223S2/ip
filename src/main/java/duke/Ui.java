package duke;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String output = "Hello from\n" + LOGO;
        output += "What can I help you with?";
        System.out.println(output);
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
     * @return the next command from user input
     */
    public String getInput() {
        return (scanner.nextLine());
    }

    /**
     * Prints goodbye message and closes scanner.
     */
    public void showGoodbye() {
        String output = "Bye. Hope to see you again soon!";
        scanner.close();
        System.out.println(output);
    }

    /**
     * Prints message when a task is added to the list.
     *
     * @param task the task to be stored in list
     */
    public void showTaskAdded(Task task) {
        String output = "I've added this task to your list:\n";
        output += task;
        System.out.println(output);
    }

    public void printList(TaskList taskList) {
        if (taskList.getSize() == 0) {
            System.out.println("Your list is empty!");
        } else {
            String output = "Here is your list:\n";
            for (int i = 0; i < taskList.getSize(); i++) {
                output += (i + 1) + ". " + taskList.getTask(i) + "\n";
            }
            System.out.println(output);
        }
    }

    public void printMark(Task task) {
        String output = "Nice! I've marked this task as done:\n" + task.toString();
        System.out.println(output);
    }

    public void printUnmark(Task task) {
        String output = "OK, I've marked this task as not done yet:\n" + task.toString();
        System.out.println(output);
    }

    public void printDeleteTask(Task task) {
        String output = "Noted. I've removed this task:\n" + task.toString();
        System.out.println(output);
    }

    public void printFind(TaskList taskList, int[] indexArray) {
        String output = "Here are the matching tasks in your list:\n";
        int counter = 1;
        for (int i = 0; i < taskList.getSize(); i++) {
            if (indexArray[i] == 1) {
                output += counter + ". " + taskList.getTask(i) + "\n";
                counter++;
            }
        }
        System.out.println(output);
    }

    public void showLoadingError() {
        System.out.println( "There was an error in loading your file!");
    }

    public void showError(DukeException e) {
        System.out.println(e.getMessage());
    }
}
