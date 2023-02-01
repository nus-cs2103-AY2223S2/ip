package duke.Tasks;

import duke.Command.AddDeadlineCommand;
import duke.Command.AddTodoCommand;
import duke.Command.AddEventCommand;
import duke.Command.Command;
import duke.Command.Commands;
import duke.Exceptions.CommandException;
import duke.Exceptions.DescriptionException;
import duke.DateTimeFormat;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Task {
    protected String description;

    protected boolean isDone;

    public Task(String description) throws DescriptionException {
        this.description = description;
        this.isDone = false;
        if (this.isEmpty()) {
            throw new DescriptionException();
        }
    }

    public boolean isEmpty() {
        return this.description == null || this.description.trim().isEmpty();
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " | " + this.description;
    }

    public static Command taskToCommand(String strTask) throws CommandException {
        if (strTask.startsWith(Commands.deadline.label)) {
            Pattern pattern = Pattern.compile("deadline (.+) /by (.+)");
            Matcher matcher = pattern.matcher(strTask);
            if (matcher.find()) {
                return new AddDeadlineCommand(matcher.group(1), matcher.group(2));
            }
        } else if (strTask.startsWith(Commands.todo.label)) {
            Pattern pattern = Pattern.compile("todo (.+)");
            Matcher matcher = pattern.matcher(strTask);
            if (matcher.find()) {
                return new AddTodoCommand(matcher.group(1));
            }
        } else if (strTask.startsWith(Commands.event.label)) {
            Pattern pattern = Pattern.compile("event (.+) /from (.+) /to (.+)");
            Matcher matcher = pattern.matcher(strTask);
            if (matcher.find()) {
                return new AddEventCommand(matcher.group(1), matcher.group(2), matcher.group(3));
            }
        }
        throw new CommandException();
    }

    public static Task strToTask(String strTask) throws CommandException {
        Task result;
        String[] separatedStr = strTask.split(" \\| ");
        if (strTask.startsWith("T")) {
            result = new ToDo(separatedStr[2]);
        } else if (strTask.startsWith("D")) {
            result = new Deadline(separatedStr[2], separatedStr[3]);
        } else if (strTask.startsWith("E")) {
            String[] separatedBy = separatedStr[3].split("-");
            result = new Event(separatedStr[2], separatedBy[0], separatedBy[1]);
        } else {
            throw new CommandException();
        }
        try {
            if (separatedStr[1].equals("X")) {
                result.markDone();
            }
            return result;
        } catch (Exception e) {
            throw new CommandException();
        }
    }

    protected static LocalDateTime getLocalDateTime(String strDate) {
        for (DateTimeFormat format : DateTimeFormat.values()) {
            try {
                return LocalDateTime.parse(strDate, format.formatter);
            } catch (DateTimeException dateTimeException) {
                // no time given
                try {
                    return LocalDate.parse(strDate, format.formatter).atStartOfDay();
                } catch (DateTimeException ignored) {}
            }
        }
        System.out.println("invalid!");
        return null;
    }
}
