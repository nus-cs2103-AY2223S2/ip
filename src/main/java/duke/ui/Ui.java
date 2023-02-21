package duke.ui;

import java.util.Scanner;

/**
 * Represents the user interface for Duke.
 */
public class Ui {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String WELCOME_MESSAGE = "WELCOME WHAT DO YOU WANT????";
    private static final String GOODBYE_MESSAGE = "YES Thank GOD! BYEEEEE";
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user.
     */
    public String getWelcomeMessage() {
        return WELCOME_MESSAGE + "\n";
    }

    public String getGoodbyeMessage() {
        return GOODBYE_MESSAGE;
    }
}
