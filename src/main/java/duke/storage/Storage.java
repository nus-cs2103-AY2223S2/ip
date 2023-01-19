package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
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
    private final String todoTag = "[T]";
    private final String deadlineTag = "[D]";
    private final String eventTag = "[E]";
    private final String storageError = "OOPS!!! There's something wrong "
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
     * @throws InvalidInputException Throws InvalidInputException when the Storage file
     * has unrecognized record
     * @throws StorageFileException Throws StoreFileIoException when encountering
     * IOException when reading the file
     */
    public TaskList load() throws InvalidInputException, StorageFileException {
        TaskList list = new TaskList();
        if (!storageFile.exists()) {
            return list;
        }

        try {
            Scanner sc = new Scanner(storageFile);
            while (sc.hasNextLine()) {
                String instruction = sc.nextLine().strip();
                String[] information = instruction.split("\\s\\|\\s");

                String taskTag = information[0];
                boolean isDone = information[1].equals("[X]");
                String description = information[2];

                if (taskTag.equals(todoTag)) {
                    Decoder.todoDecoder(list, description, isDone);
                } else if (taskTag.equals(deadlineTag)) {
                    String date = information[3];
                    Decoder.deadlineDecoder(list, description, isDone, date);
                } else {
                    String from = information[3];
                    String to = information[4];
                    Decoder.eventDecoder(list, description, isDone, from, to);
                }
            }
            return list;
        } catch (FileNotFoundException e) {
            throw new StorageFileException(storageError);
        }
    }

    private void storageFormatChecker(String tag, String isDone) {
        assert Objects.equals(tag, "[T]") || Objects.equals(tag, "[D]") || Objects.equals(tag, "[E]")
                : "Type tag of event should be [T], [D], or [E]";

        assert Objects.equals(isDone, "[X]") || Objects.equals(isDone, "[ ]")
                : "IsDone tag of event should be [ ], or [X]";
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
     * Saves the current TaskList to the given file by overwriting it.
     *
     * @param list The given file to be saved.
     * @throws StorageFileException Throws StoreFileIoException when encountering
     * IOException when writing to the file
     */
    public void save(TaskList list) throws StorageFileException {
        if (!this.folder.toFile().exists()) {
            folder.toFile().mkdirs();
        }

        StringBuilder record = new StringBuilder();
        try {
            for (int i = 0; i < list.getNoOfTasks(); i++) {
                DukeTask task = list.getTask(i);
                record.append(task.storageString()).append(System.lineSeparator());
            }
            writeToFile(filePath, record.toString());
        } catch (IOException e) {
            throw new StorageFileException(storageError);
        }
    }
}
