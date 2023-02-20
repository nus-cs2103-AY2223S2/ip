package botanic.task;

import java.time.LocalDate;
import java.util.ArrayList;

import botanic.Formatter;
import botanic.exception.OutOfBoundsException;
import botanic.gui.Gui;

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
        tasks = new ArrayList<>();
    }

    /**
     * Instantiates TaskList with the given ArrayList.
     *
     * @param tasks The list of our tasks fetched from hard drive storage.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns this list of tasks.
     *
     * @return The ArrayList containing the tasks.
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Adds tasks into the list and prints out completion message when done.
     *
     * @param task The task to be added.
     * @return A string message to signify a successful task addition.
     */
    public String add(Task task) {
        boolean isAdded = tasks.add(task);
        assert isAdded : "Task is not added to the task basket successfully.";
        String response = gui.getAddSuccessMsg(task, tasks.size());
        return response;
    }

    /**
     * Deletes task at the given index.
     *
     * @param index The index of task to be deleted.
     * @return A string message to signify a successful task deletion.
     * @throws OutOfBoundsException If index given is less than 0
     *                              or more than the index of the last list element.
     */
    public String delete(int index) throws OutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new OutOfBoundsException(gui.getOutOfBoundsErrorMsg());
        }
        assert index > 0 && index < tasks.size() : "Index given is out of bounds";

        Task removed = tasks.remove(index);
        assert removed != null : "Task at index not removed.";
        return gui.getDeleteSuccessMsg(removed, tasks.size());
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index The index number of the task given.
     * @return A string message to signify the successful marking of task as done.
     * @throws OutOfBoundsException If index given is less than 0
     *                              or more than the index of the last list element.
     */
    public String markIsDone(int index) throws OutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new OutOfBoundsException(gui.getOutOfBoundsErrorMsg());
        }
        assert index > 0 && index < tasks.size() : "Index given is out of bounds";

        Task task = tasks.get(index);
        task.setDone(true);
        return gui.getMarkSuccessMsg(task);
    }

    /**
     * Marks the task at the given index as not done.
     *
     * @param index The index number of the task given.
     * @return A string message to signify the success marking of task as not done.
     * @throws OutOfBoundsException If index given is less than 0
     *                              or more than the index of the last list element.
     */
    public String unmarkIsDone(int index) throws OutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new OutOfBoundsException(gui.getOutOfBoundsErrorMsg());
        }
        assert index > 0 && index < tasks.size() : "Index given is out of bounds";

        Task task = tasks.get(index);
        task.setDone(false);
        return gui.getUnmarkSuccessMsg(task);
    }

    /**
     * Appends all the tasks in the list into a string.
     *
     * @return A string representation of the list of all the tasks.
     */
    public String print() {
        int size = tasks.size();
        if (size == 0) {
            return gui.getNoItemErrorMsg();
        }
        assert size != 0 : "List size is 0";
        return gui.printTasks(this);
    }

    /**
     * Searches for tasks with names that completely matches
     * the given keyword (case-insensitive) and returns the result.
     *
     * @param keyword The keyword to search for.
     * @return The results of the search.
     */
    public String findAllMatch(String keyword) {
        int size = tasks.size();
        TaskList searchResult = new TaskList();
        for (int currIndex = 0; currIndex < size; currIndex++) {
            Task curr = tasks.get(currIndex);
            if (curr.hasKeyword(" " + keyword + " ")) {
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
     * the given keyword (case-insensitive) and returns the result.
     *
     * @param keyword The keyword to search for.
     * @return The results of the search.
     */
    public String findFlexibly(String keyword) {
        int size = tasks.size();
        TaskList searchResult = new TaskList();
        for (int currIndex = 0; currIndex < size; currIndex++) {
            Task curr = tasks.get(currIndex);
            if (curr.hasKeyword(keyword)) {
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
     * the given date and returns the result.
     *
     * @param dateToFind The date to search for.
     * @return The results of the search.
     */
    public String findDate(LocalDate dateToFind) {
        int size = tasks.size();
        TaskList searchResult = new TaskList();
        for (int currIndex = 0; currIndex < size; currIndex++) {
            Task curr = tasks.get(currIndex);
            if (curr.hasDate(dateToFind)) {
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
     * Returns a string representation of all the tasks in the list.
     *
     * @return A string representation of the list.
     */
    @Override
    public String toString() {
        int size = tasks.size();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(String.format("%d. %s\n", (i + 1), tasks.get(i)));
        }
        return sb.toString();
    }
}
