import java.io.*;
import java.util.*;

public class Duke {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);
        Chungus chungus = new Chungus(writer);

        while (true) {
            writer.append("chungus> ").flush();
            String input = sc.nextLine();
            boolean shouldExit = chungus.handleMessage(input.split("\\s+"));
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

    public Chungus(Writer _out) throws IOException {
        out = _out;
        tasks = new ArrayList<>();

        info("Hello, I'm Chungus.");
        info("What can I do for you?");
    }

    public boolean handleMessage(String[] args) throws IOException {
        boolean shouldExit = handleMessageImpl(args);
        flush();
        return shouldExit;
    }

    private boolean handleMessageImpl(String[] args) throws IOException {
        if (args[0].equals("bye")) {
            info("Bye!");
            return true;
        }
        if (args[0].equals("list")) {
            info("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                info().append("  ")
                        .append(String.valueOf(i + 1))
                        .append('.')
                        .append(task.toString())
                        .append('\n');
            }
            return false;
        }
        if (args[0].equals("mark")) {
            int idx = Integer.parseInt(args[1]) - 1;
            if (idx >= tasks.size() || idx < 0) {
                error().append("Task ").append(args[1]).append(" does not exist!\n");
                return false;
            }

            Task task = tasks.get(idx);
            task.setDone();

            info("Okay, I've marked this task as completed:");
            info().append("  ").append(task.toString()).append('\n');

            return false;
        }
        if (args[0].equals("unmark")) {
            int idx = Integer.parseInt(args[1]) - 1;
            if (idx >= tasks.size() || idx < 0) {
                error().append("Task ").append(args[1]).append(" does not exist!\n");
                return false;
            }

            Task task = tasks.get(idx);
            task.setNotDone();

            info("Okay, I've marked this task as incomplete:");
            info().append("  ").append(task.toString()).append('\n');

            return false;
        }

        tasks.add(new Task(args[0]));
        info().append("added: ").append(args[0]).append('\n');

        return false;
    }

    private Writer info() throws IOException {
        out.append("\u001b[36m");
        return out;
    }

    private void info(String msg) throws IOException {
        out.append("\u001b[36m").append(msg).append('\n').append("\u001b[0m").flush();
    }

    private Writer error() throws IOException {
        out.append("\u001b[31m");
        return out;
    }

    private void error(String msg) throws IOException {
        out.append("\u001b[31m").append(msg).append('\n').append("\u001b[0m").flush();
    }

    private void flush() throws IOException {
        out.append("\u001b[0m").flush();
    }
}

class Task {
    private String desc;
    private boolean isDone;

    public Task(String _desc) {
        desc = _desc;
        isDone = false;
    }

    public String desc() {
        return desc;
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

    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + desc;
    }
}