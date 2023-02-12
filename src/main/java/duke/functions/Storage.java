package duke.functions;
import duke.dukeexceptions.TaskListEmpty;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that writes and read Tasks.
 */
public class Storage {
    private String path;

    /**
     * Constructor of a Storage.
     *
     * @param filePath path of where the file is.
     */
    public Storage(String filePath) {
        this.path = "./src/main/data/duke.txt";
    }

    /**
     * Returns an ArrayList of Tasks from the hard-drive.
     *
     * @return an ArrayList containing Task.
     * @throws TaskListEmpty when the TaskList is empty.
     */
    public ArrayList<Task> load() throws TaskListEmpty {
        ArrayList<Task> actions;
        try {
            actions = new ArrayList<Task>();
            Scanner scanner = new Scanner(new File(this.path));
            while (scanner.hasNext()) {
                String taskDetails = scanner.nextLine();
                String[] details = taskDetails.split("\\|");
                for (int i = 0; i < details.length; i++) {
                    System.out.println(details[i]);
                }

                String taskType = details[0];
                String taskStatus = details[1];
                String taskName = details[2];
                taskStatus = taskStatus.replace("|", "");
                taskName = taskName.replace("|", "");
                boolean isDone;
                if (taskStatus.equals("T")) {
                    isDone = true;
                } else {
                    isDone = false;
                }
                switch (taskType) {
                case "T":
                    actions.add(new ToDo(taskName, isDone));
                    break;
                case "E":
                    actions.add(new Event(taskName, isDone));
                    break;
                case "D":
                    actions.add(new Deadline(taskName, isDone));
                    break;
                default:
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new TaskListEmpty("");
        }
        return actions;
    }

    /**
     * Writes to the storage file whenever a Task is created.
     *
     * @param input string containing the information of the task.
     * @param type string containing the type of the task.
     */
    public void writeToFile(String input, String type) {
        try {
            FileWriter filewriter = new FileWriter(this.path, true);
            String text = type + "|" + "F" + "|" + input;
            filewriter.write(System.lineSeparator());
            filewriter.write(text);
            filewriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }




}
