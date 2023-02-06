package duke.ui;

import java.util.Scanner;

/**
 * Represents a user interface that is responsible for getting input and displaying output to the user.
 */
public class Ui {
    /** Logo for the name of the chatbot. **/
    public static final String LOGO = " |            ______    ______\n"
                                     + " | ____    |         |  |         |\n"
                                     + " |        |  |         |  |         |\n"
                                     + " | ____ |  |______|  |______|\n";

    /** Straight line that separates commands. **/
    public static final String STRAIGHT_LINE =
            "_______________________________________________________________________________________________";


    /** Commands that supported by the chatbot. */
    public static final String COMMAND_LIST =
            "1.  list -> Provides a list of existing tasks.\n"
                    + "2.  mark X -> Marks task number X as done.\n"
                    + "3.  unmark X -> Marks task number X as undone.\n"
                    + "4.  todo taskName -> Creates a todo task with name taskName.\n"
                    + "5.  deadline taskName /by date -> Creates a deadline task with name taskName and deadline"
                            + " date.\n"
                    + "6.  event taskName /from startDate /to endDate -> Creates an event task with name taskName,\n"
                    + "     start date startDate, and end date endDate.\n"
                    + "7.  delete X -> Deletes task number X from the list.\n"
                    + "8.  on givenDate -> Displays all the tasks that occur on givenDate.\n"
                    + "9.  find keyPhrase -> Displays all the tasks whose names contain any words from keyPhrase.\n"
                    + "10. help -> Prints the list of commands supported by this bot.\n"
                    + "11. bye -> Exits the bot.\n\n"
                    + "Please enter dates in the format of either yyyy-MM-dd hh:mm or yyyy-MM-dd.";

    /** Introductory message excluding logo and command list. */
    public static final String INTRODUCTORY_BODY = "Boo! Nice to meet you.\n"
            + "I am here to scare all your problems away by keeping track of your tasks.\n"
                    + "What can I help you with today?\n"
                            + "\nSupported Commands:";

    /** Exit message. */
    private static final String EXIT_MESSAGE = "Goodbye. Hope that I have managed to scare all your problems away."
            + "\nHave a great day! :)";


    /** {@code Scanner} to take in user input. */
    private Scanner sc;

    /**
     * Constructs an {@code Ui} instance to read in user input and display output.
     */
    public Ui() {
        printIntroductoryMessage();
        sc = new Scanner(System.in);
    }

    /**
     * Prints out the straight line that separates commands.
     */
    public static void printStraightLine() {
        System.out.println(STRAIGHT_LINE);
    }

    /**
     * Prints the introductory message.
     */
    public void printIntroductoryMessage() {
        System.out.println(LOGO);
        printStraightLine();
        System.out.println(INTRODUCTORY_BODY);
        System.out.println(COMMAND_LIST);
        printStraightLine();
    }

    /**
     * Prints the exit message, used when bot exits normally.
     */
    public void printExitMessage() {
        printStraightLine();
        System.out.println(EXIT_MESSAGE);
        printStraightLine();
    }

    /**
     * Prints abnormal shutdown message, used when bot faces an exception.
     */
    public void printShutDownMessage() {
        printStraightLine();
        System.out.println("Bot shutting down...");
        printStraightLine();
    }

    /**
     * Prints the list of supported commands, used when user calls help.
     */
    public void printCommands() {
        printStraightLine();
        System.out.println("Supported Commands:");
        System.out.println(COMMAND_LIST);
        printStraightLine();
    }

    /**
     * Prints the given string.
     *
     * @param statement The string to be printed.
     */
    public void printStatement(String statement) {
        System.out.println(statement);
    }

    /**
     * Gets user input for processing.
     *
     * @return the raw command given by the user.
     */
    public String getUserCommand() {
        return sc.nextLine();
    }

    /**
     * Cleans up UI resources after bot ends.
     */
    public void cleanUpUi() {
        sc.close();
    }

}
