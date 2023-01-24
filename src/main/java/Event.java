public class Event extends Task {

    private String start, end;

    public Event(String title, String start, String end, boolean done) {
        super(title, done);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String doneString = this.getDone() ? "X" : " ";
        return String.format("[E][%s] %s (from: %s, to: %s)", doneString, this.getTitle(), this.start, this.end);
    }

    public String writeToMemory() {
        String doneString = this.getDone() ? "1" : "0";
        return "E, " + doneString + ", " + this.getTitle() + ", " + this.start + ", " + this.end;
    }

}
