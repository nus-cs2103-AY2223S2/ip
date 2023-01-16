import java.lang.StringBuilder;

public class TaskManager {
    private static int size = 100;
    private int counter = 0;
    private Task[] tasks = new Task[TaskManager.size];
    private static final String starting = "    ____________________________________________________________\n";
    private static final String ending = "    ____________________________________________________________\n";
    private static final String spacing = "     ";

    public TaskManager() {}

    public String addTask(String task) {
        //adds task and return add string for adding task
        Task t = new Task(task);
        this.tasks[this.counter] = t;
        this.counter++;
        return TaskManager.starting + TaskManager.spacing + "added: " + task + "\n" + TaskManager.ending;
    }

    public String mark(int index) {
        //marks the task and return the string
        this.tasks[index - 1].setStatus(true);
        String s = TaskManager.starting + TaskManager.spacing + "Nice! I've marked this task as done:\n";
        s += TaskManager.spacing + "  " + this.tasks[index-1].toString() + TaskManager.ending;
        return s;
    }

    public String unmark(int index) {
        //unmarks the task and returns the string
        this.tasks[index - 1].setStatus(false);
        String s = TaskManager.starting + TaskManager.spacing + "OK, I've marked this task as not done yet:\n";
        s += TaskManager.spacing + "  " + this.tasks[index-1].toString() + TaskManager.ending;
        return s;
    }

    public String getAllTaskStr() {
        //returns string for all the tasks
        StringBuilder s = new StringBuilder();
        s.append(TaskManager.starting);
        s.append(TaskManager.spacing).append("Here are the tasks in your list\n");
        for (int i = 0; i < this.counter; i++) {
            s.append(String.format("%s%d. %s", TaskManager.spacing, i + 1, this.tasks[i].toString()));
        }
        s.append(TaskManager.ending);
        return s.toString();
    }
}
