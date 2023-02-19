package duke.task;

import duke.Duke;
import duke.exception.DukeException;
import duke.exception.DukeInvalidDataFileException;
import duke.parser.Parser;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task with the description of the task and
 * the status of the task - whether it is done.
 */
public abstract class Task {

    protected final String description;
    protected boolean isDone;
    protected Priority priority;

    protected Task(String description) {
        assert description != null;

        this.description = description;
        this.isDone = false;
        this.priority = Priority.MEDIUM;
    }

    protected Task(String description, Priority priority) {
        assert description != null;
        assert priority != null;

        this.description = description;
        this.isDone = false;
        this.priority = priority;
    }

    /**
     * Gets a Task object from the file representation of the task.
     *
     * @param task The String representation of the task in the text file.
     * @return The Task object corresponding to the String representation in the text file.
     */
    public static Task getTaskFromString(String task) throws DukeException {

        if (!task.contains("~")) {
            throw new DukeInvalidDataFileException(
                    "The data file has been corrupted. All data has been erased :/");
        }

        try {
            String[] taskParts = task.split("~");
            String taskType = taskParts[0];
            Priority priority = Parser.parsePriority(taskParts[1]);
            String marked = taskParts[2];
            String description = taskParts[3];

            Task answer = null;

            if (taskType.equals("T")) {
                answer = new ToDo(description, priority);

            } else if (taskType.equals("D")) {
                answer = new Deadline(description,
                        LocalDateTime.parse(taskParts[4]),
                        priority);

            } else {
                answer = new Event(description,
                        LocalDateTime.parse(taskParts[4]),
                        LocalDateTime.parse(taskParts[5]),
                        priority);

            }

            if (answer != null && marked.equals("X")) {
                answer.mark();

            }

            return answer;

        } catch (Exception e) {
            throw new DukeInvalidDataFileException(
                    "The data file has been corrupted. All data has been erased :/");

        }

    }

    /**
     * Gets the representation of the LocalDateTime object formatted like Jan 21 2023 06:00 PM.
     *
     * @param dateTime The LocalDateTime object to be formatted.
     * @return The formatted String representation of the LocalDateTime object.
     */
    public static String getDateTimeString(LocalDateTime dateTime) {
        assert dateTime != null;

        DateTimeFormatter formatToPrint = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return dateTime.format(formatToPrint);

    }

    public String getDescription() {
        return this.description;
    }

    public void mark() {
        this.isDone = true;
    }
    
    public void unmark() {
        this.isDone = false;
    }

    public void setPriority(Priority p) {
        this.priority = p;
    }

    @Override
    public String toString() {
        String mark = this.isDone ? "X" : " ";
        return "{" + this.priority.toString().toLowerCase()
                + "}[" + mark + "] "
                + this.description;
    }

    /**
     * Gets the String representation of the Task to be stored in the text file.
     *
     * @return The String representation of the Task to be stored in the text file.
     */
    public abstract String getFileRepresentation();

}
