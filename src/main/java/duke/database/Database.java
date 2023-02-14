package duke.database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.databaseexceptions.DatabaseNotLoadingException;
import duke.exception.databaseexceptions.DatabaseNotUpdatingException;
import duke.exception.databaseexceptions.DatabaseTypeNotFoundException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/** Represents the database storage for Duke using a basic text file. */
public class Database {

    //ASCII 31 is known as the "Unit Seperator"
    //Value taken from
    //https://stackoverflow.com/questions/492090/least-used-delimiter-character-in-normal-text-ascii-128
    private final String delimiter = Character.toString((char) 31);
    private final String filePath;

    /**
     * Represents the database storage for Duke using a basic text file.
     *
     * @param filePath the path for which the database text file is stored in
     */
    public Database(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads Duke's taskList with the relevant saved tasks stored in the database. Usually run on startup of Duke.
     *
     * @return an ArrayList of Task objects that was saved in the database
     * @throws DatabaseNotLoadingException thrown when there is an error with loading the database file.
     */
    public ArrayList<Task> load() throws DatabaseNotLoadingException, DatabaseTypeNotFoundException {
        try {

            File dataFolder = new File("data");
            File taskFile = new File(this.filePath);
            dataFolder.mkdir();
            taskFile.createNewFile();

            Scanner dataScanner = new Scanner(taskFile);
            ArrayList<Task> tasks = new ArrayList<>();
            while (dataScanner.hasNextLine()) {
                String[] taskData = dataScanner.nextLine().split(this.delimiter);
                switch (taskData[0]) {
                case "T":
                    tasks.add(new ToDo(taskData));
                    break;
                case "E":
                    tasks.add(new Event(taskData));
                    break;
                case "D":
                    tasks.add(new Deadline(taskData));
                    break;
                default:
                    throw new DatabaseTypeNotFoundException();
                }

            }
            return tasks;

        } catch (IOException e) {
            throw new DatabaseNotLoadingException();
        }
    }

    /**
     * Updates the database on shutting down of Duke with the new taskList
     *
     * @param tasks the tasks originally stored in the taskList for Duke
     * @throws DatabaseNotUpdatingException thrown when there is an error with updating the database file.
     */
    public void update(ArrayList<Task> tasks) throws DatabaseNotUpdatingException {
        try {
            FileWriter writer = new FileWriter(this.filePath, false);
            StringBuilder data = new StringBuilder("");
            for (Task task: tasks) {
                ArrayList<String> taskData = task.getData();
                data.append(String.join(this.delimiter, taskData));
                data.append("\n");
            }
            writer.write(data.toString());
            writer.close();
        } catch (IOException e) {
            throw new DatabaseNotUpdatingException();
        }

    }

}
