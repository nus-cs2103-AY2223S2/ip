package duke.storage;

import duke.enums.TaskTypes;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class manages persistent storage of the list
 * of tasks the user has.
 */
public class TaskStorage implements Storage{

    private String filepath;
    private Path path;
    private Scanner scanner;
    private final String DELIMITER = "\\|";

    public TaskStorage(String filepath) {
        Path path = Paths.get(filepath);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        assert Files.exists(path);
        this.path = path;
        try {
            this.scanner = new Scanner(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.filepath = filepath;
    }

    /**
     * Parses through the text file containing the user's list
     * of tasks. The tasks are then created and added to an arraylist
     * accordingly before returning the arrayList. When called by the
     * constructor of the Tasklist class, allows the Tasklist to be
     * loaded with the user's existing tasks.
     *
     * @return An arraylist containing the existing tasks in the user's
     * list of tasks.
     * @see duke.tasklist.Tasklist
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] task = line.split(DELIMITER);
            String type = task[0];
            Task t;
            switch (type) {
            case "T":
                t = new Todo(task[2]);
                break;
            case "D":
                t = new Deadline(task[2], LocalDate.parse(task[3]));
                break;
            case "E":
                t = new Event(task[2], LocalDate.parse(task[3]), LocalDate.parse(task[4]));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
            }
            if (task[1].equals("1")) {
                t.markAsDone();
            }
            tasks.add(t);
        }
        return tasks;
    }

    /**
     * Adds a task into the text document for persistent storage.
     *
     * @param t The task to be added.
     * @param type The type of the task added.
     */
    public void addTask(Task t, TaskTypes type) {
        String line;
        switch (type) {
        case TODO:
            line = "T|0|" + t.getName() + "\n";
            break;
        case DEADLINE:
            Deadline deadline = (Deadline) t;
            line = "D|0|" + t.getName() + "|" + deadline.getDeadline() + "\n";
            break;
        case EVENT:
            Event event = (Event) t;
            line = "E|0|" + t.getName() + "|" + event.getStartDate()
                    + "|" + event.getEndDate() + "\n";
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + type);
        }
        try {
            Files.write(this.path, line.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a task from the text document that is used for
     * persistent storage.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        String newContent = "";
        int count = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (count != index) {
                newContent = newContent + line + System.lineSeparator();
            }
            count++;
        }
        try {
            Files.write(this.path, newContent.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Marks a task in the storage document as done.
     *
     * @param index The index of the task to be marked.
     */
    public void mark(int index) {
        try {
            replaceText(index, "0", "1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Marks a task in the storage document as not done.
     *
     * @param index The index of the task to be unmarked.
     */
    public void unmark(int index) {
        try {
            replaceText(index, "1", "0");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void replaceText(int index, String target, String replacement)
            throws IOException {
        String newContent = "";
        int count = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (count == index) {
                line = line.replaceFirst(target, replacement);
            }
            newContent = newContent + line + System.lineSeparator();
            count++;
        }
        Files.write(this.path, newContent.getBytes());
    }
}
