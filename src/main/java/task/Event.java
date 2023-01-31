package task;

import java.text.ParseException;
import java.util.Date;

public class Event extends Task {

    private Date start;

    private Date end;

    public Event(String description, String start, String end) throws ParseException {
        super(description);
        this.start = super.getReadFormat().parse(start);
        this.end = super.getReadFormat().parse(end);
    }

    @Override
    public String save() {
        StringBuilder sb = new StringBuilder();
        sb.append("[E]");
        sb.append(super.toString());
        sb.append(" (from: ");
        sb.append(super.getReadFormat().format(this.start));
        sb.append(" to: ");
        sb.append(super.getReadFormat().format(this.end));
        sb.append(")");
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[E]");
        sb.append(super.toString());
        sb.append(" (from: ");
        sb.append(super.getWriteFormat().format(this.start));
        sb.append(" to: ");
        sb.append(super.getWriteFormat().format(this.end));
        sb.append(")");
        return sb.toString();
    }
}
