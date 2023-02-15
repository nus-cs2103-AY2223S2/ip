package cluck;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import cluck.exceptions.TaskIndexOutOfBoundsException;
import cluck.tasks.Deadline;
import cluck.tasks.Event;
import cluck.tasks.Task;
import cluck.tasks.ToDo;

/**
 * TaskList contains the tasks. In a list. Yea.
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskList() {
    }

    /**
     * @param taskIndex index of task in ArrayList.
     * @return task at index given if mark operation was succssful.
     * @throws TaskIndexOutOfBoundsException is thrown when the index given is out of bounds
     */
    public Task markTask(int taskIndex) throws TaskIndexOutOfBoundsException {
        try {
            Task currTask = taskList.get(taskIndex);
            currTask.mark();
            return currTask;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException();
        }
    }

    /**
     * @param taskIndex index of task in ArrayList.
     * @return task at index given if un-mark operation was successful.
     * @throws TaskIndexOutOfBoundsException
     */
    public Task unmarkTask(int taskIndex) throws TaskIndexOutOfBoundsException {
        try {
            Task currTask = taskList.get(taskIndex);
            currTask.unmark();
            return currTask;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException();
        }
    }

    /**
     * @param task task to be added.
     * @return task added
     */
    public Task addTask(Task task) {
        this.taskList.add(task);
        return task;
    }

    /**
     * @param taskIndex index of task to be deleted.
     * @return return task if deletion was successful.
     * @throws TaskIndexOutOfBoundsException if index out of bounds of ArrayList
     */
    public Task deleteTask(int taskIndex) throws TaskIndexOutOfBoundsException {
        try {
            return taskList.remove(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException();
        }
    }

    public void addSavedTask(String taskData) {
        Task task = Task.buildTask(taskData.split("\\|"));
        if (task == null) {
            return;
        }
        taskList.add(task);
    }
    public void readSave(File savedFile) {
        try {
            Scanner saveFileScanner = new Scanner(savedFile);
            while (saveFileScanner.hasNextLine()) {
                this.addSavedTask(saveFileScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves the current list of tasks into 'CluckSave.txt'.
     * This will overwrite previous saves.
     * There should be no missing directory error since readSave()
     * will create the save directory if it does not exist.
     *
     * @param saveFile save location for tasks in task list
     */
    public void writeSave(File saveFile) {
        try {
            FileWriter writer = new FileWriter(saveFile);
            for (Task t : taskList) {
                writer.write(t.makeSaveFormat());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Buh oh! An error occurred!!");
            e.printStackTrace();
        }
    }
}
