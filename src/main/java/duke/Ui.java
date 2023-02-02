package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Represents the user interface (UI) that deals with the user inputs and outputs the corresponding texts and messages
 * generated by Duke system.
 *
 * @author MrTwit99
 * @since 2023-02-01
 */
public class Ui {

    private PrintWriter pw;
    private StringBuilder sb;
    private BufferedReader br;

    /**
     * Returns a UI object that reads inputs from user using BufferedReader and outputs messages from the system to
     * the user with the means of StringBuilder and PrintWriter.
     */
    public Ui() {
        this.pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        this.sb = new StringBuilder();
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Displays the welcome message generated by Duke system upon boot up.
     */
    public void showWelcome() {
        sb.append("Hello from\n")
                .append(" ____        _        \n")
                .append("|  _ \\ _   _| | _____ \n")
                .append("| | | | | | | |/ / _ \\\n")
                .append("| |_| | |_| |   <  __/\n")
                .append("|____/ \\__,_|_|\\_\\___|\n")
                .append("    ____________________________________________________________\n")
                .append("    Hello! I'm Duke.\n")
                .append("    What can I do for you?\n")
                .append("    ____________________________________________________________\n");
        pw.println(sb.toString());
        pw.flush(); // Flush the message out and print to user
        sb.setLength(0); // Clear string stored in StringBuilder
    }

    /**
     * Displays the farewell message generated by Duke system during termination.
     *
     * @throws IOException On input error.
     * @see IOException
     */
    public void showFarewellMessage() throws IOException {
        sb.append("    ____________________________________________________________\n")
                .append("    Bye. Hope to see you again soon!\n")
                .append("    ____________________________________________________________\n");
        pw.println(sb.toString());
        pw.flush();
        sb.setLength(0);
        pw.close();
        br.close();
    }

    /**
     * Displays the attached corresponding message attached as this method's argument to user.
     *
     * @param message Message generated by Duke system that needs to be printed out to the user.
     */
    public void printCommand(String message) {
        pw.println(message);
        pw.flush();
        sb.setLength(0);
    }

    /**
     * Displays an error message to user, informing him/her that the date/time given were of the wrong format and
     * informs them of the correct format.
     */
    public void printInvalidDateError() {
        sb.append("Invalid inputs!\n");
        sb.append("Please enter your date & time in the format: YYYY-MM-DD HH:MM \n");
        sb.append("Please also ensure they are valid values!\n");
        pw.println(sb);
        pw.flush();
        sb.setLength(0);
    }

    /**
     * Displays the error message generated by the input Exception.
     *
     * @param e Exception that was generated by Duke system.
     */
    public void showLoadingError(Exception e) {
        pw.println(e);
    }

    /**
     * Returns a string that corresponds to the user input on the CLI.
     *
     * @return A string the corresponds to the user input on the CLI.
     * @throws IOException On input error.
     * @see IOException
     */
    public String readCommand() throws IOException {
        return br.readLine();
    }
}
