import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chungus {
    private Ui ui;
    private ArrayList<Task> tasks;
    private Storage db;

    private static final Pattern deadlinePattern = Pattern.compile("^deadline\\s+(.+)\\s+/by\\s+(.+)$");
    private static final Pattern eventPattern = Pattern.compile("^event\\s+(.+)\\s+/from\\s+(.+)\\s+/to\\s+(.+)$");

    private static final String defaultDbPath = System.getProperty("user.dir") + "/chungus.db";

    private static final DateTimeFormatter dateTimeFmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public static void main(String[] args) {
        new Chungus(System.in, System.out, defaultDbPath).spin();
    }

    public Chungus(InputStream in, OutputStream out, String dbPath) {
        ui = new Ui(in, out);
        tasks = new ArrayList<>();

        File dbFile = new File(dbPath);
        if (dbFile.exists() && !dbFile.isFile()) {
            // this means that the path represents a directory
            throw new RuntimeException(String.format("%s is not a file", dbPath));
        }
        db = new Storage(dbFile);
        try {
            if (dbFile.createNewFile()) {
                ui.info("Created a database file at %s\n", dbPath);
            } else {
                tasks = new ArrayList<>(db.read());
                ui.info("Read %s task(s) from %s\n", tasks.size(), dbPath);
            }
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to create/read db file %s", dbPath), e);
        }

        ui.info("Hello, I'm Chungus.");
        ui.info("What can I do for you?\n");
    }

    public void spin() {
        while (true) {
            ui.print("chungus> ");
            boolean shouldExit = handleInput(ui.nextLine());
            if (shouldExit) {
                break;
            }
            ui.print("\n");
        }
    }

    public boolean handleInput(String msg) {
        try {
            boolean shouldExit = handleInputImpl(msg);
            // overwrite the database after every command
            db.write(tasks);
            return shouldExit;
        } catch (TaskNotFoundException e) {
            ui.error("Could not find the requested task. You currently have exactly %d %s", tasks.size(),
                    tasks.size() == 1 ? "task" : "tasks");
            ui.error("Reason: %s", e.getMessage());
            return false;
        } catch (ChungusException e) {
            ui.error("Could not handle command \"%s\".", msg);
            ui.error("Reason: %s", e.getMessage());
            return false;
        }
    }

    private boolean handleInputImpl(String msg) {
        String[] args = msg.split("\\s+");
        switch (args[0]) {
        case "bye": {
            ui.info("Bye!");
            return true;
        }
        case "list": {
            ui.info("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                ui.info("  %d.%s", i + 1, task);
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

            ui.info("Okay, I've marked this task as completed:");
            ui.info("  %s", task);

            return false;
        }
        case "unmark": {
            int idx = getTaskNumberArg(args[1]) - 1;

            Task task = tasks.get(idx);
            task.setNotDone();

            ui.info("Okay, I've marked this task as incomplete:");
            ui.info("  %s", task);

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
        ui.info("Okay, I've added this task:");
        ui.info("  %s", task);
        ui.info("Now you have %d %s.", tasks.size(), tasks.size() == 1 ? "task" : "tasks");
    }

    private void reportDeletedTask(Task task) {
        ui.info("Okay, I've deleted this task:");
        ui.info("  %s", task);
        ui.info("Now you have %d %s.", tasks.size(), tasks.size() == 1 ? "task" : "tasks");
    }

    private int getTaskNumberArg(String s) {
        String[] xs = s.split("\\s+");
        int num = Integer.parseInt(xs[xs.length - 1]);
        if (num > tasks.size() || num <= 0)
            throw new TaskNotFoundException(num);
        return num;
    }

    private static LocalDateTime parseLocalDateTime(String s) {
        try {
            return LocalDateTime.parse(s, dateTimeFmt);
        } catch (DateTimeParseException e) {
            throw new ChungusException(String.format("Bad datetime format \"%s\": expected dd/MM/yyyy HHmm", s), e);
        }
    }
}
