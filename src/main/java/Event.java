public class Event extends Duke.Task {
    private String eventSpan;
    public Event(String content) {
        super(content.substring(6).split("/")[0]);
        String[] strArr = content.split("/");
        this.eventSpan = "(" + "by:" + strArr[1].substring(4) + "to:" + strArr[2].substring(2) + ")";
    }

    public Event(String content, boolean mark) {
        super(content.split("\\(")[0], mark);
        this.eventSpan = "(" + content.split("\\(")[1];
    }

    public String toString() {
        String sign = "";
        return ". [E][" + super.markSign(super.mark) + "] " + super.content + this.eventSpan;
    }

    public String printEvent() {
        return "[E]" + " [" + super.markSign(super.mark) + "] " + super.content + " " + this.eventSpan + "\n";
    }
}