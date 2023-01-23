package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Task {

    public static final String DT_INPUT_FORMAT = "dd-MM-yyyy";
    public static final String DT_PRINT_FORMAT = "d MMM yyyy";
    public static final DateTimeFormatter DT_INPUT_FORMATTER = DateTimeFormatter.ofPattern(DT_INPUT_FORMAT);
    public static final DateTimeFormatter DT_PRINT_FORMATTER = DateTimeFormatter.ofPattern(DT_PRINT_FORMAT);

    protected boolean isDone;
    protected String name;

    public Task(String name, boolean isDone) {
        this.isDone = isDone;
        this.name = name;
    }

    public static LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString, Task.DT_INPUT_FORMATTER);
    }

    public static String formatDate(LocalDate date) {
        return date.format(DT_PRINT_FORMATTER);
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return (isDone ? "x" : " ");
    }

    protected abstract String getTaskType();

    public String serialize() {
        String serialized = String.format("%s|%s|%s", this.getTaskType(), this.isDone ? 1 : 0, this.name);
        return serialized;
    }

    public boolean hasKeyword(String keyword) {
        return this.name.contains(keyword);
    }

    @Override
    public String toString() {
        String s = String.format("[%s][%s] %s", this.getTaskType(), this.getStatusIcon(), this.name);
        return s;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj.getClass() != this.getClass()) {
            return false;
        }
        Task task = (Task) obj;
        return this.name.equals(task.name) && (this.isDone == task.isDone);
    }
}
