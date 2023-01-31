package duke.task;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

public abstract class Task {

    protected final String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static Task getTaskFromString(String task) {
        String[] taskParts = task.split("~");
        String taskType = taskParts[0];
        String marked = taskParts[1];
        String description = taskParts[2];

        Task answer = null;

        if (taskType.equals("T")) {
            answer = new ToDo(description);

        } else if (taskType.equals("D")) {
            answer = new Deadline(description, LocalDateTime.parse(taskParts[3]));

        } else if (taskType.equals("E")) {
            answer = new Event(description,
                    LocalDateTime.parse(taskParts[3]),
                    LocalDateTime.parse(taskParts[4]));

        }

        if (answer != null && marked.equals("X")) {
            answer.mark();

        }

        return answer;

    }

    public static String getDateTimeString(LocalDateTime dateTime) {
        DateTimeFormatter formatToPrint = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return dateTime.format(formatToPrint);

    }

    public String getDescription() {
        return this.description;
    }

    public void mark() {
        this.isDone = true;
    }
    
    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String mark = this.isDone ? "X" : " ";
        return "[" + mark + "] " + this.description;
    }

    public abstract String getFileRepresentation();

}
