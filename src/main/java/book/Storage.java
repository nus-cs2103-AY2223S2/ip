package book;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import book.exception.LoadException;
import book.exception.SaveException;
import book.task.Deadline;
import book.task.Event;
import book.task.Task;
import book.task.ToDo;

/**
 * Class handling the {@code save} and {@code load} operations of {@code Book}.
 */
public class Storage {
    /** Save {@code File} for {@code Book}. */
    private File bookSave;
    /** Directory {@code File} where the save {@code File} is stored. */
    private File saveDir;

    /**
     * Initializes a {@code Storage} by retrieving the save {@code File} the given {@code Path}, if
     * the save {@code File} or directory {@code File} is missing, creates the respective empty
     * {@code File}s.
     * @param filePath {@code Path} for retrieving the save {@code File}.
     */
    public Storage(Path filePath) {
        this.bookSave = filePath.toFile();
        this.saveDir = this.bookSave.getParentFile();
        if (!saveDir.exists()) {
            try {
                saveDir.mkdir();
            } catch (SecurityException exception) {
                System.out.println("Book.Book had an issue with the save directory.");
            }
        }
        if (!bookSave.exists()) {
            try {
                bookSave.createNewFile();
            } catch (IOException exception) {
                System.out.print("Book.Book had an issue loading the history book.\n");
            }
        }
    }

    /**
     * Saves the given {@code TaskList} to the save {@code File} associated with the
     * {@code Storage}.
     * @param list {@code TaskList} to save to save {@code File}.
     * @throws SaveException if an error occurs with updating the {@code File}.
     */
    public void save(TaskList list) throws SaveException {
        try {
            FileWriter writeToFile = new FileWriter(this.bookSave);
            for (int i = 0; i < list.listSize(); i++) {
                writeToFile.write(list.get(i).saveString() + "\n");
            }
            writeToFile.close();
        } catch (IOException exception) {
            throw new SaveException("There was an issue recording down your Book.Book.");
        }
    }

    /**
     * Returns an {@code ArrayList<Task>} loaded with the {@code Task}s stored in the associated
     * save {@code File}.
     * @return {@code ArrayList<Task>} loaded with the {@code Task}s stored in the associated save
     *     {@code File}.
     * @throws LoadException if an error occurs with loading the save {@code File}.
     */
    public ArrayList<Task> load() throws LoadException {
        ArrayList<Task> list = new ArrayList<Task>(100);
        try {
            Scanner fileReader = new Scanner(this.bookSave);
            while (fileReader.hasNextLine()) {
                String taskLine = fileReader.nextLine();
                String[] task = taskLine.split(";", 5);
                switch (task[0]) {
                case "T":
                    list.add(new ToDo(task[2]));
                    break;
                case "D":
                    list.add(new Deadline(task[2], Parser.parseDateTime(task[3])));
                    break;
                case "E":
                    list.add(new Event(task[2], Parser.parseDateTime(task[3]),
                            Parser.parseDateTime(task[4])));
                    break;
                default:
                    throw new LoadException("The saved Book.Book is corrupted.");
                }
                if (task[1].equals("true")) {
                    list.get(list.size() - 1).mark();
                }
            }
            return list;
        } catch (FileNotFoundException exception) {
            throw new LoadException("There was an issue finding the saved Book.Book.");
        }
    }

}
