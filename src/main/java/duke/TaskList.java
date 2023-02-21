package duke;

import static java.lang.Integer.parseInt;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import duke.exceptions.DukeyException;

/**
 * Stores and manages Tasks.
 */
public class TaskList {
    private ArrayList<Task> list;
    private Ui ui;

    /**
     * Returns an empty TaskList.
     */
    public TaskList(Ui ui) {
        this.list = new ArrayList<>();
        this.ui = ui;
    }

    /**
     * Returns an empty TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
        this.ui = new Ui();
    }

    /**
     * Returns true if the TaskList has no Tasks.
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
     * Adds a new Task to the TaskList and prints out a confirmation message for the user.
     */
    public void addTask(Task task) {
        this.list.add(task);
        ui.printAddedMessage(task);
        printSize();
    }

    /**
     * Adds a new Task to the TaskList without printing anything. This method is used to load Tasks into
     * the TaskList from a save File.
     */
    public void addTaskFromSave(Task task) {
        this.list.add(task);
    }

    /**
     * Prints out all the Tasks in the TaskList.
     */
    public void readList() {
        ui.readList(this.list);
    }

    /**
     * Marks a particular Task as done. This method uses the Ui class to read input from the user to get
     * details about which Task is to be marked.
     * @throws DukeyException on invalid Task number from user input
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
        taskToMark.setMarked();
        ui.printMarkedMessage(taskNumber, taskToMark);
    }

    /**
     * Marks a particular Task as undone. This method uses the Ui class to read input from the user to get
     * details about which Task is to be unmarked.
     * @throws DukeyException on invalid task number from user input
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
        taskToUnmark.setUnmarked();
        ui.printUnmarkedMessage(taskNumber, taskToUnmark);
    }

    /**
     * Deletes a Task from the TaskList. This method uses the Ui class to read input from the user to get
     * details about which Task is to be deleted.
     * @throws DukeyException on invalid Task number from user input
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
     * Finds Tasks in the TaskList that contains a particular keyword. This method
     * uses the Ui class to read input from the user to get the keyword. The found Tasks are then printed out.
     */
    public void find() {
        String keyword = "";
        try {
            keyword = ui.readKeyword();
        } catch (DukeyException e) {
            ui.printExceptionMessage(e);
        }

        String confirmedKeyword = keyword;
        ArrayList<TaskNumberPair> foundTaskList = new ArrayList<>();
        Iterator<Task> it = getIterator();

        it.forEachRemaining(x -> {
            if (x.getName().contains(confirmedKeyword)) {
                foundTaskList.add(new TaskNumberPair(x, list.indexOf(x)));
            }
        });

        ui.printFoundTaskList(foundTaskList);

    }

    /**
     * Returns the number of Tasks in the TaskList.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Uses the Ui class to print out the number of Tasks in the TaskList.
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
     * Saves the current TaskList to the save File.
     * @param storage deals with the saving and loading of data to the save File.
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
     * Loads all the Tasks in the save File to the TaskList.
     * @param storage deals with the saving and loading of data to the save File.
     * @throws FileNotFoundException on missing save file
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
     * Clears the save File.
     */
    public void clearSave(Storage storage) {
        storage.clearFile();
        ui.printClearedMessage();
    }



}
