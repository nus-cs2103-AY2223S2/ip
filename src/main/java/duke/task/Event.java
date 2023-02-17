package duke.task;

import java.time.LocalDate;

import duke.DukeException;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;
    public Event(String name, String start, String end) throws DukeException {
        super(name);
        this.from = LocalDate.parse(start);
        this.to = LocalDate.parse(end);
        if (this.from.isAfter(this.to)) {
            throw new DukeException("/to date is before /from date");
        }
    }

    public String changeFrom(LocalDate date) {
        this.from = date;
        return "I have changed the /from date:\n" +
                this.toString();
    }

    public String changeTo(LocalDate date) {
        this.to = date;
        return "I have changed the /to date\n" +
                this.toString();
    }

    @Override
    public String getFileDesc() {
        return "E|" + super.getFileDesc() + "|" + convertFileDate(this.from) + "|" + convertFileDate(this.to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getDate(this.from) + " to: " + getDate(this.to) + ")";
    }
}