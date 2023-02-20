package duke.functions;
import duke.dukeexceptions.DukeException;
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
    private ArrayList<String> taskStrings;

    /**
     * Constructor of a Storage.
     *
     * @param filePath path of where the file is.
     */
    public Storage(String filePath) {
        File folder = new File("data");
        File file = new File(filePath);
        try {
            if (folder.exists()) {
                System.out.println("Data directory exists.");
            } else {
                if (folder.mkdir()) {
                    System.out.println("Data directory created.");
                } else {
                    throw new DukeException("Data directory cannot be created.");
                }
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }


        try {
            if (file.exists()) {
                System.out.println("Saved list exists.");
            } else {
                if (file.createNewFile()) {
                    System.out.println("List file created.");
                } else {
                    throw new DukeException("List file cannot be created.");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        this.path = filePath;
        taskStrings = new ArrayList<String>();
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

                String taskDetails = scanner.nextLine() + '\n';
                this.taskStrings.add(taskDetails);
                String[] details = taskDetails.split("\\|");

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
            System.out.println(this.taskStrings.size());
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
            String text = type + "|" + "F" + "|" + input + '\n';
            filewriter.write(text);
            filewriter.close();
            this.taskStrings.add(text);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeStatusInFile(int index, boolean isDone) {
        String newText;
        String text = this.taskStrings.get(index);
        String[] details = text.split("\\|");
        String taskType = details[0];
        String taskName = details[2];
        if (isDone) {
            newText = taskType + '|' + 'T' + '|' + taskName;
            System.out.println(newText);
        } else {
            newText = taskType + '|' + 'F' + '|' + taskName;
        }
        this.taskStrings.set(index, newText);
        this.updateFile();
    }

    public void deleteInFile(int index) {
        this.taskStrings.remove(index);
        this.updateFile();
    }

    public void updateFile() {
        try {
            FileWriter filewriter = new FileWriter(this.path, false);
            for (String s: taskStrings) {
                filewriter.write(s);
            }
            filewriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

