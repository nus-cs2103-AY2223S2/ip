package duke;

public class Ui {

    public Ui() {
    }

    /**
     * The method greets the user by returning a string.
     *
     * @return returns a string that greets the user.
     */
    public String greetings() {
        String logo = "DUKE\n";
        return "Hello from\n" + logo + "What can I do for you today?";
    }

    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * The method is called when there is an error loading the file.
     *
     * @return returns a String message.
     */
    public String showLoadingError() {
        return "Error occurred in loading file.";
    }

    public String unknownCommand() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }




}
