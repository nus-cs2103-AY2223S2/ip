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

    public String help() {
        return "Here are the commands to use:\n"
                + "todo <description>: adds a todo task\n"
                + "deadline <description> /by <date>: adds a deadline task\n"
                + "event <description> /at <date>: adds an event task\n"
                + "mark <task number>: marks a task as done\n"
                + "unmark <task number>: marks a task as not done\n"
                + "delete <task number>: deletes a task\n"
                + "list: lists all tasks\n"
                + "find <keyword>: finds tasks with the keyword\n"
                + "bye: exits the program\n";

    }




}
