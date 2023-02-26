package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads task list from file.
     *
     * @return List of tasks loaded from file.
     * @throws FileNotFoundException If file does not exist.
     */
    public List<Task> load() throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();

        // check if file exists
        File f = new File(filePath);

        if (f.exists()) {
            Scanner s = new Scanner(f);

            while (s.hasNextLine()) {
                Task task = null;
                String currTask = s.nextLine();
                String[] taskSplit = currTask.split(" \\| ");

                if (taskSplit[0].equals("T")) {
                    task = new Todo(taskSplit[2]);
                } else if (taskSplit[0].equals("D")) {
                    task = new Deadline(taskSplit[2], taskSplit[3]);
                } else {
                    String[] eventTime = taskSplit[3].split(" - ");
                    task = new Event(taskSplit[2], eventTime[0], eventTime[1]);
                }

                if (Integer.parseInt(taskSplit[1]) == 1) {
                    String markDateString = taskSplit[taskSplit.length - 1];

                    task.setDone(true,
                            LocalDateTime.parse(markDateString,
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                }

                tasks.add(task);
            }
        }

        return tasks;
    }

    /**
     * Saves current list of tasks to file.
     *
     * @param taskList List of tasks to be saved to file.
     * @throws IOException If I/O error occurs.
     */
    public void save(List<Task> taskList) throws IOException {
        // check if directory exists
        File dir = new File("./data");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // check if file exists
        File f = new File(filePath);

        if (!f.isFile()) {
            f.createNewFile();
        }

        PrintWriter output = new PrintWriter(filePath);

        for (Task task : taskList) {
            if (task instanceof Todo) {
                output.printf("T | %d | %s | %s%n", task.getStatusIconInt(), task.getDescription(), task.getMarkDate());
            } else if (task instanceof Deadline) {
                output.printf("D | %d | %s | %s | %s%n",
                        task.getStatusIconInt(),
                        task.getDescription(),
                        ((Deadline) task).getBy(),
                        task.getMarkDate());
            } else if (task instanceof Event) {
                output.printf("E | %d | %s | %s - %s%n | %s%n",
                        task.getStatusIconInt(),
                        task.getDescription(),
                        ((Event) task).getFrom(),
                        ((Event) task).getTo(),
                        task.getMarkDate());
            } else {
                assert false : task;
            }
        }

        output.close();
    }
}
