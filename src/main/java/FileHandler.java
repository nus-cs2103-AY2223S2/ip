import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

public class FileHandler {

    public static void saveTasks(ToDoList todolist, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (int i = 0; i < todolist.getCount(); i++) {
            fw.write(todolist.getTask(i) + System.lineSeparator());
        }
        fw.close();
    }

    public static void loadTasks(ToDoList todolist, String filePath) throws FileNotFoundException {
        System.out.println("\tTasks from the previous session:\n");
        System.out.println("\t--------------------------");
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String curr = s.nextLine();
            String taskType = curr.substring(1, 2);
            System.out.println("\t" + curr);

            if (taskType.equals("T")) {
                todolist.add(curr.substring(7));
            } else if (taskType.equals("D")) {
                int firstColon = curr.indexOf(":");
                int firstBracket = curr.indexOf("(");
                String task = curr.substring(7, firstBracket - 1);
                String time = curr.substring(firstColon + 2, curr.length() - 1);
                todolist.add(task, time);
            } else if (taskType.equals("E")) { // event task
                int firstColon = curr.indexOf(":");
                int secondColon = curr.substring(firstColon + 1).indexOf(":") + firstColon + 1;
                int firstBracket = curr.indexOf("(");
                String startTime = curr.substring(firstColon + 1, secondColon - 6);
                String endTime = curr.substring(secondColon + 1, curr.length() - 1);
                String task = curr.substring(7, firstBracket - 1);
                todolist.add(task, startTime, endTime);
            }
        }
        System.out.println("\t--------------------------");
    }
}
