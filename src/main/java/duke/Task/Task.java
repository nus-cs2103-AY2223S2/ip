package duke.Task;

import java.time.LocalDateTime;

/**
 * The abstraction behind each task stored by the duke.Duke task list.
 */
public abstract class Task {
    protected String name;
    protected boolean isDone;

    /**
     * The constructor for a task object.
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * The method to convert a string of data into a task object.
     * @param data String of data to take in.
     * @return The corresponding task object.
     */
    public static Task dataToTask(String data) {
        Task task = null;
        //| is a metacharacter in regex. You'd need to escape it:
        String[] tokens = data.split("\\|");
        //remove spaces in between
        for (int i = 0; i < tokens.length; i++) {
            tokens[i] = tokens[i].trim();
        }

        switch(tokens[0]) {
            case "[T]":
                task = new ToDo(tokens[2]);
                break;
            case "[D]":
                LocalDateTime deadline = LocalDateTime.parse(tokens[3]);
                task = new Deadline(tokens[2], deadline);
                break;
            case "[E]":
                LocalDateTime from = LocalDateTime.parse(tokens[3]);
                LocalDateTime until = LocalDateTime.parse(tokens[4]);
                task = new Event(tokens[2], from, until);
                break;
        }

        if(tokens[1].equals("X")) {
            assert task != null;
            task.silentMark();
        }

        return task;
    }

    /**
     * Get the current status of the task (whether it is done).
     * If the task is done, return 'X'. Or else, return ' '.
     * @return The status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark a task as done without returning anything to the UI.
     */
    public void silentMark() {
        this.isDone = true;
    }

    /**
     * Mark a task as done, then return a confirmation message to show that the task has
     * been successfully marked.
     * @return The confirmation message.
     */
    public String markAsDone() {
        this.isDone = true;
        return "Nice! I've marked this task as done:\n"
                + String.format("[%s][X] %s", this.getTaskType(), this);
    }

    /**
     * Mark a task as undone, then return a confirmation message to show that the task has
     * been successfully unmarked.
     * @return The confirmation message.
     */
    public String markAsUndone() {
        this.isDone = false;
        return "OK, I've marked this task as not done yet:\n"
                + String.format("[%s][ ] %s", this.getTaskType(), this);
    }

    /**
     * Output a string of data which represents a task, to be stored in the data file.
     * @return The string of data representing the task.
     */
    public abstract String taskToData();

    /**
     * Output a string which represents the type of task.
     * For Deadline, return 'D'.
     * For Event, return 'E'.
     * For ToDo, return 'T'.
     * @return The type of task.
     */
    public abstract String getTaskType();

    @Override
    public String toString() {
        return this.name;
    }
}
