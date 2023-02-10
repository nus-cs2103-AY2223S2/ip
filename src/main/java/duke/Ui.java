package duke;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Returns the bye message in String format.
     */
    public String bye() {
        return "bye! see u soon! :-)";
    }

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        System.out.println("---\nhi i'm Duke! what's up?\n---");
    }

    /**
     * Prints the divider line ("---").
     */
    public void showLine() {
        System.out.println("---"); // show the divider line ("---")
    }

    /**
     * Prints the bye message.
     */
    public void showBye() {
        System.out.println("---\nbye! see u soon! :-)\n---");
    }

    /**
     * Prints a DukeException message.
     */
    public void showException(DukeException ex) {
        System.out.println(ex);
    }
}
