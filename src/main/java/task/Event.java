package task;
import java.util.Date;
import java.text.ParseException;

public class Event extends Task {
    private Date start;
    private Date end;
    public Event(String description, String start, String end) throws ParseException {
        super(description);
        this.start = super.readFormat.parse(start);
        this.end = super.readFormat.parse(end);
    }
    @Override
    public String save() {
        StringBuilder sb = new StringBuilder();
        sb.append("[E]");
        sb.append(super.toString());
        sb.append(" (from: ");
        sb.append(super.readFormat.format(this.start));
        sb.append(" to: ");
        sb.append(super.readFormat.format(this.end));
        sb.append(")");
        return sb.toString();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[E]");
        sb.append(super.toString());
        sb.append(" (from: ");
        sb.append(super.writeFormat.format(this.start));
        sb.append(" to: ");
        sb.append(super.writeFormat.format(this.end));
        sb.append(")");
        return sb.toString();
    }
}
