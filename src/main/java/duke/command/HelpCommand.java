package duke.command;

/**
 * Represents a "help" command that is entered by the user.
 */
public class HelpCommand extends Command {
    /** Commands that supported by the chatbot. */
    private static final String COMMAND_LIST =
            "1.  list -> Provides a list of existing tasks.\n"
                    + "2.  mark X -> Marks task number X as done.\n"
                    + "3.  unmark X -> Marks task number X as undone.\n"
                    + "4.  todo taskName -> Creates a todo task with name taskName.\n"
                    + "5.  deadline taskName /by date -> Creates a deadline task with name taskName and deadline"
                    + "date.\n"
                    + "6.  event taskName /from startDate /to endDate -> Creates an event task with name taskName,\n"
                    + "     start date startDate, and end date endDate.\n"
                    + "7.  delete X -> Deletes task number X from the list.\n"
                    + "8.  on givenDate -> Displays all the tasks that occur on givenDate.\n"
                    + "9.  find keyPhrase -> Displays all the tasks whose names contain any words from keyPhrase.\n"
                    + "10. help -> Prints the list of commands supported by this bot.\n"
                    + "11. bye -> Exits the bot.\n\n"
                    + "Please enter dates in the format of either yyyy-MM-dd hh:mm or yyyy-MM-dd.";

    /**
     * Constructs a {@code HelpCommand}.
     */
    public HelpCommand() {
        super();
    }

    /**
     * Causes the bot to print out the list of supported commands.
     *
     * @return the list of supported commands
     */
    @Override
    public String runCommand() {
        String output = "";
        output += "Supported Commands:\n";
        output += COMMAND_LIST;
        return output;
    }
}
