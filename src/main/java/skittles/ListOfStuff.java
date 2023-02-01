package skittles;
import java.util.ArrayList;
import java.util.Locale;

public class ListOfStuff {

    private static ArrayList<Task> skittlesList;

    public ListOfStuff(ArrayList<Task> skittlesList) {
        this.skittlesList = skittlesList;
    }

    public static void add(Task task) {
        skittlesList.add(task);
    }

    /**
     * Shows all Tasks in the list that the user has given to Skittles to store.
     * Tasks are ordered from least recent to most recent. If no tasks have been given to Skittles,
     * the appropriate message is shown.
     */
    public static void displayLst() {

        String textToDisplayLst = "Here are the tasks in your list:";
        String textForEmptyLst = "Hey looks like your list is empty man!";
        if (skittlesList == null) {
            System.out.println(textForEmptyLst);
        } else {
            for (int i = 0; i < skittlesList.size(); i++) {
                textToDisplayLst += "\n" + (i + 1) + "." + skittlesList.get(i).toString();
            }
            System.out.println(textToDisplayLst);
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

    public static void refresh() throws SkittlesException {
        Data.updateTaskInTxt(skittlesList);
    }

    /**
     * Searches the entire dukeList for Tasks that contain the keyword.
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

    public static int numOfThings() {
        return ListOfStuff.skittlesList.size();
    }
}
