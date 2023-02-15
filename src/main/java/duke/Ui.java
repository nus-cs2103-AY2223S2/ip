package duke;

public class Ui {

    public Ui() {
    }

    public String greetings() {
        String logo = "DUKE\n";
        return "Hello from\n" + logo + "What can I do for you today?";
    }

    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    public String showLoadingError() {
        return "Error occurred in loading file.";
    }

    public String unknownCommand() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }




}
