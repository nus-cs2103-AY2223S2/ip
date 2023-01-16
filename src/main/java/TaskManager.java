import java.lang.StringBuilder;

public class TaskManager {
    private static int size = 100;
    private int counter = 0;
    private Task[] tasks = new Task[TaskManager.size];
    private static final String starting = "    ____________________________________________________________\n";
    private static final String ending = "    ____________________________________________________________\n";
    private static final String spacing = "     ";

    public TaskManager() {}

    public String wrap(String s) {
        //wrap string with the correct indentation and lines when returning add task string
        StringBuilder sb = new StringBuilder();
        sb.append(TaskManager.starting).append(TaskManager.spacing).append("Got it. I've added this task:\n  ");
        sb.append(TaskManager.spacing).append(s).append(TaskManager.spacing);
        sb.append(String.format("Now you have %d tasks in the list.\n", this.counter)).append(TaskManager.ending);
        return sb.toString();
    }
    public String addDeadline(String task, String deadline) {
        //adds deadline and return the add string for deadlines
        Deadline dl = new Deadline(task, deadline);
        this.tasks[this.counter] = dl;
        this.counter++;
        return this.wrap(dl.toString());
    }

    public String addEvent(String task, String duration) {
        //adds deadline and return the add string for deadlines
        Event ev = new Event(task, duration);
        this.tasks[this.counter] = ev;
        this.counter++;
        return this.wrap(ev.toString());
    }

    public String addTodo(String task) {
        //adds deadline and return the add string for deadlines
        Todo td = new Todo(task);
        this.tasks[this.counter] = td;
        this.counter++;
        return this.wrap(td.toString());
    }

//    public String addTask(String task) {
//        //adds task and return add string for adding task
//        Task t = new Task(task);
//        this.tasks[this.counter] = t;
//        this.counter++;
//        return TaskManager.starting + TaskManager.spacing + "added: " + task + "\n" + TaskManager.ending;
//    }

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
        s.append(TaskManager.spacing).append("Here are the tasks in your list:\n");
        for (int i = 0; i < this.counter; i++) {
            s.append(String.format("%s%d.%s", TaskManager.spacing, i + 1, this.tasks[i].toString()));
        }
        s.append(TaskManager.ending);
        return s.toString();
    }
}
