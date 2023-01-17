import java.util.HashMap;

public class Tasklist {
    private HashMap<Integer, Task> taskHashMap;
    private int taskCount;

    public Tasklist() {
        this.taskCount = 0;
        this.taskHashMap = new HashMap<>();
    }

    public int getCount() {
        return taskCount;
    }

    public String addTask(String str) {
        taskCount++;
        Task newTask = new Task(str, taskCount);
        this.taskHashMap.put(taskCount, newTask);
        return ("added: " + str);
    }

    public String markTask(int taskNumber) {
        Task task = this.taskHashMap.get(taskNumber);
        task.markDone();
        return "Nice! I've marked this task as done: \n" + task;
    }

    public String unmarkTask(int taskNumber) {
        Task task = this.taskHashMap.get(taskNumber);
        task.unmarkDone();
        return "OK, I've marked this task as not done yet: \n" + task;
    }

    public String listTasks() {
        String result = "";
        result += "Here are the tasks in your list:\n";
        for (int i = 1; i <= this.taskCount; i++) {
            result += String.format("%d. %s \n", i, this.taskHashMap.get(i));
        }
        return result;
    }
}
