public class Deadline extends Duke.Task {
    private String deadlineDue;
    public Deadline(String content) {
        super(content.substring(9).split("/")[0]);
        String[] strArr = content.split("/");
        this.deadlineDue = "(" + "by:" + strArr[1].substring(2) + ")";
    }

    public Deadline(String content, boolean mark) {
        super(content.split("\\(")[0], mark);
        this.deadlineDue = "(" + content.split("\\(")[1];
    }

    public String toString() {
        String sign = "";
        return ". [D][" +super.markSign(super.mark) + "] " + super.content + deadlineDue;
    }

    public String printDeadline() {
        return "[D]" + " [" + super.markSign(super.mark) + "] " + super.content + " " + this.deadlineDue + "\n";
    }
}