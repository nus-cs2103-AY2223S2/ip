package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    private String from;
    private String to;
    private LocalDate fromDate = null;
    private LocalDate toDate = null;

    public EventTask(String title, String from, String to) {
        super(title);
        this.from = formatIfDate(from, fromDate);
        this.to = formatIfDate(to, toDate);
    }

    private String formatIfDate(String input, LocalDate date) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/uuuu");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd LLL uuuu");
        try {
            LocalDate output = LocalDate.parse(input, inputFormat);
            date =  output;
            return output.format(outputFormat);
        } catch (DateTimeParseException e) {
            return input;
        }
    }

    public String save() {
        String status = this.isDone ? "DONE/+/" : "NOTDONE/+/";
        return "EVENT/+/" + status + this.title + "/+/" + this.from + "/+/" + this.to + "\n";
    }

    @Override
    public String toString() {
        String status = this.isDone ? "[x] " : "[ ] ";
        return "[E]" + status + this.title + " (from: " + this.from + " to: " + this.to + ")";
    }
}
