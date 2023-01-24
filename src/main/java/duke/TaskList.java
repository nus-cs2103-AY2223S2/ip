package duke;

import exceptions.DukeException;
import exceptions.InvalidDataFileException;
import exceptions.TaskNotFoundException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.util.ArrayList;

public class TaskList {
    // taskList is 1 indexed
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<String[]> dataFileTasks) throws DukeException {
        this.taskList = new ArrayList<>();
        Task toAdd;
        for (String[] fileCommand : dataFileTasks) {
            switch (fileCommand[0]) {
            case "T":
                toAdd = new ToDo(fileCommand[2]);
                break;
            case "D":
                toAdd = new Deadline(fileCommand[2], fileCommand[3], true);
                break;
            case "E":
                toAdd = new Event(fileCommand[2], fileCommand[3], fileCommand[4], true);
                break;
            default:
                throw new InvalidDataFileException();
            }
            if (fileCommand[1].equals("X")) {
                toAdd.setDone(true);
            }

            this.addTask(toAdd);
        }
    }

    public Task getTask(int taskNumber) throws TaskNotFoundException {
        if (taskNumber - 1 >= getLength()) {
            throw new TaskNotFoundException();
        }
        return taskList.get(taskNumber - 1);
    }

    public int getLength() {
        return taskList.size();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void deleteTask(int taskNumber) throws TaskNotFoundException {
        if (taskNumber - 1 >= getLength()) {
            throw new TaskNotFoundException();
        }
        this.taskList.remove(taskNumber - 1);
    }

    public void setDone(int taskNumber, boolean done) throws TaskNotFoundException {
        if (taskNumber - 1 >= getLength() || taskNumber < 0) {
            throw new TaskNotFoundException();
        }
        taskList.get(taskNumber - 1).setDone(done);
    }

    public String describeLength() {
        return "Now you have " + this.getLength() + " tasks in the list.";
    }

    // For storing task list in memory
    public String toEncodedString() {
        if (getLength() == 0) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        String encodedTask;
        for (int i = 0; i < getLength(); i++) {
            encodedTask = taskList.get(i).toEncodedString();
            result.append(i + 1).append(".").append(encodedTask).append("\n");
        }
        if (result.length() > 0) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }

    public boolean isEmpty() {
        return taskList.size() == 0;
    }

    @Override
    public String toString() {
        if (getLength() == 0) {
            return "No tasks found.";
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < getLength(); i++) {
            result.append(i + 1).append(".").append(taskList.get(i)).append("\n");
        }
        if (result.length() > 0) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }
}
