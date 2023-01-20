import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> taskList;
    TaskManager() {
        // init list to store tasks
        taskList = new ArrayList<Task>();
    }
    public void addTask(String taskName) {
        Task newTask = new Task(taskName);
        taskList.add(newTask);
    }

    // get the name of the task at a given index
    public String getTaskName(int taskNumber) {
        return taskList.get(taskNumber - 1).getName();
    }

    // changes Task at given index to marked. returns a boolean stating if process is successful
    public boolean markTask(int taskNumber) {
        if (taskNumber > taskList.size()) {
            return false; // taskNumber is outside existing range of tasks
        } else {
            taskList.get(taskNumber - 1).mark();
            return true; // success marking
        }
    }

    // changes Task at given index to unmarked. returns a boolean stating if process is successful
    public boolean unmarkTask(int taskNumber) {
        if (taskNumber > taskList.size()) {
            return false; // taskNumber is outside existing range of tasks
        } else {
            taskList.get(taskNumber - 1).unmark();
            return true; // success unmarking
        }
    }

    // formats and returns the list of tasks
    public String listTasks() {
        if (taskList.size() == 0) { // returns empty string if no tasks
            return "";
        } else {
            String listMessage = "";
            for (int i = 0; i < taskList.size(); i++) {
                Task curTask = taskList.get(i);
                listMessage += "\n" + (i + 1)
                        + ".[" + curTask.getStatusIcon()
                        + "] " + curTask.getName();
            }
            return listMessage;
        }
    }
}
