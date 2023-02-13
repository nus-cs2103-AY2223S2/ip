package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * Finds a task that contains the searchTerm in its name.
     * @param searchTerm A keyword used to find a task.
     * @return A list of tasks that contains the searchTerm in its name.
     */
    public TaskList find(String searchTerm) {
        List<Task> taskList = this.taskList
                .stream()
                .filter(task -> task.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());

        return new TaskList(new ArrayList<>(taskList));
    }

    /**
     * Finds the closest free day in this TaskList. A free day is defined as a day
     * which has no events occurring.
     * @return A String that tells us the closest free day and the number
     *     of days from today until that day.
     */
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
                        + ", " + ChronoUnit.DAYS.between(LocalDate.now(), currentDate.toLocalDate())
                        + " day(s) from now!";
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
