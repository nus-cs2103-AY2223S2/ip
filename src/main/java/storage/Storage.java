package storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import dukeexception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;


/**
 * Storage class to deal with the file.
 */
public class Storage {

    private final String filePath;

    /**
     * Class constructor.
     *
     * @param filePath The path of a file to be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        checkFileExit();
    }

    /**
     * Checks whether the file exits.
     */
    public void checkFileExit() {
        try {
            Path file = Paths.get(".", filePath);
            if (!Files.exists(file)) {
                Files.createDirectories(file.getParent());
                Files.createFile(file);
            }
        } catch (IOException e) {
            System.out.println("\t Invalid Path.\n");
            e.printStackTrace();
        }
    }

    /**
     * Reads the tasks from the file.
     *
     * @return Tasklist which is stored in the file.
     */
    public TaskList loadData() {
        ArrayList<Task> arrayList = new ArrayList<>();
        Path file = Paths.get(".", filePath);
        try {
            List<String> taskLine = Files.readAllLines(file);
            for (int i = 0; i < taskLine.size(); i++) {
                String[] content = taskLine.get(i).split(" \\| ");
                switch (content[0]) {
                case "T":
                    arrayList.add(Todo.generateTask(content));
                    break;
                case "E":
                    arrayList.add(Event.generateTask(content));
                    break;
                case "D":
                    arrayList.add(Deadline.generateTask(content));
                    break;
                default:
                    throw new DukeException("\t Invalid data!\n");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("\t File not found.\n");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("\t Invalid Path.\n");
        }
        return new TaskList(arrayList);
    }


    /**
     * Saves tasks into the file.
     *
     * @param tasks Tasks are stored in the file.
     */
    public void saveData(TaskList tasks) {
        Path file = Paths.get(".", filePath);
        ArrayList<Task> arrayList = tasks.getTasks();
        String data = "";
        try {
            for (Task task : arrayList) {
                data += task.storeTaskString() + "\n";
            }
            Files.writeString(file, data);
        } catch (IOException e) {
            System.out.println("\t Invalid Path.\n");
        }
    }

    /**
     * Load all commands from help.txt.
     *
     * @return Returns all available commands.
     */
    public String loadHelpExplanationFile() {
        InputStream helpStream = this.getClass().getResourceAsStream("/explanation/help.txt");
        String explanation = "";
        try {
            explanation = new String(helpStream.readAllBytes());
        } catch (FileNotFoundException e) {
            System.out.println("\t File not found.\n");
        } catch (IOException e) {
            System.out.println("\t Invalid Path.\n");
        }
        return explanation;
    }
}
