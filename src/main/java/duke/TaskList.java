package duke;

import java.util.ArrayList;
import java.util.stream.IntStream;

import duke.exception.InvalidTaskNumberException;
import duke.task.Task;

/**
 * A task list to store all recorded tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * A constructor for TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds task to list of tasks.
     *
     * @param task Task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes task from task list.
     *
     * @param taskNum Task number of task to be deleted
     * @return Task that is deleted.
     * @throws InvalidTaskNumberException
     */
    public Task deleteTask(int taskNum) throws InvalidTaskNumberException {
        if (taskNum < 1 || taskNum > getNumOfTasks()) {
            throw new InvalidTaskNumberException(taskNum);
        }
        Task task = tasks.remove(taskNum - 1);
        return task;
    }

    /**
     * Marks task as completed.
     *
     * @param taskNum Task number of task to be marked as completed.
     * @return Task that is marked as completed
     * @throws InvalidTaskNumberException
     */
    public Task markTask(int taskNum) throws InvalidTaskNumberException {
        if (taskNum < 1 || taskNum > getNumOfTasks()) {
            throw new InvalidTaskNumberException(taskNum);
        }
        Task task = tasks.get(taskNum - 1);
        task.completeTask();
        return task;
    }

    /**
     * Marks task as not completed yet.
     *
     * @param taskNum Task number of task to be marked as not completed yet
     * @return Task that is marked as not completed yet
     * @throws InvalidTaskNumberException
     */
    public Task unmarkTask(int taskNum) throws InvalidTaskNumberException {
        if (taskNum < 1 || taskNum > getNumOfTasks()) {
            throw new InvalidTaskNumberException(taskNum);
        }
        Task task = tasks.get(taskNum - 1);
        task.undoTask();
        return task;
    }

    /**
     * Finds list of tasks that contains keyword.
     *
     * @param keyword String to be searched
     * @return TaskList object containing all tasks that contains keyword
     */
    public TaskList findTasks(String keyword) {
        TaskList tasksFound = new TaskList();
        tasks.stream().filter(x -> x.getDescription().contains(keyword))
                .forEach(y -> tasksFound.addTask(y));
        return tasksFound;
    }

    /**
     * Returns String to be displayed after a task has been added to the task list.
     *
     * @param task Task that has been added.
     * @return String to be displayed after a task has been added to the task list.
     */
    public String addTaskText(Task task) {
        return String.format("Got it. I've added this task:\n   %s\nNow you have %s task%s in the list.\n",
                task.toString(), getNumOfTasks(), getNumOfTasks() == 1 ? "" : "s");
    }

    /**
     * Returns number of tasks in task list.
     *
     * @return Number of tasks in task list
     */
    public int getNumOfTasks() {
        return tasks.size();
    }

    String getSavedListOfTasks() {
        StringBuilder listString = new StringBuilder();
        tasks.stream().map(x -> String.format("%s\n", x.getSaveTaskString())).forEach(y -> listString.append(y));
        return listString.toString();
    }

    /**
     * Returns String representation of the numbered list of tasks.
     *
     * @return String representation of the numbered list of tasks
     */
    public String getListOfTasks() {
        StringBuilder listOfTasks = new StringBuilder();
        IntStream.range(0, tasks.size()).mapToObj(x -> String.format("%d. %s\n", x + 1, tasks.get(x)))
                .forEach(y -> listOfTasks.append(y));
        return listOfTasks.toString();
    }

    /**
     * Returns String representation of the numbered task list or an empty task list.
     *
     * @return String representation of task list.
     */
    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "There are no tasks in your list";
        }
        StringBuilder printedList = new StringBuilder("Here are the tasks in your list:\n");
        printedList.append(getListOfTasks());
        return printedList.toString();
    }
}
