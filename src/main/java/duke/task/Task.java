package duke.task;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task with the description of the task and
 * the status of the task - whether it is done.
 */
public abstract class Task {

    protected final String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets a Task object from the file representation of the task.
     *
     * @param task The String representation of the task in the text file.
     * @return The Task object corresponding to the String representation in the text file.
     */
    public static Task getTaskFromString(String task) {
        String[] taskParts = task.split("~");
        String taskType = taskParts[0];
        String marked = taskParts[1];
        String description = taskParts[2];

        Task answer = null;

        if (taskType.equals("T")) {
            answer = new ToDo(description);

        } else if (taskType.equals("D")) {
            answer = new Deadline(description, LocalDateTime.parse(taskParts[3]));

        } else if (taskType.equals("E")) {
            answer = new Event(description,
                    LocalDateTime.parse(taskParts[3]),
                    LocalDateTime.parse(taskParts[4]));

        }

        if (answer != null && marked.equals("X")) {
            answer.mark();

        }

        return answer;

    }

    /**
     * Gets the representation of the LocalDateTime object formatted like Jan 21 2023 06:00 PM.
     *
     * @param dateTime The LocalDateTime object to be formatted.
     * @return The formatted String representation of the LocalDateTime object.
     */
    public static String getDateTimeString(LocalDateTime dateTime) {
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

    @Override
    public String toString() {
        String mark = this.isDone ? "X" : " ";
        return "[" + mark + "] " + this.description;
    }

    /**
     * Gets the String representation of the Task to be stored in the text file.
     *
     * @return The String representation of the Task to be stored in the text file.
     */
    public abstract String getFileRepresentation();

}
