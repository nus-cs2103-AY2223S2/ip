package duke;

import java.util.ArrayList;
import java.util.List;

import duke.tasks.Task;

public class TaskList {
    protected List<Task> tasks;
    protected int numTasks;

    /**
     * Creates a new TaskList.
     */
    TaskList() {
        tasks = new ArrayList<>();
        numTasks = 0;
    }

    /**
     * Returns the total number of tasks.
     *
     * @return the number of tasks in your TaskList.
     */
    public Integer getNumTasks() {
        return numTasks;
    }

    /**
     * Fetches the specific task and mark it as done.
     *
     * @param taskIndex the index of the specific task in the list.
     * @return the marked task.
     */
    public Task markTask(int taskIndex) throws DukeException {
        if (taskIndex > numTasks || taskIndex < 1) {
            throw new DukeException("There is no such task available\n");
        }

        Task ref = this.tasks.get(taskIndex - 1);
        ref.mark();
        return ref;
    }


    /**
     * Fetches the specific task and unmark it.
     *
     * @param taskIndex the index of the specific task in the list.
     * @return the unmarked task.
     */
    public Task unmarkTask(int taskIndex) throws DukeException {

        if (taskIndex > numTasks || taskIndex < 1) {
            throw new DukeException("There is no such task available\n");
        }
        Task ref = this.tasks.get(taskIndex - 1);
        ref.unmark();
        return ref;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task a new task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
        numTasks++;
    }

    /**
     * Deletes a task to the TaskList.
     *
     * @param index the index of the specific task in the list.
     * @return deleted task.
     */
    public Task deleteTask(int index) throws DukeException {
        if (index > numTasks || index < 1) {
            throw new DukeException("There is no such task available\n");
        }

        Task removed = tasks.remove(index - 1);
        numTasks--;
        return removed;
    }

    /**
     * Prints all the tasks in the TaskList.
     */
    public String printTasks() throws DukeException {
        if (numTasks == 0) {
            throw new DukeException("You currently have no tasks mate!");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numTasks; i++) {
            Task ref = tasks.get(i);
            String taskToPrint = String.format("%d.%s\n", (i + 1) , ref.toString());
            sb.append(taskToPrint);
        }

        return sb.toString();
    }

    public ArrayList<Task> getTasksByKeyWord(String keyWord) throws DukeException {
        ArrayList<Task> allFoundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.containsKeyWord(keyWord)) {
                allFoundTasks.add(task);
            }
        }
        if (allFoundTasks.size() == 0) {
            throw new DukeException(String.format("Unable to find any Tasks that contain this Keyword: %s\n", keyWord));
        } else {
            return allFoundTasks;
        }
    }
}
