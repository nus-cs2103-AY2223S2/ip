package duke;

public class Event extends Task {
    protected Times from, to;
    protected String type = "[E]";

    public Event(String description, String from, String to) {
        super(description);
        this.from = new Times(from);
        this.to = new Times(to);
    }

    public static Event createEvent(String input) throws DukeException {
        int index1 = input.indexOf("/");
        int index2 = input.lastIndexOf("/");
        if (index1 == -1 || index2 == -1) {
            throw new DukeException(ExceptionType.TASK_FORMAT_ERROR);
        }
        String description = input.substring(6, index1 - 1);
        String from = input.substring(index1 + 6, index2 - 1);
        String to = input.substring(index2 + 4);
        Event temp = new Event(description, from, to);
        return temp;
    }

    @Override
    public String getDescriptionAndTime() {
        return description + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toString() {
        return type + super.toString() + " (from: " + from + " to: " + to + ")";
    }

}
