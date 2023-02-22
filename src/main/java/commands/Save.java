package commands;
import tasks.Task;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Save implements Command {
    private File target;
    private ArrayList<Task> tasks;

    public Save(ArrayList<Task> tasks) {
//        this.target = target;
        this.tasks = tasks;
    }

    public String execute() {
        try {
            File dest = new File("data");
            dest.mkdir();
            File saveFile = new File(dest, "save.txt");
            if (saveFile.createNewFile()) {
                System.out.println("Save file created: " + saveFile.getName());
            } else {
                System.out.println("Save file already exists, overwriting...");
                saveFile.delete();
                saveFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(saveFile);
            for (Task task: tasks) {
                fileWriter.write(task.save());
            }
            fileWriter.close();
            System.out.println("Saved Successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Completed.";
    }
}
