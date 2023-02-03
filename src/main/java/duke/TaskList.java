package duke;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the task at the given index from the task list.
     * @param index index of task in the task list
     * @return the task at the index given
     */
    public Task getTaskByIndex(int index) {
        return this.tasks.get(index);
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public boolean contains(Task task) {
        return this.tasks.contains(task);
    }

    /**
     * Adds the given task into the task list.
     * @param task task to be added into the task list
     */
    public void addTask(Task task){
        this.tasks.add(task);
    }

    /**
     * Deletes and returns the task deleted from the task list.
     * @param indexOfTask index of task to be deleted
     * @return the task deleted
     */
    public Task deleteTask(int indexOfTask) {
        return this.tasks.remove(indexOfTask - 1);
    }

    /**
     * Marks the task given by its index as done.
     * @param indexOfTask index of the task to mark as done
     * @return the task after marking it as done
     */
    public Task markTaskAsDone(int indexOfTask) {
        Task toMarkDone = this.tasks.get(indexOfTask);
        toMarkDone.markDone();
        return toMarkDone;
    }

    /**
     * Marks the task given by its index as undone.
     * @param indexOfTask index of the task to mark as undone
     * @return the task after marking it as undone
     */
    public Task markTaskAsUndone(int indexOfTask) {
        Task toMarkUndone = this.tasks.get(indexOfTask);
        toMarkUndone.markUndone();
        return toMarkUndone;
    }

    /**
     * Retrieves the size of the task list.
     * @return size of task list
     */
    public int getTaskListSize() {
        return tasks.size();
    }

    /**
     * Searches the task list for the presence of <code>searchWord</code>
     * and returns a <code>TaskList</code> of tasks that contains
     * <code>searchWord</code>.
     * @param searchWord word to search task list for
     * @return a <code>TaskList</code> of matching tasks
     */
    public TaskList makeTaskFinder(String searchWord) {
        TaskList taskFinder = new TaskList();
        for (Task task : tasks) {
            String[] taskName = task.getName().split(" ");
            for (String s : taskName) {
                if (s.equals(searchWord) && !taskFinder.contains(task)) {
                    taskFinder.addTask(task);
                }
            }
        }
        return taskFinder;
    }
}
