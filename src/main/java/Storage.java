import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private ArrayList<Task> actions = new ArrayList<Task>();

    public Storage(String filePath) {
        try {
            File f = new File(filePath);
            Scanner scanner = new Scanner(f);
            while (scanner.hasNext()) {
                String taskDetails = scanner.nextLine();
                String[] details = taskDetails.split(" |", 2);
                String taskType = details[0];
                String taskName = details[1];
                taskName = taskName.replace("|", "");
                switch (taskType) {
                    case "T" :
                        this.actions.add(new ToDo(taskName));
                        break;
                    case "E" :
                        this.actions.add(new Event(taskName));
                        break;
                    case "D" :
                        this.actions.add(new Deadline(taskName));
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Task> load() {
        return this.actions;
    }

}
