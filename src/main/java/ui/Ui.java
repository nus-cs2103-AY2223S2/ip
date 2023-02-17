package ui;

import java.util.ArrayList;

import expense.Expense;
import expenselist.ExpenseList;
import task.Task;

/**
 * Class to encapsulate most outputs to console.
 * To be modified in order to encapsulate inputes in console.
 */
public class Ui {
    private boolean isStarted;

    /**
     * Constructor for Ui Object.
     */
    public Ui() {
        isStarted = true;
    }

    /**
     * Outputs startup sequence to console.
     */
    public static String startUpSequence() {
        String logo = "Bounsweet!!\n";


        logo += "Hello! I'm Bounsweet\n";
        logo += "What can I do for you?\n";
        return logo;
    }

    /**
     * Outputs loading error to console.
     */
    public void showLoadingError() {
        System.out.println("Error loading file, proceeding to rewrite the file");
    }

    /**
     * Returns AddedMessage , given task that was added.
     *
     * @param item Task that was added.
     * @return String to be output as response.
     */
    public static String showAddedMessage(Task item) {
        String answer = "";
        answer += "    Bounsweet says:\n";
        answer += "    Added\n";
        answer += "    " + item.toString() + "\n";
        return answer;
    }

    /**
     * Returns AddedExpenseMessage, given expense that was added.
     * @param expense expense that was added.
     * @return String to be output as response.
     */
    public static String showAddedExpenseMessage(Expense expense) {
        String answer = "";
        answer += "    Bounsweet says:\n";
        answer += "    Added\n";
        answer += "    " + expense.toString() + "\n";
        return answer;
    }
    /**
     * Outputs RemovedMessage to console, given Task that was removed.
     *
     * @param item Task that was removed.
     */
    public static String showRemovedMessage(Task item) {
        String answer = "";
        answer += "    Bounsweet says:\n";
        answer += "    Noted. I have removed this task:\n";
        answer += "    " + item.toString() + "\n";
        return answer;
    }

    /**
     * Outputs RemovedMessage to console, given Expense that was removed.
     * @param item
     * @return
     */
    public static String showRemovedExpenseMessage(Expense item) {
        String answer = "";
        answer += "    Noted. I have removed this expense:\n";
        answer += "    " + item.toString() + "\n";
        return answer;
    }

    /**
     * Outputs MarkedMessage to console, given Task that was marked as done.
     *
     * @param item Task that was marked as done.
     */
    public static String showMarkedMessage(Task item) {
        String answer = "";
        answer += "    OK I've marked this task as done:\n";
        answer += "    " + item.toString() + "\n";
        return answer;
    }

    /**
     * Outputs UnmarkedMessage to console, given Task that was marked as undone.
     *
     * @param item Task that was marked as undone.
     */
    public static String showUnmarkedMessage(Task item) {
        String answer = "";
        answer += "    OK I've marked this task as not done yet:\n";
        answer += "    " + item.toString() + "\n";
        return answer;
    }

    /**
     * Outputs SavedMessage to console, demonstrating completion of save to hard drive.
     *
     * @return String for Saved Message.
     */
    public String showSavedMessage() {
        return "    Save complete\n";
    }

    /**
     * Outputs ClosingMessage to console, demonstrating exit of program.
     *
     * @return String for closing Message.
     */
    public String showClosingMessage() {
        String answer = "";
        answer += "    Bounsweet says:\n";
        answer += "    Bye. Hope you run this program again!\n";
        return answer;
    }

    /**
     * Prints the whole list of tasks given ArrayList of tasks.
     * @param list ArrayList of Tasks to be printed.
     */
    public String printListNumber(ArrayList<Task> list) {
        assert list != null;
        return "    Now you have " + list.size() + " task(s) in the list.\n";
    }

    /**
     * Outputs SavingMessage, demonstrating the commencement of saving.
     *
     * @return String for Saving Message.
     */
    public String showSavingMessage() {
        return "    Saving:\n";
    }

    /**
     * Given an ArrayList, return strings of all tasks in it.
     * @param list ArrayList of which contents to be printed.
     * @return String of all tasks sequentially.
     */
    public static String printArrayList(ArrayList<Task> list) {
        assert list != null;
        String answer = "";
        for (int i = 0; i < list.size(); i++) {
            answer += "    " + (i + 1) + ". "
                    + list.get(i).toString() + "\n";
        }
        return answer;
    }

    /**
     * Given an ArrayList, return strings of all expenses in it.
     * @param list
     * @return String of all tasks sequentially.
     */
    public static String printExpenseArrayList(ArrayList<Expense> list) {
        assert list != null;
        String answer = "";
        for (int i = 0; i < list.size(); i++) {
            answer += "    " + (i + 1) + ". "
                    + list.get(i).toString() + "\n";
        }
        return answer;
    }

    public static String printTotalSpent(ExpenseList list) {
        return "$" + list.getTotal();
    }
}
