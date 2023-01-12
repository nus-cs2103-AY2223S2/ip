import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for the task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Adds a new todo task.
     * @param task  task to be added
     */
    public void createTodo(String task) {
        this.tasks.add(new Todo(task));
    }

    /**
     * Lists all tasks.
     * @return  A list of tasks.
     */
    public ArrayList<Task> indexTask() {
        return this.tasks;
    }

    /**
     * Shows one task.
     * @param index     index of the task item
     * @return  The task item.
     */
    public Task showTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Marks task as completed.
     * @param index     index of task
     */
    public void markTask(int index) {
        this.tasks.get(index).markCompleted();
    }

    /**
     * Marks task as uncompleted.
     * @param index     index of task
     */
    public void unmarkTask(int index) {
        this.tasks.get(index).unmarkCompleted();
    }
}
