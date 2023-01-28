package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import dukeexception.DatabaseException;
import task.Deadline;
import task.Todo;
import task.Event;
import task.Task;

public class Database {
    // Directory path to data/duke.txt
    String cwd = System.getProperty("user.dir");
    java.nio.file.Path dataPath = java.nio.file.Paths.get(cwd, "data", "duke.txt");

    ArrayList<Task> items = new ArrayList<Task>();

    public Database() {
        this.loadFromFile();
    }

    public void list() {
        int size = this.size();

        for (int i = 0; i < size; i++)
            System.out.println((i + 1) + ". " + this.get(i));

        System.out.println();
    }

    public void add(Task item) {
        this.items.add(item);
    }

    public Task get(int id) {
        return this.items.get(id);
    }

    public Task delete(int id) throws DatabaseException {
        if (id >= this.size()) {
            throw new DatabaseException("☹ OOPS!!! delete index does not exist");
        }

        Task task = this.items.remove(id);

        return task;
    }

    public int size() {
        return this.items.size();
    }

    public void close() {
        this.updateFile();
    }

    private void loadFromFile() {
        try {
            boolean directoryExists = java.nio.file.Files.exists(dataPath);

            if (!directoryExists) {
                try {
                    new File("./data", "duke.txt").createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            File dukeFile = new File("./data/duke.txt");
            Scanner scanner = new Scanner(dukeFile);

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                Task task = this.deserialiseTask(data);
                this.add(task);
            }

            scanner.close();
        } catch (FileNotFoundException error) {
        }
    }

    private void updateFile() {
        boolean directoryExists = java.nio.file.Files.exists(dataPath);

        if (!directoryExists) {
            try {
                new File("./data", "duke.txt").createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileWriter writerObj = new FileWriter("./data/duke.txt", false);
            for (int i = 0; i < this.size(); i++)
                writerObj.write(this.serialiseTask(this.get(i)) + "\n");
            writerObj.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String serialiseTask(Task task) {
        String data = "";
        String isDone = (task.getIsDone() ? "1" : "0");
        String description = task.getDescription();

        if (task instanceof Todo) {
            data = String.join(" | ", "T", isDone, description);
        }

        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            String by = deadline.getBy();
            data = String.join(" | ", "D", isDone, description, by);
        }

        if (task instanceof Event) {
            Event event = (Event) task;
            String from = event.getFrom();
            String to = event.getTo();
            data = String.join(" | ", "E", isDone, description, from, to);
        }

        return data;
    }

    public Task deserialiseTask(String data) {
        String[] params = data.split("\\|");
        String taskType = params[0].strip();
        Boolean isCompleted = params[1].contains("1");
        String description = params[2].strip();
        Task task = null;

        if (taskType.equals("T")) {
            task = new Todo(description);
        }

        if (taskType.equals("D")) {
            String by = params[3].strip();
            task = new Deadline(description, by);

        }

        if (taskType.equals("E")) {
            String from = params[3].strip();
            String to = params[4].strip();
            task = new Event(description, from, to);

        }

        if (isCompleted && task != null) {
            task.markAsDone();
        }

        return task;
    }
}
