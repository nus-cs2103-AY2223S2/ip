package duke.functions;

import duke.ToDoList;
import duke.exceptions.DukeException;
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
    Path path;

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
     * Returns an instance of a ToDoList object that contains the specific tasks in their correct state
     * based on the given path stored in the Storage object.
     *
     * @return A ToDoList object with the Task objects in it based on the information given in the
     *         file that the path in the Storage object points to.
     */
    public ToDoList load() {
        try {
            if (!Files.exists(path)) {
                createDirectory(path.getParent());
                Files.createFile(path);
            }
            File f = new File(path.toString());
            ToDoList ls = new ToDoList();
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String[] input = sc.nextLine().split(Pattern.quote("/+/"));
                String command = input[0];
                Task task;
                switch (command) {
                case "TODO":
                    task = new ToDoTask(input[2]);
                    if (input[1].equals("DONE")) {
                        task.markDone();
                    }
                    ls.add(task);
                    break;
                case "DEADLINE":
                    task = new DeadlineTask(input[2], input[3]);
                    if (input[1].equals("DONE")) {
                        task.markDone();
                    }
                    ls.add(task);
                    break;
                case "EVENT":
                    task = new EventTask(input[2], input[3], input[4]);
                    if (input[1].equals("DONE")) {
                        task.markDone();
                    }
                    ls.add(task);
                    break;
                }
            }
            return ls;
        } catch (Exception e) {
            return new ToDoList();
        }
    }

    /**
     * Saves the specific states of the different Task objects in the give ToDoList
     * to the file pointed to by the path stored in the Storage object.
     *
     * @param ls The ToDoList that is to have its state saved.
     */
    public void save(ToDoList ls)  {
        try {
            FileWriter fw = new FileWriter(path.toString());
            int count = ls.getToDoCount();
            for (int i = 1; i <= count; i++) {
                fw.write(ls.getTask(i).save());
            }
            fw.close();
        } catch (Exception e) {
        }
    }

}
