package duke;

/**
 * deals with user interactions ie user input and output
 */
public class Ui {

    /**
     * greets the user when Duke starts up
     *
     * @return greeting message 
     */
    public String greet() {
        return "Hello! I'm Somebody\n" + "What can I do for you?";
    }

    /**
     * bids the user goodbye when shutting down Duke
     *
     * @return exit message
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * prints an error when there is an issue in loading text file
     *
     * @return error message
     */
    public String showLoadingError() {
        return "Error occurred in loading file.";
    }

    /**
     * prints the message to be displayed on the UI for a list command
     *
     * @return message to be displayed for a list commmand by user
     */
    public String listCommandMessage() {
        return "Here are the tasks in your list:\n";
    }

}
