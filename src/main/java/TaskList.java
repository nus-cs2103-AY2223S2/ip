import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks = new ArrayList<>();

    public String outputList() {
        StringBuilder result = new StringBuilder();
        for (int index = 0; index < this.tasks.size(); index++) {
            result.append((index == 0 ? "" : "\n") + (index + 1) + ". " + this.tasks.get(index).toString());
        }
        return result.toString();
    }

    public String countTasks() {
        return "Now you have " + this.tasks.size() + " task" + (this.tasks.size() == 1 ? "" : "s") + " in the list.";
    }

    public Task removeTask(String command) {
        int index = Integer.parseInt(command.split(" ")[1]) - 1;
        return this.tasks.remove(index);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public void saveToFile() throws IOException {

        String strPath = "../../../data";
        // create directory
        Path path = Paths.get(strPath);
        Files.createDirectories(path);
    
        // output string to file
        PrintWriter out = new PrintWriter(strPath + "/duke.txt");
        out.println(this.outputList());
        out.close();

    }

    public void loadFromFile() throws IOException {
        Path fileName = Path.of("../../../data/duke.txt");
        String strData = Files.readString(fileName);

        String[] strTasks = strData.split("\n");
        for (String strTask : strTasks) {
            String[] info1 = strTask.split("\\[");
            char taskType = info1[1].charAt(0);
            boolean isDone = info1[2].charAt(0) == 'x';

            String[] info2 = info1[2].split(" \\(");

            Task task;
            String taskName = info2[0].substring(3);;
            switch (taskType) {
            case ('T'):
                task = new ToDo(taskName, isDone);
                this.tasks.add(task);
                break;
            case ('D'):
                String dueDate = info2[1].substring(4, info2[1].length() -1);
                task = new Deadline(taskName, dueDate, isDone);
                this.tasks.add(task);
                break;
            case ('E'):
                String[] info3 = info2[1].split(" to: ");
                String fromDate = info3[0].substring(6);
                String toDate = info3[1].substring(0, info3[1].length() - 1);
                task = new Event(taskName, fromDate, toDate, isDone);
                this.tasks.add(task);
                break;
            }
        }
    }
}
