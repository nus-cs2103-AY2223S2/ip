import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Deadline extends Duke.Task {
    private LocalDateTime dateTypeDeadline;
    private String deadlineDue;
    public Deadline(String content) {
        super(content.substring(9).split("/")[0]);
        String[] strArr = content.split("/by");
        String deadlineTime = strArr[1].substring(1);
        System.out.println(deadlineTime);
        if (deadlineTime.contains("/")) {
            String[] strArrDate = deadlineTime.split("/");
            System.out.println(Arrays.toString(strArrDate));
            this.dateTypeDeadline = LocalDateTime.of(Integer.parseInt(strArrDate[2].substring(0,4)),
                    Integer.parseInt(strArrDate[1]), Integer.parseInt(strArrDate[0]),
                    Integer.parseInt(strArrDate[2].substring(5,7)),
                    Integer.parseInt(strArrDate[2].substring(7)));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-YYYY");
            this.deadlineDue = "(" + "by: " + dtf.format(dateTypeDeadline) + ")";
        } else {
            this.deadlineDue = "(" + "by: " + strArr[1].substring(2) + ")";
        }
    }

    public Deadline(String content, boolean mark) {
        super(content.split("\\(")[0], mark);
        this.deadlineDue = "(" + content.split("\\(")[1];
    }

    public String toString() {
        String sign = "";
        return ". [D][" +super.markSign(super.mark) + "] " + super.content + deadlineDue;
    }

    public String printRecord() {
        return "[D]" + " [" + super.markSign(super.mark) + "] " + super.content + " " + this.deadlineDue + "\n";
    }
}