public class Event extends Task {

    String start, end;

    public Event(String title, String start, String end) {
        super(title);
        this.start = start;
        this.end = end;
    }

}
