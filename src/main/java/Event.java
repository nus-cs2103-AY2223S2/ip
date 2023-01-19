public class Event extends Task{
    public Event(String description) {
        super(description);
        symbol = 'E';

        String[] strArr= description.split("/", 2);
        String task = strArr[0];

        String[] timeArr = strArr[1].split("/");
        String[] fromArr = timeArr[0].split(" ", 2);
        String from = fromArr[0] + ": " + fromArr[1];
        String[] toArr = timeArr[1].split(" ", 2);
        String to = toArr[0] + ": " + toArr[1];
        String time = from + to;

        this.description = task + "(" + time + ")";
    }
}

