public class Event extends Duke.Task {
    private String eventSpan;
    public Event(String content) {
        super(content.substring(6).split("/")[0]);
        String[] strArr = content.split("/");
        this.eventSpan = "(" + "by:" + strArr[1].substring(4) + "to:" + strArr[2].substring(2) + ")";
    }

    public String toString() {
        String sign = "";
        if (super.mark == false) {
            sign = " ";
        } else {
            sign = "X";
        }
        return ". [E][" + sign + "] " + super.content + this.eventSpan;
    }
}