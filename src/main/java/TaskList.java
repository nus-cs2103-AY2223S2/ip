import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int num) throws DukeException{
        int numTasks = this.tasks.size();
        if (numTasks == 0) {
            throw new DukeException("Can't delete anything since no tasks in list! "
                    + "Add some tasks first!");
        }
        if (num < 1) {
            throw new DukeException("Tasks start from 1, please try again!");
        } else if (num > numTasks) {
            throw new DukeException(String.format("You only have %d task(s), "
                    + "please try again with a number less or equal to %d!",
                    numTasks, numTasks));
        } else {
            Task selectedTask = this.tasks.get(num - 1);
            this.tasks.remove(num - 1);
            return selectedTask;
        }
    }

    public Task changeMarkStatus(int num) throws DukeException {
        int numTasks = this.tasks.size();
        if (numTasks == 0) {
            throw new DukeException("Can't mark anything since no tasks in list! "
                    + "Add some tasks first!");
        }
        if (num < 1) {
            throw new DukeException("Tasks start from 1, please try again!");
        } else if (num > numTasks) {
            throw new DukeException(String.format("You only have %d task(s), "
                            + "please try again with a number less or equal to %d!",
                    numTasks, numTasks));
        } else {
            Task selectedTask = this.tasks.get(num - 1).mark();
            this.tasks.set(num - 1, selectedTask);
            return selectedTask;
        }
    }

    @Override
    public String toString() {
        return this.tasks.toString();
    }
}
