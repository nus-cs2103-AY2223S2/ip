package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;


/**
 * Storage class.
 * Handles the storage of tasks into the user's computer.
 */
public class Storage {
    protected String filePath;
    protected String dirPath;



    /**
     * Constructor for Storage object.
     * @param filePath Filepath of where txt file is write into.
     * @param dirPath Directory path of where txt file is put into.
     */
    public Storage(String filePath, String dirPath) {
        assert !filePath.isEmpty() : "filePath should not be empty";
        assert !dirPath.isEmpty() : "dirPath should not be empty";
        this.filePath = filePath;
        this.dirPath = dirPath;

        // to initiate locations
        File theDir = new File(dirPath);
        if (!theDir.exists()) {
            theDir.mkdirs();
        }

        try {
            createFile(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates file if it doesn't exist.
     * @param filePath Path where file is made.
     * @throws IOException If file cannot be accessed.
     */
    private static void createFile(String filePath) throws IOException {
        File file = new File(filePath);

        file.createNewFile();
    }

    /**
     * Loads tasks into an ArrayList from the filepath.
     * @return ArrayList where tasks are loaded into.
     * @throws FileNotFoundException If the filepath doesn't exist.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> arrayList = new ArrayList<>();
        while (s.hasNext()) {
            String[] parts = s.nextLine().split("/");
            if (parts[0].equals("T")) {
                Task t = new Todo(parts[2]);
                if (parts[1].equals("1")) {
                    t.markAsDone();
                }
                arrayList.add(t);
            }
            if (parts[0].equals("D")) {
                Task t = new Deadline(parts[2], LocalDateTime.parse(parts[3]));
                if (parts[1].equals("1")) {
                    t.markAsDone();
                }
                arrayList.add(t);
            }
            if (parts[0].equals("E")) {
                Task t = new Event(parts[2], LocalDateTime.parse(parts[3]),
                        LocalDateTime.parse(parts[4]));
                if (parts[1].equals("1")) {
                    t.markAsDone();
                }
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    /**
     * Stores tasks into filepath from the task list.
     * @param tasks Task list to be written from.
     * @throws IOException If file cannot be accessed.
     */
    public void store(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (int i = 0; i < tasks.getLength(); i++) {
            Task t = tasks.get(i);
            if (t instanceof Todo) {
                fw.write("T/" + t.getStatusIconBinary() + "/" + t.getDescription());
            }
            if (t instanceof Deadline) {
                fw.write("D/" + t.getStatusIconBinary() + "/" + t.getDescription() + "/" + ((Deadline) t).getBy());
            }
            if (t instanceof Event) {
                fw.write("E/" + t.getStatusIconBinary() + "/" + t.getDescription()
                        + "/" + ((Event) t).getFrom() + "/" + ((Event) t).getTo());
            }
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

}
