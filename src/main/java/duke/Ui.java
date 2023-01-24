package duke;

import java.util.Scanner;

public class Ui {
    final private Scanner sc = new Scanner(System.in);

    /**
     * Reads next command
     */
    String readCommand() {
        return sc.nextLine();
    }

    /**
     * Shows welcome message
     */
    void showWelcome() {
        System.out.println("Hello, Duke here. How can I help you?");
    }

    /**
     * Displays an error
     *
     * @param err error to be displayed
     */
    void error(String err) {
        System.out.printf("Error: %s\n", err);
    }

    /**
     * Displays a message
     *
     * @param message message to be displayed
     */
    void message(String message) {
        System.out.println(message);
    }

    /**
     * Displays a message about removing an item
     *
     * @param item index of task
     */
    void removeItemMessage(int item) {
        System.out.printf("Removing item %d\n", item);
    }

    /**
     * Lists all tasks in a task list
     *
     * @param tl task list
     */
    void listAllTasks(TaskList tl) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tl.getList().size(); i++) {
            str.append(String.format("%d: %s\n", i + 1, tl.getList().get(i)));
        }
        System.out.println(str);
    }
}
