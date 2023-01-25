import java.util.List;

public class TaskList {
    static final String BORDER = "----------------------------------------";

    private List<Task> tasks;
    protected TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    // List out all tasks and their rank.
    protected void list() {
        if (tasks.size() == 0) {
            System.out.println("No tasks left :)\n" + BORDER);
            return;
        }
        int rank = 1;
        for (Task t : tasks) {
            System.out.println(rank + "." + t.fullMessage());
            rank++;
        }
        System.out.println(BORDER);
    }

    // Mark task at index to be done
    protected void markDone(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("OOPS!!! Invalid index");
        }

        Task curr = tasks.get(index);
        curr.markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" +
                curr.fullMessage() + "\n" + BORDER);
    }

    // Mark task at index to be undone
    protected void markUndone(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("OOPS!!! Invalid index.");
        }

        Task curr = tasks.get(index);
        curr.markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:\n" +
                curr.fullMessage() + "\n" + BORDER);
    }

    // Delete task at index.
    protected void delete(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("OOPS!!! Invalid index.");
        }
        String message = tasks.remove(index).fullMessage();
        System.out.println("Noted. I've removed this task:\n" + message + "\n" +
                "Now you have " + tasks.size() + " tasks in the list.\n" + BORDER);
    }

    // Create and add task given message and code. 0 - toDos, 1 - Deadlines, 2 - Event
    protected void addTask(String message, int code) throws DukeException {
        Task t;
        if (code == 0) {
            t = new ToDos(false, new String[] {message});
        } else if (code == 1) {
            t = new Deadlines(false, message.split("/"));
        } else if (code == 2) {
            t = new Events(false, message.split("/"));
        } else {
            // Unreachable as of now.
            return;
        }
        tasks.add(t);
        System.out.println("Got it. I've added this task:\n" + t.fullMessage());
        System.out.println("Now you have " + tasks.size() + " tasks in this list\n" + BORDER);
    }



}
