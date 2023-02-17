package duke.main;

import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class stores and loads tasks from the specified saved file
 */
public class Storage {
    private final File file;

    /**
     * Creates file if it does not exist
     *
     * @param filepath the specified path of file location
     * @throws DukeException when file cannot be created
     * @throws IOException when file creation has an error
     */
    public Storage(String filepath) throws DukeException, IOException {
        String[] splits = filepath.split("/");
        File dir = new File(splits[0]);

        if (!dir.exists()) {
            if (!dir.mkdir()) {
                throw new DukeException("Error while creating ./data folder.");
            }
        }
        File dataFile = new File(filepath);
        if (!dataFile.exists()) {
            if (!dataFile.createNewFile()) {
                throw new DukeException("Error while creating duke.txt file.");
            }
        }
        this.file = dataFile;
    }

    /**
     * updates the specified saved file based on the tasklist passed into it
     *
     * @param tasklist the tasklist containing the updated tasks
     * @throws IOException when the filewriter has an error
     */
    public void update(Tasklist tasklist) throws IOException {
        StringBuilder strings = new StringBuilder();
        FileWriter fw = new FileWriter(this.file);

        for (Task curr : tasklist.getTasks()) {
            if (curr instanceof Todo) {
                String done = curr.isDone() ? "1 " : "0 ";
                String toAppend = "T " + "| " + done + "| " + curr.getDescription() + "\n";
                strings.append(toAppend);
            } else if (curr instanceof Deadline) {
                String done = curr.isDone() ? "1 " : "0 ";
                LocalDateTime by = ((Deadline) curr).getBy();
                String toAppend = "D " + "| " + done + "| " + curr.getDescription() + " | " + by + "\n";
                strings.append(toAppend);
            } else if (curr instanceof Event) {
                String done = curr.isDone() ? "1 " : "0 ";
                LocalDateTime from = ((Event) curr).getFrom();
                LocalDateTime to = ((Event) curr).getTo();
                String toAppend = "E " + "| " + done + "| " + curr.getDescription() + " | " + from + " | " + to + "\n";
                strings.append(toAppend);
            }
        }
        fw.write(strings.toString());
        fw.close();
    }

    /**
     * loads the tasks from the specified saved file
     *
     * @return an arraylist of tasks from the saved file or creates a new arraylist if file is empty
     * @throws DukeException when data from file cannot be read
     * @throws FileNotFoundException when saved file cannot be found
     */
    public ArrayList<Task> load() throws DukeException, FileNotFoundException {
            Scanner scan = new Scanner(this.file);
            ArrayList<Task> tasks = new ArrayList<>();

            while (scan.hasNext()) {
                String[] task = scan.nextLine().split(" \\| ");
                switch (task[0]) {
                    case "T":
                        Task todo = new Todo(task[2]);

                        if (task[1].equals("1")) {
                            todo.markDone();
                        }
                        tasks.add(todo);
                        break;
                    case "D":
                        Task deadline = new Deadline(task[2], LocalDateTime.parse(task[3]));

                        if (task[1].equals("1")) {
                            deadline.markDone();
                        }
                        tasks.add(deadline);
                        break;
                    case "E":
                        Task event = new Event(task[2], LocalDateTime.parse(task[3]), LocalDateTime.parse(task[4]));

                        if (task[1].equals("1")) {
                            event.markDone();
                        }
                        tasks.add(event);
                        break;
                    default:
                        throw new DukeException("Data from file does not exist, creating new task list");
                }
            }
        return tasks;
    }
}
