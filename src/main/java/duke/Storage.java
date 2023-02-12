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
import duke.tag.EmptyTag;
import duke.tag.Tag;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * A class that deals with loading stored tasks and saving
 * tasks ito a local file.
 */
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
            throw new FileNotFoundException();
        } else if (!Files.exists(Path.of(filePath))) {
            new File(filePath);
            throw new FileNotFoundException();
        }
        assert Files.exists(Path.of(filePath)) : "File path does not exist";
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

    Task parseLine(String data) throws DukeException {
        String[] taskData = data.split("\\|");
        String taskType = taskData[0].trim();
        Tag tag;
        if (taskData[taskData.length - 1].contains("tag:")) {
            tag = new Tag(taskData[taskData.length - 1].trim().substring(5));
        } else {
            tag = new EmptyTag();
        }
        switch (taskType) {
        case "T":
            return new ToDo(taskData[2].trim(), taskData[1].trim().equals("X"), tag);
        case "D":
            return new Deadline(taskData[2].trim(), taskData[1].trim().equals("X"),
                    Parser.parseDateTime(taskData[3].trim().substring(4)), tag);
        case "E":
            return new Event(taskData[2].trim(), taskData[1].trim().equals("X"),
                    Parser.parseDateTime(taskData[3].trim().substring(6)),
                    Parser.parseDateTime(taskData[4].trim().substring(4)), tag);
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
