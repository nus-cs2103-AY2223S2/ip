package boo.ui;

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
            "1.   list -> Provides a list of existing tasks.\n"
                    + "2.   todo taskName -> Creates a todo task with name taskName.\n"
                    + "3.   deadline taskName /by date -> Creates a deadline task with name taskName and deadline"
                    + " date.\n"
                    + "4.   event taskName /from startDate /to endDate -> Creates an event task with name taskName,\n"
                    + "      start date startDate, and end date endDate.\n"
                    + "5.   mark X -> Marks task number X as done.\n"
                    + "6.   unmark X -> Marks task number X as undone.\n"
                    + "7.   delete X -> Deletes task number X from the list.\n"
                    + "8.   on givenDate -> Displays all the tasks that occur on givenDate.\n"
                    + "9.   find keyPhrase -> Displays all the tasks whose names contain keyPhrase.\n"
                    + "10. reminder day/week/month -> Provides a reminder for upcoming or due tasks, either within \n"
                    + "     1 day, 1 week or 1 month from the current date and time.\n"
                    + "11. help -> Prints the list of commands supported by this bot.\n"
                    + "12. bye -> Exits the bot.\n\n"
                    + "Please enter dates in the format of either yyyy-MM-dd hh:mm or yyyy-MM-dd.";

    /** Introductory message excluding logo and command list. */
    public static final String INTRODUCTORY_BODY = "Boo! "
            + "I am here to scare all your problems away by keeping track of your tasks.\n"
                    + "What can I help you with today?\n"
                            + "\nSupported Commands:";

    /** Exit message. */
    private static final String EXIT_MESSAGE = "Goodbye. Hope that I have managed to scare all your problems away."
            + "\nHave a great day! :)";

    /**
     * Prints out the straight line that separates commands.
     */
    public static void printStraightLine() {
        System.out.println(STRAIGHT_LINE);
    }
}
