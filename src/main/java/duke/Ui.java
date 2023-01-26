package duke;

import java.util.List;

/**
 * This class handles the formatting and outputting of text based UI to the user.
 */


public class Ui {
    /**
     * Method to format and print the desired message to stdout.
     * @param message <code>String</code> of message to be displayed. Include newline character at end of message.
     */
    void displayMessage(String message) {
        System.out.println(
                "-----------------------------------------------------------\n" +
                message +
                "-----------------------------------------------------------\n"
        );
    }

    /**
     * Method to display standard welcome message to user.
     */
    void welcomeMessage() {
        displayMessage("""
                Hello! I'm Bob
                What can I do for you?
                """);
    }

    /**
     * Method to format and display all current tasks in the <code>TaskList</code> to the user.
     * @param tasks <code>TaskList</code> instance belonging to the calling instance of <code>Duke</code>.
     */
    void displayItemList(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb
                    .append(i+1)
                    .append(".")
                    .append(tasks.get(i).toString())
                    .append("\n");
        }
        displayMessage(sb.toString());
    }

    /**
     * Method to display the <code>Task</code> items at the indices specified.
     * @param tasks Instance of <code>TaskList</code> belonging to the calling instance of <code>Duke</code>.
     * @param indices <code>List<Integer></code> containing desired indices to be displayed
     */
    void displayItemsAtIndices(TaskList tasks, List<Integer> indices) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the wanted tasks in your list:\n");
        for (int i : indices) {
            sb
                    .append(i+1)
                    .append(".")
                    .append(tasks.get(i).toString())
                    .append("\n");
        }
        displayMessage(sb.toString());
    }
}
