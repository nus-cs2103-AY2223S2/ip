package smartduke;

import smartduke.task.Deadline;
import smartduke.task.Event;
import smartduke.task.Task;
import smartduke.task.ToDo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.FileWriter;

public class TaskList {
    /**
     * The list of tasks added by the user and currently recorded by SmartDuke.
     */
    private ArrayList<Task> tasks;

    public TaskList() throws DukeException  {
        this.load();
    }

    public int getNoOfTasks() {
        return this.tasks.size();
    }

    /**
     * Add the given task to list.
     * @param task the given task
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Delete the task with the given task number from the list.
     * @param taskNo the given task number
     * @throws DukeException If there is no such task in the list with the given task number.
     * @return The deleted task.
     */
    public Task delete(int taskNo) throws DukeException {
        try {
            return this.tasks.remove(taskNo - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry you have provided an invalid task number...");
        }
    }

    /**
     * Get the list of tasks currently recorded.
     * @return The list of tasks in the form of an iterator.
     */
    public Iterator<Task> list() {
        return this.tasks.iterator();
    }

    /**
     * Marks the task with the given task number as done.
     * @param taskNo the given task number
     * @throws DukeException If there is no such task in the list with the given task number.
     * @return The marked task.
     */
    public Task mark(int taskNo) throws DukeException {
        try {
            Task task = this.tasks.get(taskNo - 1);
            task.markDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry you have provided an invalid task number...");
        }
    }

    /**
     * Marks the task with the given task number as not done.
     * @param taskNo the given task number
     * @throws DukeException If there is no such task in the list with the given task number.
     * @return The unmarked task.
     */
    public Task unmark(int taskNo) throws DukeException {
        try {
            Task task = this.tasks.get(taskNo - 1);
            task.markNotDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry you have provided an invalid task number...");
        }
    }

    /**
     * Loads the saved tasks from local file into the list.
     * @throws DukeException If the directory that stores the tasks data does not exist.
     */
    private void load() throws DukeException {
        try {
            /* Reset the taskList */
            this.tasks = new ArrayList<>();

            Path path = Paths.get(".", "data", "smartduke.txt");
            File tasksData = path.toFile();
            if (!tasksData.exists()) {
                tasksData.createNewFile();
            }

            Scanner fileReader = new Scanner(tasksData);
            while (fileReader.hasNextLine()) {
                try {
                    String taskData = fileReader.nextLine();
                    String[] parsedTaskData = taskData.split(" \\| ");
                    String taskSymbol = parsedTaskData[0];
                    boolean isTaskDone = parsedTaskData[1].equals("1");
                    String taskDescription = parsedTaskData[2];

                    /* Add the task to taskList */
                    Task task = null;
                    switch (taskSymbol) {
                        case "T":
                            task = new ToDo(taskDescription);
                            break;
                        case "D":
                            String by = parsedTaskData[3];
                            task = new Deadline(taskDescription, by);
                            break;
                        case "E":
                            String from = parsedTaskData[3];
                            String to = parsedTaskData[4];
                            task = new Event(taskDescription, from, to);
                            break;
                        default:
                            throw new DukeException("Task is not recorded in a valid format...");
                    }
                    if (isTaskDone) task.markDone();
                    this.add(task);
                } catch (DukeException e) {
                    System.err.println(e.getMessage());
                }
            }
            fileReader.close();
        } catch (IOException e) {
            throw new DukeException("The directory that stores the tasks data does not exist...");
        }
    }

    /**
     * Loads the current tasks from the list into local file.
     * @throws DukeException If the directory that stores the tasks data does not exist.
     */
    public void save() throws DukeException {
        try {
            Path path = Paths.get(".", "data", "smartduke.txt");
            File tasksData = path.toFile();
            tasksData.createNewFile();

            FileWriter fileWriter = new FileWriter(tasksData);

            for (int i = 0; i < this.tasks.size(); i++) {
                Task task = this.tasks.get(i);
                fileWriter.write(task.getSavedFormat());
                fileWriter.write('\n');
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("The directory that stores the tasks data does not exist...");
        }
    }
}
