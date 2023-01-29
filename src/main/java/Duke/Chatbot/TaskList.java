package Duke.Chatbot;

import Duke.Exceptions.UnimplementedTaskTypeException;
import Duke.Tasks.DeadlineTask;
import Duke.Tasks.EventTask;
import Duke.Tasks.Task;
import Duke.Tasks.TodoTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * TaskList keeps tracks of all operations related to
 * the current tasks, as well as manipulation of tasks
 */
public class TaskList {

    private final List<Task> tasks;

    /**
     * Loads the tasklist from save file representation
     *
     * @param tasks List of tasks in save file representation
     */
    public TaskList(List<String> tasks) {
        this();
        for (String info : tasks) {
            loadData(info);
        }

    }

    /**
     * If no save file is found, create an empty TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }


    private void loadData(String data) {
        if (data.length() == 0)
            return;

        Task todo;
        switch (data.charAt(0)) {
            case 'T':
                todo = TodoTask.loadData(data);
                break;
            case 'D':
                todo = DeadlineTask.loadData(data);
                break;
            case 'E':
                todo = EventTask.loadData(data);
                break;
            default:
                throw new IllegalArgumentException();
        }

        tasks.add(todo);
    }

    /**
     * Converts this TaskList's contents to Save File Representation
     *
     * @return Save File Representation of this TaskList's tasks
     */
    public String toSaveData() {
        String output = "";
        for (Task nextTask : tasks) {
            output += nextTask.toSaveData();
            output += "\n";
        }
        return output;
    }

    /**
     * Returns the User Representation of a specific task at an index
     *
     * @param index index of task to query
     * @return User Representation of task queried
     */
    public String getTaskInfo(int index) {
        return tasks.get(index).toString();
    }

    /**
     * Checks if the index specified corresponds to a Task in TaskList
     *
     * @param index index of task to query
     * @return True if task index corresponds to a task, false otherwise
     */
    public boolean isValidIndex(int index) {
        return tasks.size() > index;
    }

    /**
     * Removes and returns a task at a specific index
     *
     * @param toRemove Index of task to be removed
     * @return The removed task
     */
    public Task removeTask(int toRemove) {

        Task removedTask = tasks.get(toRemove);
        tasks.remove(toRemove);
        return removedTask;
    }

    /**
     * Returns the number of tasks currently in this TaskList
     *
     * @return The number of tasks currently in this TaskList
     */
    public int numTasks() {
        return tasks.size();
    }

    /**
     * Returns the completion state of a task specified by the input index
     *
     * @param index the index of the Task to query
     * @return Returns true if task corresponding to this index is completed, false otherwise
     */
    public boolean getTaskState(int index) {
        return tasks.get(index).getCompletionStatus();
    }

    /**
     * Toggles the state of a task specified by the input index
     *
     * @param index the index of the Task to toggle state
     */
    public void toggleState(int index) {
        tasks.get(index).toggleState();
    }

    /**
     * Adds a task to the TaskList
     *
     * @param toAdd the Task to add
     */
    public void addTask(Task toAdd) {
        tasks.add(toAdd);
    }

    /**
     * Changes this TaskList into User Representation form
     *
     * @return This TaskList in User Representation form
     */
    @Override
    public String toString() {
        String result = "";
        int i = 0;
        for (Task entry : tasks) {
            i += 1;
            result += ((i == 1 ? "" : "\n") + i + "." + entry);

        }
        return result;
    }


    /**
     * Converts a task in Save File Representation form into its corresponding
     * Todo, Deadline or Event.
     *
     * @param type      Enum specifying the type of Task to generate
     * @param arguments Array of strings representing the name and optionally the DateTime in string
     * @return Returns the Task generated from this Save File Representation form.
     * @throws DateTimeParseException         Throws this Error if DateTime String given cannot be parsed
     * @throws UnimplementedTaskTypeException Throws this Error if Tasktype given does not match an implemented Task
     */
    public Task convertToTask(Tasktype type, String[] arguments)
            throws DateTimeParseException, UnimplementedTaskTypeException {
        switch (type) {
            case TODO:
                return new TodoTask(arguments[0]);
            case EVENT:
                return new EventTask(arguments[0], LocalDateTime.parse(arguments[1]),
                        LocalDateTime.parse(arguments[2]));
            case DEADLINE:
                return new DeadlineTask(arguments[0], LocalDateTime.parse(arguments[1]));
            default:
                throw new UnimplementedTaskTypeException();
        }

    }

    /**
     * Removes all elements from this TaskList
     */
    public void removeAllTasks() {
        tasks.clear();
    }

    /**
     * Specifies the Tasktypes currently supported by TaskList
     */
    public enum Tasktype {
        TODO,
        EVENT,
        DEADLINE
    }
}
