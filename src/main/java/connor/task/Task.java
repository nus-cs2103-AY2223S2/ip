package connor.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public abstract class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public Task(String taskName, Boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    private String getMark() {
        if (this.isDone) {
            return "[X]";
        }
        return "[ ]";
    }

    public String dataFormat() {
        return this.isDone + "|" + this.taskName;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String formatDateTime(LocalDateTime input) {
        String month = input.getMonth().toString().substring(0, 3);
        int day = input.getDayOfMonth();
        int year = input.getYear();
        String hour = String.format("%02d", input.getHour());
        String minute = String.format("%02d", input.getMinute());
        return month + " " + day + " " + year + " " + hour + minute;
    }

    public String dateTimeFormat(String input) {
        String[] dateTimePair = input.split(" ");
        String date = dateTimePair[0];
        String time = dateTimePair[1];
        String hrStr = time.substring(0, 2);
        String minStr = time.substring(2, 4);
        return date + "T" + hrStr + ":" + minStr + ":00";
    }

    public LocalDateTime parseDateTime(String input) throws DateTimeException {
        String formattedDateTime = dateTimeFormat(input);
        return LocalDateTime.parse(formattedDateTime);
    }

    public String toString() {
        return getMark() + " " + this.taskName;
    }
}
