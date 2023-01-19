public class Event extends Task {
    String start;
    String end;

    private Event(String description, String start, String end) {
        super("E", description);
        this.start = start;
        this.end = end;
    }

    public static Event to(String str) {
        String target1 = " /from ";
        String target2 = " /to ";
        String description, start, end;
        int index1 = str.indexOf(target1);
        int index2 = str.indexOf(target2);
        description = str.substring(0, index1);
        start = str.substring(index1 + 7, index2);
        end = str.substring(index2 + 5);
        return new Event(description, start, end);
    }

    @Override
    public String toString() {
        String str = super.toString();
        str += " (from: " + this.start + " to: " + this.end + ")" + "\n";
        return str;
    }
}
