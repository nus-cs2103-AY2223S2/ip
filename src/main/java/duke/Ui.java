package duke;

public class Ui {
    /**
     * Handles the welcome user interface when the programs start.
     */
    public String welcome() {
        return "Hello! I'm duke.\nWhat can I do for you?";
    }

    /**
     * Handles to goodbye user interface when programs end.
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Handles the user interface if there is not previously saved tasklist.
     */
    public String showLoadingError() {
        return "There is no saved file! Start a new list :)";
    }
}
