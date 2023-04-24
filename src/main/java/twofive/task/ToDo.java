package twofive.task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a Todo task with a given description.
 */
public class ToDo extends Task {
    public ToDo(String taskDescription) {
        super(taskDescription);
    }

    public ToDo(String taskDescription, String tag) {
        super(taskDescription, tag);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     * The type of task is also included.
     */
    @Override
    public String getFileWriteString() {
        return "T" + super.getFileWriteString();
    }

    /**
     * {@inheritDoc}
     * Always returns false for a TodoTask as we cannot assuming that the deadline
     * is the same as the provided date.
     *
     * @return false.
     */
    @Override
    public boolean isToday(LocalDate date) {
        return false;
    }

    @Override
    public ArrayList<String> getTaskDetails() {
        ArrayList<String> taskDetails = new ArrayList<>();
        taskDetails.add("ToDo");
        taskDetails.add(super.getTaskStatus());
        taskDetails.add(super.getTaskDescription());
        taskDetails.add(super.getTag());
        return taskDetails;
    }
}
