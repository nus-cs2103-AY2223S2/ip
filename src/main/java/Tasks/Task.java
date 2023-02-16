package tasks;

import exceptions.InsufficientArgumentsException;
import exceptions.InvalidDateFormatException;
import exceptions.NoTaskDescriptionException;
import exceptions.UnknownTaskException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;


/**
 * This class represents a Generic Task which could be a Todo, Deadline or Event Task
 */
public abstract class Task {

    protected String name;
    protected Boolean isChecked = false;


    protected Task(String name, String type) throws NoTaskDescriptionException {
        if (name.isBlank()) {
            throw new NoTaskDescriptionException(type);
        } 
        this.name = name;
    }

    public static Task createTask(String[] args) throws NoTaskDescriptionException, InvalidDateFormatException, InsufficientArgumentsException, UnknownTaskException {
        // for (int i = 0; i < args.length; i++) {
        //     args[i].strip();
        // }

        LocalDateTime[] dates = new LocalDateTime[2];
        
        Task task = null;

        try {
            EnumTask tt = EnumTask.valueOf(args[0]);
            switch(tt) {
            case TODO:
                task = new Todo(args[1]);
                break;
            case DEADLINE:
                dates[0] = LocalDateTime.parse(args[2].strip());
                task = new Deadline(args[1], dates[0]);
                break;
            case EVENT:
                dates[0] = LocalDateTime.parse(args[2].strip());
                dates[1] = LocalDateTime.parse(args[3].strip());
                task = new Event(args[1], dates[0], dates[1]);
                break;
            }
        } catch (IllegalArgumentException e) {
            throw new UnknownTaskException(args[0]);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException(e.getParsedString());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InsufficientArgumentsException(args[0]);
        }

        return task;
    }

    public abstract boolean contains(LocalDate date);

    public String mark() {
        boolean isPrevMarked = this.isChecked;
        this.isChecked = true;
        String s = isPrevMarked 
                    ? "This task \n    " + this + "\n had previously already marked as done! Did you forget?" 
                    : "This task is marked as done: \n    " + this;
        return s;
    }

    public String unmark() {
        boolean isPrevMarked = this.isChecked;
        this.isChecked = false;
        String s = isPrevMarked  
                    ? "Okay... Being unproductive I see...: \n    " + this
                    : "This task \n    " + this + "\n hasn't been done! Did you not know?!";
        return s;
    }

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

    public String stringifyTaskToSave() {
        return this.isChecked.toString() + "|" + this.name;
    }
}
