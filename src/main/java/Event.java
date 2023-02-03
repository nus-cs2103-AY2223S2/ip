class Event extends Task {
    public Event(String name, String start, String end) {
        super(name, start, end);
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
                ? "[E][X] " + this.name + " (from: " + this.startDate + " to: " + this.endDate + ")"
                : "[E][ ] " + this.name + " (from: " + this.startDate + " to: " + this.endDate + ")";
    }
}