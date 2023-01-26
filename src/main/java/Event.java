import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Tasks {
    private LocalDateTime start_Date;
    private LocalDateTime end_Date;
    public Event(String content, String start_Date, String end_Date) {
        super(content);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.start_Date = LocalDateTime.parse(start_Date.trim(), formatter);
        this.end_Date = LocalDateTime.parse(end_Date.trim(), formatter);
    }

    public Event(boolean isMarked, String content, String start_Date, String end_Date) {
        super(isMarked, content);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.start_Date = LocalDateTime.parse(start_Date.trim(), formatter);
        this.end_Date = LocalDateTime.parse(end_Date.trim(), formatter);
    }

    @Override
    public String addDivider() {
        String d = " | ";
        int marked = this.isMarked() ? 1 : 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String timePeriod = start_Date.format(formatter) + d + end_Date.format(formatter);
        return "E" + d + marked + d + this.get_content() + d + timePeriod;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

        return "[E] " + super.toString() + " (from: " + start_Date.format(formatter) + " to: " + end_Date.format(formatter) + ")";
    }
}
