package duke.util;

import duke.taskers.Task;

import java.util.ArrayList;

public class Ui {

    /**
     * Iterates through the arraylist and prints out the elements inside.
     */
    public void printList(ArrayList<Task> arrList) {
        String totalString = "";
        totalString += " Here are the tasks in your list:";
        for (int i = 0; i < arrList.size(); i++) {
            totalString += "\n     " + (i+1) + "." + arrList.get(i).toString();
        }
        this.printWithLines(totalString);
    }

    /**
     * Prints out the text with lines on top and below.
     *
     * @param text The content that is to be printed out
     */
    public void printWithLines(String text) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + text);
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints out the greetings when Duke is started.
     */
    public void greet() {
        this.printWithLines(" Hello! I'm duke.Duke!\n     What can I do for you today?");
    }

    /**
     * Prints response when a task is marked as done.
     *
     * @param t The task to be marked done.
     */
    public void markResponse(Task t) {
        String str = " Nice! I've marked this task as done!:\n" + "       " + t.toString();
        this.printWithLines(str);
    }

    /**
     * Prints response when a task is marked as undone.
     *
     * @param t The task to be marked undone
     */
    public void unmarkResponse(Task t) {
        String str = " OK, I've marked this task as not done yet:\n" + "       " + t.toString();
        this.printWithLines(str);
    }

    /**
     * Prints response when the item is added to taskList.
     *
     * @param t The task to be added to the taskList.
     * @param arr The taskList array.
     */
    public void showAddItem(Task t, ArrayList<Task> arr) {
        String str = "  " + t.toString();
        str = " Got it. I've added this task:\n     " + str;
        this.printWithLines(str + showListUpdate(arr));

    }

    /**
     * Prints response when user exits Duke.
     */
    public void sayGoodBye() {

        this.printWithLines(" Bye! Hope to see you again soon!");
    }

    /**
     * Prints response when user deletes an item.
     *
     * @param t The task to be deleted.
     * @param arr The taskList array.
     */
    public void showDeleteItem(Task t, ArrayList<Task> arr) {
        String str = " Noted. I'm removing this task:\n       " + t.toString();
        this.printWithLines(str + showListUpdate(arr));

    }

    /**
     * Prints response when the list is updated.
     *
     * @return The string that updates the number of elements left in the string
     */
    public String showListUpdate(ArrayList<Task> arr) {
        String plural = "";
        if (arr.size() > 1) {
            plural = "s";
        }
        return "\n     Now you have " + arr.size() +
                " task" + plural + " in the list.";
    }


}
