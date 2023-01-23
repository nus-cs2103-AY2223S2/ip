package Week2.src.main;
public class Deadline extends Task {
    String date;

    Deadline(String content, String date) {
        super(content);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D][" +this.getDone()+ "] " +this.content+ "(by:" +this.date+ ")";
    }
}
