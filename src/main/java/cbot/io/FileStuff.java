package cbot.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
    public TaskList loadFile() throws FileNotFoundException {
        Scanner s = new Scanner(this.file);

        ArrayList<Task> tdl = new ArrayList<>();

        while (s.hasNext()) {
            String[] taskStr = s.nextLine().split(Task.SEP);

            // type SEP done SEP desc SEP due/from SEP to

            boolean isDone = taskStr[1].equals(Task.DONE_TRUE);
            String desc = taskStr[2];

            switch (taskStr[0]) {
            case Task.TODO_SYMBOL:
                tdl.add(new Task(desc, isDone));
                break;

            case Deadline.DEADLINE_SYMBOL:
                tdl.add(new Deadline(desc, LocalDateTime.parse(taskStr[3]), isDone));
                break;

            case Event.EVENT_SYMBOL:
                tdl.add(new Event(desc, LocalDateTime.parse(taskStr[3]), LocalDateTime.parse(taskStr[4]), isDone));
                break;

            default:
            }
        }

        return new TaskList(tdl);
    }
}
