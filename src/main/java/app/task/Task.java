package app.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import app.chatbot.Storage;

/**
 * Represents a Task, which can contain additional attributes, such as the
 * starting and ending datetime.
 * A Task is not done by default.
 * A description for the Task is compulsory.
 * <br>
 * Supported DateTime formats are included in this class for Tasks that
 * have DateTime attributes.
 * <br>
 * Attributes of a Task cannot be accessed and modified directly from other packages
 * (use the TaskList to modify a Task).
 */
public abstract class Task implements Comparable<Task> {
    protected static final String DESC_MISSING_ERROR = "Plz provide d description.";
    protected static final String INVALID_DATETIME_FORMAT_ERROR =
            "Try reformatting your date/time to the supported formats:\n"
            + "yyyy-MM-dd HHmm or yyyy/MM/dd HHmm\n"
            + "Make sure that the date/time is valid!";
    protected static final List<DateTimeFormatter> SUPPORTED_DATE_TIME_INPUT = new ArrayList<>();
    protected static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("EEE, dd MMM yy hh:mma");
    protected String description;
    protected boolean isDone;
    protected String symbol;
    protected TaskTypes.Type taskType;
    protected final Map<String, String> fieldToValueMap = new HashMap<>();
    static {
        SUPPORTED_DATE_TIME_INPUT.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        SUPPORTED_DATE_TIME_INPUT.add(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
        SUPPORTED_DATE_TIME_INPUT.add(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * Abstract constructor for Task. Description is compulsory.
     * @param description
     * @throws InvalidInputException
     */
    public Task(String description) throws InvalidInputException {
        if (isArgEmpty(description)) {
            throw new InvalidInputException(DESC_MISSING_ERROR);
        }
        this.description = description;
        this.isDone = false;
        this.fieldToValueMap.put("description", description);
    }

    /**
     * Checks if a String argument is null or blank.
     * @param arg
     * @return
     */
    protected boolean isArgEmpty(String arg) {
        return Objects.isNull(arg) || arg.equals("");
    }

    /**
     * Attempts to parse and return a String datetime into LocalDateTime,
     * by trying across supported formats.
     * @param input
     * @return
     * @throws InvalidDateTimeException if datetime fails to parse against available formats
     */
    protected LocalDateTime parseDate(String input) throws InvalidDateTimeException {
        LocalDateTime date = null;
        for (DateTimeFormatter f : SUPPORTED_DATE_TIME_INPUT) {
            try {
                date = LocalDateTime.parse(input, f);
                break;
            } catch (DateTimeParseException ignored) {
            }
        }
        if (Objects.isNull(date)) {
            throw new InvalidDateTimeException(INVALID_DATETIME_FORMAT_ERROR);
        } else {
            return date;
        }
    }

    /**
     * Returns the status icon for display. Marks a task as done with "X", blank otherwise.
     * @return
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getDesc() {
        return this.description;
    }

    public TaskTypes.Type getType() {
        return TaskTypes.symbolToTask.getValue().get(this.symbol);
    }

    protected Map<String, String> getMapping() {
        return this.fieldToValueMap;
    }

    protected boolean containsField(String s) {
        return this.fieldToValueMap.containsKey(s);
    }
    /**
     * Marks task as done
     */
    protected void markAsDone() {
        isDone = true;
    }

    /**
     * Marks task as undone
     */
    protected void unmarkDone() {
        isDone = false;
    }

    /**
     * Formats the task as data to be stored in text file.
     * @return data format recognisable by the app .
     */
    public abstract String asDataFormat();


    /**
     * Formats the task as data to be stored in text file.
     * @param fields variable number of fields (attributes) of the task.
     * @return data format recognisable by the app.
     */
    protected String asDataFormat(String... fields) {
        String base = String.join(Storage.SEPARATOR, this.symbol, this.isDone() ? "1" : "0", this.description);
        for (String s : fields) {
            if (!s.isBlank()) {
                base = String.join(Storage.SEPARATOR, base, s);
            }
        }
        return base;
    }

    /**
     * @return user-readable format with information regarding the task.
     */
    @Override
    public String toString() {
        return "[" + this.symbol + "][" + this.getStatusIcon() + "] " + this.description;
    }

    @Override
    public abstract int compareTo(Task other);
}
