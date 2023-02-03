package seedu.duke;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Storage {

    private static final File save = new File("./data/duke.txt");

    public static boolean saveExists() {
        return save.exists();
    }

    /**
     * Creates a new save file
     */
    public static void createSave() {
        try {
            save.createNewFile();
        } catch (IOException e) {
            System.out.println("Unable to create new save file: " + e);
        }
    }

    /**
     * Creates a new TaskList based on an existing save
     * @param ls The TaskList to be written to
     */
    public static void loadSave(TaskList ls) {
        BufferedReader r = null;
        try {
            r = new BufferedReader(new FileReader(save));
        } catch (FileNotFoundException e) {
            System.out.println("Save file cannot be found");
        }
        String keyword;
        String task;
        try {
            task = r.readLine();
            while (task != null) {
                keyword = task.split(" ")[0];
                switch (keyword) {
                case ("todo"): {
                    ls.addToDo(task);
                    break;
                }
                case ("deadline"): {
                    ls.addDeadline(task);
                    break;
                }
                case ("event"): {
                    ls.addEvent(task);
                    break;
                }
                default:
                }
                task = r.readLine();
            }
        } catch (IOException e) {
            System.out.println(e);
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    /**
     * Saves the TaskList into a save file
     * @param ls The TaskList to be saved
     * @throws IOException When the save file cannot be written to
     */
    public static void saveList(TaskList ls) throws IOException {
        ArrayList<Task> tasks = ls.getList();
        BufferedWriter writer = new BufferedWriter(new FileWriter("./data/duke.txt"));
        for (Task task : tasks) {
            writer.write(task.getBreakdown() + "\n");
        }
        writer.close();
    }
}
