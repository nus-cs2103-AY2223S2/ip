import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int getSize() {
        return this.taskList.size();
    }

    public Task getTask(int index) throws DukeException {
        // handle errors out of range
        if (index < 0 || index >= this.getSize()) {
            int display = index + 1;
            throw new DukeException("Task " + display + " does not exist.");
        }
        return taskList.get(index);
    }

    // mark tasks
    public void markTask(int index) throws DukeException {
        // handle errors out of range
        if (index < 0 || index >= this.getSize()) {
            int display = index + 1;
            throw new DukeException("Task " + display + " does not exist.");
        }
        this.getTask(index).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.getTask(index));
    }

    // unmark tasks
    public void unmarkTask(int index) throws DukeException {
        // handle errors out of range
        if (index < 0 || index >= this.getSize()) {
            int display = index + 1;
            throw new DukeException("Task " + display + " does not exist.");
        }
        this.getTask(index).markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.getTask(index));
    }

    // Add tasks
    public void addTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        taskList.add(task);
        System.out.println("Now you have " + this.getSize() + " tasks in the list.");
    }

    public void removeTask(int index) throws DukeException {
        // handle errors out of range
        if (index < 0 || index >= this.getSize()) {
            int display = index + 1;
            throw new DukeException("Task " + display + " does not exist.");
        }
        System.out.println("Noted. I've removed this task:");
        System.out.println(this.getTask(index));
        taskList.remove(index);
        System.out.println("Now you have " + this.getSize() + " tasks in the list.");
    }
}
