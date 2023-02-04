import java.time.LocalDate;

class Event extends Task {
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

    @Override
    public String getFileDesc() {
        return this.isDone
        ? "E|1|" + this.name + "|" + this.startDate + "|" + this.endDate
        : "E|0|" + this.name + "|" + this.startDate + "|" + this.endDate;
    }

    @Override
    public String toString() {
        return this.isDone
                ? "[E][X] " + this.name + " (from: " + getDate(this.from) + " to: " + getDate(this.to) + ")"
                : "[E][ ] " + this.name + " (from: " + getDate(this.from) + " to: " + getDate(this.to) + ")";
    }
}