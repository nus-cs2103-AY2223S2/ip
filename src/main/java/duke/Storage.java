package duke;
import duke.task.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(String filepath) {
        this.file = new File(filepath);
    }

    /**
     * Loads a saved task list from file in this Storage object.
     * If the file does not exist, creates a new empty files and parent directories.
     *
     * @return Saved ArrayList of Tasks from the location.
     * @throws DukeException If the file does not exist or if there is loading errors from the file.
     */
    public ArrayList<Task> load() throws DukeException {
        if (!file.exists()) {
            try {
                this.file.getParentFile().mkdirs();
                this.file.createNewFile();
            } catch (IOException e) {
                // We do not expect an IOException to occur here.
            }
            throw new DukeException("File does not exist!");
        }
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                String taskData = scanner.nextLine();
                tasks.add(this.storageParse(taskData));
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found!");
        }
        return tasks;
    }


    private Task storageParse(String data) throws DukeException {
        //
        String[] taskData = data.split("\\|");

        if (taskData[0].equals("T")) {
            return new Todo(taskData[1].equals("1"), taskData[2]);
        } else if (taskData[0].equals("E")) {
            return new Event(taskData[1].equals("1"), taskData[2], taskData[3]);
        } else if (taskData[0].equals("D")) {
            try {
                return new Deadline(taskData[1].equals("1"), taskData[2], LocalDate.parse(taskData[3]));
            } catch (DateTimeParseException e) {
                throw new DukeException("Please enter a valid date in the format [yyyy-mm-dd]");
            }
        } else {
            throw new DukeException("Invalid file");
        }
    }

    /**
     * Saves the TaskList to the file in this Storage object.
     *
     * @param tasks TaskList of Tasks to be saved to file.
     */
    public void save(TaskList tasks) {
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            fileWriter.write(tasks.createTaskSaveData());
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
