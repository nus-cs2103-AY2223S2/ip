package hachi.main;

import hachi.tasks.Task;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

            String content = "  hachi finds the following items saved in your list ";
            content = content.concat(tasks.getStoredString());

            writer.write(content);
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
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            Stream<String> content = reader.lines();
            content.forEach(s -> addToList(s, tasks));

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
    private void addToList(String text, ArrayList<Task> tasks) {
        Task task = Parser.parseSaved(text);
        if (task != null) {
            tasks.add(task);
        }
    }

}
