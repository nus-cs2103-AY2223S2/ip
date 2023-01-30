import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Save {
    public static List<Task> loadTaskList() throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();

        // check if file exists
        File f = new File("./data/data.txt");

        if (f.exists()) {
            Scanner s = new Scanner(f);
            Task task;

            while (s.hasNextLine()) {
                String currTask = s.nextLine();
                String[] taskSplit = currTask.split(" \\| ");

                for (String c : taskSplit) {
                    System.out.println(c);
                }

                if (taskSplit[0].equals("T")) {
                    task = new Todo(taskSplit[2]);
                } else if (taskSplit[0].equals("D")) {
                    task = new Deadline(taskSplit[2], taskSplit[3]);
                } else {
                    String[] eventTime = taskSplit[3].split(" - ");
                    task = new Event(taskSplit[2], eventTime[0], eventTime[1]);
                }

                if (Integer.parseInt(taskSplit[1]) == 1) {
                    task.markAsDone();
                }

                tasks.add(task);
            }
        }

        return tasks;
    }

    public static void saveTaskList(List<Task> taskList) throws IOException {
        // check if directory exists
        File dir = new File("./data");
        if (!dir.exists()) dir.mkdirs();

        // check if file exists
        File f = new File("./data/data.txt");
        if (!f.isFile()) {
            f.createNewFile();
        }

        PrintWriter output = new PrintWriter("./data/data.txt");

        for (Task task : taskList) {
            if (task instanceof Todo) {
                output.printf("T | %d | %s%n", task.getStatusIconInt(), task.getDescription());
            } else if (task instanceof Deadline) {
                output.printf("D | %d | %s | %s%n", task.getStatusIconInt(), task.getDescription(), ((Deadline) task).getBy());
            } else if (task instanceof Event) {
                output.printf("E | %d | %s | %s - %s%n", task.getStatusIconInt(), task.getDescription(), ((Event) task).getFrom(), ((Event) task).getTo());
            }
        }

        output.close();
    }
}
