import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Save {
    private static String readerFriendly(Task task) {
        String status = task.getStatus() ? "๑(◕‿◕)๑ COMPLETED! ๑(◕‿◕)๑" : "(｡-_-｡ ) INCOMPLETE ( ｡-_-｡)";
        if (task instanceof Todo) {
            Todo t = (Todo) task;
            return "Todo: " + t.getDescription() + " " + status;
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return "Deadline: " + d.getDescription() + " by " + d.getBy() + " " + status;
        } else { // instance of Event
            Event e = (Event) task;
            return "Event: " + e.getDescription() + " from " + e.getFrom() + " to " + e.getTo() + " " + status;
        }
    }


    public static void autoSave(ArrayList<Task> listOfTasks) throws IOException{
        // Get the directory of from our root.
        String root = Paths.get(".").toAbsolutePath().normalize().toString();
        // Make sure that it is independent of the OS.
        String PATH = java.nio.file.Paths.get(root,"src", "data", "duke.txt").toString();

        // Ensures that even if the file does not exist, a new one is created each time.
        File f = new File(PATH);
        FileWriter fw = new FileWriter(f.getAbsolutePath());
        try {
            if (listOfTasks.size() == 0) {
                fw.write("");
            } else {
                for (int i = 0; i < listOfTasks.size(); i++) {
                    Task task = listOfTasks.get(i);
                    fw.write(Save.readerFriendly(task));
                    fw.write(System.lineSeparator()); // new line
                }
                fw.close();
            }
        } catch (IOException e) {
            System.out.println("Unable to write to file, check path: " + PATH);
        }
    }
}
