package Week2.src.main;
public class Event extends Task {

    String from;
    String to;

    Event(String content, String from, String to) {
        super(content);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E][" +this.getDone()+ "] " +this.content+ "(from:" +this.from+ " to:" +this.to+ ")";
    }
}
