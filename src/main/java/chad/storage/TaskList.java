package chad.storage;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

import chad.task.Deadline;
import chad.task.Event;
import chad.task.Task;
import chad.task.Todo;

/**
 * List to keep track of user's tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private File file;

    /**
     * Constructor to create new Task List
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor to instantiate new task list with data from local file prepended.
     * @param file
     */
    public TaskList(File file) {
        this.tasks = new ArrayList<>();
        this.file = file;
    }

    /**
     * Return the task array list.
     * @return the task list.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Add new duke.task to current duke.task list
     * @param task new task to be added into the duke.task list
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Add new duke.task to current duke.task list
     * @param task new duke.task to be added into the duke.task list
     * @return the Added Todo
     */
    public Todo addTodo(String task) {
        Todo todo = new Todo(task);
        this.tasks.add(todo);
        return todo;
    }

    /**
     * Add new deadline to current task list
     * @param task new duke.task to be added
     * @param dueDate the deadline of the task
     * @return the added Deadline
     */
    public Deadline addDeadline(String task, LocalDateTime dueDate) {
        Deadline taskDeadline = new Deadline(task, dueDate);
        this.tasks.add(taskDeadline);
        return taskDeadline;
    }

    /**
     * Add new event to current task list
     * @param task event task to be added
     * @param from start time of the event
     * @param to end time of the event
     * @return the added Event
     */
    public Event addEvent(String task, LocalDateTime from, LocalDateTime to) {
        Event event = new Event(task, from, to);
        this.tasks.add(event);
        return event;
    }

    /**
     * Get the specific task at given index, idx
     * @param idx Given index of the task in the list
     * @return Task at index, idx
     */
    public Task getTask(Integer idx) {
        return tasks.get(idx);
    }

    /**
     * Delete task at specific index, idx
     * @param idx index of the task in the array to be deleted
     */
    public void deleteTask(Integer idx) {
        this.tasks.remove(idx.intValue());
    }

    /**
     * Getter method to get the task list
     * @return task list
     */
    public String getTaskList() {
        StringBuilder taskList = new StringBuilder();

        for (int i = 1; i <= this.tasks.size(); i++) {
            if (i == this.tasks.size()) {
                taskList.append(i + ": " + this.tasks.get(i - 1));
                break;
            }
            taskList.append(i + ": " + this.tasks.get(i - 1) + "\n");
        }

        return taskList.toString();
    }

    /**
     * Function to find tasks using keyword.
     * @param keyword keyword the user wish to find the task
     * @return list of tasks that contain the keyword.
     */
    public ArrayList<Task> find(String keyword) {
        return tasks.stream()
                .filter(task -> task.toString().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
    }
    /**
     * Get the number of tasks in the list
     * @return number of tasks
     */
    public int numOfTask() {
        return this.tasks.size();
    }
}
