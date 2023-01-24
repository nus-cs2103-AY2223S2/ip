import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private List<Task> listOfTasks = new ArrayList<>();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String str;
        for (Task task : listOfTasks) {
            str = task.toString();
            str = str.replace("[ ]", " | 0 |");
            str = str.replace("[X]", " | 1 |");
            str = str.replace("[", "");
            str = str.replace("]", "");
            if (str.startsWith("D")) {
                str = str.replace("(by:", "|");
                str = str.replace(")", "");
            } else if (str.startsWith("E")) {
                str = str.replace("(from:", "|");
                str = str.replace(" to: ", "-");
                str = str.replace(")", "");
            }
            str = str + "\n";
            fw.write(str);
        }
        fw.close();
    }

    public List<Task> load() throws FileNotFoundException {
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            Task task;
            String description, str, taskInfo;
            while (s.hasNext()) {
                str = s.nextLine();
                taskInfo = str.substring(8);
                if (str.startsWith("T")) {
                    description = taskInfo;
                    task = new Todo(description);
                } else if (str.startsWith("D")) {
                    int byIdx = taskInfo.indexOf("|");
                    description = taskInfo.substring(0, byIdx - 1);
                    String by = taskInfo.substring(byIdx + 2);
                    task = new Deadline(description, by);
                } else {
                    int fromIdx = taskInfo.indexOf("|");
                    int toIdx = taskInfo.indexOf("-");
                    description = taskInfo.substring(0, fromIdx - 1);
                    String from = taskInfo.substring(fromIdx + 2, toIdx);
                    String to = taskInfo.substring(toIdx + 1);
                    task = new Event(description, from, to);
                }
                if (str.charAt(4) == '1') {
                    task.markAsDone();
                }
                listOfTasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return listOfTasks;
    }
}















