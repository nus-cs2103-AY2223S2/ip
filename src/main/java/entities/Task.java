package entities;

import enums.TaskType;
import exceptions.DukeException;
import utils.CustomValidator;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract public class Task {
    private static final Pattern DATE_FORMAT =
            Pattern.compile("^(?<year>\\d{4})-(?<month>0[0-9]|1[0-2])-(?<day>0[0-9]|1[0-9]|2[0-9]|3[0-1])$");
    public static final Pattern DEADLINE =
            Pattern.compile("^(deadline) (?<description>.+) /by (?<by>.+)$", Pattern.CASE_INSENSITIVE);
    public  static final Pattern EVENT =
            Pattern.compile("^(event) (?<description>.+) /from (?<from>.+) /to (?<to>.+)$", Pattern.CASE_INSENSITIVE);
    public static final Pattern TODO = Pattern.compile("^(todo) (?<description>.+)$", Pattern.CASE_INSENSITIVE);

    private static final String INVALID_DATE_MESSAGE = "Date is not valid! Please try again.";

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public abstract SerializableTask serialize();
    public abstract boolean activeOn(LocalDate date);

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    private static boolean validateDate(String date) {
        return CustomValidator.validate(date, (String val) -> DATE_FORMAT.matcher(val).matches());
    }

    private static Task createTask(TaskType type, String description, Matcher matcher) throws DukeException {
        switch (type) {
            case TODO:
                return new Todo(description);
            case EVENT:
                String from = matcher.group("from");
                String to = matcher.group("to");
                if (!validateDate(from) || !validateDate(to) ) {
                    throw type.getErr();
                }
                LocalDate fromDate = LocalDate.parse(from);
                LocalDate toDate = LocalDate.parse(to);
                if (!fromDate.isBefore(toDate) && fromDate.isAfter(LocalDate.now())) {
                    throw new DukeException(INVALID_DATE_MESSAGE);
                }
                return new Event(description, fromDate, toDate);
            case DEADLINE:
                String by = matcher.group("by");
                if (!validateDate(by)) {
                    throw type.getErr();
                }
                LocalDate byDate = LocalDate.parse(by);
                if (! byDate.isAfter(LocalDate.now())) {
                    throw new DukeException(INVALID_DATE_MESSAGE);
                }
                return new Deadline(description, byDate);
            default:
                throw type.getErr();
        }
    }

    public static void processTask(Matcher matcher, TaskType type) throws DukeException {
        if (!matcher.find()) {
            throw type.getErr();
        }
        String description = matcher.group("description");
        Task task = createTask(type, description, matcher);
        TaskList.addTask(task);
    }

    public void markTask() {
        isDone = true;
        System.out.println("Nice! I've marked this task as done:\n [X] "  + description);
    }

    public void unmarkTask() {
        isDone = false;
        System.out.println("OK, I've marked this task as not done yet:\n [ ] " + description);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
