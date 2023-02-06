package duke;

import java.util.Scanner;

/**
 * UI class that handles any interaction with the user such as display
 * or reading input.
 */
public class Ui {

    private Scanner scanner;
    private String horizontalLine = "************************";
    private TaskList taskList;

    public Ui(TaskList taskList) {
        scanner = new Scanner(System.in);
        this.taskList = taskList;
    }

    /**
     * Displays the start screen text of Duke
     */
    public void showIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(horizontalLine);
        System.out.println("HELLO! I'M DUKE!");
        System.out.println("HOW CAN I HELP?");
        System.out.println("NOTE! USE THIS FORMAT FOR DATES: dd/MM/yyyy HH:mm");
        System.out.println(horizontalLine);
    }

    /**
     * Reads any commands the user types in
     *
     * @return The String that the user typed in
     */
    public String readInput() {
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Displays the acknowledgement of adding a task
     * and also shows the number of tasks created
     */
    public void showAddTask(Task task) {
        System.out.println(horizontalLine);
        System.out.println("OK. I'VE ADDED THIS TASK:");
        System.out.println("[" + task.getIcon() + "][ ] " + task);
        showCount();
        System.out.println(horizontalLine);
    }

    /**
     * Displays the acknowledgement of deleting a task
     */
    public void showDeleteTask(int index) {
        System.out.println(horizontalLine);
        System.out.println("OK! I'VE DELETED THIS TASK: ");
        System.out.println("" + (index + 1) + ". " +
                "[" + taskList.getTask(index).getIcon() + "]" +
                "[" + taskList.getTask(index).getStatusIcon() + "] " +
                taskList.getTask(index));
        System.out.println(horizontalLine);
    }

    /**
     * Displays the acknowledgement of marking a task as done
     */
    public void showMark(int index) {
        System.out.println(horizontalLine);
        System.out.println("I'VE MARKED THIS TASK AS DONE: ");
        System.out.println("[X] " + taskList.getTask(index));
        System.out.println(horizontalLine);
    }

    /**
     * Displays the acknowledgement of marking a task as not done
     */
    public void showUnmark(int index) {
        System.out.println(horizontalLine);
        System.out.println("I'VE MARKED THIS TASK AS UNDONE: ");
        System.out.println("[] " + taskList.getTask(index));
        System.out.println(horizontalLine);
    }

    /**
     * Displays the list of tasks stored in Duke
     */
    public void showList(TaskList taskList) {
        System.out.println(horizontalLine);
        System.out.println("HERE ARE YOUR TASKS!");
        for (int i = 0; i < taskList.getCount(); i++) {
            System.out.println("" + (i+1) + ". " +
                    "[" + taskList.getTask(i).getIcon() + "]" +
                    "[" + taskList.getTask(i).getStatusIcon() + "] " +
                    taskList.getTask(i));
        }
        System.out.println(horizontalLine);
    }

    /**
     * Displays the total number of tasks
     */
    public void showCount() {
        String plural = "";
        int n = taskList.getCount();
        if (n > 1) {
            plural = "S";
        }
        System.out.println("NOW YOU HAVE " + n + " TASK" + plural + " IN THE LIST!");
    }

    /**
     * Displays any error the user may trigger while using Duke
     *
     * @param errorMsg The error message to show the user
     */
    public void showUnknown(String errorMsg) {
        System.out.println(horizontalLine);
        System.out.println(":( SORRY! " + errorMsg);
        System.out.println(horizontalLine);
    }

    /**
     * Displays the farewell text when the user requests to
     * close the program
     */
    public void showBye() {
        System.out.println(horizontalLine);
        System.out.println("BYE!");
        System.out.println(horizontalLine);
    }

}
