package Storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

/**
 * This class is used to represent the Storage, which will load and store the database from the local file.
 */
public class Storage {
    private String filePath;
    private File file;

    /**
     * Constructor for the Storage.
     * @param filePath The filepath for the database.
     * @throws IOException Throws if there is an I/O error.
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.file = new File(filePath);
        if (!this.file.exists()) {
            new File("data").mkdir();
            this.file.createNewFile();
        }
    }

    /**
     * Load the database from the local file.
     * @return The database
     * @throws IOException Throws if there is an I/O error.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> db = new ArrayList<Task>(100);
        BufferedReader brFile = new BufferedReader(new FileReader(this.filePath));
        String input;
        while ((input = brFile.readLine()) != null) {
            String[] temp = input.split(" \\| ");
            Task task;
            if (temp[0].equals("T")) {
                task = new ToDo(temp[2]);
            } else if (temp[0].equals("D")) {
                task = new Deadline(temp[2], temp[3]);
            } else {
                task = new Event(temp[2], temp[3], temp[4]);
            }

            if (temp[1].equals("X")) {
                task.setDone();
            }
            db.add(task);
        }
        brFile.close();
        return db;
    }

    /**
     * Store the database back into the local file.
     * @param tasks The tasks to be stored.
     * @throws IOException Throws if there is an I/O error.
     */
    public void store(TaskList tasks) throws IOException {
        ArrayList<Task> db = tasks.getDb();
        StringBuilder sb = new StringBuilder();
        for (Task task: db) {
            if (task instanceof ToDo) {
                sb.append("T");
            } else if (task instanceof Deadline) {
                sb.append("D");
            } else if (task instanceof Event) {
                sb.append("E");
            }

            sb.append(" | ").append(task.getStatusIcon()).append(" | ").append(task.getDescription());

            if (task instanceof Deadline) {
                Deadline temp = (Deadline) task;
                sb.append(" | ").append(temp.getBy());
            } else if (task instanceof Event) {
                Event temp = (Event) task;
                sb.append(" | ").append(temp.getFrom()).append(" | ").append(temp.getTo());
            }

            sb.append("\n");
        }
        FileWriter fw = new FileWriter(this.file, false);
        fw.write(sb.toString());
        fw.close();
    }
}
