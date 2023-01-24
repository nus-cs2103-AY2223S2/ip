import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    private ArrayList<Task> lstOfItems;

    public TaskList(File file) {
        this.lstOfItems = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(file);
            int noOfTasks = Integer.parseInt(fileScanner.nextLine());
            for (int i = 0; i < noOfTasks; i++) {
                String curr = fileScanner.nextLine();
                parseTask(curr);
            }
        } catch (FileNotFoundException err) {
            System.out.println(err);
        }
    }

    public void parseTask(String currentTask) {
        Task task = null;
        if (currentTask.charAt(1) == 'T') {
            task = new Todo(currentTask.substring(7));
        } else if (currentTask.charAt(1) == 'D') {
            String[] split = currentTask.split("by: ");
            String description = split[0].substring(7, split[0].length() - 2);
            String date = split[1].substring(0, split[1].length() - 1);
            task = new Deadline(description, date);
        } else {
            String[] split = currentTask.split("from: ");
            String description = split[0].substring(7, split[0].length() - 2);
            String[] dateSplit = split[1].split(" to: ");
            String from = dateSplit[0];
            String to = dateSplit[1].substring(0, dateSplit[1].length() - 1);
            task = new Event(description, from, to);
        }
        if (currentTask.charAt(4) == 'X') {
            task.makeCompleted();
        }
        lstOfItems.add(task);
    }

    public void printContents() {
        for (int i = 0; i < lstOfItems.size(); i++) {
            System.out.print(String.valueOf(i + 1) + ": ");
            System.out.println(lstOfItems.get(i));
        }
    }
}
