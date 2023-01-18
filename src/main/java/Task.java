import java.time.LocalDate;
import java.time.LocalDateTime;

abstract class Task {
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

    public String toString() {
        return getMark() + " " + this.taskName;
    }
}
