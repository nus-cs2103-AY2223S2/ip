public class Deadline extends Duke.Task {
    private String deadlineDue;
    public Deadline(String content) {
        super(content.substring(9).split("/")[0]);
        String[] strArr = content.split("/");
        this.deadlineDue = "(" + "by:" + strArr[1].substring(2) + ")";
    }

    public String toString() {
        String sign = "";
        if (super.mark == false) {
            sign = " ";
        } else {
            sign = "X";
        }
        return ". [D][" + sign + "] " + super.content + deadlineDue;
    }
}