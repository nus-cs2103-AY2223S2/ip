import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String FILE_NAME = "berry.txt";
    private static final String FOLDER_NAME = "data";

    public void saveTasks(TaskBook tb) throws IOException {
        FileWriter writer = new FileWriter(createFile());
        for (Task task : tb.getList()) {
            writer.write(task.interpretTaskToString() + "\n");
        }
        writer.close();
    }

    public ArrayList<Task> retrieveTasks() throws FileNotFoundException {
        ArrayList<Task> listOfTasks = new ArrayList<Task>();

        Scanner sc = new Scanner(createFile());
        while (sc.hasNext()) {
            Task task = Task.interpretStringToTask(sc.nextLine());
            listOfTasks.add(task);
        }
        return listOfTasks;
    }

    private File createFile() {
        File dataFile = new File(FOLDER_NAME + "/" + FILE_NAME);
        File folder =  new File(FOLDER_NAME);
        try {
            folder.mkdir();
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("handle exception case");
        }
        return dataFile;
    }
}
