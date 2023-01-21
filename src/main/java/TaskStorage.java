/**
 * A storage of Tasks.
 */
class TaskStorage {
    /**
     * An array of Tasks.
     */
    Task[] storage = new Task[100];
    /**
     * Keeps track of number of Tasks stored.
     */
    int storageCount = 0;

    /**
     * Lists all the tasks stored.
     */
    public void listTasks() {
        String topDivider = "~~~~~~~~~~~~~~~~o~~~~~~~~~~~~~~~~\n" + "Duke's Response: \n"
                    + "Here are the tasks in your list: \n";
        String botDivider = "~~~~~~~~~~~~~~~~o~~~~~~~~~~~~~~~~";
        System.out.println(topDivider);

        for (int i = 0; i < this.storageCount; i++) {
            String output = this.storage[i].provideDetails();
            System.out.println((i + 1) + "." + output);
        }

        System.out.println(botDivider);
    }


    /**
     * Stores a new task in storage.
     * @param task The task to be stored.
     */
    public void storeTasks(Task task) {
        this.storage[this.storageCount] = task;
        this.storageCount++;
    }

    /**
     * Updates the status of a Task.
     * @param number The number representing the task to be updated.
     */
    public void updateTask(int number) {
        this.storage[number -  1].updateTask();
    }

    public int getStorageCount() {
        return this.storageCount;
    }
}

