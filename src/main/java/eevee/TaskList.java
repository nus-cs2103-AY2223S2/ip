package eevee;

import java.io.IOException;
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
     * @param storage storage location to store new task added
     * @throws IOException if something goes wrong while adding task to task list in storage
     */
    public void addTask(Task task, Storage storage) throws IOException {
        this.tasks.add(task);
        assert !tasks.isEmpty() : "Something went wrong when adding first task.";
        storage.updateTaskList(this);
    }

    /**
     * Checks if the given task can be added into the task list. If there are clashes in timings with a task currently
     * in the task list, the task is not added. If there are no clashes, the task can be added into the task list.
     * @param task task to be added into the task list
     * @param storage storage location to store new task added
     * @return true if the task can be added as there are no clashes in timing and false if the task cannot be added
     * due to the presence of a clash in timing
     * @throws IOException if something goes wrong while adding task to task list in storage
     */
    public boolean canAddTask(Task task, Storage storage) throws IOException {
        if (haveClashWithCurrentTasks(task)) {
            return false;
        }
        this.addTask(task, storage);
        return true;
    }

    /**
     * Checks for the presence of clashes with the tasks currently in the task list and returns true if clashes are
     * found
     * @param currTask task to compare if clashes are present between the tasks in the task list and the current task
     * @return true if there are clashes in timings found and false if no clashes are found
     */
    public boolean haveClashWithCurrentTasks(Task currTask) {
        for (Task task : tasks) {
            boolean isEventAndClashWithCurrTask = task.getTaskType().equals("E")
                    && task.taskClashWithCurrTask(currTask);
            if (isEventAndClashWithCurrTask) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds given task to the task list
     * @param task task to be added into the task list
     */
    public void addTaskToSearchList(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes and returns the task deleted from the task list.
     * @param indexOfTask index of task to be deleted
     * @param storage storage location to update the task list of the task deleted
     * @return the task deleted
     * @throws IOException if something goes wrong when updating the deletion to task list
     */
    public Task deleteTask(int indexOfTask, Storage storage) throws IOException{
        Task taskToDelete = this.tasks.remove(indexOfTask);
        storage.updateTaskList(this);
        return taskToDelete;
    }

    /**
     * Marks the task given by its index as done.
     * @param indexOfTask index of the task to mark as done
     * @param storage storage location to update the task list after task is mark done
     * @return the task after marking it as done
     * @throws IOException if something goes wrong when updating the marking of the task to the task list
     */
    public Task markTaskAsDone(int indexOfTask, Storage storage) throws IOException {
        Task toMarkDone = this.tasks.get(indexOfTask);
        toMarkDone.markDone();
        storage.updateTaskList(this);
        return toMarkDone;
    }

    /**
     * Marks the task given by its index as undone.
     * @param indexOfTask index of the task to mark as undone
     * @param storage storage location to update the task list after task is mark undone
     * @return the task after marking it as undone
     * @throws IOException if something goes wrong when updating the unmarking of the task to the task list
     */
    public Task markTaskAsUndone(int indexOfTask, Storage storage) throws IOException {
        Task toMarkUndone = this.tasks.get(indexOfTask);
        toMarkUndone.markUndone();
        storage.updateTaskList(this);
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
            for (int i = 0; i < taskName.length; i++) {
                if (taskName[i].equals(searchWord) && !taskFinder.contains(task)) {
                    taskFinder.addTaskToSearchList(task);
                }
            }
        }
        return taskFinder;
    }
}
