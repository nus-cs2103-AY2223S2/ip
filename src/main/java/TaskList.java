import java.util.ArrayList;

public class TaskList {

    private int numOfTasks;
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> loadedTasks) {
        this.numOfTasks = loadedTasks.size();
        this.tasks = loadedTasks;
    }

    public TaskList() {
        this.numOfTasks = 0;
        this.tasks = new ArrayList<>();
    }

    public void markTask(String arg) throws DukeException {
        try {
            int num = Integer.parseInt(arg);
            if (num > numOfTasks || num <= 0) {
                throw new DukeException("Task number is out of bounds!");
            }
            tasks.get(num - 1).markDone();
            System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(num - 1));
        } catch (NumberFormatException ex) {
            throw new DukeException("Invalid number");
        }
    }

    public void unmarkTask(String arg) throws DukeException {
        try {
            int num = Integer.parseInt(arg);
            if (num > numOfTasks || num <= 0) {
                throw new DukeException("Task number is out of bounds!");
            }
            tasks.get(num - 1).markUndone();
            System.out.println("Ok, I've marked this task as not done yet:\n  " + tasks.get(num - 1));
        } catch (NumberFormatException ex) {
            throw new DukeException("Invalid number");
        }
    }

    public void addTodo(String[] args) {
        int len = args.length;
        StringBuilder taskName = new StringBuilder(args[1]);
        for (int i = 2; i < len; i++) {
            taskName.append(" ").append(args[i]);
        }
        Todo todo = new Todo(taskName.toString());
        tasks.add(todo);
        System.out.println("Got it. I've added this To Do:\n  " + tasks.get(numOfTasks));
        numOfTasks++;
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
    }

    public void addDeadline(String[] args, int by) {
        int len = args.length;
        StringBuilder taskName = new StringBuilder(args[1]);
        for (int i = 2; i < by; i++) {
            taskName.append(" ").append(args[i]);
        }
        StringBuilder byWhen = new StringBuilder(args[by + 1]);
        for (int i = by + 2; i < len; i++) {
            byWhen.append(" ").append(args[i]);
        }
        Deadline deadline = new Deadline(taskName.toString(), byWhen.toString());
        tasks.add(deadline);
        System.out.println("Got it. I've added this Deadline:\n  " + tasks.get(numOfTasks));
        numOfTasks++;
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
    }

    public void addEvent(String[] args, int from, int to) {
        int len = args.length;
        StringBuilder taskName = new StringBuilder(args[1]);
        for (int i = 2; i < from; i++) {
            taskName.append(" ").append(args[i]);
        }
        StringBuilder fromWhen = new StringBuilder(args[from + 1]);
        for (int i = from + 2; i < to; i++) {
            fromWhen.append(" ").append(args[i]);
        }
        StringBuilder toWhen = new StringBuilder(args[to + 1]);
        for (int i = to + 2; i < len; i++) {
            toWhen.append(" ").append(args[i]);
        }
        Event event = new Event(taskName.toString(), fromWhen.toString(), toWhen.toString());
        tasks.add(event);
        System.out.println("Got it. I've added this Event:\n  " + tasks.get(numOfTasks));
        numOfTasks++;
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
    }

    public void deleteTask(String arg) throws DukeException {
        try {
            int num = Integer.parseInt(arg);
            if (num > numOfTasks || num <= 0) {
                throw new DukeException("Task number is out of bounds!");
            }
            System.out.println("Noted. I've removed this task:\n  " + tasks.get(num - 1));
            tasks.remove(num - 1);
            numOfTasks--;
            System.out.println("Now you have " + (numOfTasks) + " tasks in the list.");
        } catch (NumberFormatException ex) {
            throw new DukeException("Invalid number");
        }
    }
}
