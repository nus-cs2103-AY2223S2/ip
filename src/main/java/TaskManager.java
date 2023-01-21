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

    // get the name of the task at a given index
    public String getTaskString(int taskNumber) {
        return this.taskList.get(taskNumber - 1).toString();
    }

    // changes Task at given index to marked. returns a boolean stating if process is successful
    public boolean markTask(int taskNumber) {
        if (taskNumber > taskList.size()) {
            return false; // taskNumber is outside existing range of tasks
        } else {
            taskList.get(taskNumber - 1).mark();
            return true; // successful marking
        }
    }

    // changes Task at given index to unmarked. returns a boolean stating if process is successful
    public boolean unmarkTask(int taskNumber) {
        if (taskNumber > taskList.size()) {
            return false; // taskNumber is outside existing range of tasks
        } else {
            taskList.get(taskNumber - 1).unmark();
            return true; // successful unmarking
        }
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
