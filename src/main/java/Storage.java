import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage which stores a list of tasks to be kept track of
 */
public class Storage {
    protected String path;
    protected final String DATE_TIME_TO_PARSE = "yyyy-MM-dd HH:mm";
    protected final String DATE_TO_PRINT = "d MMM yyyy";
    protected ArrayList<Task> taskList;

    /**
     * Creates a storage object which stores a task list
     */
    public Storage(String path) {
        this.path = path;
        this.taskList = new ArrayList<>();
    }

    public void load() {
        try {
            File f = new File(this.path);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String taskString = sc.nextLine();
                Task currTask = parse(taskString);
                this.taskList.add(currTask);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error encountered when loading tasks :(\n");
            System.out.println("Ignore this message if it is your first time using my service :>");
        } catch (IllegalStateException e) {
            System.out.println("Error encountered when loading tasks :(");
        }
    }

    public void save() {
        try {
            FileWriter fw = new FileWriter(this.path);
            for (Task task: this.taskList) {
                DateTimeFormatter formatterParse = DateTimeFormatter.ofPattern(DATE_TIME_TO_PARSE);
                StringBuilder sb = new StringBuilder(task.getStatusIcon());
                sb.append("\\");
                sb.append(task.getDescription());
                sb.append("\\");
                // X\desc\T
                if (task instanceof ToDo) {
                    sb.append("T");
                    fw.write(sb + System.lineSeparator());
                } else if (task instanceof Deadline) { // X\desc\D\By
                    sb.append("D");
                    sb.append("\\");
                    sb.append(((Deadline) task).getDateTimeBy().format(formatterParse));
                    fw.write(sb + System.lineSeparator());
                } else {
                    sb.append("E");
                    sb.append("\\");
                    sb.append(((Event) task).getDateTimeFrom().format(formatterParse)); // X\desc\E\From\To
                    sb.append("\\");
                    sb.append(((Event) task).getDateTimeTo().format(formatterParse));
                    fw.write(sb + System.lineSeparator());
                }

            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error encountered when saving tasks :(");
        }
    }

    public Task parse(String str) {
        Task task;
        String[] arr = str.split("\\\\");

        switch (arr[2]) {
        case "T":
            task = new ToDo(arr[1]);
            break;
        case "D":
            task = new Deadline(arr[1], arr[3]);
            break;
        case "E":
            task = new Event(arr[1], arr[3], arr[4]);
            break;
        default:
            throw new IllegalStateException();
        }
        if (arr[0].equals("X")) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Gets the current total number of tasks in the list
     * @return current number of tasks
     */
    public int getNumberOfTasks() {
        return this.taskList.size();
    }

    /**
     * Adds task to the list
     * @param task Task to be added
     */
    public void addTask(Task task) {
        this.taskList.add(task);
        String statement = "Got it! I've added this task:\n" + task + "\nNow you have ";
        String t = this.getNumberOfTasks() == 1 ? " task" : " tasks";
        System.out.println(statement + this.getNumberOfTasks() + t + " in the list.");
    }

    /**
     * Deletes task from the list
     * @param i Index of task to be deleted
     */
    public void deleteTask(int i) {
        Task task = this.taskList.get(i);
        this.taskList.remove(i);
        String t = this.getNumberOfTasks() <= 1 ? " task" : " tasks";
        System.out.println("Got it! I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.getNumberOfTasks() + t + " in the list.");
    }

    /**
     * Marks task as done
     * @param i Index of task to be marked
     */
    public void markTask(int i) {
        Task task = this.taskList.get(i);
        if (task.getStatusIcon().equals("X")) {
            System.out.println("This task is already marked as done!");
        } else {
            task.markAsDone();
            System.out.println("Good job! I've marked this task as done:");
        }
        System.out.println(task);
    }

    /**
     * Marks task as not done
     * @param i Index of task to be unmarked
     */
    public void unmarkTask(int i) {
        Task task = this.taskList.get(i);
        if (task.getStatusIcon().equals(" ")) {
            System.out.println("This task is already marked as not done!");
        } else {
            task.unmarkFromDone();
            System.out.println("Okay, I've marked this task as not done yet:");
        }
        System.out.println(task);
    }

    /**
     * Prints out the list of current tasks
     */
    public void showAllTasks() {
        System.out.println("Here are all the tasks in your list:");
        for (int i = 0; i < this.getNumberOfTasks(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(i+1);
            sb.append(".");
            sb.append(this.taskList.get(i));
            System.out.println(sb);
        }
    }

    public void showTasksDue(LocalDate byDate) {
        DateTimeFormatter formatterPrint = DateTimeFormatter.ofPattern(DATE_TO_PRINT);
        String formattedDueDate = byDate.format(formatterPrint);
        int j = 1;
        System.out.println("Here are the task(s) due on " + formattedDueDate + " :");
        for (int i = 0; i < this.getNumberOfTasks(); i++) {
            Task currTask = this.taskList.get(i);
            StringBuilder sb = new StringBuilder();
            sb.append(j);
            sb.append(".");
            if (currTask instanceof Deadline) {
                if (((Deadline) currTask).getDateTimeBy().toLocalDate().compareTo(byDate) == 0) {
                    sb.append(currTask);
                    System.out.println(sb);
                    j++;
                }
            } else if (currTask instanceof Event) {
                if (((Event) currTask).getDateTimeTo().toLocalDate().compareTo(byDate) == 0) {
                    sb.append(currTask);
                    System.out.println(sb);
                    j++;
                }
            }
        }
    }
}
