package storage;

import tasklist.TaskList;
import tasktypes.Deadline;
import tasktypes.Event;
import tasktypes.Task;
import tasktypes.ToDo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Storage {

    public static final String DEFAULT_DIRECTORY = "./data/";
    public static final String DEFAULT_FILEPATH = "./data/duke.txt";
    public File dukeFile;
    public TaskList tasks;

    public Storage() {
        File directory = new File(DEFAULT_DIRECTORY);
        boolean directoryCreated = directory.mkdir();
        if (directoryCreated) {
            System.out.println("Data folder created!");
        }

        dukeFile = new File(DEFAULT_FILEPATH);
        try {
            boolean fileCreated = dukeFile.createNewFile();
            if (fileCreated) {
                System.out.println("Data file: duke.txt created!");
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        tasks = new TaskList();
    }

    public TaskList load() {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(DEFAULT_FILEPATH));
            boolean emptyFile = true;

            for (String line : allLines) {
                emptyFile = false;
                String[] taskSplit = line.split(",,");
                String type = taskSplit[0];
                Task task = null;
                switch (type) {
                    case "T": {
                        task = new ToDo(taskSplit[2]);
                        break;
                    }
                    case "D": {
                        task = new Deadline(taskSplit[2], taskSplit[3]);
                        break;
                    }
                    case "E": {
                        task = new Event(taskSplit[2], taskSplit[3], taskSplit[4]);
                        break;
                    }
                }

                String done = taskSplit[1];
                if (done.equals("1")) {
                    assert task != null;
                    task.markDone();
                }

                tasks.loadTask(task);
            }

            if (!emptyFile) {
                tasks.printTasks();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return tasks;
    }

    public void save(TaskList tasks) {
        this.tasks = tasks;

        try {
            PrintWriter writer = new PrintWriter(dukeFile);

            System.out.println("Updating your data. Please wait..");

            int count = 0;
            for (Task task : tasks.getTasks()) {
                count++;
                String toWrite = task.getSaveFormat();
                if (count == 1) {
                    writer.println(toWrite);
                } else {
                    writer.append(toWrite).append("\n");
                }
            }
            writer.close();

            System.out.println("All changes saved successfully!");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
