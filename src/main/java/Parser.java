import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    private static DateTimeFormatter savedDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static Task parseSavedTask(String savedTask) {
        String[] taskArr = savedTask.split("\\|");
        Task newTask;
        if (taskArr[0].equals("T")) {
            newTask = new Todo(taskArr[2]);
        } else if (taskArr[0].equals("D")) {
            LocalDate by = LocalDate.parse(taskArr[3], savedDateFormatter);
            newTask = new Deadline(taskArr[2], by);
        } else if (taskArr[0].equals("E")) {
            LocalDate from = LocalDate.parse(taskArr[3], savedDateFormatter);
            LocalDate to = LocalDate.parse(taskArr[4], savedDateFormatter);
            newTask = new Event(taskArr[2], from, to);
        } else {
            throw new DukeException();
        }
        return newTask;
    }

    public static Command parse(String cmd) {
        switch (cmd) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        }

        String[] cmdArr = cmd.split(" ");
        switch(cmdArr[0]) {
        case "mark":
            return new TickCommand(Integer.parseInt(cmdArr[1]) - 1);
        case "unmark":
            return new UntickCommand(Integer.parseInt(cmdArr[1]) - 1);
        case "todo":
            String title = cmd.replaceAll("todo", "").trim();
            return new TodoCommand(title);
        case "deadline":
            return parseDeadline(cmd);
        case "event":
            return parseEvent(cmd);
        case "delete":
            return new DeleteCommand(Integer.parseInt(cmdArr[1]) - 1);
        }

        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    private static DeadlineCommand parseDeadline(String cmd) {
        try {
            String[] cmdArr = cmd.split("/by");
            String title = cmdArr[0].replaceAll("deadline", "").trim();
            LocalDate by = LocalDate.parse(cmdArr[1].substring(1), savedDateFormatter);
            return new DeadlineCommand(title, by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Sorry! That is the incorrect date format. Please use dd/MM/yyyy");
        }
    }

    private static EventCommand parseEvent(String cmd) {
        try {
            String[] cmdArr = cmd.split("/from");
            String title = cmdArr[0].replaceAll("event", "").trim();
            String[] times = cmdArr[1].split("/to");
            LocalDate from = LocalDate.parse(times[0].trim(), savedDateFormatter);
            LocalDate to = LocalDate.parse(times[1].trim(), savedDateFormatter);
            return new EventCommand(title, from, to);
        } catch (DateTimeParseException e) {
            throw new DukeException("Sorry! That is the incorrect date format. Please use dd/MM/yyyy");
        }
    }
}
