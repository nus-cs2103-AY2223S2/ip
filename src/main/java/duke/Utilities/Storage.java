package duke.Utilities;

import duke.Exception.NoSuchFileException;
import duke.Note.Note;
import duke.Task.Task;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The main class to store and load data from a file.
 */
public class Storage {
    private List<Task> tasks;
    private List<Note> notes;
    private File file;

    /**
     * The constructor for a storage object, that loads a file from the filepath or creates a new file with the
     * filepath name input.
     * @param filepath The file name.
     */
    public Storage(String filepath) {
        this.tasks = new ArrayList<>();
        this.notes = new ArrayList<>();
        this.file = new File(filepath);
    }

    /**
     * Loads an existing task list from the data file.
     * @return the list of tasks stored in the file.
     * @throws NoSuchFileException If there is no space to make the file, throw this exception.
     */
    public List<Task> loadTasksFromFile() throws NoSuchFileException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            tasks.clear();
            String line = reader.readLine();

            if (line != null && line.equals("Tasks:")) {
                line = reader.readLine();
                while (line != null && !line.equals("Notes:")) {
                    tasks.add(Task.dataToTask(line));
                    line = reader.readLine();
                }
            }

            return tasks;
        } catch (IOException exception) {
            throw new NoSuchFileException(file.getName());
        }
    }

    /**
     * Loads an existing notes list from the data file.
     * @return the list of notes stored in the file.
     * @throws NoSuchFileException If there is no space to make the file, throw this exception.
     */
    public List<Note> loadNotesFromFile() throws NoSuchFileException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            notes.clear();
            String line = reader.readLine();

            while (line != null) {
                if (line.equals("Notes:")) {
                    line = reader.readLine();
                    while (line != null) {
                        notes.add(Note.dataToNote(line));
                        line = reader.readLine();
                    }
                }
                line = reader.readLine();
            }
            return notes;
        } catch (IOException exception) {
            throw new NoSuchFileException(file.getName());
        }
    }

    /**
     * Saves the existing task list and notes list with the latest changes to the file.
     * @param taskList The task list with the latest changes to be saved.
     */
    public void saveToFile(List<Task> taskList, List<Note> noteList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            if (!taskList.isEmpty()) {
                writer.write("Tasks:\n");
            }

            for (Task task : taskList) {
                writer.write(task.taskToData() + '\n');
            }

            if (!noteList.isEmpty()) {
                writer.write("Notes:\n");
            }
            for (Note note : noteList) {
                writer.write(note.toString() + '\n');
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not save the file :( " + e);
        }

    }
}
