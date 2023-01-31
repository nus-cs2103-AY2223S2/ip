package duke;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.List;
public class TaskList {
    protected List<Task> tasks;
    protected int num_tasks;

    TaskList() {
        tasks = new ArrayList<>();
        num_tasks = 0;
    }

    public Task markTask(int task_index) throws DukeException {
        if (task_index > num_tasks || task_index < 1) {
            throw new DukeException("There is no such task available\n");
        }

        Task ref = this.tasks.get(task_index - 1);
        ref.mark();
        return ref;
    }

    public Task unmarkTask(int task_index) throws DukeException {

        if (task_index > num_tasks || task_index < 1) {
            throw new DukeException("There is no such task available\n");
        }
        Task ref = this.tasks.get(task_index - 1);
        ref.unmark();
        return ref;
    }

    public void add(Task task) {
        tasks.add(task);
        num_tasks++;
    }

    public Task deleteTask(int index) throws DukeException {
        if (index > num_tasks || index < 1) {
            throw new DukeException("There is no such task available\n");
        }

        Task removed = tasks.remove(index - 1);
        num_tasks--;
        return removed;
    }

    public void printTasks() throws DukeException {
        if (num_tasks == 0) {
            throw new DukeException("You currently have no tasks mate!");
        }
        for (int i = 0; i < num_tasks; i++) {
            Task ref = tasks.get(i);
            System.out.println((i + 1) + "." + ref.getStatusIcon() + " " + ref.getDescription());
        }
    }
}
