package duke.database;

import duke.task.Deadline;
import duke.task.Event;
import duke.exception.DatabaseExceptions.DatabaseNotLoadingException;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {

    //ASCII 31 is known as the "Unit Seperator"
    //Value taken from
    //https://stackoverflow.com/questions/492090/least-used-delimiter-character-in-normal-text-ascii-128
    private final String delimiter = Character.toString((char) 31);
    private final String filePath;

    public Database(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DatabaseNotLoadingException {
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
                }

            }
            return tasks;

        } catch (IOException e) {
            throw new DatabaseNotLoadingException();
        }
    }

    public void update(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(this.filePath, false);
        StringBuilder data = new StringBuilder("");
        for (Task task: tasks) {
            ArrayList<String> taskData = task.data();
            data.append(String.join(this.delimiter, taskData));
            data.append("\n");
        }
        writer.write(data.toString());
        writer.close();
    }

}
