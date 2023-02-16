package storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

/**
 * This class is used to represent the Storage, which will load and store the database from the local file.
 */
public class Storage {
    private String filePath;
    private File file;

    /**
     * Constructor for the Storage.
     * @param filePath The filepath for the database.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
        if (!this.file.exists()) {
            new File("data").mkdir();
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Load the database from the local file.
     * @return The database
     */
    public ArrayList<Task> load() {
        ArrayList<Task> db = new ArrayList<Task>(100);
        BufferedReader brFile;
        try {
            brFile = new BufferedReader(new FileReader(this.filePath));
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return db;
    }

    /**
     * Store the database back into the local file.
     * @param tasks The tasks to be stored.
     */
    public void store(TaskList tasks) {
        List<Task> db = tasks.getTasks();
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
                sb.append(" | ").append(formatLocalDateTime(temp.getBy()));
            } else if (task instanceof Event) {
                Event temp = (Event) task;
                sb.append(" | ").append(formatLocalDateTime(temp.getFrom()))
                    .append(" | ").append(formatLocalDateTime(temp.getUntil()));
            }

            sb.append("\n");
        }
        try {
            FileWriter fw = new FileWriter(this.file, false);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the formatted string representation of the date for storing in the database.
     * @param date The date to be formatted.
     * @return The formatted string representation of the date.
     */
    private String formatLocalDateTime(LocalDateTime date) {
        return date.getYear() + "-"
            + (date.getMonthValue() < 10 ? "0" + date.getMonthValue() : date.getMonthValue()) + "-"
            + (date.getDayOfMonth() < 10 ? "0" + date.getDayOfMonth() : date.getDayOfMonth()) + " "
            + (date.getHour() < 10 ? "0" + date.getHour() : date.getHour())
            + (date.getMinute() < 10 ? "0" + date.getMinute() : date.getMinute());
    }
}
