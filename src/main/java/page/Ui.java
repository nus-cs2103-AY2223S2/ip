package page;

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
     * Prints out a greeting message upon start-up.
     */
    public void printGreeting() {
        String welcome = "Greetings! 'Tis I, Page, thy medieval assistant.\n"
                + "Type 'help' for the list of available commands.";
        System.out.println(welcome);
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
     * Prints the current Quest Log.
     *
     * @param questLog Quest Log to be printed.
     */
    public void printQuestLog(QuestLog questLog) {
        System.out.println("Quest Log:\n" + questLog.toString());
    }

    /**
     * Prints out the error message of the given PageException.
     *
     * @param e The given PageException.
     */
    public void printErrorMessage(PageException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints an acknowledgement that the given quest has been added to the Quest Log.
     *
     * @param q The added quest.
     */
    public void printQuestAdded(Quest q) {
        System.out.println("Added to Quest Log:\n" + q.toString());
    }

    /**
     * Prints an acknowledgement that the given quest has been completed.
     *
     * @param q The given quest.
     */
    public void printQuestCompleted(Quest q) {
        System.out.println("Quest Complete! The bards shall sing of your victory!\n" + q.toString());
    }

    /**
     * Prints an acknowledgement that the given quest has been marked incomplete.
     *
     * @param q The given quest.
     */
    public void printQuestIncompleted(Quest q) {
        System.out.println("Quest Incomplete?? Oh no!\n" + q.toString());
    }

    /**
     * Prints an acknowledgement that the given quest has been deleted from the Quest Log.
     *
     * @param q The quest to be deleted.
     */
    public void printQuestDeleted(Quest q) {
        System.out.println("Quest Deleted... lost to the ages...\n" + q.toString());
    }

    /**
     * Informs the user that their input is invalid.
     */
    public void printInvalidInput() {
        String text = "My apologies, I cannot decipher your arcane incantations. "
                + "Type 'help' for the list of commands I can understand.";
        System.out.println(text);
    }

    /**
     * Prints a message listing all the commands available to Page.
     */
    public void printHelpMessage() {
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
        System.out.println(helpText);
    }

    /**
     * Prints an exit message.
     */
    public void printBye() {
        System.out.println("Farewell, my liege.");
    }
}
