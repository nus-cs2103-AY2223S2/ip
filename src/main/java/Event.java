import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;

    private Event(String description, LocalDateTime start, LocalDateTime end) {
        super(TaskType.EVENT, description);
        this.description = description;
        this.start = start;
        this.end = end;
    }

    public static Event to(String str) {
        String target1 = " /from ";
        String target2 = " /to ";
        LocalDateTime startDateTime, endDateTime;
        String description, start, end, startDay, startMonth, endDay, endMonth;
        int index1 = str.indexOf(target1);
        int index2 = str.indexOf(target2);
        description = str.substring(0, index1);
        start = str.substring(index1 + 7, index2);
        end = str.substring(index2 + 5);
        int startFirstSlash = start.indexOf("/");
        int startSecondSlash = start.indexOf("/", startFirstSlash + 1);
        int endFirstSlash = end.indexOf("/");
        int endSecondSlash = end.indexOf("/", endFirstSlash + 1);
        startDay = startFirstSlash == 1 ? "d" : "dd";
        startMonth = startSecondSlash - startFirstSlash == 2 ? "M" : "MM";
        endDay = endFirstSlash == 1 ? "d" : "dd";
        endMonth = endSecondSlash - endFirstSlash == 2 ? "M" : "MM";
        DateTimeFormatter startInFormatter = DateTimeFormatter.ofPattern(startDay + "/" + startMonth + "/yyyy HHmm");
        DateTimeFormatter endInFormatter = DateTimeFormatter.ofPattern(endDay + "/" + endMonth + "/yyyy HHmm");
        try {
            startDateTime = LocalDateTime.parse(start, startInFormatter);
            endDateTime = LocalDateTime.parse(end, endInFormatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Invalid date and time! Please try again!");
        }
        if (startDateTime.isAfter(endDateTime)) {
            throw new RuntimeException("Unable to create Event! Starting date and time cannot be after ending date and time!");
        }
        return new Event(description, startDateTime, endDateTime);
    }

    @Override
    public String taskToSavedForm() {
        DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        String marked = super.getStatusIcon() == "X" ? "1" : "0";
        return "event " + this.description + " /from " + this.start.format(outFormatter) + " /to " + this.end.format(outFormatter) + marked;
    }

    @Override
    public String toString() {
        DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        String str = super.toString();
        str += " (from: " + this.start.format(outFormatter) + " to: " + this.end.format(outFormatter) + ")" /*+ "\n"*/;
        return str;
    }
}
