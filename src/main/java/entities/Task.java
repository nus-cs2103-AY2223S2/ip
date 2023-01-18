package entities;

import enums.TaskType;
import exceptions.DukeException;

import java.util.regex.Matcher;

abstract public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public abstract SerializableTask serialize();

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markTask() {
        isDone = true;
        System.out.println("Nice! I've marked this task as done:\n [X] "  + description);
    }

    public void unmarkTask() {
        isDone = false;
        System.out.println("OK, I've marked this task as not done yet:\n [ ] " + description);
    }

    public static void processTask(Matcher matcher, TaskType type) throws DukeException {
        if (matcher.find()) {
            String description = matcher.group(2);
            Task task;
            switch (type) {
                case TODO:
                    task = new Todo(description);
                    break;
                case EVENT:
                    String from = matcher.group(3);
                    String to = matcher.group(4);
                    task = new Event(description, from, to);
                    break;
                case DEADLINE:
                    String by = matcher.group(3);
                    task = new Deadline(description, by);
                    break;
                default:
                    task = null;
            }
            if (task != null) TaskList.addTask(task);
        } else {
            throw type.getErr();
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
