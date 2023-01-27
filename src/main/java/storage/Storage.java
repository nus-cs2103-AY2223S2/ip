package storage;

import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import tasks.ToDo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;

public class Storage {
    private final Path HOME_DIRECTORY = Path.of(System.getProperty("user.dir") + "/data");
    private File dataFile;
    private TaskList tasks;

    public Storage (TaskList tasks) {
        this.tasks = tasks;
    }

    public void load() throws IOException {
        try {
            if (!Files.exists(HOME_DIRECTORY)) {
                Files.createDirectories(HOME_DIRECTORY);
            }

            File file = new File(this.HOME_DIRECTORY + "/duke.txt");
            boolean isCreated = true;
            if (!file.exists()) {
                isCreated = file.createNewFile();
            }
            if (!isCreated) {
                throw new DukeException("Failed to create storage file!");
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                parseFile(input, tasks);
            }

        } catch (FileNotFoundException | DukeException e) {
            System.out.println(e);
        }
    }

    public void save() throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(this.HOME_DIRECTORY + "/duke.txt");
            for (int i = 0; i < this.tasks.size(); i++) {
                String line = tasks.get(i).toSaveFormat();
                fileWriter.write(line);
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Failed to save existing tasks...");
        }
    }
    public static void parseFile(String input, TaskList tasks) throws DukeException {
        String[] inputList = input.split(",");
        String taskType = inputList[0];
        String taskName = inputList[1];

        switch (taskType) {
            case "T":
                tasks.addTask(new ToDo(taskName));
                break;
            case "D":
                String by = inputList[2];
                LocalDateTime deadline = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                tasks.addTask(new Deadline(taskName, deadline));
                break;
            case "E":
                String from = inputList[2];
                String to = inputList[3];
                LocalDateTime startDate = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                LocalDateTime endDate = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                tasks.addTask(new Event(taskName, startDate, endDate));
                break;
        }
    }
}
