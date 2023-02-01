package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.util.DateTimeUtils;

/**
 * File storage implementation.
 */
public class FileStorage implements Storage {
    private final String directoryPath;
    private final String fileName;

    /**
     * Initialize storage using file.
     *
     * @param directoryPath The directory of where the file is stored.
     * @param fileName The name of the file.
     */
    public FileStorage(String directoryPath, String fileName) {
        this.directoryPath = directoryPath;
        this.fileName = fileName;
    }

    /**
     * Loads saved tasks from file.
     *
     * @return List of tasks saved from file.
     */
    @Override
    public List<Task> load() {
        List<Task> savedTasks = new ArrayList<>();

        File saveDataFile = new File(String.format("%s/%s", directoryPath, fileName));

        try {
            Scanner fileScanner = new Scanner(saveDataFile);

            while (fileScanner.hasNextLine()) {
                String[] lineTokens = fileScanner.nextLine().split(";");
                String taskType = lineTokens[0];
                boolean isDone = Integer.parseInt(lineTokens[1]) == 1;
                String description = lineTokens[2];

                if (taskType.equals("T")) {
                    ToDo todo = new ToDo(description);
                    todo.setIsDone(isDone);

                    savedTasks.add(todo);
                } else if (taskType.equals("D")) {
                    String by = lineTokens[3];

                    LocalDateTime byDateTime = LocalDateTime.parse(by, DateTimeUtils.DATE_TIME_FORMAT_INPUT);

                    Deadline deadline = new Deadline(description, byDateTime);
                    deadline.setIsDone(isDone);

                    savedTasks.add(deadline);
                } else if (taskType.equals("E")) {
                    String from = lineTokens[3];
                    String to = lineTokens[4];

                    LocalDateTime fromDateTime = LocalDateTime.parse(from, DateTimeUtils.DATE_TIME_FORMAT_INPUT);
                    LocalDateTime toDateTime = LocalDateTime.parse(to, DateTimeUtils.DATE_TIME_FORMAT_INPUT);

                    Event event = new Event(description, fromDateTime, toDateTime);
                    event.setIsDone(isDone);

                    savedTasks.add(event);
                }
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            File dir = new File(directoryPath);
            dir.mkdirs();
        }

        return savedTasks;
    }

    /**
     * Saves tasks to file.
     *
     * @param tasks Tasks to be saved.
     * @throws DukeException If I/O errors are encountered.
     */
    @Override
    public void save(List<Task> tasks) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(String.format("%s/%s", directoryPath, fileName));
            for (Task task : tasks) {
                switch (task.getTaskType()) {
                case TODO:
                    ToDo todo = (ToDo) task;
                    fileWriter.write(String.format("T;%d;%s\n", todo.isDone() ? 1 : 0, todo.getDescription()));

                    break;
                case DEADLINE:
                    Deadline deadline = (Deadline) task;
                    fileWriter.write(String.format("D;%d;%s;%s\n", deadline.isDone() ? 1 : 0, deadline.getDescription(),
                            deadline.getBy().format(DateTimeUtils.DATE_TIME_FORMAT_INPUT)));

                    break;
                case EVENT:
                    Event event = (Event) task;
                    fileWriter.write(String.format("E;%d;%s;%s;%s\n", event.isDone() ? 1 : 0, event.getDescription(),
                            event.getFrom().format(DateTimeUtils.DATE_TIME_FORMAT_INPUT),
                            event.getTo().format(DateTimeUtils.DATE_TIME_FORMAT_INPUT)));

                    break;
                default:
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Failed to save to save file!");
        }
    }
}
