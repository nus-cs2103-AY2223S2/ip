package duke;

import java.util.Scanner;

/**
 * Class that contains user interaction functionality. User interaction is done via the command line.
 */
public class Ui {
    private Scanner inputReader = new Scanner(System.in);
    private String wrapTop = "________________________________\n";
    private String wrapBottom = "\n________________________________";

    public String readInput() {
        return inputReader.nextLine();
    }

    /**
     * Shows the application's welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        this.showMessage("Hello! I'm Duke What can I do for you?");
    }

    /**
     * Wraps messages before displaying them to the terminal.
     *
     * @param msg the message to be displayed
     */
    public void showMessage(String msg) {

        String wrapTop = "__________________________\n";
        String wrapBottom = "\n__________________________";
        System.out.println(wrapTop + msg.toString() + wrapBottom);
    }
}
