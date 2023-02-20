package duke.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import duke.interfaces.Model;

/**
 * The `TaskModel` class is responsible for managing the list of tasks.
 * It provides methods for adding, updating and removing tasks from the list.
 * It also persists the list of tasks to a file so that it can be retrieved later.
 *
 * @author jayanth
 */

public class TaskModel implements Model {
    private static final String TASK_STORE_PATH = "./data/tasks.ser";
    private static final String DATA_DIR_PATH = "./data";
    private final ArrayList<Task> tasks;
    private final File tasksFile;

    /**
     * Model that stores the task list. It is also responsible for saving and loading tasks (on startup) from the disk.
     */
    public TaskModel() {
        File dataDir = new File(DATA_DIR_PATH);
        if (!dataDir.exists()) {
            try {
                dataDir.mkdir();
            } catch (SecurityException e) {
                throw new RuntimeException(e);
            }
        }

        this.tasksFile = new File(TASK_STORE_PATH);
        if (tasksFile.exists() && tasksFile.length() > 0) {
            try {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(tasksFile));
                Object obj = in.readObject();
                this.tasks = (ArrayList<Task>) obj;
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                tasksFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            this.tasks = new ArrayList<>();
        }
    }
    /**
     * Write the task list to the tasksFile.
     */
    private void writeToFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream(this.tasksFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(tasks);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Create a todo (a task with only a description).
     * Also writes the task list to the file.
     * @param description Description of the task.
     * @return A Task containing the description supplied.
     */
    public Task createTask(String description) {
        Task newTask = new ToDo(description);
        this.tasks.add(newTask);
        writeToFile();
        return newTask;
    }

    /**
     * Create a deadline (a task with a description and deadline).
     * Also writes the task list to the file.
     * @param description Description of the task.
     * @param deadline The deadline for the task.
     * @return A Task containing the description and deadline supplied.
     */
    public Task createTask(String description, LocalDateTime deadline) {
        Task newTask = new Deadline(description, deadline);
        this.tasks.add(newTask);
        writeToFile();
        return newTask;
    }

    /**
     * Create an event (a task with a description, start and end time).
     * Also writes the task list to the file.
     * @param description Description of the task.
     * @param startTime The start time for the event.
     * @param endTime The end time for the event.
     * @return A Task containing the description, start and end time supplied.
     */
    public Task createTask(String description, LocalDateTime startTime, LocalDateTime endTime) {
        Task newTask = new Event(description, startTime, endTime);
        this.tasks.add(newTask);
        writeToFile();
        return newTask;
    }

    /**
     * A method to return all the tasks.
     * @return a list of all the tasks.
     */
    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(this.tasks);
    }

    /**
     * A method to get deadlines and events that contain the given date and time.
     * @param time The date and time to search for.
     * @return a list of tasks containing deadlines due on the supplied time and events containing the supplied time.
     */
    public List<Task> getTasksOn(LocalDateTime time) {
        // only deadlines and events
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.isDueOn(time)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Get the task at the supplied index.
     * @param index The index of task to be returned.
     * @return The task at the supplied index.
     */
    public Task getTask(int index) {
        // the index of task to get must be positive and less than the length of the list.
        assert index >= 0 && index < tasks.size();
        return this.tasks.get(index);
    }

    public int getNumberOfTasks() {
        assert tasks != null;
        return this.tasks.size();
    }

    /**
     * Delete the supplied task object from the list of tasks.
     * @param task The task to delete.
     */
    public void deleteTask(Task task) {
        int indexToRemove = tasks.indexOf(task);
        // since the command passes a task that *should* be in the model's task list,
        // taskIndex should never be -1 (ie the task should always be in the list).
        assert indexToRemove >= 0;
        this.tasks.remove(indexToRemove);
        writeToFile();
    }

    /**
     * Marks the supplied task as done.
     * @param task the task to mark as done.
     */
    public void markTaskDone(Task task) {
        int taskIndex = tasks.indexOf(task);
        // since the command passes a task that *should* be in the model's task list,
        // taskIndex should never be -1 (ie the task should always be in the list).
        assert taskIndex >= 0;
        tasks.get(taskIndex).markTaskDone(); // handle out of bounds exception
        writeToFile();
    }

    /**
     * Marks the supplied task as undone.
     * @param task the task to mark as undone.
     */
    public void markTaskUndone(Task task) {
        int taskIndex = tasks.indexOf(task);
        // since the command passes a task that *should* be in the model's task list,
        // taskIndex should never be -1 (ie the task should always be in the list).
        assert taskIndex >= 0;
        tasks.get(taskIndex).markTaskUndone(); // handle out of bounds exception
        writeToFile();
    }

    /**
     * Find tasks containing the supplied substring in their description.
     * @param subStr The string to search for.
     */
    public List<Task> findTasks(String subStr) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.descriptionContains(subStr)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
