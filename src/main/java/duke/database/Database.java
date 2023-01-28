package database;

import java.io.File;
import java.io.FileNotFoundException;
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
        this.loadFromTaskFile();
    }

    public void list() {
        int size = this.size();

        for (int i = 0; i < size; i++)
            System.out.println((i + 1) + ". " + this.get(i));

        System.out.println();
    }

    public void add(Task item) {
        this.items.add(item);
        this.updateFile();
    }

    public Task get(int id) {
        return this.items.get(id);
    }

    public Task delete(int id) throws DatabaseException {
        if (id >= this.size()) {
            throw new DatabaseException("☹ OOPS!!! delete index does not exist");
        }

        T task = this.items.remove(id);

        return task;
    }

    public int size() {
        return this.items.size();
    }

    private void loadFromFile() {
        try {
            boolean directoryExists = java.nio.file.Files.exists(dataPath);

            if (directoryExists) {
                File dukeFile = new File("./data/duke.txt");
                Scanner scanner = new Scanner(dukeFile);

                while (scanner.hasNextLine()) {
                    String data = scanner.nextLine();
                    String[] params = data.split("\\|");
                    String taskType = params[0].strip();
                    Boolean isCompleted = params[1] == "1";
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
                        String to = params[4];
                        task = new Event(description, from, to);

                    }

                    if (task == null) {
                        continue;
                    }

                    if (isCompleted) {
                        task.markAsDone();
                    }
                }

                // TODO: Add task to database

                scanner.close();
                return;
            }
        } catch (FileNotFoundException error) {
        }

    }

    private void updateFile() {
    }
}
