package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime dateTypeDeadline;
    private String deadlineDue;
    public Deadline(String content) {
        super(content.substring(9).split("/")[0]);
        String[] strArr = content.split("/by");
        String deadlineTime = strArr[1].substring(1);
        if (deadlineTime.contains("/")) {
            String[] strArrDate = deadlineTime.split("/");
            this.deadlineDue = this.dateFormat(strArrDate);
        } else {
            this.deadlineDue = "(" + "by:" + strArr[1].substring(0) + ")";
        }
    }

    public String dateFormat(String[] strArrDate) {
        LocalDateTime dateTypeDeadline;
        dateTypeDeadline = LocalDateTime.of(Integer.parseInt(strArrDate[2].substring(0,4)),
                Integer.parseInt(strArrDate[1]), Integer.parseInt(strArrDate[0]),
                Integer.parseInt(strArrDate[2].substring(5,7)),
                Integer.parseInt(strArrDate[2].substring(7)));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-YYYY");
        return "(" + "by: " + dtf.format(dateTypeDeadline) + ")";
    }

    public Deadline(String content, boolean mark) {
        super(content.split("\\(")[0], mark);
        this.deadlineDue = "(" + content.split("\\(")[1];
    }

    public String toString() {
        String sign = "";
        return ". [D][" +super.markSign(super.isMark) + "] " + super.content + deadlineDue;
    }

    public String printRecord() {
        return "[D]" + " [" + super.markSign(super.isMark) + "] " + super.content + this.deadlineDue + "\n";
    }
}