import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskTracker {
    protected ArrayList<Task> tasks = new ArrayList<Task>();

    public int GetNumTasks() {
        return tasks.size();
    }

    public Task GetTask(int index) throws TaskNotFoundException {
        if (tasks.size() < index + 1) {
            throw new TaskNotFoundException("Task " + index + " not found!");
        }

        return tasks.get(index);
    }

    public Task AddTodo(String taskDescription) {
        Task t = new Task(taskDescription);
        tasks.add(t);
        return t;
    }

    public Task AddDeadline(String taskDescription, LocalDateTime endDate) {
        Task t = new Deadline(taskDescription, endDate);
        tasks.add(t);
        return t;
    }

    public Task AddEvent(String taskDescription, LocalDateTime startDate, LocalDateTime endDate) {
        Task t = new Event(taskDescription, startDate, endDate);
        tasks.add(t);
        return t;
    }

    public Task DeleteTask(int index) throws TaskNotFoundException {
        Task toDelete = GetTask(index);
        tasks.remove(index);
        return toDelete;
    }

    public Task MarkTask(int index) throws TaskNotFoundException {
        return MarkUnmarkTask(index, true);
    }

    public Task UnmarkTask(int index) throws TaskNotFoundException {
        return MarkUnmarkTask(index, false);
    }

    public Task MarkUnmarkTask(int index, boolean isMarked) throws TaskNotFoundException {
        Task task = GetTask(index);
        if (isMarked) {
            task.mark();
        } else {
            task.unmark();
        }
        return task;
    }

    public String ListTasks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < GetNumTasks(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }
}
