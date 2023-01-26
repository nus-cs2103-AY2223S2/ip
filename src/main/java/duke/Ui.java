package duke;

import java.util.List;

public class Ui {
    void displayMessage(String message) {
        System.out.println(
                "-----------------------------------------------------------\n" +
                message +
                "-----------------------------------------------------------\n"
        );
    }

    void welcomeMessage() {
        displayMessage("""
                Hello! I'm Bob
                What can I do for you?
                """);
    }

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
