package duke.task;

import java.time.LocalDateTime;

public class Events extends Task{
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Events(String description, String from, String to) {
        super(description);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
    }

    @Override
    public String toString() {
        return "E" + " | " + super.toString() + " (from: " + this.from
                + "to: " + this.to + ")";
    }

    private LocalDateTime parseDateTime(String period) {
        String[] dateAndTime = period.split(" ");
        String[] dayMonthYear = dateAndTime[0].split("/");
        int hour = Integer.valueOf(dateAndTime[1])/100;
        int minute = Integer.valueOf(dateAndTime[1])%100;
        int[] ddMMYY = new int[3];
        for (int i = 0; i < dayMonthYear.length;i++){
            ddMMYY[i] = Integer.valueOf(dayMonthYear[i]);
        }

        return LocalDateTime.of(ddMMYY[2], ddMMYY[1], ddMMYY[0], hour, minute);
    }
}
