package cbot.io;

import cbot.task.Deadline;
import cbot.task.Event;
import cbot.task.Task;
import cbot.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

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
     *
     * @throws IOException If the save file cannot be accessed.
     */
    public void makeFile() throws IOException  {
        this.file.getParentFile().mkdir();
        this.file.createNewFile();
    }

    /**
     * Writes the current state of the TaskList to the save file.
     *
     * @param tl The TaskList to save.
     * @throws IOException If the save file cannot be accessed.
     * @see TaskList
     */
    public void saveFile(TaskList tl) throws IOException {
        if (!fileExists()) {
            makeFile();
        }
        
        FileWriter fw = new FileWriter(this.file);
        fw.write(tl.makeFileFriendly());
        fw.close();
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
                tdl.add(new Deadline(desc, isDone, LocalDateTime.parse(taskStr[3])));
                break;

            case Event.EVENT_SYMBOL:
                tdl.add(new Event(desc, isDone, LocalDateTime.parse(taskStr[3]), LocalDateTime.parse(taskStr[4])));
                break;
            }
        }
        
        return new TaskList(tdl);
    }
}