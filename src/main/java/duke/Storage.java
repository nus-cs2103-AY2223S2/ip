package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents the file to store tasks the user has inputted.
 */
public class Storage {
    private static final String DEFAULT_FILEPATH = "./data/duke.txt";

    /**
     * Creates a storage file at the intended file path.
     */
    public Storage() {
    }

    /**
     * Saves user-inputted tasks into a file.
     * @param tasks
     * @throws IOException
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(DEFAULT_FILEPATH);
        StringBuilder textToAdd = new StringBuilder();
        for (Task task : tasks) {
            textToAdd.append(task).append("\n");
        }
        fw.write(String.valueOf(textToAdd));
        fw.close();
    }

    /**
     * Retrieves tasks previously saved in a file for the user.
     * @return ArrayList of Tasks that were saved previously
     * @throws IOException
     * @throws DukeException
     */
    public ArrayList<Task> retrieveTasks() throws IOException, DukeException {
        File f = new File(DEFAULT_FILEPATH);
        f.getParentFile().mkdirs();
        if (!f.exists()) {
            f.createNewFile();
        }
        Scanner scanner = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] inputs = line.split("]", 3);
            String taskType = inputs[0];
            String status = inputs[1];
            boolean isDone = status.equals("[X");
            String rest = inputs[2];

            if (taskType.equals("[T")) {
                // then it is a Todo
                Task t = new Todo(rest.trim());
                if (isDone) {
                    t.mark();
                }
                tasks.add(t);
            } else if (taskType.equals("[D")) {
                // then it is a Deadline
                String[] restStrings = rest.split("by:", 2);
                String description = restStrings[0].replaceAll("\\(", "").trim();
                String by = restStrings[1].replaceAll("\\)", "").trim();
                Task t = new Deadline(description, by);
                if (isDone) {
                    t.mark();
                }
                tasks.add(t);
            } else {
                // then it is an Event
                String[] restStrings = rest.split("from:", 2);
                String description = restStrings[0].replaceAll("\\(", "").trim();
                String[] duration = restStrings[1].split("to:", 2);
                String from = duration[0].trim();
                String to = duration[1].replaceAll("\\)", "").trim();
                Task t = new Event(description, from, to);
                if (isDone) {
                    t.mark();
                }
                tasks.add(t);
            }
        }
        return tasks;
    }
}
