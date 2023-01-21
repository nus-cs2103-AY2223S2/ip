import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

abstract class Task {
    private String desc;
    private boolean isDone;

    protected final static DateTimeFormatter dateTimeFmt = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Task(String _desc) {
        desc = _desc;
        isDone = false;
    }

    protected String format() {
        return (isDone() ? "[X] " : "[ ] ") + desc;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

    public void setNotDone() {
        isDone = false;
    }

    public String desc() {
        return desc;
    }

    @Override
    public abstract String toString();

    public abstract String marshal();

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
            return null;
        }
    }

    protected static void trueOrThrow(boolean cond, RuntimeException t) {
        if (!cond) {
            throw t;
        }
    }
}

class Todo extends Task {
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
}

class Deadline extends Task {
    LocalDateTime deadline;

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
}

class Event extends Task {
    LocalDateTime from, to;

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
}
