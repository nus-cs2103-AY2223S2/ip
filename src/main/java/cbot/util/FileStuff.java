package cbot.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import cbot.task.Deadline;
import cbot.task.Event;
import cbot.task.Task;
import cbot.task.TaskList;

/**
 * Manages save-file reading and writing.
 */
public class FileStuff {
    private final File file;

    /**
     * Constructs a new instance to manage the save file at the given path.
     *
     * @param path Directory location (relative) of the save file.
     */
    public FileStuff(String path) {
        this.file = new File(path);
    }

    /**
     * Returns true if the file already exists.
     *
     * @return Whether the file already exists.
     */
    boolean fileExists() {
        return this.file.exists();
    }

    /**
     * Creates the parent file and the save file itself, if either or both do not yet exist.
     */
    public void makeFile() {
        try {
            this.file.getParentFile().mkdir();
            this.file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the current state of the TaskList to the save file.
     *
     * @param tl The TaskList to save.
     * @see TaskList
     */
    public void saveFile(TaskList tl) {
        if (!fileExists()) {
            makeFile();
        }

        try {
            FileWriter fw = new FileWriter(this.file);
            fw.write(tl.makeFileFriendly());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads and loads the save file as a list of tasks.
     *
     * @return The loaded list of tasks.
     * @throws FileNotFoundException If the save file does not exist in the expected location.
     * @see TaskList
     */
    public TaskList loadFile()
            throws FileNotFoundException {
        Scanner s = new Scanner(this.file);
        ArrayList<Task> tdl = new ArrayList<>();

        while (s.hasNext()) {
            String taskSave = s.nextLine();
            tdl.add(restoreTask(taskSave));
        }

        return new TaskList(tdl);
    }

    private Task restoreTask(String taskSave) {
        String[] splitSave = taskSave.split(Task.SEP);
        // type SEP done SEP desc SEP due/from SEP to

        try {
            boolean isDone = splitSave[1].equals(Task.DONE_TRUE);
            String desc = splitSave[2];

            switch (splitSave[0]) {
            case Task.TODO_SYMBOL:
                return new Task(desc, isDone);

            case Deadline.DEADLINE_SYMBOL:
                return new Deadline(desc, LocalDateTime.parse(splitSave[3]), isDone);

            case Event.EVENT_SYMBOL:
                return new Event(desc, LocalDateTime.parse(splitSave[3]), LocalDateTime.parse(splitSave[4]), isDone);

            default:
                throw new UncheckedIOException(new IOException("Unknown task type symbol: " + splitSave[0]));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // Change to assert
            throw new UncheckedIOException(new IOException("Missing SEP (" + Task.SEP + ") in save file"));
        }
    }
}
