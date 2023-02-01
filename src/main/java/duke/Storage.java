package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Storage {
    // private final SaveManager saveManager;
    private String filePath;

    public Storage(String filePath) {
        // this.saveManager = new SaveManager(filePath);
        this.filePath = filePath;
    }

    public void changeFilePath(String filePath) {
        // this.saveManager.changeFilePath(filePath);
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasklistFromFile() throws DukeException {
        /*
        boolean isLoaded = saveManager.deserialize();
        if (!isLoaded) {
            return new ArrayList<>();
        }

        ArrayList<Task> taskList = saveManager.load("ArrayList<Task>");
        return taskList;
        */
        ArrayList<Task> tasks = new ArrayList<>();
        Path path = Paths.get(filePath);
        if (Files.notExists(path)) {
            return tasks;
        }

        List<String> lines;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("Something went wrong reading the file.");
            return tasks;
        }

        // TODO: Exception Handling
        for (String line : lines) {
            List<String> args = List.of(line.split("\\|"));
            String type = args.get(0);
            String isDone = args.get(1);
            String description = args.get(2);

            Task task;
            switch (type) {
                case "todo":
                    task = new Todo(description);
                    break;
                case "deadline":
                    Date by = Parser.parseDate(args.get(3));
                    task = new Deadline(description, by);
                    break;
                case "event":
                    Date from = Parser.parseDate(args.get(3));
                    Date to = Parser.parseDate(args.get(4));
                    task = new Event(description, from, to);
                    break;
                default:
                    System.out.println("Something went wrong");
                    throw new DukeException();
            }

            if (isDone.equals("true")) {
                task.markAsDone();
            }
            tasks.add(task);
        }
        return tasks;
    }

    public void saveTasklistToFile(TaskList tasks) {
        /*
        ArrayList<Task> taskList = tasks.getTaskList();
        saveManager.save("ArrayList<Task>", taskList);
        try {
            saveManager.serialize();
        } catch (DukeException ignored) {
            System.out.println("Something went wrong");
        }

         */
        Path path = Paths.get(filePath);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.getSize(); i++) {
            sb.append(tasks.get(i).getFileRepresentation()).append("\n");
        }

        try {
            Files.write(path, sb.toString().getBytes());
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
}
