package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import duke.exception.InvalidInputException;
import duke.exception.StorageFileException;
import duke.task.DukeTask;
import duke.task.TaskList;

/**
 * A Storage object that handles the saving and loading of the TaskList.
 * Writes the TaskList in an external file whenever it is updated. Load the existing
 * file when the user opens the Duke program.
 */

public class Storage {
    private final String filePath;
    private final Path folder;
    private final File storageFile;
    private final String TODO_TAG = "[T]";
    private final String DEADLINE_TAG = "[D]";
    private final String EVENT_TAG = "[E]";
    private final String IS_DONE_TAG = "[X]";
    private final String NOT_DONE_TAG = "[ ]";
    private final String STORAGE_ERROR = "OOPS!!! There's something wrong "
            + "when reading the Storage list";

    /**
     * Constructor of that takes a path of the file and specify the file for
     * storage of the given path.
     *
     * @param filePath The path of the storage file
     */
    public Storage(String filePath) {
        String rootPath = Paths.get("").toAbsolutePath().toString();
        this.filePath = Paths.get(rootPath, filePath).toString();

        Path path = Paths.get(filePath);
        int len = path.getNameCount();

        this.folder = Paths.get(rootPath, path.subpath(0, len - 1).toString());
        this.storageFile = new File(this.filePath);
    }

    /**
     * Load the TaskList from the given data file. If the file does not exist return
     * a new empty TaskList.
     *
     * @return Return the TaskList parsed from the given file
     * @throws InvalidInputException Thrown when the Storage file has unrecognized record
     * @throws StorageFileException Thrown when encountering IOException when reading the file
     */
    public TaskList load() throws InvalidInputException, StorageFileException {
        TaskList list = new TaskList();
        if (!storageFile.exists()) {
            return list;
        }

        try {
            // read file
            List<String> lines = readFile(storageFile);
            // process each line
            for (String line : lines) {
                processLine(list, line);
            }
            return list;
        } catch (FileNotFoundException e) {
            throw new StorageFileException(STORAGE_ERROR);
        }
    }

    /**
     * Reads the content of a file and returns the lines in a list.
     *
     * @param file the file to read
     * @return list of lines in the file
     * @throws FileNotFoundException if the file doesn't exist
     */
    private List<String> readFile(File file) throws FileNotFoundException {
        // Create a scanner to read the file
        Scanner sc = new Scanner(file);
        List<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine().strip());
        }
        return lines;
    }

    /**
     * Processes a line from the storage file and decodes the task information into a task object.
     * The task object is then added to the task list.
     *
     * @param list the task list to add the task object to
     * @param line the line of information to be processed
     * @throws InvalidInputException if the input is invalid
     */
    private void processLine(TaskList list, String line) throws InvalidInputException {
        String[] information = line.split("\\s\\|\\s");

        String taskTag = information[0];
        boolean isDone = information[1].equals(IS_DONE_TAG);
        storageFormatChecker(information[0], information[1]);

        // Decode the task information based on the task tag
        switch (taskTag) {
            case TODO_TAG:
                Decoder.todoDecoder(list, information[2], isDone);
                break;
            case DEADLINE_TAG:
                Decoder.deadlineDecoder(list, information[2], isDone, information[3]);
                break;
            case EVENT_TAG:
                Decoder.eventDecoder(list, information[2], isDone, information[3], information[4]);
                break;
            default:
                throw new InvalidInputException("Unrecognized task tag: " + taskTag);
        }
    }

    /**
     * Check if the input for the task tag and isDone match the expected format.
     *
     * @param tag The task tag of the task
     * @param isDone The status of the task
     */
    private void storageFormatChecker(String tag, String isDone) {
        String errorMessage = "Type tag of event should be [T], [D], or [E]";
        assert Objects.equals(tag, TODO_TAG) || Objects.equals(tag, DEADLINE_TAG) || Objects.equals(tag, EVENT_TAG)
                : errorMessage;

        errorMessage = "IsDone tag of event should be [ ], or [X]";
        assert Objects.equals(isDone, IS_DONE_TAG) || Objects.equals(isDone, NOT_DONE_TAG)
                : errorMessage;
    }


    //@@author Yufannnn-reused
    //https://nus-cs2103-ay2223s2.github.io/website/schedule/week3/topics.html#W3-4c
    //with minor modification, nice and concise function to overwrite text to a given file.
    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
    //@@author

    /**
     * Saves the given task list to the file associated with this StorageFile object.
     * If the parent folder of the file does not exist, it will be created.
     *
     * @param taskList The task list to be saved.
     * @throws StorageFileException Throws StorageFileException when encountering an IOException when writing to the file
     */
    public void saveTaskList(TaskList taskList) throws StorageFileException {
        createParentFolderIfNotExists();

        StringBuilder record = new StringBuilder();
        for (int i = 0; i < taskList.getNoOfTasks(); i++) {
            DukeTask task = taskList.getTask(i);
            record.append(task.storageString()).append(System.lineSeparator());
        }

        try {
            writeToFile(this.filePath, record.toString());
        } catch (IOException e) {
            throw new StorageFileException(this.STORAGE_ERROR);
        }
    }

    /**
     * Creates the parent folder of the file associated with this StorageFile object if it does not exist.
     */
    private void createParentFolderIfNotExists() {
        if (!this.folder.toFile().exists()) {
            this.folder.toFile().mkdirs();
        }
    }

}


