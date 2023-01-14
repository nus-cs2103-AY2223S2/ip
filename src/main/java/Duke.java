import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);
        Chungus chungus = new Chungus(writer);

        while (true) {
            writer.append("chungus> ").flush();
            String input = sc.nextLine();
            boolean shouldExit = chungus.handleInput(input);
            if (shouldExit)
                break;
            writer.append('\n').flush();
        }

        sc.close();
    }
}


class Chungus {
    private Writer out;
    private ArrayList<Task> tasks;

    private static Pattern deadlinePattern = Pattern.compile("^deadline\\s+(.+)\\s+/by\\s+(.+)$");
    private static Pattern eventPattern =
            Pattern.compile("^event\\s+(.+)\\s+/from\\s+(.+)\\s+/to\\s+(.+)$");

    public Chungus(Writer _out) {
        out = _out;
        tasks = new ArrayList<>();

        try {
            info("Hello, I'm Chungus.");
            info("What can I do for you?\n");
        } catch (IOException e) {
            throw new RuntimeException("could not write to designated output", e);
        }
    }

    public boolean handleInput(String msg) {
        try {
            boolean shouldExit = handleInputImpl(msg);
            flush();
            return shouldExit;
        } catch (IOException e) {
            throw new RuntimeException("failed while handling input", e);
        }
    }

    private boolean handleInputImpl(String msg) throws IOException {
        String[] args = msg.split("\\s+");
        switch (args[0]) {
            case "bye": {
                info("Bye!");
                return true;
            }
            case "list": {
                info("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    info("  %d.%s", i + 1, task);
                }
                return false;
            }
            case "todo": {
                String[] pair = msg.split("\\s+", 2);
                if (pair.length < 2) {
                    error("Invalid task description.");
                    return false;
                }

                Todo task = new Todo(pair[1]);
                tasks.add(task);
                reportNewTask(task);

                return false;
            }
            case "deadline": {
                Matcher matcher = deadlinePattern.matcher(msg);
                if (!matcher.find()) {
                    error("Input format seems off.");
                    return false;
                }

                String desc = matcher.group(1);
                String deadline = matcher.group(2);

                Deadline task = new Deadline(desc, deadline);
                tasks.add(task);
                reportNewTask(task);

                return false;
            }
            case "event": {
                Matcher matcher = eventPattern.matcher(msg);
                if (!matcher.find()) {
                    error("Input format seems off.");
                    return false;
                }

                String desc = matcher.group(1);
                String from = matcher.group(2);
                String to = matcher.group(3);

                Event task = new Event(desc, from, to);
                tasks.add(task);
                reportNewTask(task);

                return false;
            }
            case "mark": {
                int idx = Integer.parseInt(args[1]) - 1;
                if (idx >= tasks.size() || idx < 0) {
                    error("Task %s does not exist!", args[1]);
                    return false;
                }

                Task task = tasks.get(idx);
                task.setDone();

                info("Okay, I've marked this task as completed:");
                info("  %s", task);

                return false;
            }
            case "unmark": {
                int idx = Integer.parseInt(args[1]) - 1;
                if (idx >= tasks.size() || idx < 0) {
                    error("Task %s does not exist!", args[1]);
                    return false;
                }

                Task task = tasks.get(idx);
                task.setNotDone();

                info("Okay, I've marked this task as incomplete:");
                info("  %s", task);

                return false;
            }
            default: {
                error("Big big chungus.");
                return false;
            }
        }
    }

    private void reportNewTask(Task task) throws IOException {
        info("Okay, I've added this task:");
        info("  %s", task);
        info("Now you have %d %s.", tasks.size(), tasks.size() == 1 ? "task" : "tasks");
    }

    private void info(String msg, Object... args) throws IOException {
        out.append("\u001b[36m").append(String.format(msg, args)).append('\n').append("\u001b[0m")
                .flush();
    }

    private void error(String msg, Object... args) throws IOException {
        out.append("\u001b[31m").append(String.format(msg, args)).append('\n').append("\u001b[0m")
                .flush();
    }

    private void flush() throws IOException {
        out.append("\u001b[0m").flush();
    }
}


abstract class Task {
    private String desc;
    private boolean isDone;

    public Task(String _desc) {
        desc = _desc;
        isDone = false;
    }

    public String desc() {
        return (isDone() ? "[X] " : "[ ] ") + desc;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

    public void setNotDone() {
        isDone = false;
    }

    public abstract String toString();
}


class Todo extends Task {
    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + desc();
    }
}


class Deadline extends Task {
    String deadline;

    public Deadline(String desc, String _deadline) {
        super(desc);
        deadline = _deadline;
    }

    @Override
    public String toString() {
        return "[D]" + desc() + String.format(" (by: %s)", deadline);
    }
}


class Event extends Task {
    String from, to;

    public Event(String desc, String _from, String _to) {
        super(desc);
        from = _from;
        to = _to;
    }

    @Override
    public String toString() {
        return "[E]" + desc() + String.format(" (from: %s to: %s)", from, to);
    }
}
