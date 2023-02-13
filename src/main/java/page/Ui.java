package page;

import java.util.ArrayList;
import java.util.Scanner;

import page.quest.Quest;

/**
 * Represents the UI that handles interactions with the user.
 */
public class Ui {
    /** Scanner that reads user input */
    private Scanner scanner;

    /**
     * Constructs a new Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns a greeting message upon start-up.
     *
     * @return Preset greeting message.
     */
    public String showGreeting() {
        String welcome = "Greetings! 'Tis I, Page, thy medieval assistant.\n"
                + "Type 'help' for the list of available commands.";
        return welcome;
    }

    /**
     * Returns the next line of user input.
     *
     * @return Next line of user input.
     */
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Returns the current Quest Log.
     *
     * @param questLog Quest Log to be printed.
     * @return The current Quest Log.
     */
    public String showQuestLog(QuestLog questLog) {
        return "Quest Log:\n" + questLog.toString();
    }

    /**
     * Returns the error message of the given PageException.
     *
     * @param e The given PageException.
     * @return The error message of the given PageException.
     */
    public String showErrorMessage(PageException e) {
        return e.getMessage();
    }

    /**
     * Returns an acknowledgement that the given quest has been added to the Quest Log.
     *
     * @param q The added quest.
     * @return The acknowledgement message.
     */
    public String showQuestAdded(Quest q) {
        return "Added to Quest Log:\n" + q.toString();
    }

    /**
     * Returns an acknowledgement that the given quest has been completed.
     *
     * @param q The given quest.
     * @return The acknowledgement message.
     */
    public String showQuestCompleted(Quest q) {
        return "Quest Complete! The bards shall sing of your victory!\n" + q.toString();
    }

    /**
     * Returns an acknowledgement that the given quest has been marked incomplete.
     *
     * @param q The given quest.
     * @return The acknowledgement message.
     */
    public String showQuestIncompleted(Quest q) {
        return "Quest Incomplete?? Oh no!\n" + q.toString();
    }

    /**
     * Returns an acknowledgement that the given quest has been deleted from the Quest Log.
     *
     * @param q The quest to be deleted.
     * @return The acknowledgement message.
     */
    public String showQuestDeleted(Quest q) {
        return "Quest Deleted... lost to the ages...\n" + q.toString();
    }

    /**
     * Returns the list of quests in the Quest Log matching a keyword.
     *
     * @param arr The list of quests.
     * @return The list of quests matching the keyword.
     */
    public String showFilteredQuestLog(ArrayList<Quest> arr) {
        StringBuilder output = new StringBuilder("Greetings sire, here are the matching quests in the Quest Log:");
        if (arr.isEmpty()) {
            output.append("No matching quests found!");
        } else {
            for (Quest q : arr) {
                output.append("\n").append(q.toString());
            }
        }
        return output.toString();
    }

    /**
     * Returns a warning to the user that their input is invalid.
     *
     * @return Invalid Input warning.
     */
    public String showInvalidInputWarning() {
        String text = "My apologies, I cannot decipher your arcane incantations. "
                + "Type 'help' for the list of commands I can understand.";
        return text;
    }

    /**
     * Returns a message listing all the commands available to Page.
     *
     * @return List of commands.
     */
    public String showHelpMessage() {
        String helpText = "type 'help' to show this help text!\n"
                + "type 'todo someTask' to add the task to the Quest Log.\n"
                + "type 'deadline someDeadline /by someTime' to add a task with deadline someTime.\n"
                + "type 'event someEvent /from startTime /to endTime' "
                + "to add an event lasting from startTime to endTime.\n"
                + "type 'log' to show the current Quest Log.\n"
                + "type 'complete 1' to mark the 1st quest as complete.\n"
                + "type 'incomplete 2' to mark the 2nd quest as incomplete.\n"
                + "type 'delete 3' to delete the 3rd quest.\n"
                + "type 'bye' to exit.";
        return helpText;
    }

    /**
     * Returns an exit message.
     *
     * @return Exit message.
     */
    public String showByeMessage() {
        return "Farewell, my liege.";
    }
}
