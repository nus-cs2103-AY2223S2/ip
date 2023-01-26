import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Deadline extends Tasks {
    private LocalDateTime time = null;
    public Deadline(String content, String date, boolean isDone) {
        super(content, isDone);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        this.time = LocalDateTime.parse(date, format);
        this.type = 'D';
    }
    @Override
    public String getDuration() {
        return this.time.toString();
    }
    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm");
        String time = this.time.format(format);
        return "[" + this.getTypeIcon() + "]"
                + "[" + this.getStatusIcon() + "] " + this.seeTaskContent() + " (by: " + time + ")";
    }
}
