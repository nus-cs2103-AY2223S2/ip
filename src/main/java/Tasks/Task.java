package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import exceptions.InsufficientArgumentsException;
import exceptions.InvalidDateFormatException;
import exceptions.NoTaskDescriptionException;
import exceptions.UnknownTaskException;


/**
 * This class represents a Generic Task which could be a Todo, Deadline or Event Task
 */
public abstract class Task {

    /** Name of Task */
    protected String name;
    protected Boolean isChecked = false;

    /**
     * Creates a Task object
     *
     * @param name name of the task
     * @param type type of the task (todo, deadline or event)
     * @throws NoTaskDescriptionException
     */
    protected Task(String name, String type) throws NoTaskDescriptionException {
        if (name.isBlank()) {
            throw new NoTaskDescriptionException(type);
        }
        this.name = name;
    }

    /**
     * Creates Task with the argument input
     *
     * @param args array of strings
     * @return newly created task
     * @throws NoTaskDescriptionException
     * @throws InvalidDateFormatException
     * @throws InsufficientArgumentsException
     * @throws UnknownTaskException
     */
    public static Task createTask(String[] args)
            throws NoTaskDescriptionException, InvalidDateFormatException,
            InsufficientArgumentsException, UnknownTaskException {

        LocalDateTime[] dates = new LocalDateTime[2];

        Task task = null;

        try {
            EnumTask tt = EnumTask.valueOf(args[0]);
            switch(tt) {
            case TODO:
                task = new Todo(args[1].strip());
                break;
            case DEADLINE:
                dates[0] = LocalDateTime.parse(args[2].strip());
                task = new Deadline(args[1].strip(), dates[0]);
                break;
            case EVENT:
                dates[0] = LocalDateTime.parse(args[2].strip());
                dates[1] = LocalDateTime.parse(args[3].strip());
                task = new Event(args[1].strip(), dates[0], dates[1]);
                break;
            default:
                break;
            }
        } catch (IllegalArgumentException e) {
            throw new UnknownTaskException(args[0]);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException(e.getParsedString());
        } catch (NullPointerException e) {
            throw new InsufficientArgumentsException(args[0]);
        }

        return task;
    }

    /**
     * Checks whether this task contains a specified date
     *
     * @param date date to compare date of task to
     * @return true if task contains specified date, false otherwise
     */
    public abstract boolean contains(LocalDate date);

    /**
     * Checks whether this task contains a specified keyword
     *
     * @param keyword word to compare task name to
     * @return true if task name contains keyword, false otherwise
     */
    public boolean contains(String keyword) {
        return this.name.contains(keyword);
    }

    /**
     * Marks a task as done
     *
     * @returns Duke's response for marking a task
     */
    public String mark() {
        boolean isPrevMarked = this.isChecked;
        this.isChecked = true;
        String s = isPrevMarked
                    ? "This task \n    " + this + "\n had previously already marked as done! Did you forget?"
                    : "This task is marked as done: \n    " + this;
        return s;
    }

    /**
     * Marks a task as undone
     *
     * @return Duke's response for unmarking a task
     */
    public String unmark() {
        boolean isPrevMarked = this.isChecked;
        this.isChecked = false;
        String s = isPrevMarked
                    ? "Okay... Being unproductive I see...: \n    " + this
                    : "This task \n    " + this + "\n hasn't been done! Did you not know?!";
        return s;
    }

    /**
     * Converts isChecked value of task to string to be printed
     *
     * @return string of whether task is marked in a checkbox lookalike format
     */
    protected String markToString() {
        return this.isChecked ? "[X]" : "[ ]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return markToString() + " " + this.name;
    }

    /**
     * Converts a task into a string format for saving into database
     *
     * @return String format of task for database
     */
    public String stringifyTaskToSave() {
        return this.isChecked.toString() + "|" + this.name;
    }
}
