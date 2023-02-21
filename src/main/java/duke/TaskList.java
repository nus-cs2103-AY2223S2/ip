package duke;

import duke.exceptions.DukeyException;

import static java.lang.Integer.parseInt;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Stores and performs various operations to a list of Tasks.
 */
public class TaskList {
    private ArrayList<Task> list;
    private Ui ui;

    /**
     * Returns a new empty TaskList
     * @param ui object that deals with user interactions.
     */
    public TaskList(Ui ui) {
        this.list = new ArrayList<>();
        this.ui = ui;
    }

    /**
     * Returns a new empty TaskList
     */
    public TaskList() {
        this.list = new ArrayList<>();
        this.ui = new Ui();
    }

    /**
     * Returns true if the TaskList does not contain any Tasks.
     */
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /**
     * Returns an iterator of the list of Tasks.
     */
    public Iterator<Task> getIterator() {
        return this.list.iterator();
    }

    /**
     * Adds a new Task to the TaskList, printing out a confirmation message for the user.
     * @param task the Task to be added
     */
    public void addTask(Task task) {
        this.list.add(task);
        ui.printAddedMessage(task);
        printSize();
    }

    /**
     * Adds a new Task to the TaskList.
     * @param task the Task to be added
     */
    public void addTaskFromSave(Task task) {
        this.list.add(task);
    }

    /**
     * Prints out the TaskList.
     */
    public void readList() {
        ui.readList(this.list);
    }

    /**
     * Changes the status of a Task to done and prints out a confirmation message for the
     * user. This method makes use of ui to read input from the user for details regarding the task to
     * be marked.
     * @exception DukeyException on invalid task numbers
     */
    public void mark() throws DukeyException {
        String taskNumberString = ui.getTaskNumber();
        int taskNumber;
        try {
            taskNumber = parseInt(taskNumberString.strip()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeyException("Error! Invalid task number format!");
        }

        if (taskNumber < 0) {
            throw new DukeyException("Error! Invalid task number");
        } else if (taskNumber >= this.list.size()) {
            throw new DukeyException("Error! DukeyList only contains " + this.list.size() + " tasks");
        }

        Task taskToMark = this.list.get(taskNumber);
        taskToMark.markAsDone();
        ui.printMarkedMessage(taskNumber, taskToMark);
    }


    /**
     * Changes the status of a Task to undone and prints out a confirmation message for the
     * user. This method makes use of ui to read input from the user for details regarding the task to
     * be marked.
     * @exception DukeyException on invalid task numbers
     */
    public void unmark() throws DukeyException {
        String taskNumberString = ui.getTaskNumber();
        int taskNumber;
        try {
            taskNumber = parseInt(taskNumberString.strip()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeyException("Error! Invalid task number format!");
        }

        if (taskNumber < 0) {
            throw new DukeyException("Error! Invalid task number");
        } else if (taskNumber >= this.list.size()) {
            throw new DukeyException("Error! DukeyList only contains " + this.list.size() + " tasks");
        }
        Task taskToUnmark = this.list.get(taskNumber);
        taskToUnmark.unmark();
        ui.printUnmarkedMessage(taskNumber, taskToUnmark);
    }

    /**
     * Deletes a Task from the TaskList. This method makes use of ui to read input from the user for details
     * regarding the task to be deleted.
     * @exception DukeyException on invalid task numbers
     */
    public void delete() throws DukeyException {
        String taskNumberString = ui.getTaskNumber();
        int taskNumber;
        try {
            taskNumber = parseInt(taskNumberString.strip()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeyException("Error! Invalid task number format!");
        }

        if (taskNumber < 0) {
            throw new DukeyException("Error! Invalid task number");
        } else if (taskNumber >= this.list.size()) {
            throw new DukeyException("Error! DukeyList only contains " + this.list.size() + " tasks");
        }

        Task taskToRemove = list.get(taskNumber);
        ui.printDeletedMessage(taskToRemove);
        list.remove(taskNumber);
        this.printSize();
    }

    /**
     * Returns the number of Tasks in the TaskList.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Prints out the number of Tasks in the TaskList.
     */
    public void printSize() {
        ui.printSize(getSize());
    }

    /**
     * Clears the TaskList.
     */
    public void clearList() {
        this.list.clear();
        System.out.println("DukeyList cleared! DukeyList is now empty.");
    }

    /**
     * Saves the TaskList locally.
     * @param storage the class that deals with loading and saving Tasks
     */
    public void save(Storage storage) {
        try {
            storage.save(this);
        } catch (DukeyException e) {
            ui.printExceptionMessage(e);
        }

        ui.printSavedMessage();
    }

    /**
     * Loads Tasks into the TaskList from a save, if there are any.
     * @param storage the class that deals with loading and saving Tasks
     * @exception FileNotFoundException on missing saved file
     */
    public void initiate(Storage storage) throws FileNotFoundException {
        storage.load(this);

        if (this.list.isEmpty()) {
            ui.printLoadMessage(0);
        } else {
            ui.printLoadMessage(1);
            ui.readList(list);
        }

    }

    /**
     * Clears any Tasks that are saved on the hard drive.
     * @param storage the class that deals with loading and saving Tasks
     */
    public void clearSave(Storage storage) {
        storage.clearFile();
        ui.printClearedMessage();
    }



}
