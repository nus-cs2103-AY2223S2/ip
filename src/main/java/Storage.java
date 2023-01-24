import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filepath;
    private Path path;
    private Scanner scanner;
    private final String DELIMITER = "\\|";

    public Storage(String filepath) {
        Path path = Paths.get(filepath);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.path = path;
        try {
            this.scanner = new Scanner(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.filepath = filepath;
    }

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

    public void mark(int index) {
        try {
            replaceText(index, "0", "1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unmark(int index) {
        try {
            replaceText(index, "1", "0");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void replaceText(int index, String target, String replacement) throws IOException {
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
