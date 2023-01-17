package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


class Events extends Task {

    protected String from;
    protected String to;

    Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    Events(String description,String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }
    
    
    String localDateParser(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
    

    @Override
    Events markAsDone() {
        return new Events(this.getDescription(), from, to, true);
    }
    
    @Override
    Events markAsUndone() {
        return new Events(this.getDescription(), from, to, false);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + localDateParser(from) + ")" + "(to: " + localDateParser(to) + ")";
    }
}
