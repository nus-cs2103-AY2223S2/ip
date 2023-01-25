package task;

import util.DukeException;

public class Event extends Task {
    private String startFormatted;
    private String start;
    private String endFormatted;
    private String end;

    public Event(String description, String start, String end) throws DukeException {
        super(description);
        this.start = start;
        this.end = end;
        this.startFormatted = super.dateFormatter(start);
        this.endFormatted = super.dateFormatter(end);
    }

    public String serialise() {
        return String.format("Event,%s,%s,%s,%s", super.getStatusIcon(),
                super.getDescription(), this.start, this.end);
    }

    public static Task deserialise(String data) throws DukeException {
        String arr[] = data.split(",");

        boolean isDone = arr[1].equals("X");
        String description = arr[2];
        String start = arr[3];
        String end = arr[4];

        return new Event(description, start, end);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (Start: " + startFormatted + " | End: " + endFormatted + ")";
    }

    //event party /from 12/2/23 6:00PM /to 12/2/23 10:00PM
}
