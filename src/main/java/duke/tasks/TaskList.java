package duke.tasks;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A wrapper class for a list of tasks.
 */
public class TaskList {
    /** ArrayList to store the tasks. **/
    private final ArrayList<Task> taskList;

    /**
     * A Constructor for a TaskList object.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Adds a task to the TaskList.
     * @param task
     */
    public void addTask(Task task) {
        this.taskList.add(task);
        assert this.isEmpty() : "Failed to add task";
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int index) {
        assert index < 0 : "Index is invalid";
        return this.taskList.get(index);
    }

    /**
     * Deletes a task from this TaskList based on its index.
     * @param index Index of the task in 1-based indexing format
     * @return Task that is deleted
     */
    public Task delete(int index) {
        assert index < 1 : "Index is invalid";
        return this.taskList.remove(index - 1);
    }

    /**
     * Marks a task as done in this TaskList based on its index.
     * @param index Index of the task in 1-based indexing format
     */
    public void markDone(int index) {
        assert index < 1 : "Index is invalid";
        Task task = this.taskList.get(index - 1);
        task.markAsDone();
    }

    /**
     * Marks a task as not done in this TaskList based on its index.
     * @param index Index of the task in 1-based indexing format
     */
    public void markUndone(int index) {
        assert index < 1 : "Index is invalid";
        Task task = this.taskList.get(index - 1);
        task.markAsUndone();
    }

    public boolean isEmpty() {
        return (this.size() == 0);
    }

    public TaskList find(String searchTerm) {
        List<Task> taskList = this.taskList
                .stream()
                .filter(task -> task.getName().contains(searchTerm))
                .collect(Collectors.toList());

        return new TaskList(new ArrayList<>(taskList));
    }

    public String findFreeDay() {
        LocalDateTime startingDate = LocalDateTime.now();
        while (true) {
            LocalDateTime currentDate = startingDate;
            List<Event> taskList = this.taskList
                    .stream()
                    .filter(task -> task instanceof Event)
                    .map(Event.class::cast)
                    .filter(event -> event.hasDateClash(currentDate))
                    .collect(Collectors.toList());

            if (taskList.isEmpty()) {
                if (currentDate.equals(LocalDateTime.now())) {
                    return "You are free as you have no pending events!";
                }
                return "Your closest free day is in "
                        + startingDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        + ", " + Duration.between(LocalDateTime.now(), currentDate).toDays() + " days from now!";
            }
            startingDate = startingDate.plusDays(1);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("List of Tasks: \n");
        for (int i = 0; i < taskList.size(); i++) {
            result.append((i + 1))
                    .append(". ")
                    .append(taskList.get(i).toString())
                    .append("\n");
        }
        return result.toString();
    }
}
