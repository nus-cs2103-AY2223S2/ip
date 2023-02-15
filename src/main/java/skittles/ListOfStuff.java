package skittles;
import java.util.ArrayList;
import java.util.Locale;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import skittles.Task;

public class ListOfStuff {

    private static ArrayList<Task> skittlesList;
    private static Data data;

    private static HashMap<String, Boolean> taskHashMap;


    public ListOfStuff(ArrayList<Task> skittlesList, Data data) {

        this.skittlesList = skittlesList;
        this.data = data;
        this.taskHashMap = new HashMap<>();
    }


    public static void add(Task task) {
        skittlesList.add(task);
    }

    /**
     * Shows all Tasks in the list that the user has given to Skittles to store.
     * Tasks are ordered from least recent to most recent. If no tasks have been given to Skittles,
     * the appropriate message is shown.
     */
    public static String displayLst() {

        String textToDisplayLst = "Here are the tasks in your list:";
        String textForEmptyLst = "Hey looks like your list is empty man!";
        if (skittlesList == null) {
            return textForEmptyLst;
        } else {
            for (int i = 0; i < skittlesList.size(); i++) {
                textToDisplayLst += "\n" + (i + 1) + "." + skittlesList.get(i).toString();
            }
            return textToDisplayLst;
        }
    }

    /* old displayLst() method
    //basically print out the list. must be numbered.
    public static void displayLst() {
        boolean isItMT = false;
        StringBuilder txtToDisplay = new StringBuilder("Here are the tasks in your list:");
        String nothingMessage = "Nothing in your list man!";
        for (Task thingInList : skittlesList) {
            if (thingInList != null) {
                isItMT = true;
                txtToDisplay.append("\n").append(thingInList.getRank()).append(".")
                        .append(thingInList.toString());
            }
        }
        if (!isItMT) {
            System.out.println(nothingMessage);
        } else {
            System.out.println(txtToDisplay);
        }
    }
    */

    /**
     * Updates the marking of a certain Task as 'done'.
     * @param userTyped The entire String that the user has input i.e. "mark 2".
     * @throws SkittlesException If an incorrect input is entered.
     */
    public static void completeTask(String userTyped) throws SkittlesException {

        int taskId;

        if (userTyped.split(" ", 2).length == 1) {
            throw new SkittlesException("Hey you forgot to type the number of the task you wanna mark as done!");
        }

        String idUserTyped = userTyped.split(" ", 2)[1];
        try {
            taskId = Integer.parseInt(idUserTyped);
        } catch (NumberFormatException e) {
            throw new SkittlesException("Hey we're detecting an error; do input a number behind 'mark' if you haven't!");
        }
        String text = "Hey we can't seem to find the task ID you typed!";

        if (skittlesList.isEmpty()) {
            text = "Hey your list is empty man! Try adding a Task first!";
        } else if (taskId <= skittlesList.size()){
            Task intendedItem = skittlesList.get(taskId - 1);
            intendedItem.strike();
            text = "Nice! I've marked this task as done:\n" + " " + intendedItem.toString();
        }
        System.out.println(text);
    }

    /**
     * Deletes a specific task that Skittles has stored.
     * @param input The entire String that the user has input i.e. "delete 2".
     * @throws SkittlesException If an incorrect input is entered.
     */
    public static void delete(String input) throws SkittlesException{
        int itemNumber;
        if (input.split(" ", 2).length == 1) {
            throw new SkittlesException("☹ Oops! Looks like you are missing the number of the task you wish to delete! Try again :-)");
        }
        String numberInput = input.split(" ", 2)[1];
        try {
            itemNumber = Integer.parseInt(numberInput);
        } catch (NumberFormatException e) {
            throw new SkittlesException("You may have entered something incorrectly. Try adding a number behind 'delete'!");
        }
        String message = "☹ Oops! You may have incorrectly entered a number. Try again!";
        if (itemNumber > skittlesList.size()) {
            throw new SkittlesException("☹ Oops! Looks like you are trying to delete something that is not in your list! Try again!");
        }
        Task removed = skittlesList.remove(itemNumber - 1);
        System.out.println("Noted. I've removed this task:\n" + removed.toString() + "\nNow you have " + skittlesList.size()
                + " tasks in the list");
    }

    /**
     * Updates the skittlesList.
     * @throws SkittlesException if cannot be updated.
     */
    public static void refresh() throws SkittlesException {
        Data.updateTaskInTxt(skittlesList);
    }

    /**
     * Searches the entire skittlesList for Tasks that contain the keyword.
     * @param wordUserIsSearching User input that they wish to search for.
     * @return An ArrayList of Tasks that contain the keyword.
     */
    public static ArrayList<Task> findInList(String wordUserIsSearching) {
        ArrayList<Task> outcome = new ArrayList<>();
        for (Task task : skittlesList) {
            if (task.toString().toLowerCase().contains(wordUserIsSearching.toLowerCase())) {
                outcome.add(task);
            }
        }
        return outcome;
    }

    public static ArrayList<Task> getSkittlesList() {
        return ListOfStuff.skittlesList;
    }

    public int getSize() {
        return skittlesList.size();
    }

    public Task removeTask(int i) {
        return skittlesList.remove(i);
    }

    /**
     * Check if skittlesList is empty.
     *
     * @return Boolean relating to if skittlesList is empty or not.
     */
    public boolean isEmpty() {
        return skittlesList.isEmpty();
    }

    /**
     * Checks if task is in the list that skittles has.
     * @param task Task that is to be checked with.
     * @return A boolean that will equal true should the task be found.
     */
    public static boolean contains(Task task) {
        for (Task t : skittlesList) {
            if (t.toString().equals(task.toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return Task at index i of skittlesList
     *
     * @param i index of task in skittlesList.
     * @return Task
     */
    public Task getTask(int i) {
        return skittlesList.get(i);
    }

    public static int numOfThings() {
        return ListOfStuff.skittlesList.size();
    }
}
