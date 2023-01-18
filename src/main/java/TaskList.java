import java.util.HashMap;

public class TaskList {
    private HashMap<Integer, Task> taskHashMap;
    private int taskCount;

    public TaskList() {
        this.taskCount = 0;
        this.taskHashMap = new HashMap<>();
    }

    public int getCount() {
        return taskCount;
    }

    private String addTaskMessage(Task task) {
        return "Got it. I've added this task:\n     " + task + "\nNow you have " + this.taskCount + " tasks in the list";
    }

    //==== OVERLOADED METHOD addTask =====
    public String addTask(String str) {
        taskCount++;
        Task newTask = new ToDo(str, taskCount);
        this.taskHashMap.put(taskCount, newTask);
        return addTaskMessage(newTask);
    }
    public String addTask(String task, String deadline) {
        taskCount++;
        Task newTask = new Deadline(task, deadline, taskCount);
        this.taskHashMap.put(taskCount, newTask);
        return addTaskMessage(newTask);
    }

    public String addTask(String task, String from, String to) {
        taskCount++;
        Task newTask = new Event(task, from, to, taskCount);
        this.taskHashMap.put(taskCount, newTask);
        return addTaskMessage(newTask);
    }
    //======================================

    public String markTask(int taskNumber) throws DukeException {
        try {
            Task task = this.taskHashMap.get(taskNumber);
            task.markDone();
            return "Nice! I've marked this task as done: \n" + task;
        } catch (NullPointerException e) {
            throw new DukeException("No task with given task number of " + taskNumber);
        }
    }

    public String unmarkTask(int taskNumber) throws DukeException {
        try {
            Task task = this.taskHashMap.get(taskNumber);
            task.unmarkDone();
            return "OK, I've marked this task as not done yet: \n" + task;
        } catch (NullPointerException e) {
            throw new DukeException("No task with given task number of " + taskNumber);
    }
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
