package duke;

/**
 * deals with user interactions ie user input and output
 */
public class Ui {

    /**
     * greets the user when Duke starts up
     */
    public String greet() {
        return "Hello! I'm Somebody\n" + "What can I do for you?";
    }

    /**
     * bids the user goodbye when shutting down Duke
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * prints an error when there is an issue in loading text file
     */
    public String showLoadingError() {
        return "Error occurred in loading file.";
    }

    /**
     * prints the message as system output
     * 
     * @param msg message to be printed
     */
    public String printMessage(String msg) {
        return msg;
    }

    public String listCommandMessage() {
        return "Here are the tasks in your list:\n";
    }

}
