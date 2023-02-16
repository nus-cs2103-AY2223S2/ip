package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.Parser;
import duke.tag.EmptyTag;
import duke.tag.Tag;

/**
 * An abstract class Task
 */
public abstract class Task {
    private static final String DATE_TIME_DISPLAY_FORMAT = "MMM dd yyyy HH:mm";

    protected String description;
    protected boolean isDone;
    protected Tag tag;

    Task(String description) {
        this(description, false, new EmptyTag());
    }

    Task(String description, Tag tag) {
        this(description, false, tag);
    }

    Task(String description, boolean isDone) {
        this(description, isDone, new EmptyTag());
    }

    Task(String description, boolean isDone, Tag tag) {
        this.description = description;
        this.isDone = isDone;
        this.tag = tag;
    }

    /**
     * Marks task as completed.
     */
    public void completeTask() {
        this.isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void undoTask() {
        this.isDone = false;
    }

    String displayDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_DISPLAY_FORMAT);
        return dateTime.format(formatter);
    }

    String formatSavedDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Parser.DATE_TIME_READ_FORMAT);
        return dateTime.format(formatter);
    }

    public String getDescription() {
        return description;
    }

    public Tag getTag() {
        return tag;
    }

    /**
     * Returns String representation of tag
     *
     * @return
     */
    public String getTagString() {
        return tag.equals(new EmptyTag()) ? "" : String.format(" | tag: %s", tag.getTagName());
    }

    /**
     * Returns String representation of task to be saved in file.
     *
     * @return String representation of task to be saved in file.
     */

    public abstract String getSaveTaskString();

    /**
     * Returns String representation of task to be printed.
     *
     * @return String representation of task to be printed.
     */
    @Override
    public String toString() {
        return String.format("%s | %s", isDone ? "X" : " ", description);
    }
}
