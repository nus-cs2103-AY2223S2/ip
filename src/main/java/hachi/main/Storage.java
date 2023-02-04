package hachi.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.stream.Stream;

/**
 * Deals with saving tasks in the file and loading tasks from the file.
 */
public class Storage {
    private String filePath;
    static String separator = "‿୨♡୧‿︵‿︵︵‿︵‿୨♡୧‿︵‿︵︵‿︵‿୨♡୧‿";

    /**
     * Storage constructor.
     *
     * @param filePath The relative path to the file containing saved tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the TaskList into a text file.
     *
     * @param tl TaskList that contains tasks.
     */
    public void saveTaskList(TaskList tl) {
        try {
            FileWriter writer = new FileWriter(filePath);
            String msg = "   your to-do list: ";
            for (int i = 0; i < tl.size(); i++) {
                msg += "\n   " + tl.get(i).toString();
            }
            writer.write(msg);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the TaskList from the text file if the file exists,
     * or return a new TaskList and creates a new file
     *
     * @return List of stored tasks.
     */
    public TaskList loadTaskList() {
        File file = new File(this.filePath);
        if (file.exists()) {
            TaskList tl = new TaskList();

            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                Stream<String> content = reader.lines();
                content.forEach(s -> System.out.println(s));
                System.out.println("\n" + separator);
            } catch (Exception e) {
                System.out.println("file empty");
            }
            return tl;
        } else {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();

                FileWriter writer = new FileWriter(file);
                writer.write("   You do not have a to-do list yet!\n Create one by adding your hachi.hachi.tasks here!");
                writer.close();
                System.out.println("   Sorry i have encountered an error..");
            } catch (IOException e2) {
                System.out.println("   Sorry i have encountered an error..");
            }
            return new TaskList();
        }
    }
}
