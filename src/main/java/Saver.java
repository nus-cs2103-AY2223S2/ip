import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Saver {
    private String directory = System.getProperty("user.dir");
    private File path = new File(directory + "/duke.txt");
    private ArrayList<Task> tasks = new ArrayList<Task>();

    void writeTasksToFile(String taskListStrings) {
        try {
            FileWriter fw = new FileWriter(this.path);
            fw.write(taskListStrings.substring(1, taskListStrings.length()-1));
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    ArrayList<Task> readTasksFromFile() {
        try {
            String path = System.getProperty(("user.dir")) + "/duke.txt";
            Scanner scanner = new Scanner(new File(path));
            String contentsOfFile = scanner.nextLine();
            String[] fileContentsArray = contentsOfFile.split(", ");
            for (String task: fileContentsArray) {
                processTask(task);
//                System.out.println(task);
            }
            return this.tasks;
        } catch (IOException e) {
            this.makeDirectory();
        }
        return this.tasks;
    }

    void makeDirectory() {
        try {
            if (!checkDirectory()) {
                path.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    boolean checkDirectory() {
        return path.exists();
    }

    void processTask(String task) {
        String taskType = "" + task.charAt(1);
        if (taskType.equals("T")) {
            addTodo(task.substring(3));
        } else if (taskType.equals("D")) {
            addDeadline(task.substring(3));
        } else if (taskType.equals("E")) {
            addEvent(task.substring(3));
        }

    }
    void addEvent(String task) {
        String taskDescriptionWithTime = task.substring(4);
        String[] taskDetails = taskDescriptionWithTime.split("\\(");
        String taskDescription = taskDetails[0];
        taskDescription = taskDescription.substring(0, taskDescription.length() - 1);
        String timeRange = taskDetails[1].substring(6, taskDetails[1].length() - 1);
        String[] timings = timeRange.split(" to: ");
        String start = timings[0];
        String end = timings[1];
        Task curTask = new Event(taskDescription, start, end);
        if (("" + task.charAt(1)).equals("X")) {
            curTask = curTask.markAsDone();
        }
        ArrayList<Task> curList = this.tasks;
        curList.add(curTask);
        this.tasks = curList;
    }
    void addTodo(String task) {
        String taskDescription = task.substring(4);
        Task curTask = new Todo(taskDescription);
        if (("" + task.charAt(1)).equals("X")) {
            curTask = curTask.markAsDone();
        }
        ArrayList<Task> curList = this.tasks;
        curList.add(curTask);
        this.tasks = curList;
    }
    void addDeadline(String task) {
        String taskDescriptionWithTime = task.substring(4);
        String[] taskDetails = taskDescriptionWithTime.split(" \\(by: ");
        String taskDescription = taskDetails[0];
        String by = taskDetails[1];
        Task curTask = new Deadline(taskDescription, by);
        if (("" + task.charAt(1)).equals("X")) {
            curTask = curTask.markAsDone();
        }
        ArrayList<Task> curList = this.tasks;
        curList.add(curTask);
        this.tasks = curList;
    }
}
