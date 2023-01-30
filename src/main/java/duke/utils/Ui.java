package duke.utils;

import java.util.ArrayList;

import duke.taskers.Task;

/**
 * UI class the handles the user interaction.
 */
public class Ui {

    /**
     * Iterates through the arraylist and prints out the elements inside.
     */
    public void printList(ArrayList<Task> arrList) {
        String totalString = "";
        totalString += " Here are the tasks in your list:";
        for (int i = 0; i < arrList.size(); i++) {
            totalString += "\n     " + (i + 1) + "." + arrList.get(i).toString();
        }
        this.printWithLines(totalString);
    }

    /**
     * Prints out the matching tasks.
     *
     * @param arrList Array of matching tasks.
     */
    public void showMatchingTasks(ArrayList<Task> arrList) {
        String totalString = "";
        totalString += " Here are the matching tasks in your list:";
        for (int i = 0; i < arrList.size(); i++) {
            totalString += "\n     " + (i + 1) + "." + arrList.get(i).toString();
        }
        this.printWithLines(totalString);
    }

    /**
     * Prints out the text with lines on top and below.
     *
     * @param text The content that is to be printed out.
     */
    public void printWithLines(String text) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + text);
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Greets when program starts.
     */
    public void greetings() {
        this.printWithLines(" Hello! I'm Duke!\n     What can I do for you today?");

    }

    /**
     * Response when task is marked.
     *
     * @param t duke.taskers.Task to be marked.
     */
    public void markResponse(Task t) {
        String str = " Nice! I've marked this task as done!:\n" + "       " + t.toString();
        this.printWithLines(str);
    }

    /**
     * Response when task is unmarked.
     *
     * @param t duke.taskers.Task to be unmarked.
     */
    public void unmarkResponse(Task t) {
        String str = " OK, I've marked this task as not done yet:\n" + "       "
                + t.toString();
        this.printWithLines(str);
    }

    /**
     * Response when item is added.
     *
     * @param t duke.Task to be added.
     * @param arr Array for the task to be added to.
     */
    public void addItemResponse(Task t, ArrayList<Task> arr) {
        String str = "  " + t.toString();
        str = " Got it. I've added this task:\n     " + str;
        this.printWithLines(str + listUpdate(arr));

    }

    /**
     * Prints response when user exits duke.Duke.
     */
    public void sayGoodBye() {
        this.printWithLines(" Bye! Hope to see you again soon!");
    }

    /**
     * Replies when the item is deleted.
     *
     * @param t The task to be deleted.
     * @param arr The array from the task to be deleted.
     */
    public void deleteItemResponse(Task t, ArrayList<Task> arr) {
        String str = " Noted. I'm removing this task:\n       " + t.toString();
        this.printWithLines(str + listUpdate(arr));

    }

    /**
     * Updates the list.
     *
     * @param arr The array that is remaining after something is done.
     * @return The string reply.
     */
    public String listUpdate(ArrayList<Task> arr) {
        String plural = "";
        if (arr.size() > 1) {
            plural = "s";
        }
        return "\n     Now you have " + arr.size()
                + " task" + plural + " in the list.";
    }


}
