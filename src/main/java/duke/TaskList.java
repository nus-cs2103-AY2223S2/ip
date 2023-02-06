package duke;

import java.util.ArrayList;

import exceptions.DukeException;
import exceptions.InvalidDataFileException;
import exceptions.TaskNotFoundException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

/**
 * Wraps the task list and provides methods to interact with it.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Constructs a new task list wrapper.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a new task list wrapper using parsed input from a data file.
     *
     * @param dataFileTasks Lines of parsed input commands from the data file.
     * @throws DukeException if the command given is invalid or is not a command known by the chatbot.
     */
    public TaskList(ArrayList<String[]> dataFileTasks) throws DukeException {
        this.taskList = new ArrayList<>();
        Task newTask;
        for (String[] fileCommand : dataFileTasks) {
            assert fileCommand.length > 0;
            int priority;
            try {
                priority = Integer.parseInt(fileCommand[2]);
                if (priority <= 0) {
                    throw new InvalidDataFileException();
                }
            } catch (Exception e) {
                throw new InvalidDataFileException();
            }
            switch (fileCommand[0]) {
            case "T":
                newTask = new ToDo(fileCommand[3], priority);
                break;
            case "D":
                newTask = new Deadline(fileCommand[3], priority, Parser.parseDate(fileCommand[4], true));
                break;
            case "E":
                newTask = new Event(fileCommand[3], priority, Parser.parseDate(fileCommand[4], true),
                    Parser.parseDate(fileCommand[5], true));
                break;
            default:
                throw new InvalidDataFileException();
            }
            if (fileCommand[1].equals("X")) {
                newTask.setDone(true);
            }

            this.addTask(newTask);
        }
    }

    /**
     * Gets the length of the task list.
     *
     * @return Length of the task list.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return Whether the task list is empty or not.
     */
    public boolean isEmpty() {
        return this.taskList.size() == 0;
    }

    private boolean isIndexOutOfRange(int index) {
        return index - 1 >= this.size() || index < 0;
    }

    /**
     * Gets a task by its index.
     *
     * @param taskNumber Index of the task.
     * @return The requested task.
     * @throws TaskNotFoundException if task index is out of range.
     */
    public Task getTask(int taskNumber) throws TaskNotFoundException {
        if (this.isIndexOutOfRange(taskNumber)) {
            throw new TaskNotFoundException();
        }
        return this.taskList.get(taskNumber - 1);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskNumber Index of the task to be deleted.
     * @throws TaskNotFoundException if task index is out of range.
     */
    public void deleteTask(int taskNumber) throws TaskNotFoundException {
        if (this.isIndexOutOfRange(taskNumber)) {
            throw new TaskNotFoundException();
        }
        this.taskList.remove(taskNumber - 1);
    }

    /**
     * Marks a task as complete or uncomplete.
     *
     * @param taskNumber Index of the task.
     * @param done       Whether to mark the task as complete or uncomplete.
     * @throws TaskNotFoundException if task index is out of range.
     */
    public void setDone(int taskNumber, boolean done) throws TaskNotFoundException {
        if (this.isIndexOutOfRange(taskNumber)) {
            throw new TaskNotFoundException();
        }
        this.taskList.get(taskNumber - 1).setDone(done);
    }

    /**
     * Finds tasks from the task list matching the given search string.
     *
     * @param searchString String to search for in the tasks.
     * @return List of tasks matching the search string.
     */
    public ArrayList<Task> findTasks(String searchString) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.match(searchString)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Describes the length of the task list in a human readable format.
     *
     * @return Description of the task list in a human readable format.
     */
    public String describeLength() {
        return "Now you have " + this.size() + " tasks in the list.";
    }

    /**
     * Represents the task list in a string suitable for storing in memory.
     *
     * @return Representation of the task list to be stored in memory.
     */
    public String toEncodedString() {
        if (size() == 0) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            String encodedTask = taskList.get(i).toEncodedString();
            result.append(i + 1).append(".").append(encodedTask).append("\n");
        }
        if (result.length() > 0) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }

    @Override
    public String toString() {
        if (size() == 0) {
            return "No tasks found.";
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            result.append(i + 1).append(".").append(taskList.get(i)).append("\n");
        }
        if (result.length() > 0) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }
}
