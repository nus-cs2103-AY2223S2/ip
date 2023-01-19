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
        if (str.contains(target1) && str.contains(target2)) {
            String description, start, end;
            int index1 = str.indexOf(target1);
            int index2 = str.indexOf(target2);
            if (index2 - index1 > 7) {
                description = str.substring(0, index1);
                start = str.substring(index1 + 7, index2);
                end = str.substring(index2 + 5);
                return new Event(description, start, end);
            }
            throw new RuntimeException("Unable to create Event! Check your /from and /to formatting!\n");
        }
        throw new RuntimeException("Unable to create Event! Check your format!\n");
    }

    @Override
    public String toString() {
        String str = super.toString();
        str += " (from: " + this.start + " to: " + this.end + ")" + "\n";
        return str;
    }
}
