package duke.storage;

import static duke.parser.ErrorMessage.INVALID_STATUS_ERROR;
import static duke.parser.ErrorMessage.INVALID_TYPE_ERROR;

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
import duke.parser.ErrorMessage;
import duke.task.DukeTask;
import duke.task.TaskList;



/**
 * A Storage object that handles the saving and loading of the TaskList.
 * Writes the TaskList in an external file whenever it is updated. Load the existing
 * file when the user opens the Duke program.
 */

public class Storage {
    private static final String TODO_TAG = "[T]";
    private static final String DEADLINE_TAG = "[D]";
    private static final String EVENT_TAG = "[E]";
    private static final String FIXED_DURATION_TAG = "[F]";
    private static final String IS_DONE_TAG = "[X]";
    private static final String NOT_DONE_TAG = "[ ]";
    private final String filePath;
    private final Path folder;
    private final File storageFile;

    /**
     * Constructor of that takes a path of the file and specify the file for
     * storage of the given path.
     *
     * @param filePath The path of the storage file
     */
    public Storage(String filePath) {
        // Get the absolute path of the root directory
        String rootPath = Paths.get("").toAbsolutePath().toString();
        // Create the full path to the storage file by concatenating the root path and the file path
        this.filePath = Paths.get(rootPath, filePath).toString();

        // Get the path of the file
        Path path = Paths.get(filePath);
        // Get the number of elements in the path
        int len = path.getNameCount();

        // Get the parent folder of the storage file by getting the path of all elements except the last one
        this.folder = Paths.get(rootPath, path.subpath(0, len - 1).toString());
        // Create a new file object for the storage file
        this.storageFile = new File(this.filePath);
    }


    /**
     * Loads the TaskList from the given data file. If the file does not exist return
     * a new empty TaskList.
     *
     * @return Return the TaskList parsed from the given file
     * @throws InvalidInputException Thrown when the Storage file has unrecognized record
     * @throws StorageFileException Thrown when encountering IOException when reading the file
     */
    public TaskList load() throws InvalidInputException, StorageFileException {
        TaskList list = new TaskList();
        // If the storage file does not exist, return an empty task list
        if (!storageFile.exists()) {
            return list;
        }

        try {
            // Read the file
            List<String> lines = readFile(storageFile);
            // Process each line in the file
            for (String line : lines) {
                // Parse the line and add the task to the task list
                processLine(list, line);
            }
            // Return the task list
            return list;
        } catch (FileNotFoundException e) {
            // Throw an exception if the file is not found
            throw new StorageFileException(ErrorMessage.STORAGE_ERROR);
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
        // Initialize a list to store the lines read from the file
        List<String> lines = new ArrayList<>();
        // Iterate through each line of the file
        while (sc.hasNextLine()) {
            // Read the line and strip leading/trailing whitespaces
            String line = sc.nextLine().strip();
            // Add the line to the list
            lines.add(line);
        }
        // Return the list of lines
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
        String[] informationList = line.split("\\s\\|\\s");

        String taskTag = informationList[0];
        boolean isDone = informationList[1].equals(IS_DONE_TAG);
        storageFormatChecker(informationList[0], informationList[1]);

        // Decode the task information based on the task tag
        switch (taskTag) {
        case TODO_TAG:
            // Decode the to-do task information
            Decoder.decodeTodo(list, informationList[2], isDone);
            break;
        case DEADLINE_TAG:
            // Decode the deadline task information
            Decoder.decodeDeadline(list, informationList[2], isDone, informationList[3]);
            break;
        case EVENT_TAG:
            // Decode the event task information
            Decoder.decodeEvent(list, informationList[2], isDone, informationList[3], informationList[4]);
            break;
        case FIXED_DURATION_TAG:
            Decoder.decodeFixedDuration(list, informationList[2], isDone, informationList[3]);
            break;
        default:
            // Throw an exception if the task tag is not recognized
            throw new InvalidInputException("Unrecognized task tag: " + taskTag);
        }
    }

    /**
     * Checks if the input for the task tag and isDone match the expected format.
     *
     * @param tag The task tag of the task
     * @param isDone The status of the task
     */
    private void storageFormatChecker(String tag, String isDone) {
        // Type tag of event should be [T], [D], or [E]

        assert Objects.equals(tag, TODO_TAG) || Objects.equals(tag, DEADLINE_TAG)
                || Objects.equals(tag, EVENT_TAG) || Objects.equals(tag, FIXED_DURATION_TAG)
                : INVALID_TYPE_ERROR;

        // IsDone tag of event should be [ ], or [X]
        assert Objects.equals(isDone, IS_DONE_TAG) || Objects.equals(isDone, NOT_DONE_TAG)
                : INVALID_STATUS_ERROR;
    }

    //@@author Yufannnn-reused
    //https://nus-cs2103-ay2223s2.github.io/website/schedule/week3/topics.html#W3-4c
    //with minor modification, nice and concise function to overwrite text to a given file.
    private void writeToFile(String filePath, String textToAdd) throws IOException {
        // Initialize the FileWriter
        FileWriter fw = new FileWriter(filePath);
        // Write the text to the file
        fw.write(textToAdd);
        // Close the FileWriter
        fw.close();
    }
    //@@author

    /**
     * Saves the given task list to the file associated with this StorageFile object.
     * If the parent folder of the file does not exist, it will be created.
     *
     * @param taskList The task list to be saved.
     * @throws StorageFileException Throws StorageFileException when encountering an
     *      IOException when writing to the file.
     */
    public void saveTaskList(TaskList taskList) throws StorageFileException {
        // Create parent folder if it does not exist
        createParentFolderIfNotExists();

        StringBuilder record = new StringBuilder();
        // Append each task's storage string to the record
        for (int i = 0; i < taskList.getNoOfTasks(); i++) {
            DukeTask task = taskList.getTask(i);
            record.append(task.storageString()).append(System.lineSeparator());
        }

        try {
            // Write the record to the file
            writeToFile(this.filePath, record.toString());
        } catch (IOException e) {
            throw new StorageFileException(ErrorMessage.STORAGE_ERROR);
        }
    }

    /**
     * Creates the parent folder of the file associated with this StorageFile object if it does not exist.
     */
    private void createParentFolderIfNotExists() {
        // check if the folder exists
        if (!this.folder.toFile().exists()) {
            // if it does not, create the folder using mkdirs()
            this.folder.toFile().mkdirs();
        }
    }
}


