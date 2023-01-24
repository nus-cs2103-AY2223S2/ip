package task;

public class Event extends Task {
    private String startFormatted;
    private String endFormatted;
    private String ID;
    public Event(String description, String start, String end) {
        super(description);
        this.ID = "E";
        this.startFormatted = super.dateFormatter(start);
        this.endFormatted = super.dateFormatter(end);
    }

    @Override
    public String toString() {
        return "[" + ID + "]" + super.toString() + " (Start: " + startFormatted + " | End: " + endFormatted + ")";
    }

    //event party /from 12/2/23 6:00PM /to 12/2/23 10:00PM
}
