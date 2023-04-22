package duke.ui;


import java.util.Scanner;

public class Ui {
    public static final String HORIZONTAL_LINE = "    ____________________________________________________________";

    /**
     * Show the horizontal divider line.
     */
    public static void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Show the loading error of the file that stores the tasks, which may not exist.
     */
    public void showLoadingError() {
        showMessage("     Load failed! Please put the formatted file \"tasks.txt\" in the path");
    }


    /**
     * Show welcome message to the user.
     */
    public static void showWelcome() {
        showMessage("     Hello! I'm Duke\n" +
                "     What can I do for you?");
    }

    /**
     * Formatting the output message, adding divider line above and below.
     *
     * @param message the message that needs to be formatted and shown.
     */
    public static void showMessage(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    public static void showError(String message) {
        if (message == null) {
            System.out.println("     T_T OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else {
            System.out.println(message);
        }
    }

    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
