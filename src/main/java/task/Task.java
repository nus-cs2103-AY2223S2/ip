package task;

import exception.MissingParameterException;

import java.util.Scanner;

public abstract class Task {

    public abstract String serialize();

    private boolean isDone;
    private final String description;

    public Task(String description) throws MissingParameterException {
        if (description == null || description.isBlank()) {
            throw new MissingParameterException("Missing description", "A task description is needed.");
        }

        this.isDone = false;
        this.description = description;
    }

    public Task(String description, boolean isDone) throws MissingParameterException {
        this(description);
        this.isDone = isDone;
    }

    public static Task deserialize(String serial) {
        if (serial == null || serial.isBlank()) return null;

        Scanner scanner = new Scanner(serial).useDelimiter("\\s*/\\s*");
        String type = "";
        boolean isDone = false;
        String description = null;
        String deadline = null;
        String fromDateTime = null;
        String toDateTime = null;

        if (scanner.hasNext()) type = scanner.next();
        if (scanner.hasNextBoolean()) isDone = scanner.nextBoolean();
        if (scanner.hasNext()) description = scanner.next();

        try {
            switch (type) {
                case "T":
                    return new ToDoTask(description, isDone);
                case "D":
                    if (scanner.hasNext()) deadline = scanner.next();
                    return new DeadlineTask(description, deadline, isDone);
                case "E":
                    if (scanner.hasNext()) fromDateTime = scanner.next();
                    if (scanner.hasNext()) toDateTime = scanner.next();
                    return new EventTask(description, fromDateTime, toDateTime, isDone);
                default:
                    return null;
            }
        } catch (MissingParameterException e) {
            return null;
        }
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.description);
    }
}
