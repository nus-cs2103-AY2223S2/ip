package fideline.task;

import java.util.ArrayList;

import fideline.exception.CorruptedDataFileException;
import fideline.exception.DuplicateTaskException;

/**
 * Manager of new and existing tasks. Handles creating, deletion and
 * status change of all tasks.
 *
 * @author Fun Leon
 */

public class TaskManager {

    private ArrayList<Task> taskList;

    public TaskManager() {
        taskList = new ArrayList<Task>();
    }

    public TaskManager(String storedData) throws CorruptedDataFileException {
        taskList = new ArrayList<Task>();
        addDataToTaskList(storedData);
    }

    public void addDataToTaskList(String storedData) throws CorruptedDataFileException {
        String[] dataEntries = splitData(storedData);
        // if data is empty, command is completed
        if (isDataEmpty(dataEntries)) {
            return;
        }
        for (int i = 0; i < dataEntries.length; i++) {
            String taskDataEntry = dataEntries[i];
            Task newTask = getTaskFromData(taskDataEntry);
            taskList.add(newTask);
        }
    }

    public String[] splitData(String storedData) {
        String newLineRegex = "\\r?\\n|\\r";
        return storedData.split(newLineRegex);
    }

    public boolean isDataEmpty(String[] dataEntries) {
        return (dataEntries.length == 1) && (dataEntries[0].length() == 0);
    }

    public Task getTaskFromData(String taskDataEntry) throws CorruptedDataFileException {
        String[] taskComponents = getTaskComponents(taskDataEntry);
        String taskType = taskComponents[0];
        final String todo = "T";
        final String deadline = "D";
        final String event = "E";
        Task newTask;
        try {
            if (taskType.equals(todo)) {
                newTask = new Todo(taskComponents[2]);
            } else if (taskType.equals(deadline)) {
                newTask = new Deadline(taskComponents[2], taskComponents[3]);
            } else if (taskType.equals(event)) {
                newTask = new Event(taskComponents[2], taskComponents[3],
                        taskComponents[4]);
            } else {
                throw new CorruptedDataFileException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new CorruptedDataFileException();
        }

        String doneStatus = taskComponents[1];
        if (doneStatus.equals("X")) {
            newTask.mark();
        } else if (doneStatus.equals(" ")) {
            // task is unmarked by default
        } else {
            throw new CorruptedDataFileException();
        }
        return newTask;
    }

    public static String[] getTaskComponents(String taskDataEntry) {
        String deliminatorRegex = "\\|";
        return taskDataEntry.split(deliminatorRegex);
    }

    public Task getTask(int taskNum) {
        return taskList.get(taskNum - 1);
    }

    public void addTodo(String taskName) throws DuplicateTaskException {
        Todo newTodo = new Todo(taskName);
        addNewTask(newTodo);
    }


    public void addDeadline(String taskName, String deadlineTiming)
            throws DuplicateTaskException {
        Deadline newDeadline = new Deadline(taskName, deadlineTiming);
        addNewTask(newDeadline);
    }


    public void addEvent(String taskName, String startTime, String endTime)
            throws DuplicateTaskException {
        Event newEvent = new Event(taskName, startTime, endTime);
        addNewTask(newEvent);
    }

    /**
     * Adds new task to the task list. New task cannot be a duplicate of a pre-existing
     * task.
     *
     * @param task new task that is to be created.
     * @throws DuplicateTaskException Thrown if the new task created already exists in the task list.
     */
    public void addNewTask(Task task) throws DuplicateTaskException {
        if (isExistingTask(task)) {
            throw new DuplicateTaskException();
        }
        taskList.add(task);
    }

    // checks if task exists at given index
    public boolean isValidTask(int taskNumber) {
        return taskNumber <= taskList.size();
    }


    // get the name of the task at a given index
    public String getTaskString(int taskNum) {
        return getTask(taskNum).toString();
    }


    // changes Task at given index to marked.
    public void markTask(int taskNum) {
        Task t = getTask(taskNum);
        t.mark();
    }


    // changes Task at given index to unmarked.
    public void unmarkTask(int taskNum) {
        Task t = getTask(taskNum);
        t.unmark();
    }


    // delete Task at given index.
    public void deleteTask(int taskNum) {
        taskList.remove(taskNum - 1);
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

    public String findTask(String findString) {
        ArrayList<Task> list = new ArrayList<>();
        taskList.forEach(x -> {
            if (x.getDescription().contains(findString)) {
                list.add(x);
            }
        });
        String output = "";
        for (int i = 1; i <= list.size(); i++) {
            output += "\n" + i + ". "
                    + list.get(i - 1).toString();
        }
        return output;
    }

    public String getTaskStorageString(int taskNum) {
        Task task = getTask(taskNum);
        return task.getStorageString();
    }

    public boolean isExistingTask(Task task) {
        for (int i = 0; i < taskList.size(); i++) {
            Task taskToCompare = taskList.get(i);
            if (task.equals(taskToCompare)) {
                return true;
            }
        }
        return false;
    }

}
