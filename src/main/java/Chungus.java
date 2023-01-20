import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chungus {
    private Writer out;
    private ArrayList<Task> tasks;

    private static Pattern deadlinePattern = Pattern.compile("^deadline\\s+(.+)\\s+/by\\s+(.+)$");
    private static Pattern eventPattern = Pattern.compile("^event\\s+(.+)\\s+/from\\s+(.+)\\s+/to\\s+(.+)$");

    private static DateTimeFormatter dateTimeFmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);
        Chungus chungus = new Chungus(writer);

        while (true) {
            writer.append("chungus> ").flush();
            String input = sc.nextLine();
            boolean shouldExit = chungus.handleInput(input.trim());
            if (shouldExit)
                break;
            writer.append('\n').flush();
        }

        sc.close();
    }

    public Chungus(Writer _out) {
        out = _out;
        tasks = new ArrayList<>();

        info("Hello, I'm Chungus.");
        info("What can I do for you?\n");
    }

    public boolean handleInput(String msg) {
        try {
            boolean shouldExit = handleInputImpl(msg);
            flush();
            return shouldExit;
        } catch (TaskNotExistException e) {
            error("Could not find the requested task. You currently have exactly %d %s",
                    tasks.size(), tasks.size() == 1 ? "task" : "tasks");
            error("Reason: %s", e.getMessage());
            return false;
        } catch (ChungusException e) {
            error("Could not handle command \"%s\".", msg);
            error("Reason: %s", e.getMessage());
            return false;
        }
    }

    private boolean handleInputImpl(String msg) {
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
                    throw new ChungusException("Description of todo cannot be empty.");
                }

                Todo task = new Todo(pair[1]);
                tasks.add(task);
                reportNewTask(task);

                return false;
            }
            case "deadline": {
                Matcher matcher = deadlinePattern.matcher(msg);
                if (!matcher.find()) {
                    throw new ChungusException(
                            "Bad format for creating deadline task. Must be of the form deadline <task> /by <datetime>.");
                }

                String desc = matcher.group(1);
                LocalDateTime deadline = parseLocalDateTime(matcher.group(2));

                Deadline task = new Deadline(desc, deadline);
                tasks.add(task);
                reportNewTask(task);

                return false;
            }
            case "event": {
                Matcher matcher = eventPattern.matcher(msg);
                if (!matcher.find()) {
                    throw new ChungusException(
                            "Bad format for creating event. Must be of the form event <name> /from <datetime> /to <datetime>.");
                }

                String desc = matcher.group(1);
                LocalDateTime from = parseLocalDateTime(matcher.group(2));
                LocalDateTime to = parseLocalDateTime(matcher.group(3));

                Event task = new Event(desc, from, to);
                tasks.add(task);
                reportNewTask(task);

                return false;
            }
            case "mark": {
                int idx = getTaskNumberArg(args[1]) - 1;

                Task task = tasks.get(idx);
                task.setDone();

                info("Okay, I've marked this task as completed:");
                info("  %s", task);

                return false;
            }
            case "unmark": {
                int idx = getTaskNumberArg(args[1]) - 1;

                Task task = tasks.get(idx);
                task.setNotDone();

                info("Okay, I've marked this task as incomplete:");
                info("  %s", task);

                return false;
            }
            case "delete": {
                int idx = getTaskNumberArg(args[1]) - 1;

                Task task = tasks.remove(idx);
                reportDeletedTask(task);

                return false;
            }
            default: {
                throw new ChungusException("Unknown command.");
            }
        }
    }

    private void reportNewTask(Task task) {
        info("Okay, I've added this task:");
        info("  %s", task);
        info("Now you have %d %s.", tasks.size(), tasks.size() == 1 ? "task" : "tasks");
    }

    private void reportDeletedTask(Task task) {
        info("Okay, I've deleted this task:");
        info("  %s", task);
        info("Now you have %d %s.", tasks.size(), tasks.size() == 1 ? "task" : "tasks");
    }

    private int getTaskNumberArg(String s) {
        String[] xs = s.split("\\s+");
        int num = Integer.parseInt(xs[xs.length - 1]);
        if (num > tasks.size() || num <= 0)
            throw new TaskNotExistException(num);
        return num;
    }

    private static LocalDateTime parseLocalDateTime(String s) {
        try {
            return LocalDateTime.parse(s, dateTimeFmt);
        } catch (DateTimeParseException e) {
            throw new ChungusException(String.format("Bad datetime format \"%s\": expected dd/MM/yyyy HHmm", s), e);
        }
    }

    private void info(String msg, Object... args) {
        try {
            out.append("\u001b[36m")
                    .append(String.format(msg, args))
                    .append('\n')
                    .append("\u001b[0m")
                    .flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void error(String msg, Object... args) {
        try {
            out.append("\u001b[31m")
                    .append(String.format(msg, args))
                    .append('\n')
                    .append("\u001b[0m")
                    .flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void flush() {
        try {
            out.append("\u001b[0m").flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class ChungusException extends RuntimeException {
    public ChungusException(String msg) {
        super(msg);
    }

    public ChungusException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

class TaskNotExistException extends ChungusException {
    public TaskNotExistException(int idx) {
        super(String.format("Task %d does not exist.", idx));
    }
}
