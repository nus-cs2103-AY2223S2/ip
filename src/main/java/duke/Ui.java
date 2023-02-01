package duke;

public class Ui {
    /**
     * Handles the welcome user interface when the programs start.
     */
    public void welcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Handles to goodbye user interface when programs end.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Handles the user interface if there is not previously saved tasklist.
     */
    public void showLoadingError() {
        System.out.println("There is no saved file! Start a new list :)");
    }
}
