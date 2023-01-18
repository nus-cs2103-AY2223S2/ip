import java.util.LinkedList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;

    TaskList() {
        this.tasks = new LinkedList<>();
    }

    public Task addTask(String input) throws CommandNotFoundException, EmptyCommandException {
        if (input != null && (input.equals("todo") || input.equals("deadline") || input.equals("event"))) {
            throw new EmptyCommandException("Empty argument", input);
        } else if (input.matches("deadline .* /by .*")) {
            // Handle deadline
            String[] arr = input.split(" /by ");
            String content = arr[0].substring(9, arr[0].length());

            if (content.length() == 0 || arr[1].length() == 0) {
                throw new EmptyCommandException("Empty argument", "deadline");
            }

            this.tasks.add(new Deadline(content, arr[1]));
        } else if (input.matches("event .* /from .* /to .*")) {
            // Handle event
            String[] arr = input.split(" /from ");
            String content = arr[0].substring(6, arr[0].length());
            String[] startEnd = arr[1].split(" /to ");

            if (content.length() == 0 || startEnd[0].length() == 0 || startEnd[1].length() == 0) {
                throw new EmptyCommandException("Empty argument", "event");
            }

            this.tasks.add(new Event(content, startEnd[0], startEnd[1]));
        } else if (input.matches("todo .*")) {
            // Handle todo
            if (input.length() == 5) {
                throw new EmptyCommandException("Empty argument", "todo");
            }

            this.tasks.add(new ToDo(input.substring(5, input.length())));
        } else {
            throw new CommandNotFoundException("Duke command is invalid.", input);
        }

        return tasks.get(tasks.size() - 1);
    }

    public void addDeadline(String task, String deadline) {
        this.tasks.add(new Deadline(task, deadline));
    }

    public void addEvent(String task, String start, String end) {
        this.tasks.add(new Event(task, start, end));
    }

    public void markTask(int i) {
        tasks.get(i).mark();
    }

    public void unmarkTask(int i) {
        tasks.get(i).unmark();
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public int numberOfTasks() {
        return tasks.size();
    }

    @Override
    public String toString() {
        String result = "";
        Integer curr = 1;

        for (Task t: tasks) {
            result += curr.toString() + ". " + t;
            if (curr != tasks.size()) result += '\n';

            curr++;
        }

        return result;
    }
}
