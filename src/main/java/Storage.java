import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class Storage {
    private File path;

    private ArrayList<Task> tasks = new ArrayList<Task>();

    public Storage(String filePath) {
        this.path = new File(filePath);
    }
    void writeTasksToFile(String taskListStrings) {
        try {
            FileWriter fw = new FileWriter(this.path);
            fw.write(taskListStrings.substring(1, taskListStrings.length()-1));
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    ArrayList<Task> readTasksFromFile() throws DukeException{
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
        try {
            String taskType = "" + task.charAt(1);
            if (taskType.equals("T")) {
                addTodo(task.substring(3));
            } else if (taskType.equals("D")) {
                addDeadline(task.substring(3));
            } else if (taskType.equals("E")) {
                addEvent(task.substring(3));
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }
    void addEvent(String task) throws ParseException {
        String taskDescriptionWithTime = task.substring(4);
        String[] taskDetails = taskDescriptionWithTime.split("\\(");
        String taskDescription = taskDetails[0];
        taskDescription = taskDescription.substring(0, taskDescription.length() - 1);
        String timeRange = taskDetails[1].substring(6, taskDetails[1].length() - 1);
        String[] timings = timeRange.split(" to: ");

        String start = timings[0];
        String displayFormat = "MMM d yyyy";
        String taskFormat = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(displayFormat);
        Date oldStart = format.parse(start);
        format.applyPattern(taskFormat);
        String newStart = format.format(oldStart);

        String end = timings[1];
        format = new SimpleDateFormat(displayFormat);
        Date oldEnd = format.parse(end);
        format.applyPattern(taskFormat);
        String newEnd = format.format(oldEnd);

        Task curTask = new Event(taskDescription, newStart, newEnd);
        if (("" + task.charAt(1)).equals("X")) {
            curTask = curTask.mark();
        }
        ArrayList<Task> curList = this.tasks;
        curList.add(curTask);
        this.tasks = curList;
    }
    void addTodo(String task) {
        String taskDescription = task.substring(4);
        Task curTask = new Todo(taskDescription);
        if (("" + task.charAt(1)).equals("X")) {
            curTask = curTask.mark();
        }
        ArrayList<Task> curList = this.tasks;
        curList.add(curTask);
        this.tasks = curList;
    }
    void addDeadline(String task) throws ParseException {
        String taskDescriptionWithTime = task.substring(4);

        String[] taskDetails = taskDescriptionWithTime.split(" \\(by: ");
        String taskDescription = taskDetails[0];

        String by = taskDetails[1];
        by = by.substring(0, by.length() - 1);

        String displayFormat = "MMM d yyyy";
        String taskFormat = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(displayFormat);
        Date oldDate = format.parse(by);
        format.applyPattern(taskFormat);
        String newDateString = format.format(oldDate);

        Task curTask = new Deadline(taskDescription, newDateString);
        if (("" + task.charAt(1)).equals("X")) {
            curTask = curTask.mark();
        }
        //DateTimeFormatter.ofPattern("MMM d yyyy")
        ArrayList<Task> curList = this.tasks;
        curList.add(curTask);
        this.tasks = curList;
    }
}
