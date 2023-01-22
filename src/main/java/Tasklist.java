import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Scanner;

public class Tasklist {

    private ArrayList<Task> tasks;
    private Path path;
    private final String DELIMITER = "\\|";

    public Tasklist(Path path) {
        this.tasks = new ArrayList<>();
        this.path = path;
        Scanner s = null;
        try {
            s = new Scanner(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (s.hasNextLine()) {
            String line = s.nextLine();
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

    }

    public int size() {
        return this.tasks.size();
    }

    public void addTask(Task t, TaskTypes type) {
        tasks.add(t);
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

    public Task deleteTask(int index) {
        try {
            Scanner s = new Scanner(this.path);
            String newContent = "";
            int count = 0;
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (count != index) {
                    newContent = newContent + line + System.lineSeparator();
                }
                count++;
            }
            Files.write(this.path, newContent.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.tasks.remove(index);
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    private void replaceText(int index, String target, String replacement) throws IOException {
        Scanner s = new Scanner(this.path);
        String newContent = "";
        int count = 0;
        while (s.hasNextLine()) {
            String line = s.nextLine();
            if (count == index) {
                line = line.replaceFirst(target, replacement);
            }
            newContent = newContent + line + System.lineSeparator();
            count++;
        }
        Files.write(this.path, newContent.getBytes());
    }

    public boolean mark(int index) {
        if (this.tasks.get(index).markAsDone()) {
            try {
                replaceText(index, "0", "1");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public boolean unmark(int index) {
        if (this.tasks.get(index).markAsUndone()) {
            try {
                replaceText(index, "1", "0");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public void viewList() {
        if (this.tasks.isEmpty()) {
            System.out.println("\t You currently have no tasks.");
        } else {
            System.out.println("\t Here is a list of your tasks:");
            for (int i = 0; i < this.tasks.size(); i++) {
                System.out.println("\t " + String.valueOf(i+1) + "." +  this.tasks.get(i));
            }
        }

    }
}
