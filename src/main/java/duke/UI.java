package duke;

/**
 * UI represents the methods that print messages that facilitate interaction with the user
 */
public class UI {
    private static final String UI_FIRST_COMMAND_MESSAGE = "You can let me know by typing it below!";
    private static final String UI_NEXT_COMMAND_MESSAGE = "What can I do for you next?";
    private static final String UI_INVALID_DATE_FORMAT_MESSAGE = "Please re-enter the request with the date in the \n" +
                                                              "following format: dd-Mmm-yyyy";
    private static final String UI_EMPTY_REQUEST_MESSAGE = "Your request cannot be empty! Please re-enter your request";

    private static final String UI_REQUIREMENT_FOR_ONE_ARGUMENT_MESSAGE = "This request requires exactly" +
            " one task number as the second argument!";

    private static final String UI_REQUIREMENT_FOR_START_FINISH_DATE_TIME =  "You have to enter a start" +
            " and a finish date and time!";
    private static final String UI_MISSING_DEADLINE_MESSAGE = "You have to enter a deadline!";

    private static final String UI_MISSING_TASK_NUMBER_MESSAGE = "You have to specify a task number to be deleted!";

    private static final String UI_ONLY_ONE_KEYWORD_FOR_FIND_MESSAGE = "You have to enter exactly one keyword " +
            "to find a task with a match!";
    private static final String UI_BYE_MESSAGE = "Thank You and have a great day ahead!";

    private static final String UI_INVALID_COMMAND_MESSAGE = "You may have accidentally entered in " +
            "an invalid command. Please re-enter!";

    private static final String UI_INVALID_DUKE_REQUEST_MESSAGE = "Invalid Duke Request; please re-enter your request!";
    public void printNextCommandMessage() {
        System.out.println(UI_NEXT_COMMAND_MESSAGE);
    }


    /**
     * Prints a message asking the user to re-enter a date in the correct format
     */
    public String printInvalidDateFormatMessage() {
        return UI_INVALID_DATE_FORMAT_MESSAGE;
    }

    /**
     * Prints list of available commands so that the user could refer to it
     * @return A String that is the list of the commands
     */
    public static String printAvailableCommands() {
        String output = "";
        output += "Here is the list of commands and their required details!\n";
        output += "Ensure that you leave spaces between the different fields!\n";
        output += "1. list - view a list of all your tasks and their completion status!\n";
        output += "2. mark {insert task number in your list} - mark the corresponding task as completed!\n";
        output += "3. unmark {insert task number in your list} - mark the corresponding task as incomplete!\n";
        output += "4. todo {insert task description} - add a task with only a description to your list!";
        output += "5. deadline {insert description} / {insert start date} / {insert end date} - add a deadline\n" +
                      " to your list!\n";
        output += "6. event {insert description} / {insert start date and time} / {insert end date and time}\n" +
                      " add an event to your list\n";
        output += "7. delete {insert task number in your list} - delete the corresponding task!\n";
        output += "8. find {insert keyword} - find the task with a matching keyword\n";
        return output;
    }

    public String printEmptyRequestMessage() {
        return UI_INVALID_DATE_FORMAT_MESSAGE;
    }

    public String printRequireExactlyOneArgumentMessage() {
        return UI_REQUIREMENT_FOR_ONE_ARGUMENT_MESSAGE;
    }

    public String printMissingDeadlineMessage() {
        return UI_MISSING_DEADLINE_MESSAGE;
    }

    public String printRequirementForStartFinishDateTimeMessage() {
        return UI_REQUIREMENT_FOR_START_FINISH_DATE_TIME;
    }

    public String printMissingTaskNumberMessage() {
        return UI_MISSING_TASK_NUMBER_MESSAGE;
    }

    public String printOneKeywordMessage() {
        return UI_ONLY_ONE_KEYWORD_FOR_FIND_MESSAGE;
    }

    public String printByeMessage() {
        return UI_BYE_MESSAGE;
    }

    public String printInvalidCommandMessage() {
        return UI_INVALID_COMMAND_MESSAGE;
    }

    public String printInvalidDukeRequestMessage()  {
        return UI_INVALID_DUKE_REQUEST_MESSAGE;
    }
}