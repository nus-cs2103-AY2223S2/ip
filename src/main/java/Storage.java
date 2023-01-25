import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String path;

    public Storage(String filePath) {
        this.path = "./src/main/data/duke.txt";
    }

    public ArrayList<Task> load() throws TaskListEmpty {
        ArrayList<Task> actions;
        try {
            actions = new ArrayList<Task>();
            Scanner scanner = new Scanner(new File(this.path));
            while (scanner.hasNext()) {
                String taskDetails = scanner.nextLine();
                String[] details = taskDetails.split(" |", 2);
                String taskType = details[0];
                String taskName = details[1];
                taskName = taskName.replace("|", "");
                switch (taskType) {
                case "T":
                    actions.add(new ToDo(taskName));
                    break;
                case "E":
                    actions.add(new Event(taskName));
                    break;
                case "D":
                    actions.add(new Deadline(taskName));
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new TaskListEmpty("");
        }
        return actions;
    }

    public void writeToFile(String input , String type) {
        try {
            FileWriter filewriter =  new FileWriter(this.path, true);
            String text = type + " |" + input;
            filewriter.write(System.lineSeparator());
            filewriter.write(text);
            filewriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
