package duke;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    /**
     * Returns the welcome message in String format.
     */
    public String welcome() {
        return "---\nhi i'm Duke! what's up?\n---";
    }

    /**
     * Returns the top divider line ("---") in String format.
     */
    public String lineTop() {
        return "---\n"; // show the divider line ("---")
    }

    /**
     * Returns the bottom divider line ("---") in String format.
     */
    public String lineBottom() {
        return "\n---"; // show the divider line ("---")
    }

    /**
     * Returns the bye message in String format.
     */
    public String bye() {
        return "---\nbye! see u soon! :-)\n---";
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
}
