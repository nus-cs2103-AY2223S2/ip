package botanic.task;

import java.time.LocalDate;
import java.util.ArrayList;

import botanic.Formatter;
import botanic.exception.OutOfBoundsException;

/**
 * Encapsulates the related fields and behavior of the list containing tasks.
 */
public class TaskList {
    /**
     * An ArrayList to store the tasks.
     */
    private ArrayList<Task> tasks;

    /**
     * Instantiates TaskList with no arguments given.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Instantiates TaskList with the given ArrayList.
     *
     * @param taskList The list of our tasks fetched from hard drive storage.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Returns this list of tasks.
     *
     * @return The ArrayList containing the tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Adds tasks into the list and prints out completion message when done.
     *
     * @param task The task to be added.
     * @return A string message to signify the success or failure of task executed.
     */
    public String add(Task task) {
        boolean hasAdded = this.tasks.add(task);
        assert hasAdded : "Task is not added to the task basket successfully.";
        String output = "Pear-fect! I've added this task:\n " + task + "\nNow you have "
                + this.tasks.size() + " task(s) in your basket.\n";
        return output;
    }

    /**
     * Deletes task at the given index.
     *
     * @param index The index of task to be deleted.
     * @return A string message to signify the success or failure of task executed.
     * @throws OutOfBoundsException If index given is less than 0
     *                              or more than the index of the last list element.
     */
    public String delete(int index) throws OutOfBoundsException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new OutOfBoundsException("Item at given index does not exist! "
                    + "Please enter a valid index.");
        }
        assert index > 0 && index < this.tasks.size() : "Index given is out of bounds";

        Task removed = this.tasks.remove(index);
        assert removed != null : "Task at index not removed.";

        return "Gourd it! I've removed this task:\n " + " "
                + removed + "\nNow you have " + this.tasks.size()
                + " task(s) in your basket.\n";
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index The index number of the task given.
     * @return A string message to signify the success of failure of task executed.
     * @throws OutOfBoundsException If index given is less than 0
     *                              or more than the index of the last list element.
     */
    public String markIsDone(int index) throws OutOfBoundsException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new OutOfBoundsException("Item at given index does not exist! "
                    + "Please enter a valid index.");
        }
        assert index > 0 && index < this.tasks.size() : "Index given is out of bounds";

        this.tasks.get(index).markIsDone();
        return "Sweet! I've marked this task as done:\n "
                + this.tasks.get(index) + "\n";
    }

    /**
     * Marks the task at the given index as not done.
     *
     * @param index The index number of the task given.
     * @return A string message to signify the success of failure of task executed.
     * @throws OutOfBoundsException If index given is less than 0
     *                              or more than the index of the last list element.
     */
    public String unmarkIsDone(int index) throws OutOfBoundsException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new OutOfBoundsException("Item at given index does not exist! "
                    + "Please enter a valid index.");
        }
        assert index > 0 && index < this.tasks.size() : "Index given is out of bounds";

        this.tasks.get(index).unmarkIsDone();
        return "Grape! I've marked this task as not done:\n "
                + this.tasks.get(index) + "\n";
    }

    /**
     * Appends all the tasks in the list into a string.
     *
     * @return A string representation of the list of all the tasks.
     */
    public String print() {
        int size = this.tasks.size();
        if (size == 0) {
            return "There are no items in your basket.\n"
                    + "Start adding some!\n";
        }
        assert size != 0 : "List size is 0";

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your basket:");
        for (int i = 0; i < size; i++) {
            sb.append(String.format("\n%d. %s", (i + 1), this.tasks.get(i)));
        }
        return sb.toString();
    }

    /**
     * Searches for tasks with names that completely matches
     * the given keyword (case-insensitive) and prints them out.
     *
     * @param keyword The keyword to search for.
     * @return The results of the search.
     */
    public String findAllMatch(String keyword) {
        int size = this.tasks.size();
        int currIndex = 0;
        int printIndex = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your basket:");
        while (currIndex < size) {
            Task curr = this.tasks.get(currIndex);
            if (curr.containKeyword(" " + keyword + " ")) {
                sb.append(String.format("\n%d. %s", printIndex, curr));
                printIndex++;
            }
            currIndex++;
        }
        if (printIndex == 1) {
            return "None of the items in your basket completely matches with \"" + keyword + "\"";
        }
        assert printIndex > 1 : "Item could not be found.";
        return sb.toString();
    }

    /**
     * Searches for tasks with names that partially or completely matches
     * the given keyword (case-insensitive) and prints them out.
     *
     * @param keyword The keyword to search for.
     * @return The results of the search.
     */
    public String findFlexibly(String keyword) {
        int size = this.tasks.size();
        int currIndex = 0;
        int printIndex = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your basket:");
        while (currIndex < size) {
            Task curr = this.tasks.get(currIndex);
            if (curr.containKeyword(keyword)) {
                sb.append(String.format("\n%d. %s", printIndex, curr));
                printIndex++;
            }
            currIndex++;
        }
        if (printIndex == 1) {
            return "None of the items in your basket contains the keyword \"" + keyword + "\"";
        }
        assert printIndex > 1 : "Item could not be found.";
        return sb.toString();
    }

    /**
     * Searches for tasks with dates that matches
     * the given date and prints them out.
     *
     * @param dateToFind The date to search for.
     * @return The results of the search.
     */
    public String findDate(LocalDate dateToFind) {
        int size = this.tasks.size();
        int currIndex = 0;
        int printIndex = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your basket:");
        while (currIndex < size) {
            Task curr = this.tasks.get(currIndex);
            if (curr.containDate(dateToFind)) {
                sb.append(String.format("\n%d. %s", printIndex, curr));
                printIndex++;
            }
            currIndex++;
        }
        if (printIndex == 1) {
            String dateFormatted = Formatter.formatDateForPrint(dateToFind);
            return "None of the items in your basket have the date \"" + dateFormatted + "\"";
        }
        assert printIndex > 1 : "Item could not be found.";
        return sb.toString();
    }
}
