package duke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.DukeException;

/**
 * The TaskTracker class tracks a collection of tasks
 * and provides functions to add, delete, list, mark and unmark tasks.
 */
public class TaskTracker {
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private final TaskStorage taskStorage;

    public TaskTracker() {
        tasks = new ArrayList<Task>();
        taskStorage = new TaskStorage();
    }

    /**
     * Returns the number of tasks tracked.
     *
     * @return number of tasks tracked
     */
    public int getNumTasks() {
        return tasks.size();
    }

    /**
     * Returns the task of a given index.
     *
     * @param index index of task to get
     * @return task of given index
     * @throws TaskNotFoundException
     */
    public Task getTask(int index) throws TaskNotFoundException {
        if (index < 0 || tasks.size() < index + 1) {
            throw new TaskNotFoundException("Task " + (index + 1) + " not found!");
        }

        return tasks.get(index);
    }

    /**
     * Creates and adds a new todo.
     *
     * @param taskDescription description of new todo
     * @return new todo
     */
    public Task addTodo(String taskDescription) {
        Task t = new Task(taskDescription);
        tasks.add(t);
        return t;
    }

    /**
     * Creates and adds a new deadline.
     *
     * @param taskDescription description of new deadline
     * @param endDate         end date of new deadline
     * @return new deadline
     */
    public Task addDeadline(String taskDescription, LocalDateTime endDate) {
        Task t = new Deadline(taskDescription, endDate);
        tasks.add(t);
        return t;
    }

    /**
     * Creates and adds a new event.
     *
     * @param taskDescription description of new event
     * @param startDate       start date of new event
     * @param endDate         end date of new event
     * @return new event
     */
    public Task addEvent(String taskDescription, LocalDateTime startDate, LocalDateTime endDate) {
        Task t = new Event(taskDescription, startDate, endDate);
        tasks.add(t);
        return t;
    }

    /**
     * Deletes a task of the given index.
     *
     * @param index index of task to delete
     * @return deleted task
     * @throws DukeException
     */
    public Task deleteTask(int index) throws DukeException {
        Task toDelete = getTask(index);
        tasks.remove(index);
        return toDelete;
    }

    /**
     * Marks a task of the given index.
     *
     * @param index index of task to mark
     * @return marked task
     * @throws DukeException
     */
    public Task markTask(int index) throws DukeException {
        return markUnmarkTask(index, true);
    }

    /**
     * Unmarks a task of the given index.
     *
     * @param index index of task to unmark
     * @return unmarked task
     * @throws DukeException
     */
    public Task unmarkTask(int index) throws TaskNotFoundException {
        return markUnmarkTask(index, false);
    }

    /**
     * Marks or unmarks a task of the given index.
     *
     * @param index    index of task to mark/unmark
     * @param isMarked marks the task if true, else unmarks the task
     * @return marked/unmarked task
     * @throws DukeException
     */
    public Task markUnmarkTask(int index, boolean isMarked) throws TaskNotFoundException {
        Task task = getTask(index);
        if (isMarked) {
            task.mark();
        } else {
            task.unmark();
        }
        return task;
    }

    /**
     * Returns a String that lists all tasks.
     *
     * @return list of tasks
     */
    public String listTasks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getNumTasks(); i++) {
            sb.append("\n\n").append(i + 1).append(". ").append(tasks.get(i));
        }
        return sb.toString();
    }

    /**
     * Returns a String that lists all tasks that match a given keyword.
     *
     * @param keyword keyword to match
     * @return tasks that match a given keyword
     */
    public String listTasksByKeyword(String keyword) {
        List<Task> matches = tasks.stream()
                .filter(t -> t.getTaskDescription().contains(keyword))
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matches.size(); i++) {
            sb.append("\n\n").append(i + 1).append(". ").append(matches.get(i));
        }
        return sb.toString();
    }

    /**
     * Saves all tasks to storage.
     *
     * @throws TaskSaveException
     */
    public void saveAllTasks() throws TaskSaveException {
        taskStorage.saveTasks(tasks);
    }

    /**
     * Loads tasks from storage.
     *
     * @throws TaskSaveException
     */
    public void loadTasks() throws TaskSaveException {
        if (tasks.size() > 0) {
            throw new TaskSaveException("Unable to load! Tasks already loaded!");
        }
        tasks = taskStorage.loadTasks();
    }
}
