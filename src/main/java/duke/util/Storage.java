package duke.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Responsible for both loading and storing data from a specified file.
 */
public class Storage {
    private String filePath;
    private Ui ui;

    /**
     * A constructor for Storage.
     * @param filePath The file path of the data file.
     * @param ui The current Ui that is running.
     */
    public Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui = ui;
    }

    /**
     * Retrieves the current tasks from the data file.
     * @return An ArrayList of current tasks.
     */
    public ArrayList<Task> loadData() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(this.filePath);
        try {
            file.createNewFile();
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String nextLine = fileReader.nextLine();
                String[] taskSplit = nextLine.split(" \\| ", 3);
                String taskType = taskSplit[0];
                String taskMark = taskSplit[1];
                String taskDetails = taskSplit[2];

                switch (taskType) {
                case "T": {
                    Task toAdd = new Todo(taskDetails);
                    if (taskMark.equals("X")) {
                        toAdd.markAsDone();
                    }
                    tasks.add(toAdd);
                    break;
                }
                case "D": {
                    String[] deadlineDetails = taskDetails.split(" \\| ", 2);
                    String deadlineDescription = deadlineDetails[0];
                    String deadline = deadlineDetails[1];
                    Task toAdd = new Deadline(deadlineDescription, deadline);
                    if (taskMark.equals("X")) {
                        toAdd.markAsDone();
                    }
                    tasks.add(toAdd);
                    break;
                }
                case "E": {
                    String[] eventDetails = taskDetails.split(" \\| ", 2);
                    String eventDescription = eventDetails[0];
                    String[] eventPeriod = eventDetails[1].split(" to ");
                    Task toAdd = new Event(eventDescription, eventPeriod);
                    if (taskMark.equals("X")) {
                        toAdd.markAsDone();
                    }
                    tasks.add(toAdd);
                    break;
                }
                default:
                }
            }
        } catch (IOException e) {
            ui.displayMessage("Error loading data file!");
        }
        return tasks;
    }

    /**
     * Stores the current tasks as data into data file.
     * @param tasks An ArrayList of current tasks.
     */
    public void saveData(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            for (Task task : tasks) {
                fileWriter.write(String.format("%s\n", task.toData()));
            }
            fileWriter.close();
        } catch (IOException e) {
            ui.displayMessage("Error creating or finding file!");
        }
    }
}
