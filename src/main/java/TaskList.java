import Tasks.DeadlineTask;
import Tasks.EventTask;
import Tasks.Task;
import Tasks.TodoTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private final List<Task> tasks;

    public TaskList(List<String> tasks) {
        this();
        for (String info : tasks) {
            loadData(info);
        }

    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    private void loadData(String data) {
        if (data.length() == 0)
            return;

        Task todo;
        switch (data.charAt(0)) {
            case 'T':
                todo = TodoTask.loadData(data);
                break;
            case 'D':
                todo = DeadlineTask.loadData(data);
                break;
            case 'E':
                todo = EventTask.loadData(data);
                break;
            default:
                throw new IllegalArgumentException();
        }

        tasks.add(todo);
    }

    public String toSaveData() {
        String output = "";
        for (Task nextTask : tasks) {
            output += nextTask.toSaveData();
            output += "\n";
        }
        return output;
    }

    public String getTaskInfo(int index) {
        return tasks.get(index).toString();
    }

    public boolean isValidIndex(int index) {
        return tasks.size() > index;
    }

    public Task removeTask(int toRemove) {

        Task removedTask = tasks.get(toRemove);
        tasks.remove(toRemove);
        return removedTask;
    }

    public int numTasks() {
        return tasks.size();
    }

    public boolean getTaskState(int index) {
        return tasks.get(index).getCompletionStatus();
    }

    public void toggleState(int index) {
        tasks.get(index).toggleState();
    }

    public void addTask(Task toAdd) {
        tasks.add(toAdd);
    }

    @Override
    public String toString() {
        String result = "";
        int i = 0;
        for (Task entry : tasks) {
            i += 1;
            result += ((i == 1 ? "" : "\n") + i + "." + entry);

        }
        return result;
    }

    public Task convertToTask(Tasktype type, String[] arguments) throws DateTimeParseException, UnimplementedTaskTypeException {
        switch (type) {
            case TODO:
                return new TodoTask(arguments[0]);
            case EVENT:
                return new EventTask(arguments[0], LocalDateTime.parse(arguments[1]), LocalDateTime.parse(arguments[2]));
            case DEADLINE:
                return new DeadlineTask(arguments[0], LocalDateTime.parse(arguments[1]));
            default:
                throw new UnimplementedTaskTypeException();
        }

    }

    public void removeAllTasks() {
        tasks.clear();
    }

    public enum Tasktype {
        TODO,
        EVENT,
        DEADLINE
    }
}
