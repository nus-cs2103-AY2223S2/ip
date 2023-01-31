package duke.main;

import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final File file;

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
    public void update(Tasklist tasklist) throws IOException {
        StringBuilder strings = new StringBuilder();
        FileWriter fw = new FileWriter(this.file);
        for (Task curr : tasklist.getTasks()) {
            if (curr instanceof Todo) {
                strings.append("T ")
                        .append("| ")
                        .append(curr.isDone() ? "1 " : "0 ")
                        .append("| ")
                        .append(curr.getDescription())
                        .append("\n");
            } else if (curr instanceof Deadline) {
                strings.append("D ")
                        .append("| ")
                        .append(curr.isDone() ? "1 " : "0 ")
                        .append("| ")
                        .append(curr.getDescription())
                        .append(" | ")
                        .append(((Deadline) curr).getBy())
                        .append("\n");
            } else if (curr instanceof Event) {
                strings.append("E ")
                        .append("| ")
                        .append(curr.isDone() ? "1 " : "0 ")
                        .append("| ")
                        .append(curr.getDescription())
                        .append(" | ")
                        .append(((Event) curr).getFrom())
                        .append(" | ")
                        .append(((Event) curr).getTo())
                        .append("\n");
            }
        }
        fw.write(strings.toString());
        fw.close();
    }
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
