package botanic.gui;

import botanic.task.Task;
import botanic.task.TaskList;

/**
 * Encapsulates the related fields and behavior of the Gui
 * that handles interaction with user.
 */
public class Gui {
    /**
     * Gets the welcome message.
     *
     * @return A String representation of the welcome message.
     */
    public String getWelcome() {
        return "Aloe there! I am BOTanic! How may I help you today?\n";
    }

    /**
     * Gets the goodbye message.
     *
     * @return A string representation of the goodbye message.
     */
    public String getBye() {
        return "Bye, hApple a-maize-zing day!\n" + "See you again soon!";
    }

    /**
     * Gets botanic message response to successful add command.
     *
     * @param task The task added.
     * @param size The size of task list after new task added.
     * @return Add success message.
     */
    public String getAddSuccessMsg(Task task, int size) {
        assert task != null : "Task to be printed cannot be null";
        return "Pear-fect! I've added this task:\n " + task + "\nNow you have "
                + size + " task(s) in your basket.\n";
    }

    /**
     * Gets botanic message response to successful delete command.
     *
     * @param task The task deleted.
     * @param size The size of task list after task deleted.
     * @return Delete success message.
     */
    public String getDeleteSuccessMsg(Task task, int size) {
        return "Gourd it! I've removed this task:\n " + " "
                + task + "\nNow you have " + size
                + " task(s) in your basket.\n";
    }

    /**
     * Gets botanic message response to successful mark command.
     *
     * @param task The task marked.
     * @return Mark success message.
     */
    public String getMarkSuccessMsg(Task task) {
        return "Sweet! I've marked this task as done:\n " + task + "\n";
    }

    /**
     * Gets botanic message response to successful unmark command.
     *
     * @param task The task unmarked.
     * @return Unmark success message.
     */
    public String getUnmarkSuccessMsg(Task task) {
        return "Grape! I've marked this task as not done:\n " + task + "\n";
    }

    /**
     * Gets a string representation of the tasks in the list in response
     * to the list command.
     *
     * @param tasks The task list to be printed.
     * @return Response to a successful list command.
     */
    public String printTasks(TaskList tasks) {
        assert tasks != null : "Task list given cannot be null";
        return "Here are the tasks in your basket:\n" + tasks;
    }

    /**
     * Gets results found from searching for a keyword in the list.
     *
     * @param tasks The tasks that matches with given keyword.
     * @return Search results.
     */
    public String getFindResult(TaskList tasks) {
        String output = "Here are the matching tasks in your basket:\n";
        return output + tasks;
    }

    /**
     * Gets botanic response to an out-of-bounds exception.
     *
     * @return Out-of-bounds exception error message.
     */
    public String getOutOfBoundsErrorMsg() {
        return "Item at given index does not exist! Please enter a valid index.";
    }

    /**
     * Gets botanic response to when there is no item to list.
     *
     * @return No-item-to-list error message.
     */
    public String getNoItemErrorMsg() {
        return "There are no items in your basket.\n"
                + "Start adding some!\n";
    }

    /**
     * Gets botanic response to when there is no item found using findAllMatch().
     *
     * @param keyword The keyword searched for.
     * @return No-completely-matching-item error message.
     */
    public String getNoCompleteMatchErrorMsg(String keyword) {
        return "None of the items in your basket completely matches with \"" + keyword + "\"";
    }

    /**
     * Gets botanic response to when there is no item found using findflex().
     *
     * @param keyword The keyword searched for.
     * @return No-partially-matching-item error message.
     */
    public String getNoPartialMatchErrorMsg(String keyword) {
        return "None of the items in your basket partially "
                + "or completely matches with \"" + keyword + "\"";
    }

    /**
     * Gets botanic response to when there is no item found using findDate().
     *
     * @param date The date searched for.
     * @return No-date-match-item error message.
     */
    public String getNoDateMatchErrorMsg(String date) {
        return "None of the items in your basket have the date \"" + date + "\"";
    }

    /**
     * Gets botanic response to a command with incomplete description (no name).
     *
     * @param cmdType The type of command which was incomplete.
     * @return Incomplete Description exception error message.
     */
    public String getNoDescErrorMsg(String cmdType) {
        return "The description of a " + cmdType + " cannot be empty!";
    }

    /**
     * Gets botanic response to a command with incomplete description
     * (missing fields such as name, index, dates).
     *
     * @param fieldType The field that was missing.
     * @return Incomplete Description exception error message.
     */
    public String getMissingFieldErrorMsg(String fieldType) {
        return "Please add the " + fieldType;
    }

    /**
     * Gets botanic response to a command with an invalid date input given in
     * the wrong date format.
     *
     * @return Invalid Date error message.
     */
    public String getInvalidDateErrorMsg() {
        return "Please enter a valid date/time in \"yyyy/mm/dd\" format.";
    }

    /**
     * Gets botanic response to an attempt to add
     * a deadline or event task that has passed.
     *
     * @param dateType The type of the date (start date, end date or due date).
     * @param dateFormatted The date input formatted into DD MONTH YYYY format.
     * @return Date Passed error message.
     */
    public String getDatePassedErrorMsg(String dateType, String dateFormatted) {
        return "The given " + dateType + " (yyyy/mm/dd) " + dateFormatted + " has passed.";
    }

    /**
     * Gets botanic response to an attempt to add
     * an event with a start date that is later than the end date.
     *
     * @param startDateFormatted The start date input formatted into DD MONTH YYYY format.
     * @return Start date after end date error message.
     */
    public String getStartAfterEndErrorMsg(String startDateFormatted) {
        return "The given start date " + startDateFormatted
                + " (yyyy/mm/dd) should be before the end date (yyyy/mm/dd).";
    }

    /**
     * Prints out the given string to screen.
     */
    public void printMsg(String errMessage) {
        System.out.println(errMessage + "\n");
    }

}
