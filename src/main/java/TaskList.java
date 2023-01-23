import java.time.LocalDateTime;
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

            try {
                this.tasks.add(new Deadline(content, arr[1]));
            } catch (InvalidDateFormatException e) {
                System.out.println(e.getMessage());
            }
        } else if (input.matches("event .* /from .* /to .*")) {
            // Handle event
            String[] arr = input.split(" /from ");
            String content = arr[0].substring(6, arr[0].length());
            String[] startEnd = arr[1].split(" /to ");

            if (content.length() == 0 || startEnd[0].length() == 0 || startEnd[1].length() == 0) {
                throw new EmptyCommandException("Empty argument", "event");
            }

            try {
                this.tasks.add(new Event(content, startEnd[0], startEnd[1]));
            } catch (InvalidDateFormatException e) {
                System.out.println(e.getMessage());
            }
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

    public Task deleteTask(int i) {
        Task res = this.getTask(i);
        tasks.remove(i);
        return res;
    }

    public void addDeadline(String task, String deadline) {
        try {
            this.tasks.add(new Deadline(task, deadline));
        } catch (InvalidDateFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addEvent(String task, String start, String end) {
        try {
            this.tasks.add(new Event(task, start, end));
        } catch (InvalidDateFormatException e) {
            System.out.println(e.getMessage());
        }
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

    public void printTasksOnDate(String deadline) throws InvalidDateFormatException {
        LocalDateTime dt = DateTimeHelper.parse(deadline);
        int counter = 1;

        for (Task t: tasks) {
            if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                if (d.occursOn(dt)) {
                    System.out.println(Integer.valueOf(counter) + ". " + d);
                    counter++;
                }
            } else if (t instanceof Event) {
                Event e = (Event) t;
                if (e.occursOn(dt)) {
                    System.out.println(Integer.valueOf(counter) + ". " + e);
                    counter++;
                }
            }
        }
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
