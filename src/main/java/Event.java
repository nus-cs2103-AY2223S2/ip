import java.util.Date;
import java.text.ParseException;

public class Event extends Task {
    private Date start;
    private Date end;
    public Event(String description, String start, String end) throws SundayException {
        super(description);
        try {
            this.start = super.readFormat.parse(start);
            this.end = super.readFormat.parse(end);
        } catch (ParseException e) {
            throw new SundayException(
                    "OOPS! It appears the deadline given was not of the format dd/mm/yyyy hhmm");
        }
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
