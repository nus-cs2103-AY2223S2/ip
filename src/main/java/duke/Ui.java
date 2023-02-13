package duke;

/**
 * Represents the UI
 */
public class Ui {

    public Ui() {
    }

    /**
     * Prints Logo of Duke
     */
    public String showLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("\nHello! I'm Duke\nWhat can I do for you?\n");
        return (logo + "\nHello! I'm Duke\nWhat can I do for you?\n");
    }

    public void showLine() {
        System.out.println("-----------------------------------------");
    }

    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }
}
