import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> taskList;
    TaskManager() {
        // init list to store tasks
        this.taskList = new ArrayList<Task>();
    }

    public void addTodo(String taskName) {
        Todo newTodo = new Todo(taskName);
        this.taskList.add(newTodo);
    }

    public void addDeadline(String taskName, String deadlineTiming) {
        Deadline newDeadline = new Deadline(taskName, deadlineTiming);
        this.taskList.add(newDeadline);
    }

    public void addEvent(String taskName, String startTime, String endTime) {
        Event newEvent = new Event(taskName, startTime, endTime);
        this.taskList.add(newEvent);
    }

    // checks if task exists at given index
    public boolean checkTask(int taskNumber) {
        return taskNumber <= taskList.size();
    }

    // get the name of the task at a given index
    public String getTaskString(int taskNumber) {
        return this.taskList.get(taskNumber - 1).toString();
    }

    // changes Task at given index to marked.
    public void markTask(int taskNumber) {
        taskList.get(taskNumber - 1).mark();
    }

    // changes Task at given index to unmarked.
    public void unmarkTask(int taskNumber) {
        taskList.get(taskNumber - 1).unmark();
    }

    // delete Task at given index.
    public void deleteTask(int taskNumber) {
        taskList.remove(taskNumber - 1);
    }

    // formats and returns the list of tasks
    public String listTasks() {
        String listMessage = "";    // returns empty string if no tasks
        if (taskList.size() != 0) { // formats and adds each task to list
            for (int i = 1; i <= taskList.size(); i++) {
                listMessage += "\n" + i + ". "
                        + getTaskString(i);
            }
        }
    return listMessage;
    }

    public int getTaskCount() {
        return this.taskList.size();
    }
}
