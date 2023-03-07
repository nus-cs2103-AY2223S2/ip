package sam.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import sam.parser.Parser;
import sam.parser.SamInvalidDateException;

/**
 * Represents a user task.
 */
public abstract class Task implements Cloneable {
    private static final String DATE_PATTERN = "MMM d yyyy";

    private String title;
    private boolean isDone;
    private String typeIcon;

    /**
     * Constructs a new Task.
     *
     * @param title The title of the task.
     * @param isDone Indicates whether the task is done.
     */
    public Task(String title, boolean isDone, String typeIcon) {
        this.title = title;
        this.isDone = isDone;
        this.typeIcon = typeIcon;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return "";
    }

    public boolean matchTitle(String subString) {
        return title.contains(subString);
    }

    /**
     * Updates details of the task.
     *
     * @param argsMap A Map of the task arguments.
     * @throws SamInvalidDateException If a date string is in the wrong format.
     */
    public void update(Map<String, String> argsMap) throws SamInvalidDateException {
        if (argsMap.containsKey("title")) {
            this.title = argsMap.get("title");
        }
    }

    /**
     * Returns a string character representing the type of the task.
     *
     * @return A character representing the type of the task.
     */
    public String getTypeIcon() {
        return typeIcon;
    };

    /**
     * Returns a string character representing the status of the task.
     *
     * @return A character if the task is done, or a blank space otherwise.
     */
    public String getStatusIcon() {
        return isDone ? "✔" : " ";
    }

    /**
     * Returns an integer representing the status of the task.
     *
     * @return A 1 if the task is done, or a 0 otherwise.
     */
    protected int getStatusNo() {
        return isDone ? 1 : 0;
    }

    /**
     * Formats the specified date to the display format.
     *
     * @param date The date to be formatted
     * @return A string representation of the date.
     */
    protected String formatDateDisplay(LocalDate date) {
        return date.format(
                DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    /**
     * Formats the specified date to the parser's format.
     *
     * @param date The date to be formatted.
     * @return A string representation of the date.
     */
    protected String formatDateSave(LocalDate date) {
        return date.format(
                DateTimeFormatter.ofPattern(Parser.DATE_PATTERN));
    }

    /**
     * Returns a string to be used when saving the task to a file.
     *
     * @return A string representation of the task.
     */
    public String toSaveFormat() {
        return String.format("%s | %d | %s",
                getTypeIcon(), getStatusNo(), getTitle());
    };

    @Override
    public String toString() {
        return String.format("[%s][%s] %s",
                getTypeIcon(), getStatusIcon(), getTitle());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
