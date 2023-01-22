
import java.util.ArrayList;

/**
 * A storage of Tasks.
 */
class TaskStorage {
    /**
     * An ArrayList of Tasks.
     */
    ArrayList<Task> storage = new ArrayList<>(100);

    /**
     * Lists all the tasks stored.
     */
    public void listTasks() {
        String topDivider = "~~~~~~~~~~~~~~~~o~~~~~~~~~~~~~~~~\n" + "Duke's Response: \n"
                    + "Here are the tasks in your list: \n";
        String botDivider = "~~~~~~~~~~~~~~~~o~~~~~~~~~~~~~~~~";
        System.out.println(topDivider);

        for (int i = 0; i < this.storage.size(); i++) {
            String output = this.storage.get(i).provideDetails();
            System.out.println((i + 1) + "." + output);
        }

        System.out.println(botDivider);
    }


    /**
     * Stores a new task in storage.
     * @param task The task to be stored.
     */
    public void storeTasks(Task task) {
        this.storage.add(task);
    }

    /**
     * Updates the status of a Task.
     * @param number The number representing the task to be updated.
     */
    public void updateTask(int number) {
        this.storage.get(number - 1).updateTask();
    }

    public void deleteTask(int number) {
        this.storage.remove(number - 1);
    }

    public int getStorageCount() {
        return this.storage.size();
    }

    public Task getTask(int number) {
        return this.storage.get(number - 1);
    }
}

