package task;

import java.text.ParseException;
import java.util.Date;

public class Deadline extends Task {

    private Date deadline;

    public Deadline(String description, String deadline) throws ParseException {
        super(description);
        this.deadline = super.getReadFormat().parse(deadline);
    }

    @Override
    public String save() {
        StringBuilder sb = new StringBuilder();
        sb.append("[D]");
        sb.append(super.toString()).append(" (by: ");
        sb.append(super.getReadFormat().format(this.deadline));
        sb.append(")");
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[D]");
        sb.append(super.toString()).append(" (by: ");
        sb.append(super.getWriteFormat().format(this.deadline));
        sb.append(")");
        return sb.toString();
    }
}
