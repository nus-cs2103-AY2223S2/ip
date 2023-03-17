package pix.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

import pix.tasks.Deadline;
import pix.tasks.Event;
import pix.tasks.Task;
import pix.tasks.ToDo;

/**
 * MyData class to keep track of the list of tasks.
 */
public class MyData {
    /** Arraylist to keep track of all tasks. */
    private final ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Gets tasks at index.
     *
     * @param index Index of task to retrieve.
     * @return the Task object.
     */
    public Task getTaskAtIndex(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds task to the list of tasks.
     *
     * @param command Task to add to the list.
     */
    public void addTask(Task command) {
        tasks.add(command);
    }

    /**
     * Delete the task at index.
     *
     * @param index Index of task to delete.
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Marks the task at index as done.
     *
     * @param index Index to mark task as done.
     */
    public void markDone(int index) {
        this.tasks.get(index).markDone();
    }


    /**
     * Marks the task at index as undone.
     *
     * @param index Index to mark task as undone.
     */
    public void markUndone(int index) {
        this.tasks.get(index).markUndone();
    }

    /**
     * Gets the length of the list of tasks.
     *
     * @return Length of list.
     */
    public int len() {
        return this.tasks.size();
    }

    /**
     * Saves the list of tasks to a text file on local device.
     */
    public void saveToFile() {
        String homeDirectory = System.getProperty("user.dir");
        java.nio.file.Path pathToSavedTasks = java.nio.file.Paths.get(homeDirectory, "Pix.txt");
        try {
            FileWriter writer = new FileWriter(pathToSavedTasks.toFile());
            for (int i = 0; i < len(); i++) {
                writer.write(getTaskAtIndex(i).toSave() + "\r\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * If Pix.txt does not exist, create the file.
     * Load the tasks in the file into the ArrayList data.
     *
     * @throws IOException If path does not exist.
     */
    public void loadData() throws IOException {
        String homeDirectory = System.getProperty("user.dir");
        java.nio.file.Path pathToSavedTasks = java.nio.file.Paths.get(homeDirectory, "Pix.txt");
        try {
            Scanner sc = new Scanner(new File(pathToSavedTasks.toString()));
            int id = 0;
            while (sc.hasNextLine()) {
                String[] arr = sc.nextLine().split(" / ");
                String command = arr[0];
                String marked = arr[1];
                switch (command) {
                case "T":
                    ToDo todo = new ToDo(arr[2]);
                    addTask(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(arr[2], arr[3]);
                    addTask(deadline);
                    break;
                default:
                    Event event = new Event(arr[2], arr[3], arr[4]);
                    addTask(event);
                    break;
                }
                if (marked.equals("1")) {
                    markDone(id);
                }
                id++;
            }
        } catch (FileNotFoundException e) {
            Files.createFile(pathToSavedTasks);
        }
    }
}

