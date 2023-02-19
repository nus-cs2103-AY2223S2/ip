package elems;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

import dukeexceptions.StorageException;
import dukeexceptions.TaskListIndexOutOfBoundsException;
import items.Deadline;
import items.Event;
import items.Task;
import items.ToDo;

/**
 * Represents the object that stores the Tasks
 * @author clydelhui
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Generates a new TaskList object which loads from the given <code>Storage</code>
     * @param storage The storage to load the data from
     * @param ui The <code>Ui</code> to display error messages with
     */
    public TaskList(Storage storage, Ui ui) {
        try {
            loadFromStorageText(storage.load());
        } catch (FileNotFoundException | StorageException e) {
            ui.errorDisplay(e);
            ui.dukeDisplay("Issue with the storage file, generating empty list!");
            this.taskList = new ArrayList<>();
        }

    }

    /**
     * Deletes the <code>Task</code> at a given index (1 based indexing) from the <code>TaskList</code>
     * @param index The index of the <code>Task</code> to be deleted
     * @return The deleted <code>Task</code>
     * @throws TaskListIndexOutOfBoundsException if the index given is out of bounds of the <code>TaskList</code>
     */
    public Task delete(int index) throws TaskListIndexOutOfBoundsException {
        assert this.taskList != null;
        try {
            return this.taskList.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskListIndexOutOfBoundsException("Tried to delete a task that is out of task list bounds");
        }

    }

    /**
     * Generates a string array containing the String representation of
     * all the <code>Tasks</code> in the <code>TaskList</code>
     * @return A string array containing the String representation of
     *      * all the <code>Tasks</code> in the <code>TaskList</code>
     */
    public String[] enumerate() {
        assert this.taskList != null;
        int length = this.taskList.size();
        String[] taskStringList = new String[length];
        for (int i = 0; i < length; i++) {
            taskStringList[i] = taskList.get(i).toString();
        }
        return taskStringList;
    }

    public void addTask(Task newTask) {
        assert this.taskList != null;
        this.taskList.add(newTask);
    }

    /**
     * Marks the <code>Task</code> at a given index (1 based indexing)
     * @param index The index of the <code>Task</code>
     * @throws TaskListIndexOutOfBoundsException when the index is out of bounds of the <code>TaskList</code>
     */
    public void markTask(int index) throws TaskListIndexOutOfBoundsException {
        assert this.taskList != null;
        try {
            Task mark = this.taskList.get(index - 1);
            mark.setDone();
        } catch (IndexOutOfBoundsException e) {
            throw new TaskListIndexOutOfBoundsException("Tried to mark task that is out of task list bounds");
        }
    }

    /**
     * Unmarks the <code>Task</code> at a given index(1 based indexing)
     * @param index The index of the <code>Task</code> to be unmarked
     * @throws TaskListIndexOutOfBoundsException when an index is out of bounds of the given <code>TaskList</code>
     */
    public void unmarkTask(int index) throws TaskListIndexOutOfBoundsException {
        assert this.taskList != null;
        try {
            Task unmark = this.taskList.get(index - 1);
            unmark.setNotDone();
        } catch (IndexOutOfBoundsException e) {
            throw new TaskListIndexOutOfBoundsException("Tried to unmark task that is out of task list bounds");
        }
    }

    public String getListStorageText() {
        StringBuilder output = new StringBuilder();
        for (Task task : this.taskList) {
            String taskText = task.generateStorageForm() + System.lineSeparator();
            output.append(taskText);
        }
        return output.toString();
    }

    public String getTaskString(int index) {
        Task task = this.taskList.get(index - 1);
        return task.toString();
    }

    /**
     * Returns a String array which contains the String representations of the Tasks in the TaskList
     * with descriptions that contain the given String search term
     * @param searchTerm The search term to find in the descriptions of the Tasks
     * @return A String array which contains the String representations of the Tasks in the TaskList
     *     with descriptions that contain the given String search term
     */
    public String[] searchTaskDescription(String searchTerm) {
        assert this.taskList != null;
        ArrayList<String> matchingItems = new ArrayList<>();
        for (Task current : this.taskList) {
            String currentDescription = current.getDescription();
            String[] parsedDescription = currentDescription.split(" ");
            for (String word : parsedDescription) {
                if (word.equals(searchTerm)) {
                    matchingItems.add(current.toString());
                }
            }
        }
        String[] result = new String[matchingItems.size()];
        matchingItems.toArray(result);
        return result;
    }
    private void loadFromStorageText(ArrayList<String> storageText) throws StorageException {
        this.taskList = new ArrayList<>();
        String[] taskStringArray = storageText.toArray(new String[0]);

        for (String taskText : taskStringArray) {
            String[] parsedInput = taskText.split("@");

            boolean isDone = parsedInput[2].equals("X");
            switch (parsedInput[0]) {
            case "T":
                this.taskList.add(new ToDo(parsedInput[1], isDone));
                break;
            case "D":
                this.taskList.add(new Deadline(parsedInput[1], isDone, LocalDate.parse(parsedInput[3])));
                break;
            case "E":
                this.taskList.add(new Event(parsedInput[1], isDone, LocalDate.parse(parsedInput[3]),
                        LocalDate.parse(parsedInput[4])));
                break;
            default:
                throw new StorageException("Corrupted data file!");
            }
        }
    }
}
