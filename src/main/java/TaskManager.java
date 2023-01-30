import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class TaskManager {
    private ArrayList<Task> taskList;
    private DataHandler dataHandler;
    TaskManager() {
        // init list to store tasks
        this.taskList = new ArrayList<Task>();
        File dataDir = new File("./Fideline-Data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
        dataHandler = new DataHandler("./Fideline-Data/data.txt");
    }

    public void addTodo(String taskName) {
        Todo newTodo = new Todo(taskName);
        dataHandler.addLine(newTodo.toString());
    }

    public void addDeadline(String taskName, String deadlineTiming) {
        Deadline newDeadline = new Deadline(taskName, deadlineTiming);
        dataHandler.addLine(newDeadline.toString());
    }

    public void addEvent(String taskName, String startTime, String endTime) {
        Event newEvent = new Event(taskName, startTime, endTime);
        dataHandler.addLine(newEvent.toString());
    }

    // checks if task exists at given index
    public boolean checkTask(int taskNumber) {
        String[] allData = dataHandler.getData();
        int dataLength;
        if (allData.length == 1 && allData[0] == "") {
            dataLength = 0;
        } else {
            dataLength = allData.length;
        }
        return taskNumber <= dataLength;
    }

    // get the name of the task at a given index
    public String getTaskString(int taskNumber) {
        return dataHandler.getData()[taskNumber - 1];
    }

    // changes Task at given index to marked.
    public void markTask(int taskNumber) {
        dataHandler.editTaskStatus(taskNumber - 1, true);
    }

    // changes Task at given index to unmarked.
    public void unmarkTask(int taskNumber) {
        dataHandler.editTaskStatus(taskNumber - 1, false);
    }

    // delete Task at given index.
    public void deleteTask(int taskNumber) {
        dataHandler.deleteLine(taskNumber - 1);
    }

    // formats and returns the list of tasks
    public String listTasks() {
        String listMessage = "";    // returns empty string if no tasks
        String[] dataArr = dataHandler.getData();
        if (dataArr.length == 1 && dataArr[0] == "") {
            return listMessage;
        }
        for (int i = 1; i <= dataArr.length; i++) { // formats and adds each task to list
            listMessage += "\n" + i + ". "
                    + dataArr[i - 1];
        }
    return listMessage;
    }

    public int getTaskCount() {
        return dataHandler.getData().length;
    }
}
