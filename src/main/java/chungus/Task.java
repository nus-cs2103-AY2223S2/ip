package chungus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

import chungus.util.Pair;

/**
 * Represents a task.
 */
abstract class Task {
    private String desc;
    private boolean isDone;

    protected HashSet<String> tags;

    protected static final DateTimeFormatter DATETIME_FMT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * Constructor for a task. All tasks require a description.
     * 
     * @param desc Description for the task.
     */
    public Task(String desc) {
        this.desc = desc;
        isDone = false;
        tags = new HashSet<>();
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
        assert s.length() > 1;
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

    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }

    public void addTags(String... tags) {
        for (String tag : tags) {
            this.tags.add(tag);
        }
    }

    protected String chonkifyTags() {
        StringBuilder sb = new StringBuilder();
        for (String tag : tags) {
            sb.append(Chonk.chonkify(tag));
        }
        return sb.toString();
    }

    protected static HashSet<String> dechonkifyTags(String s, int idx) {
        Pair<String, Integer> dechonked;
        HashSet<String> tags = new HashSet<>();

        while (idx < s.length()) {
            dechonked = Chonk.dechonkify(s, idx);
            trueOrThrow(dechonked != null, new TaskMarshalException(s));
            String tag = dechonked.first();
            idx = dechonked.second();

            tags.add(tag);
        }

        return tags;
    }

    public String[] getTags() {
        return tags.stream().toArray(String[]::new);
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
        b.append(Chonk.chonkify(desc()));

        b.append(chonkifyTags());

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

        int idx = 2;
        Pair<String, Integer> dechonked;

        // Dechonk the description
        dechonked = Chonk.dechonkify(s, idx);
        trueOrThrow(dechonked != null, new TaskMarshalException(s));
        String desc = dechonked.first();
        idx = dechonked.second();

        Todo ret = new Todo(desc);

        // Dechonk the tags
        ret.tags = dechonkifyTags(s, idx);

        if (isDone) {
            ret.setDone();
        } else {
            ret.setNotDone();
        }

        return ret;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Todo)) {
            return false;
        }
        Todo otherTodo = (Todo) other;
        return this.desc().equals(otherTodo.desc());
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
     * @param desc     The task's description.
     * @param deadline Deadline for the task.
     */
    public Deadline(String desc, LocalDateTime deadline) {
        super(desc);
        this.deadline = deadline;
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
        b.append(Chonk.chonkify(deadline.format(DATETIME_FMT)));

        b.append(chonkifyTags());

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
        trueOrThrow(s.charAt(0) == 'D', new TaskMarshalException(s));

        int idx = 2;
        Pair<String, Integer> dechonked;

        dechonked = Chonk.dechonkify(s, idx);
        trueOrThrow(dechonked != null, new TaskMarshalException(s));
        String desc = dechonked.first();
        idx = dechonked.second();

        dechonked = Chonk.dechonkify(s, idx);
        trueOrThrow(dechonked != null, new TaskMarshalException(s));
        String deadline = dechonked.first();
        idx = dechonked.second();

        Deadline ret = new Deadline(desc, LocalDateTime.parse(deadline, DATETIME_FMT));
        boolean isDone = s.charAt(1) == '0' ? false : true;

        if (isDone) {
            ret.setDone();
        } else {
            ret.setNotDone();
        }

        // Dechonk the tags
        ret.tags = dechonkifyTags(s, idx);

        return ret;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Deadline)) {
            return false;
        }
        Deadline otherDeadline = (Deadline) other;
        return this.desc().equals(otherDeadline.desc()) && this.deadline.equals(otherDeadline.deadline);
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
     * @param desc Description for the event.
     * @param from When the event starts.
     * @param to   When the event ends.
     */
    public Event(String desc, LocalDateTime from, LocalDateTime to) {
        super(desc);
        this.from = from;
        this.to = to;
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
        b.append(Chonk.chonkify(from.format(DATETIME_FMT)));
        b.append(Chonk.chonkify(to.format(DATETIME_FMT)));

        b.append(chonkifyTags());

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
        trueOrThrow(s.charAt(0) == 'E', new TaskMarshalException(s));
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
        idx = dechonked.second();

        Event ret = new Event(desc, LocalDateTime.parse(from, DATETIME_FMT), LocalDateTime.parse(to, DATETIME_FMT));
        if (isDone) {
            ret.setDone();
        } else {
            ret.setNotDone();
        }

        // Dechonk the tags
        ret.tags = dechonkifyTags(s, idx);

        return ret;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Event)) {
            return false;
        }
        Event otherEvent = (Event) other;
        return this.desc().equals(otherEvent.desc()) && this.from.equals(otherEvent.from)
                && this.to.equals(otherEvent.to);
    }
}
