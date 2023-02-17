package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Represents loading data from file and updating data to file
 */
public class Storage {
    private final String filePath;

    /**
     * Storage constructor
     * @param filePath file path for saving file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads data from data file
     * @return tasks from saving file
     * @throws IOException exception
     */
    public TaskList load() throws IOException {
        TaskList tasks = new TaskList();
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                Task task;
                String data = sc.nextLine();
                String[] commandInFile = data.split(" \\| ");
                boolean isDoneInFile = commandInFile[1].equals("1");

                switch (commandInFile[0]) {
                case "T":
                    task = new Todo(commandInFile[2], isDoneInFile);
                    tasks.add(task);
                    break;
                case "D":
                    task = new Deadline(commandInFile[2], commandInFile[3], isDoneInFile);
                    tasks.add(task);
                    break;
                case "E":
                    task = new Event(commandInFile[2], commandInFile[3],
                            commandInFile[4], isDoneInFile);
                    tasks.add(task);
                    break;
                default:
                    System.out.println("The record cannot be parsed");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            new File("data").mkdir();
            new File(filePath).createNewFile();
        }
        return tasks;
    }

    /**
     * Updates date in data file
     * @param tasks task
     * @throws IOException exception
     */
    public void updateFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw.close();
        if (tasks.getTaskCounter() > 0) {
            for (int i = 0; i < tasks.getTaskCounter(); i++) {
                appendToFile(tasks.get(i).file());
            }
        }
    }

    /**
     * Appends data to file
     * @param info information needs to be appended to file
     * @throws IOException exception
     */
    private void appendToFile(String info) throws IOException {
        //Constructs a FileWriter object given a file name
        // with a boolean indicating whether to append the data written
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(info + "\n");
        fw.close();
    }
}
