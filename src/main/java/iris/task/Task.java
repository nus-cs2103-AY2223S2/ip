package iris.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import iris.exception.IrisException;
import iris.exception.MissingFieldException;
import iris.exception.NoTaskException;

/**
 * Abstract class that wraps the tasks - Deadline, Event and ToDo classes
 */
public abstract class Task {
    private boolean isDone = false;
    private final String name;

    public Task(String name) {
        this.name = name;
    }

    /**
     * Marks the task done
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the task not done
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns the name of the task
     * @return the name of the task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the status of the task
     * @return the status of the task - done or not
     */
    public boolean isDone() {
        return this.isDone;
    }

    private String status() {
        if (this.isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    /**
     * parses the description of task in storage format and returns Task objects
     * @param str description of task stores in storage format
     * @return Task object
     * @throws IrisException when there are fields missing in the task description
     */
    public static Task parse(String str) throws IrisException {
        Task task;
        String type;
        String mark;
        String[] strArray = str.split("\\|");
        try {
            type = strArray[0];
            mark = strArray[1];
            switch (type) {
            case "T":
                task = new Todo(strArray[2]);
                break;
            case "D":
                task = new Deadline(strArray[2], strArray[3]);
                break;
            case "E":
                task = new Event(strArray[2], strArray[3], strArray[4]);
                break;
            default:
                throw new NoTaskException();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingFieldException("Field missing in tasks file.");
        }
        if (mark.equals("X")) {
            task.mark();
        }
        return task;
    }

    /**
     * returns description of task in a format easy to store and parse
     * @return description of task in storage format
     */
    public String storageFormat() {
        return String.join("|", this.status(), this.name);
    }

    protected String dateString(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + this.status() + "] " + this.name;
    }
}
