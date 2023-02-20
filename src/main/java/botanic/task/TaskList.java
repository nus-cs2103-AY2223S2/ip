package botanic.task;

import java.time.LocalDate;
import java.util.ArrayList;

import botanic.Formatter;
import botanic.gui.Gui;
import botanic.exception.OutOfBoundsException;

/**
 * Encapsulates the related fields and behavior of the list containing tasks.
 */
public class TaskList {
    /**
     * An ArrayList to store the tasks.
     */
    private ArrayList<Task> tasks;
    private Gui gui = new Gui();

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
        String response = gui.getAddSuccessMsg(task, this.tasks.size());
        return response;
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
            throw new OutOfBoundsException(gui.getOutOfBoundsErrorMsg());
        }
        assert index > 0 && index < this.tasks.size() : "Index given is out of bounds";

        Task removed = this.tasks.remove(index);
        assert removed != null : "Task at index not removed.";
        return gui.getDeleteSuccessMsg(removed, this.tasks.size());
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
            throw new OutOfBoundsException(gui.getOutOfBoundsErrorMsg());
        }
        assert index > 0 && index < this.tasks.size() : "Index given is out of bounds";

        Task task = this.tasks.get(index);
        task.markIsDone();
        return gui.getMarkSuccessMsg(task);
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
            throw new OutOfBoundsException(gui.getOutOfBoundsErrorMsg());
        }
        assert index > 0 && index < this.tasks.size() : "Index given is out of bounds";

        Task task = this.tasks.get(index);
        task.unmarkIsDone();
        return gui.getUnmarkSuccessMsg(task);
    }

    /**
     * Appends all the tasks in the list into a string.
     *
     * @return A string representation of the list of all the tasks.
     */
    public String print() {
        int size = this.tasks.size();
        if (size == 0) {
            return gui.getNoItemErrorMsg();
        }
        assert size != 0 : "List size is 0";
        return gui.printTasks(this);
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
        TaskList searchResult = new TaskList();
        for (int currIndex = 0; currIndex < size; currIndex++) {
            Task curr = this.tasks.get(currIndex);
            if (curr.containKeyword(" " + keyword + " ")) {
                searchResult.add(curr);
            }
        }
        int resultSize = searchResult.getTaskList().size();
        if (resultSize > 0) {
            return gui.getFindResult(searchResult);
        }
        assert resultSize < 0 : "Items found but not printed.";
        return gui.getNoCompleteMatchErrorMsg(keyword);
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
        TaskList searchResult = new TaskList();
        for (int currIndex = 0; currIndex < size; currIndex++) {
            Task curr = this.tasks.get(currIndex);
            if (curr.containKeyword(keyword)) {
                searchResult.add(curr);
            }
        }
        int resultSize = searchResult.getTaskList().size();
        if (resultSize > 0) {
            return gui.getFindResult(searchResult);
        }
        assert resultSize < 0 : "Items found but not printed.";
        return gui.getNoPartialMatchErrorMsg(keyword);
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
        TaskList searchResult = new TaskList();
        for (int currIndex = 0; currIndex < size; currIndex++) {
            Task curr = this.tasks.get(currIndex);
            if (curr.containDate(dateToFind)) {
                searchResult.add(curr);
            }
        }
        int resultSize = searchResult.getTaskList().size();
        if (resultSize > 0) {
            return gui.getFindResult(searchResult);
        }
        assert resultSize < 0 : "Items found but not printed.";
        String dateFormatted = Formatter.formatDateForPrint(dateToFind);
        return gui.getNoDateMatchErrorMsg(dateFormatted);
    }

    /**
     * Gets a string representation of all the tasks in the list.
     *
     * @return A string representation of the list.
     */
    @Override
    public String toString() {
        int size = this.tasks.size();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(String.format("%d. %s\n", (i + 1), this.tasks.get(i)));
        }
        return sb.toString();
    }
}
