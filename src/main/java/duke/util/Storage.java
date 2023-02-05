package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    public void saveData(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            for (Task task : tasks) {
                fileWriter.write(String.format("%s\n", task.toData()));
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Error creating or finding file!");
        }
    }
}
