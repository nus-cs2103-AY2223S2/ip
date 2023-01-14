import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Task addTodo(String task) {
        Task newTask = new Todo(task);
        this.taskList.add(newTask);
        return newTask;
    }

    public Task addDeadline(String task, String endTime) {
        Task newTask = new Deadline(task, new EndTime(endTime));
        this.taskList.add(newTask);
        return newTask;
    }

    public Task addEvent(String task, String from, String to) {
        Task newTask = new Event(task, new Duration(from, to));
        this.taskList.add(newTask);
        return newTask;
    }

    public Task markTaskAtIndex(int taskIndex) throws IndexOutOfBoundsException{
        return this.taskList.get(taskIndex-1).mark();
    }

    public Task unmarkTaskAtIndex(int taskIndex) throws IndexOutOfBoundsException {
        return this.taskList.get(taskIndex-1).unmark();
    }

    public Task deleteTaskAtIndex(int taskIndex) throws IndexOutOfBoundsException {
        return this.taskList.remove(taskIndex-1);
    }

    public int getTotalTasks() {
        return taskList.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int totalTasks = this.getTotalTasks();
        for(int i = 0; i < totalTasks; i ++ ) {
            Task t = taskList.get(i);
            sb.append(Utilities.space()).append(i+1).append(".").append(t.toString()).append("\n");
        }
        if(sb.length()!=0) {
            sb.delete(sb.length()-1, sb.length());
        }
        return sb.toString();
    }
}
