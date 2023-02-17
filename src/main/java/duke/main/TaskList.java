package duke.main;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import duke.task.*;

public class TaskList {

    private List<Task> allTasks;

    TaskList() {
        this.allTasks = new ArrayList<Task>();
    }

    TaskList(List<Task> allTasks) {
        this.allTasks = allTasks;
    }

    public Task markTaskAsDone(Task oldTask, int taskIndex) {
        if (oldTask.getTaskType().equals("[T]")) {
            Todo todo = new Todo(oldTask.getTaskNumber(),
                    true, oldTask.getTask(),
                    this.allTasks.size());
            this.allTasks.set(taskIndex, todo);
            oldTask = todo;
        } else if (oldTask.getTaskType().equals("[D]")) {
            Deadline deadline = new Deadline(oldTask.getTaskNumber(),
                    true, oldTask.getTask(),
                    oldTask.getDeadline(), this.allTasks.size());
            this.allTasks.set(taskIndex, deadline);
            oldTask = deadline;
        } else if (oldTask.getTaskType().equals("[E]")) {
            Event event = new Event(oldTask.getTaskNumber(),
                    true, oldTask.getTask(),
                    oldTask.getEventStartTime(),
                    oldTask.getEventEndTime(), this.allTasks.size());
            this.allTasks.set(taskIndex, event);
            oldTask = event;
        }
        return oldTask;
    }

    public Task unmarkTaskAsUndone(Task oldTask, int taskIndex) {
        if (oldTask.getTaskType().equals("[T]")) {
            Todo todo = new Todo(oldTask.getTaskNumber(),
                    false, oldTask.getTask(),
                    this.allTasks.size());
            this.allTasks.set(taskIndex, todo);
        } else if (oldTask.getTaskType().equals("[D]")) {
            Deadline deadline = new Deadline(oldTask.getTaskNumber(),
                    false, oldTask.getTask(),
                    oldTask.getDeadline(), this.allTasks.size());
            this.allTasks.set(taskIndex, deadline);
        } else if (oldTask.getTaskType().equals("[E]")) {
            Event event = new Event(oldTask.getTaskNumber(),
                    false, oldTask.getTask(),
                    oldTask.getEventStartTime(),
                    oldTask.getEventEndTime(), this.allTasks.size());
            this.allTasks.set(taskIndex, event);
        }
        return oldTask;
    }

    public void deleteTask(int taskIndex) {
        this.allTasks.remove(taskIndex);
    }

    public void addTask(Task task) {
        this.allTasks.add(task);
    }

    public List<Task> getAllTasks() {
        return this.allTasks;
    }

    public Task getTask(int index) {
        return this.allTasks.get(index);
    }

    public int getNumberOfTask() {
        return this.allTasks.size();
    }

}
