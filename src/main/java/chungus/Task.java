package chungus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import chungus.util.Pair;

/**
 * Represents a task.
 */
abstract class Task {
    private String desc;
    private boolean isDone;

    protected final static DateTimeFormatter dateTimeFmt = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * Constructor for a task. All tasks require a description.
     * 
     * @param _desc Description for the task.
     */
    public Task(String _desc) {
        desc = _desc;
        isDone = false;
    }

    /**
     * Returns a user-friendly format of the task.
     * 
     * @return A user-friendly format of the task.
     */
    protected String format() {
        return (isDone() ? "[X] " : "[ ] ") + desc;
    }

    /**
     * Returns whether the task is complete.
     * 
     * @return Whether the task is complete.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as complete.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void setNotDone() {
        isDone = false;
    }

    /**
     * Retrieves the raw description for the task.
     * 
     * @return The raw description.
     */
    public String desc() {
        return desc;
    }

    @Override
    public abstract String toString();

    /**
     * Serialies the task to make it suitable for storing to disk.
     * 
     * @return The serialized string.
     */
    public abstract String marshal();

    /**
     * Tries to deserialize a string to produce a task.
     * 
     * @param s The serialized string.
     * @return The task.
     * @throws TaskMarshalException If the string is invalid.
     */
    public static Task unmarshal(String s) {
        char typ = s.charAt(0);
        switch (typ) {
            case 'T':
                return Todo.unmarshal(s);
            case 'D':
                return Deadline.unmarshal(s);
            case 'E':
                return Event.unmarshal(s);
            default:
                throw new TaskMarshalException(s);
        }
    }

    /**
     * Throws an exception if the condition is false. Just a convenience method.
     * 
     * @param cond The boolean condition.
     * @param t    The exception to throw.
     */
    protected static void trueOrThrow(boolean cond, RuntimeException t) {
        if (!cond) {
            throw t;
        }
    }
}

/**
 * A todo.
 */
class Todo extends Task {
    /**
     * Constructor for a todo.
     * 
     * @param desc Description for the todo.
     */
    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + format();
    }

    @Override
    public String marshal() {
        StringBuilder b = new StringBuilder();

        b.append('T');
        b.append(isDone() ? '1' : '0');
        b.append(desc());

        return b.toString();
    }

    /**
     * Tries to deserialize a string to produce a todo.
     * 
     * @param s The serialized string.
     * @return The todo.
     * @throws TaskMarshalException If the string is invalid.
     */
    public static Todo unmarshal(String s) {
        trueOrThrow(s.charAt(0) == 'T', new TaskMarshalException(s));

        boolean isDone = s.charAt(1) == '0' ? false : true;

        String desc = s.substring(2, s.length());
        Todo ret = new Todo(desc);
        if (isDone) {
            ret.setDone();
        } else {
            ret.setNotDone();
        }

        return ret;
    }

    @Override
    public boolean equals(Object _other) {
        if (!(_other instanceof Todo)) {
            return false;
        }
        Todo other = (Todo) _other;
        return this.desc().equals(other.desc());
    }
}

/**
 * A task with deadline.
 */
class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor for a deadline task.
     * 
     * @param desc      The task's description.
     * @param _deadline Deadline for the task.
     */
    public Deadline(String desc, LocalDateTime _deadline) {
        super(desc);
        deadline = _deadline;
    }

    @Override
    public String toString() {
        return "[D]" + format() + String.format(" (by: %s)", deadline);
    }

    @Override
    public String marshal() {
        StringBuilder b = new StringBuilder();

        b.append('D');
        b.append(isDone() ? '1' : '0');
        b.append(Chonk.chonkify(desc()));
        b.append(Chonk.chonkify(deadline.format(dateTimeFmt)));

        return b.toString();
    }

    /**
     * Tries to deserialize a string to produce a task with deadline.
     * 
     * @param s The serialized string.
     * @return The deadline task.
     * @throws TaskMarshalException If the string is invalid.
     */
    public static Deadline unmarshal(String s) {
        if (s.charAt(0) != 'D') {
            throw new TaskMarshalException(s);
        }
        boolean isDone = s.charAt(1) == '0' ? false : true;

        int idx = 2;
        Pair<String, Integer> dechonked;

        dechonked = Chonk.dechonkify(s, idx);
        trueOrThrow(dechonked != null, new TaskMarshalException(s));
        String desc = dechonked.first();
        idx = dechonked.second();

        dechonked = Chonk.dechonkify(s, idx);
        trueOrThrow(dechonked != null, new TaskMarshalException(s));
        String deadline = dechonked.first();

        Deadline ret = new Deadline(desc, LocalDateTime.parse(deadline, dateTimeFmt));
        if (isDone) {
            ret.setDone();
        } else {
            ret.setNotDone();
        }

        return ret;
    }

    @Override
    public boolean equals(Object _other) {
        if (!(_other instanceof Deadline)) {
            return false;
        }
        Deadline other = (Deadline) _other;
        return this.desc().equals(other.desc()) && this.deadline.equals(other.deadline);
    }
}

/**
 * An event.
 */
class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructor for an event.
     * 
     * @param desc  Description for the event.
     * @param _from When the event starts.
     * @param _to   When the event ends.
     */
    public Event(String desc, LocalDateTime _from, LocalDateTime _to) {
        super(desc);
        from = _from;
        to = _to;
    }

    @Override
    public String toString() {
        return "[E]" + format() + String.format(" (from: %s to: %s)", from, to);
    }

    @Override
    public String marshal() {
        StringBuilder b = new StringBuilder();

        b.append('E');
        b.append(isDone() ? '1' : '0');
        b.append(Chonk.chonkify(desc()));
        b.append(Chonk.chonkify(from.format(dateTimeFmt)));
        b.append(Chonk.chonkify(to.format(dateTimeFmt)));

        return b.toString();
    }

    /**
     * Tries to deserialize a string to produce an event.
     * 
     * @param s The serialized string.
     * @return The event object.
     * @throws TaskMarshalException If the string is invalid.
     */
    public static Event unmarshal(String s) {
        if (s.charAt(0) != 'E') {
            throw new TaskMarshalException(s);
        }
        boolean isDone = s.charAt(1) == '0' ? false : true;

        int idx = 2;
        Pair<String, Integer> dechonked;

        dechonked = Chonk.dechonkify(s, idx);
        trueOrThrow(dechonked != null, new TaskMarshalException(s));
        String desc = dechonked.first();
        idx = dechonked.second();

        dechonked = Chonk.dechonkify(s, idx);
        trueOrThrow(dechonked != null, new TaskMarshalException(s));
        String from = dechonked.first();
        idx = dechonked.second();

        dechonked = Chonk.dechonkify(s, idx);
        trueOrThrow(dechonked != null, new TaskMarshalException(s));
        String to = dechonked.first();

        Event ret = new Event(desc, LocalDateTime.parse(from, dateTimeFmt), LocalDateTime.parse(to, dateTimeFmt));
        if (isDone) {
            ret.setDone();
        } else {
            ret.setNotDone();
        }

        return ret;
    }

    @Override
    public boolean equals(Object _other) {
        if (!(_other instanceof Event)) {
            return false;
        }
        Event other = (Event) _other;
        return this.desc().equals(other.desc()) && this.from.equals(other.from) && this.to.equals(other.to);
    }
}
