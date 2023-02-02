package fideline.task;

import java.util.ArrayList;

import fideline.exception.CorruptedDataFileException;

/**
 * Manager of new and existing tasks. Handles creating, deletion and
 * status change of all tasks.
 *
 * @author Fun Leon
 */


// rewrite taskManager to also store the tasks.
// has to parse and read storage at the start using parser
// maybe rewrite the command with the mark input

public class TaskManager {
    private ArrayList<Task> taskList;
    public TaskManager(String storedData) throws CorruptedDataFileException {
        taskList = new ArrayList<Task>();
        String[] storedTaskString = storedData.split("\\r?\\n|\\r");
        for (int i = 0; i < storedTaskString.length; i++) {
            String taskString = storedTaskString[i];
            String[] taskComponent = taskString.split("\\|");
            if (taskComponent[0].equals("T")) {
                addTodo(taskComponent[2]);
            } else if (taskComponent[0].equals("D")) {
                addDeadline(taskComponent[2], taskComponent[3]);
            } else if (taskComponent[0].equals("E")) {
                addEvent(taskComponent[2], taskComponent[3], taskComponent[4]);
            } else {
                throw new CorruptedDataFileException();
            }
            if (taskComponent[1].equals("X")) {
                markTask(i + 1);
            }
        }
    }

    public TaskManager() {
        taskList = new ArrayList<Task>();
    }

    public String addTodo(String taskName) {
        Todo newTodo = new Todo(taskName);
        taskList.add(newTodo);
        return newTodo.toString();
    }

    public String addDeadline(String taskName, String deadlineTiming) {
        Deadline newDeadline = new Deadline(taskName, deadlineTiming);
        taskList.add(newDeadline);
        return newDeadline.toString();
    }

    public String addEvent(String taskName, String startTime, String endTime) {
        Event newEvent = new Event(taskName, startTime, endTime);
        taskList.add(newEvent);
        return newEvent.toString();
    }

    // checks if task exists at given index
    public boolean checkTask(int taskNumber) {
        return taskNumber <= taskList.size();
    }

    // get the name of the task at a given index
    public String getTaskString(int taskNumber) {
        return taskList.get(taskNumber - 1).toString();
    }

    // changes Task at given index to marked.
    public String markTask(int taskNumber) {
        taskList.get(taskNumber - 1).mark();
        return taskList.get(taskNumber - 1).toString();
    }

    // changes Task at given index to unmarked.
    public String unmarkTask(int taskNumber) {
        taskList.get(taskNumber - 1).unmark();
        return taskList.get(taskNumber - 1).toString();
    }

    // delete Task at given index.
    public String deleteTask(int taskNumber) {
        String deleted = taskList.get(taskNumber - 1).toString();
        taskList.remove(taskNumber - 1);
        return deleted;
    }


    // formats and returns the list of tasks
    public String getTaskListString() {
        String listString = "";
        for (int i = 1; i <= taskList.size(); i++) {
            listString += "\n" + i + ". "
                    + getTaskString(i);
        }
        return listString;
    }

    public int getTaskCount() {
        return taskList.size();
    }
}
