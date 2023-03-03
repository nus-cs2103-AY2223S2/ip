package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Analogous to Storage suggested by the module coordinators.
 * Stores all the tasks of a Duke Object.
 */
public class TaskManagement {
    private File file;
    private File filedirectory;
    private String path;
    private String directory;

    /**
     * Constructs a TaskManagement object. By default, the file which contains
     * the tasks of the TaskManagement object will be placed in ./data/tasks.txt.
     */
    public TaskManagement() {
        this.directory = "./data/";
        this.path = this.directory + "tasks.txt";
        this.file = new File(this.path);
        this.filedirectory = new File(this.directory);
        try {
            if (!this.filedirectory.exists()) { //handle case where folder does not exist
                filedirectory.mkdirs();
            }
            if (!this.file.exists()) { //handle case where file does not exist
                this.file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Constructs a TaskManagement object.
     *
     * The file which contains the tasks of the TaskManagement object
     * will be placed in the filepath given.
     *
     * @param filepath The filepath given.
     * @throws DukeException If invalid filepath input is given.
     */
    public TaskManagement(String filepath) throws DukeException {
        if (!filepath.substring(0, 2).equals("./") || filepath.length() <= 2) {
            throw new DukeException();
        }
        String rem = filepath.substring(2);
        String[] inp = rem.split("/");
        if (inp.length != 2) {
            throw new DukeException();
        }
        this.directory = "./" + inp[0] + "/";
        this.path = filepath;
        this.file = new File(this.path);
        this.filedirectory = new File(this.directory);
        try {
            if (!this.filedirectory.exists()) { //handle case where folder does not exist
                filedirectory.mkdirs();
            }
            if (!this.file.exists()) { //handle case where file does not exist
                this.file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves the tasks of the TaskStorage object into the TaskManagement object.
     * The file will be updated.
     *
     * @param taskStorage The TaskStorage object given.
     */
    public void save(TaskStorage taskStorage) {
        try {
            FileWriter fw = new FileWriter(this.path);
            for (int i = 0; i < taskStorage.noTasks(); i++) {
                fw.write(taskStorage.getTask(i).original());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads the data from the output file into the taskStorage object.
     *
     * @return a list of tasks stored.
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        try {
            Path path = Paths.get("./data/");
            Files.createDirectories(path);
            File file = new File(this.path);
            file.createNewFile();

            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                char action = data.charAt(1);
                assert action == 'T' || action == 'E' || action == 'D' : "Tasks not stored properly!";
                Task task;
                int taskNameStart = 7;
                int removeFromLength = 7;
                int dateLength = 10;
                int byLength = 5;
                if (action == 'T') {
                    task = new Todo(data.substring(taskNameStart));

                } else if (action == 'E') {
                    int symbol = data.indexOf("(");
                    int symbolClose = data.indexOf(")");
                    String first = data.substring(symbol + removeFromLength, symbol + removeFromLength + dateLength);
                    String second = data.substring(symbolClose - dateLength, symbolClose);
                    task = new Event(data.substring(taskNameStart, symbol), first, second);

                } else if (action == 'D') {
                    int symbol = data.indexOf("(");
                    int symbolClose = data.indexOf(")");
                    String first = data.substring(taskNameStart, symbol);
                    String second = data.substring(symbol + byLength, symbolClose);
                    task = new Deadline(first, second);

                } else {
                    reader.close();
                    throw new DukeException("Task loaded is formatted incorrectly");
                }
                char isDone = data.charAt(4);
                assert isDone == 'X'
                        || isDone == ' ' : "The state of the task is stored wrongly";
                tasks.add(task);
                if (isDone == 'X') {
                    task.markAsDone();
                }
            }
            reader.close();
            return tasks;
        } catch (IOException | DukeException e) {
            return tasks;
        }
    }

}
