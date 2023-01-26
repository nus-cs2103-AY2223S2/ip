package duke.task;

import duke.DukeException;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskTracker {
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private final TaskStorage taskStorage;

    public TaskTracker() {
        tasks = new ArrayList<Task>();
        taskStorage = new TaskStorage();
    }

    public int getNumTasks() {
        return tasks.size();
    }

    public Task getTask(int index) throws TaskNotFoundException {
        if (index < 0 || tasks.size() < index + 1) {
            throw new TaskNotFoundException("duke.task.Task " + (index + 1) + " not found!");
        }

        return tasks.get(index);
    }

    public Task addTodo(String taskDescription) {
        Task t = new Task(taskDescription);
        tasks.add(t);
        return t;
    }

    public Task addDeadline(String taskDescription, LocalDateTime endDate) {
        Task t = new Deadline(taskDescription, endDate);
        tasks.add(t);
        return t;
    }

    public Task addEvent(String taskDescription, LocalDateTime startDate, LocalDateTime endDate) {
        Task t = new Event(taskDescription, startDate, endDate);
        tasks.add(t);
        return t;
    }

    public Task deleteTask(int index) throws DukeException {
        Task toDelete = getTask(index);
        tasks.remove(index);
        return toDelete;
    }

    public Task markTask(int index) throws DukeException {
        return markUnmarkTask(index, true);
    }

    public Task unmarkTask(int index) throws TaskNotFoundException {
        return markUnmarkTask(index, false);
    }

    public Task markUnmarkTask(int index, boolean isMarked) throws TaskNotFoundException {
        Task task = getTask(index);
        if (isMarked) {
            task.mark();
        } else {
            task.unmark();
        }
        return task;
    }

    public String listTasks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getNumTasks(); i++) {
            sb.append("\n\t").append(i + 1).append(". ").append(tasks.get(i));
        }
        return sb.toString();
    }

    public void saveAllTasks() throws TaskSaveException {
        taskStorage.saveTasks(tasks);
    }

    public void loadTasks() throws TaskSaveException {
        if (tasks.size() > 0) {
            throw new TaskSaveException("Unable to load! Tasks already loaded!");
        }
        tasks = taskStorage.loadTasks();
    }
}
