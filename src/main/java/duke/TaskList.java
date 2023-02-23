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
     * @return the confirmation message for the user
     */
    public String addTask(Task task) {
        assert !task.equals(null);
        this.list.add(task);
        return ui.printAddedMessage(task) + '\n' + printSize();
    }

    /**
     * Adds a new Task to the TaskList without printing anything. This method is used to load Tasks into
     * the TaskList from a save File.
     */
    public void addTaskFromSave(Task task) {
        assert !task.equals(null);
        this.list.add(task);
    }

    /**
     * Prints out all the Tasks in the TaskList.
     * @return the string containing the list of tasks
     */
    public String readList() {
        return ui.readList(this.list);
    }

    /**
     * Marks a particular Task as done. This method uses the Ui class to read input from the user to get
     * details about which Task is to be marked.
     * @return the confirmation message for the user
     * @throws DukeyException on invalid Task number from user input
     */
    public String mark(String input) throws DukeyException {
        String[] inputArray = input.split("/");
        if (inputArray.length != 2) {
            throw new DukeyException("Error, Invalid Format!");
        }
        int taskNumber;
        try {
            taskNumber = parseInt(inputArray[1].strip()) - 1;
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
        return ui.printMarkedMessage(taskNumber, taskToMark);
    }

    /**
     * Marks a particular Task as undone. This method uses the Ui class to read input from the user to get
     * details about which Task is to be unmarked.
     * @return the confirmation message for the user
     * @throws DukeyException on invalid task number from user input
     */
    public String unmark(String input) throws DukeyException {
        String[] inputArray = input.split("/");
        if (inputArray.length != 2) {
            throw new DukeyException("Error, Invalid Format!");
        }
        int taskNumber;
        try {
            taskNumber = parseInt(inputArray[1].strip()) - 1;
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
        return ui.printUnmarkedMessage(taskNumber, taskToUnmark);
    }

    /**
     * Deletes a Task from the TaskList. This method uses the Ui class to read input from the user to get
     * details about which Task is to be deleted.
     * @return the confirmation message for the user
     * @throws DukeyException on invalid Task number from user input
     */
    public String delete(String input) throws DukeyException {
        String[] inputArray = input.split("/");
        if (inputArray.length != 2) {
            throw new DukeyException("Error, Invalid Format!");
        }
        int taskNumber;
        try {
            taskNumber = parseInt(inputArray[1].strip()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeyException("Error! Invalid task number format!");
        }

        if (taskNumber < 0) {
            throw new DukeyException("Error! Invalid task number");
        } else if (taskNumber >= this.list.size()) {
            throw new DukeyException("Error! DukeyList only contains " + this.list.size() + " tasks");
        }

        Task taskToRemove = list.get(taskNumber);
        String response = ui.printDeletedMessage(taskToRemove);
        list.remove(taskNumber);
        response += '\n' + this.printSize();
        return response;
    }

    /**
     * Finds Tasks in the TaskList that contains a particular keyword. This method
     * uses the Ui class to read input from the user to get the keyword. The found Tasks are then printed out.
     * @return the confirmation message for the user
     */
    public String find(String input) throws DukeyException {
        String[] inputArray = input.split("/");
        if (inputArray.length != 2) {
            throw new DukeyException("Error! Invalid format!");
        }
        String keyword = ui.readKeyword(inputArray[1]);
        String confirmedKeyword = keyword;
        ArrayList<TaskNumberPair> foundTaskList = new ArrayList<>();
        Iterator<Task> it = getIterator();

        it.forEachRemaining(x -> {
            if (x.getName().contains(confirmedKeyword)) {
                foundTaskList.add(new TaskNumberPair(x, list.indexOf(x)));
            }
        });

        return ui.printFoundTaskList(foundTaskList);

    }

    /**
     * Returns the number of Tasks in the TaskList.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Uses the Ui class to print out the number of Tasks in the TaskList.
     * @return the string containing the size
     */
    public String printSize() {
        return ui.printSize(getSize());
    }

    /**
     * Clears the TaskList.
     */
    public String clearList() {
        this.list.clear();
        return ui.printClearedMessage();
    }

    /**
     * Saves the current TaskList to the save File.
     * @return the confirmation message for the user
     * @param storage deals with the saving and loading of data to the save File.
     */
    public String save(Storage storage) throws DukeyException {
        storage.save(this);
        return ui.printSavedMessage();
    }

    /**
     * Loads all the Tasks in the save File to the TaskList.
     * @param storage deals with the saving and loading of data to the save File.
     * @throws FileNotFoundException on missing save file
     */
    public int initiate(Storage storage) throws FileNotFoundException {
        assert !storage.equals(null);
        storage.load(this);

        if (this.list.isEmpty()) {
            return 0;
        } else {
            return 1;
        }

    }

    /**
     * Clears the save File.
     */
    public void clearSave(Storage storage) {
        assert !storage.equals(null);
        storage.clearFile();
        ui.printClearedMessage();
    }



}
