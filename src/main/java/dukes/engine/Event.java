package dukes.engine;

class Event extends Task {
    String start;
    String end;

    Event(String taskName, String start, String end) {
        super(taskName);
        this.tag = "E";
        this.start = start;
        this.end = end;
    }

    Event(String taskName, boolean isDone, String start, String end) {
        super(taskName, isDone);
        this.tag = "E";
        this.start = start;
        this.end = end;
    }

    @Override
    String getFromTime() {
        return this.start;
    }

    @Override
    String getToTime() {
        return this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                this.start + " to: " + this.end + ")";
    }
}
