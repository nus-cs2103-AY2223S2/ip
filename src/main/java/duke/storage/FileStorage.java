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
     * Initializes storage using file.
     *
     * @param directoryPath The directory of where the file is stored.
     * @param fileName The name of the file.
     */
    public FileStorage(String directoryPath, String fileName) {
        this.directoryPath = directoryPath;
        this.fileName = fileName;
    }

    private ToDo getToDoFromLineTokens(String[] lineTokens) {
        boolean isDone = Integer.parseInt(lineTokens[1]) == 1;
        String description = lineTokens[2];
        String place = lineTokens[3];

        ToDo todo = new ToDo(description, place);
        todo.setIsDone(isDone);

        return todo;
    }

    private Deadline getDeadlineFromLineTokens(String[] lineTokens) {
        boolean isDone = Integer.parseInt(lineTokens[1]) == 1;
        String description = lineTokens[2];
        String place = lineTokens[3];
        String by = lineTokens[4];

        LocalDateTime byDateTime = LocalDateTime.parse(by, DateTimeUtils.DATE_TIME_FORMAT_INPUT);

        Deadline deadline = new Deadline(description, place, byDateTime);
        deadline.setIsDone(isDone);

        return deadline;
    }

    private Event getEventFromLineTokens(String[] lineTokens) {
        boolean isDone = Integer.parseInt(lineTokens[1]) == 1;
        String description = lineTokens[2];
        String place = lineTokens[3];
        String from = lineTokens[4];
        String to = lineTokens[5];

        LocalDateTime fromDateTime = LocalDateTime.parse(from, DateTimeUtils.DATE_TIME_FORMAT_INPUT);
        LocalDateTime toDateTime = LocalDateTime.parse(to, DateTimeUtils.DATE_TIME_FORMAT_INPUT);

        Event event = new Event(description, place, fromDateTime, toDateTime);
        event.setIsDone(isDone);

        return event;
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

                if (taskType.equals("T")) {
                    savedTasks.add(getToDoFromLineTokens(lineTokens));
                } else if (taskType.equals("D")) {
                    savedTasks.add(getDeadlineFromLineTokens(lineTokens));
                } else if (taskType.equals("E")) {
                    savedTasks.add(getEventFromLineTokens(lineTokens));
                }
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            File dir = new File(directoryPath);
            dir.mkdirs();
        }

        return savedTasks;
    }

    private void writeToDoToFile(ToDo todo, FileWriter fileWriter) throws IOException {
        fileWriter.write(String.format("T;%d;%s;%s\n", todo.isDone() ? 1 : 0,
                todo.getDescription(), todo.getPlace()));
    }

    private void writeDeadlineToFile(Deadline deadline, FileWriter fileWriter) throws IOException {
        fileWriter.write(String.format("D;%d;%s;%s;%s\n", deadline.isDone() ? 1 : 0, deadline.getDescription(),
                deadline.getPlace(),
                deadline.getBy().format(DateTimeUtils.DATE_TIME_FORMAT_INPUT)));
    }

    private void writeEventToFile(Event event, FileWriter fileWriter) throws IOException {
        fileWriter.write(String.format("E;%d;%s;%s;%s;%s\n", event.isDone() ? 1 : 0, event.getDescription(),
                event.getPlace(),
                event.getFrom().format(DateTimeUtils.DATE_TIME_FORMAT_INPUT),
                event.getTo().format(DateTimeUtils.DATE_TIME_FORMAT_INPUT)));
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
                switch (task.getTypeOfTask()) {
                case TODO:
                    writeToDoToFile((ToDo) task, fileWriter);
                    break;
                case DEADLINE:
                    writeDeadlineToFile((Deadline) task, fileWriter);
                    break;
                case EVENT:
                    writeEventToFile((Event) task, fileWriter);
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
