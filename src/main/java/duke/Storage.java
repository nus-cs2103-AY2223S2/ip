package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

/**
 * This class deals with data saving and loading
 *
 * @author He Shuimei
 * @version 0
 */
public class Storage {
    static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
    private final String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Checks for the data folder and save.txt
     * Create folder/file if not exist
     *
     * @throws IOException not able to create file/folder
     */
    public void createFile() throws IOException {
        File f = new File(this.filePath);
        File d = new File("data");

        if (!d.exists()) {
            System.out.println("Data directory does not exist\nCreating new data directory...");
            if (d.mkdir()) {
                System.out.println("Successfully created new data directory");
            }
        }

        if (!f.isFile()) {
            System.out.println("Save file does not exist!\nCreating new save file...");
            if (f.createNewFile()) {
                System.out.println("Successfully created new file at " + this.filePath);
            }
        }

        String archivePath = "data\\archive.txt";
        File a = new File(archivePath);
        if(!a.isFile()) {
            System.out.println("Archive file does not exist!\nCreating new archive file...");
            if (a.createNewFile()) {
                System.out.println("Successfully created new file at " + archivePath);
            }
        }
    }

    /**
     * Reads tasks from save file and loads it into memory
     *
     * @return ArrayList tasks of tasks read from save file
     * @throws IOException error with I/O
     */
    public ArrayList<Task> readFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        if (!new File(this.filePath).isFile() || !new File("data\\archive.txt").isFile()) {
            createFile();
        }

        // creating the buffered reader to read the file
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(this.filePath)));

        try {
            String curr;
            while ((curr = br.readLine()) != null) {
                boolean done;

                String[] currArr = curr.split("\\|");
                switch(currArr[0]) {
                    case ("T"):
                        done = currArr[1].equals("1");
                        tasks.add(new ToDo(currArr[2], done));
                        break;
                    case ("D"):
                        done = currArr[1].equals("1");
                        LocalDateTime by = LocalDateTime.parse(currArr[3], DATE_TIME_FORMATTER);
                        tasks.add(new Deadline(currArr[2], done, by));
                        break;
                    case ("E"):
                        done = currArr[1].equals("1");
                        LocalDateTime from = LocalDateTime.parse(currArr[3], DATE_TIME_FORMATTER);
                        LocalDateTime to = LocalDateTime.parse(currArr[4], DATE_TIME_FORMATTER);
                        tasks.add(new Event(currArr[2], done, from, to));
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error occurred reading save file: " + e);
        }

        return tasks;
    }

    /**
     * Writes current data to save file
     *
     * @param tasks task list
     */
    public void writeFile(ArrayList<Task> tasks) {
        System.out.println("Saving your task list...");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.filePath))) {
            for (Task t : tasks) {
                int done;
                if (t instanceof ToDo) {
                    done = t.isDone() ? 1 : 0;
                    bw.write("T|" + done + "|" + t.getDesc());
                } else if (t instanceof Deadline) {
                    done = t.isDone() ? 1 : 0;
                    bw.write("D|" + done + "|" + t.getDesc() + "|" + ((Deadline) t).getDeadlineDay());
                } else if (t instanceof Event) {
                    done = t.isDone() ? 1 : 0;
                    bw.write("E|" + done + "|" + t.getDesc() + "|"
                            + ((Event) t).getFrom() + "|" + ((Event) t).getTo());
                }
                bw.write("\n");
            }
        } catch (Exception e) {
            System.out.println("Error occurred writing to save file" + e);
        }
    }

    /**
     * Writes data to an archive file
     */
    public void writeArchive(ArrayList<Task> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data\\archive.txt"))) {
            for (Task t : tasks) {
                int done;
                if (t instanceof ToDo) {
                    done = t.isDone() ? 1 : 0;
                    bw.write("T|" + done + "|" + t.getDesc());
                } else if (t instanceof Deadline) {
                    done = t.isDone() ? 1 : 0;
                    bw.write("D|" + done + "|" + t.getDesc() + "|" + ((Deadline) t).getDeadlineDay());
                } else if (t instanceof Event) {
                    done = t.isDone() ? 1 : 0;
                    bw.write("E|" + done + "|" + t.getDesc() + "|"
                            + ((Event) t).getFrom() + "|" + ((Event) t).getTo());
                }
                bw.write("\n");
            }
        } catch (Exception e) {
            System.out.println("Error occurred writing to archive file" + e);
        }
    }
}
