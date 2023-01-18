/**
 * TaskMaster is the managing class for all Tasks.
 */
import java.util.LinkedList;
public class TaskMaster {
    private final LinkedList<Task> ll;
    private final DukeIO dio;

    protected TaskMaster(DukeIO dio) {
        ll = new LinkedList<>();
        this.dio = dio;
    }

    protected void list() {
        if (ll.size() > 0) {
            int number = 1;
            for (Task task: ll) {
                dio.println(String.format("%d.%s", number++, task));
            }
        } else {
            dio.println("Oh my, the list is empty!");
        }
    }

    private void add(Task task) {
        dio.println("Got it. I've added this task:");
        ll.add(task);
        dio.println(task);
        dio.println(String.format("Now you have %d tasks in the list.", ll.size()));

    }

    private Task getTaskAtIndex(int index) throws DukeException.Invalid.Index {
        Task task;
        try {
            task = ll.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException.Invalid.Index(index);
        }
        return task;
    }

    protected void markComplete(int index, boolean status) throws DukeException.Invalid.Index {
        Task task = this.getTaskAtIndex(index);
        if (status) {
            task.setComplete(Task.STATUS.COMPLETE);
            dio.println("Nice! I've marked this task as done:");
        } else {
            task.setComplete(Task.STATUS.INCOMPLETE);
            dio.println("OK, I've marked this task as not done yet:");
        }
        dio.println(task);

    }

    protected void delete(int index) throws DukeException.Invalid.Index {
//        Task task = this.getTaskAtIndex(index);
        dio.println("Noted. I've removed this task:");
        dio.println(ll.remove(index));

    }

    protected void addToDo(String taskName) {
        this.add(new ToDo(taskName));
    }

    protected void addDeadLine(String taskName, String by) {
        this.add(new Deadline(taskName, by));
    }
    protected void addEvent(String taskName, String from, String to) {
        this.add(new Event(taskName, from, to));
    }
}
