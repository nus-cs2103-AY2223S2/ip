import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DukeFileWriter {

    public static void writeToFile(String path, TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            ArrayList<Task> taskArrayList = taskList.getTaskList();
            for (int i = 0; i < taskArrayList.size() - 1; i++) {
                fileWriter.write(taskArrayList.get(i).writeTask());
                fileWriter.write("\n");
            }
            fileWriter.write(taskArrayList.get(taskArrayList.size() - 1).writeTask());
            fileWriter.close();
            System.out.println("Saving your tasks my padawan, I am");
        } catch (IOException e) {
            System.out.println("There is an error writing to file!");
            System.out.println(e.getMessage());
        }
    }

}
