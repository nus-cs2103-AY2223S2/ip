package twofive.task;

import java.time.LocalDate;

public class ToDo extends Task {
    public ToDo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getFileWriteString() {
        return "T" + super.getFileWriteString();
    }

    @Override
    public boolean isToday(LocalDate date) {
        // Always returns true as we cannot assuming that the deadline is same as the provided date
        return false;
    }


}
