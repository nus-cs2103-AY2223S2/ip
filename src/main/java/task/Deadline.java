package task;
import java.util.Date;
import java.text.ParseException;

public class Deadline extends Task{
    private Date deadline;
    public Deadline(String description, String deadline) throws ParseException {
        super(description);
        this.deadline = super.readFormat.parse(deadline);
    }
    @Override
    public String save() {
        StringBuilder sb = new StringBuilder();
        sb.append("[D]");
        sb.append(super.toString()).append(" (by: ");
        sb.append(super.readFormat.format(this.deadline));
        sb.append(")");
        return sb.toString();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[D]");
        sb.append(super.toString()).append(" (by: ");
        sb.append(super.writeFormat.format(this.deadline));
        sb.append(")");
        return sb.toString();
    }
}
