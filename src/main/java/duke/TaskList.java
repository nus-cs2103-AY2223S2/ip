package duke;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.List;
public class TaskList {
    protected List<Task> tasks;
    protected int num_tasks;

    /**
     * Creates a new TaskList.
     */
    TaskList() {
        tasks = new ArrayList<>();
        num_tasks = 0;
    }

    /**
     * Returns the total number of tasks.
     *
     * @return the number of tasks in your TaskList.
     */
    public Integer getNumTasks() {
        return num_tasks;
    }

    /**
     * Fetches the specific task and mark it as done.
     *
     * @param task_index the index of the specific task in the list.
     * @return the marked task.
     */
    public Task markTask(int task_index) throws DukeException {
        if (task_index > num_tasks || task_index < 1) {
            throw new DukeException("There is no such task available\n");
        }

        Task ref = this.tasks.get(task_index - 1);
        ref.mark();
        return ref;
    }


    /**
     * Fetches the specific task and unmark it.
     *
     * @param task_index the index of the specific task in the list.
     * @return the unmarked task.
     */
    public Task unmarkTask(int task_index) throws DukeException {

        if (task_index > num_tasks || task_index < 1) {
            throw new DukeException("There is no such task available\n");
        }
        Task ref = this.tasks.get(task_index - 1);
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
        num_tasks++;
    }

    /**
     * Deletes a task to the TaskList.
     *
     * @param index the index of the specific task in the list.
     * @return deleted task.
     */
    public Task deleteTask(int index) throws DukeException {
        if (index > num_tasks || index < 1) {
            throw new DukeException("There is no such task available\n");
        }

        Task removed = tasks.remove(index - 1);
        num_tasks--;
        return removed;
    }

    /**
     * Prints all the tasks in the TaskList.
     */
    public void printTasks() throws DukeException {
        if (num_tasks == 0) {
            throw new DukeException("You currently have no tasks mate!");
        }
        for (int i = 0; i < num_tasks; i++) {
            Task ref = tasks.get(i);
            System.out.println((i + 1) + "." + ref.toString());
        }
    }
}
