package duke;

/**
 * deals with user interactions ie user input and output
 */
public class Ui {

    /**
     * greets the user when Duke starts up
     */
    public void greet() {
        System.out.println("Hello! I'm Somebody\n" + "What can I do for you?");
    }

    /**
     * bids the user goodbye when shutting down Duke
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * prints an error when there is an issue in loading text file
     */
    public void showLoadingError() {
        System.out.println("Error occured in loading file.");
    }

    /**
     * prints the message as system output
     * 
     * @param msg message to be printed
     */
    public void printMessage(String msg) {
        System.out.println(msg);
    }

}
