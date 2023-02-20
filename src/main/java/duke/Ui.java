package duke;

/**
 * Ui class
 */
public class Ui {

    /**
     * Displays a greeting to the user when he first opens the application
     */
    public String greet() {
        return "Hello! I'm Duke" + "\n" + "What can I do for you?";
    }

    /**
     * Displays a goodbye message to the user when he closes the application
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays an error in loading the file
     */
    public void showLoadingError() {
        System.out.println("There was an error in loading your file");
    }

    public String listCommandMessage() {
        return "Here are the tasks in your list:\n";
    }
}
