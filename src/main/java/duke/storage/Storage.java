package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import taskList.TaskList;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Storage {
    private String cwd = System.getProperty("user.dir");
    private java.nio.file.Path dataPath;
    private String dataPathString;

    public Storage(String filePath) {
        this.dataPath = java.nio.file.Paths.get(cwd, filePath.split("/"));
        this.dataPathString = dataPath.toString();
    }

    public TaskList load() {
        TaskList tasks = new TaskList();

        try {
            boolean directoryExists = java.nio.file.Files.exists(dataPath);

            if (!directoryExists) {
                try {
                    new File(this.dataPathString).createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            File dukeFile = new File(this.dataPathString);
            Scanner scanner = new Scanner(dukeFile);

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                Task task = this.deserialise(data);
                tasks.add(task);
            }

            scanner.close();
        } catch (FileNotFoundException error) {
            // Try already handles the creation of new file when it doesn't exist
        }

        return tasks;
    }

    public void save(TaskList tasks) {
        boolean directoryExists = java.nio.file.Files.exists(dataPath);

        if (!directoryExists) {
            try {
                new File(this.dataPathString).createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileWriter writerObj = new FileWriter(this.dataPathString, false);
            for (int i = 0; i < tasks.size(); i++)
                writerObj.write(this.serialise(tasks.get(i)) + "\n");
            writerObj.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String serialise(Task task) {
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

    private Task deserialise(String data) {
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
