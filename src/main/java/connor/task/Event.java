package connor.task;

import connor.parser.Parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String dataFormat1;
    private String dataFormat2;

    public Event(String taskName,String taskStart, String taskEnd) {
        super(taskName);
        try {
            this.startTime = Parser.parseDateTime(taskStart);
            this.endTime = Parser.parseDateTime(taskEnd);
        } catch (DateTimeException e) {
            System.out.println("        " + e.getMessage());
        }
        this.dataFormat1 = Parser.formatDateTime(taskStart);
        this.dataFormat2 = Parser.formatDateTime(taskEnd);
    }

    public Event(String taskName, Boolean isDone, String startData, String endData) {
        super(taskName, isDone);
        this.startTime = LocalDateTime.parse(startData);
        this.endTime = LocalDateTime.parse(endData);
        this.dataFormat1 = startData;
        this.dataFormat2 = endData;
    }

    @Override
    public String dataFormat() {
        return "E|" + super.dataFormat() + "|" + this.dataFormat1 + "|" + this.dataFormat2;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: "
                + this.formatDateTime(this.startTime)
                + " to: "
                + this.formatDateTime(this.endTime)
                + ")";
    }
}
