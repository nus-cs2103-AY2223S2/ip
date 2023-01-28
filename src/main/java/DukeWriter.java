import java.io.FileWriter;
import java.io.IOException;

public class DukeWriter {

    private static String tasks;

    public DukeWriter(String tasks) {
        DukeWriter.tasks = tasks;
    }

    private static void writeToFile(String filePath, String tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(tasks);
        fw.close();
    }

    public static void main(String[] args) {
        String filePath = "../../../../data/duke.txt";
        try {
            writeToFile(filePath, tasks);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}