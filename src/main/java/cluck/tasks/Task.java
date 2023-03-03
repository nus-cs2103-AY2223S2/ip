package cluck.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import cluck.exceptions.CorruptedDataException;
import cluck.messages.Messages;

/**
 * The type Task is an abstract class containing .
 */
public abstract class Task {
    /**
     * The constant formatter used for dates.
     */
    protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(
            "dd MMM yy HHmm");
    /**
     * The Description.
     */
    protected String description; // name of the task
    /**
     * The Is marked.
     */
    protected Boolean isMarked; // indicates whether task is marked
    /**
     * Instantiates a new Task. All tasks are un-marked initially.
     *
     * @param description the description of the task
     */
    protected Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    /**
     * Instantiates a new Task, but task can be manually set to be either marked or un-marked.
     *
     * @param isMarked    whether task is marked
     * @param description the description of the task
     */
    protected Task(boolean isMarked, String description) {
        this.description = description;
        this.isMarked = isMarked;
    }

    /**
     * Interprets string as local date time.
     *
     * @param input the input
     * @return date and time as a LocalDateTime class
     * @throws DateTimeParseException if input cannot be parsed using the given format
     */
    protected static LocalDateTime interpretLocalDateTime(String input) throws DateTimeParseException {
        return LocalDateTime.parse(input, FORMATTER);
    }

    /**
     * Set task to be marked.
     */
    public void mark() {
        this.isMarked = true;
    }

    /**
     * Set task to be un-marked.
     */
    public void unmark() {
        this.isMarked = false;
    }

    /**
     * Abstract method to be overridden by child-classes to save tasks into .txt file.
     *
     * @return task in the save format as a String
     */
    public String makeSaveFormat() {
        return String.format("|%1$s|%2$s", this.isMarked ? "1" : "0", this.description);
    }

    /**
     * Builds task from saved task format.
     *
     * @param savedTask the saved task
     * @return the task as a Task class
     */
    public static Task buildTaskFromSave(String savedTask) throws CorruptedDataException {
        String[] savedTaskFields = savedTask.split("\\|");
        boolean isMarked;

        if (savedTaskFields[1].equals("1")) {
            isMarked = true;
        } else if (savedTaskFields[1].equals("0")) {
            isMarked = false;
        } else {
            throw new CorruptedDataException(Messages.MESSAGE_CORRUPTED_DATA + savedTask + "\n");
        }
        try {
            switch (savedTaskFields[0]) {
            case "E":
                return new Event(isMarked, savedTaskFields[2], savedTaskFields[3], savedTaskFields[4]);

            case "D":
                return new Deadline(isMarked, savedTaskFields[2], savedTaskFields[3]);

            case "T":
                return new ToDo(isMarked, savedTaskFields[2]);

            default:
                throw new CorruptedDataException(Messages.MESSAGE_CORRUPTED_DATA + savedTask + "\n");
            }
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new CorruptedDataException(Messages.MESSAGE_CORRUPTED_DATA + savedTask + "\n");
        }
    }

    /**
     * Checks if the task description contains the keyword.
     *
     * @param keyWord the key word
     * @return true if keyword is found, false otherwise.
     */
    public boolean containsKeyWord(String keyWord) {
        String lowerCaseDescription = this.description.toLowerCase();
        String lowerCaseKeyWord = keyWord.toLowerCase();
        return lowerCaseDescription.contains(lowerCaseKeyWord);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return description.equals(task.description) && isMarked.equals(task.isMarked);
    }

    @Override
    public String toString() {
        return this.isMarked
                ? String.format("[X] %s", this.description)
                : String.format("[ ] %s", this.description);
    }

}
