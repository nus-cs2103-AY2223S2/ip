package duke.functions;

import duke.ToDoList;

import duke.exceptions.LoadDukeException;
import duke.exceptions.SaveDukeException;

import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.ToDoTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * A class that can hold a specified directory path with respect
 * to the home directory of the user.
 * It can then save or load the state of the ToDoList to and from
 * the specified directory path respectively.
 */
public class Storage {
    private Path path;

    public Storage(String pathStr) {
        String home = System.getProperty("user.home");
        this.path = Paths.get(home, pathStr);
    }

    /**
     * Creates the given directory if it does not exist.
     *
     * @param dir The directory path to be created if it does not exist.
     * @throws IOException If the given directory path does not exist
     *                     even after creating it in this method.
     */
    public void createDirectory(Path dir) throws IOException {
        if (!Files.exists(dir)) {
            createDirectory(dir.getParent());
        }
        Files.createDirectories(dir);
    }

    /**
     * Returns an instance of a ToDoList object that contains the specific tasks
     * in their correct state based on the given path stored in the Storage object.
     *
     * @throws LoadDukeException If an error occurred while loading the contents of
     *                           the file from the path stored in this Storage object.
     */
    public ToDoList load() throws LoadDukeException {
        try {
            if (!Files.exists(path)) {
                createDirectory(path.getParent());
                Files.createFile(path);
            }
            File f = new File(path.toString());
            ToDoList list = new ToDoList();
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String[] inputs = sc.nextLine().split(Pattern.quote("/+/"));
                String command = inputs[0];
                Task task;
                switch (command) {
                case "TODO":
                    task = new ToDoTask(inputs[2]);
                    if (inputs[1].equals("DONE")) {
                        task.markDone();
                    }
                    list.add(task);
                    break;
                case "DEADLINE":
                    task = new DeadlineTask(inputs[2], inputs[3]);
                    if (inputs[1].equals("DONE")) {
                        task.markDone();
                    }
                    list.add(task);
                    break;
                case "EVENT":
                    task = new EventTask(inputs[2], inputs[3], inputs[4]);
                    if (inputs[1].equals("DONE")) {
                        task.markDone();
                    }
                    list.add(task);
                    break;
                }
            }
            return list;
        } catch (Exception e) {
            throw new LoadDukeException();
        }
    }

    /**
     * Saves the specific states of the different Task objects in the give ToDoList
     * to the file pointed to by the path stored in the Storage object.
     *
     * @param list The ToDoList that is to have its state saved.
     * @throws SaveDukeException If an error occurred while saving the contents of
     *                           the ToDoList object to the file.
     */
    public void save(ToDoList list) throws SaveDukeException {
        try {
            FileWriter fw = new FileWriter(path.toString());
            int count = list.getToDoCount();
            for (int i = 1; i <= count; i++) {
                fw.write(list.getTask(i).save());
            }
            fw.close();
        } catch (Exception e) {
            throw new SaveDukeException();
        }
    }

}
