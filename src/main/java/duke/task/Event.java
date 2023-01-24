package duke.task;

import duke.exception.InvalidDateTimeException;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime startDateTime;
    private LocalDateTime dueDateTime;

    public Event(String description, String from, String to) throws InvalidDateTimeException {
        super(description);

        this.startDateTime = handleDateTime(from);
        this.dueDateTime = handleDateTime(to);
    }

    public static Event createEvent(String desc) throws InvalidDateTimeException {
        String[] eventArr = desc.split(" /from ");
        String[] dataTimes = eventArr[1].split(" /to ");
        String eventDesc = eventArr[0].trim();
        String from = dataTimes[0].trim();
        String to = dataTimes[1].trim();
        return new Event(eventDesc, from, to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s %s to: %s %s)", super.toString(),
                this.startDateTime.toLocalDate(), this.startDateTime.toLocalTime(),
                this.dueDateTime.toLocalDate(), this.startDateTime.toLocalTime());
    }
}
