package alfred.ui;

import java.util.Scanner;

import alfred.exceptions.AlfredException;

/**
 * Represents the interface where it deals with the user interaction with the uer.
 */
public class Ui {

    private Scanner sc;
    /**
     * Constructs an Ui object where it defines how the system input is given.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }


    /**
     * Asks the user for the next input.
     * @return the input written by the user.
     */
    public String getCommand() {
        return sc.nextLine();
    }

    /**
     * Displays the opening introduction by the program.
     */
    public String getOpening() {
        StringBuilder opening = new StringBuilder();
        opening.append("*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*\n");
        opening.append("\"| Your favourite personal assistant:  |\"\n");
        opening.append("*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*\n");
        opening.append(getLogo());
        opening.append(getIntro());
        return opening.toString();
    }

    /**
     * Displays the command given.
     * @param command The command written by the user.
     */
    public String getCommandMessage(String command) {
        StringBuilder message = new StringBuilder();
        message.append(getLines());
        message.append(command);
        message.append(getLines());
        return message.toString();
    }

    /**
     * Displays the error given.
     * @param e The AlfredException that was thrown in the program.
     */
    public String getErrorMessage(AlfredException e) {
        return getCommandMessage(e.getMessage());
    }

    /**
     * Displays the goodbye message by the program.
     */
    public String getByeMessage() {
        String command = "Bye. Hope to see you again soon!";
        return getCommandMessage(command);
    }

    /**
     * Displays the logo of the program.
     */
    public String getLogo() {
        StringBuilder logo = new StringBuilder();
        logo.append(" _____ __     ______ _____ ____ ___ \n");
        logo.append("|  -  |  |   |  ____|  _  |  __| _ \\     \n");
        logo.append("| | | |  |   | |___ | |_|_| |__|| | |  \n");
        logo.append("|  -  |  |___|  ___||  _ \\  |__||_| |\n");
        logo.append("|_| |_| ____ |__|   |_| \\_|____|__ /   \n");
        return logo.toString();
    }

    /**
     * Displays the opening message from the program.
     * @return returns the opening message from the program.
     */
    public String getIntro() {
        StringBuilder intro = new StringBuilder();
        intro.append(getLines());
        intro.append("Hello! I'm Alfred :>\n");
        intro.append("How can I help you today?\n");
        intro.append("Type \"help\" to get all the commands available in the program!\n");
        intro.append(getLines());
        return intro.toString();
    }

    /**
     * Displays an empty line that separates commands.
     * @return returns all the lines.
     */
    public String getLines() {
        String lines = "-".repeat(70);
        return lines + "\n";
    }

    /**
     * Creates indentation for sentences.
     * @return returns the indentation for sentences.
     */
    public String getIndent() {
        return "    ";
    }
}
