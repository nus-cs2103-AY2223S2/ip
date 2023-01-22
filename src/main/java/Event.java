public class Event extends Task {

    protected String from;
    protected String until;

    public Event(String name, String from, String until) {
        super(name);
        this.from = from;
        this.until = until;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        String[] strArr = from.split(" ", 2);
        String[] strArr2 = until.split(" ", 2);
        return name + " (" + strArr[0] + ":" + " " + strArr[1] + " " + strArr2[0] + ":" + " " + strArr2[1] + ")";
    }
}
