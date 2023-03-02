package duke;

/**
 * UI represents the methods that print messages that facilitate interaction with the user
 */
public class UI {
    private static final String UI_FIRST_COMMAND_MESSAGE = "You can let me know by typing it below!";
    private static final String UI_NEXT_COMMAND_MESSAGE = "What can I do for you next?";
    private static final String UI_INVALID_DATE_FORMAT_MESSAGE = "Please re-enter the request with the date in the \n" +
                                                              "following format: dd-Mmm-yyyy";

    
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
     * Prints a horizontal line to the screen
     */

    public static void printHorizontalLine() {
        for (int i = 0; i < 50; i++) {
            char horizontalBar = '\u2015';
            System.out.print(horizontalBar);

        }

        System.out.print("\n");
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
}