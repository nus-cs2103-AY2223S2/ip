package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String eventSpan;
    public Event(String content) {
        super(content.substring(6).split("/")[0]);
        String[] strArr = content.split("/from");
        String[] eventTime = strArr[1].substring(1).split("/to");
        String eventStartTime = eventTime[0];
        String eventEndTime = eventTime[1].substring(1);
        if (eventStartTime.contains("/")) {
            String[] strArrStart = eventStartTime.split("/");
            eventStartTime = dateFormat(strArrStart);
        } if (eventEndTime.contains("/")) {
            String[] strArrEnd = eventEndTime.split("/");
            eventEndTime = dateFormat(strArrEnd);
        }
        this.eventSpan = "(" + "by: " + eventStartTime + " to: " + eventEndTime + ")";
    }

    public String dateFormat(String[] strArrDate) {
        LocalDateTime dateTypeEvent;
        dateTypeEvent = LocalDateTime.of(Integer.parseInt(strArrDate[2].substring(0,4)),
                Integer.parseInt(strArrDate[1]), Integer.parseInt(strArrDate[0]),
                Integer.parseInt(strArrDate[2].substring(5,7)),
                Integer.parseInt(strArrDate[2].substring(7,9)));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-YYYY");
        return dtf.format(dateTypeEvent);
    }

    public Event(String content, boolean mark) {
        super(content.split("\\(")[0], mark);
        this.eventSpan = "(" + content.split("\\(")[1];
    }

    public String toString() {
        String sign = "";
        return ". [E][" + super.markSign(super.mark) + "] " + super.content + this.eventSpan;
    }

    public String printRecord() {
        return "[E]" + " [" + super.markSign(super.mark) + "] " + super.content + " " + this.eventSpan + "\n";
    }
}