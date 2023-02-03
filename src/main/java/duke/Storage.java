package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.exception.InvalidTaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Storage {
    private String directoryPath;
    private String filePath;
    Storage(String directoryPath, String filePath) {
        this.directoryPath = directoryPath;
        this.filePath = filePath;
    }

    TaskList load() throws DukeException, FileNotFoundException {
        if (!Files.exists(Path.of(directoryPath))) {
            new File(directoryPath).mkdirs();
            new File(filePath);
            throw new DukeException("File not found");
        }
        if (!Files.exists(Path.of(filePath))) {
            new File(filePath);
            throw new DukeException("File not found");
        }
        return parseFile(new File(filePath));
    }

    TaskList parseFile(File file) throws DukeException, FileNotFoundException {
        Scanner sc = new Scanner(file);
        TaskList tasks = new TaskList();
        while (sc.hasNextLine()) {
            String data = sc.nextLine().trim();
            tasks.addTask(parseLine(data));
        }
        return tasks;
    }

    Task parseLine(String data) throws InvalidTaskException {
        String[] taskData = data.split("\\|");
        String taskType = taskData[0].trim();
        switch (taskType) {
        case "T":
            return new ToDo(taskData[2].trim(), taskData[1].trim().equals("X"));
        case "D":
            return new Deadline(taskData[2].trim(), taskData[1].trim().equals("X"),
                    Parser.parseDateTime(taskData[3].trim().substring(4)));
        case "E":
            return new Event(taskData[2].trim(), taskData[1].trim().equals("X"),
                    Parser.parseDateTime(taskData[3].trim().substring(6)),
                    Parser.parseDateTime(taskData[4].trim().substring(4)));
        default:
            throw new InvalidTaskException();
        }
    }

    void saveTasksInFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(tasks.getSavedListOfTasks());
        fw.close();
    }
}
