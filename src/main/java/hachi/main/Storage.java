package hachi.main;

import hachi.tasks.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Deals with saving tasks in the file and loading tasks from the file.
 */
public class Storage {
    private File filePath;

    /**
     * Storage constructor.
     *
     * @param filePath The relative path to the file containing saved tasks.
     */
    public Storage(String filePath) {
        this.filePath = new File(filePath);
    }

    /**
     * Saves the TaskList into a text file.
     *
     * @param tasks TaskList that contains tasks.
     */
    public void saveTaskList(TaskList tasks) throws HachiExceptions{
        try {
            FileWriter writer = new FileWriter(this.filePath);
            writer.write(tasks.getTaskList());
            writer.close();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Loads the TaskList from the text file if the file exists,
     * or return a new TaskList and creates a new file
     *
     * @return List of stored tasks.
     */
    public ArrayList<Task> loadTaskList() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(this.filePath);
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                tasks.add(Parser.parseSaved(nextLine));
            }
           scanner.close();
        } catch (FileNotFoundException e1) {
            filePath.getParentFile().mkdirs();
            filePath.createNewFile();

            FileWriter writer = new FileWriter(filePath);
            writer.write("  hachi finds the following items saved in your list ");
            writer.close();
        } finally {
            return tasks;
        }
    }


}
